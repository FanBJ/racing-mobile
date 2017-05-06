package com.hy.core.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hy.core.entity.SysDictInfo;
import com.hy.utils.cache.CacheUtils;

public abstract class ResultInfo {

	private static Map<Integer, String> m = new HashMap<Integer, String>();

	// -------------success属性值-----------
	/**
	 * 访问成功，不代表业务处理成功
	 */
	public static final int SUCCESS = 1;
	/**
	 * 访问失败，数据不正常、非法访问、恶意访问等
	 */
	public static final int FAIL = -1;

	// ***********公用*************
	public static final int COMMON_ADD_SUC = 10;
	public static final int COMMON_ADD_FAIL = -10;
	public static final int COMMON_EDIT_SUC = 20;
	public static final int COMMON_EDIT_FAIL = -20;

	// **********************************
	// 登陆失效
	public static final int LOGON_FAILURE = -30;

	// -------------state属性值-----------
	/**
	 * 没有数据，某些访问不需要数据时
	 */
	public static final int NOTHING = 0;
	/**
	 * 不存在，该值主要用于各种不存在的业务数据
	 */
	public static final int NO_EXIST = 99;
	/**
	 * 已发送验证码
	 */
	public static final int VALICODE_SEND_SUCCESS = 88;
	/**
	 * 发送验证码失败
	 */
	public static final int VALICODE_SEND_FAIL = -88;
	/**
	 * 验证码失效
	 */
	public static final int VALICODE_LOSE = -77;
	/**
	 * 验证码正确
	 */
	public static final int VALICODE_RIGHT = 77;
	/**
	 * 验证码错误
	 */
	public static final int VALICODE_WRONG = -78;
	/**
	 * 验证码还未失效
	 */
	public static final int VALICODE_NOT_INVALID = -79;
	/**
	 * 手机号错误，不是一个正确的手机号码
	 */
	public static final int VALICODE_TEL_ERROR = -80;

	// ***********登录相关*************
	/**
	 * 登录成功
	 */
	public static final int LOGIN_OK = 100;
	/**
	 * 登录失败
	 */
	public static final int LOGIN_FAIL = -104;
	/**
	 * 账号不存在
	 */
	public static final int LOGIN_NOT_FOUND = -101;
	/**
	 * 账号被禁用
	 */
	public static final int LOGIN_ACCOUNT_OFF = -102;
	/**
	 * 密码错误
	 */
	public static final int LOGIN_PWD_ERROR = -103;
	/**
	 * 密码为null
	 */
	public static final int LOGIN_PWD_NULL = -104;

	// ***********注册相关*************
	/**
	 * 注册成功
	 */
	public static final int REG_OK = 200;
	/**
	 * 账号已存在
	 */
	public static final int REG_TEL_EXIST = -201;
	/**
	 * 密码格式不正确
	 */
	public static final int REG_PWD_ILLEGALITY = -202;

	// **************修改密码***********
	/**
	 * 修改密码成功
	 */
	public static final int UPDATE_PASSWORD_SUC = 203;
	/**
	 * 修改的密码相同
	 */
	public static final int UPDATE_PASSWORD_EQUAL = -706;

	// ***********订单相关*************
	/**
	 * 购物车中的商品验证通过，可进行购买
	 */
	public static final int ORDER_GOODSVALI_SUC = 300;
	/**
	 * 当次购物车中的物品超过每日限定消费金额
	 */
	public static final int ORDER_GOODSVALI_OUTOFDAYMONEY = -301;
	/**
	 * 当次购物车中的物品超过当年限定的消费金额
	 */
	public static final int ORDER_GOODSVALI_OUTOFYEARMONEY = -302;
	/**
	 * 商品数据有更改，需要重新加入该商品
	 */
	public static final int ORDER_GOODSVALI_GOODSINFOCHANGE = -303;
	/**
	 * 商品库存不足
	 */
	public static final int ORDER_GOODSVALI_GOODSOUTOFSTORE = -304;
	/**
	 * 添加订单，未找到订单数据
	 */
	public static final int ORDER_ADD_NOTGOODSDATA = -305;
	/**
	 * 订单状态有变
	 */
	public static final int ORDER_STATUS_CHANGE = -311;
	/**
	 * 价格验证错误306
	 */
	public static final int ORDER_VERIFY_PRICE = -306;
	/**
	 * 订单签收成功
	 *
	 */
	public static final int ORDER_SIGN_FOR_SUC = 312;
	/**
	 * 订单签收失败
	 */
	public static final int ORDER_SIGN_FOR_FALL = -312;
	/**
	 * 延迟收货成功
	 */
	public static int ORDER_EXTEND_RECEIPT_SUC = 314;
	/**
	 * 延迟收货失败
	 */
	public static int ORDER_EXTEND_RECEIPT_FALL = -314;

