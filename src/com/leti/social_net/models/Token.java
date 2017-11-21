package com.leti.social_net.models;

import com.leti.social_net.models.services.NetworkService;
import com.leti.social_net.models.servicesImpl.NetworkPlaceholder;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.util.HashMap;
import java.util.Set;

/**
 * Work with tokens
 */
public class Token {

    private static HashMap<String,String> tokens = new HashMap<>(10);

    @Nullable
    public static Integer getIdFromToken(@NotNull String token)
    {
        Integer id;
        try{
            id = Integer.parseInt(token);
        }catch (NumberFormatException e)
        {
            return null;
        }
        if(id < 0)
        {
            return  null;
        }else
            return id;
    }


    public static void registerToken(@NotNull String userName,@NotNull String password,@NotNull String id)
    {
        tokens.put(userName+password,id);
    }

    @Nullable
    public static String getToken(@NotNull String userName,@NotNull String password)
    {
        return tokens.get(userName+"@"+password);
    }

    /**
     * Check if username already exists
     * @param userName username
     */
    public static boolean isUserNameExists(String userName) {
        boolean contains = false;
        Set<String> tkns = tokens.keySet();
        for (String curT:tkns)
        {
            if(curT.startsWith(userName+"@"))
            {
                contains = true;
                break;
            }
        }
        return contains;
    }
}

