package com.gabia.gnbapi.repository;

import com.gabia.gnbapi.entity.QTitle;
import com.gabia.gnbapi.entity.Title;
import com.querydsl.jpa.impl.JPAQueryFactory;
import javassist.NotFoundException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.Optional;

import static com.gabia.gnbapi.entity.QTitle.*;

@Repository
public class TitleRepository extends QuerydslRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    @PersistenceContext
    private EntityManager entityManager;

    public TitleRepository(JPAQueryFactory jpaQueryFactory) {
        super(Title.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    // 중분류 INSERT
    @Transactional
    public void saveTitle(Title title) {
        entityManager.persist(title);
    }

    public Title findById(Long id) throws NotFoundException {
        Title title = jpaQueryFactory
                .select(QTitle.title)
                .from(QTitle.title)
                .where(QTitle.title.id.eq(id))
                .fetchOne();

        Optional<Title> findTitle = Optional.ofNullable(title);
        return findTitle.orElseThrow(() -> new NotFoundException(String.format("존재하지 않는 Middle Menu ID : %s", id)));
    }

    // 중분류 UPDATE
    @Modifying
    @Transactional
    public void updateTitle(Long id, Title updateForTitleEntity) {
        Title title = jpaQueryFactory
                .select(QTitle.title)
                .from(QTitle.title)
                .where(QTitle.title.id.eq(id))
                .fetchOne();

        title.updateTitle(updateForTitleEntity);
    }

    // 중분류 DELETE
    @Transactional
    public void deleteTitle(Long id) {
        jpaQueryFactory
                .delete(title)
                .where(title.id.eq(id))
                .execute();
    }
}
