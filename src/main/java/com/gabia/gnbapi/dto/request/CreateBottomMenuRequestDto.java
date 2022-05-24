package com.gabia.gnbapi.dto.request;

import com.gabia.gnbapi.entity.Detail;
import com.gabia.gnbapi.entity.status.BadgeStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@ApiModel(value = "CreateBottomMenuRequestDto: Bottom 메뉴 생성")
@Getter
public class CreateBottomMenuRequestDto {
    @ApiModelProperty(value = "메뉴 이름")
    private String bottomTitle;
    @ApiModelProperty(value = "서비스 주소")
    private String link;
    @ApiModelProperty(value = "아이콘 주소")
    private String icon;
    @ApiModelProperty(value = "새창으로 열기")
    private boolean targetBlank;
    @ApiModelProperty(value = "사이트 맵에 보여지는 여부")
    private boolean sitemapShow;
    @ApiModelProperty(value = "모바일에 보여지는 여부")
    private boolean mobileShow;
    @ApiModelProperty(value = "순서")
    private int bottomOrder;
    @ApiModelProperty(value = "뱃지")
    private BadgeStatus badge;

    public Detail toDetailEntity() {
        return Detail.builder()
                .name(this.bottomTitle)
                .link(this.link)
                .icon(this.icon)
                .isTargetBlank(this.targetBlank)
                .isSitemapShow(this.sitemapShow)
                .isMobileShow(this.mobileShow)
                .order(this.bottomOrder)
                .badge(this.badge)
                .build();
    }
}
