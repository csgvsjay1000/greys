package ivg.cn.greys.core.util;

public class GaStringUtils {

	
	/**
	 * 获取异常信息的描述
	 * */
	public static String getCauseMessage(Throwable t) {
		if (t.getCause() !=null) {
			return getCauseMessage(t.getCause());
		}
		return t.getMessage();
	}
	
}
