package portal.bbs;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.example.sample.service.SampleDefaultVO;
import egovframework.example.sample.service.SampleVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import portal.bbs.service.BbsService;

@Controller
public class BbsController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BbsController.class);
	
	@Resource(name = "bbsService")
	private BbsService bbsService;
	
	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
	
	/** Validator */
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;	
	
	/**
	 * 게시물 목록 조회
	 * @param searchVO
	 * @param model
	 * @return "sample/egovSampleList"
	 * @throws Exception
	 */
	@RequestMapping(value = "/egovSampleList.do")
	public String selectListBbs(@ModelAttribute("searchVO") SampleDefaultVO searchVO, ModelMap model) throws Exception {
		
		/** EgovPropertyService.sample */
		searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
		searchVO.setPageSize(propertiesService.getInt("pageSize"));

		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		List<SampleVO> bbsList = bbsService.selectList(searchVO);
		model.addAttribute("resultList", bbsList);
		
		LOGGER.debug("bbsList :::: ", bbsList);
		
		int totCnt = bbsService.selectListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);		
		model.addAttribute("paginationInfo", paginationInfo);
		
		return "sample/egovSampleList";
	}
	
	/**
	 * 게시물 수정 화면 조회
	 * @param id
	 * @param searchVO
	 * @param model
	 * @return "sample/egovSampleRegister"
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateSampleView.do")
	public String updateSampleView(@RequestParam("selectedId") String id, 
					@ModelAttribute("searchVO") SampleDefaultVO searchVO, Model model) throws Exception {
		
		SampleVO sampleVO = new SampleVO();
		sampleVO.setId(id);
		sampleVO = bbsService.selectArticle(sampleVO);
		model.addAttribute("sampleVO", sampleVO);
		
		return "sample/egovSampleRegister";
	}
	
	@RequestMapping(value = "/addSample.do", method = RequestMethod.POST)
	public String addSampleView(@ModelAttribute("searchVO") SampleDefaultVO searchVO, Model model) throws Exception {
		
		model.addAttribute("sampleVO", new SampleVO());
		// 없으면 IllegalStateException: Neither BindingResult nor plain target object for bean name 'sampleVO' available as request attribute
		
		return "sample/egovSampleRegister";
	}
}
