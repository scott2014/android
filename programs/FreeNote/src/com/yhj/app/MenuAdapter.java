package com.yhj.app;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MenuAdapter extends BaseAdapter {
	
	private Context mContext = null;
	private List<String> mItems = null;
	
	private Handler mHandler = null;
	
	public MenuAdapter(Context context,List<String> items,Handler handler) {
		this.mContext = context;
		this.mItems = items;
		this.mHandler = handler;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.mItems.size();
	}

	@Override
	public Object getItem(int position) {
		return this.mItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		LinearLayout layout = null;
		TextView textView = null;
		
		if (convertView == null) {
			convertView = new LinearLayout(mContext);
			textView = new TextView(mContext);
			
			convertView.setTag(textView);
		} else {
			textView = (TextView) convertView.getTag();
		}
		
		layout = (LinearLayout) convertView;
		
		layout.removeView(textView);
		
		textView.setLayoutParams(new GridView.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		textView.setText(mItems.get(position));
		textView.setTextColor(Color.BLACK);
		textView.setGravity(Gravity.CENTER);
		textView.setTextSize(18);
		
		layout.setLayoutParams(new ListView.LayoutParams(LayoutParams.MATCH_PARENT,100));
		layout.setBackgroundColor(0x3aadf0);
		layout.addView(textView);
		
		layout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (position == 0) {
					Message msg = new Message();
					msg.what = MainActivity.ADD;
					mHandler.sendMessage(msg);
				}
			}
		});
		
		return layout;
	}

}
