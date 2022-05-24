package com.gabia.gnbapi.repository;

import com.gabia.gnbapi.entity.Detail;
import com.gabia.gnbapi.entity.QDetail;
import com.querydsl.jpa.impl.JPAQueryFactory;
import javassist.NotFoundException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

import static com.gabia.gnbapi.entity.QDetail.detail;

@Repository
public class DetailRepository extends QuerydslRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    @PersistenceContext
    private EntityManager entityManager;

    public DetailRepository(JPAQueryFactory jpaQueryFactory) {
        super(Detail.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    // 소분류 INSERT
    @Transactional
    public void saveDetail(Detail detail) {
        entityManager.persist(detail);
    }

    public Detail findById(Long id) throws NotFoundException {
        Detail detail = jpaQueryFactory
                .select(QDetail.detail)
                .from(QDetail.detail)
                .where(QDetail.detail.id.eq(id))
                .fetchOne();

        Optional<Detail> findDetail = Optional.ofNullable(detail);
        return findDetail.orElseThrow(() -> new NotFoundException(String.format("존재하지 않는 Bottom Menu ID : %s", id)));
    }

    // 소분류 UPDATE
    @Modifying
    @Transactional
    public void updateDetail(Long id, Detail updateForDetailEntity) throws NotFoundException {
        Detail detail = findById(id);

        detail.updateDetail(updateForDetailEntity);
    }

    // 소분류 DELETE
    @Transactional
    public void deleteDetail(Long id) {
        jpaQueryFactory
                .delete(detail)
                .where(detail.id.eq(id))
                .execute();
    }
}
