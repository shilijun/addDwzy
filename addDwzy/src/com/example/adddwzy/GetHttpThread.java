package com.example.adddwzy;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.app.AlertDialog;
import android.content.DialogInterface;

public final class GetHttpThread extends Thread{
	 String name = "";   //url parameter
	 AlertDialog.Builder dialog = new AlertDialog.Builder(null);
	 
	 public void getName(String name){
		 this.name = name;
	 }
	 
		  @Override
		  public void run() {
				//�½�URL       
			    String url = "http://10.10.10.211:8090/DataSh/androidConnectSql.jsp?name="+name;
			    System.out.println("Url is" + url);
		    	//�½�POST���͵�����
		    	HttpPost httpRequest = new HttpPost(url);
		    	//�½���Ҫ���ݲ��������ݽṹ
		    	List<NameValuePair> params = new ArrayList<NameValuePair>();
		    	//�½���ֵ��
		    	BasicNameValuePair pair1 = new BasicNameValuePair("param", "AaBbCcDdEe");
		    	//��������ӵ���ֵ����
		    	params.add(pair1);
		    	try{  
		    		System.out.println("�����Ѿ�����");
		    		//���ñ��뷽ʽ
		    		HttpEntity entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
					httpRequest.setEntity(entity);
					//�½�HTTP�ͻ���
					HttpClient httpClient = new DefaultHttpClient();
					//ִ������õ���Ӧ
					HttpResponse httpResponse = httpClient.execute(httpRequest);
					//�ж���Ӧ��״̬�Ƿ�ɹ�
					if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
						//�õ�����ַ���
						dialog.setTitle("message");
						MainActivity.result = EntityUtils.toString(httpResponse.getEntity());
						if(MainActivity.result.split("&")[0].equals("true")){
							dialog.setMessage("success");
						}else{
							dialog.setMessage("false");
						}
						dialog.setNegativeButton("confrim", new DialogInterface.OnClickListener(){
							@Override
							public void onClick(DialogInterface dialog, int which){
								System.exit(0);
							}
						});
					 }else{
						System.out.println("Ӧ�����:"+httpResponse.getStatusLine().toString());
					 }
				}
				catch (UnsupportedEncodingException e){
					//�ַ������벻֧���򲶻���쳣
					e.printStackTrace();
				}
				catch (ClientProtocolException e){
					//�ͻ���Э���쳣
					e.printStackTrace();
				}
				catch (IOException e){
					// IO�쳣��ҿ϶���İ����
					e.printStackTrace();
				}
		  }
}
