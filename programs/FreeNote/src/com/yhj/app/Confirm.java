package com.yhj.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;

public class Confirm extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.confirm);
		
		Button okBtn = (Button) this.findViewById(R.id.okBtn);
		Button cancelBtn = (Button) this.findViewById(R.id.cancelBtn);
		
		final Bundle  data = getIntent().getBundleExtra("data");
		
		
		final int id = data.getInt("id");
		final String title = data.getString("title");
		
//		final TitleAdapter adapter = (TitleAdapter) data.getSerializable("titleAdapter");
		
		TextView textView = (TextView) this.findViewById(R.id.tip);
		textView.setText("日记" + title + "将被删除，删除后将不能被恢复");
		
		okBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				/*MySQLiteOpenHelper helper = new MySQLiteOpenHelper(Confirm.this, "notes", null,2,null);
				
				SQLiteDatabase db = helper.getWritableDatabase();
				db.delete("note", "id=?", new String[] {String.valueOf(id)});
				adapter.notifyDataSetChanged();*/
				Intent intent = new Intent();
				intent.putExtra("flag", true);
				intent.putExtra("id", id);
				setResult(0xeee,intent);
				finish();
			}
		});
		
		
		cancelBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("flag", false);
				intent.putExtra("id", id);
				setResult(0xeee,intent);
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
