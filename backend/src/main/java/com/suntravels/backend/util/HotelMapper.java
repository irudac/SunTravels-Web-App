package com.suntravels.backend.util;

import com.suntravels.backend.dto.HotelDto;
import com.suntravels.backend.model.Hotel;

import java.util.stream.Collectors;

public class HotelMapper
{
    // Private constructor to prevent instantiation
    private HotelMapper() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static Hotel dtoToEntity( HotelDto hotelDto )
    {
        Hotel hotel = new Hotel();
        hotel.setHotelName( hotelDto.getHotelName() );
        return hotel;
    }

    public static HotelDto entityToDto( Hotel hotel ) {
        HotelDto hotelDto = new HotelDto();
        hotelDto.setHotelId( hotel.getHotelId() );
        hotelDto.setHotelName( hotel.getHotelName() );
        if (hotel.getContracts() != null) {
            hotelDto.setContracts(  hotel.getContracts().stream().map(ContractMapper::entityToDtoWithoutHotel).collect(Collectors.toList()) );
        }
        return hotelDto;
    }

    public static HotelDto entityToDtoWithoutContract( Hotel hotel ) {
        HotelDto hotelDto = new HotelDto();
        hotelDto.setHotelId( hotel.getHotelId() );
        hotelDto.setHotelName( hotel.getHotelName() );
        return hotelDto;
    }

}
