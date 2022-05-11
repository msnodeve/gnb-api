package com.gabia.gnbapi.dto.response;

import com.gabia.gnbapi.entity.Detail;
import com.gabia.gnbapi.entity.Title;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "TitleMenuResponseDto: Middle 메뉴 객체")
@Getter
public class TitleMenuResponseDto {
    @ApiModelProperty(value = "고유 ID")
    private long id;
    @ApiModelProperty(value = "메뉴 이름")
    private String title;
    @ApiModelProperty(value = "메뉴 순서")
    private int order;
    @ApiModelProperty(value = "라인 변경 기준점")
    private int gnbChangePoint;
    @ApiModelProperty(value = "PC 기준 메뉴 길이")
    private String desktopWidth;
    @ApiModelProperty(value = "Bottom 메뉴 리스트 객체")
    private List<DetailMenuResponseDto> bottomClasses = new ArrayList<>();

    public TitleMenuResponseDto(Title title) {
        this.id = title.getId();
        this.title = title.getName();
        this.order = title.getOrder();
        this.gnbChangePoint = title.getChangePointer();
        this.desktopWidth = title.getDesktopWidth();

        for (Detail detail : title.getDetails()) {
            this.bottomClasses.add(new DetailMenuResponseDto(detail));
        }
    }
}
