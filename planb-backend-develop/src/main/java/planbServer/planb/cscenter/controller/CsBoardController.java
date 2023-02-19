package planbServer.planb.cscenter.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import planbServer.planb.cscenter.model.FaqVO;
import planbServer.planb.cscenter.model.NoticeVO;
import planbServer.planb.cscenter.service.FaqService;
import planbServer.planb.cscenter.service.NoticeService;


import java.util.List;

@Slf4j
@RestController
@RequestMapping("/csCenter")
public class CsBoardController {
    @Autowired
    NoticeService noticeService;
    @Autowired
    FaqService faqService;

    @ApiOperation(value = "공지사항 게시글 출력")
    @GetMapping("/noticeList")
    public List<NoticeVO> getNoticeList(){
        log.info("공지사항 리스트");

        return noticeService.getNoticeList();
    }

    @ApiOperation(value = "FAQ 게시글 출력")
    @GetMapping("/faqList")
    public List<FaqVO> getFaqList(){
        log.info("질문답변 리스트");

        return faqService.getFaqList();
    }


}
