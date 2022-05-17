package com.gabia.gnbapi.repository;

import com.gabia.gnbapi.entity.HeadLine;
import com.gabia.gnbapi.entity.QDetail;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.gabia.gnbapi.entity.QDetail.*;
import static com.gabia.gnbapi.entity.QHeadLine.headLine;
import static com.gabia.gnbapi.entity.QTitle.title;

@Repository
public class HeadLineRepository extends QuerydslRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    public HeadLineRepository(JPAQueryFactory jpaQueryFactory) {
        super(HeadLine.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<HeadLine> findAll() {
        // Top Menu - Middle Menu Join
        List<HeadLine> headLines = jpaQueryFactory
                .select(headLine)
                .from(headLine)
                .leftJoin(headLine.titles, title).fetchJoin()
                .distinct()
                .orderBy(headLine.order.asc(), title.order.asc())
                .fetch();

        // Middle Menu - Bottom Menu Join
        jpaQueryFactory.select(title)
                .from(title)
                .leftJoin(title.details, detail).fetchJoin()
                .distinct()
                .orderBy(title.order.asc(), detail.order.asc())
                .fetch();

        return headLines;
    }
}
