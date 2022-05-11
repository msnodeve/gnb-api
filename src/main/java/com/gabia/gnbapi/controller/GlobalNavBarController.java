package com.gabia.gnbapi.controller;

import com.gabia.gnbapi.dto.response.HeadLineMenuResponseDto;
import com.gabia.gnbapi.service.GlobalNavBarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "Global Navigation Bar API V1")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/gnb")
public class GlobalNavBarController {
    private final GlobalNavBarService globalNavBarService;

    @ApiOperation(value = "모든 메뉴 조회")
    @GetMapping()
    public ResponseEntity<List<HeadLineMenuResponseDto>> getAll() {
        List<HeadLineMenuResponseDto> menuResponseDtoList = globalNavBarService.getAllMenu();

        return ResponseEntity.status(HttpStatus.OK).body(menuResponseDtoList);
    }
}
