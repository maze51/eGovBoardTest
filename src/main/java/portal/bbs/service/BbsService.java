package portal.bbs.service;

import java.util.List;

import egovframework.example.sample.service.SampleDefaultVO;

public interface BbsService {

	List<?> selectList(SampleDefaultVO searchVO);

	int selectListTotCnt(SampleDefaultVO searchVO);

}
