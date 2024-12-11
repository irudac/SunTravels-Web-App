package com.suntravels.backend.dto.response;

import java.math.BigDecimal;

public class RoomsResponseDto
{
    private final Long typeId;
    private String typeName;
    private final BigDecimal pricePerPersonMarkedUp;
    private final int maxAdults;
    private final int numberOfRoomsRequested;
    private final BigDecimal totalPriceForAllSameTypeRooms;

    public RoomsResponseDto( Long typeId, BigDecimal pricePerPersonMarkedUp, int maxAdults, int numberOfRoomsRequested )
    {
        this.typeId = typeId;
        this.pricePerPersonMarkedUp = pricePerPersonMarkedUp;
        this.maxAdults = maxAdults;
        this.numberOfRoomsRequested = numberOfRoomsRequested;
        if (pricePerPersonMarkedUp == null) {
            this.totalPriceForAllSameTypeRooms =  BigDecimal.ZERO;
        } else
        {
            this.totalPriceForAllSameTypeRooms = pricePerPersonMarkedUp.multiply( BigDecimal.valueOf( maxAdults ) )
                                                                       .multiply( BigDecimal.valueOf( numberOfRoomsRequested ) );
        }
    }

    public void setTypeName( String typeName )
    {
        this.typeName = typeName;
    }

    public Long getTypeId()
    {
        return typeId;
    }

    public String getTypeName()
    {
        return typeName;
    }

    public BigDecimal getPricePerPersonMarkedUp()
    {
        return pricePerPersonMarkedUp;
    }

    public int getMaxAdults()
    {
        return maxAdults;
    }

    public int getNumberOfRoomsRequested()
    {
        return numberOfRoomsRequested;
    }

    public BigDecimal getTotalPriceForAllSameTypeRooms()
    {
        return totalPriceForAllSameTypeRooms;
    }
}
