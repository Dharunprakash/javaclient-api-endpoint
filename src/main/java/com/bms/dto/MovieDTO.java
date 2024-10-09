package com.bms.dto;
import com.bms.model.Movie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO{
    private Long id;
    private String name;
    private String language;
    private String genre;
    private String duration;
    private String releaseDate;
    private Float rating;


}
