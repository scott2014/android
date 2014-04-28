package com.yhj.app;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

@SuppressLint("ValidFragment")
public class AddFragment extends Fragment {
	
	private View rootView;
	
	public AddFragment(View rootView) {
		this.rootView = rootView;
	}
	
	@Override
	public View getView() {
		return super.getView();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
	//	View rootView = inflater.inflate(R.layout.note, null);
		return rootView;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}
	
}
