package com.gabia.gnbapi.repository;

import com.gabia.gnbapi.entity.HeadLine;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.gabia.gnbapi.entity.QHeadLine.headLine;
import static com.gabia.gnbapi.entity.QTitle.title;

@Repository
@RequiredArgsConstructor
public class HeadLineRepositoryImpl implements HeadLineRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<HeadLine> findAll() {
        // Top Menu - Middle Menu Join
        List<HeadLine> headLines = jpaQueryFactory
                .select(headLine)
                .from(headLine)
                .leftJoin(headLine.titles).fetchJoin()
                .distinct()
                .fetch();

        // Middle Menu - Bottom Menu Join
        jpaQueryFactory.select(title)
                .from(title)
                .leftJoin(title.details).fetchJoin()
                .distinct()
                .fetch();

        return headLines;
    }
}
