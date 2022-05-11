package com.gabia.gnbapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "titles")
public class Title {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonManagedReference
    @OneToMany(mappedBy = "title", fetch = LAZY)
    private List<Detail> details = new ArrayList<>();

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "headline_id")
    private HeadLine headLine;

    @Column(name = "title", columnDefinition = "varchar(128)", nullable = false)
    private String name;
    @Column(columnDefinition = "varchar(128)")
    private String desktopWidth;

    private int order;
    private int changePointer;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    @PrePersist
    public void createdAt() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    public void updateAt() {
        this.updatedAt = LocalDateTime.now();
    }
}
