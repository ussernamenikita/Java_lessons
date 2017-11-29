package com.leti.social_net.dao;

import com.leti.social_net.models.Post;
import javafx.geometry.Pos;
import javafx.scene.control.ListCell;

import java.util.List;

/**
 *  DAO for posts
 */
public interface PostsDao {


    void insertPost(Post post);

    void insertPosts(List<Post> postsList);

    List<Post> getAllPosts();

    List<Post> getPostsByUser(int userId);
}