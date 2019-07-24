package ru.rustavil.fuel_consumption.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.rustavil.fuel_consumption.domain.Notification;
import ru.rustavil.fuel_consumption.domain.service.NotificationSender;

@Service
public class MailNotificationSender implements NotificationSender {

    private static final Logger LOG = LoggerFactory.getLogger(MailNotificationSender.class);

    private final MailSender mailSender;
    @Value("${app.mail.receivers}")
    private String[] receivers;
    @Value("${app.mail.sender}")
    private String sender;

    @Autowired
    public MailNotificationSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    @Override
    public void sendFuelPurchaseNotice(Notification notification) {
        LOG.info("Sending 'FuelPurchaseNotification' email...");
        for (String receiver: receivers) {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(receiver);
            mail.setFrom(this.sender);
            mail.setSubject(notification.getTitle());
            mail.setText(notification.buildMsg());
            mailSender.send(mail);
        }
        LOG.info("'FuelPurchaseNotification' email sent!");
    }
}
