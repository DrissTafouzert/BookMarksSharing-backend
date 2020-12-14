package com.drissT.reddit.RedditClone.Service;

import com.drissT.reddit.RedditClone.Model.NotificationEmail;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import javax.mail.*;
import javax.mail.internet.*;

import java.util.Properties;

import javax.activation.*;

@Service
@AllArgsConstructor
public class MailService 
{
   // private final JavaMailSender javaMailSender;
    private final MailContentBuilder mailContentBuilder;
    
    void sendMail(NotificationEmail notificationEmail)
    {
        // MimeMessagePreparator messagePreparator = mimeMessage -> 
        // {
        //     MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
        //     messageHelper.setFrom("driss.tafouzert@gmail.com");
        //     messageHelper.setTo(notificationEmail.getRecipient());
        //     messageHelper.setSubject(notificationEmail.getSubject());
        //     messageHelper.setText(notificationEmail.getBody());
        // };
        // try 
        // {
        //     javaMailSender.send(messagePreparator);
        //     // log.info("Activation email sent!!");
        // } catch (Exception e) 
        // {
        //     // log.error("Exception occurred when sending mail", e);
        //     throw new RuntimeException("Exception occurred when sending mail to " + notificationEmail.getRecipient(), e);
        // }

      String to = notificationEmail.getRecipient();
      String from = "driss.tafouzert@gmail.com";
      Properties props = new Properties();
      props.put("mail.smtp.host", "smtp.gmail.com");
      props.put("mail.smtp.port", "587");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.user", "driss.tafouzert@gmail.com");
      props.put("mail.smtp.password", "driss  tafouzert");

      // Get the default Session object.
      Session session = Session.getInstance(props,new Authenticator() 
                {
                    protected PasswordAuthentication  getPasswordAuthentication() 
                    {
                    return new PasswordAuthentication(
                                "driss.tafouzert@gmail.com", "driss  tafouzert");
                    }
                });

      try 
      {
         MimeMessage message = new MimeMessage(session);
         message.setFrom(new InternetAddress(from));
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
         message.setSubject("This is the Subject Line!");
         message.setContent(notificationEmail.getBody(), "text/html");
         Transport.send(message);
         System.out.println("Sent message successfully....");
      } 
      catch (MessagingException mex) 
      {
        mex.printStackTrace();
        throw new RuntimeException("SomeThing worng occured durring sending email to "+to);
      }

    }

}
