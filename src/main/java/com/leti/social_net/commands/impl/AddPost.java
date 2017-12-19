package com.leti.social_net.commands.impl;

import com.leti.social_net.commands.Command;
import com.leti.social_net.commands.NotAuthorized;
import com.leti.social_net.commands.Receiver;
import com.leti.social_net.models.Post;
import com.leti.social_net.models.Token;
import com.leti.social_net.models.User;
import com.leti.social_net.services.NetworkService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

/**
 * Created by Nikita on 19.12.2017.
 */
@Service
public class AddPost implements Command {

    private static final Logger logger = Logger.getLogger(ShowMyRecentPostsCommand.class);

    @Autowired
    private Receiver receiver;
    Scanner scanner = null;

    @Override
    public void execute() throws NotAuthorized {
        NetworkService service = receiver.getNetwork();
        String token= receiver.getToken();
        if(token == null)
            throw new NotAuthorized("This operation requires authorization");
        scanner = receiver.getScanner();
        User author = receiver.getNetwork().getUser(Token.getIdFromToken(token,receiver.getNetwork()));
        Post post = new Post();
        System.out.println("Title :");
        post.setTitle(scanner.nextLine());
        System.out.println("Text :");
        post.setText(scanner.nextLine());
        post.setLikeCount(0);
        post.setPostedTime(System.currentTimeMillis()/1000);
        post.setAuthor(author);
        receiver.getNetwork().post(post);
    }

    @Override
    public String getName() {
        return "Add post";
    }
}
