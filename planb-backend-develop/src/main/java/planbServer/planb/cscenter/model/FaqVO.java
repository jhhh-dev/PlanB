package planbServer.planb.cscenter.model;

import io.swagger.annotations.ApiParam;
import lombok.Data;
@Data
public class FaqVO {
    @ApiParam(value = "FAQ 게시글 아이디")
    private int faqId;
    @ApiParam(value = "FAQ 게시글 제목")
    private String faqTitle;
    @ApiParam(value = "FAQ 게시글 내용")
    private String faqContent;
}
