package tests;

import com.leti.social_net.models.Message;
import com.leti.social_net.models.services.Rating;
import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Junit test for rating service
 */
public class RatingTest extends TestCase {

    public RatingTest(String name) {
        super(name);
    }

    public void testGetRating() throws Exception {
        Rating rating  = new Rating();
        ArrayList<Message> messages = new ArrayList<>(50);
        for (int i = 1; i < 11; i++) {
            for (int j = 0; j < 11-i; j++) {
                Message tmp = new Message();
                tmp.setMessage(Integer.toString(i));
                messages.add(tmp);
            }
        }
       assertEquals(rating.getRating("1",messages),10);
       assertEquals(rating.getRating("2",messages),9);
       assertEquals(rating.getRating("3",messages),8);

    }

}