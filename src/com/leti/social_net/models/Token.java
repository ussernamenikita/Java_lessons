package com.leti.social_net.models;

import com.leti.social_net.models.services.NetworkService;
import com.leti.social_net.models.servicesImpl.NetworkPlaceholder;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.util.HashMap;

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
        return tokens.get(userName+password);
    }
}

