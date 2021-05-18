package com.bayside.app.opinion.mobile.war.user.impl;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayside.app.opinion.mobile.war.user.bo.UserBo;
import com.bayside.app.opinion.mobile.war.user.dao.UserMapper;
import com.bayside.app.opinion.mobile.war.user.dao.WordSetMapper;
import com.bayside.app.opinion.mobile.war.user.model.User;
import com.bayside.app.opinion.mobile.war.user.model.UserLogBo;
import com.bayside.app.opinion.mobile.war.user.model.WordSet;
import com.bayside.app.opinion.mobile.war.user.service.UserLogBoService;
import com.bayside.app.opinion.mobile.war.user.service.UserService;
import com.bayside.app.util.AddressUtils;
import com.bayside.app.util.RequestBrowserInfo;
import com.bayside.app.util.UuidUtil;
@Service("userServiceImpl")
public class UserServiceImpl implements UserService {
	private static final Logger log = Logger.getLogger(UserServiceImpl.class);
    @Autowired
	private UserMapper userMapper; 
    @Autowired
    private WordSetMapper wordSetMapper;
	@Autowired
	private UserLogBoService userLogBoImpl;
	@Override
	public User selectAll(User user) {
		return userMapper.selectAll(user);
}
	@Override
	public User CheckLogin(String name, String password) {
	
		return null;
	}
	@Override
	public WordSet selectPowerByName(WordSet record) {
		// TODO Auto-generated method stub
		return wordSetMapper.selectPowerByName(record);
	}
	@Override
	public UserBo selectPowerByUserId(UserBo record) {
		// TODO Auto-generated method stub
		return userMapper.selectPowerByUserId(record);
	}
	@Override
	public int insertSelective(User u, HttpServletRequest request) {
		// TODO Auto-generated method stub
		//存入日志中
		UserLogBo userlog = new UserLogBo();
		userlog.setId(UuidUtil.getUUID());
		userlog.setUserid(u.getId());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    userlog.setOperatortime(df.format(new Date()));
		InetAddress ia = null;
		try {
			ia = ia.getLocalHost();
			String localname = ia.getHostName();
			String localip = ia.getHostAddress();
			//String ip = request.getRemoteAddr();
			String ip = AddressUtils.getIpAddress(request);
			//操作系统
			String system = System.getProperty("os.name").toLowerCase();
			
			//浏览器
			String brower = RequestBrowserInfo.getRequestBrowserInfo(request);				
			//用户地址
	        AddressUtils addressUtils = new AddressUtils();
	        //外网ip		        
	        String address = "";  	            
	          try {  		                
	           address = addressUtils.getAddresses("ip="+ip, "utf-8");
	           System.out.println(address);		             
	          } catch (Exception e) {  
	              //  e.printStackTrace(); 
	        		log.info(e.getMessage(),e);
	          }  
	        String s = request.getHeader("user-agent");		       
	        userlog.setAddress(address);
			userlog.setBrowser(brower);
			userlog.setIp(ip);
			userlog.setSystem(system);
			userlog.setType(0);
			userlog.setUsername(u.getName());
			
		
		
	}catch (Exception e){
		System.out.println(e.getMessage());
		log.info(e.getMessage(),e);
	}
		return userLogBoImpl.insert(userlog);
	}
	@Override
	public User selectSysPassword(String id) {
		// TODO Auto-generated method stub
		return userMapper.selectSysPassword(id);
	}
	
}
