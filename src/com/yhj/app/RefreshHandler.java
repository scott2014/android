package com.yhj.app;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

public class RefreshHandler extends Handler {
	
	private Context mContext = null;
	
	private TetrisView mTetrisView = null;
	
	public static final int REFRESH_VIEW = 0xee;
	
	public RefreshHandler(TetrisView tetrisView) {
		this.mTetrisView = tetrisView;
	}
	
	
	@Override
	public void handleMessage(Message msg) {
		if (msg.what == REFRESH_VIEW) {
			mTetrisView.invalidate();
		}
	}
}
