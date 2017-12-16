package com.leti.social_net.services.servicesImpl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.leti.social_net.dao.FriendsDao;
import com.leti.social_net.dao.MessagesDao;
import com.leti.social_net.dao.PostsDao;
import com.leti.social_net.dao.UserDao;
import com.leti.social_net.models.*;
import com.leti.social_net.services.NetworkService;
import javafx.geometry.Pos;
import javafx.util.Pair;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.persistence.EntityManagerFactory;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Network imitation
 */
@Service
public class NetworkPlaceholder implements NetworkService {


    private static Logger Log = Logger.getLogger(NetworkPlaceholder.class);


    UserDao userDao;
    FriendsDao friendsDao;
    MessagesDao messagesDao;
    PostsDao postsDao;


    @Autowired
    public NetworkPlaceholder(UserDao userDao, FriendsDao friendsDao, MessagesDao messagesDao, PostsDao postsDao) {
        this.userDao = userDao;
        this.friendsDao = friendsDao;
        this.messagesDao = messagesDao;
        this.postsDao = postsDao;
    }

    @Override
    public String getToken(String userName, String password) {
        String t= Token.getToken(userName,password);
        Integer value = Token.getIdFromToken(t);

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
        User newFriend = findUserById(userId);
        Integer i = Token.getIdFromToken(token);
        if(i == null) {
            return;
        }
        User currentUser = findUserById(i);
        if (currentUser != null && newFriend != null) {
            friendsDao.addFriends(currentUser,newFriend);
        }
    }

    @Override
    public Pair<Long, List<Post>> getRecentPosts(String token, long offset, int limit) {
        Integer id = Token.getIdFromToken(token);
        if(id == null)
            return null;
        User user = new User();
        user.setId(id);
        List<Post> posts = postsDao.getRecentPosts(user,offset,limit);
        Long newV = (long) (posts != null ? posts.size() : 0);
        newV= newV+offset-1;
        return new Pair<Long,List<Post>>(newV,posts) ;
    }


    @Override
    public int getFriendsCount(String token) {
        Integer id = Token.getIdFromToken(token);
        if(id == null)
            return 0;
        User user = new User();
        user.setId(id);
        List<User> list = friendsDao.getFriends(user);
        return list == null ? 0 : list.size();
    }

    @Override
    public List<User> getUserFriends(String token, int limit, int offset) {
        Integer id = Token.getIdFromToken(token);
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
        Integer integer = Token.getIdFromToken(token);
        return isFriends(integer,secondUsrId);
    }

    @Override
    public List<Message> getMessgaes(Integer userTo, Integer userFrom) {
        List<Message> msg = messagesDao.getMessages(userFrom,userTo);
        msg.sort((o1, o2) -> (int) (o1.getSendTimestamp() - o2.getSendTimestamp()));
        return msg;

    }






}
