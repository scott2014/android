package com.yhj.app;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup.LayoutParams;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class PMenu extends PopupWindow {
	
	private Context mContext = null;
	
	public PMenu(Context context) {
		this.mContext = context;
		
		LinearLayout layout = new LinearLayout(context);
		layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		layout.setOrientation(LinearLayout.HORIZONTAL);
		layout.setBackgroundColor(Color.BLACK);
		
		GridView gridView = new GridView(context);
		gridView.setFocusable(true);
		gridView.setFocusableInTouchMode(true);
		gridView.setChoiceMode(GridView.CHOICE_MODE_SINGLE);
		gridView.setNumColumns(3);
		
		
		List<String> items = new ArrayList<String>();
		items.add("Ìí¼Ó");
		items.add("±£´æ");
		items.add("ÍË³ö");
		
		MenuAdapter adapter = new MenuAdapter(mContext,items);
		gridView.setAdapter(adapter);
		
		layout.addView(gridView);
		
		setContentView(layout);
		
		this.setWidth(LayoutParams.MATCH_PARENT);
		this.setHeight(LayoutParams.WRAP_CONTENT);
	}
}
