package egovframework.example.sample.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import egovframework.example.sample.service.EmpVO;
import egovframework.example.sample.service.MCVO;
import egovframework.example.sample.service.OxService;

@Controller
@RequestMapping(value = "/controller")
public class OxController {
	
	/** OxService */
	@Resource(name = "oxService")
	private OxService oxService;
	
	/**
	 * index.jsp 에서 getName.jsp로 이동한다
	 * @return "getName"
	 * @exception Exception
	 */
	@RequestMapping(value = "/go.do")
	public String goGetNamePage(HttpServletRequest request) {
		System.out.println("OxController.goGetNamePage 함수 진입 >>>");
		
		Device device = DeviceUtils.getCurrentDevice(request);
		if (device.isNormal()) {
			return "redirect:/controller/goPc.do";
		}
		else if (device.isMobile()) {
			return "oxGame/getName";
		}
		else if (device.isTablet()) {
			return "oxGame/getName";
		}
		else
			return "../common/error";
	}
	
	/**
	 * getName.jsp 에서 mobile.jsp로 이동한다.
	 * 사원테이블에 이름,세션을 등록한다.
	 * @param userName
	 * @param request
	 * @return mav
	 */
	@RequestMapping(value = "/sendUser.do")
	public ModelAndView insertEmp(@RequestParam("userName") String userName, HttpServletRequest request) {
		System.out.println("OxController.inserEmp 함수 진입 >>>");
		
		// session, userName 값 확인
		HttpSession session = request.getSession();
		System.out.println("session >>> " + session);
		userName = request.getParameter("userName");
		System.out.println("userName >>> " + userName);
		
		try {
			// userName 기존값 확인
			int count  = 0;
			EmpVO empVO = null;
			empVO = new EmpVO();
			empVO.setEmpName(userName);
			count = oxService.selectEmp(empVO);
			System.out.println("Controller userName 기존값? >>> " + count);
			
			// 기존에 uerName이 없을 때 
			// INSERT session, userName
			if (count == 0) {
				empVO.setEmpPass("Y");
				String result = oxService.insertEmp(empVO);
				System.out.println("OxController.inserEmp result >>> " + result);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		// sesson에 userName 값 담기
		session.setAttribute("name", userName);
		System.out.println("session.getAttribute('name') : " + session.getAttribute("name"));
		
		// ModelAndView에 session, view 설정
		ModelAndView mav = new ModelAndView();
		mav.addObject("session", session);
		mav.setViewName("oxGame/mobile");
		
		return mav;
	}
	
	/**
	 * selectMcQStart에서 문제 시작 여부에 따라
	 * mobile.jsp에서 사원이 제출한 정답을 등록/거절 한다.
	 * @param empVO
	 * @param request
	 * @return "success", "deny", "fail"
	 */
	@RequestMapping(value= "/updateEmpAnswer.do")
	@ResponseBody
	public String updateEmpAnswer(@ModelAttribute("empVO") EmpVO empVO, HttpServletRequest request) {
		System.out.println("OxController.updateEmpAnswer 함수 진입 >>>");
		System.out.println("empName >>> " + empVO.getEmpName());
		System.out.println("empAnswer >>> " + empVO.getEmpAnswer());
		
		// 밀리세컨드 구하기
		Date now = null;
		now = new Date();
		String format = new SimpleDateFormat("HHmmssSS", Locale.KOREA).format(now);
		System.out.println("현재 시간 >>> " + format);
		
		// empVO.empAnswerTime 에 set하기
		empVO.setEmpAnswerTime(format);
		System.out.println("empAnswerTime >>> " + empVO.getEmpAnswerTime());
		
		try {
			// mcVO에서 mcQStart 값 가져오기
			String flag = null;
			flag = oxService.selectMcQStart();
			System.out.println("Controller.mcQStart >>> " + flag);
			
			// mcQStart == "Y" 이면 empAnswer을 업데이트한다.
			if (flag.equals("Y")) {				
				int result = oxService.updateEmpAnswer(empVO);
				System.out.println("OxController.updateEmpAnswer result >>> " + result);
				
				if (result>0) // 사원정답이 업데이트 됨
					return "success";
				else // 사원정답이 업데이트 되지 않음
					return "deny";
			} else {
				return "deny";
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "fail";
		}
		
	}
	
	/**
	 * pc.jsp에서 문제 시작 여부(mcQStart)를 업데이트 한다.
	 * @param mcVO
	 * @return "Y", "N", "fail"
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateQuestionStart")
	@ResponseBody
	public String updateQuestionStart(@ModelAttribute("mcVO") MCVO mcVO) throws Exception {
		System.out.println("OxController.updateQuestionStart 함수 진입  >>> ");
		System.out.println("Controller.mcQStart >>> " + mcVO.getMcQStart());
		int result = 0;
		String resultStr = null;

		// [다음] 버튼일 경우 empAnswer = ""
		if (mcVO.getMcQStart().equals("Y")) {
			EmpVO empVO = null;
			empVO = new EmpVO();
			empVO.setEmpAnswer("");
			Date now = null;
			now = new Date();
			String format = new SimpleDateFormat("HHmmssSS", Locale.KOREA).format(now);
			System.out.println("현재 시간 >>> " + format);
			empVO.setEmpAnswerTime(format);
			int empResult = oxService.updateEmpAnswer(empVO);
			System.out.println("[다음]버튼일때 empAnswser == null >>> " + empResult);
		}
		
//		mcVO.setMcQStart("N");
		result = oxService.updateMcQStart(mcVO);
		System.out.println("OxController.updateQuestionStart result >>> " + result);

		if (result > 0) {
			resultStr = oxService.selectMcQStart();
			System.out.println("OxController.mcQStart >>> " + resultStr);
		} else 
			resultStr = "fail";

		return resultStr;
	}
	
	/**
	 * mcAnswer을 업데이트 함과 동시에 채점하여 EmpPass를 업데이트한다.
	 * @param mcVO
	 * @return "success", "fail"
	 */
	@RequestMapping(value="/updateMcAnswerAndEmpPass")
	@ResponseBody
	public String updateMcAnswerAndEmpPass(@ModelAttribute("mcVO") MCVO mcVO) {
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@OxController.updateMcAnswerAndEmpPass 함수 진입 >>>");
		System.out.println("Controller.mcAnswer >>> " + mcVO.getMcAnswer());
		
		int resultMC = 0;
		int resultEMP = 0;
		
		try {
			resultMC = oxService.updateMcAnswer(mcVO);
			System.out.println("Controller.resultMC >>> " + resultMC);
			
			if (resultMC > 0) {
				String mcAnswer = mcVO.getMcAnswer();
				resultEMP = oxService.updateEmpPass(mcAnswer);
				
				System.out.println("Controller.resultEMP >>> " + resultEMP);
				if (resultEMP >= 0)
					return "success";
				else
					return "fail";
			}else
				return "fail";
			/*
			
			return "success";
			 */
		} catch(Exception e) {
			e.printStackTrace();
			
			return "fail";
		}
	}
	
	/**
	 * pc.jsp에 O,X 를 답으로 낸 사원의 리스트를 조회한다.
	 * @return model
	 */
	@RequestMapping(value = "/goPc.do")
	public ModelAndView selectAllEmpList() {
		System.out.println("OxController.selectAllEmpList 함수 진입 >>>");
		
		List oList = null;
		List xList = null;
		EmpVO empVO = null;
		empVO = new EmpVO();
		
		// O List
		empVO.setEmpAnswer("O");
		empVO.setEmpPass("Y");
		System.out.println(empVO.getEmpAnswer() + " 를 답으로 고른 사람 list");
		oList = oxService.selectEmpAnswerList(empVO);
		System.out.println("oList >>> " + oList);
		
		empVO = null;
		empVO = new EmpVO();
		// X List
		empVO.setEmpAnswer("X");
		empVO.setEmpPass("Y");
		System.out.println(empVO.getEmpAnswer() + " 를 답으로 고른 사람 list");
		xList = oxService.selectEmpAnswerList(empVO);
		System.out.println("xList >>> " + xList);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("oList", oList);
		mav.addObject("xList", xList);
		mav.setViewName("oxGame/pc");
		
		return mav;
	}
	
	/**
	 * AddEmp 페이지로 이동
	 */
	@RequestMapping(value = "/goAddEmp.do")
	public String goAddEmp() throws Exception {
		System.out.println("OxController.goAddEmp 함수 진입 >>>");
		
		return "oxGame/addemp";
	}
	
	/**
	 * [추가]버튼 으로 Emp 업데이트
	 * 
	 * @param EmpVO
	 * @return update 적용 결과 count
	 */
	@RequestMapping(value = "/updateAddEmpPass")
	@ResponseBody
	public String updateAddEmpPass(@ModelAttribute("empVO") EmpVO empVO) {
		System.out.println("OxController.updateAddEmpPass 함수 진입 >>>");
		System.out.println("Controller.empName >>> " + empVO.getEmpName());
		System.out.println("Controller.empAnswer >>> " + empVO.getEmpAnswer());

		// 밀리세컨드 구하기
		Date now = null;
		now = new Date();
		String format = new SimpleDateFormat("HHmmssSS", Locale.KOREA).format(now);
		System.out.println("현재 시간 >>> " + format);
		
		empVO.setEmpAnswerTime(format);
		System.out.println("OxController.updateAddEmpPass EmpPass >>> " + empVO.getEmpName());
		System.out.println("OxController.updateAddEmpPass EmpAnswerTime >>> " + empVO.getEmpAnswerTime());
		
		int result = 0;
		String resultStr = null;

		result = oxService.updateAddEmpPass(empVO);
		System.out.println("OxController.updateAddEmpPass result >>> " + result);

		if (result > 0) {
			resultStr = "success";
		} else {
			resultStr = "fail"; }

		return resultStr;
		
	}
	
	/**
	 * [재시작]버튼을 누르면 판이 다시 시작한다.
	 * 모든 emp의 empPass = 'Y', empAnswer = "", empAnswerTime = "" 로 업데이트한다
	 * mcQStart = 'Y'로 업데이트한다.
	 * @return "success", "fail"
	 */
	@RequestMapping(value = "/updateReset.do")
	public @ResponseBody String updateReset() {
		System.out.println("OxController.updateReset 함수 진입 >>>");
		
		int empResult = 0;
		int mcResult = 0;
		String resultStr = null;
		
		try {
			// EMP 테이블 reset
			empResult = oxService.updateResetEmpPass();
			System.out.println("Controller.updateReset empresult >>> " + empResult);
			if (empResult >= 0) {
				// mcQStart = 'Y'로 업데이트
				MCVO mcVO = new MCVO();
				mcVO.setMcQStart("Y");
				mcResult = oxService.updateMcQStart(mcVO);
				System.out.println("Controller.updateReset mcResult >>> " + mcResult);
				
				if (mcResult >= 0)
					resultStr = "success";
				else
					resultStr = "fail";
			} else
				resultStr = "fail";
		} catch(Exception e) {
			e.printStackTrace();
			resultStr =  "fail";
		}
		
		System.out.println("Controller.updateReset resultStr >>> " + resultStr);
		return resultStr;
	}

}
