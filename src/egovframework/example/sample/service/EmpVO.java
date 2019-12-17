package egovframework.example.sample.service;

/**
 * @Class Name : EmpVO.java
 * @Description : EmpVO Class
 * @Modification Information
 * @ @ 수정일 수정자 수정내용 @ ---------- ------------ ------------------------ @
 *   2019.11.15 최초생성
 * 
 * @author 올포랜드2 강희진, 김율희
 * @since 2019.11.15
 * @version 1.0
 * 
 */
public class EmpVO {

	/** 사원명 */
	private String empName;

	/** 정답 */
	private String empAnswer;

	/** 통과 여부 */
	private String empPass;

	/** 정답 제출 시간 */
	private String empAnswerTime;

	/** 생성자 */
	public EmpVO() {
	}

	public EmpVO(String empName, String empAnswer, String empPass, String empAnswerTime) {
		this.empName = empName;
		this.empAnswer = empAnswer;
		this.empPass = empPass;
		this.empAnswerTime = empAnswerTime;
	}
	
	/** getter & setter */
	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpAnswer() {
		return empAnswer;
	}

	public void setEmpAnswer(String empAnswer) {
		this.empAnswer = empAnswer;
	}

	public String getEmpPass() {
		return empPass;
	}

	public void setEmpPass(String empPass) {
		this.empPass = empPass;
	}

	public String getEmpAnswerTime() {
		return empAnswerTime;
	}

	public void setEmpAnswerTime(String empAnswerTime) {
		this.empAnswerTime = empAnswerTime;
	}

}
