package com.example.paul.aklotski;

import java.util.ArrayList;

/**
 * Created by Paul on 3/13/2018.
 */

public class GameManager extends Object {
    public static ArrayList<GameInfo> getAllGames() {
        init();
        return games;
    }
    static ArrayList<GameInfo> games;
    public static int getNumOfGames() {
        init();
        return games.size();
    }

    public static GameInfo getGame(int id) {
        init();
        return games.get(id);
    }
    static void init()
    {
        if(games != null)
            return;
        games = new ArrayList<GameInfo>();
        games.add(new GameInfo("KkHhkkHhHhHhPPHhP  P", 32));
        games.add(new GameInfo("VKkVvkkvVHhVvPPvP  P", 116, "横刀立马"));
        games.add(new GameInfo("VKkPvkkPVHhVvPVv Pv ", 64));
        games.add(new GameInfo("PKkPPkkP Hh VVVVvvvv", 61));
        games.add(new GameInfo("VKkPvkkPVHhPvVVP vv ", 95));
        games.add(new GameInfo("PHhPVKkVvkkvVPPVv  v", 13));
        games.add(new GameInfo("VHhPvKkVVkkvvPVP Pv ", 22));
        games.add(new GameInfo("PVVVPvvvHhPPHhKk  kk", 124));
        games.add(new GameInfo("PVVVPvvvHhHhPPKk  kk", 3));
        games.add(new GameInfo("PPPVKkVvkkvV Hhv PHh", 179, "峰回路转"));
    }
}
