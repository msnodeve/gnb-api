package com.gabia.gnbapi.repository;

import com.gabia.gnbapi.entity.HeadLine;

import java.util.List;

public interface HeadLineRepository {
    List<HeadLine> findAll();
}
