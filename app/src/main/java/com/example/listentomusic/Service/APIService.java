package com.example.listentomusic.Service;

public class APIService {
    private static String base_url = "https://mp333.000webhostapp.com/server/";
    public static DataService getService(){
        return APIRetrofitClient.getClient(base_url).create(DataService.class);

    }
}
