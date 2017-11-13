package com.leti.social_net.models.servicesImpl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.leti.social_net.commands.Command;
import com.leti.social_net.models.*;
import com.leti.social_net.models.services.NetworkService;
import javafx.util.Pair;

import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Network imitation
 */
public class NetworkPlaceholder implements NetworkService {

    private static NetworkPlaceholder instance;
    private Random random = new Random(System.currentTimeMillis());

    ArrayList<User> users;
    ArrayList<Message> messages;
    ArrayList<Post> posts;
    ArrayList<Comment> comments;
    HashMap<User,List<User>> friends;


    private NetworkPlaceholder() {
        Gson gson = new Gson();
        Type usersType = new TypeToken<List<User>>() {
        }.getType();
        ClassLoader loader = getClass().getClassLoader();
        users = gson.fromJson(new InputStreamReader(loader.getResourceAsStream("users.json")), usersType);
        Type postsType = new TypeToken<List<Post>>() {
        }.getType();
        posts = gson.fromJson(new InputStreamReader(loader.getResourceAsStream("Posts.json")), postsType);
        Type messagesTypes = new TypeToken<List<Message>>() {
        }.getType();
        messages = gson.fromJson(new InputStreamReader(loader.getResourceAsStream("Messages.json")), messagesTypes);
        Type commentType = new TypeToken<List<Comment>>() {
        }.getType();
        comments = gson.fromJson(new InputStreamReader(loader.getResourceAsStream("Comments.json")), commentType);
        for (int i = 0; i < users.size(); i++) {
            int count = random.nextInt(users.size());
            if (count > 0) {
                List<User> friendsL = new ArrayList<>();
                for (int j = 0; j < count; j++) {
                    User newFriend = users.get(random.nextInt(users.size()));
                    if(newFriend != users.get(i))
                    {
                        friendsL.add(newFriend);
                    }
                }
                if(friendsL.size() > 0)
                {
                    friends.put(users.get(i),friendsL);
                }
            }
        }
    }

    public static NetworkPlaceholder getInstance()
    {
        if(instance == null)
            instance = new NetworkPlaceholder();
        return instance;
    }

    @Override
    public User registerNewUser(String userName, String password, String name, String surname) {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Token.registerToken(userName,password,Integer.toString(users.size()+1));
        return new User(format.format(date),null,null,null,null,
                "LETI",true,null,surname,name,users.size()+1);
    }

    @Override
    public String getToken(String userName, String password) {
        return Token.getToken(userName,password);
    }

    @Override
    public User getUserById(int id) {
        for(User user:users)
        {
            if(user.getId() == id)
                return user;
        }
        return  null;
    }

    @Override
    public List<User> getUserFriends(int userId, int limit, int offset) {
        User user = findUserById(userId);
        if(user == null)
            return  null;
        List<User> friendsL = friends.get(user);
        if(friendsL == null || friendsL.size() == 0 || offset > friendsL.size())
            return null;
        if(friendsL.size() < offset+limit)
        {
            return friendsL.subList(offset,friendsL.size());
        }
        return friendsL;
    }

    private User findUserById(int id) {
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getId() == id)
                return users.get(i);
        }
        return null;
    }

    @Override
    public int getFriendsCount(int userId)
    {
        User user = findUserById(userId);
        if(user == null)
            return  0;
        List<User> friendsL = friends.get(user);
        if(friendsL == null)
            return 0;
        return friendsL.size();
    }
    @Override
    public void sendMessage(Message msg) {
        messages.add(msg);
        //TODO sender and getter validation
    }

    @Override
    public void addPost(String token, Post post) {
        Integer id  = Token.getIdFromToken(token);
        if(post.getAuthor().equals(id))
        {
            posts.add(post);
        }
        //TODO log,exeception
    }

    @Override
    public void addComment(String token, int postId, String text) {
        Integer id  = Token.getIdFromToken(token);
        Comment comment = new Comment();

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
        return true;
    }

    @Override
    public void updateUserData(String token, User newUserData) {
        int userId = token;
    }


}
