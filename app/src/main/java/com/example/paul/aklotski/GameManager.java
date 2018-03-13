package com.example.paul.aklotski;

import java.util.ArrayList;

/**
 * Created by Paul on 3/13/2018.
 */

public class GameManager extends Object {
    public static ArrayList<String[]> getAllGames() {
        init();
        return games;
    }

    public static int getNumOfGames() {
        init();
        return games.size();
    }

    public static String[] getGame(int id) {
        init();
        return games.get(id);
    }
    static void init()
    {
        if(games != null)
            return;
        games = new ArrayList<String[]>();
        games.add(new String[]{
                "KkHh",
                "kkHh",
                "HhHh",
                "PPHh",
                "P  P"});
        games.add(new String[]{
                "VKkV",
                "vkkv",
                "VHhV",
                "vPPv",
                "P  P"});
        games.add(new String[]{
                "VKkP",
                "vkkP",
                "VHhV",
                "vPVv",
                " Pv "});
        games.add(new String[]{
                "VKkP",
                "vkkP",
                "VHhV",
                "vPVv",
                " Pv "});
        games.add(new String[]{
                "PKkP",
                "PkkP",
                " Hh ",
                "VVVV",
                "vvvv"});
        games.add(new String[]{
                "VKkP",
                "vkkP",
                "VHhP",
                "vVVP",
                " vv "});
        games.add(new String[]{
                "PHhP",
                "VKkV",
                "vkkv",
                "VPPV",
                "v  v"});
        games.add(new String[]{
                "VHhP",
                "vKkV",
                "Vkkv",
                "vPVP",
                " Pv "});

        games.add(new String[]{
                "PVVV",
                "Pvvv",
                "HhPP",
                "HhKk",
                "  kk"});

    }
    static ArrayList<String[]> games;

}
