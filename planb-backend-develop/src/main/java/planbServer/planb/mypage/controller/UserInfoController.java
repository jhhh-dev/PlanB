package planbServer.planb.mypage.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import planbServer.planb.mypage.model.*;
import planbServer.planb.mypage.service.UserInfoService;

import java.util.List;

@Api("마이페이지API Controller")
@Slf4j
@RestController
@RequestMapping("/userInfo")
public class UserInfoController {

    @Autowired
    private UserInfoService memberService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @ApiOperation(value = "유저정보 호출")
    @GetMapping("")
    public MemberDTO selectUserInfo(MemberDTO memberDTO){
       // 회원정보
        return memberService.selectUserInfo(memberDTO);
    }

    @ApiOperation(value = "유저이름 호출")
    @GetMapping("/selectUserName")
    public MemberDTO selectUserName(MemberDTO memberDTO){
        // 이름
        return memberService.selectUserName(memberDTO);
    }

    @ApiOperation(value = "이름 수정하기")
    @PostMapping("/updateName")
    public int updateUserInfo(@RequestBody MemberDTO memberDTO){
        //중복검사
        List<String> list = memberService.selectNameList();

        if(list.contains(memberDTO.getUserName())){
            return 0;
        }else{
            return memberService.updateUserName(memberDTO);
        }

    }

    @ApiOperation(value = "비밀번호 수정")
    @PostMapping("/updatePw")
    public void updateUserPw(@RequestBody MemberDTO memberDTO){
        memberDTO.setUserPw(passwordEncoder.encode(memberDTO.getUserPw()));
        memberService.updateUserPw(memberDTO);
    }

    @ApiOperation(value = "비밀번호 확인")
    @PostMapping("/pwCheck")
    public int selectPw(@RequestBody MemberDTO memberDTO){
        log.info(memberDTO.getUserPw()+" 비번");

        if(!passwordEncoder.matches(memberDTO.getUserPw(), memberService.selectPw(memberDTO).getUserPw())){
            return 0;
        }else{
            return 1;
        }
    }

    @ApiOperation(value = "주소 수정하기")
    @PostMapping("/updateAddr")
    public void updateUserAddr(@RequestBody MemberDTO memberDTO){
        memberService.updateUserAddr(memberDTO);
    }

    @ApiOperation(value = "관심상품 호출")
    @GetMapping("/selectBMList")
    public List<BookMarkListDTO> selectBMList(MemberDTO memberDTO){
        return memberService.selectBMList(memberDTO);
    }

    @ApiOperation(value = "리뷰 내역 호출")
    @GetMapping("/selectReviewList")
    public List<ReviewListDTO> selectReviewList(ListDTO listDTO){
        return memberService.selectReviewList(listDTO);
    }

    @ApiOperation(value = "구내 내역 호출")
    @GetMapping("/selectOrderList")
    public List<OrderDetailDTO> selectOrderList(ListDTO listDTO){
        return memberService.selectOrderList(listDTO);
    }

    @ApiOperation(value = "구내 상세 호출")
    @GetMapping("/selectOrderDetail")
    public OrderDetailDTO selectOrderDetail(OrderDetailDTO orderDetailDTO){
        return memberService.selectOrderDetail(orderDetailDTO);
    }
}
