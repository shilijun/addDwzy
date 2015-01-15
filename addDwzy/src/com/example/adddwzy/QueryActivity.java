package com.example.adddwzy;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class QueryActivity extends Activity {
	private GetHttpThread getHttpThread;
	String result = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_query);
		TableLayout layout = new TableLayout(this);//����һ����񲼾�
		ScrollView scroll  = new ScrollView(this);
		layout.addView(scroll);
		super.setContentView(layout);//���ø�activity ��ʾ�����layout
		layout.setOrientation(TableLayout.VERTICAL);//���ô�ֱ��ʾ
		layout.setStretchAllColumns(true);//����������
		String[] rowVal =MainActivity.result.split("&")[1].split(",");
		for(int i=0;i<rowVal.length;i++){
			System.out.println(2);
			TableRow row = new TableRow(this);                                                                                                                                          
			
			final TextView xh = new TextView(this);
			final TextView mc = new TextView(this);
			xh.setGravity(Gravity.CENTER);		
		    xh.setText(rowVal[i].split("\\|")[0]);
		    System.out.println("3"+rowVal[i].split("\\|")[0]);
			xh.setHeight(40);
			
			mc.setGravity(Gravity.CENTER);
			mc.setText(rowVal[i].split("\\|")[1]);
			mc.setHeight(40);
			
			if(i == rowVal.length -1){
				break;
			}
			row.addView(xh);
			row.addView(mc);
			layout.addView(row);
		}
	}
}
