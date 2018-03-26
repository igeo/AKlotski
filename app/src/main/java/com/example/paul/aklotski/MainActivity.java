package com.example.paul.aklotski;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Paul on 3/11/2018.
 * http://www.javahelps.com/2015/04/import-and-use-external-database-in.html how to ship with prepopulated DB
 */

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        intent.putExtra("GAMEID", 1);


        RoomDatabase.Callback rdc = new RoomDatabase.Callback(){
            public void onCreate (SupportSQLiteDatabase db){
                // do something after database has been created
            }
            public void onOpen (SupportSQLiteDatabase db){
                //do something every time database is open
            }
        };
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "player_database").allowMainThreadQueries().addCallback(rdc).build();

        setContentView(R.layout.game_selection);
        TableLayout gt = findViewById(R.id.game_table);
        for(int gid = 0; gid < GameManager.getNumOfGames(); ++gid)
        {
            TableRow row= new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);

            TextView text = new TextView(this);
            text.setTextSize(32);
            text.setText("" + gid);
            text.setPadding(0,0,20,0);
            text.setGravity(Gravity.RIGHT);
            row.addView(text);

            text = new TextView(this);
            text.setTextSize(32);
            text.setText("" + GameManager.getGame(gid).par);
            text.setPadding(20,0,20,0);
            text.setGravity(Gravity.RIGHT);
            row.addView(text);

            // Your best
            List<GamePlayed> games = db.MyGameDao().loadAllByGameId(gid);
            int best = -1;
            for(GamePlayed game : games){
                if(!game.won)
                    continue;
                if(best >= 0) {
                    best = Math.min(best, game.steps);
                }else
                    best = game.steps;
            }
            text = new TextView(this);
            text.setTextSize(32);
            text.setText(best > 0 ? "" + best : "-");
            text.setPadding(20,0,5,0);
            if(best > 0) {
                text.setText("" + best);
                text.setGravity(Gravity.RIGHT);
            } else {
                text.setText("-" );
                text.setGravity(Gravity.CENTER_HORIZONTAL);
            }

            row.addView(text);

            text = new TextView(this);
            text.setTextSize(24);
            text.setText(GameManager.getGame(gid).name);
            text.setPadding(20,0,20,0);
            text.setGravity(Gravity.RIGHT);
            row.addView(text);

            row.setTag(Integer.valueOf(gid));
            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int gameId = (Integer)((TableRow)v).getTag();
                    Intent intent = new Intent(MainActivity.this, GameActivity.class);
                    intent.putExtra("GAMEID", gameId);
                    startActivity(intent);
                }
            });
            gt.addView(row);

            // add a h line
            text  = new TextView(this);
            lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,5);
            text.setLayoutParams(lp);
            text.setBackgroundColor(0x33ff0033);
            gt.addView(text);
        }

    }
}