	// ***********收藏相关*************
	/**
	 * 商品已经收藏过了
	 *
	 */
	public static final int COLLECT_STATUS_EXIST = -401;
	/**
	 * 收藏成功商品
	 */
	public static final int COLLECT_STATUS_GOODS_SUC = 400;
	/**
	 * 收藏成功笔记
	 */
	public static final int COLLECT_STATUS_NOTE_SUC = 410;

	/**
	 * 收藏失败
	 *
	 */
	public static final int COLLECT_STATUS_FAIL = -400;
	/**
	 * 取消收藏成功
	 */
	public static final int COLLECT_STATUS_DELSUC = 402;

	// *************其他***********************
	/**
	 * 删除地址成功
	 */
	public static final int ADDRESS__STATUS_DELSUC = 900;
	/**
	 * 编辑地址地址成功
	 */
	public static final int ADDRESS__STATUS_UPDATE_SUC = 901;
	/**
	 * 取消订单成功
	 */
	public static final int ORDER__STATUS_ABROGATE_SUC = 902;
	/**
	 * 删除订单成功
	 */
	public static final int ORDER__STATUS_DEL_SUC = 903;
	/**
	 * 参数错误
	 */
	public static final int STATUS_FAIL = -100;

	/**
	 * 身份证已存在
	 */
	public static final int INSERT_USER_IDENTITY = -111;

	// *********************优惠券**************
	/**
	 * 商品总价不满足优惠券的满减
	 */
	public static final int DISCOUNT_STATUS_FAIL = -500;
	/**
	 * 优惠券已使用过
	 */
	public static final int DISCOUNT_FAIL = -501;
	/**
	 * 已领过优惠券了
	 */
	public static final int DISCOUNT_YET_FALL = -1101;
	/**
	 * 领过优惠券成功
	 */
	public static final int DISCOUNT_SUC = 1100;
	/**
	 * 优惠券已经被领完
	 */
	public static final int DISCOUNT_NO_FALL = -1102;
	/**
	 * 优惠券兑换码错误
	 */
	public static final int DISCOUNT_REDEEM_CODE_FALL = -1103;
	/**
	 * 优惠券兑换码已经兑换过
	 */
	public static final int DISCOUNT_REDEEM_YET_FALL = -1104;
	/**
	 * 优惠券兑换码已经失效
	 */
	public static final int DISCOUNT_REDEEM_LOSE_FALL = -1105;
	/**
	 * 优惠券兑换码成功
	 */
	public static final int DISCOUNT_REDEEM_SUC = 1101;
	/**
	 * 优惠券兑换码失败
	 */
	public static final int DISCOUNT_REDEEM_FALL = -1106;

	// ***************************退款**************
	/**
	 * 申请退款成功
	 */
	public static final int REFUND_SUC = 600;
	/**
	 * 申请退款失败
	 */
	public static final int REFUND_FAIL = -601;
	/**
	 * 申请退款失败
	 */
	public static final int REFUND_EXIST_FAIL = -602;

