package com.yhj.app;

import java.util.Random;

import android.content.Context;

public class TileView {
	
	int[][] mTile = new int[4][4];
	int mColor = 1;
	int mShape = 0;
	int mOffsetX = (Court.COURT_WIDTH-4)/2+1;
	int mOffsetY = 0;
	
	private ResourceStore mRs = null;
	
	private Context mContext = null;
	
	public TileView(Context context) {
		mContext = context;
		
		this.mRs = new ResourceStore(context);
		
		init();
	}
	
	public void init() {
		Random r = new Random();
		this.mShape = r.nextInt(28);
		
		this.mColor = r.nextInt(9);
		
		for (int i=0;i<mTile.length;i++) {
			for (int j=0;j<mTile[i].length;j++) {
				mTile[i][j] = TileStore.store[mShape][i][j];
			}
		}
	}

	public int getmColor() {
		return mColor;
	}

	public void setmColor(int mColor) {
		this.mColor = mColor;
	}
}
