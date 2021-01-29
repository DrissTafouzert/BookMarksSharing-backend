package com.drissT.reddit.RedditClone.Service;

import com.drissT.reddit.RedditClone.Model.NotificationEmail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import java.io.IOException; 

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

@Service
@AllArgsConstructor
public class MailService 
{ 
  private final MailContentBuilder mailContentBuilder;

  @Autowired
  private SendGridAPI sendGridAPi;
    
    public void sendMail(NotificationEmail notificationEmail) throws IOException
    {
      Email from = new Email("driss.tafouzert@gmail.com");
      String subject = notificationEmail.getSubject();
      Email to = new Email(notificationEmail.getRecipient());
      Content content = new Content("text/html", mailContentBuilder.build(notificationEmail.getBody()));
      Mail mail = new Mail(from, subject, to, content);

      // SendGrid sg = new SendGrid(SENDGRID_API_KEY.toString());
      Request request = new Request();
      try {
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());
        Response response = sendGridAPi.api(request);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());
        System.out.println(response.getHeaders());
      } catch (IOException ex) {
        throw ex;
      }
    }

}
