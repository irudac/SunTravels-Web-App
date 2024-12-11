package com.suntravels.backend.repository;

import com.suntravels.backend.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HotelRepository extends JpaRepository<Hotel, Long>
{
    // find hotel object using hotel name
    Optional<Hotel> findByHotelName(String hotelName);
}
