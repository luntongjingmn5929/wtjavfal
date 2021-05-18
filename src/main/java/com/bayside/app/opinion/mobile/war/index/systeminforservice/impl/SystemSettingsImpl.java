package com.bayside.app.opinion.mobile.war.index.systeminforservice.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayside.app.opinion.mobile.war.index.systeminforentity.MessagePush;
import com.bayside.app.opinion.mobile.war.index.systeminforservice.SystemSettingsService;
import com.bayside.app.opinion.mobile.war.user.bo.InforpushBo;
import com.bayside.app.opinion.mobile.war.user.dao.InforpushMapper;
import com.bayside.app.opinion.mobile.war.user.model.Inforpush;
import com.bayside.app.opinion.mobile.war.user.service.InforpushService;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;
@Service("systemSettingsImpl")
public class SystemSettingsImpl implements SystemSettingsService {
	private static final Logger log = Logger.getLogger(SystemSettingsImpl.class);
	@Autowired
	private InforpushService inforpushImpl;	
	@Autowired
	private InforpushMapper inforpushMapper;
	private static String appId = "FyoVWB1PIg9flN6u8ImYv7";
    private static String appKey = "qrOzZJ41qZ8tMhLGxmwZH9";
    private static String masterSecret = "SI6uuxqghV8Y6g9zTlMjF3";
  //  static String CID = "6e40ea2e32e38382bbe33a4f71209651";//对应的手机   userid对应的是用户
    //别名推送方式
    static String Alias = "TOM";
    static String host = "http://sdk.open.api.igexin.com/apiex.htm";
    @Override
	public IPushResult getOpenPush(MessagePush messagePush) {
    	IGtPush pushed = new IGtPush(host, appKey, masterSecret);//调用该类实例的方法来执行对个推的请求       
        LinkTemplate template = new LinkTemplate();//点击通知打开网页
        template.setAppId(appId);
        template.setAppkey(appKey);
        template.setTitle(messagePush.getTitle());
        template.setText(messagePush.getContent());
        template.setUrl("http://www.baidu.com");
        template.setLogoUrl("www.baidu.com");
     /*   List<String> appIds = new ArrayList<String>();
        appIds.add(appId);  */         
        SingleMessage message = new SingleMessage();
        message.setOffline(true);
        // 离线有效时间，单位为毫秒，可选        
        message.setOfflineExpireTime(24 * 3600 * 1000);
        // 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
        message.setPushNetWorkType(0); 
        Target target = new Target();
        target.setAppId(appId);
        target.setAlias(Alias);
        List<Inforpush> cidList = inforpushImpl.seCidByuid(messagePush.getUserid());
        IPushResult ret = null;  
        for (Inforpush ipush : cidList) {
        	 template.setIsVibrate(ipush.getIsVibrate());//震动
             template.setIsRing(ipush.getIsRing());//开启声音
             message.setData(template);
             target.setClientId(ipush.getCid());
             
             try {
                 ret = pushed.pushMessageToSingle(message, target);//对单个用户推送消息
             } catch (RequestException e) {
                System.out.println(e.getMessage());
            	log.error(e.getMessage(),e);
             }
		}
		return ret;
	}
    
    @Override
	public int updateByCidSelective(InforpushBo record) {
		return inforpushMapper.updateByCidSelective(record);
	}

	//该方法用于通过Userid查询Cid
	public String getCID(String userid ){
		String cid="";
		List<Inforpush> ifp = inforpushImpl.seCidByuid(userid);
		if(ifp.size()>0){
			cid = ifp.get(0).getCid();
		}
		return cid;
	}

	

	
}
