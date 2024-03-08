package com.example.noticeboard.domain.controller;

import com.example.noticeboard.domain.dto.ContentDTO;
import com.example.noticeboard.domain.entities.Content;
import com.example.noticeboard.domain.mapper.ContentMapper;
import com.example.noticeboard.domain.repository.ContentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Controller
public class ContentController {
    private final ContentRepository contentRepository;

    public ContentController(ContentRepository contentRepository){
        this.contentRepository = contentRepository;
    }


    @GetMapping
    public Iterable<ContentDTO> findAll() {
        return contentRepository.findAll().stream().map(ContentMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ContentDTO findOne(@PathVariable long id) {
        Content content = contentRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
        return ContentMapper.toDto(content);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContentDTO create(@RequestBody ContentDTO contentDTO) {
        return ContentMapper.toDto(contentRepository.save(ContentMapper.toEntity(contentDTO)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        contentRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        contentRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public ContentDTO updateContent(@RequestBody ContentDTO contentDTO, @PathVariable long id) throws IllegalAccessException {
        if (ContentMapper.toEntity(contentDTO).getId() != id) {
            throw new IllegalAccessException("게시글의 id와 updated content의 id가 다릅니다. ");
        }
        contentRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return ContentMapper.toDto(contentRepository.save(ContentMapper.toEntity(contentDTO)));
    }

}
