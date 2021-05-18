package com.bayside.app.opinion.mobile.war.index.systeminforcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bayside.app.opinion.mobile.war.index.systeminforservice.SystemSettingsService;
import com.bayside.app.opinion.mobile.war.user.model.Inforpush;
import com.bayside.app.opinion.mobile.war.user.service.InforpushService;
import com.bayside.app.util.Response;

public class Testing {
	@Autowired
	private static SystemSettingsService systemSettingsImpl;
	@Autowired
	private static InforpushService inforpushImpl;	
	private static String appId = "FyoVWB1PIg9flN6u8ImYv7";
    private static String appKey = "qrOzZJ41qZ8tMhLGxmwZH9";
    private static String masterSecret = "SI6uuxqghV8Y6g9zTlMjF3";
    static String CID = "6e40ea2e32e38382bbe33a4f71209651";//对应的手机   userid对应的是用户
    //别名推送方式
    static String Alias = "TOM";
    static String host = "http://sdk.open.api.igexin.com/apiex.htm";
	public static void main(String[] args) {
		//InforPush inforPush = new InforPush();
//		inforPush.setPush(true);
//		inforpush.setBeginTime(beginTime);
//		inforpush.setFinishTime(finishTime);
//		inforpush.setIntervalTime(intervalTime);
		//IPushResult test = systemSettingsImpl.getOpenPush(inforPush);		 
    }
	public static  String getCID(String userid ){
		String cid="";
		List<Inforpush> ifp = inforpushImpl.seCidByuid(userid);
		if(ifp.size()>0){
			cid = ifp.get(0).getCid();
		}
		return cid;
	}
	public Response getOpenPush(Inforpush inforpush){
		//IPushResult test = systemSettingsImpl.getOpenPush(inforpush);
		//return new Response(ResponseStatus.Success,test,true);
		return null;
	}	
}
