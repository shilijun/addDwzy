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
				//新建URL       
			    String url = "http://10.10.10.211:8090/DataSh/androidConnectSql.jsp?name="+name;
			    System.out.println("Url is" + url);
		    	//新建POST类型的请求
		    	HttpPost httpRequest = new HttpPost(url);
		    	//新建需要传递参数的数据结构
		    	List<NameValuePair> params = new ArrayList<NameValuePair>();
		    	//新建键值对
		    	BasicNameValuePair pair1 = new BasicNameValuePair("param", "AaBbCcDdEe");
		    	//将数据添加到键值对中
		    	params.add(pair1);
		    	try{  
		    		System.out.println("方法已经进入");
		    		//设置编码方式
		    		HttpEntity entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
					httpRequest.setEntity(entity);
					//新建HTTP客户端
					HttpClient httpClient = new DefaultHttpClient();
					//执行请求得到响应
					HttpResponse httpResponse = httpClient.execute(httpRequest);
					//判断响应的状态是否成功
					if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
						//得到结果字符串
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
						System.out.println("应答错误:"+httpResponse.getStatusLine().toString());
					 }
				}
				catch (UnsupportedEncodingException e){
					//字符集编码不支持则捕获此异常
					e.printStackTrace();
				}
				catch (ClientProtocolException e){
					//客户端协议异常
					e.printStackTrace();
				}
				catch (IOException e){
					// IO异常大家肯定不陌生了
					e.printStackTrace();
				}
		  }
}
