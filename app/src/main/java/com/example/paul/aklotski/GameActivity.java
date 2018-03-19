package com.example.paul.aklotski;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.Button;
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
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int margin = 20;
        windowSize.x -= margin * 2 * metrics.density;
        windowSize.y -= margin * 2 * metrics.density;

        Intent intent = getIntent();
        gameId = intent.getIntExtra("GAMEID", 0);

        String game [] =  GameManager.getGame(gameId);
        board = new Board(game);

        setContentView(R.layout.game_activity);
        updateboard();
        Button undoButton = findViewById(R.id.undo);
        undoButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "back", Toast.LENGTH_SHORT).show();
                ((GameActivity)(v.getContext())).undo();
        }});
    }

    @Override
    public void onClick(View v) {
        movePiece(v, Board.Direction.None);
    }
    private void undo() {
        if(history.isEmpty())
            return;
        board = history.get(history.size()-1);
        history.remove(history.size()-1);
        updateboard();
    }
    private void updateboard(){
        BoardView bv = new BoardView(this, board, windowSize, hasWon);
        FrameLayout layoutBoard = findViewById(R.id.board);
        layoutBoard.removeAllViews();
        layoutBoard.addView(bv);
        layoutBoard.requestLayout();
        TextView viewSteps = findViewById(R.id.steps);
        viewSteps.setText("step " + history.size());
    }
    public void movePiece(View v, Board.Direction direction)
    {
        Board previous = board.clone();
        if(board.movePiece(((BlockView)v).id, direction)) {
            history.add(previous);
        }
        if(board.hasWon())
        {
            hasWon = true;
            AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                    AppDatabase.class, "player_database").allowMainThreadQueries().build();
            GamePlayed game = new GamePlayed();
            game.gameId = gameId;
            game.won = true;
            game.steps = history.size();
            db.MyGameDao().insert(game);
        }
        updateboard();
    }
    Board board;
    ArrayList<Board> history = new ArrayList<Board>();
    int gameId;
    Point windowSize = new Point();
    boolean hasWon = false;
}

// one block of the game
    class BlockView extends  android.support.v7.widget.AppCompatTextView  {
        BlockView(Context context, Block b, int index, int gridWidth, int gridHeight) {
            super(context);
            block = b;
            id = index;
            setTextSize(32);

            int color = Color.BLACK;
            switch (block.t){
                case K: color = 0xffe24d3b; break;
                case H: color = 0xff4396dc; break;
                case V: color = 0xffedc500; break;
                case P: color = 0xff34af5e; break;
            }
            setBackgroundColor(color);
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
        BoardView(final Context context, Board board, Point windowSize, boolean won) {
            super(context);
            int windowWidth = windowSize.x;
            int windowHeight = windowSize.y;
            int gridWidth = windowWidth / board.W;
            int gridHeight = windowHeight / board.H;
            gridWidth = Math.min(gridWidth, gridHeight);
            gridHeight = gridWidth;

            setLayoutParams(new AbsListView.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT));
            //setBackgroundColor(0xffbc4d05);

            for (int i = 0; i < board.blocks.length; ++i) {
                //if(won && board.blocks[i].t == Block.Type.K) continue;
                BlockView b = new BlockView(context, board.blocks[i], i, gridWidth, gridHeight);

                if(!won) {
                    boolean useClick = false;
                    if (useClick)
                        b.setOnClickListener((OnClickListener) context);
                    else // use swipe
                        b.setOnTouchListener(new OnSwipeTouchListener(context) {
                            public boolean onSwipeTop() {
                                Toast.makeText(context, "top", Toast.LENGTH_SHORT).show();
                                ((GameActivity) context).movePiece(view, Board.Direction.U);
                                return true;
                            }

                            public boolean onSwipeRight() {
                                Toast.makeText(context, "right", Toast.LENGTH_SHORT).show();
                                ((GameActivity) context).movePiece(view, Board.Direction.R);
                                return true;
                            }

                            public boolean onSwipeLeft() {
                                Toast.makeText(context, "left", Toast.LENGTH_SHORT).show();
                                ((GameActivity) context).movePiece(view, Board.Direction.L);
                                return true;
                            }

                            public boolean onSwipeBottom() {
                                Toast.makeText(context, "bottom", Toast.LENGTH_SHORT).show();
                                ((GameActivity) context).movePiece(view, Board.Direction.D);
                                return true;
                            }
                        });
                }
                addView(b);
            }
        }
    }
