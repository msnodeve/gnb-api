package com.gabia.gnbapi.dto.request;

import com.gabia.gnbapi.entity.Title;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@ApiModel(value = "ModifyMiddleMenuRequestDto: Middle 메뉴 수정")
@Getter
public class ModifyMiddleMenuRequestDto {
    @ApiModelProperty(value = "메뉴 이름")
    private String middleTitle;
    @ApiModelProperty(value = "PC 화면에서 보여질 메뉴 길이")
    private String desktopWidth;
    @ApiModelProperty(value = "다음라인으로 넘어갈 지점")
    private int changePointer;
    @ApiModelProperty(value = "순서")
    private int middleOrder;

    public Title toTitleEntity() {
        return Title.builder()
                .name(this.middleTitle)
                .desktopWidth(this.desktopWidth)
                .order(this.middleOrder)
                .changePointer(this.changePointer)
                .build();
    }
}
