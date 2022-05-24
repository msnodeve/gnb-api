package com.gabia.gnbapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AccessLevel;
import lombok.Builder;
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

    @Column(name = "`order`")
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

    @Builder
    public Title(Long id, List<Detail> details, HeadLine headLine, String name, String desktopWidth, int order, int changePointer, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt) {
        this.id = id;
        this.details = details;
        this.headLine = headLine;
        this.name = name;
        this.desktopWidth = desktopWidth;
        this.order = order;
        this.changePointer = changePointer;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public void updateHeadLine(HeadLine headLine) {
        this.headLine = headLine;
    }

    public void updateTitle(Title title) {
        this.name = title.getName().isEmpty() ? this.name : title.getName();
        this.desktopWidth = title.getDesktopWidth().isEmpty() ? this.desktopWidth : title.getDesktopWidth();
        this.changePointer = title.getChangePointer() == this.changePointer ? this.changePointer : title.getChangePointer();
        this.order = title.getOrder() == this.order ? this.order : title.getOrder();
    }
}
