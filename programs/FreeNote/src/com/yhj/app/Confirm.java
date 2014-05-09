package com.yhj.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;

public class Confirm extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.confirm);
		
		Button okBtn = (Button) this.findViewById(R.id.okBtn);
		Button cancelBtn = (Button) this.findViewById(R.id.cancelBtn);
		
		okBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			}
		});
		
		
		cancelBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
	/*	btnColorListener listener = new btnColorListener();
		okBtn.setOnTouchListener(listener);
		cancelBtn.setOnTouchListener(listener);*/
	
	}
	
	class btnColorListener implements OnTouchListener {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			v.setBackgroundColor(0x0099cc);
			return false;
		}
		
	}
}
