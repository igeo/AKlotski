package com.example.paul.aklotski;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager wm = (WindowManager) this.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        try {
            display.getSize(windowSize);
        } catch (NoSuchMethodError e) {
            windowSize.x = display.getWidth();
            windowSize.y = display.getHeight();
        }

        //Adding views to FrameLayout
        ArrayList<String[]> games = new  ArrayList<String[]>();
        games.add(new String[]{
                "KkHh",
                "kkHh",
                "HhHh",
                "PPHh",
                "P  P"});
        games.add( new String[]{
                "VKkV",
                "vkkv",
                "VHhV",
                "vPPv",
                "P  P"});
        games.add( new String[]{
                "VKkP",
                "vkkP",
                "VHhV",
                "vPVv",
                " PV "});
        games.add( new String[]{
                "VKkP",
                "vkkP",
                "VHhV",
                "vPVv",
                " Pv "});
        games.add( new String[]{
                "PKkP",
                "PkkP",
                " Hh ",
                "VVVV",
                "vvvv"});
        games.add( new String[]{
                "VKkP",
                "vkkP",
                "VHhP",
                "vVVP",
                " vv "});

        Intent intent = getIntent();
        int gameId = intent.getIntExtra("GAMEID", 0);
        gameId %= games.size();
        String game [] = games.get(gameId);
        board = new Board(game);
        BoardView bv = new BoardView(this, board, windowSize);
        setContentView(bv);
    }

    @Override
    public void onClick(View v) {
       //((TextView)v).setText("C");
       board.movePiece(((BlockView)v).id, Board.Direction.None);
        BoardView bv = new BoardView(this, board, windowSize);
        setContentView(bv);
    }
    public void movePiece(View v, Board.Direction direction)
    {
        board.movePiece(((BlockView)v).id, direction);
        BoardView bv = new BoardView(this, board, windowSize);
        setContentView(bv);
    }
    Board board;
    Point windowSize = new Point();
}

// one block of the game
    class BlockView extends TextView {
        BlockView(Context context, Block b, int index, int gridWidth, int gridHeight) {
            super(context);
            block = b;
            id = index;
            setTextSize(32);
            setBackgroundColor(0x88ffa54f);
            setTextColor(0x330D0D0D);
            setGravity(Gravity.CENTER);
            int G = gridHeight / 50;
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(gridWidth * block.W() - 2*G, gridHeight * block.H()-2*G);
            int x1 = block.x * gridWidth + G;
            int y1 = block.y * gridHeight + G;
            lp.setMargins(x1, y1, 0, 0);
            setLayoutParams(lp);
            setText(block.t.toString());
        }
        int id;
        Block block;
    }

    class BoardView extends FrameLayout {
        BoardView(final Context context, Board board, Point windowSize) {
            super(context);
            int windowWidth = windowSize.x;
            int windowHeight = windowSize.y;
            int gridWidth = windowWidth / board.W;
            int gridHeight = windowHeight / board.H;
            gridWidth = Math.min(gridWidth, gridHeight);
            gridHeight = gridWidth;

            setLayoutParams(new AbsListView.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT));
            setBackgroundColor(0xffbc4d05);
            for (int i = 0; i < board.blocks.length; ++i) {
                BlockView b = new BlockView(context, board.blocks[i], i, gridWidth, gridHeight);
                boolean useClick = false;
                if(useClick)
                    b.setOnClickListener((OnClickListener) context);
                else // use swipe
                    b.setOnTouchListener(new OnSwipeTouchListener(context) {
                        public boolean onSwipeTop() {
                            Toast.makeText(context, "top", Toast.LENGTH_SHORT).show();
                            ((GameActivity)context).movePiece(view, Board.Direction.U);
                            return true;
                        }
                        public boolean onSwipeRight() {
                            Toast.makeText(context, "right", Toast.LENGTH_SHORT).show();
                            ((GameActivity)context).movePiece(view, Board.Direction.R);
                            return true;
                        }
                        public boolean onSwipeLeft() {
                            Toast.makeText(context, "left", Toast.LENGTH_SHORT).show();
                            ((GameActivity)context).movePiece(view, Board.Direction.L);
                            return true;
                        }
                        public boolean onSwipeBottom() {
                            Toast.makeText(context, "bottom", Toast.LENGTH_SHORT).show();
                            ((GameActivity)context).movePiece(view, Board.Direction.D);
                            return true;
                        }
                    });
                addView(b);
            }
        }
    }
