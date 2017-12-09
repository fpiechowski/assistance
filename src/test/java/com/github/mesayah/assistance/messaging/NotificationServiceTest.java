package com.github.mesayah.assistance.messaging;


import com.github.mesayah.assistance.model.Notification;
import org.junit.Test;

public class NotificationServiceTest {

    @Test
    public void testSave() {

        NotificationService service = new NotificationService();

        Notification testNotification = new Notification();
        testNotification.setTitle("Test notification");
        testNotification.setTextBody("Test notification body.");

        service.save(testNotification);

        Assert.
    }
}
