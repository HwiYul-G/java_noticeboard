package com.example.noticeboard.domain.repository;

import com.example.noticeboard.domain.dto.ContentDTO;
import com.example.noticeboard.domain.entities.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {

}
