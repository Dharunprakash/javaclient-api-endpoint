package com.bms.dto;

import com.bms.model.Theatre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TheatreDTO {

    private Long id;
    private String name;
    private String location;
    private String address;

}
