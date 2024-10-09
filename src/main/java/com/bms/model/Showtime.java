package com.bms.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Showtime {
    private Long id;
    private Long screenId;
    private Long movieId;
    private LocalDateTime startTime;
    private Float price;
    private Integer availableSeats;
    private List<Booking> bookings;
}

