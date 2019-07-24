package ru.rustavil.fuel_consumption.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.context.junit4.SpringRunner;
import ru.rustavil.fuel_consumption.domain.FuelPurchaseNotification;
import ru.rustavil.fuel_consumption.domain.Notification;
import ru.rustavil.fuel_consumption.domain.service.NotificationSender;

import java.util.HashMap;
import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
public class MailNotificationSenderUnitTest {

    @MockBean
    private MailSender mailSender;
    @Autowired
    private NotificationSender mailNotificationSender;

    @TestConfiguration
    static class NotificationSenderTestContextConfiguration {
        @Bean
        public static PropertySourcesPlaceholderConfigurer properties() throws Exception {
            final PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
            Properties properties = new Properties();

            properties.setProperty("app.mail.receivers", "to");
            properties.setProperty("app.mail.sender", "from");

            pspc.setProperties(properties);
            return pspc;
        }

        @Bean
        public NotificationSender mailNotificationSender(MailSender mailSender) {
            return new MailNotificationSender(mailSender);
        }

    }

    @Before
    public void setUp() throws Exception {
        assertThat(mailSender).isNotNull();
        assertThat(mailNotificationSender).isNotNull();

        doNothing().when(mailSender).send(any(SimpleMailMessage.class));
    }

    @Test
    public void sendMail() {
        Notification notification = new FuelPurchaseNotification(10.0, new HashMap<>(), 10.5);
        mailNotificationSender.sendFuelPurchaseNotice(notification);

        ArgumentCaptor<SimpleMailMessage> argument = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mailSender, atLeastOnce()).send(argument.capture());
        assertThat(argument.getValue().getTo()).isEqualTo(new String[]{"to"});
        assertThat(argument.getValue().getFrom()).isEqualTo("from");
    }
}
