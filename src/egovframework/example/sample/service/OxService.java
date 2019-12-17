package egovframework.example.sample.service;

import java.util.List;

public interface OxService {
	
	/**
	 * 사원을 등록한다.
	 * @param empVO
	 * @return 등록 결과 
	 * @throws Exception
	 */
	public String insertEmp(EmpVO empVO);
	
	/**
	 * 사원테이블에서 정답을 업데이트한다.
	 * @param vo - 수정할 정보가 담긴 EmpVO
	 * @return upadate 적용 결과 count
	 */
	public int updateEmpAnswer(EmpVO empVO);
	
	/**
	 * MC테이블에서 문제 시작 여부를 업데이트한다.
	 * @param vo - 수정할 정보가 담긴 MCVO
	 * @return update 적용 결과 count
	 */
	public int updateMcQStart(MCVO mcVO);
	
	/**
	 * MC테이블에서 정답을 업데이트한다.
	 * @param vo - 수정할 정보가 담긴 MCVO
	 * @return update 적용 결과 count
	 */
	public int updateMcAnswer(MCVO mcVO);
	
	/**
	 * 사원테이블에서 통과여부를 'N'으로 업데이트한다.
	 * @param vo - 수정할 정보가 담긴 (String)mcAnswer
	 * @return update 적용 결과 count
	 */
	public int updateEmpPass(String mcAnswer);
	
	/**
	 * 사원테이블에서 '?'를 정답으로 제출한 사람을 조회한다.
	 * @param vo - 조회할 정보가 담긴 EmpVO
	 * @return '?'를 정답으로 제출한 사원의 리스트
	 */
	public List selectEmpAnswerList(EmpVO empVO);
	
	/**
	 * MC테이블에서 문제 시작 여부를 조회한다.
	 * @return vo - MCVO
	 */
	public String selectMcQStart();
	
	/**
	 * @param vo - 조회할 정보가 담긴 EmpVO(empName)
	 * @return count
	 */
	public int selectEmp(EmpVO empVO); 
	
	/**
	 *  [추가]버튼 으로 Emp 업데이트
	 * @param EmpVO 
	 * @return update 적용 결과 count
	 */
	public int  updateAddEmpPass(EmpVO empVO);

	/**
	 * [재시작]버튼으로 모든 emp reset
	 * @return
	 */
	public int updateResetEmpPass();
	
	
}
