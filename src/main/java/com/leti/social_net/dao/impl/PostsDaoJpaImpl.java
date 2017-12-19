package com.leti.social_net.dao.impl;

import com.leti.social_net.dao.PostsDao;
import com.leti.social_net.models.Post;
import com.leti.social_net.models.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Posts DAO implementation
 * with JPA and Hibernate
 */
@Repository
@Transactional
public class PostsDaoJpaImpl implements PostsDao {

    /**
     * Entity manager uses for connection with database
     */
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void insertPost(Post post) {
        entityManager.merge(post);
    }

    @Override
    public List<Post> getRecentPosts(User user, long offset, int limit) {
        return entityManager
                .createQuery("from Post as p where p.author = ?",Post.class)
                .setFirstResult((int) offset)
                .setMaxResults(limit)
                .setParameter(1,user)
                .getResultList();
    }

    @Override
    public List<Post> getRecentPosts(long offset, int limit) {
        return entityManager
                .createQuery("from Post",Post.class)
                .setFirstResult((int) offset)
                .setMaxResults(limit)
                .getResultList();
    }
}
