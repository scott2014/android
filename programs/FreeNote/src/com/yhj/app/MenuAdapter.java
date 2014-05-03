package com.yhj.app;

import java.util.List;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
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
		
		if (convertView == null) {
			layout = new LinearLayout(mContext);
		} else {
			layout = (LinearLayout)convertView;
		}
		
		layout.setLayoutParams(new GridView.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setGravity(Gravity.CENTER);
		
		TextView item = new TextView(mContext);
		item.setLayoutParams(new GridView.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
		item.setText(mItems.get(position));
		
		layout.addView(item);
		
		return layout;
	}

}
