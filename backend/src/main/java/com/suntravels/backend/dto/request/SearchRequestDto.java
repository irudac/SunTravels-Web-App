package com.suntravels.backend.dto.request;

import java.time.LocalDate;
import java.util.List;

public class SearchRequestDto
{
    private LocalDate checkInDate;
    private int numOfNights;
    private List<RoomsRequestDto> roomsRequiredList;

    public LocalDate getCheckInDate()
    {
        return checkInDate;
    }

    public int getNumOfNights()
    {
        return numOfNights;
    }

    public List<RoomsRequestDto> getRoomsRequiredList()
    {
        return roomsRequiredList;
    }
}