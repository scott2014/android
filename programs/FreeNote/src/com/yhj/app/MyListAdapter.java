package com.yhj.app;

import java.util.LinkedHashMap;
import java.util.Map;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

public class MyListAdapter extends BaseAdapter {
	
	
	private Context mContext = null;
	
	//存储用户在EditText输入的值
	//存储的key值
	private final String KEY = "input";
//	private Map<String,String> mValue = new HashMap<String,String>();
	public Map<Integer,String> mValue = new LinkedHashMap<Integer, String>();
	
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
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		EditText editText = null;
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(mContext);
			View rootView = inflater.inflate(R.layout.content_list_item, null);
			editText = (EditText) rootView.findViewById(R.id.editText);
		} else {
			editText = (EditText)convertView;
		}
		
	
		editText.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				mValue.put(position, s.toString());
			}
		});
		
		return editText;
	}

}
