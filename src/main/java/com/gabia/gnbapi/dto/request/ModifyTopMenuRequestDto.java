package com.gabia.gnbapi.dto.request;

import com.gabia.gnbapi.entity.HeadLine;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@ApiModel(value = "ModifyTopMenuRequestDto: Top 메뉴 수정")
@Getter
public class ModifyTopMenuRequestDto {
    @ApiModelProperty(value = "메뉴 이름")
    private String topTitle;
    @ApiModelProperty(value = "PC 메뉴 설명")
    private String desktopDescription;
    @ApiModelProperty(value = "Mobile 메뉴 설명")
    private String mobileDescription;
    @ApiModelProperty(value = "Tablet 메뉴 설명")
    private String tabletDescription;
    @ApiModelProperty(value = "서비스 링크")
    private String serviceLink;
    @ApiModelProperty(value = "메뉴 이름에 대한 유니크 값")
    private String titleKey;
    @ApiModelProperty(value = "새창으로 열 여부")
    private boolean targetBlank;
    @ApiModelProperty(value = "순서")
    private int topOrder;

    public HeadLine toHeadLineEntity() {
        return HeadLine.builder()
                .name(this.topTitle)
                .desktopDescription(this.desktopDescription)
                .mobileDescription(this.mobileDescription)
                .tabletDescription(this.tabletDescription)
                .serviceLink(this.serviceLink)
                .titleKey(this.titleKey)
                .isTargetBlank(this.targetBlank)
                .order(this.topOrder)
                .build();
    }
}
