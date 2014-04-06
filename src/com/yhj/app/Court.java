package com.yhj.app;

import android.content.Context;

public class Court {
	
	public final static int COURT_WIDTH  = 11;
	public final static int COURT_HEIGHT = 23+4;
	
	public final static int BLOCK_WIDTH  = 20;
	public final static int BLOCK_HEIGHT = 20;
	
	public final static int ABOVE_VISIBLE_TOP = 4;
	
	public final static int BEGIN_DRAW_X = 0;
	public final static int BEGIN_DRAW_Y = TetrisView.SCREEN_HEIGHT - Court.BLOCK_WIDTH * Court.COURT_HEIGHT;

	
	private int[][] mCourt = new int[COURT_WIDTH][COURT_HEIGHT];
	private Context mContext = null;
	private ResourceStore mRs = null;
	
	public Court(Context context) {
		this.mContext = context;
	}
}
