package skilltracker.fse.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Builder;

@Builder
@Data
public class Result implements Serializable {
	
	private static final long serialVersionUID = -2211832491775352290L;
	private String result = "0";
	private String errorMessage = "";
	private String errorCode = "";
	private Object[] resposnse;

	public Object[] getResposnse() {
		return resposnse;
	}
	public void setResposnse(Object[] resposnse) {
		this.resposnse = resposnse;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Result(Object[] resposnse) {
		this.resposnse = resposnse;
	}
	public Result(String result, String errorMessage, String errorCode) {
		this.result = result;
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
	}
	
	public Result(String result, String errorMessage, String errorCode, Object[] resposnse) {
		this.result = result;
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
		this.resposnse = resposnse;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
