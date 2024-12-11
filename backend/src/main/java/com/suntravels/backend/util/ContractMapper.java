package com.suntravels.backend.util;

import com.suntravels.backend.dto.ContractDto;
import com.suntravels.backend.model.Contract;
import com.suntravels.backend.model.Hotel;

import java.util.stream.Collectors;

public class ContractMapper
{
    // Private constructor to prevent instantiation
    private ContractMapper() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static Contract dtoToEntity( ContractDto contractDto, Hotel hotel) {
        Contract contract = new Contract();
        contract.setValidUntil( contractDto.getValidUntil() );
        contract.setValidFrom( contractDto.getValidFrom() );
        contract.setHotel( hotel );
        return contract;
    }

    public static ContractDto entityToDto( Contract contract ) {
        ContractDto contractDto = new ContractDto();
        contractDto.setContractId( contract.getContractId() );
        contractDto.setHotel( HotelMapper.entityToDto( contract.getHotel() ) );
        contractDto.setValidFrom( contract.getValidUntil() );
        contractDto.setValidUntil( contract.getValidUntil() );
        if ( contract.getRoomTypes() != null ) {
            contractDto.setRoomTypes( contract.getRoomTypes().stream().map( RoomTypeMapper::entityToDtoWithoutContract ).collect( Collectors.toList() ) );
        }
        return contractDto;
    }

    public static ContractDto entityToDtoWithoutHotel( Contract contract ) {
        ContractDto contractDto = new ContractDto();
        contractDto.setContractId( contract.getContractId() );
        contractDto.setValidFrom( contract.getValidUntil() );
        contractDto.setValidUntil( contract.getValidUntil() );
        if ( contract.getRoomTypes() != null ) {
            contractDto.setRoomTypes( contract.getRoomTypes().stream().map( RoomTypeMapper::entityToDtoWithoutContract ).collect( Collectors.toList() ) );
        }
        return contractDto;
    }

}