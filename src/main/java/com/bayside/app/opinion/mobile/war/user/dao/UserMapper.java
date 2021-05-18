package com.bayside.app.opinion.mobile.war.user.dao;

import java.util.List;

import com.bayside.app.opinion.mobile.war.user.bo.UserBo;
import com.bayside.app.opinion.mobile.war.user.model.User;




public interface UserMapper {
	    int deleteByPrimaryKey(String id);

	    int insert(User record);

	    int insertSelective(User record);

	    User selectByPrimaryKey(String id);

	    int updateByPrimaryKeySelective(User record);

	    int updateByPrimaryKey(User record);
	    
	    User selectAll(User user);
	    
	    List<User> selectByName(User user);
	    
	    List<User> selectByTel(User user);
	    
	    List<User> selectByEmail(User user);
	    
	    List<User> selectByParentId(String parentid);
	    int countUser(User user);
		
	    User querySingleUser(String name);
	    
	    List<User> selectAllagent(User record);
	    /**
	     * 
	     * <p>方法名称：selectParentUser</p>
	     * <p>方法描述：查询所有的父级账号</p>
	     * @return
	     * @author sql
	     * @since  2016年10月2日
	     * <p> history 2016年10月2日 sql  创建   <p>
	     */
	    List<User> selectParentUser();

	    List<User> selectuserInfo(User record);
	    List<User> selectByUserType(String usertype);
	    
	    List<User> getRegist();
	    UserBo selectPowerByUserId(UserBo user);
	    User selectSysPassword(String id);

}