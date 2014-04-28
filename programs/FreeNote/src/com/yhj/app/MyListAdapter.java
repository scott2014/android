package com.yhj.app;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
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
			LayoutInflater inflater = LayoutInflater.from(mContext);
			View rootView = inflater.inflate(R.layout.content_list_item, null);
			text = (EditText) rootView.findViewById(R.id.editText);
		} else {
			text = (EditText)convertView;
		}
		return text;
	}

}
