package planbServer.planb.common.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class CommonController {

    // 백엔드 이미지 저장 경로
    private final static String imageDirectory = Paths.get("").toAbsolutePath() + "/src/main/resources/images/";

    // [GET 방식 : map]
    // [경로 지정 : http://localhost:7000/showImage?name=PT20210715181929_black_pw_icon.png]
    // [로직 : 서버 로컬 pc에 저장 된 이미지 확인 >> 저장된 이미지 파일이 존재하는 경우 >> 브라우저에서 이미지 표시]
    @ApiOperation(value = "이미지 파일 리턴", notes = "요청받은 파일명을 경로에서 찾은 후 있으면 파일을 리턴한다")
    @GetMapping("/showImage")
    public ResponseEntity<Resource> showImage(
            @ApiParam(value="찾을 파일 이름", required=false)
            @RequestParam(value = "fileName", required = false) String fileName) {
        System.out.println("\n");
        System.out.println("=======================================");
        System.out.println("[request name] : " + String.valueOf(fileName));

        // 사진이 저장된 폴더 경로 변수 선언
        String imageRoot = imageDirectory;

        // 서버 로컬 경로 + 파일 명 저장 실시
        imageRoot = imageRoot + String.valueOf(fileName);

        System.out.println("[path] : " + imageRoot);
        System.out.println("=======================================");


        // Resorce를 사용해서 로컬 서버에 저장된 이미지 경로 및 파일 명을 지정
        // 절대경로라던지 파일시스템에서 리소스를 찾는 방식임
        Resource resource = new FileSystemResource(imageRoot);


        // 로컬 서버에 저장된 이미지 파일이 없을 경우
        if(!resource.exists()){
            return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND); // 리턴 결과 반환 404
        }


        // 로컬 서버에 저장된 이미지가 있는 경우 로직 처리
        HttpHeaders header = new HttpHeaders();
        Path filePath = null;
        try {
            filePath = Paths.get(imageRoot);
            // 인풋으로 들어온 파일명 .png / .jpg 에 맞게 헤더 타입 설정
            header.add("Content-Type", Files.probeContentType(filePath));
        }
        catch (Exception e){
            e.printStackTrace();
        }

        // 이미지 리턴 실시 [브라우저에서 get 주소 확인 가능]
        return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
    }



}
