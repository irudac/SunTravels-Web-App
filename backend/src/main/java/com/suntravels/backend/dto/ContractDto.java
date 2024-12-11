package com.suntravels.backend.dto;

import java.time.LocalDate;
import java.util.List;

public class ContractDto
{
    private Long contractId;
    private HotelDto hotel;
    private LocalDate validFrom;
    private LocalDate validUntil;
    private List<RoomTypeDto> roomTypes;

    public Long getContractId()
    {
        return this.contractId;
    }

    public HotelDto getHotel()
    {
        return this.hotel;
    }

    public LocalDate getValidFrom()
    {
        return this.validFrom;
    }

    public LocalDate getValidUntil()
    {
        return this.validUntil;
    }

    public List<RoomTypeDto> getRoomTypes()
    {
        return this.roomTypes;
    }

    public void setContractId( Long contractId )
    {
        this.contractId = contractId;
    }

    public void setHotel( HotelDto hotel )
    {
        this.hotel = hotel;
    }

    public void setValidFrom( LocalDate validFrom )
    {
        this.validFrom = validFrom;
    }

    public void setValidUntil( LocalDate validUntil )
    {
        this.validUntil = validUntil;
    }

    public void setRoomTypes( List<RoomTypeDto> roomTypes )
    {
        this.roomTypes = roomTypes;
    }
}
