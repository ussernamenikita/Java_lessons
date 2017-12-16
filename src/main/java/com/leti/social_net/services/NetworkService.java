package com.leti.social_net.services;

import com.leti.social_net.models.*;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import javafx.util.Pair;

import java.util.List;

/**
 * Service that can be used for network request
 */
public interface NetworkService {


    /**
     * Get token what can be used for identify user by server.
     * @param userName username
     * @param password password
     * @return new token, or null if user or password is incorrect
     */
    @Nullable
    String getToken(@NotNull String userName,@NotNull String password);





    /**
     * Send message to some user
     * @param msg message
     */
    void sendMessage(@NotNull Message msg);






    /**
     * Add some user to friends
     * @param token some string which could identify user on server.
     * @param userId user which need add to friends
     */
    void addUserToFriend(@NotNull String token, int userId);




    /**
     * Get recent posts.
     * For example with offset = 0 and limit = 10
     * user receive last 10 added posts.
     * If need receive next 20 posts need send request with limit = 20,
     * and offset = {index of last received post from response}-20
     * @param token some string which could identify user on server.
     * @param offset offset by posts count
     * @param limit count of post need receive
     * @return list of recent posts, and index of last received post.
     */
    @Nullable
    Pair<Long,List<Post>> getRecentPosts(@NotNull String token, long offset, int limit);








    /**
     * Get users friends count
     * @param token user token whose friends count need
     * @return count of friends
     */
    int getFriendsCount(String token);

    /**
     * Get user friends list from server.
     * Limit and offset use for part load,
     * for example, there are so many friends
     *  that you can not get all at once.
     * @param token of user whose friends you want to get
     * @param limit how many users need receive
     * @param offset begin index in friends list( last index = offset+limit)
     * @return users list or null. Return null if users
     * friends count < offset
     */
    @Nullable
    List<User> getUserFriends(String token,int limit,int offset);

    /**
     * Check if user with id userId is friend for user with id userTo
     * @param userId first user id
     * @param userTo second user id
     * @return true if user with id userId friend for user with id userTo
     */
    boolean isFriends(Integer userId, Integer userTo);


    /**
     * Check if user with token is friend for user with id userTo
     * @param token token for first user
     * @param secondUsrId second user id
     * @return true if user with id userId friend for user with id userTo
     */
    boolean isFriends(String token,Integer secondUsrId);


    /**
     * Get all messages from user with id userTo,
     * sent to user with id equals userFrom
     * @param userTo user id to
     * @param userFrom user id from
     * @return all messages from userFrom to userTo
     */
    List<Message> getMessgaes(Integer userTo,Integer userFrom);
}
