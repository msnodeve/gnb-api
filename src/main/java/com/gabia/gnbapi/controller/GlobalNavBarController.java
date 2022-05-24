package com.gabia.gnbapi.controller;

import com.gabia.gnbapi.dto.request.*;
import com.gabia.gnbapi.dto.response.HeadLineMenuResponseDto;
import com.gabia.gnbapi.service.GlobalNavBarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
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

    @ApiOperation(value = "모든 메뉴 조회")
    @GetMapping()
    public ResponseEntity<List<HeadLineMenuResponseDto>> getAllMenus() {
        List<HeadLineMenuResponseDto> menuResponseDtoList = globalNavBarService.getAllMenu();

        return ResponseEntity.status(HttpStatus.OK).body(menuResponseDtoList);
    }

    @ApiOperation(value = "대분류 메뉴 등록")
    @PostMapping("/topMenu")
    public ResponseEntity createTopMenu(@RequestBody CreateTopMenuRequestDto createTopMenuRequestDto) {
        globalNavBarService.createTopMenu(createTopMenuRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "대분류 수정")
    @PutMapping("/topMenu/{topId}")
    public ResponseEntity<?> modifyTopMenu(@PathVariable Long topId,
                                           @RequestBody ModifyTopMenuRequestDto modifyTopMenuRequestDto) throws NotFoundException {
        globalNavBarService.modifyTopMenu(topId, modifyTopMenuRequestDto);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "대분류 삭제")
    @DeleteMapping("/topMenu/{topId}")
    public ResponseEntity<?> removeTopMenu(@PathVariable Long topId) {
        globalNavBarService.removeTopMenu(topId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "중분류 메뉴 등록")
    @PostMapping("/topMenu/{topId}/middleMenu")
    public ResponseEntity createMiddleMenu(@PathVariable Long topId,
                                           @RequestBody CreateMiddleMenuRequestDto createMiddleMenuRequestDto) throws NotFoundException {
        globalNavBarService.createMiddleMenu(topId, createMiddleMenuRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "중분류 수정")
    @PutMapping("/topMenu/{topId}/middleMenu/{middleId}")
    public ResponseEntity<?> modifyMiddleMenu(@PathVariable Long topId,
                                              @PathVariable Long middleId,
                                              @RequestBody ModifyMiddleMenuRequestDto modifyMiddleMenuRequestDto) throws Exception {
        globalNavBarService.modifyMiddleMenu(topId, middleId, modifyMiddleMenuRequestDto);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "중분류 삭제")
    @DeleteMapping("/topMenu/{topId}/middleMenu/{middleId}")
    public ResponseEntity<?> removeMiddleMenu(@PathVariable Long topId,
                                              @PathVariable Long middleId) throws Exception {
        globalNavBarService.removeMiddleMenu(topId, middleId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "소분류 메뉴 등록")
    @PostMapping("/topMenu/{topId}/middleMenu/{middleId}/bottomMenu")
    public ResponseEntity createBottomMenu(@PathVariable Long topId,
                                           @PathVariable Long middleId,
                                           @RequestBody CreateBottomMenuRequestDto createBottomMenuRequestDto) throws NotFoundException {
        globalNavBarService.createBottomMenu(topId, middleId, createBottomMenuRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "소분류 수정")
    @PutMapping("/topMenu/{topId}/middleMenu/{middleId}/bottomMenu/{bottomId}")
    public ResponseEntity<?> modifyBottomMenu(@PathVariable Long topId,
                                              @PathVariable Long middleId,
                                              @PathVariable Long bottomId,
                                              @RequestBody ModifyBottomMenuRequestDto modifyBottomMenuRequestDto) throws Exception {
        globalNavBarService.modifyBottomMenu(topId, middleId, bottomId, modifyBottomMenuRequestDto);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "소분류 삭제")
    @DeleteMapping("/topMenu/{topId}/middleMenu/{middleId}/bottomMenu/{bottomId}")
    public ResponseEntity<?> removeBottomMenu(@PathVariable Long topId,
                                              @PathVariable Long middleId,
                                              @PathVariable Long bottomId) throws Exception {
        globalNavBarService.removeBottomMenu(topId, middleId, bottomId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
