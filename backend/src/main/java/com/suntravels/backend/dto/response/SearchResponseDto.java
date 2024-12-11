package com.suntravels.backend.dto.response;

import com.suntravels.backend.dto.HotelDto;

import java.math.BigDecimal;
import java.util.List;

public class SearchResponseDto
{
    private HotelDto hotel;
    private List<RoomsResponseDto> roomList;
    private BigDecimal totalPrice;

    public void setHotel( HotelDto hotel )
    {
        this.hotel = hotel;
    }

    public void setRoomList( List<RoomsResponseDto> roomList )
    {
        this.roomList = roomList;
    }

    public void setTotalPrice() {
        this.totalPrice = roomList.stream()
                                  .map(RoomsResponseDto::getTotalPriceForAllSameTypeRooms) // Extract the total price for each room
                                  .reduce(BigDecimal.ZERO, BigDecimal::add);  // Sum them up, starting with BigDecimal.ZERO
    }

    public HotelDto getHotel()
    {
        return hotel;
    }

    public List<RoomsResponseDto> getRoomList()
    {
        return roomList;
    }

    public BigDecimal getTotalPrice()
    {
        return totalPrice;
    }
}

