package com.leti.social_net.dao.impl;

import com.leti.social_net.dao.DaoBase;
import com.leti.social_net.dao.UserDao;
import com.leti.social_net.models.User;
import javafx.scene.layout.StackPane;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation dao for user
 */
@Repository
public class UserDaoImpl extends DaoBase implements UserDao{

    private Logger log = Logger.getLogger(UserDaoImpl.class);
    private Statement statement  = null;

    private Connection connection;

    public UserDaoImpl() {
        connection = getConnection();
    }

    @Override
    public List<com.leti.social_net.models.User> getAllUser() {
        ArrayList<User> users = new ArrayList<User>();
        ResultSet result = null;
        try {
            if(statement == null)
                statement = connection.createStatement();
            result = statement.executeQuery("SELECT * FROM users ");
            log.info("Выводим пользователей которые есть в базе данных");
            while (result.next()) {
                User tmpuser = new User(result.getInt("id"),
                        result.getString("name"),
                        result.getString("surname"),
                        result.getString("registered"),
                        result.getString("email"),
                        result.getString("address"),
                        result.getString("phone"),
                        result.getString("birthday"),
                        result.getString("company"),
                        result.getBoolean("online"),
                        result.getString("avatar"));
                users.add(tmpuser);
                log.info("Получен пользователь "+ tmpuser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public com.leti.social_net.models.User getParticularUser(Integer id) {
        ResultSet result = null;
        try {
            String query = "SELECT * FROM users where id = "+id;
            if(statement == null)
                statement = connection.createStatement();
            result = statement.executeQuery(query);
            log.info("Выполнен запрос на пользователя с id="+id);
            while (result.next()) {
                User tmpuser = new User(result.getInt("id"),
                        result.getString("name"),
                        result.getString("surname"),
                        result.getString("registered"),
                        result.getString("email"),
                        result.getString("address"),
                        result.getString("phone"),
                        result.getString("birthday"),
                        result.getString("company"),
                        result.getBoolean("online"),
                        result.getString("avatar"));
                log.info("Результат :"+ tmpuser);
                return tmpuser;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insertOrUpdate(List<com.leti.social_net.models.User> users) {
        for (User user : users) {
            insertOrUpdate(user);
        }
    }

    @Override
    public void insertOrUpdate(com.leti.social_net.models.User user) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.
                    prepareStatement("insert into users values(?,?,?,?,?,?,?,?,?,?,?) on conflict(id) DO update set name = ?,surname = ?,registered = ?,email = ?,address = ?,phone = ?,birthday = ?,company = ?,online = ?,avatar = ? ");

            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getSurname());
            preparedStatement.setString(4, user.getRegistered());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getAddress());
            preparedStatement.setString(7, user.getPhone());
            preparedStatement.setString(8, user.getBirthday());
            preparedStatement.setString(9, user.getCompany());
            preparedStatement.setBoolean(10, user.getOnline());
            preparedStatement.setString(11, user.getAvatar());
            preparedStatement.setString(12, user.getName());
            preparedStatement.setString(13, user.getSurname());
            preparedStatement.setString(14, user.getRegistered());
            preparedStatement.setString(15, user.getEmail());
            preparedStatement.setString(16, user.getAddress());
            preparedStatement.setString(17, user.getPhone());
            preparedStatement.setString(18, user.getBirthday());
            preparedStatement.setString(19, user.getCompany());
            preparedStatement.setBoolean(20, user.getOnline());
            preparedStatement.setString(21, user.getAvatar());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void createIfNotExistsTable() {
        try {
            if(statement == null)
            {
                statement = connection.createStatement();
            }
            String sqlCreate = "CREATE TABLE IF NOT EXISTS users(\n" +
                    "id serial primary key ,\n" +
                    "name VARCHAR(50) NOT NULL,\n" +
                    "surname VARCHAR(50) NOT NULL,\n" +
                    "registered VARCHAR(50) NOT NULL,\n" +
                    "email VARCHAR(50) NOT NULL,\n" +
                    "address VARCHAR(50) NOT NULL,\n" +
                    "phone VARCHAR(50) NOT NULL,\n" +
                    "birthday VARCHAR(50) NOT NULL,\n" +
                    "company VARCHAR(50) NOT NULL,\n" +
                    "online boolean NOT NULL,\n" +
                    "avatar VARCHAR(100) NOT NULL)\n";
            statement.execute(sqlCreate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
