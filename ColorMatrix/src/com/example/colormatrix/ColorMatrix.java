package com.example.colormatrix;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
/**
 * 通过调节 颜色4x5      Matrix矩阵来 自动设置数值
 * @author charles
 *
 */
public class ColorMatrix extends Activity {


    private ImageView mImageView;
    private GridLayout mGroup;
    private Bitmap bitmap;
    private int mEtWidth, mEtHeight;
    private EditText[] mEts = new EditText[20];
    private float[] mColorMatrix = new float[20];    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.color_matrix);
		 bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test1);
		 mImageView=(ImageView) findViewById(R.id.imageview);
		 mGroup= (GridLayout) findViewById(R.id.group);
		 mImageView.setImageBitmap(bitmap);
		 //获取grid的加载完后的宽高
		 mGroup.post(new Runnable() {
			@Override
			public void run() {  //设置为4行5列
				  mEtWidth = mGroup.getWidth() / 5;
	              mEtHeight = mGroup.getHeight() / 4;
	              //动态添加20个editext
	              addEts();
	              //初始化Matrix的值
	              initMatrix();
			}
		});
	}
	//添加点击按钮的事件
	public void btnChange(View v){
		getMatrix();
		setImageMatrix();
	}
    public void btnReset(View v){
		initMatrix();  //获取开始的
		getMatrix();   //然后保存到mColorMatrix里面
		setImageMatrix(); //设置bitmap
	}
	
	
	//设置Matrix的值
    private void setImageMatrix(){
    	//创建一个新的bitmap
    	Bitmap btm=Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(), Bitmap.Config.ARGB_8888);
    	android.graphics.ColorMatrix colorMatrix=new android.graphics.ColorMatrix();
    	colorMatrix.set(mColorMatrix);//将调好的数值设置进去
    	Canvas canvas=new Canvas(btm);
    	Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);
    	paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
    	canvas.drawBitmap(bitmap, 0, 0, paint);
    	mImageView.setImageBitmap(btm);
    }
    
	//获取每个格子里面的值
    private void getMatrix(){
    	for (int i = 0; i < 20; i++) {  //转换成Float行装起来
    		mColorMatrix[i]=Float.valueOf(mEts[i].getText().toString());
		}
    }
	
	
	private void initMatrix() {
		for (int i = 0; i < 20; i++) {
			if(i%6==0){
				mEts[i].setText(String.valueOf(1));
			}else{
				mEts[i].setText(String.valueOf(0));
			}
		}
	}
	private void addEts() {
		for (int i = 0; i < 20; i++) {
			EditText et=new EditText(ColorMatrix.this);
			mEts[i]=et;//用集合记录每个et;
			mGroup.addView(et, mEtWidth, mEtHeight);
		}
	}
	
}
