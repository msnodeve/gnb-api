package com.gabia.gnbapi.dto.response;

import com.gabia.gnbapi.entity.Detail;
import com.gabia.gnbapi.entity.status.BadgeStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@ApiModel(value = "DetailMenuResponseDto: Bottom 메뉴 객체")
@Getter
public class DetailMenuResponseDto {
    @ApiModelProperty(value = "고유 ID")
    private long bottomId;
    @ApiModelProperty(value = "메뉴 이름")
    private String bottomTitle;
    @ApiModelProperty(value = "메뉴 뱃지 상태")
    private BadgeStatus badge;
    @ApiModelProperty(value = "이동 링크")
    private String link;
    @ApiModelProperty(value = "아이콘 주소")
    private String icon;
    @ApiModelProperty(value = "메뉴 주소")
    private int bottomOrder;
    @ApiModelProperty(value = "새창으로 열지에 대한 여부")
    private boolean isTargetBlank;
    @ApiModelProperty(value = "사이트맵에 보일지에 대한 여부")
    private boolean isSitemapShow;
    @ApiModelProperty(value = "모바일에 보일지에 대한 여부")
    private boolean isMobileShow;

    public DetailMenuResponseDto(Detail detail) {
        this.bottomId = detail.getId();
        this.bottomTitle = detail.getName();
        this.badge = detail.getBadge();
        this.link = detail.getLink();
        this.icon = detail.getIcon();
        this.bottomOrder = detail.getOrder();
        this.isTargetBlank = detail.isTargetBlank();
        this.isSitemapShow = detail.isSitemapShow();
        this.isMobileShow = detail.isMobileShow();
    }
}
