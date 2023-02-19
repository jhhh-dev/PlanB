package planbServer.planb.cscenter.repository;

import org.apache.ibatis.annotations.Mapper;
import planbServer.planb.cscenter.model.NoticeVO;

import java.util.List;
@Mapper
public interface NoticeDAO {
   //List<NoticeDTO> getNoticeList(NoticeDTO noticeDTO);
   public List<NoticeVO> getNoticeList();

}
