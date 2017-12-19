package com.leti.social_net.models;

import com.leti.social_net.dao.UserDao;
import com.leti.social_net.services.NetworkService;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;

import java.util.HashMap;
import java.util.Set;

/**
 * Work with tokens
 */
public class Token {


    @Nullable
    public static Integer getIdFromToken(@NotNull String token, NetworkService service)
    {
        String UP[] = token.split("@");
        if(UP.length < 1)
        {
            return null;
        }
        User user = service.getUserByLoginAndPassword(UP[0],UP[1]);
        return user == null ?-1 : user.getId();
    }




    @Nullable
    public static String getToken(@NotNull String userName,@NotNull String password)
    {
        return userName+"@"+password;
    }

}

