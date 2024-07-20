package org.example.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.entity.Category;

import java.time.Instant;

@NoArgsConstructor
@Getter
@Setter
public class NewsDto {
    private Long id;
    private String title;
    private String text;
    private Instant date;
    private String category;
}
