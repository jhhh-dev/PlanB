package planbServer.planb.mypage.model;

import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class ListDTO {
    @ApiParam(value = "유저 아이디")
    private String userId;
    @ApiParam(value = "페이지 번호")
    private int page;

    public void setPage(int page) {
        this.page = page*5;
    }
}
