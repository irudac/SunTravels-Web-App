package com.suntravels.backend.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table( name = "hotel" )
public class Hotel
{

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "hotel_id" )
    private Long hotelId;

    @Column( name = "hotel_name", nullable = false )
    private String hotelName;

    @OneToMany( mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true )
    private List<Contract> contracts;

    public Long getHotelId()
    {
        return this.hotelId;
    }

    public String getHotelName()
    {
        return this.hotelName;
    }

    public List<Contract> getContracts()
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

    public void setContracts( List<Contract> contracts )
    {
        this.contracts = contracts;
    }
}
