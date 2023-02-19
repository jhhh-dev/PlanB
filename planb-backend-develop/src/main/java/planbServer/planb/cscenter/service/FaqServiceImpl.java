package planbServer.planb.cscenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import planbServer.planb.cscenter.model.FaqVO;
import planbServer.planb.cscenter.repository.FaqDAO;

import java.util.List;
@Service
public class FaqServiceImpl implements FaqService{
    @Autowired
    private FaqDAO faqDAO;

    @Override
    public List<FaqVO> getFaqList(){
        return faqDAO.getFaqList();
    }
}
