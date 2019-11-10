package app.hackathon.test;

import static org.junit.Assert.assertTrue;

import app.hackathon.test.helpers.Base;
import org.junit.Test;
import org.testng.annotations.BeforeMethod;

/**
 * Unit test for simple App.
 */
public class AppTest extends Base {

    @BeforeMethod(alwaysRun = true)
    public void initSession(){
        startUp();

    }

    @Test
    public void shouldAnswerWithTrue()
    {
        startUp();
        assertTrue( true );
    }
}
