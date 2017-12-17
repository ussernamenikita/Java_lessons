package com.leti.social_net;

import com.leti.social_net.commands.NotAuthorized;
import com.leti.social_net.config.Configuration;
import com.leti.social_net.services.Menu;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) throws NotAuthorized {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Configuration.class);
        Menu menu = (Menu) applicationContext.getBean("menu");
        menu.showMenu();
        applicationContext.close();
    }
}
