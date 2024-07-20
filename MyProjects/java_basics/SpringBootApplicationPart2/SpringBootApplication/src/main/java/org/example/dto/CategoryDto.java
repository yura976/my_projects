package org.example.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = "news")
public class CategoryDto {
    private Long id;
    private String title;
    @JsonIgnore
    private List<NewsDto> news;
    public CategoryDto(String title) {
        this.title = title;
    }
}
