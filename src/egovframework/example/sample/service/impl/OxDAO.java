package egovframework.example.sample.service.impl;

import java.util.List;

import javax.ws.rs.container.Suspended;

import org.springframework.stereotype.Repository;

import egovframework.example.sample.service.EmpVO;
import egovframework.example.sample.service.MCVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("oxDAO")
public class OxDAO extends EgovAbstractDAO {
	
	/**
	 * 사원을 등록한다.
	 * @param vo - 등록할 정보가 담긴 EmpVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public String insertEmp(EmpVO empVO) throws Exception{
		System.out.println("OxDAO.inserEmp 함수 진입 >>>");
		return (String) insert("oxDAO.insertEmp", empVO);
	}
	
	/**
	 * 사원테이블에서 정답을 업데이트한다.
	 * @param vo - 수정할 정보가 담긴 EmpVO
	 * @return update 적용 결과 count
	 * @exception Exception
	 */
	public int updateEmpAnswer(EmpVO empVO) throws Exception {
		System.out.println("OxDAO.updateEmpAnswer 함수 진입 >>>");
		
		return update("oxDAO.updateEmpAnswer", empVO);
	}
	
	/**
	 * MC테이블에서 문제 시작 여부를 업데이트한다.
	 * @param vo - 수정할 정보가 담긴 MCVO
	 * @return update 적용 결과 count
	 * @exception Exception
	 */
	public int updateMcQStart(MCVO mcVO) throws Exception {
		System.out.println("OxDAO.updateMcQStart 함수 진입 >>>");
		System.out.println("DAO.mcQStart >>> " + mcVO.getMcQStart());
		
		return update("oxDAO.updateMcQStart", mcVO);
	}
	
	/**
	 * MC테이블에서 정답을 업데이트한다.
	 * @param vo - 수정할 정보가 담긴 MCVO
	 * @return update 적용 결과 count
	 * @exception Exception
	 */
	public int updateMcAnswer(MCVO mcVO) throws Exception {
		System.out.println("OxDAO.updateMcAnswer 함수 진입 >>>");
		System.out.println("DAO.mcAnswer >>> " + mcVO.getMcAnswer());
		
		return update("oxDAO.updateMcAnswer", mcVO);
	}
	
	/**
	 * 사원테이블에서 통과여부를 'N'으로 업데이트한다.
	 * @param vo - 수정할 정보가 담긴 (String)mcAnswer
	 * @return update 적용 결과 count
	 * @exception Exception
	 */
	public int updateEmpPass(String mcAnswer) {
		System.out.println("oxDAO.updateEmpPass 함수 진입 >>> ");
		System.out.println("DAO.mcAnwer >>> " + mcAnswer);
		
		try {
			return update("oxDAO.updateEmpPass", mcAnswer);
			
		} catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * 사원테이블에서 '?'를 정답으로 제출한 사람을 조회한다.
	 * @param vo - 조회할 정보가 담긴 EmpVO
	 * @return '?'를 정답으로 제출한 사원의 리스트
	 */
	public List selectEmpAnswerList(EmpVO empVO) {
		System.out.println("OxDAO.selectEmpAnswerList 함수 진입 >>>");
		System.out.println("DAO.empAnswer >>> " + empVO.getEmpAnswer());
		
		try {			
			return list("oxDAO.selectEmpAnswerList", empVO);
		} catch (Exception e) {
			e.printStackTrace();
			List list = null;
			
			return list;
		}
	}
	
	/**
	 * MC테이블에서 문제 시작 여부를 조회한다.
	 * @return vo - MCVO
	 */
	public MCVO selectMcQStart(MCVO mcVO) {
		System.out.println("OxDAO.selectMcQStart 함수 진입 >>>");
		System.out.println("DAO.mcName >>> " + mcVO.getMcName());
		System.out.println("DAO.mcQStart >>> " + mcVO.getMcQStart());
		
		try {
			mcVO = (MCVO)select("oxDAO.selectMcQStart", mcVO);
			System.out.println("----------------------------");
			System.out.println("oxDAO.mcQStart >>> " + mcVO.getMcQStart());
			
			return mcVO;
		} catch (Exception e) {
			e.printStackTrace();
			mcVO = null;
			
			return mcVO;
		}
	}
	
	/**
	 * 사원의 이름을 조회하여 카운트로 리턴한다.
	 * @param empVO
	 * @return
	 */
	public int selectEmp(EmpVO empVO) {
		System.out.println("OxDAO.selectEmp 함수 진입 >>>");
		
		return (int)select("oxDAO.selectEmp", empVO);
	}
	
	/**
	 * [추가]버튼 으로 Emp 업데이트
	 * 
	 * @param EmpVO
	 * @return update 적용 결과 count
	 */
	public int updateAddEmpPass(EmpVO empVO) {
		System.out.println("OxDAO.updateAddEmpPass 함수 진입 >>>");
		
		return update("oxDAO.updateAddEmpPass", empVO);
	}
	
	/**
	 * [재시작]버튼으로 모든 emp reset
	 * @return
	 */
	public int updateResetEmpPass() {
		System.out.println("OxDAO.updateResetEmpPass 함수 진입 >>>");
		
		return update("oxDAO.updateResetEmpPass");
	}
}
