package com.behere.system.util;
/**
 * 前台返回操作成功对象.
 * @author nanbo
 * @version V1.0.0-RELEASE 日期：2016-4-20
 * @since 1.0.0-RELEASE
 */
public class RespJson {
	private String respCode;
	
    private String respDesc;
    
    private String stackTrace;

	public RespJson() {}
	
	public RespJson(String respCode, String respDesc) {
		this.respCode = respCode;
		this.respDesc = respDesc;
	}

	public RespJson(String respCode, String respDesc, String stackTrace) {
		this.respCode = respCode;
		this.respDesc = respDesc;
		this.stackTrace = stackTrace;
	}
	
	/**
     * 封装ajax前台返回信息对象。<br/>
     * 详细描述：封装ajax前台返回信息对象，包含message信息，和异常堆栈信息。<br/>
     * 使用方式：实例化RespJson后调用getJson方法。
     * @param respCode 自定义的错误代码。
     * @param respDesc 自定义的描述信息。
     * @param stackTrace 异常堆栈信息。
     * @return ajax前台返回信息对象。
     */
    public static RespJson getJson(String respCode, String respDesc, String stackTrace){
        return new RespJson(respCode, respDesc, stackTrace);
    }
    
    public static RespJson getJson(String respCode, String respDesc){
        return new RespJson(respCode, respDesc);
    }

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getRespDesc() {
		return respDesc;
	}

	public void setRespDesc(String respDesc) {
		this.respDesc = respDesc;
	}

	public String getStackTrace() {
		return stackTrace;
	}

	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}
}