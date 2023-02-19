package planbServer.planb.admin.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import planbServer.planb.admin.model.*;
import planbServer.planb.admin.service.MemberService;
import planbServer.planb.admin.service.OrderService;
import planbServer.planb.admin.service.PaymentService;
import planbServer.planb.admin.service.ProductService;
import planbServer.planb.util.ChangeName;
import planbServer.planb.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
@Slf4j
@RequestMapping("/admin")
@RestController
public class AdminController {
    private final static String imageDirectory = Paths.get("").toAbsolutePath() + "/src/main/resources/images/";
    String uploadFileName;

    @Autowired
    ProductService productService;
    @Autowired
    MemberService memberService;
    @Autowired
    OrderService orderService;
    @Autowired
    PaymentService paymentService;


    @ApiOperation(value = "상품 리스트 출력", notes = "상품 리스트를 출력한다")
    @GetMapping("/productList")
    public List<ProductVO> selectProductList(){

        log.info("상품리스트 출력");

        return productService.selectProductList();
    }

    @ApiOperation(value = "상품 숨김옵션 변경", notes = "상품의 숨김 옵션을 변경한다")
    @PostMapping("/productHide")
    public void updateStatus(@ApiParam(value="상품 ID", required=true) @RequestBody ProductHideDTO productHideDTO){

        log.info("상품 숨김상태 변경 : " + productHideDTO);
        productService.updateStatus(productHideDTO);

        log.info("변경 완료!!!");
    }

