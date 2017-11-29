package com.leti.social_net.dao.impl;

import com.leti.social_net.dao.DaoBase;
import com.leti.social_net.dao.PostsDao;
import com.leti.social_net.models.Post;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Posts DAO implementation
 */
@Repository
public class PostsDaoImpl extends DaoBase implements PostsDao {
    Connection connection = null;

    public PostsDaoImpl() {
        connection  = getConnection();
        createIfNotExistsTable();
    }

    @Override
    public void insertPost(Post post) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("insert into posts values(?,?,?,?,?,?,?) on conflict do update ");
            preparedStatement.setInt(1, post.getId());
            preparedStatement.setString(1, post.getTitle());
            preparedStatement.setInt(1, post.getAuthor());
            preparedStatement.setInt(1, post.getMediaId());
            preparedStatement.setString(1, post.getText());
            preparedStatement.setInt(1, post.getLikeCount());
            preparedStatement.setInt(1, new Long(post.getPostedTime()).intValue());
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void insertPosts(List<Post> postsList) {
            postsList.forEach(this::insertPost);
    }

    @Override
    public List<Post> getAllPosts() {
        ArrayList<Post> resultSet = new ArrayList<>();
        ResultSet result = null;
        Statement statement;
        try {
            String query = "SELECT * FROM posts";
            statement = connection.createStatement();
            result = statement.executeQuery(query);
            while (result.next()) {
                Post tmpPost = new Post();
                tmpPost.setId(result.getInt("id"));
                tmpPost.setAuthor(result.getInt("author"));
                tmpPost.setLikeCount(result.getInt("likeCount"));
                tmpPost.setMediaId(result.getInt("mediaId"));
                tmpPost.setPostedTime(result.getInt("postedTime"));
                tmpPost.setText(result.getString("text"));
                tmpPost.setTitle(result.getString("title"));
                resultSet.add(tmpPost);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    @Override
    public List<Post> getPostsByUser(int userId) {
        ArrayList<Post> resultSet = new ArrayList<>();
        ResultSet result = null;
        Statement statement;
        try {
            String query = "SELECT * FROM posts where author = "+userId;
            statement = connection.createStatement();
            result = statement.executeQuery(query);
            while (result.next()) {
                Post tmpPost = new Post();
                tmpPost.setId(result.getInt("id"));
                tmpPost.setAuthor(result.getInt("author"));
                tmpPost.setLikeCount(result.getInt("likeCount"));
                tmpPost.setMediaId(result.getInt("mediaId"));
                tmpPost.setPostedTime(result.getInt("postedTime"));
                tmpPost.setText(result.getString("text"));
                tmpPost.setTitle(result.getString("title"));
                resultSet.add(tmpPost);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    @Override
    protected void createIfNotExistsTable() {
        String sqlCommandText = "CREATE TABLE if not exists posts(\n" +
                "id serial primary key,\n" +
                "title VARCHAR(50) NOT NULL,\n" +
                "author integer NOT NULL,\n" +
                "mediaId integer ,\n" +
                "_text text   NOT NULL,\n" +
                "likeCount DOUBLE NOT NULL,\n" +
                "postedTime DOUBLE NOT NULL)";
        try {
            Statement  statement = connection.createStatement();
            statement.execute(sqlCommandText);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
