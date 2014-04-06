package com.yhj.app;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

public class ResourceStore {
	private Bitmap mBackground = null;
	
	private Bitmap[] mBlocks = null;
	
	private Context mContext = null;
	
	public ResourceStore(Context context) {
		this.mContext = context;
		
		Resources r = context.getResources();
		
		if (mBackground == null) {
			this.mBackground = createImage(r.getDrawable(R.drawable.courtbg), Court.COURT_WIDTH * Court.BLOCK_WIDTH, Court.COURT_HEIGHT);
		}
		
		if (mBlocks == null) {
			mBlocks = new Bitmap[8];
			for (int i=0;i<8;i++) {
				mBlocks[i] = createImage(r.getDrawable(R.drawable.block0 + i), Court.BLOCK_WIDTH,Court.BLOCK_HEIGHT);
			}
		}
	}
	
	
	public Bitmap createImage(Drawable d,int w,int h) {
		Bitmap bitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		d.setBounds(0,0,w,h);
		d.draw(canvas);
		return bitmap;
	}
}
