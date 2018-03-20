package com.example.paul.aklotski;

import java.util.ArrayList;

/**
 * Created by Paul on 3/13/2018.
 */

public class GameManager extends Object {
    public static ArrayList<String> getAllGames() {
        init();
        return games;
    }
    static ArrayList<String> games;
    public static int getNumOfGames() {
        init();
        return games.size();
    }

    public static String getGame(int id) {
        init();
        return games.get(id);
    }
    static void init()
    {
        if(games != null)
            return;
        games = new ArrayList<String>();
        games.add(new String("KkHhkkHhHhHhPPHhP  P"));
        games.add(new String("VKkVvkkvVHhVvPPvP  P"));
        games.add(new String("VKkPvkkPVHhVvPVv Pv "));
        games.add(new String("VKkPvkkPVHhVvPVv Pv "));
        games.add(new String("PKkPPkkP Hh VVVVvvvv"));
        games.add(new String("VKkPvkkPVHhPvVVP vv "));
        games.add(new String("PHhPVKkVvkkvVPPVv  v"));
        games.add(new String("VHhPvKkVVkkvvPVP Pv "));
        games.add(new String("PVVVPvvvHhPPHhKk  kk"));
        games.add(new String("PVVVPvvvHhHhPPKk  kk"));
    }
}
