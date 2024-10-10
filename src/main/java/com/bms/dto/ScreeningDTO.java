package com.bms.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ScreeningDTO extends TheatreDTO {
    private List<ScreenShowtimeDTO> showtimeDetails;
}
