package com.leti.social_net.dao;

import com.leti.social_net.models.Post;
import com.leti.social_net.models.User;

import java.util.List;

/**
 *  DAO for posts
 *  ...
 */
public interface PostsDao {


    void insertPost(Post post);

    List<Post> getRecentPosts(User user, long offset, int limit);

    List<Post> getRecentPosts(long offset,int limit);
}
