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
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main); // use code ot set instead of xml
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

        //Adding views to FrameLayout
        String state[] = new String[]{
                "VKkV",
                "vkkv",
                "VHhV",
                "vPPv",
                "P  P"};
        Board board = new Board(state);
        BoardView bv = new BoardView(this, board);
        setContentView(bv);
    }
}
    class BlockView extends FrameLayout {
        BlockView(Context context, Block block, int boardW, int boardH) {
            super(context);
            label = new TextView(getContext());
            label.setTextSize(32);
            label.setBackgroundColor(0xffbc4d05);
            label.setTextColor(0x330D0D0D);
            label.setGravity(Gravity.CENTER);
            //LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

            int W = 300;
            int H = 300;
            int G = 10;
            LayoutParams lp = new LayoutParams(W * block.W() - 2*G, H * block.H()-2*G);
            int x1 = block.x * W + G;
            int x2 = (block.x + block.W()) * W - G;
            x2 = boardW * W - x2;
            int y1 = block.y * H + G;
            int y2 = (block.y + block.H()) * H - G;
            y2 = boardH * H - y2;
            lp.setMargins(x1, y1, x2, y2);
            label.setLayoutParams(lp);
            label.setText(block.t.toString());
            addView(label);
        }
        TextView label;
    }

    class BoardView extends FrameLayout {
        BoardView(Context context, Board board) {
            super(context);
            ;
            setLayoutParams(new AbsListView.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT));
            for (int i = 0; i < board.blocks.length; ++i) {
                BlockView b = new BlockView(context, board.blocks[i], board.W, board.H);
                addView(b);
            }
        }
    }
