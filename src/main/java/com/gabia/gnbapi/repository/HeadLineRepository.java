package com.gabia.gnbapi.repository;

import com.gabia.gnbapi.entity.HeadLine;
import com.gabia.gnbapi.entity.QHeadLine;
import com.querydsl.jpa.impl.JPAQueryFactory;
import javassist.NotFoundException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

import static com.gabia.gnbapi.entity.QDetail.detail;
import static com.gabia.gnbapi.entity.QHeadLine.headLine;
import static com.gabia.gnbapi.entity.QTitle.title;

@Repository
public class HeadLineRepository extends QuerydslRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    @PersistenceContext
    private EntityManager entityManager;

    public HeadLineRepository(JPAQueryFactory jpaQueryFactory) {
        super(HeadLine.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    // 대분류 INSERT
    @Transactional
    public void saveHeadLine(HeadLine headLine) {
        entityManager.persist(headLine);
    }

    // 대분류-중분류-소분류 ALL SELECT
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

    // 대분류 UPDATE
    @Modifying
    @Transactional
    public void updateHeadLine(Long id, HeadLine updateForHeadLineEntity) throws NotFoundException {
        HeadLine headLine = findById(id);
        headLine.updateHeadLine(updateForHeadLineEntity);
    }

    // 대분류 DELETE
    @Transactional
    public void deleteHeadLine(Long id) {
        jpaQueryFactory
                .delete(headLine)
                .where(headLine.id.eq(id))
                .execute();
    }

    public HeadLine findById(Long id) throws NotFoundException {
        HeadLine headLine = jpaQueryFactory
                .select(QHeadLine.headLine)
                .from(QHeadLine.headLine)
                .where(QHeadLine.headLine.id.eq(id))
                .fetchOne();

        Optional<HeadLine> findHeadLine = Optional.ofNullable(headLine);

        return findHeadLine.orElseThrow(() -> new NotFoundException(String.format("존재하지 않는 Top Menu ID : %s", id)));
    }
}
