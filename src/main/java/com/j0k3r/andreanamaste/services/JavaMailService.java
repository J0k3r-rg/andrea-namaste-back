package com.j0k3r.andreanamaste.services;

import com.j0k3r.andreanamaste.security.jwt.JwtService;
import com.j0k3r.andreanamaste.security.models.User;
import jakarta.mail.BodyPart;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class JavaMailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private Environment environment;

    public void sendEmailByActivateUser(User user) throws MessagingException {
        MimeMessage msg = javaMailSender.createMimeMessage();
        msg.setSubject("Activate your account");
        msg.setRecipients(MimeMessage.RecipientType.TO, user.getEmail());
        MimeMultipart multipart = new MimeMultipart();
        multipart.addBodyPart(getLogoImage());
        BodyPart bodyPart = new MimeBodyPart();
        bodyPart.setContent("""
                <h1>Hello %s %s,</h1>
                
                <p>Please click the link below to activate your account:</p>
                <a href="%s/api/v1/user/activate?token=%s&email=%s">Activate account</a>
                
                <h3>Thank you for using Andrea Namaste!</h3>
                """.formatted(user.getNames(), user.getLastnames(), environment.getProperty("BASE_URL"), jwtService.generateTokenToActivateAccount(user), user.getEmail()), "text/html");
        multipart.addBodyPart(bodyPart);
        multipart.addBodyPart(setFooter());
        msg.setContent(multipart);
        javaMailSender.send(msg);
    }

    public void sendEmailByRestorePassword(User user) {

    }

    private BodyPart getLogoImage() throws MessagingException {
        BodyPart bodyPart = new MimeBodyPart();
        bodyPart.setContent("""
                <img src="https://andrea-namaste.netlify.app/_ipx/w_1920,q_75/%2F_next%2Fstatic%2Fmedia%2Flogo.2d5b2bf4.png?url=%2F_next%2Fstatic%2Fmedia%2Flogo.2d5b2bf4.png&w=1920&q=75" alt="Andrea Namaste" style="width: 150px; margin: 0 auto; display: block;">
                """, "text/html");
        return bodyPart;
    }

    private BodyPart setFooter() throws MessagingException {
        BodyPart bodyPart = new MimeBodyPart();
        bodyPart.setContent("""
                <footer>
                    <p>Andrea Namaste</p>
                    <p>© 2024 Andrea Namaste. All rights reserved.</p>
                </footer>
                """, "text/html");
        return bodyPart;
    }
}
