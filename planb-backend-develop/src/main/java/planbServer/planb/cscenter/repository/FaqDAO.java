package planbServer.planb.cscenter.repository;

import org.apache.ibatis.annotations.Mapper;
import planbServer.planb.cscenter.model.FaqVO;

import java.util.List;
@Mapper
public interface FaqDAO {
    public List<FaqVO> getFaqList();
}
