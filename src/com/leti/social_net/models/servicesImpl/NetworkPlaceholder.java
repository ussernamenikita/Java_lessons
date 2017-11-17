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
    ArrayList<Pair<String,Integer>> LP;


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
        for (int i = 1; i < users.size(); i++) {
            LP.add(new Pair<>("user"+i+"password"+i,i));
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
        //TODO log,exception
    }

    @Override
    public void addComment(String token, int postId, String text) {
        Integer id  = Token.getIdFromToken(token);
        //TODO post id check
        Comment comment = new Comment();
        comment.setId(comments.size()+1);
        comment.setPostId(postId);
        comment.setText(text);
        comment.setUserId(id);
        comments.add(comment);
    }


    @Override
    public void addUserToFriend(String token, int userId) {
        User newFriend = findUserById(userId);
        Integer i = Token.getIdFromToken(token);
        if(i == null) {
            return;
        }
        User currentUser = findUserById(i);
        if (currentUser != null && newFriend != null) {
            List<User> friendsL = friends.computeIfAbsent(currentUser, k -> new ArrayList<>(10));
            friendsL.add(newFriend);
        }
    }

    @Override
    public void removeUserFromFriends(String token, int userId) {
        Integer id = Token.getIdFromToken(token);
        User newFriend = findUserById(userId);
        if (id == null || newFriend == null || findUserById(id) == null) {
            return;
        }
        User user = findUserById(id);
        List<User> friendsL = friends.computeIfAbsent(user,k -> new ArrayList<>(10));
        friendsL.add(newFriend);
    }

    @Override
    public Pair<Long, List<Post>> getRecentPosts(String token, long offset, int limit) {
        int size = posts.size();
        if(offset > size)
        {
            return null;
        }else
        {
            int intOffset = (int) offset;
            if(limit+intOffset > size)
            {
                limit = size;
            }else
                limit = limit+intOffset;
            return new Pair<>((long) limit-1,posts.subList(intOffset,limit));
        }
    }

    @Override
    public List<Comment> getCommentOfPost(int postId) {
        List<Comment> resuzltComments= new ArrayList<>();
        comments.forEach(comment -> {
            if(comment.getPostId().equals(postId))
                resuzltComments.add(comment);
        });
        return resuzltComments;
    }

    @Override
    public boolean isNetworkAvailable() {
        return true;
    }

    @Override
    public void updateUserData(String token, User newUserData) {
        int id = Token.getIdFromToken(token);
        if(id == newUserData.getId())
        {
            User oldUser = users.get(users.indexOf(newUserData));
            oldUser.update(newUserData);
        }
    }

    @Override
    public int createNewUser(User newUser) {
        newUser.setId(users.size());
        users.add(newUser);
        return users.size()-1;
    }

    @Override
    public String login(String login, String password) {

    }


}
