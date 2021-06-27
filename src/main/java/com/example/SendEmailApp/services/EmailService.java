package com.example.SendEmailApp.services;

import javax.mail.MessagingException;
import java.io.IOException;

public interface EmailService {

    public void send(String to) throws MessagingException;
    public void sendEmailWithAttachment() throws MessagingException, IOException;
}
