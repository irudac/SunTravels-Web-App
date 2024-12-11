package com.suntravels.backend.dto;

import java.util.List;

public class HotelDto
{
    private Long hotelId;
    private String hotelName;
    private List<ContractDto> contracts;

    public Long getHotelId()
    {
        return this.hotelId;
    }

    public String getHotelName()
    {
        return this.hotelName;
    }

    public List<ContractDto> getContracts()
    {
        return this.contracts;
    }

    public void setHotelId( Long hotelId )
    {
        this.hotelId = hotelId;
    }

    public void setHotelName( String hotelName )
    {
        this.hotelName = hotelName;
    }

    public void setContracts( List<ContractDto> contracts )
    {
        this.contracts = contracts;
    }
}
