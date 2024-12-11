package com.suntravels.backend.util;

import com.suntravels.backend.dto.RoomTypeDto;
import com.suntravels.backend.dto.response.RoomsResponseDto;
import com.suntravels.backend.model.Contract;
import com.suntravels.backend.model.RoomType;

public class RoomTypeMapper
{
    // Private constructor to prevent instantiation
    private RoomTypeMapper() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static RoomType dtoToEntity( RoomTypeDto roomTypeDto, Contract contract ) {
        RoomType roomType = new RoomType();
        roomType.setContract( contract );
        roomType.setTypeName( roomTypeDto.getTypeName() );
        roomType.setNoOfRoomsAvailable( roomTypeDto.getNoOfRoomsAvailable() );
        roomType.setMaxAdults( roomTypeDto.getMaxAdults() );
        roomType.setPricePerPerson( roomTypeDto.getPricePerPerson() );
        return roomType;
    }

    public static RoomTypeDto entityToDto( RoomType roomType ) {
        RoomTypeDto roomTypeDto = new RoomTypeDto();
        roomTypeDto.setTypeId( roomType.getTypeId() );
        roomTypeDto.setTypeName( roomType.getTypeName() );
        roomTypeDto.setNoOfRoomsAvailable( roomType.getNoOfRoomsAvailable() );
        roomTypeDto.setPricePerPerson( roomType.getPricePerPerson() );
        roomTypeDto.setMaxAdults( roomType.getMaxAdults() );
        roomTypeDto.setContract( ContractMapper.entityToDto( roomType.getContract() ) );
        return roomTypeDto;
    }

    // Add a new method in RoomTypeMapper to avoid circular dependency
    public static RoomTypeDto entityToDtoWithoutContract(RoomType roomType) {
        RoomTypeDto roomTypeDto = new RoomTypeDto();
        roomTypeDto.setTypeId( roomType.getTypeId() );
        roomTypeDto.setTypeName( roomType.getTypeName() );
        roomTypeDto.setNoOfRoomsAvailable( roomType.getNoOfRoomsAvailable() );
        roomTypeDto.setPricePerPerson( roomType.getPricePerPerson() );
        roomTypeDto.setMaxAdults( roomType.getMaxAdults() );
        return roomTypeDto;
    }

    public static RoomsResponseDto entityToSearchResDto(RoomType roomType, int numberOfRoomsRequested) {
        RoomsResponseDto roomsResponseDto = new RoomsResponseDto( roomType.getTypeId(), roomType.getPricePerPerson(), roomType.getMaxAdults(), numberOfRoomsRequested);
        roomsResponseDto.setTypeName( roomType.getTypeName() );
        return roomsResponseDto;
    }
}
