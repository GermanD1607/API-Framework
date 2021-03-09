import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;

import java.util.Random;

public class Movies extends Auth{

    @DataProvider(name ="IDs")
    public Object[][] testData(){
        Random random = new Random();
        return new Object[][]{
                {random.nextInt(200)},
                {random.nextInt(200)},
                {random.nextInt(200)},
                {random.nextInt(200)},
                {random.nextInt(200)}
                // 29 41 42 45 51 52 57 151 181
        };
    }

    @org.testng.annotations.Test(dataProvider = "IDs")
    public void GetMovie_Test(Integer id)
    {
        Response response=messageSender.getRequest("/movie/"+id.toString());
        System.out.println(response.then().log().body());
        Assert.assertEquals(200,response.statusCode());
    }

    @org.testng.annotations.Test(dataProvider = "IDs")
    public void PostRate_Test(Integer id)
    {
        Value body = new Value(8);
        Response response=messageSender.rateRequest("/movie/"+id.toString()+"/rating",body);
        System.out.println(response.then().log().body());
        Assert.assertEquals(201,response.statusCode());
    }

    @org.testng.annotations.Test(dataProvider = "IDs")
    public void DeleteRate_Test(Integer id)
    {
        Response response=messageSender.deleteRateRequest("/movie/"+id.toString()+"/rating");
        System.out.println(response.then().log().body());
        Assert.assertEquals(200,response.statusCode());
    }

    @DataProvider(name ="urls")
    public Object[][] endpoints(){
        Random random = new Random();
        return new Object[][]{
                {"alternative_titles"},
                {"credits"},
                {"images"},
                {"release_dates"},
                {"similar"}
        };
    }

    @org.testng.annotations.Test(dataProvider = "urls")
    public void EndpointGet_Test(String endpoint)
    {
        Response response=messageSender.getRequest("/movie/527774/"+endpoint);
        System.out.println(response.then().log().body());
        Assert.assertEquals(200,response.statusCode());
    }
}
