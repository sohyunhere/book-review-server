package com.example.bookreviewserver.repo;

import com.example.bookreviewserver.model.Comments;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import static com.example.bookreviewserver.model.QComments.comments;
@Repository
public class CommentQueryRepo extends QuerydslRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;
    /**
     * Creates a new {@link QuerydslRepositorySupport} instance for the given domain type.
     *
     * @param jpaQueryFactory must not be {@literal null}.
     */

    public CommentQueryRepo(JPAQueryFactory jpaQueryFactory) {
        super(Comments.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public void updateContent(Long id, String content){
        jpaQueryFactory
                .update(comments)
                .set(comments.content, content)
                .where(comments.commentId.eq(id))
                .execute();
    }
}
