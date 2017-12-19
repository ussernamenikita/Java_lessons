package com.leti.social_net.services.servicesImpl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.leti.social_net.dao.FriendsDao;
import com.leti.social_net.dao.MessagesDao;
import com.leti.social_net.dao.PostsDao;
import com.leti.social_net.dao.UserDao;
import com.leti.social_net.models.Message;
import com.leti.social_net.models.Post;
import com.leti.social_net.models.Token;
import com.leti.social_net.models.User;
import com.leti.social_net.services.NetworkService;
import javafx.util.Pair;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Network imitation
 */
@Service
public class NetworkPlaceholder implements NetworkService {






    private static Logger Log = Logger.getLogger(NetworkPlaceholder.class);


    @Autowired
    UserDao userDao;
    @Autowired
    FriendsDao friendsDao;
    @Autowired
    MessagesDao messagesDao;
    @Autowired
    PostsDao postsDao;




    @Override
    public String getToken(String userName, String password) {
        String t = Token.getToken(userName, password);
        Integer value = Token.getIdFromToken(t, this);
        if (value == null || value == -1) {
            return null;
        }
        return t;
    }



    private User findUserById(int id) {
        return userDao.getParticularUser(id);
    }



    @Override
    public void sendMessage(Message msg) {
        messagesDao.putMessage(msg);
    }





    @Override
    public void addUserToFriend(String token, int userId) {
        Integer i = Token.getIdFromToken(token,this);
        if(i == null) {
            return;
        }
        if(isFriends(i,userId))
        {
            return;
        }
        User currentUser = findUserById(i);
        User newFriend = findUserById(userId);
        if (currentUser != null && newFriend != null) {
            friendsDao.addFriends(currentUser,newFriend);
        }
    }

    @Override
    public Pair<Long, List<Post>> getMyRecentPosts(String token, long offset, int limit) {
        Integer id = Token.getIdFromToken(token,this);;
        if(id == null)
            return null;
        User user = userDao.getParticularUser(id);
        List<Post> posts = postsDao.getRecentPosts(user,offset,limit);
        Long newV = (long) (posts != null ? posts.size() : 0);
        newV= newV+offset-1;
        return new Pair<Long,List<Post>>(newV,posts) ;
    }

    @Override
    public Pair<Long, List<Post>> getRecentPosts(long offset, int limit) {
        List<Post> posts = postsDao.getRecentPosts(offset,limit);
        Long newV = (long) (posts != null ? posts.size() : 0);
        newV= newV+offset-1;
        return new Pair<Long,List<Post>>(newV,posts) ;
    }


    @Override
    public int getFriendsCount(String token) {
        Integer id = Token.getIdFromToken(token,this);;
        if(id == null)
            return 0;
        User user = new User();
        user.setId(id);
        List<User> list = friendsDao.getFriends(user);
        return list == null ? 0 : list.size();
    }

    @Override
    public List<User> getUserFriends(String token, int limit, int offset) {
        Integer id = Token.getIdFromToken(token,this);;
        if(id != null)
        {
            User u = new User();
            u.setId(id);
            return friendsDao.getFriends(u,limit,offset);
        }
        return null;
    }

    @Override
    public boolean isFriends(Integer userId, Integer userTo) {
        User u1 = new User();
        u1.setId(userId);
        User u2 = new User();
        u2.setId(userTo);
        List<User> list = friendsDao.getFriends(u1);
        return list != null && list.contains(u2);
    }

    @Override
    public boolean isFriends(String token, Integer secondUsrId) {
        Integer integer = Token.getIdFromToken(token,this);;
        return isFriends(integer,secondUsrId);
    }

    @Override
    public List<Message> getMessgaes(User userTo, User userFrom) {
        List<Message> msg = messagesDao.getMessages(userFrom,userTo);
        msg.sort((o1, o2) -> (int) (o1.getSendTimestamp() - o2.getSendTimestamp()));
        return msg;

    }

    @Override
    public User getUserByLoginAndPassword(String login, String password) {
        return userDao.getUserByLoginAndPassword(login,password);
    }

    @Override
    public boolean userExist(String username) {
        User user = userDao.getUserByLogin(username);
        return  user != null;
    }

    @Override
    public User getUser(Integer id) {
        if(id == null)
            return null;
        return userDao.getParticularUser(id);
    }

    @Override
    public void post(Post post) {
        postsDao.insertPost(post);
    }


}
