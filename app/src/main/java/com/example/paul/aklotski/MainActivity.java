package com.example.paul.aklotski;

import android.content.Context;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.app.Activity;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main); // use code ot set instead of xml
        /*
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.forgotmenot);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT));
        TextView textView1 = new TextView(this);
        textView1.setText("Klotski");
        textView1.setTextSize(30);
        textView1.setGravity(Gravity.CENTER);
        textView1.setTextColor(Color.parseColor("#f3f3f3"));
        textView1.setTypeface(null, Typeface.BOLD);
        textView1.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT));
        FrameLayout.LayoutParams lp1 = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        lp1.setMargins(0, 20, 0, 0);
        textView1.setLayoutParams(lp1);
    */
        WindowManager wm = (WindowManager) this.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        try {
            display.getSize(windowSize);
        } catch (NoSuchMethodError e) {
            windowSize.x = display.getWidth();
            windowSize.y = display.getHeight();
        }

        //Adding views to FrameLayout
        String state[] = new String[]{
                "VKkV",
                "vkkv",
                "VHhV",
                "vPPv",
                "P  P"};
        state = new String[]{
                "KkHh",
                "kkHh",
                "HhHh",
                "PPHh",
                "P  P"};
        board = new Board(state);
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
                            ((MainActivity)context).movePiece(view, Board.Direction.U);
                            return true;
                        }
                        public boolean onSwipeRight() {
                            Toast.makeText(context, "right", Toast.LENGTH_SHORT).show();
                            ((MainActivity)context).movePiece(view, Board.Direction.R);
                            return true;
                        }
                        public boolean onSwipeLeft() {
                            Toast.makeText(context, "left", Toast.LENGTH_SHORT).show();
                            ((MainActivity)context).movePiece(view, Board.Direction.L);
                            return true;
                        }
                        public boolean onSwipeBottom() {
                            Toast.makeText(context, "bottom", Toast.LENGTH_SHORT).show();
                            ((MainActivity)context).movePiece(view, Board.Direction.D);
                            return true;
                        }
                    });
                addView(b);
            }
        }
    }
