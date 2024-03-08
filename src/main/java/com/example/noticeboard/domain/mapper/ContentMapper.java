package com.example.noticeboard.domain.mapper;

import com.example.noticeboard.domain.dto.ContentDTO;
import com.example.noticeboard.domain.entities.Content;

public class ContentMapper {
    public static ContentDTO toDto(Content entity) {
        return new ContentDTO(entity.getTitle(), entity.getDescription(), entity.getWriter(), entity.getPassword(), entity.getCreatedAt(), entity.getModifiedAt());
    }

    public static Content toEntity(ContentDTO dto) {
        return new Content(dto.title(), dto.description(), dto.writer(), dto.password());
    }
}
