package pl.mesayah.assistance.messaging;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.mesayah.assistance.domain.Notification;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NotificationServiceTest {

    @Autowired
    private NotificationService service;

    /**
     * Test for CRUD operations.
     * <p>
     * It starts with creating test notification object, then It saves and fetches it to and from the database.
     * It then asserts objects equality. Then it modifies an object and sends it again to the database. Object is
     * fetched and modified value is asserted. Then it deletes the object from database and tries to fetch it.
     * Checks if fetched object is null.
     */
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
