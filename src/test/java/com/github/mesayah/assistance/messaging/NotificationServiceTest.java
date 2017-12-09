package com.github.mesayah.assistance.messaging;


import com.github.mesayah.assistance.model.Notification;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NotificationServiceTest {

    @Autowired
    private NotificationService service;

    @Test
    public void testCrud() {

        // Create test notification and set its attributes.
        Notification testNotification = new Notification();
        testNotification.setTitle("Test notification");
        testNotification.setTextBody("Test notification body.");

        // Save notification in the database and retrieve an object with ID given.
        Notification sent = service.save(testNotification);

        Long sentId = sent.getId();
        Notification fetched = service.findById(sentId);

        // Test if sent and fetched objects are equal.
        Assert.assertEquals(sent, fetched);

        // Modify and save an object.
        String newTitle = "Modified test title";
        fetched.setTitle(newTitle);
        service.save(fetched);

        // Fetch the object and check the modified value.
        fetched = service.findById(fetched.getId());

        // Test modified title
        Assert.assertEquals(fetched.getTitle(), newTitle);

        // Delete an object from the database.
        service.delete(fetched.getId());
        fetched = service.findById(fetched.getId());

        // Test if object is null.
        Assert.assertNull(fetched);
    }
}
