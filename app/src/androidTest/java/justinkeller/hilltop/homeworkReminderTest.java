package justinkeller.hilltop;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Created by jkeller on 9/16/17.
 */
public class homeworkReminderTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }


    @Test
    public void onCreate() throws Exception {
        assertNotNull(new homeworkReminder().getMyRemTime());
    }

    @Test
    public void addReminder() throws Exception {

    }

}

