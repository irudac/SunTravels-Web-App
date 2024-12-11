package com.suntravels.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "contract")
public class Contract
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contract_id")
    private Long contractId;

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @Column(name = "valid_from", nullable = false)
    private LocalDate validFrom;

    @Column(name = "valid_until", nullable = false)
    private LocalDate validUntil;

    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoomType> roomTypes;

    public Long getContractId()
    {
        return contractId;
    }

    public void setContractId( Long contractId )
    {
        this.contractId = contractId;
    }

    public Hotel getHotel()
    {
        return hotel;
    }

    public void setHotel( Hotel hotel )
    {
        this.hotel = hotel;
    }

    public LocalDate getValidFrom()
    {
        return validFrom;
    }

    public void setValidFrom( LocalDate validFrom )
    {
        this.validFrom = validFrom;
    }

    public LocalDate getValidUntil()
    {
        return validUntil;
    }

    public void setValidUntil( LocalDate validUntil )
    {
        this.validUntil = validUntil;
    }

    public List<RoomType> getRoomTypes()
    {
        return roomTypes;
    }

    public void setRoomTypes( List<RoomType> roomTypes )
    {
        this.roomTypes = roomTypes;
    }
}
