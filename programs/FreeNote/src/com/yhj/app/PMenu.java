package com.yhj.app;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.view.ViewGroup.LayoutParams;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class PMenu extends PopupWindow {
	
	private Context mContext = null;
	
	public PMenu(Context context,MenuAdapter adapter) {
		this.mContext = context;
		
		LinearLayout layout = new LinearLayout(context);
		layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		layout.setOrientation(LinearLayout.HORIZONTAL);
		
		GridView gridView = new GridView(context);
		gridView.setFocusable(true);
		gridView.setFocusableInTouchMode(true);
		gridView.setChoiceMode(GridView.CHOICE_MODE_SINGLE);
		gridView.setNumColumns(3);
		
		gridView.setAdapter(adapter);
		
		layout.addView(gridView);
		layout.setBackgroundColor(Color.CYAN);

		setContentView(layout);
		
		this.setWidth(LayoutParams.MATCH_PARENT);
		this.setHeight(LayoutParams.WRAP_CONTENT);
	}
}
