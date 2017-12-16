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
 * Created by Nikita on 12.12.2017.
 */
@Repository
@Transactional
public class PostsDaoJpaImpl implements PostsDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void insertPost(Post post) {
        entityManager.persist(post);
    }

    @Override
    public void insertPosts(List<Post> postsList) {
        entityManager.getTransaction();
        postsList.forEach(this::insertPost);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Post> getAllPosts() {
        return entityManager.createQuery("select p from Post p",Post.class).getResultList();
    }

    @Override
    public List<Post> getPostsByUser(int userId) {
        return entityManager.createQuery("select p from Posts as p where p.author = ?",Post.class)
                .setParameter(0,userId).getResultList();
    }

    @Override
    public List<Post> getRecentPosts(User user, long offset, int limit) {
        return entityManager
                .createQuery("select p from Post as p where p.author = ?",Post.class)
                .setParameter(0,user)
                .setFirstResult((int)offset)
                .setMaxResults(limit)
                .getResultList();
    }
}
