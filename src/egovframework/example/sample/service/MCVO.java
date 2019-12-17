package egovframework.example.sample.service;

public class MCVO {
	
	/** MC */
	private String mcName;
	
	/** 정답 */
	private String mcAnswer;
	
	/** 문제 시작 여부 */
	private String mcQStart;
	
	/** 생성자 */
	public MCVO() {}

	public MCVO(String mcName, String mcAnswer, String mcQStart) {
		this.mcName	  = mcName;
		this.mcAnswer = mcAnswer;
		this.mcQStart = mcQStart;
	}

	/** getter & setter */
	public String getMcName() {
		return mcName;
	}
	
	public void setMcName(String mcName) {
		this.mcName = mcName;
	}
	
	public String getMcAnswer() {
		return mcAnswer;
	}

	public void setMcAnswer(String mcAnswer) {
		this.mcAnswer = mcAnswer;
	}

	public String getMcQStart() {
		return mcQStart;
	}

	public void setMcQStart(String mcQStart) {
		this.mcQStart = mcQStart;
	}
	
	
}
