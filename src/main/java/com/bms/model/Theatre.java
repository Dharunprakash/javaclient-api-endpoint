package com.bms.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Theatre {

    private Long id;
    private String name;
    private String location;
    private String address;
    private List<Screen> screens;
}
