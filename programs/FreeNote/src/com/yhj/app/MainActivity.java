package com.yhj.app;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.app.Activity;
import android.app.Fragment;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		this.mDrawerLayout = (DrawerLayout) this.findViewById(R.id.drawer_layout);
		this.mDrawerList = (ListView) this.findViewById(R.id.note_title);
		
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_launcher, R.string.app_name, R.string.app_name){

			@Override
			public void onDrawerClosed(View drawerView) {
				// TODO Auto-generated method stub
				super.onDrawerClosed(drawerView);
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				// TODO Auto-generated method stub
				super.onDrawerOpened(drawerView);
			}
		};
		
		this.mDrawerLayout.setDrawerListener(mDrawerToggle);
		
		List<Note> notes = new ArrayList<Note>();
		Note n1 = new Note();
		n1.setTitle("标题一");
		Note n2 = new Note();
		n2.setTitle("标题二");
		Note n3 = new Note();
		n3.setTitle("标题三");
		Note n4 = new Note();
		n4.setTitle("标题四");
		notes.add(n1);
		notes.add(n2);
		notes.add(n3);
		notes.add(n4);
		
		
		BaseAdapter adapter = new TitleAdapter(this, notes);
		this.mDrawerList.setAdapter(adapter);
		
		this.mAdd = (ImageView) this.findViewById(R.id.add);
		mAdd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				LayoutInflater inflater = MainActivity.this.getLayoutInflater();
				
				View rootView = inflater.inflate(R.layout.note, null);
				mListView = (ListView) rootView.findViewById(R.id.listView);
				mTitleEdit = (EditText) rootView.findViewById(R.id.note_title);
				
				listAdapter = new MyListAdapter(MainActivity.this);
				mListView.setAdapter(listAdapter);
				
				Fragment f = new AddFragment(rootView);
				getFragmentManager().beginTransaction().replace(R.id.note_content, f).commit();
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
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
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
			
			SQLiteDatabase db = new MySQLiteOpenHelper(this, "notes", null, 2, null).getWritableDatabase();
			
			ContentValues values = new ContentValues();
			values.put("title", title);
			values.put("content", builder.toString());
			db.insert("note", null, values);
			Log.i("input",builder.toString());
			this.finish();
		}
		return true;
	}



	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
}
