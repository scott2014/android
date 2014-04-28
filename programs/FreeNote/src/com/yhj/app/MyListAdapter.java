package com.yhj.app;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

public class MyListAdapter extends BaseAdapter {
	
	
	private Context mContext = null;
	
	public MyListAdapter(Context context) {
		this.mContext = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 10;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		EditText text = null;
		if (convertView == null) {
			text = new EditText(mContext);
		} else {
			text = (EditText)convertView;
		}
		return text;
	}

}
