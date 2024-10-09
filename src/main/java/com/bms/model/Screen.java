package com.bms.model;

import lombok.*;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Screen {
    private Long id;
    private String name;
    private Long theaterId;
    private List<Seat> seats;
    private List<Showtime> showtimes;
}