	// ************************绑定**********************
	/**
	 * 已绑定微信
	 */
	public static final int USER_BINGING_ALREAD_WXCHAT = -701;
	/**
	 * 已绑定手机
	 */
	public static final int USER_BINGING_ALREAD_PHONE = -702;
	/**
	 * 手机已被绑定微信
	 */
	public static final int USER_BINGING_B_ALREAD_WXCHAT = -703;
	/**
	 * 微信已被绑定手机
	 */
	public static final int USER_BINGING_B_ALREAD_PHONE = -704;
	//
	/**
	 * 绑定成功
	 */
	public static final int USER_BINGING_SUC = 700;
	/**
	 * 用户反馈成功
	 */
	public static final int USER_FEED_BACK_SUC = 705;
	/**
	 * 用户反馈失败
	 */
	public static final int USER_FEED_BACK_FALL = -705;

	// *******************分销员***************************
	/**
	 * 已是分销员
	 */
	public static final int SHARE_STAFF_ALREAD = -801;
	/**
	 * 成为分销员成功
	 */
	public static final int SHARE_STAFF_SUC = 800;
	/**
	 * 成为分销员失败
	 */
	public static final int SHARE_STAFF_FALL = -801;

	// ************************支付异常***********************
	/**
	 * 微信端不支持购买这个仓的商品
	 */
	public static final int WX_PAT_FAIL = -1000;
	// ***************************商品***********************
	/**
	 * 设置成功！该商品补货后会第一时间通知您！
	 */
	public static int INSERT_USER_REMIND_SUC = 1200;
	/**
	 * 设置失败
	 */
	public static int INSERT_USER_REMIND_FALL = -1200;

	// ************************发布笔记****************************
	/**
	 * 发布笔记失败
	 */
	public static int ADD_USER_NOTE_FALL = -1300;
	/**
	 * 发布笔记成功
	 */
	public static int ADD_USER_NOTE_SUC = 1300;

	/**
	 * 删除笔记成功
	 */
	public static int DEL_USER_NOTE_SUC = 1301;
	/**
	 * 删除笔记失败
	 */
	public static int DEL_USER_NOTE_FALL = -1301;
	// **************************举报************************************
	/**
	 * 举报失败
	 */
	public static int ADD_USER_INFORM_FALL = -1400;

	/**
	 * 举报成功
	 */
	public static int ADD_USER_INFORM_SUC = 1400;
	// *********************************点赞**********************************
	/**
	 * 点赞成功
	 */
	public static int ADD_USER_LIKE_SUC = 1500;
	/**
	 * 点赞失败
	 */
	public static int ADD_USER_LIKE_FALL = -1500;
	/**
	 * 已经点过赞了
	 */
	public static int ADD_USER_LIKE_YET_FALL = -1501;
	/**
	 * 已取消点赞
	 */
	public static int CANCEL_USER_LIKE_SUC = 1502;
	/**
	 * 取消点赞失败
	 */
	public static int CANCEL_USER_LIKE_FALL = -1502;
	// ********************************回复***************************************
	/**
	 * 添加回复成功
	 */
	public static int ADD_USER_COMMENT_SUC = 1600;
	/**
	 * 添加回复失败
	 */
	public static int ADD_USER_COMMENT_FALL = -1600;
	
	// ****************************车辆管理相关**************************************
	/**
	 * 添加车辆成功
	 */
	public static final int ADD_CAR_SUC = 1700;
	/**
	 * 车辆信息已存在
	 */
	public static final int ADD_CAR_CODE_EXIST = -1701;
	/**
	 * 车辆数据不完整
	 */
	public static final int ADD_CAR_CODE_DATA_ERROR = -1702;
	/**
	 * 添加车辆时,没有找到车队信息
	 */
	public static final int ADD_CAR_TEAMCODE_NOT_EXIST = -1703;
	

	public static int getfilval(String name) {
		String fieldVal = CacheUtils.getFieldVal("ResultInfo", name);
		if (fieldVal != null) {
			return Integer.valueOf(fieldVal);
		}
		return -1;
	}

	public static String getZhMsg(int key) {
		if (null == m || m.isEmpty()) {
			List<SysDictInfo> list = CacheUtils.findFieldVlsList("ResultInfo");
			if (null != list)
				for (SysDictInfo dic : list) {
					m.put(Integer.parseInt(dic.getFieldVal()), dic.getParentFieldVal());
				}
		}
		return m.get(key) == null ? "" : m.get(key);
	}
}
