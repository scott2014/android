package com.yhj.app;

import java.util.Random;

import android.content.Context;
import android.util.Log;

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
	
	//判断是否移动到底,同时往下移动
	public boolean moveDownOnCourt(Court court) {
		int i,j;
		for (i = 0; i < 4; i++) {
			for (j = 0; j < 4; j++) {
				if (mTile[i][j] != 0) {
					if (!court.isSpace(mOffsetX + i, mOffsetY + j + 1)
							|| isUnderBaseline(mOffsetY+j+1) ) {
						Log.i("it's over","已经到头了!!!");
						return false;
					}
				}
			}
		}
		mOffsetY++;
		return true;
	}

	private boolean isUnderBaseline(int posY )
	{
		if(posY >= Court.COURT_HEIGHT)
			return true;
		return false;
	}
	
	public int getmColor() {
		return mColor;
	}

	public void setmColor(int mColor) {
		this.mColor = mColor;
	}

	public int getmOffsetX() {
		return mOffsetX;
	}

	public void setmOffsetX(int mOffsetX) {
		this.mOffsetX = mOffsetX;
	}

	public int getmOffsetY() {
		return mOffsetY;
	}

	public void setmOffsetY(int mOffsetY) {
		this.mOffsetY = mOffsetY;
	}
}
