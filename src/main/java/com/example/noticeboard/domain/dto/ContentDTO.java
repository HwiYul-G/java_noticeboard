package com.example.noticeboard.domain.dto;

import java.time.LocalDateTime;

public record ContentDTO(
        String title,
        String description,
        String writer,
        String password,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
}
