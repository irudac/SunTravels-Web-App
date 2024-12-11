package com.suntravels.backend.service;

import com.suntravels.backend.dto.ContractDto;
import com.suntravels.backend.dto.RoomTypeDto;
import com.suntravels.backend.dto.request.RoomsRequestDto;
import com.suntravels.backend.dto.request.SearchRequestDto;
import com.suntravels.backend.dto.response.RoomsResponseDto;
import com.suntravels.backend.dto.response.SearchResponseDto;
import com.suntravels.backend.model.Contract;
import com.suntravels.backend.model.Hotel;
import com.suntravels.backend.model.RoomType;
import com.suntravels.backend.repository.ContractRepository;
import com.suntravels.backend.repository.HotelRepository;
import com.suntravels.backend.repository.RoomTypeRepository;
import com.suntravels.backend.util.ContractMapper;
import com.suntravels.backend.util.HotelMapper;
import com.suntravels.backend.util.RoomTypeMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ContractServices
{

    private final ContractRepository contractRepository;
    private final HotelRepository hotelRepository;
    private final RoomTypeRepository roomTypeRepository;

    public ContractServices(ContractRepository contractRepository, HotelRepository hotelRepository, RoomTypeRepository roomTypeRepository) {
        this.contractRepository = contractRepository;
        this.hotelRepository = hotelRepository;
        this.roomTypeRepository = roomTypeRepository;
    }

    public List<ContractDto> getAllContracts()
    {
        List<Contract> contracts = contractRepository.findAll();
        return contracts.stream().map(ContractMapper::entityToDto).collect( Collectors.toList() );
    }

    public ContractDto getContract( Long id )
    {
        Contract contract = contractRepository.findById( id ).orElseThrow( () -> new NoSuchElementException( "Contract not found with id " + id ) );
        return ContractMapper.entityToDto( contract );
    }

    public ContractDto createContract( ContractDto contractDto )
    {
        Hotel hotel = hotelRepository.findByHotelName( contractDto.getHotel().getHotelName() ).orElseGet( () -> HotelMapper.dtoToEntity( contractDto.getHotel() ) );
        hotelRepository.save( hotel );
        Contract contract = ContractMapper.dtoToEntity( contractDto, hotel );
        contractRepository.save( contract );
        List<RoomTypeDto> roomTypeDtos = contractDto.getRoomTypes();
        List<RoomType> roomTypes = roomTypeDtos.stream().map( roomTypeDto -> saveRoomTypes( roomTypeDto, contract )).collect( Collectors.toList() );
        contract.setRoomTypes( roomTypes );
        return ContractMapper.entityToDto( contract );
    }

    public List<SearchResponseDto> searchContracts( SearchRequestDto searchRequestDto )
    {
        LocalDate checkIn = searchRequestDto.getCheckInDate();
        LocalDate checkOut = checkIn.plusDays( searchRequestDto.getNumOfNights() );
        List<RoomsRequestDto> roomsRequestDtos = searchRequestDto.getRoomsRequiredList();

        List<Contract> validContractWithinDuration = contractRepository.searchValidContractWithinDuration( checkIn, checkOut );
        List<List<RoomType>> roomsForValidContracts = validContractWithinDuration.stream().map( validContract -> fulfillRoomRequirements( validContract, roomsRequestDtos ) ).collect( Collectors.toList() );

        List<SearchResponseDto> searchResponseDtos = new java.util.ArrayList<>( List.of() );

        for (int i = 0; i < validContractWithinDuration.size(); i++) {
            Contract contract = validContractWithinDuration.get(i);
            List<RoomType> roomTypes = roomsForValidContracts.get(i);
            SearchResponseDto searchResponseDto = new SearchResponseDto();

            if (!roomTypes.contains(null)) {
                List<RoomsResponseDto> roomList = IntStream.range(0, roomTypes.size())
                                                           .mapToObj(index -> RoomTypeMapper.entityToSearchResDto(roomTypes.get(index), roomsRequestDtos.get(index).getNumOfRooms()))
                                                           .collect(Collectors.toList());
                searchResponseDto.setRoomList(roomList);
            }

            searchResponseDto.setHotel( HotelMapper.entityToDtoWithoutContract( contract.getHotel() ) );
            searchResponseDto.setTotalPrice();
            searchResponseDtos.add( searchResponseDto );
        }
        return searchResponseDtos;
    }

    private RoomType saveRoomTypes(RoomTypeDto roomTypeDto, Contract contract)
    {
        RoomType roomType = RoomTypeMapper.dtoToEntity( roomTypeDto, contract );
        roomTypeRepository.save( roomType );
        return roomType;
    }

    private List<RoomType> fulfillRoomRequirements( Contract validContract, List<RoomsRequestDto> roomsRequestDtos )
    {
        return roomsRequestDtos.stream().map( roomsRequestDto -> {
            List<RoomType> roomTypes = roomTypeRepository.searchTypeIdsFulfillRoomRequirements( validContract, roomsRequestDto.getAdultsPerRoom(), roomsRequestDto.getNumOfRooms() );
            return roomTypes.stream()
                            .reduce((room1, room2) ->
                                                                 room1.getPricePerPerson().multiply( BigDecimal.valueOf(room1.getMaxAdults()))
                                                                      .compareTo(room2.getPricePerPerson().multiply(BigDecimal.valueOf(room2.getMaxAdults()))) < 0 ? room1 : room2)
                            .orElse(null);
        } ).collect( Collectors.toList() );
    }
}
