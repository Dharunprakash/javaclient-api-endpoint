package com.bms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingDTO {
    private Long id;
    private Long userId;
    private String theatreName;
    private Long showtimeId;
    private Float price;
    private Long screenId;
    private String screenName;
    private Long movieId;
    private String movieName;

    private LocalDateTime showTime;
    private List<Integer> bookedSeats;

}