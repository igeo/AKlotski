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
        lp1.setMargins(0,20,0,0);
        textView1.setLayoutParams(lp1);

//Initializing frame layout

        FrameLayout framelayout = new FrameLayout(this);
        framelayout.setLayoutParams(new AbsListView.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));
/*
        GridLayout gridlayout = new GridLayout(this);
        gridlayout.setColumnCount(4);
        gridlayout.setRowCount(4);
        GridLayout.LayoutParams lp2 = new GridLayout.LayoutParams();
        gridlayout.setLayoutParams(new AbsListView.LayoutParams(GridLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));
        for(int i = 0; i < 8; ++i) {
            PieceLayout piece = new PieceLayout(this);
            piece.setNum(i);
            gridlayout.addView(piece);
        }
*/
//Adding views to FrameLayout

        for(int x = 0; x < 4; ++x)
            for(int y = 0; y < 5; ++y)
            {
                PieceLayout piece = new PieceLayout(this, x, y);
                piece.setNum(x*10+y);
                //framelayout.addView(imageView);
                //framelayout.addView(textView1);
                framelayout.addView(piece);
            }
        setContentView(framelayout);
    }


}

class PieceLayout extends FrameLayout {
        PieceLayout(Context context, int x, int y)
        {
            super(context);
            label = new TextView(getContext());
            label.setTextSize(32);
            label.setBackgroundColor(0x33ff0033);
            label.setTextColor(0x330D0D0D);
            label.setGravity(Gravity.CENTER);
            //LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

            int W = 300;
            int H = 300;
            int G = 10;
            LayoutParams lp = new LayoutParams(W, H);
            lp.setMargins(x*(W+G), y*(H+G), (3-x)*(W+G), (4-y)*(H+G));
            label.setLayoutParams(lp);
            setNum(0);
            addView(label);
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
        if (num <= 0) {
            label.setText("");
        } else {
            label.setText(num + "");
        }
    }
    int num;
    TextView label;
}