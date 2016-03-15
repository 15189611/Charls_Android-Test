package com.example.colormatrix;

import com.example.colormatrix.Utils.ImageUtils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
/**
 * 通过rgba算法来进行  像美图秀秀样图片的改变      (通过设置像素点来计算宽*高)
 * @author charles
 *
 */
public class PixelsEffect extends Activity{
	  private ImageView imageView1,imageView2,imageView3,imageView4;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pixels_effect);
		  Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test2);
		    imageView1 = (ImageView) findViewById(R.id.imageview1);
	        imageView2 = (ImageView) findViewById(R.id.imageview2);
	        imageView3 = (ImageView) findViewById(R.id.imageview3);
	        imageView4 = (ImageView) findViewById(R.id.imageview4);
		    imageView1.setImageBitmap(bitmap);  //  原图
		    imageView2.setImageBitmap(ImageUtils.handleImageNegative(bitmap)); //修改1
		    imageView3.setImageBitmap(ImageUtils.handleImagePixelsRelief(bitmap));//修改2
		    imageView4.setImageBitmap(ImageUtils.handleImagePixelsOldPhoto(bitmap));//修改3
	}
}
