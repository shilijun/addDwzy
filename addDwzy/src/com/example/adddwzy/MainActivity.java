package com.example.adddwzy;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	private GetHttpThread getHttpThread;
	String url =  "";
	public static String result = "";
	EditText name ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		name = (EditText)findViewById(R.id.name);
		Button insert = (Button)findViewById(R.id.insert);
		Button query  = (Button)findViewById(R.id.query);
		insert.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				getHttpThread = new GetHttpThread();
				getHttpThread.getName(getCode(name.getText().toString()));
				getHttpThread.start();
			}
		});
		
		query.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, com.example.adddwzy.QueryActivity.class);
			startActivity(intent);
			}
		});
		
	   
		
	}
	
	//×Ö·û×ª16 ½øÖÆ
	public String getCode(String title) {
		   String str="";
		   try {
		       byte[] b = title.getBytes();
		       for (int i = 0; i < b.length; i++) {
		       Integer I = new Integer(b[i]);
		       String strTmp = I.toHexString(b[i]);
		       if (strTmp.length() > 2)
		       strTmp = strTmp.substring(strTmp.length() - 2);
		       str = str + strTmp;
		       }
		   }catch (Exception e){
		   e.printStackTrace();
		   }
		 return str.toUpperCase();
		 }
	
}
