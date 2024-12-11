package com.suntravels.backend.repository;

import com.suntravels.backend.model.Contract;
import com.suntravels.backend.model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomTypeRepository extends JpaRepository<RoomType, Long>
{
    @Query("""
        SELECT r
        FROM RoomType r
        WHERE r.contract = :contract
          AND r.maxAdults >= :adultsPerRoom
          AND r.noOfRoomsAvailable >= :numOfRooms
    """)
    List<RoomType> searchTypeIdsFulfillRoomRequirements(
            @Param("contract") Contract contract,
            @Param("adultsPerRoom") int adultsPerRoom,
            @Param("numOfRooms") int numOfRooms
    );

}
