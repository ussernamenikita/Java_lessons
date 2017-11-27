package com.leti.social_net.models.services;

import com.leti.social_net.models.Message;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Class calculate rating
 */
public class Rating {

    public int getRating(String message, List<Message> userMsg)
    {
        HashMap<String,Integer> wordsRating = new HashMap<>(10);
        List<String> best10 = userMsg.stream().map(Message::getMessage)
                .map(s -> s.split(" "))
                .map(Arrays::asList)
                .flatMap(Collection::stream)
                .filter(strings -> !strings.isEmpty())
                .map(s -> s.replaceAll("[^A-Za-z0-9]",""))
                .filter(s -> {
                    Integer r = wordsRating.get(s);
                    if(r != null)
                    {
                        r++;
                    }else
                    {
                        r = 1;
                    }
                    wordsRating.put(s,r);
                    return r == 1;
                })
                .sorted(Comparator.comparingInt(wordsRating::get))
                .limit(10).collect(Collectors.toList());
        int index = best10.indexOf(message);
        return index+1;
    }
}
