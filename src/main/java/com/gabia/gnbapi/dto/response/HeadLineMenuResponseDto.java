package com.gabia.gnbapi.dto.response;

import com.gabia.gnbapi.entity.HeadLine;
import com.gabia.gnbapi.entity.Title;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "HeadLineMenuResponseDto: Top 메뉴 객체")
@Getter
public class HeadLineMenuResponseDto {
    @ApiModelProperty(value = "고유 ID")
    private long topId;
    @ApiModelProperty(value = "이름")
    private String topTitle;
    @ApiModelProperty(value = "PC 설명")
    private String desktopDescription;
    @ApiModelProperty(value = "Mobile 설명")
    private String mobileDescription;
    @ApiModelProperty(value = "Tablet 설명")
    private String tabletDescription;
    @ApiModelProperty(value = "순서")
    private int topOrder;
    @ApiModelProperty(value = "메뉴 이름에 대한 유니크 키 이름")
    private String titleKey;
    @ApiModelProperty(value = "서비스 링크")
    private String serviceLink;
    @ApiModelProperty(value = "새창으로 열지에 대한 여부")
    private boolean isTargetBlank;
    @ApiModelProperty(value = "Middle 메뉴 리스트 객체")
    private List<TitleMenuResponseDto> middleMenus = new ArrayList<>();

    public HeadLineMenuResponseDto(HeadLine headLine) {
        this.topId = headLine.getId();
        this.topTitle = headLine.getName();
        this.desktopDescription = headLine.getDesktopDescription();
        this.mobileDescription = headLine.getMobileDescription();
        this.tabletDescription = headLine.getTabletDescription();

        this.topOrder = headLine.getOrder();
        this.titleKey = headLine.getTitleKey();
        this.serviceLink = headLine.getServiceLink();
        this.isTargetBlank = headLine.isTargetBlank();

        for (Title title : headLine.getTitles()) {
            this.middleMenus.add(new TitleMenuResponseDto(title));
        }
    }
}
