package com.example.bookreviewserver.repo;

import com.example.bookreviewserver.model.PostCount;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

import static com.example.bookreviewserver.model.QPostCount.postCount;

@Repository
public class PostCountQueryRepo extends QuerydslRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;

    public PostCountQueryRepo(JPAQueryFactory jpaQueryFactory) {
        super(PostCount.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public PostCount selectCount(String name, LocalDate time){
        return jpaQueryFactory
                .selectFrom(postCount)
                .where(postCount.categoryName.eq(name)
                .and(postCount.time.eq(time)))
                .fetchOne();
    }
}
