package com.gabia.gnbapi.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "headlines")
public class HeadLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonManagedReference
    @OneToMany(mappedBy = "headLine", fetch = LAZY)
    private List<Title> titles = new ArrayList<>();

    @Column(name = "title", columnDefinition = "varchar(128)", nullable = false)
    private String name;
    @Column(columnDefinition = "text", nullable = false)
    private String desktopDescription;
    @Column(columnDefinition = "text", nullable = false)
    private String mobileDescription;
    @Column(columnDefinition = "text", nullable = false)
    private String tabletDescription;
    @Column(columnDefinition = "text")
    private String serviceLink;
    @Column(columnDefinition = "varchar(32)")
    private String titleKey;

    @Column(name = "target_blank_flag", columnDefinition = "tinyint(1)", length = 1)
    private boolean isTargetBlank;

    @Column(name = "`order`")
    private int order;

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
    public HeadLine(Long id, List<Title> titles, String name, String desktopDescription, String mobileDescription, String tabletDescription, String serviceLink, String titleKey, boolean isTargetBlank, int order, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt) {
        this.id = id;
        this.titles = titles;
        this.name = name;
        this.desktopDescription = desktopDescription;
        this.mobileDescription = mobileDescription;
        this.tabletDescription = tabletDescription;
        this.serviceLink = serviceLink;
        this.titleKey = titleKey;
        this.isTargetBlank = isTargetBlank;
        this.order = order;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public void updateHeadLine(HeadLine headLine) {
        this.name = headLine.getName().isEmpty() ? this.name : headLine.getName();
        this.desktopDescription = headLine.getDesktopDescription().isEmpty() ? this.desktopDescription : headLine.getDesktopDescription();
        this.serviceLink = headLine.getServiceLink().isEmpty() ? this.serviceLink : headLine.getServiceLink();
        this.titleKey = headLine.getTitleKey().isEmpty() ? this.titleKey : headLine.getTitleKey();
        this.isTargetBlank = headLine.isTargetBlank() == this.isTargetBlank ? this.isTargetBlank : headLine.isTargetBlank();
        this.order = headLine.getOrder() == this.order ? this.order : headLine.getOrder();
    }
}
