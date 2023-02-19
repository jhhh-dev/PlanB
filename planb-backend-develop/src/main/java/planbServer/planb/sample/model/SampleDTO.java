package planbServer.planb.sample.model;

import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SampleDTO {
    @ApiParam(value = "사용자 아이디", required = true)
    private int id;
    @ApiParam(value = "이름", required = true)
    private String name;
    @ApiParam(value = "성", required = true)
    private String sex;
    @ApiParam(value = "지역번호", required = false)
    private int locationId;
    @ApiParam(value = "생일", required = false)
    private String birthday;
}
