package planbServer.planb.sample.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import planbServer.planb.sample.model.SampleDTO;
import planbServer.planb.sample.sampleService.SampleService;

import java.util.List;

@Api("샘플API Controller")
@RestController
public class SampleController {

    @Autowired
    SampleService sampleService;

    @ApiOperation(value = "hello-world 호출")
    @GetMapping("/api/sampleApi")
    public String sampleApi(){
        return "hello-world";
    }

    @ApiOperation(value = "샘플 서비스 호출")
    @GetMapping("/api/smapleService")
    public List<SampleDTO> sampleService(){
        return sampleService.selectSample();
    }

    @ApiOperation(value = "샘플 파라미터 서비스 호출")
    @GetMapping("/api/sampleServiceParam")
    public List<SampleDTO> sampleServiceParam(SampleDTO sampleDTO){
        return sampleService.selectSample();
    }
}
