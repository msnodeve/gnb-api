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
    private long id;
    @ApiModelProperty(value = "이름")
    private String title;
    @ApiModelProperty(value = "설명")
    private String description;
    @ApiModelProperty(value = "순서")
    private int order;
    @ApiModelProperty(value = "서비스 링크")
    private String serviceLink;
    @ApiModelProperty(value = "Middle 메뉴 리스트 객체")
    private List<TitleMenuResponseDto> midClasses = new ArrayList<>();

    public HeadLineMenuResponseDto(HeadLine headLine) {
        this.id = headLine.getId();
        this.title = headLine.getName();
        this.description = headLine.getDescription();
        this.order = headLine.getOrder();
        this.serviceLink = headLine.getServiceLink();

        for (Title title : headLine.getTitles()) {
            this.midClasses.add(new TitleMenuResponseDto(title));
        }
    }
}
