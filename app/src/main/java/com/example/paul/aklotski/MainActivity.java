package com.example.paul.aklotski;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.app.Activity;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


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
        //Adding views to FrameLayout
        String state[] = new String[]{
                "VKkV",
                "vkkv",
                "VHhV",
                "vPPv",
                "P  P"};
        board = new Board(state);
        BoardView bv = new BoardView(this, board);
        setContentView(bv);
    }

    @Override
    public void onClick(View v) {
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(v.getWidth(), v.getHeight());
        lp.setMargins(10, 10, 0, 0);
        //((TextView)v).setText("C");\
        board.movePiece(((BlockView)v).id);
        BoardView bv = new BoardView(this, board);
        setContentView(bv);
    }
    Board board;
}
    class BlockView extends TextView {
        BlockView(Context context, Block b, int index) {
            super(context);
            block = b;
            id = index;
            setTextSize(32);
            setBackgroundColor(0xffbc4d05);
            setTextColor(0x330D0D0D);
            setGravity(Gravity.CENTER);
            int W = 300;
            int H = 300;
            int G = 10;
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(W * block.W() - 2*G, H * block.H()-2*G);
            int x1 = block.x * W + G;
            int y1 = block.y * H + G;
            lp.setMargins(x1, y1, 0, 0);
            setLayoutParams(lp);
            setText(block.t.toString());
        }
        int id;
        Block block;
    }

    class BoardView extends FrameLayout {
        BoardView(Context context, Board board) {
            super(context);
            ;
            setLayoutParams(new AbsListView.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT));
            for (int i = 0; i < board.blocks.length; ++i) {
                BlockView b = new BlockView(context, board.blocks[i], i);
                b.setOnClickListener((OnClickListener) context);
                addView(b);
            }
        }
    }
