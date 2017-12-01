package com.leti.social_net.services.servicesImpl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.leti.social_net.models.*;
import com.leti.social_net.services.NetworkService;
import javafx.util.Pair;
import org.apache.log4j.Logger;

import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Network imitation
 */
public class NetworkPlaceholder implements NetworkService {


    private static Logger Log = Logger.getLogger(NetworkPlaceholder.class);

    private ArrayList<User> users;
    private ArrayList<Message> messages;
    private ArrayList<Post> posts;
    private ArrayList<Comment> comments;
    private HashMap<User,List<User>> friends = new HashMap<>();


    public NetworkPlaceholder() {
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
            Random random = new Random(System.currentTimeMillis());
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



    @Override
    public User registerNewUser(String userName, String password, String name, String surname) {
        if(Token.isUserNameExists(userName))
        {
            Log.info("UserDaoImpl with username="+userName+" already exists");
            return null;
        }
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Token.registerToken(userName,password,Integer.toString(users.size()));
        User newUser = new User(users.size(),name,surname, format.format(date),null,null,null,null,            "LETI",
    true,null);
        users.add(newUser);
        return newUser;
    }

    @Override
    public String getToken(String userName, String password) {
        String t= Token.getToken(userName,password);
        Integer value = Token.getIdFromToken(t);
        if(t != null && value != null)
            users.get(value).setOnline(true);
        return t;
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
        for (User user : users) {
            if (user.getId() == id)
                return user;
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
        Integer id = Token.getIdFromToken(token);
        if(id != null && id.equals(newUserData.getId()))
        {
            User oldUser = users.get(users.indexOf(newUserData));
            oldUser.update(newUserData);
        }
    }

    @Override
    public int getFriendsCount(String token) {
        Integer id = Token.getIdFromToken(token);
        if(id == null)
            return 0;
        return getFriendsCount(id);
    }

    @Override
    public List<User> getUserFriends(String token, int limit, int offset) {
        Integer id = Token.getIdFromToken(token);
        if(id != null)
        {
            return getUserFriends(id,limit,offset);
        }
        return null;
    }

    @Override
    public boolean isFriends(Integer userId, Integer userTo) {
        List<User> uFriends = friends.get(getUserById(userId));
        return uFriends != null && uFriends.contains(getUserById(userTo));
    }

    @Override
    public boolean isFriends(String token, Integer secondUsrId) {
        Integer integer = Token.getIdFromToken(token);
        return isFriends(integer,secondUsrId);
    }

    @Override
    public List<Message> getMessgaes(Integer userTo, Integer userFrom) {
        int size = users.size();
        if(size > userTo && size > userFrom && userTo > 0 && userFrom > 0)
        {
            List<Message> msg = new ArrayList<>(messages.size()/2);
            messages.forEach(message -> {
                if(message.getUserIdFrom().equals(userFrom) && message.getUserIdTo().equals(userTo))
                {
                    msg.add(message);
                }
            });
            msg.sort((o1, o2) -> (int) (o1.getSendTimestamp() - o2.getSendTimestamp()));
            return msg;
        }
        return null;
    }


}
