package planbServer.planb.cscenter.model;

import io.swagger.annotations.ApiParam;
import lombok.Data;
@Data
public class NoticeVO {
    @ApiParam(value = "공지 게시글 아이디")
    private int noticeId;
    @ApiParam(value = "공지 게시글 제목")
    private String noticeTitle;
    @ApiParam(value = "공지 게시글 내용")
    private String noticeContent;
}
