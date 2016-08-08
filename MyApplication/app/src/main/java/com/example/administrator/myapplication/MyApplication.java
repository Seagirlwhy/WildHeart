package com.example.administrator.myapplication;

import android.annotation.SuppressLint;
import android.app.Application;


import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;

@SuppressLint("NewApi")
@SuppressWarnings("deprecation")
public class MyApplication extends Application {

	@Override
	public void onCreate() {

		//mImageLoader=ToolImage.init(getApplicationContext());
		BmobConfig bmobconfig =new BmobConfig.Builder()
				//请求超时时间（单位为秒）：默认15s
				.setConnectTimeout(30)
						//文件分片上传时每片的大小（单位字节），默认512*1024
				.setBlockSize(500*1024)
				.build();
		Bmob.getInstance().initConfig(bmobconfig);
		//Bmob初始化
		Bmob.initialize(this, "20ce2eee68e4afb6b99f0a9114f927a8");
		//Bmob的APPKEY
//		Bmob.initialize(getApplicationContext(), "534745ff9e9c1b6b1e54b47115d80f38");
//		BmobInstallation.getCurrentInstallation(getApplicationContext()).save();
//		BmobPush.startWork(getApplicationContext(),"534745ff9e9c1b6b1e54b47115d80f38");
		super.onCreate();
	}

}
