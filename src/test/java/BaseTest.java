import io.restassured.RestAssured;
import org.testng.annotations.BeforeTest;

import java.util.ArrayList;

public class BaseTest {
    @BeforeTest
    public void getDeckData() {
        RestAssured.baseURI = "https://www.deckofcardsapi.com/api/deck";
    }
}

