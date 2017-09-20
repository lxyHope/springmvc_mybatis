package cn.ssm.exception;

/**
 * 异常信息处理
 * <p>Description:自定义异常类：针对预期异常，需要在程序中抛出此类异常</p>
 * @author LXY
 * @time 2017年3月13日 下午4:52:07
 */
public class CustomException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9157123634938912967L;
	public String message ;
	
	public CustomException(String message){
		super(message) ;
		this.message = message ;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
