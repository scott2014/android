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
	

	//判断此处是否为空
	public boolean isSpace(int posX,int posY)
	{
		if (posX < 0 || posX >= COURT_WIDTH) 
			return false;
		if (posY < 0 || posY >= COURT_HEIGHT) 
			return false;
		if(0 == mCourt[posX][posY])
			return true;
		return false;
	}

	public void placeTile(TileView tile)
	{
		int i,j;
		for (i = 0; i < 4; i++)
		{
			for (j = 0; j < 4; j++) 
			{
				if (tile.mTile[i][j] != 0)
				{
					mCourt[tile.getmOffsetX() + i][tile.getmOffsetY() + j] = tile.getmColor();
				}
			}
		}
	}
}
