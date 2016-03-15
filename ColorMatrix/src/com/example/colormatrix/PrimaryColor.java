package com.example.colormatrix;

import com.example.colormatrix.Utils.ImageUtils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class PrimaryColor extends Activity implements OnSeekBarChangeListener {
    private ImageView mImageView;
    private SeekBar mSeekbarhue,mSeekbarSaturation, mSeekbarLum;
    private static int MAX_VALUE = 255;
    private static int MID_VALUE = 127;
    private float mHue,mStauration, mLum;
    private Bitmap bitmap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.primary_color);
		 bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test3);
	        mImageView = (ImageView) findViewById(R.id.imageview);
	        mSeekbarhue = (SeekBar) findViewById(R.id.seekbarHue);
	        mSeekbarSaturation = (SeekBar) findViewById(R.id.seekbarSaturation);
	        mSeekbarLum = (SeekBar) findViewById(R.id.seekbatLum);
	        mSeekbarhue.setOnSeekBarChangeListener(this);
	        mSeekbarSaturation.setOnSeekBarChangeListener(this);
	        mSeekbarLum.setOnSeekBarChangeListener(this);
	        /**
	         * 设置进度最大值
	         */
	        mSeekbarhue.setMax(MAX_VALUE);
	        mSeekbarSaturation.setMax(MAX_VALUE);
	        mSeekbarLum.setMax(MAX_VALUE);
	        /**
	         * 设置进度最小值，和默认图片
	         */
	        mSeekbarhue.setProgress(MID_VALUE);
	        mSeekbarSaturation.setProgress(MID_VALUE);
	        mSeekbarLum.setProgress(MID_VALUE);
	        mImageView.setImageBitmap(bitmap);
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		switch (seekBar.getId()) {
		  /**
		   * 为三个变量赋值获取progress拖动的值
		   */
		  case R.id.seekbarHue:
			  mHue = (progress - MID_VALUE) * 1.0F / MID_VALUE * 180;
			  break;
		  case R.id.seekbarSaturation:
			  mStauration = progress * 1.0F / MID_VALUE;
			  break;
          case R.id.seekbatLum:
        	  mLum = progress * 1.0F / MID_VALUE;
        	  break;
		}
		mImageView.setImageBitmap(ImageUtils.handlerImage(bitmap, mHue, mStauration, mLum));
	}

	@Override
	public void onStartTrackingTouch(SeekBar arg0) {
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar arg0) {
		
	}
}
