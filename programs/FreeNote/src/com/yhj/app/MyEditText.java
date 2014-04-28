package com.yhj.app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.EditText;

public class MyEditText extends EditText {
	
	//������ɫ
	private int mLineColor;;
	private float mLineWidth;
	
	public MyEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		this.mLineColor = Color.BLUE;
		this.mLineWidth = 2f;
	}
	
	public MyEditText(Context context,int color,float width) {
		super(context);
		
		this.mLineColor = color;
		this.mLineWidth = width;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		Paint p = new Paint();
		p.setStrokeWidth(mLineWidth);
		p.setColor(mLineColor);
		p.setStyle(Paint.Style.FILL);
		
		
		int w = this.getWidth();
		int h = this.getHeight();
		
		int padB = this.getPaddingBottom();
		int padL = this.getPaddingLeft();
		int padR = this.getPaddingRight();
		
		float size = this.getTextSize() * 7 / 6;
		
		//��ȡ����
		int lines = (int) (h / size);
		
		//�������ϻ���
		for (int i=0;i<lines;i++) {
			canvas.drawLine(padL,  this.getHeight()-padB-size*i, this.getWidth()-padR,this.getHeight()-padB - size*i, p);
		}
	}
	
}
