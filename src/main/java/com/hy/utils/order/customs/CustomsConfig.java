package com.hy.utils.order.customs;

public class CustomsConfig {
	    // data=后面的需要用Base64进行加密，并转换成UTF-8，请查收
		//测试环境
//		public static String url = "http://221.224.206.244:8081/KJPOSTWEB/Data.aspx";
//		public static String customsCode = "12345678FK";
		
		//正式环境
		//public static String url = "http://218.70.16.168/KJPostWeb/Data.aspx";
	     public static String customsCode = "50069607FK";
		 public static String enterpriseName = "重庆荐外科技有限公司";
		
		public static String districtCode = "8012";
		
		public static String copNo = "eeout";//企业内部编号 C..20
		
		//电子口岸用户编码
		public static String USERKEY="DXPENT0000012618";
		
		//仓储
		public static String areaCode="5006660012";//区内企业代码
		public static String areaName="重庆嘉铂国际物流有限公司";//区内企业名称
		
		//物流
		public static String logisticsCode="5003980036";
		public static String logisticsName="中国邮政速递物流股份有限公司重庆市分公司";
	
}
