package com.leti.social_net.dao.impl;

import com.leti.social_net.dao.DaoBase;
import com.leti.social_net.dao.MessagesDao;
import com.leti.social_net.models.Message;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Types.INTEGER;


/**
 * Messages dao implementation
 */
@Repository
public class MessagesDaoImpl extends DaoBase  implements MessagesDao{
    Logger log = Logger.getLogger(MessagesDaoImpl.class);

    Connection connection = null;

    public MessagesDaoImpl() {
        connection = getConnection();
        createIfNotExistsTable();
    }


    @Override
    public List<Message> getMessages(int userFromId, int userToid) {
        ArrayList<Message> messages = new ArrayList<>();
        ResultSet result = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM messages where userIdFrom = ? and userIdTo = ? ");
            statement.setInt(1,userFromId);
            statement.setInt(2,userToid);
            result = statement.executeQuery();
            while (result.next()) {
                Message tmpmsg = new Message();
                tmpmsg.setUserIdFrom(result.getInt("userIdFrom"));
                tmpmsg.setUserIdTo(result.getInt("userIdTo"));
                tmpmsg.setMessage(result.getString("message"));
                tmpmsg.setSendTimestamp((long) result.getInt("sendTimestamp"));
                tmpmsg.setReadTimestamp((long) result.getInt ("readTimestamp"));
                messages.add(tmpmsg);
                log.info("Сообщение прочитано из базы "+ tmpmsg);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }

    @Override
    public void putMessage(Message msg) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("insert into messages (userIdFrom,userIdTo,message,sendTimestamp,readTimestamp) values(?,?,?,?,?) on conflict(id) do nothing");
            preparedStatement.setInt(1,msg. getUserIdFrom());
            preparedStatement.setInt (2,msg.getUserIdTo());
            preparedStatement.setString (3,msg.getMessage());
            preparedStatement.setInt (4, msg.getSendTimestamp().intValue());
            if(msg.getReadTimestamp() == null)
            {
                preparedStatement.setNull(5,INTEGER);
            }
            else {
                preparedStatement.setInt(5, Math.toIntExact(msg.getReadTimestamp()));
            }
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void createIfNotExistsTable() {
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE if not exists messages(id serial primary key,"+
                    "userIdFrom integer NOT NULL,\n" +
                    "userIdTo integer NOT NULL,\n" +
                    "message VARCHAR(50) ,\n" +
                    "sendTimestamp integer NOT NULL,\n" +
                    "readTimestamp integer )");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
