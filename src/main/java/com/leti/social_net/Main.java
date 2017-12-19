package com.leti.social_net;

import com.leti.social_net.commands.NotAuthorized;
import com.leti.social_net.config.Configuration;
import com.leti.social_net.services.Menu;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;
import java.net.MalformedURLException;

public class Main {

    public static void main(String[] args) throws NotAuthorized {
        try {
            System.setProperty("log4j.configuration", new File("resources", "log4j.properties").toURI().toURL().toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Configuration.class);
        Menu menu = (Menu) applicationContext.getBean("menu");
        menu.showMenu();
        applicationContext.close();
    }
}
