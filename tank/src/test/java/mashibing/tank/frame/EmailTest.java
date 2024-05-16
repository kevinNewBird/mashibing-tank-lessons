package mashibing.tank.frame;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.junit.Test;

/**
 * description  EmailTest <BR>
 * <p>
 * author: zhao.song
 * date: created in 18:41  2022/8/10
 * company: TRS信息技术有限公司
 * version 1.0
 */
public class EmailTest {

    @Test
    public void qqEmailTest() throws EmailException {
        Email email = new SimpleEmail();
        email.setHostName("smtp.qq.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("1170500835@qq.com", "lnzreqwekisibaac"));
        email.setSSLOnConnect(true);
        email.setFrom("1170500835@qq.com");
        email.setSubject("TestMail");
        email.setMsg("This is a test mail ... :-)");
        email.addTo("song0586@126.com","zhaosong@vastdata.com.cn");
        email.send();
    }
}
