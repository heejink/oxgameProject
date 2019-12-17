package egovframework.example.sample.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.example.sample.service.EmpVO;
import egovframework.example.sample.service.MCVO;
import egovframework.example.sample.service.OxService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("oxService")
public class OxServiceImpl extends EgovAbstractServiceImpl implements OxService{

	/** OxDAO */
	// TODO ibatis 사용
	@Resource(name = "oxDAO")
	private OxDAO oxDAO;
	
	/**
	 * 사원을 등록한다.
	 * @param empVO
	 * @return 등록 결과 
	 * @throws Exception
	 */
	@Override
	public String insertEmp(EmpVO empVO) {
		// TODO Auto-generated method stub
		System.out.println("OxServiceImpl.insertEmp 함수 진입 >>>");
		System.out.println("Service.empName >>> " + empVO.getEmpName());
		System.out.println("Service.empPass >>> " + empVO.getEmpPass());
		
		String result = "";
		try {
			result = oxDAO.insertEmp(empVO);
			System.out.println("OxServiceImpl.inserEmp result >>> " + result);
		}catch(Exception e) {
			e.printStackTrace();
			result = "ServiceImpl.insertEmp에서 에러";
		}
		
		return result;
	}
	
	/**
	 * 사원테이블에서 정답을 업데이트한다.
	 * @param vo - 수정할 정보가 담긴 EmpVO
	 * @return upadate 적용 결과 count
	 */
	@Override
	public int updateEmpAnswer(EmpVO empVO){
		// TODO Auto-generated method stub
		System.out.println("OxServiceImpl.updateEmpAnswer 함수 진입 >>>");
		System.out.println("Service.empName >>> " + empVO.getEmpName());
		System.out.println("Service.empAnswer >>> " + empVO.getEmpAnswer());
		
		int result = 0;
		try {
			result = oxDAO.updateEmpAnswer(empVO);
			System.out.println("OxServiceImpl.updateEmpAnswer result >>> " + result);
		}catch(Exception e) {
			e.printStackTrace();
			result = -1;
		}
		
		return result;
	}

	/**
	 * MC테이블에서 문제 시작 여부를 업데이트한다.
	 * @param vo - 수정할 정보가 담긴 MCVO
	 * @return update 적용 결과 count
	 */
	@Override
	public int updateMcQStart(MCVO mcVO){
		// TODO Auto-generated method stub
		System.out.println("OxServiceImpl.updateMcQStart 함수 진입 >>>");
		
		int result = 0;
		try {
			result = oxDAO.updateMcQStart(mcVO);
			System.out.println("Service.result >>> " + result);
		} catch(Exception e) {
			e.printStackTrace();
			result = -1;
		}
		
		return result;
	}

	/**
	 * MC테이블에서 정답을 업데이트한다.
	 * @param vo - 수정할 정보가 담긴 MCVO
	 * @return update 적용 결과 count
	 */
	@Override
	public int updateMcAnswer(MCVO mcVO){
		// TODO Auto-generated method stub
		System.out.println("OxServiceImpl.updateMcAnswer 함수 진입 >>> ");
		System.out.println("Service.mcAnswer >>> " + mcVO.getMcAnswer());
		
		int result = 0;
		try {
			result = oxDAO.updateMcAnswer(mcVO);
			System.out.println("Service.result >>> " + result);
		} catch(Exception e) {
			e.printStackTrace();
			result = -1;
		}
		
		return result;
	}

	/**
	 * 사원테이블에서 통과여부를 'N'으로 업데이트한다.
	 * @param vo - 수정할 정보가 담긴 (String)mcAnswer
	 * @return update 적용 결과 count
	 */
	@Override
	public int updateEmpPass(String mcAnswer){
		// TODO Auto-generated method stub
		System.out.println("OxServiceImpl.updateEmpPass 함수 진입 >>>");
		System.out.println("Service.mcAnswer >>> " + mcAnswer);
		
		int result = 0;
		try {
			result = oxDAO.updateEmpPass(mcAnswer);
			System.out.println("Service.result >>> " + result);
		} catch(Exception e) {
			e.printStackTrace();
			result = -1;
		}
		
		return result;
	}

	/**
	 * 사원테이블에서 '?'를 정답으로 제출한 사람을 조회한다.
	 * @param vo - 조회할 정보가 담긴 EmpVO
	 * @return '?'를 정답으로 제출한 사원의 리스트
	 */
	@Override
	public List selectEmpAnswerList(EmpVO empVO){
		// TODO Auto-generated method stub
		System.out.println("OxService.selectEmpAnswerList 함수 진입 >>>");
		System.out.println("Service.empAnswer >>> " + empVO.getEmpAnswer());
		
		List list = null;
		try {
			list = oxDAO.selectEmpAnswerList(empVO);
			System.out.println("OxService.list >>> " + list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	/**
	 * MC테이블에서 문제 시작 여부를 조회한다.
	 * @return vo - MCVO
	 */
	@Override
	public String selectMcQStart(){
		// TODO Auto-generated method stub
		System.out.println("OxService.selectMcQStart 함수 진입 >>>");
		
		MCVO mcVO = null;
		mcVO = new MCVO();
		mcVO.setMcName("MC");

		String result = null;
		try {
			System.out.println("Service.mcVO >>> " + mcVO);
			System.out.println("Service.mcName >>> " +mcVO.getMcName());
			System.out.println("Service.mcQStart >>> " + mcVO.getMcQStart());
			mcVO = oxDAO.selectMcQStart(mcVO);
			System.out.println("--------------------------------");
			System.out.println("Service.selectMcQStart result >>> " + mcVO.getMcQStart());
			result = mcVO.getMcQStart();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * @param vo - 조회할 정보가 담긴 EmpVO(empName)
	 * @return count
	 */
	@Override
	public int selectEmp(EmpVO empVO) {
		// TODO Auto-generated method stub
		System.out.println("Service.selectEmp 함수 진입 >>>");
		System.out.println("empName >>> " + empVO.getEmpName());
		int result = 0;
		
		try {
			result = oxDAO.selectEmp(empVO);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	/**
	 *  [추가]버튼 으로 Emp 업데이트
	 * @param EmpVO 
	 * @return update 적용 결과 count
	 */
	@Override
	public int updateAddEmpPass(EmpVO empVO) {
		// TODO Auto-generated method stub
		System.out.println("OxService.updateAddEmpPass 함수 진입 >>>");
		
		int result = 0;
		try {
			result = oxDAO.updateAddEmpPass(empVO);
			System.out.println("OxService.updateAddEmpPass Service.result >>> " + result);
		} catch(Exception e) {
			e.printStackTrace();
			result = -1;
		}
		
		return result;

	}

	/**
	 * [재시작]버튼으로 모든 emp reset
	 * @return
	 */
	@Override
	public int updateResetEmpPass() {
		// TODO Auto-generated method stub
		System.out.println("OxService.updateResetEmpPass 함수 진입 >>>");
		
		int result = 0;
		try {
			result = oxDAO.updateResetEmpPass();
			System.out.println("OxService.updateResetEmpPass Service.result >>> " + result);
		} catch(Exception e) {
			e.printStackTrace();
			result = -1;
		}
		
		return 0;
	}


	
}
