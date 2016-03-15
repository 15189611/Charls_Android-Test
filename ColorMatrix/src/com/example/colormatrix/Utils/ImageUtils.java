package com.example.colormatrix.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.Log;

public class ImageUtils {
	/**
	 * 根据ColorMatrix 来设置图片的颜色变化
	 */
	public static Bitmap handlerImage(Bitmap bm, float hue,float saturation,float lum){
		//因为传进来的bitmap不能进行操作，所以创建一个新的s
		Bitmap bitmap=Bitmap.createBitmap(bm.getWidth(),bm.getHeight(),Bitmap.Config.ARGB_8888);
		Canvas canvas=new Canvas(bitmap);
		Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);//设置处锯齿
		 
		//1. 这是rgb  0:r 1:g 2:b      调节饱和度
		ColorMatrix hueMatrix=new ColorMatrix();
		hueMatrix.setRotate(0, hue);
		hueMatrix.setRotate(1, hue);
		hueMatrix.setRotate(2, hue);
		//2.调节色相
		ColorMatrix saturationMatrix=new ColorMatrix();
		saturationMatrix.setSaturation(saturation);
		//3. 这是 调节亮度
		ColorMatrix lumMatrix=new ColorMatrix();
		lumMatrix.setScale(lum, lum, lum, 1);
		
		//最后将它们放在一起组合起来
		ColorMatrix imgMatrix=new ColorMatrix();
		imgMatrix.postConcat(hueMatrix);
		imgMatrix.postConcat(saturationMatrix);
		imgMatrix.postConcat(lumMatrix);
		
	    //将它设置给画笔 设置颜色过滤器
		paint.setColorFilter(new ColorMatrixColorFilter(imgMatrix));
		
		canvas.drawBitmap(bm, 0, 0, paint);
		return bitmap;
	}
	
	//设置图片底色为Negative
    public static Bitmap handleImageNegative(Bitmap bm){
    	int width = bm.getWidth();
    	int height = bm.getHeight();
    	int color;
    	int r,g,b,a;
    	Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
    	
    	int[] oldpx=new int [width*height];
    	int[] newpx=new int [width*height];
    	bm.getPixels(oldpx, 0, width, 0, 0, width, height);
    	for (int i = 0; i < width * height; i++) {
			color=oldpx[i];
			r=Color.red(color);     //读取rgba数值
			g=Color.green(color);
			b=Color.blue(color);
			a=Color.alpha(color);
			//Log.i("Charles","r="+ r +", g= "+g +", b= " +b +", a= "+a);
			r=255-r;
			g=255-g;
			b=255-b;
			
			if(r>255){
				r=255;
			}else if(r<0){
				r=0;			newpx[i] = Color.argb(a, r, g, b); //将算好的argb 放到newpx里面去

			}
			if(g>255){
				g=255;
			}else if(g<0){
				g=0;
			}
			if(b>255){
				b=255;
			}else if(b<0){
				b=0;
			}
			newpx[i] = Color.argb(a, r, g, b); //将算好的argb 放到newpx里面去
			
		}
    	bitmap.setPixels(newpx, 0, width, 0, 0, width, height);
    	return bitmap;
    }

    public static Bitmap handleImagePixelsOldPhoto(Bitmap bm){
    	int width = bm.getWidth();
    	int height = bm.getHeight();
    	int color;
    	int r,g,b,a,r1,g1,b1;
    	Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
    	
    	int[] oldpx=new int [width*height];
    	int[] newpx=new int [width*height];
    	bm.getPixels(oldpx, 0, width, 0, 0, width, height);
    	for (int i = 0; i < width * height; i++) {
			color=oldpx[i];
			r=Color.red(color);     //读取rgba数值
			g=Color.green(color);
			b=Color.blue(color);
			a=Color.alpha(color);
			//Log.i("Charles","r="+ r +", g= "+g +", b= " +b +", a= "+a);

            r1 = (int) (0.393 * r + 0.769 * g + 0.189 * b);
            g1 = (int) (0.349 * r + 0.686 * g + 0.168 * b);
            b1 = (int) (0.272 * r + 0.534 * g + 0.131 * b);

			
			if(r1>255){
				r1=255;
			}
			if(g1>255){
				g1=255;
			}
			if(b1>255){
				b1=255;
			}
			newpx[i] = Color.argb(a, r1, g1, b1); //将算好的argb 放到newpx里面去
			
		}
    	bitmap.setPixels(newpx, 0, width, 0, 0, width, height);
    	return bitmap;
    }
	
    //设置图片底色为PixelsRelief       
    public static Bitmap handleImagePixelsRelief(Bitmap bm){
    	int width = bm.getWidth();
    	int height = bm.getHeight();
    	int color = 0,colorBefore = 0;
    	 int a, r, g, b;
         int r1, g1, b1;

    	Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
    	
    	int[] oldpx=new int [width*height];
    	int[] newpx=new int [width*height];
    	bm.getPixels(oldpx, 0, width, 0, 0, width, height);
    	for (int i = 1; i < width * height; i++) {
    	    colorBefore = oldpx[i - 1];
			r=Color.red(colorBefore);     //读取rgba数值
			g=Color.green(colorBefore);
			b=Color.blue(colorBefore);
			a=Color.alpha(colorBefore);
			//Log.i("Charles","r="+ r +", g= "+g +", b= " +b +", a= "+a);
			color = oldpx[i];
            r1 = Color.red(color);
            g1 = Color.green(color);
            b1 = Color.blue(color);

            r = (r - r1 + 127);
            g = (g - g1 + 127);
            b = (b - b1 + 127);
            if (r > 255) {
                r = 255;
            }
            if (g > 255) {
                g = 255;
            }
            if (b > 255) {
                b = 255;
            }
			newpx[i] = Color.argb(a, r, g, b); //将算好的argb 放到newpx里面去
			
		}
    	bitmap.setPixels(newpx, 0, width, 0, 0, width, height);
    	return bitmap;
    }
	@SuppressLint("SimpleDateFormat") public static String s() {
		String str="20150909102020";
		int year=Integer.valueOf(str.substring(0,4));
		int yue=Integer.valueOf(str.substring(4,6));
		int r  = Integer.valueOf(str.substring(6,8));
		int ss = Integer.valueOf(str.substring(8,10));
		int ff  = Integer.valueOf(str.substring(10,12));
		int mm = Integer.valueOf(str.substring(12,14));
		Log.i("Charles","sdata======"+"");
		
		@SuppressWarnings("deprecation")
		Date d=new Date(year-1900,yue-1,r,ss,ff,mm);
		SimpleDateFormat sin=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String s = sin.format(d);
		return s;
	}
	
}
