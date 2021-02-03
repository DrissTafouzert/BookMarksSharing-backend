package com.drissT.reddit.RedditClone.Service;

import com.drissT.reddit.RedditClone.Model.NotificationEmail;

import org.springframework.beans.factory.annotation.Autowired;
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

  @Autowired
  private SendGridAPI sendGridAPi;
    
    public void sendMail(NotificationEmail notificationEmail) throws IOException
    {
      Email from = new Email("driss.tafouzert@gmail.com");
      String subject = notificationEmail.getSubject();
      Email to = new Email(notificationEmail.getRecipient());
      Content content = new Content("text/html", notificationEmail.getBody());
      Mail mail = new Mail(from, subject, to, content); 

      Request request = new Request();
      try {
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());
        Response response = sendGridAPi.api(request);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());
        System.out.println(response.getHeaders());
      } catch (IOException ex) 
      {
        throw new RuntimeException("Something wrong during sending email to your address, try again.");
      }
    }

}
