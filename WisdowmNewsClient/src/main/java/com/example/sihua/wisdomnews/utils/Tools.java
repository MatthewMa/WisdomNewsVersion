package com.example.sihua.wisdomnews.utils;

import com.example.sihua.wisdomnews.global.Constants;

/**
 * Created by Sihua on 2016/5/10.
 */
public class Tools {
    public static String changeUrl(String url){
        return url.replace("10.0.2.2", Constants.SERVER_ADDRESS);
    }
}
