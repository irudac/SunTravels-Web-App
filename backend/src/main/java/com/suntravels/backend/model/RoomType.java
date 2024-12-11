package com.suntravels.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "room_type")
public class RoomType
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private Long typeId;

    @ManyToOne
    @JoinColumn(name = "contract_id", nullable = false)
    private Contract contract;

    @Column(name = "type_name", nullable = false)
    private String typeName;

    @Column(name = "no_of_rooms_available", nullable = false)
    private int noOfRoomsAvailable;

    @Column(name = "price_per_person", nullable = false, precision = 10, scale = 2)
    private BigDecimal pricePerPerson;

    @Column(name = "max_adults", nullable = false)
    private int maxAdults;

    public Long getTypeId()
    {
        return typeId;
    }

    public void setTypeId( Long typeId )
    {
        this.typeId = typeId;
    }

    public Contract getContract()
    {
        return contract;
    }

    public void setContract( Contract contract )
    {
        this.contract = contract;
    }

    public String getTypeName()
    {
        return typeName;
    }

    public void setTypeName( String typeName )
    {
        this.typeName = typeName;
    }

    public int getNoOfRoomsAvailable()
    {
        return noOfRoomsAvailable;
    }

    public void setNoOfRoomsAvailable( int noOfRoomsAvailable )
    {
        this.noOfRoomsAvailable = noOfRoomsAvailable;
    }

    public BigDecimal getPricePerPerson()
    {
        return pricePerPerson;
    }

    public void setPricePerPerson( BigDecimal pricePerPerson )
    {
        this.pricePerPerson = pricePerPerson;
    }

    public int getMaxAdults()
    {
        return maxAdults;
    }

    public void setMaxAdults( int maxAdults )
    {
        this.maxAdults = maxAdults;
    }
}
