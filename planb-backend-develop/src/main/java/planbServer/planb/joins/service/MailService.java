package planbServer.planb.joins.service;

import planbServer.planb.joins.model.JoinDTO;
import planbServer.planb.joins.model.MailDTO;

public interface MailService {
    void updateTempPw(JoinDTO loginsDTO);
    public void mailSend(MailDTO mailDTO);
    public String getTempPassword();
    public MailDTO getMail(JoinDTO loginsDTO);
    public JoinDTO findPw(JoinDTO loginsDTO);
}
