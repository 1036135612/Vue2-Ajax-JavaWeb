package web_demo;

public class test {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		long xs=6;
		long mz=50;
		long mm=00;
		long time = System.currentTimeMillis() / 1000;
		long s3;
		long s2 = xs*60*60+mz*60+mm;
		//转换成正数
		if( s2<Pdata(time) ) {
			 s2=s2+24*60*60;
			 s3 =Math.abs(s2- Pdata(time));
		}else {
			 s3 =Math.abs(s2- Pdata(time));
		}
		System.out.println("截至时间：" + s2);
		System.out.println("时间差：" + s3);
		System.out.println("时间差：" + Odata(s3));

	}
	
	public static long Pdata(long time ) {
		
		System.out.println(time);
		// 取天余数，得今天过了多少秒,注意时差
		long h = (time + 8 * 60 * 60) % (24 * 60 * 60);
		// 小时换算
		int h1 = (int) h / 3600;
		// 分钟换算
		long m1 = (h / 60) % 60;

		long s1 = h - (h1 * 60 * 60) - (m1 * 60);
		long s2 = h1*60*60+m1*60+s1;
		System.out.println("北京时间戳取余" + h);
		System.out.println("换算成小时：" + h1);
		System.out.println("换算成分钟：" + m1);
		System.out.println("换算成秒：" + s1);
		return s2;
		
		}
   public static long Odata(long time ) {
		
		System.out.println(time);
		
		// 小时换算
		int h1 = (int) time / 3600;
		// 分钟换算
		long m1 = (time / 60) % 60;

		long s1 = time - (h1 * 60 * 60) - (m1 * 60);
		long s2 = h1*60*60+m1*60+s1;
		System.out.println("换算成小时：" + h1);
		System.out.println("换算成分钟：" + m1);
		System.out.println("换算成秒：" + s1);
		return s2;
		
		}

}
