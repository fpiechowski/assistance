package pl.mesayah.assistance.messaging;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.mesayah.assistance.notification.Notification;
import pl.mesayah.assistance.notification.NotificationService;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CrudTest {

    @Autowired
    private NotificationService notificationService;

    /**
     * Test for Notification CRUD operations.
     * <p>
     * It starts with creating test notification object, then It saves and checks if it exists in the repository.
     * Then it fetches it from the database.
     * It then asserts objects equality. Then it modifies an object and sends it again to the database. Object is
     * fetched and modified value is asserted. Then it deletes the object from database and tries to fetch it.
     * Checks if fetched object is null.
     */
    @Test
    public void testNotificationCrud() {

        // Create test notification and set its attributes.
        Notification testNotification = new Notification();
        testNotification.setTitle("Test notification");
        testNotification.setType(Notification.NotificationType.INFO);
        testNotification.setSendDateTime(LocalDateTime.now());

        // Save notification in the database and check if it exists there.
        Notification sent = notificationService.save(testNotification);
        long sentId = sent.getId();
        boolean exists = notificationService.exists(sentId);
        Assert.assertTrue(exists);

        // Fetch saved notification from the database and test if it's the same as a sent one.
        Notification fetched = notificationService.findById(sentId);
        // Test if sent and fetched objects are equal.
        Assert.assertEquals(sent, fetched);

        // Try to retrieve not existing notification.
        Notification notExisting = notificationService.findById(-1L);
        Assert.assertNull(notExisting);

        // Modify and save an object.
        String newTitle = "Modified test title";
        fetched.setTitle(newTitle);
        notificationService.save(fetched);

        // Fetch the object and check the modified value.
        fetched = notificationService.findById(fetched.getId());

        // Test modified title
        Assert.assertEquals(fetched.getTitle(), newTitle);

        // Delete an object from the database.
        notificationService.delete(fetched.getId());
        fetched = notificationService.findById(fetched.getId());

        // Test if object is null.
        Assert.assertNull(fetched);
    }
}
