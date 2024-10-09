package com.bms.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private Long id;
    private String name;
    private String password;
    private String phone;
    private String email;
    private Role role;
    private List<Booking> bookings;
    public enum Role {
        ADMIN, USER
    }
}


