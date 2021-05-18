package com.bayside.app.opinion.mobile.war.report.service.impl;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bayside.app.opinion.mobile.war.report.bo.OpinionReportBo;
import com.bayside.app.opinion.mobile.war.report.dao.OpinionReportMapper;
import com.bayside.app.opinion.mobile.war.report.model.OpinionReport;
import com.bayside.app.opinion.mobile.war.report.service.OpinionReportService;
import com.bayside.app.opinion.mobile.war.subject.dao.SubjectStatisticalMapper;
import com.bayside.app.opinion.mobile.war.user.bo.UserBo;
import com.bayside.app.opinion.mobile.war.user.dao.UserMapper;
import com.bayside.app.opinion.mobile.war.user.model.User;
import com.bayside.app.util.HdfsUpLoadUtil;
@Service("opinionReportServiceImpl")
@Transactional
public class OpinionReportServiceImpl implements OpinionReportService {
	private static final Logger log = Logger.getLogger(OpinionReportServiceImpl.class);
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private OpinionReportMapper opinionReportMapper;
	@Autowired
	private SubjectStatisticalMapper subjectStatisticalMapper;
	
	@Override
	public List<OpinionReport> getOpinionReport(OpinionReportBo oReportBo) {
		
		OpinionReport oReport = new OpinionReport();
		if(oReportBo!=null){
			BeanUtils.copyProperties(oReportBo, oReport);
			List<OpinionReport> list = opinionReportMapper.selectOpinionReport(oReport);
			return list;
		}else{
			return null;
		}
		
	}
	
	
	@Override
	public UserBo getUserInfo(String userid) {
		User user = userMapper.selectByPrimaryKey(userid);
		UserBo userbo = new UserBo();
		if(user!=null){
			BeanUtils.copyProperties(user, userbo);
		}
		return userbo;
	}

	
	
	/**
	 * 
	 * <p>方法名称：sort</p>
	 * <p>方法描述：对map值进行排序从大到小</p>
	 * @param map
	 * @author sql
	 * @since  2016年9月28日
	 * <p> history 2016年9月28日 sql  创建   <p>
	 */
	public static Map<String, Integer> sort(Map<String,Integer> map){  
		Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		if(map!=null){
	    List<Map.Entry<String, Integer>> infoIds = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());   
	    Collections.sort(infoIds, new Comparator<Map.Entry<String, Integer>>() {     
	        public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {     
	            return (o2.getValue() - o1.getValue());     
	        }     
	}); //排序  
	    
	    for (int i = 0; i < infoIds.size(); i++) {   //输出  
	        Entry<String, Integer> id = infoIds.get(i);  
	        sortedMap.put(id.getKey(), id.getValue());
	        }  
	    System.out.println(sortedMap);
		}
	    return sortedMap;
	}
	
	@Override
	public void downloadWorld(String id, HttpServletRequest request, HttpServletResponse response) {
		try{
			OpinionReport oReport = opinionReportMapper.selectByPrimaryKey(id);
			response.setContentType("application/x-download");
			response.addHeader("Content-Disposition",
	              "attachment;filename=" + URLEncoder.encode(oReport.getTitle()+".doc","UTF8"));
			OutputStream os = response.getOutputStream();
		    Configuration conf = new Configuration();
	        conf.set("fs.defaultFS", "hdfs://60.205.106.32:9000");
	        HdfsUpLoadUtil.readFile(oReport.getWordurl(), os, conf);
	         os.close();
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
	}  
	@Override
	public void downloadHtml(String id, HttpServletRequest request, HttpServletResponse response) {
		try{
			OpinionReport oReport = opinionReportMapper.selectByPrimaryKey(id);
			response.setContentType("application/x-download");
			response.addHeader("Content-Disposition",
					"attachment;filename=" + URLEncoder.encode(oReport.getTitle()+".html","UTF8"));
			OutputStream os = response.getOutputStream();
			Configuration conf = new Configuration();
			conf.set("fs.defaultFS", "hdfs://60.205.106.32:9000");
			 FileSystem fs = FileSystem.get(conf);
		        Path srcPath = new Path(oReport.getHtmlurl());
		        FSDataInputStream fsStream = fs.open(srcPath);  
		        BufferedReader bis = new BufferedReader(new InputStreamReader(fsStream));  
		        StringBuffer html = new StringBuffer();
		        String temp = "";
		        while ((temp = bis.readLine()) != null) {  
		            html.append(temp);
		        }     
		        String htm = URLDecoder.decode(html.toString(),"utf-8");
		        htm = htm.replace("../", "http://huolandata.com/");
		        os.write(htm.getBytes("utf-8"));
			os.close();
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage(),e);
		}
	}


	@Override
	public String privew(String id, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try{
			OpinionReport oReport = opinionReportMapper.selectByPrimaryKey(id);
			response.setContentType("text/html;charset=utf-8");
			Configuration conf = new Configuration();
		    conf.set("fs.defaultFS", "hdfs://60.205.106.32:9000");
		    FileSystem fs = FileSystem.get(conf);
	        Path srcPath = new Path(oReport.getHtmlurl());
	        FSDataInputStream fsStream = fs.open(srcPath);  
	        BufferedReader bis = new BufferedReader(new InputStreamReader(fsStream));  
	        StringBuffer html = new StringBuffer();
	        String temp = "";
	        while ((temp = bis.readLine()) != null) {  
	            html.append(temp);
	        }       
	       /* PrintWriter out=response.getWriter();
	        out.println(html.toString());*/
	        return URLDecoder.decode(html.toString(),"utf-8");
			}catch(Exception e){
				/*e.printStackTrace();*/
				log.error(e.getMessage(),e);
				return null;
			}
	}
}
