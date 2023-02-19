package planbServer.planb.joins.model;

import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class MailDTO {
    @ApiParam(value = "유저 아이디")
    private String userId;
    @ApiParam(value = "이메일 제목")
    private String title;
    @ApiParam(value = "이메일 내용")
    private String message;
}
