package com.yhj.app;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.app.Activity;
import android.app.Fragment;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;

public class MainActivity extends Activity {
	
	private DrawerLayout mDrawerLayout = null;
	private ListView mDrawerList = null;
	
	private ImageView mAdd = null;
	
	private ActionBarDrawerToggle mDrawerToggle = null;
	
	private ListView mListView = null;
	
	private MyListAdapter listAdapter = null;
	
	//标题
	private EditText mTitleEdit = null;
	
	private SQLiteOpenHelper helper = null;
	
	private TitleAdapter titleAdapter = null;
	
	private PMenu p = null;
	
	//定义一个置位符号
	//这个符号用于判断用户是点击标题进入记事本添加页面
	//还是通过点击添加按钮进入添加页面
	//默认为false，即是通过点击添加进入记事本页面
	private boolean isShow = false;
	
	//点击的标题的笔记的内容
	private Map<Integer,String> content = new LinkedHashMap<Integer, String>();
	
	//添加日记handler
	private Handler addHandler = null;
	
	public static final int ADD = 0x1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		this.mDrawerLayout = (DrawerLayout) this.findViewById(R.id.drawer_layout);
		this.mDrawerList = (ListView) this.findViewById(R.id.note_list);
		
		
		
		final View noteView = getLayoutInflater().inflate(R.layout.note, null);
		mListView = (ListView) noteView.findViewById(R.id.listView);
		mTitleEdit = (EditText) noteView.findViewById(R.id.note_title);
		
		this.addHandler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				if (msg.what == ADD) {
					LayoutInflater inflater = MainActivity.this.getLayoutInflater();
					
					mListView.setAdapter(listAdapter);
					
					Fragment f = new AddFragment(noteView);
					
					getFragmentManager().beginTransaction().replace(R.id.note_content, f).commit();
					
					if (p.isShowing()) p.dismiss();
				}
			}
			
		};
		
		List<String> items = new ArrayList<String>();
		items.add("添加");
		items.add("保存");
		items.add("退出");
		
		MenuAdapter adapter = new MenuAdapter(this,items,addHandler);
		
		p = new PMenu(this,adapter);
		
		listAdapter = new MyListAdapter(MainActivity.this,p,content);
		
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_launcher, R.string.app_name, R.string.app_name){

			@Override
			public void onDrawerClosed(View drawerView) {
				// TODO Auto-generated method stub
				super.onDrawerClosed(drawerView);
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				if (p.isShowing()) {
					p.dismiss();
				}
				super.onDrawerOpened(drawerView);
			}
		};
		
		this.mDrawerLayout.setDrawerListener(mDrawerToggle);
		
		helper = new MySQLiteOpenHelper(this, "notes", null, 2, null);
		SQLiteDatabase db = helper.getReadableDatabase();
		
		List<Note> notes = new ArrayList<Note>();

		Cursor c = db.query("note", new String[] {"id","title","content"}, null, null, null, null, null);
		while (c.moveToNext()) {
			Note n = new Note();
			n.setId(c.getInt(0));
			n.setTitle(c.getString(1));
			n.setContent(c.getString(2));
			notes.add(n);
		}
		
		titleAdapter = new TitleAdapter(this, notes);
		this.mDrawerList.setAdapter(titleAdapter);
		
		this.mAdd = (ImageView) this.findViewById(R.id.add);
		mAdd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Message msg = new Message();
				msg.what = ADD;
				addHandler.sendMessage(msg);
			}
		});
		
		mTitleEdit.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (p.isShowing()) p.dismiss();
				return false;
			}
		});
		
		//监听触屏事件
		FrameLayout fl = (FrameLayout) this.findViewById(R.id.note_content);
		
		fl.setFocusable(true);
		fl.setFocusableInTouchMode(true);
		fl.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (p.isShowing()) p.dismiss();
				return false;
			}
		});
		
		//抽屉标题点击事件监听
		this.mDrawerList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				mDrawerLayout.closeDrawer(Gravity.START);
				
				Note n = (Note) titleAdapter.getItem(position);
				Log.i("Note",n.getId() + "");
				
				mTitleEdit.setText(n.getTitle());
				mTitleEdit.setTag(n.getId());
				
				Fragment f = new AddFragment(noteView);
				
				getFragmentManager().beginTransaction().replace(R.id.note_content, f).commit();
				
				String cc = n.getContent();
				
				Log.i("cc",cc);
				
				String[] s = null;
				
				if (cc != null) {
					if (cc.contains("@")) {
						s = cc.split("@");
						
						for (String ss : s) {
							String[] temp = ss.split("#");
							
							int pos = Integer.parseInt(temp[0]);
							
							if (temp.length >= 2) {
								content.put(pos, temp[1]);
							} else {
								content.put(pos,"");
							}
						}
						
					} else {
						if (cc.contains("#")) {
							String[] temp = cc.split("#");
							
							int pos = Integer.parseInt(temp[0]);
							
							if (temp.length >= 2) {
								content.put(pos, temp[1]);
							} else {
								content.put(pos,"");
							}
						}
					}
				}
			
				listAdapter = new MyListAdapter(MainActivity.this, p, content);
				mListView.setAdapter(listAdapter);
				
				
			}
		});
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


	@Override
	public boolean onMenuOpened(int featureId, Menu menu) {
		Log.i("menu","onMenuOpened");
		p.showAtLocation(mDrawerLayout, Gravity.LEFT | Gravity.BOTTOM, 0, 0);
		return false;
	}



	@Override
	protected void onPause() {
		if (mListView != null && mTitleEdit != null) {
			
			//判断是更新操作还是插入操作
			//通过mTitleEdit中的id值进行判断
			//如果id为空，则判断为插入操作，否则为 更新操作
			Integer id = (Integer) mTitleEdit.getTag();
			Map<Integer,String> map = listAdapter.mValue;
			StringBuilder builder = new StringBuilder("");
			
			Set keySet = map.keySet();
			Iterator<Integer> it = keySet.iterator();
			while (it.hasNext()) {
				Integer key = it.next();
				String value = map.get(key);
				builder.append(key)
				       .append("#")
				       .append(value)
				       .append("@");
			}
			builder.deleteCharAt(builder.length()-1);
			
			String title = mTitleEdit.getText().toString();
			
			
			ContentValues values = new ContentValues();
			values.put("title", title);
			values.put("content", builder.toString());
			SQLiteDatabase db = helper.getWritableDatabase();
			
			if (id == null) {
				db.insert("note", null, values);
				Log.i("input",builder.toString());
			} else {
				db.update("note", values, "id=?", new String[] {String.valueOf(id)});
			}
		}
		super.onPause();
	}
}
