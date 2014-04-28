package com.yhj.app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.EditText;

public class MyEditText extends EditText {
	
	public MyEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public MyEditText(Context context,int color,float width) {
		super(context);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		Paint p = new Paint();
		p.setStrokeWidth(2f);
		p.setColor(Color.BLUE);
		
		canvas.drawLine(0, this.getHeight()-1, this.getWidth()-1, this.getHeight()-1, p);
	}
	
}
