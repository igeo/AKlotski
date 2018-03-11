package com.example.paul.aklotski;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by Paul on 3/11/2018.
 */

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        intent.putExtra("GAMEID", 1);
        //startActivity(intent);
        FrameLayout framelayout = new FrameLayout(this);
        framelayout.setLayoutParams(new AbsListView.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));

        for(int gid = 0; gid < 6; ++gid)

            {
                int W = 4;
                int y = gid / W;
                int x = gid - y * W;
                PieceLayout piece = new PieceLayout(this, x, y);
                piece.setNum(gid);
                piece.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int gameId = ((PieceLayout)v).getNum();
                    Intent intent = new Intent(MainActivity.this, GameActivity.class);
                    intent.putExtra("GAMEID", gameId);
                    startActivity(intent);
                    }
                });
                framelayout.addView(piece);
            }
        setContentView(framelayout);
    }
}


class PieceLayout extends TextView {
    PieceLayout(Context context, int x, int y)
    {
        super(context);
        setTextSize(32);
        setBackgroundColor(0x33ff0033);
        setTextColor(0x330D0D0D);
        setGravity(Gravity.CENTER);
        int W = 200;
        int H = 200;
        int G = 5;
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(W, H);
        lp.setMargins(x*(W+G), y*(H+G), 0, 0);
        setLayoutParams(lp);
        setNum(0);
      }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
        setText(num + "");
    }
    int num;
}


