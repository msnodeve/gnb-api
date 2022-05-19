package com.gabia.gnbapi.service;

import com.gabia.gnbapi.dto.request.CreateTopMenuRequestDto;
import com.gabia.gnbapi.dto.request.ModifyTopMenuRequestDto;
import com.gabia.gnbapi.dto.response.HeadLineMenuResponseDto;
import com.gabia.gnbapi.entity.HeadLine;
import com.gabia.gnbapi.repository.HeadLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GlobalNavBarService {
    private final HeadLineRepository headLineRepository;

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
}
