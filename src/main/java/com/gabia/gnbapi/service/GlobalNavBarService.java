package com.gabia.gnbapi.service;

import com.gabia.gnbapi.dto.request.CreateMiddleMenuRequestDto;
import com.gabia.gnbapi.dto.request.CreateTopMenuRequestDto;
import com.gabia.gnbapi.dto.request.ModifyMiddleMenuRequestDto;
import com.gabia.gnbapi.dto.request.ModifyTopMenuRequestDto;
import com.gabia.gnbapi.dto.response.HeadLineMenuResponseDto;
import com.gabia.gnbapi.entity.HeadLine;
import com.gabia.gnbapi.entity.Title;
import com.gabia.gnbapi.repository.HeadLineRepository;
import com.gabia.gnbapi.repository.TitleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GlobalNavBarService {
    private final HeadLineRepository headLineRepository;
    private final TitleRepository titleRepository;

    public void createTopMenu(CreateTopMenuRequestDto createTopMenuRequestDto) {
        HeadLine headLine = createTopMenuRequestDto.toHeadLineEntity();
        headLineRepository.saveHeadLine(headLine);
    }

    public List<HeadLineMenuResponseDto> getAllMenu() {
        List<HeadLine> headLines = headLineRepository.findAll();

        List<HeadLineMenuResponseDto> response = new ArrayList<>();
        for (HeadLine headLine : headLines) {
            response.add(new HeadLineMenuResponseDto(headLine));
        }

        return response;
    }

    public void modifyTopMenu(Long topId, ModifyTopMenuRequestDto modifyTopMenuRequestDto) {
        HeadLine headLine = modifyTopMenuRequestDto.toHeadLineEntity();
        headLineRepository.updateHeadLine(topId, headLine);
    }

    public void removeTopMenu(Long topId) {
        headLineRepository.deleteHeadLine(topId);
    }

    public void createMiddleMenu(Long topMenuId, CreateMiddleMenuRequestDto createMiddleMenuRequestDto) {
        HeadLine headLine = headLineRepository.findById(topMenuId);
        Title title = createMiddleMenuRequestDto.toTitleEntity();
        title.updateHeadLine(headLine);

        titleRepository.saveTitle(title);
    }

    public void modifyMiddleMenu(Long topMenuId, Long middleMenuId, ModifyMiddleMenuRequestDto modifyMiddleMenuRequestDto) throws Exception {
        HeadLine headLine = headLineRepository.findById(topMenuId);
        Title title = titleRepository.findById(middleMenuId);

        if (title.getHeadLine().getId() != headLine.getId()) {
            throw new Exception(String.format("대분류(%s)에 중분류(%s)가 속해있지 않습니다.", topMenuId, middleMenuId));
        }

        Title updateForTitle = modifyMiddleMenuRequestDto.toTitleEntity();
        titleRepository.updateTitle(middleMenuId, updateForTitle);
    }

    public void removeMiddleMenu(Long topMenuId, Long middleMenuId) throws Exception {
        HeadLine headLine = headLineRepository.findById(topMenuId);
        Title title = titleRepository.findById(middleMenuId);

        if (title.getHeadLine().getId() != headLine.getId()) {
            throw new Exception(String.format("대분류(%s)에 중분류(%s)가 속해있지 않습니다.", topMenuId, middleMenuId));
        }

        titleRepository.deleteTitle(middleMenuId);
    }
}
