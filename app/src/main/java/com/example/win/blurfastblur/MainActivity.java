package com.example.win.blurfastblur;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import android.view.View;


import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    ImageView iv;
    Button btn;
    boolean flag = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.img1);
        btn= (Button) findViewById(R.id.btn);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bmp = BitmapFactory.decodeResource(getResources(),R.drawable.beauty  );
                if(flag){
                    flag = false;
                    blur(bmp, iv);
                }else{
                    flag = true;
                    iv.setImageBitmap(bmp);
                }

            }
        });
    }

    private void blur(Bitmap bkg, ImageView view) {
        WindowManager wm1 = this.getWindowManager();
        int width1 = wm1.getDefaultDisplay().getWidth();
        int height1 = wm1.getDefaultDisplay().getHeight();
        long startMs = System.currentTimeMillis();
        int scaleFactor = 5;
        int radius = 8;
        Bitmap bmpblur = Bitmap.createScaledBitmap(bkg, bkg.getWidth() / scaleFactor, bkg.getHeight() / scaleFactor, false);
        Bitmap blurBitmap = FastBlur.doBlur(bmpblur, radius, true);
        view.setLayoutParams(new LinearLayout.LayoutParams(width1,height1));
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.setImageBitmap(blurBitmap);
        System.out.println(System.currentTimeMillis() - startMs + "ms");
    }


}
