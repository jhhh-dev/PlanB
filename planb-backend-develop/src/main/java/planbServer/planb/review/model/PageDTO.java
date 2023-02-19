package planbServer.planb.review.model;

import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class PageDTO {
    @ApiParam(value = "현재 페이지 번호")
    private int page; // 현재 페이지 번호
    @ApiParam(value = "정렬 옵션")
    private int option;

    public int getOffset() {
        int result = (page) * 12;
        //log.info(result+"몇개");
        //log.info(page+"몇개");
        return result;
    }


}
