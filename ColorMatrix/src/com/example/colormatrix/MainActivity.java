package com.example.colormatrix;


import com.example.colormatrix.Utils.ImageUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tx = (TextView) findViewById(R.id.mytext);
			tx.setText(ImageUtils.s());
    }
    public void btnPixelsEffect(View view) {
        startActivity(new Intent(this, PixelsEffect.class));
    }

    public void btnColorMatrix(View view) {
        startActivity(new Intent(this, com.example.colormatrix.ColorMatrix.class));
    }

    public void btnPrimaryColor(View view) {
        startActivity(new Intent(this, PrimaryColor.class));
    }
    
}	
