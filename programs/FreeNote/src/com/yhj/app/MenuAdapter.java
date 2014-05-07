package com.yhj.app;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
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
	
	public MenuAdapter(Context context,List<String> items) {
		this.mContext = context;
		this.mItems = items;
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
	public View getView(int position, View convertView, ViewGroup parent) {
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
		textView.setTextSize(16);
		
		layout.setLayoutParams(new ListView.LayoutParams(LayoutParams.MATCH_PARENT,100));
		layout.setBackgroundColor(0x3aadf0);
		layout.addView(textView);
		
		return layout;
	}

}
