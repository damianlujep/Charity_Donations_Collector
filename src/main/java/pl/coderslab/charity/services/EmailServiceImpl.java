package pl.coderslab.charity.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import pl.coderslab.charity.dto.DonationFormDto;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@Component
@Service
public class EmailServiceImpl implements IEmailService {
    private final JavaMailSender emailSender;
    private final SpringTemplateEngine templateEngine;

    @Autowired
    public EmailServiceImpl(JavaMailSender emailSender, SpringTemplateEngine templateEngine) {
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("project.email.testing2021@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    @Override
    public void sendHtmlMessage(String to,  Map <String, Object> handleDataForEmail) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

//        ObjectMapper mapper = new ObjectMapper();
//        Map<String, Object> orderDetails = mapper.convertValue(donationModel, Map.class);

        Context context = new Context();
        context.setVariables(handleDataForEmail);

        String html = templateEngine.process("/email/email-confirmation-template", context);

        //Send email confirmation to client
        helper.setTo(to);
        helper.setSubject("Potwierdzenie darowizny");
        helper.setText(html, true);
        helper.setFrom("project.email.testing2021@gmail.com", "Charity App - Test");
        emailSender.send(message);
    }

    @Override
    public Map <String, Object> handleDataForEmail(DonationFormDto donationModel){
        Map <String, Object> orderDetails = new HashMap<>();
        orderDetails.put("categories", donationModel.getCategories());
        orderDetails.put("institution", donationModel.getInstitution());
        orderDetails.put("bagsQuantity", donationModel.getBagsQuantity());
        orderDetails.put("city", donationModel.getCity());
        orderDetails.put("pickUpDate", donationModel.getPickUpDate());
        orderDetails.put("pickUpTime", donationModel.getPickUpTime());
        orderDetails.put("street", donationModel.getStreet());
        orderDetails.put("zipCode", donationModel.getZipCode());
        orderDetails.put("pickUpComment", donationModel.getPickUpComment());

        return orderDetails;
    }
}
