package com.suntravels.backend.dto;

import java.math.BigDecimal;

public class RoomTypeDto
{
    private Long typeId;
    private ContractDto contract;
    private String typeName;
    private int noOfRoomsAvailable;
    private BigDecimal pricePerPerson;
    private int maxAdults;

    public Long getTypeId()
    {
        return this.typeId;
    }

    public ContractDto getContract()
    {
        return this.contract;
    }

    public String getTypeName()
    {
        return this.typeName;
    }

    public int getNoOfRoomsAvailable()
    {
        return this.noOfRoomsAvailable;
    }

    public BigDecimal getPricePerPerson()
    {
        return this.pricePerPerson;
    }

    public int getMaxAdults()
    {
        return this.maxAdults;
    }

    public void setTypeId( Long typeId )
    {
        this.typeId = typeId;
    }

    public void setContract( ContractDto contract )
    {
        this.contract = contract;
    }

    public void setTypeName( String typeName )
    {
        this.typeName = typeName;
    }

    public void setNoOfRoomsAvailable( int noOfRoomsAvailable )
    {
        this.noOfRoomsAvailable = noOfRoomsAvailable;
    }

    public void setPricePerPerson( BigDecimal pricePerPerson )
    {
        this.pricePerPerson = pricePerPerson;
    }

    public void setMaxAdults( int maxAdults )
    {
        this.maxAdults = maxAdults;
    }
}
