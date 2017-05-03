package com.hy.utils.math;

import java.text.DecimalFormat;
import java.util.Random;


public class NumberUtil {
	/**
	 * 元转分
	 */
	public static int zmoney(float money) {
		return (int)(money*100+0.5);
	}
	/**
	 * 获取随机数
	 * 
	 * @param length
	 * @return
	 */
	public String getRandomNum(int length) {
		int[] i = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		StringBuffer sb = new StringBuffer();
		Random rd = new Random();
		for (int j = 0; j < length; j++) {
			int n = rd.nextInt(10);
			sb.append(i[n]);
		}
		return sb.toString();
	}

	public double formatDouble(double pram, int l) {
		StringBuffer str = new StringBuffer();
		str.append("1");
		for (int i = 0; i < l; i++) {
			str.append("0");
		}
		int f = Integer.parseInt(str.toString());
		double b = Math.round(pram * (f));
		return b / f;
	}

	/**
	 * 保留 两位小数的 float
	 * 
	 * @param price
	 * @return
	 */
	public static float formatFloat(float price) {
		DecimalFormat decimalFormat = new DecimalFormat("#.##");// 构造方法的字符格式这里如果小数不足2位,会以0补足.
		return Float.parseFloat(decimalFormat.format(price));// format 返回的是字符串
	}

	/**
	 * 保留 两位小数的 float
	 * 
	 * @param price
	 * @return
	 */
	public static String formatFloatToString(float price) {
		DecimalFormat decimalFormat = new DecimalFormat("#.##");// 构造方法的字符格式这里如果小数不足2位,会以0补足.
		Float f = Float.parseFloat(decimalFormat.format(price));
		String[] strs = f.toString().split("\\.");
		if (strs[1].length() == 1) { return f.toString() + "0"; }
		return f.toString();// format 返回的是字符串
	}
	
	/**
	 * 分摊
	 * 
	 * @param sum 邮费
	 * @param count
	 * @return
	 */
	public static float[] avgTransportFee(float sum, int count) {
		float avg = sum / count;
		float[] val = new float[count];
		float temp = 0;
		for (int j = 0; j < count - 1; j++) {
			val[j] = formatFloat(avg);
			temp += val[j];
		}
		val[count - 1] = formatFloat(sum - temp);
		return val;
	}

}
