package com.leti.social_net.commands.impl;

import com.leti.social_net.commands.Command;
import com.leti.social_net.commands.NotAuthorized;
import com.leti.social_net.commands.Receiver;
import com.leti.social_net.models.Post;
import com.leti.social_net.services.NetworkService;
import javafx.util.Pair;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

/**
 * Show recent posts
 * show all posts
 */
@Service
public class ShowRecentPosts implements Command {

    /**
     * Standard logger
     */
    private static final Logger logger = Logger.getLogger(ShowRecentPosts.class);


    /**
     * Receiver instance
     */
    private final Receiver receiver;

    /**
     * Scanner reference
     */
    Scanner scanner = null;

    @Autowired
    public ShowRecentPosts(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() throws NotAuthorized {
        NetworkService service = receiver.getNetwork();
        scanner = receiver.getScanner();
        Long offset = 0L;
        for (int i = 0;;i++) {
            System.out.println("Posts, page "+i);
            Pair<Long, List<Post>> requests = service.getRecentPosts(offset,10);
            offset = requests.getKey();
            List<Post> posts = requests.getValue();
            logger.debug("Page number "+i+", get "+(posts != null ? posts.size(): 0)+" posts");
            if(posts != null) {
                for (Post p : posts) {
                    System.out.println(p);
                }
                if (posts.size() < 10) {
                    System.out.println("All posts viewed");
                    break;
                } else {
                    int response = -1;
                    while (response < 1 || response > 2) {
                        System.out.println("Show next page ?");
                        System.out.println("1 - yes 2 - no");
                        response = scanner.nextInt();
                    }
                    if (response == 2) {
                        break;
                    }
                }
            }
        }
    }

    @Override
    public String getName() {
        return "Show recent posts";
    }
}
