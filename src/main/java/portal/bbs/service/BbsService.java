package portal.bbs.service;

import java.util.List;

import egovframework.example.sample.service.SampleDefaultVO;
import egovframework.example.sample.service.SampleVO;

public interface BbsService {

	List<SampleVO> selectList(SampleDefaultVO searchVO);

	int selectListTotCnt(SampleDefaultVO searchVO);

	SampleVO selectArticle(SampleVO sampleVO);

}
