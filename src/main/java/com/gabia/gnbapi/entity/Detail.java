package com.gabia.gnbapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gabia.gnbapi.entity.status.BadgeStatus;
import lombok.AccessLevel;
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
}
