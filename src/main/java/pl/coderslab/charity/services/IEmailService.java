package pl.coderslab.charity.services;

import pl.coderslab.charity.dto.DonationFormDto;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public interface IEmailService {
    void sendSimpleMessage(String to, String subject, String text);
    void sendHtmlMessage(String to,  Map <String, Object> handleDataForEmail) throws MessagingException, UnsupportedEncodingException;
    Map<String, Object> handleDataForEmail(DonationFormDto donationModel);
}
