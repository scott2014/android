package com.yhj.app;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Message;
import android.util.Log;
import android.view.View;

public class TetrisView extends View implements Runnable {
	final static int SCREEN_WIDTH = 320;
	final static int SCREEN_HEIGHT = 455;
	
	private Context mContext = null;
	
	boolean mIsCombo = false; //
	
	long mMoveDelay = 600;
	long mLastMove  = 0;
	
	RefreshHandler mRefreshHandler = null;
	
	//RefreshHandler mRefreshHandler = null;
	TileView mCurrentTile = null;
	TileView mNextTile = null;
	Court mCourt = null;
	ResourceStore mResourceStore = null;
	
	public TetrisView(Context context) {
		super(context);
		this.mContext = context;
		
		init();
	}
	
	public void init() {
		this.mResourceStore = new ResourceStore(mContext);
		
		this.mRefreshHandler = new RefreshHandler(this);
		
		this.mCurrentTile = new TileView(mContext);
		
		this.mNextTile = new TileView(mContext);
		
		this.mCourt = new Court(mContext);
			
		new Thread(this).start();
	}

	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			Message msg = new Message();
			msg.what = RefreshHandler.REFRESH_VIEW;
			this.mRefreshHandler.sendMessage(msg);
			try {
				Thread.sleep(mMoveDelay);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}
	
	public void play() {
		long now = System.currentTimeMillis();
		if(now - mLastMove > mMoveDelay)
		{
			/*if(mIsPaused)
			{
				return;
			}*/
			if(mIsCombo)
			{
				mCourt.placeTile(mCurrentTile);
				//////
			//	mMPlayer.playMoveVoice();
				
				/*if(mCourt.isGameOver() )
				{
					mGamestate = STATE_OVER;
					return;
				}
				int line = mCourt.removeLines();
				if(line > 0 )
				{
					mMPlayer.playBombVoice();
				}
				mDeLine += line;
				countScore(line);*/
			
				mCurrentTile = mNextTile;
				mNextTile = new TileView(mContext);
				
				mIsCombo = false;
			}
			moveDown();
			
			mLastMove = now;
	}
	}
	
	//mIsCombo标记是否可以继续往下移动
	private void moveDown()
	{
		if(!mIsCombo)
		{
			if( ! mCurrentTile.moveDownOnCourt(mCourt) )
				mIsCombo = true;
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		Log.i("mCourt", mCourt + "");
		Log.i("canvas",canvas + "");
		
		this.mCourt.paintCourt(canvas);
		this.mCurrentTile.paintTile(canvas);
	}
}
