package planbServer.planb.joins.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import planbServer.planb.joins.model.JoinDTO;
import planbServer.planb.joins.model.MailDTO;
import planbServer.planb.joins.repository.JoinDAO;

@Slf4j
@Service
//@AllArgsConstructor
public class MailServiceImpl implements MailService{
    @Autowired
    private JoinDAO loginsDAO;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

 //   @Autowired
    private static final String FROM_ADDRESS = "2planb.mail@gmail.com";

    //아디있는지


    @Override
    public JoinDTO findPw(JoinDTO loginsDTO) {
        return loginsDAO.findPw(loginsDTO);
    }

//    @Bean
//    public JavaMailSenderImpl mailSender() {
//        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
//        javaMailSender.setProtocol("smtp");
//        javaMailSender.setHost("127.0.0.1");
//        javaMailSender.setPort(25);
//        return javaMailSender;
//    }


    //이메일 보내기
    @Override
    public void mailSend(MailDTO mailDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailDTO.getUserId());
        message.setFrom(MailServiceImpl.FROM_ADDRESS);
        message.setSubject(mailDTO.getTitle());
        message.setText(mailDTO.getMessage());

        mailSender.send(message);
        log.info(message+"3532dsfdsf");
    }

    //임시 비밀번호
    @Override
    public String getTempPassword(){
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String str = "";

        // 문자 배열 길이의 값을 랜덤으로 10개를 뽑아 구문을 작성함
        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
    }


    //비번 업뎃
    @Override
    public void updateTempPw(JoinDTO loginsDTO) {
        loginsDAO.updateTempPw(loginsDTO);
    }

    //이메일 설정
    @Override
//    @Bean
    public MailDTO getMail(JoinDTO loginsDTO){
        MailDTO mailDTO = new MailDTO();
        String str = getTempPassword();
        mailDTO.setUserId(loginsDTO.getUserId());
        mailDTO.setTitle("[PlanB] 임시 비밀번호 안내.");
        mailDTO.setMessage("회원님의 임시 비밀번호는 " + str + " 입니다.");

        //비번 변경

        loginsDTO.setUserPw(passwordEncoder.encode(str));
        updateTempPw(loginsDTO);

        return mailDTO;
    }
}
