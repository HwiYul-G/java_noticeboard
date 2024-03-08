package com.example.noticeboard.domain.entities;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContentTest {

    @Test
    void whenCreatingEntity_ThenCreatedOnAndLastUpdatedOnAreBothSet() {
        Content content = new Content("제목", "설명", "저자", "비밀 번호");

        assertNotNull(content.getCreatedAt());
        assertNotNull(content.getModifiedAt());
    }


}