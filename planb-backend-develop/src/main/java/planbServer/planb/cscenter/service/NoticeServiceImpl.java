package planbServer.planb.cscenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import planbServer.planb.cscenter.model.NoticeVO;
import planbServer.planb.cscenter.repository.NoticeDAO;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeDAO noticeDAO;

    @Override
    public List<NoticeVO> getNoticeList(){

        return noticeDAO.getNoticeList();
    }
}
