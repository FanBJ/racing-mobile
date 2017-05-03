package com.hy.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

/**
 * 极光推送
 */
public class JpushUtils {
	private static String masterSecret="b051f47f5681d13787dc2035";
	private static String appKey="105cfa8f4bc3648203001821";
	
	public static String lufengwen="140fe1da9ea883be4c6";
	static Logger LOG = Logger.getLogger(JpushUtils.class);
	/**
	 * 快捷地构建推送对象：所有平台，所有设备，内容为 ALERT 的通知。
	 * @param ALERT
	 * @return
	 */
	public static PushPayload buildPushObject_all_all_alert(String alert) {
		return PushPayload.alertAll(alert);
	}
	/**
	 * 构建推送对象：所有平台，推送目标是别名为 "alias1"，通知内容为 ALERT。
	 * @param alias
	 * @param alert
	 * @return
	 */
    public static PushPayload buildPushObject_all_alias_alert(String alias,String alert) {
	        return PushPayload.newBuilder()
	                .setPlatform(Platform.all())
	                .setAudience(Audience.alias(alias))
	                .setNotification(Notification.alert(alert))
	                .build();
	    }
    /**
     * 构建推送对象：平台是 Android，目标是 tag 为 "tag1" 的设备，内容是 Android 通知 ALERT，并且标题为 TITLE。
     * @param tag
     * @param alert
     * @param title
     * @return
     */
    public static PushPayload buildPushObject_android_tag_alertWithTitle(String tag,String alert,String title) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.tag(tag))
                .setNotification(Notification.android(alert, title, null))
                .build();
    }
    /**
     * 构建推送对象：平台是 iOS，推送目标是 "tag1", "tag_all" 的交集，推送内容同时包括通知与消息 - 通知信息是 ALERT，角标数字为 5，
     * 通知声音为 "happy"，并且附加字段 from = "JPush"；消息内容是 MSG_CONTENT。
     * 通知是 APNs 推送通道的，消息是 JPush 应用内消息通道的。APNs 的推送环境是“生产”（如果不显式设置的话，Library 会默认指定为开发）
     * @param tag
     * @param tagAll
     * @param alert
     * @param msgContent
     * @return
     */
    public static PushPayload buildPushObject_ios_tagAnd_alertWithExtrasAndMessage(String tag,String tagAll,String alert,String msgContent) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setAudience(Audience.tag_and(tag,tagAll))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(alert)
                                .setBadge(5)
                                .setSound("happy.caf")
                                .addExtra("from", "JPush")
                                .build())
                        .build())
                 .setMessage(Message.content(msgContent))
                 .setOptions(Options.newBuilder()
                         .setApnsProduction(true)
                         .build())
                 .build();
    }
	/**
	 * 构建推送对象：平台是 Andorid 与 iOS，推送目标是 （"tag1" 与 "tag2" 的并集）且（"alias1" 与 "alias2" 的并集），
	 * 推送内容是 - 内容为 MSG_CONTENT 的消息，并且附加字段 from = JPush。
	 * @param tag
	 * @param tagAll
	 * @param alias1
	 * @param alias2
	 * @param msgContent
	 * @return
	 */
    public static PushPayload buildPushObject_ios_audienceMore_messageWithExtras(String tag,String tagAll ,String alias1,String alias2 ,String msgContent) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.newBuilder()
                        .addAudienceTarget(AudienceTarget.tag(tag, tagAll))
                        .addAudienceTarget(AudienceTarget.alias(alias1, alias2))
                        .build())
                .setMessage(Message.newBuilder()
                        .setMsgContent(msgContent)
                        .addExtra("from", "JPush")
                        .build())
                .build();
    }
    
    /**
	 * 构建推送对象：平台是 Andorid 与 iOS，推送目标是 tag，
	 * 推送内容是 - 内容为 MSG_CONTENT 的消息，并且附加字段 from = JPush。
	 * @param tag
	 * @param  title 标题
	 * @param msgContent
	 * @param  extras  map形式参数
	 * @return
	 */
    public static PushPayload buildPushObject_ios_audienceMore_messageWithExtras(String tag,String title,String msgContent) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.newBuilder()
                        .addAudienceTarget(AudienceTarget.tag(tag))
                        .build())
                .setNotification(  Notification
                        .newBuilder()
                        .setAlert(title)//title 为空时为静默推送
                        .build())
                .setMessage(Message.newBuilder()
                		.setMsgContent(msgContent)
                        .addExtra("from", "JPush")
                        .build())
                .build();
    }
    /**
     * ios与android端
     * 极光用户离线消息构建
     */
    public static PushPayload buildPushObject_android_iosMore_messageWithExtras(String tag,String msgContent) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.registrationId(lufengwen))
                .setMessage(Message.newBuilder()
                        .setMsgContent(msgContent)
                        .addExtra("from", "JPush")
                        .build())
                .build();
    }
    
    
    /**
     * ios与android端
     * 极光用户离线消息推送
     */
    public static void PushPay(String registrationId,String msgContent) {
    	JPushClient jpushClient= new JPushClient(masterSecret, appKey);
    	PushPayload payload = buildPushObject_android_iosMore_messageWithExtras(registrationId,msgContent);

		try {
			PushResult result = jpushClient.sendPush(payload);
			LOG.info("Got result - " + result);

		} catch (APIConnectionException e) {
			// Connection error, should retry later
			LOG.error("Connection error, should retry later", e);

		} catch (APIRequestException e) {
			// Should review the error, and fix the request
			LOG.error("Should review the error, and fix the request", e);
			LOG.info("HTTP Status: " + e.getStatus());
			LOG.info("Error Code: " + e.getErrorCode());
			LOG.info("Error Message: " + e.getErrorMessage());
		}

    }
    
    public static void pushOrder(String tag,String title,String msgContent){
    	JPushClient jpushClient= new JPushClient(masterSecret, appKey);
    	PushPayload payload = buildPushObject_ios_audienceMore_messageWithExtras(tag,title,msgContent);
    	try {
			jpushClient.sendPush(payload);
		} catch (APIConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (APIRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
		JPushClient jpushClient= new JPushClient(masterSecret, appKey);
	
		//JPushClient jpushClient= new JPushClient(masterSecret, appKey, proxy, config);
		//JPushClient jpushClient= new JPushClient(masterSecret, appKey, apnsProduction, timeToLive);
		//JPushClient jpushClient= new JPushClient(masterSecret, appKey, maxRetryTimes, proxy);
		//JPushClient jpushClient= new JPushClient(masterSecret, appKey, maxRetryTimes);
	    //JPushClient jpushClient= new JPushClient(masterSecret, appKey, maxRetryTimes, proxy, conf);
	    //JPushClient jpushClient= new JPushClient(masterSecret, appKey, maxRetryTimes, proxy, conf, apnsProduction, timeToLive);
        // For push, all you need do is to build PushPayload object.
//		PushPayload payload = buildPushObject_android_iosMore_messageWithExtras("","{\"typr\":1,\"msg\":\"用户离线\"}");
		
		Map<String ,String > map=new HashMap<String, String>();
		map.put("ddd", "dsafa");
		PushPayload payload = buildPushObject_ios_audienceMore_messageWithExtras("13985502172","订单收货","safaf");

		try {
			PushResult result = jpushClient.sendPush(payload);
			LOG.info("Got result - " + result);

		} catch (APIConnectionException e) {
			// Connection error, should retry later
			LOG.error("Connection error, should retry later", e);

		} catch (APIRequestException e) {
			// Should review the error, and fix the request
			LOG.error("Should review the error, and fix the request", e);
			LOG.info("HTTP Status: " + e.getStatus());
			LOG.info("Error Code: " + e.getErrorCode());
			LOG.info("Error Message: " + e.getErrorMessage());
		}

	}
}
