package com.bayside.app.opinion.mobile.war.index.systeminforcontroller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.bayside.app.opinion.mobile.war.user.model.Inforpush;
import com.bayside.app.opinion.mobile.war.user.service.InforpushService;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;

public class KD {
	private static final Logger log = Logger.getLogger(KD.class);
	@Autowired
	private static InforpushService inforpushImpl;	
	private static String appId = "FyoVWB1PIg9flN6u8ImYv7";
    private static String appKey = "qrOzZJ41qZ8tMhLGxmwZH9";
    private static String masterSecret = "SI6uuxqghV8Y6g9zTlMjF3";
    static String CID = "c531b18093eec9cd2660bd6ce481cc1c";//对应的手机   userid对应的是用户
    //别名推送方式
    static String Alias = "TOM";
    static String host = "http://sdk.open.api.igexin.com/apiex.htm";

	public static void main(String[] args) {
		IGtPush push = new IGtPush(host, appKey, masterSecret);
        LinkTemplate template = linkTemplateDemo();
        SingleMessage message = new SingleMessage();
        message.setOffline(true);
        // 离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(24 * 3600 * 1000);
        message.setData(template);
        // 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
        message.setPushNetWorkType(0); 
        Target target = new Target();
        target.setAppId(appId);
        target.setClientId(CID);
        //target.setAlias(Alias);
        IPushResult ret = null;
        try {
            ret = push.pushMessageToSingle(message, target);
        } catch (RequestException e) {
            e.printStackTrace();
            ret = push.pushMessageToSingle(message, target, e.getRequestId());
        	log.error(e.getMessage(),e);
        }
        if (ret != null) {
            System.out.println(ret.getResponse().toString());
        } else {
            System.out.println("服务器响应异常");
        }
    }
    public static LinkTemplate linkTemplateDemo() {
        LinkTemplate template = new LinkTemplate();
        // 设置APPID与APPKEY
        template.setAppId(appId);
        template.setAppkey(appKey);
        // 设置通知栏标题与内容
        template.setTitle("请输入通知栏标题");
        template.setText("请输入通知栏内容");
        // 配置通知栏图标
        template.setLogo("icon.png");
        // 配置通知栏网络图标，填写图标URL地址
        template.setLogoUrl("");
        // 设置通知是否响铃，震动，或者可清除
        template.setIsRing(true);
        template.setIsVibrate(true);
        template.setIsClearable(true);
        // 设置打开的网址地址
        template.setUrl("http://www.baidu.com");
        return template;
    }
	public static  String getCID(String userid ){
		String cid="";
		List<Inforpush> ifp = inforpushImpl.seCidByuid(userid);
		if(ifp.size()>0){
			cid = ifp.get(0).getCid();
		}
		return cid;
	}

}
