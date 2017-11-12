package com.leti.social_net.models.servicesImpl;

import com.leti.social_net.models.Comment;
import com.leti.social_net.models.Message;
import com.leti.social_net.models.Post;
import com.leti.social_net.models.User;
import com.leti.social_net.models.services.NetworkService;
import javafx.util.Pair;

import java.util.List;

/**
 * Network imitation
 */
public class NetworkPlaceholder implements NetworkService {



    @Override
    public User registerNewUser(String userName, String password, String name, String surname) {
        return null;
    }

    @Override
    public String getToken(String userName, String password) {
        return null;
    }

    @Override
    public User getUserById(int id) {
        return null;
    }

    @Override
    public List<User> getUserFriends(int userId, int limit, int offset) {
        return null;
    }

    @Override
    public int getFriendsCount(int userId) {
        return 0;
    }

    @Override
    public void sendMessage(Message msg) {

    }

    @Override
    public void addPost(String token, Post post) {

    }

    @Override
    public void addComment(String token, int postId) {

    }

    @Override
    public void addUserToFriend(String token, int userId) {

    }

    @Override
    public void removeUserFromFriends(String token, int userId) {

    }

    @Override
    public Pair<Long, List<Post>> getRecentPosts(String token, long offset, int limit) {
        return null;
    }

    @Override
    public List<Comment> getCommentOfPosts(int postId) {
        return null;
    }

    @Override
    public boolean isNetworkAvailable() {
        return false;
    }
}