    @ApiOperation(value = "상품 등록", notes = "상품정보와 상품이미지를 등록한다")
    @PostMapping("/insertProduct")
    public void insertProduct(
            @ApiParam(value="상품 첨부 이미지", required=true) MultipartFile uploadFile,
            @ApiParam(value="상품 등록 정보", required=true) ProductVO productVO
    )
    {
        //VO + 업로드파일 확인
        log.info("VO 확인 = "+ productVO);
        log.info("upload 확인 = "+ uploadFile);
        log.info("상품 등록 진행");

        // 업로드할 폴더의 경로(path) 구하기
        String path = imageDirectory;

        //업로드한 파일의 파일명을 변수에 담기
        uploadFileName = uploadFile.getOriginalFilename();

        //파일명을 날짜타입으로 변경
        uploadFileName = ChangeName.getChangeFileName(uploadFile.getOriginalFilename());

        try {
            //path 경로에 파일 업로드 진행
            uploadFile.transferTo(new File(path + "/" + uploadFileName));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //성공 시 콘솔에 찍기
        log.info("파일 업로드 성공 -> " + path + uploadFileName );

        //VO 에 uploadFile 담기
        productVO.setProImg(uploadFileName);


        log.info("최종 VO확인 : " + productVO);

        //서비스로 데이터 전달
        productService.insertProduct(productVO);

    }

    @ApiOperation(value = "상품 정보 가져오기", notes = "상품 1개의 VO를 가져온다")
    @GetMapping("/getProductInfo")
    public ProductVO getProductInfo(@RequestParam @ApiParam(value="상품 ID", required=true) int proId){
        
        log.info("상품 정보 가져오기");

        return productService.getProductInfo(proId);
    }

    @ApiOperation(value = "상품 정보 수정", notes = "등록된 상품의 정보를 수정한다")
    @PostMapping("/updateProduct")
    public void updateProduct(
            @ApiParam(value="상품 수정 첨부 이미지", required=false) MultipartFile uploadFile,
            @ApiParam(value="상품 수정 정보", required=true) ProductVO productVO
    )
    {
        //VO + 업로드파일 확인
        log.info("VO 확인 = "+ productVO);
        log.info("upload 확인 = "+ uploadFile);
        log.info("상품 수정 진행");


        // 업로드할 폴더의 경로(path) 구하기
        String path = imageDirectory;

        // DB의 기존 파일명을 변수에 담기
        String oldPhoto = productService.getProductInfo(productVO.getProId()).getProImg();

        // 수정할 이미지파일 첨부했을때
        if (uploadFile != null) {

            // 수정할 이미지파일을 첨부했고, 기존 db에 이미지 파일이 있는 경우
            if (oldPhoto != null) {

                // path 경로에서 기존 파일 삭제
                FileUtil.deletePhoto(path, oldPhoto);   //있을 경우 path 경로의 oldPhoto 를 지운다
                log.info("기존 사진 oldPhoto 삭제 완료 : " + oldPhoto);
            }

            //업로드한 파일의 파일명을 변수에 담기
            uploadFileName = uploadFile.getOriginalFilename();

            //파일명을 날짜타입으로 변경
            uploadFileName = ChangeName.getChangeFileName(uploadFile.getOriginalFilename());

            try {
                //path 경로에 파일 업로드 진행
                uploadFile.transferTo(new File(path + "/" + uploadFileName));

                //성공 시 콘솔에 찍기
                log.info("파일 업로드 성공 -> " + path + uploadFileName);

                //VO 에 uploadFile 담기
                productVO.setProImg(uploadFileName);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            //서비스로 데이터 전달
            productService.updateProduct(productVO);

        // 새로운 파일을 첨부하지 않았을 때
        } else {

            log.info("기존 파일 유지!" + oldPhoto);

            // 기존 DB에 있던 파일명을 VO -> uploadFile 에 담기
            productVO.setProImg(oldPhoto);

            //서비스로 데이터 전달
            productService.updateProduct(productVO);
        }

    }

    @ApiOperation(value = "상품 삭제", notes = "상품 1개의 정보와 상품이미지를 삭제한다")
    @GetMapping("/deleteProduct")
    public void deleteProduct(@ApiParam(value="상품 ID", required=true) int proId) {

        // 삭제대상 폴더의 경로(path) 구하기
        String path = imageDirectory;

        // DB의 기존 파일명을 변수에 담기
        String oldPhoto = productService.getProductInfo(proId).getProImg();

        // path 경로에서 기존 파일 삭제
        FileUtil.deletePhoto(path, oldPhoto);   //있을 경우 path 경로의 oldPhoto 를 지운다
        log.info("기존 사진 oldPhoto 삭제 완료 : " + oldPhoto);

        log.info("DB 삭제 요청 확인.. 삭제 대상 : " + proId);
        productService.deleteProduct(proId);
    }


    @ApiOperation(value = "회원 리스트 출력", notes = "가입된 회원 리스트를 출력한다")
    @PostMapping("/memberList")
    public List<MemberVO> getMemberList(
            @ApiParam(value="검색단어, 정렬필터", required=false) @RequestBody (required = false) MemberSearchDTO memberSearchDTO
    )
    {

        //searchWord 넘어오는지 테스트
        log.info("회원정보 출력");

        //searchOption 재설정
        if(memberSearchDTO.getSearchOption().equals("최신순")){

            memberSearchDTO.setSearchOption("order by user_date desc");

        } else if(memberSearchDTO.getSearchOption().equals("")) {

            memberSearchDTO.setSearchOption("order by user_date desc");

        } else {
            memberSearchDTO.setSearchOption("order by user_date asc");
        }

        //DTO 값 확인
        log.info("DTO 확인 = "+memberSearchDTO);

        return memberService.getMemberList(memberSearchDTO);

    }

    @ApiOperation(value = "주문내역 출력", notes = "주문내역 리스트를 출력한다")
    @GetMapping("/orderList")
    public List<OrderDTO> getOrderList(){

        log.info("주문내역 출력 요청");

        return orderService.getOrderList();
    }

    @ApiOperation(value = "결제내역 출력", notes = "결제내역 리스트를 출력한다")
    @GetMapping("/paymentList")
    public List<PaymentDTO> getPaymentList(){

        log.info("결제내역 출력 요청");

        return paymentService.getPaymentList();
    }




}
