package com.example.noticeboard.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "content")
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private String password;

    @Column(updatable = false, nullable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    public Content() {
        super();
    }

    public Content(String title, String description, String writer, String password){
        this.title = title;
        this.description = description;
        this.writer = writer;
        this.password = password;
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
    }

    public Content(String title, String description, String writer, String password, LocalDateTime createdAt) {
        this.title = title;
        this.description = description;
        this.writer = writer;
        this.password = password;
        this.createdAt = createdAt;
        updatedModifiedAt();
    }

    private void updatedModifiedAt(){
        this.modifiedAt = LocalDateTime.now();
    }

}
