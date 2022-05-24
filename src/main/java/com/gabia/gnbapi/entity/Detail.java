package com.gabia.gnbapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gabia.gnbapi.entity.status.BadgeStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "details")
public class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "title_id")
    private Title title;

    @Column(name = "title", columnDefinition = "varchar(128)", nullable = false)
    private String name;
    @Column(columnDefinition = "text", nullable = false)
    private String link;
    @Column(columnDefinition = "text")
    private String icon;

    @Column(name = "target_blank_flag", columnDefinition = "tinyint(1)", length = 1)
    private boolean isTargetBlank;
    @Column(name = "sitemap_show_flag", columnDefinition = "tinyint(1)", length = 1)
    private boolean isSitemapShow;
    @Column(name = "mobile_show_flag", columnDefinition = "tinyint(1)", length = 1)
    private boolean isMobileShow;

    @Column(name = "`order`")
    private int order;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(256)")
    private BadgeStatus badge;

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
    public Detail(Long id, Title title, String name, String link, String icon, boolean isTargetBlank, boolean isSitemapShow, boolean isMobileShow, int order, BadgeStatus badge, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt) {
        this.id = id;
        this.title = title;
        this.name = name;
        this.link = link;
        this.icon = icon;
        this.isTargetBlank = isTargetBlank;
        this.isSitemapShow = isSitemapShow;
        this.isMobileShow = isMobileShow;
        this.order = order;
        this.badge = badge;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public void updateTitle(Title title) {
        this.title = title;
    }

    public void updateDetail(Detail detail) {
        this.name = detail.getName().isEmpty() ? this.name : detail.getName();
        this.link = detail.getLink().isEmpty() ? this.link : detail.getLink();
        this.icon = detail.icon.isEmpty() ? this.icon : detail.getIcon();
        this.isTargetBlank = detail.isTargetBlank() == this.isTargetBlank ? this.isTargetBlank : detail.isTargetBlank();
        this.isSitemapShow = detail.isSitemapShow() == this.isSitemapShow ? this.isSitemapShow : detail.isSitemapShow();
        this.isMobileShow = detail.isMobileShow() == this.isMobileShow ? this.isMobileShow : detail.isMobileShow();
        this.order = detail.getOrder() == this.order ? this.order : detail.getOrder();
        this.badge = detail.getBadge().name().isEmpty() ? this.badge : detail.getBadge();
    }
}
