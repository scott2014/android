package com.yhj.app;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class TitleAdapter extends BaseAdapter {
	
	private List<Note> mNotes = null;
	
	private Context mContext = null;

	public TitleAdapter(Context context,List<Note> notes) {
		this.mContext = context;
		this.mNotes = notes;
	}
	@Override
	public int getCount() {
		return mNotes.size();
	}

	@Override
	public Object getItem(int position) {
		return mNotes.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("NewApi")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView textView = null;
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(mContext);
			View view = inflater.inflate(R.layout.drawer_list_item, null);
			textView = (TextView) view.findViewById(R.id.textView);
		} else {
			textView = (TextView) convertView;
		}
/*		textView.setLayoutParams(new GridView.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		textView.setTextAppearance(mContext, android.R.attr.textAppearanceListItemSmall);
		textView.setGravity(Gravity.CENTER_VERTICAL);
		textView.setPadding(16, 0, 16, 0);
		textView.setTextColor(Color.WHITE);
		textView.setBackground(this.mContext.getResources().getDrawable(android.R.attr.activatedBackgroundIndicator));
		//textView.setBackgroundDrawable(android.R.attr.activatedBackgroundIndicator);
		//textView.setMinHeight(android.R.attr.listPreferredItemHeightSmall);
*/		textView.setText(this.mNotes.get(position).getTitle());

		textView.setTag(this.mNotes.get(position).getId());
		return textView;
	}

}
