package com.yhj.app;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AddFragment extends Fragment {

	@Override
	public View getView() {
		return super.getView();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.note, null);
		return rootView;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}
	
}
