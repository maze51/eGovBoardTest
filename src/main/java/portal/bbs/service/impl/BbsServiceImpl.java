package portal.bbs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.example.sample.service.SampleDefaultVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import portal.bbs.dao.BbsDAO;
import portal.bbs.service.BbsService;

@Service("bbsService")
public class BbsServiceImpl extends EgovAbstractServiceImpl implements BbsService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BbsServiceImpl.class);
	
	@Resource(name="bbsDAO")	
	private BbsDAO bbsDAO;
	
	@Override
	public List<?> selectList(SampleDefaultVO searchVO) {
		return bbsDAO.selectList("BbsQry.selectBbsList", searchVO);
	}

	@Override
	public int selectListTotCnt(SampleDefaultVO searchVO) {
		return bbsDAO.selectOne("BbsQry.selectBbsListTotCnt", searchVO);
	}

}
