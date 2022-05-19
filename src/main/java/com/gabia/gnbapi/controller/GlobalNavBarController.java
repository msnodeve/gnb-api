package com.gabia.gnbapi.controller;

import com.gabia.gnbapi.dto.request.CreateTopMenuRequestDto;
import com.gabia.gnbapi.dto.request.ModifyTopMenuRequestDto;
import com.gabia.gnbapi.dto.response.HeadLineMenuResponseDto;
import com.gabia.gnbapi.service.GlobalNavBarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Global Navigation Bar API V1")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/gnb")
public class GlobalNavBarController {
    private final GlobalNavBarService globalNavBarService;

    @ApiOperation(value = "대분류 메뉴 등록")
    @PostMapping("/topMenu") // Admin 권한 필요
    public ResponseEntity createTopMenu(@RequestBody CreateTopMenuRequestDto createTopMenuRequestDto) {
        globalNavBarService.createTopMenu(createTopMenuRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "모든 메뉴 조회")
    @GetMapping()
    public ResponseEntity<List<HeadLineMenuResponseDto>> getAllMenus() {
        List<HeadLineMenuResponseDto> menuResponseDtoList = globalNavBarService.getAllMenu();

        return ResponseEntity.status(HttpStatus.OK).body(menuResponseDtoList);
    }

    @ApiOperation(value = "대분류 수정")
    @PutMapping("/topMenu/{topId}")
    public ResponseEntity<?> modifyTopMenu(@PathVariable Long topId,
                                           @RequestBody ModifyTopMenuRequestDto modifyTopMenuRequestDto) {
        globalNavBarService.modifyTopMenu(topId, modifyTopMenuRequestDto);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "대분류 삭제")
    @DeleteMapping("/topMenu/{topId}")
    public ResponseEntity<?> removeTopMenu(@PathVariable Long topId) {
        globalNavBarService.removeTopMenu(topId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
