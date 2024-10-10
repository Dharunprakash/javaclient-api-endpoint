package com.bms.dto;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ScreenDTO {
    private Long id;
    private String name;
    private Long theaterId;
}
