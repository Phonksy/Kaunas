package com.example.kaunas;

import java.util.ArrayList;

public class hotel {
    private String mname;
    private String maddress;
    private int mrating;

    public hotel(String name, String address, int rating) {
        mname = name;
        maddress = address;
        mrating = rating;
    }

    public String getName() {
        return mname;
    }

    public String getAddress() {
        return maddress;
    }

    public int getRating() {
        return mrating;
    }

    public static ArrayList<hotel> createHotelsList(String name, String address, int rating) {
        ArrayList<hotel> hotels = new ArrayList<hotel>();
        hotels.add(new hotel(name,address,rating));
        return hotels;
    }
}
