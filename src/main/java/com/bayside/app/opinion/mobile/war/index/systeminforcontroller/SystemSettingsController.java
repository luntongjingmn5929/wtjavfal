package com.bayside.app.opinion.mobile.war.index.systeminforcontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bayside.app.opinion.mobile.war.index.systeminforentity.MessagePush;
import com.bayside.app.opinion.mobile.war.index.systeminforservice.SystemSettingsService;
import com.bayside.app.opinion.mobile.war.user.model.Inforpush;
import com.bayside.app.opinion.mobile.war.user.service.InforpushService;

@RestController
public class SystemSettingsController {
	@Autowired
	private InforpushService inforpushImpl;	
	@Autowired
	private SystemSettingsService systemSettingsImpl;
	private static String appId = "FyoVWB1PIg9flN6u8ImYv7";
    private static String appKey = "qrOzZJ41qZ8tMhLGxmwZH9";
    private static String masterSecret = "SI6uuxqghV8Y6g9zTlMjF3";
    static String CID = "6e40ea2e32e38382bbe33a4f71209651";//对应的手机   userid对应的是用户
    //别名推送方式
    static String Alias = "TOM";
    static String host = "http://sdk.open.api.igexin.com/apiex.htm";
    //private static String url = "http://sdk.open.api.igexin.com/apiex.htm";
    @RequestMapping(value="/getOpenPush" , method=RequestMethod.GET)
	public void getOpenPush(HttpServletRequest request,MessagePush messagePush){
    	messagePush.setUserid("0100");
    	messagePush.setArticleid("123456");
    	messagePush.setTitle("测试");
    	messagePush.setContent("测试成功啦");
    	messagePush.setMid("sdfeeee");
    	systemSettingsImpl.getOpenPush(messagePush);
    /*	IGtPush pushed = new IGtPush(host, appKey, masterSecret);//调用该类实例的方法来执行对个推的请求       
        LinkTemplate template = new LinkTemplate();//点击通知打开网页
        template.setAppId(appId);
        template.setAppkey(appKey);
        template.setTitle("欢迎使用舆情预警：");
        template.setText("发送最新系统信息。");
        template.setUrl("http://www.baidu.com");
        template.setLogoUrl("www.baidu.com");
        template.setIsVibrate(true);//震动
        template.setIsRing(true);//开启声音
        List<String> appIds = new ArrayList<String>();
        appIds.add(appId);           
        SingleMessage message = new SingleMessage();
        message.setOffline(true);
        // 离线有效时间，单位为毫秒，可选        
        message.setOfflineExpireTime(24 * 3600 * 1000);
        message.setData(template);
        // 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
        message.setPushNetWorkType(0); 
        Target target = new Target();
        String userid="0100";
        String cid = getCID(userid);
        target.setAppId(appId);
        target.setClientId(cid);
        target.setAlias(Alias);
        IPushResult ret = null;       
        try {
            ret = pushed.pushMessageToSingle(message, target);//对单个用户推送消息
        } catch (RequestException e) {
            e.printStackTrace();
            ret = pushed.pushMessageToSingle(message, target, e.getRequestId());
        }
        if (ret != null) {
            System.out.println(ret.getResponse().toString());
        } else {
            System.out.println("服务器响应异常");
        }
	 */
   }
    
   
	public String getCID(String userid ){
		String cid="";
		List<Inforpush> ifp = inforpushImpl.seCidByuid(userid);
		if(ifp.size()>0){
			cid = ifp.get(0).getCid();
		}
		return cid;
	}

}