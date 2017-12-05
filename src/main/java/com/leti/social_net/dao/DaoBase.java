package com.leti.social_net.dao;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;


public abstract class DaoBase {

    Logger logger = Logger.getLogger(DaoBase.class);

    protected String url = "jdbc:postgresql://127.0.0.1:5432/javadb";

    protected static final String DRIVER_CLASS_NAME = "org.postgresql.Driver";

    protected String user = "java";

    protected String password = "java";

    protected abstract void createIfNotExistsTable();


    /**
     * Create and return new connection  from database
     * @return new connection
     */
    public Connection getConnection()
    {
            try{
                Class.forName(DRIVER_CLASS_NAME);
                logger.info("Driver is created");
                return DriverManager.getConnection(url, user, password);
            }catch (Exception  e)
            {
                e.printStackTrace();
            }
            return null;
    }
}
