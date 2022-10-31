import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class DeckOfCardsApiTest extends BaseTest{
    RequestSpecification request;
    Response response;
    JsonPath jp;

    @BeforeTest
    public void givenRequest() {
        request = RestAssured.given();
    }

    @Test
    public void createNewDeckOfCards() {
        response = request.get("/new");
        jp = response.jsonPath();
        boolean deckCreated = jp.get("success");
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(deckCreated, true);
    }
    @Test
    public void drawCardWithDeckId() {
        response = request.get("/new");
        String deckId = jp.get("deck_id");
        response = request.get("/"+deckId+"/draw/?count=2");
        jp = response.jsonPath();
        List<String> cardsDrawn = jp.get("cards");
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(cardsDrawn.size(), 2);
    }
    @Test
    public void drawCardFromNewDeck() {
        response = request.get("/new/draw/?count=3");
        jp = response.jsonPath();
        List<String> cardsDrawn = jp.get("cards");
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(cardsDrawn.size(), 3);
    }

    @Test
    public void addJokerToDeck(){
        response = request.queryParam("jokers_enabled", "true").get("/new");
        jp = response.jsonPath();
        boolean success = jp.get("success");
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertTrue(success, "true");
    }

}
