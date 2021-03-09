import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;

import java.util.Random;

public class List extends Auth{

    @DataProvider(name ="IDs")
    public Object[][] idData(){
        Random random = new Random();

        return new Object[][]{
                {random.nextInt(200)},
                {random.nextInt(200)},
                {random.nextInt(200)},
                {random.nextInt(200)},
                {random.nextInt(200)}
                // 42 94 95
        };
    }

    @org.testng.annotations.Test(dataProvider = "IDs")
    public void GetList_Test(Integer id)
    {
        Response response=messageSender.getRequest("/list/"+id.toString());
        System.out.println(response.then().log().body());
        Assert.assertEquals(200,response.statusCode());
    }

    @org.testng.annotations.Test(dataProvider = "IDs")
    public void PostAdd_Test(Integer id)
    {
        MediaId body = new MediaId(8);
        Response response=messageSender.addRequest("/list/"+id.toString()+"/add_item",body);
        System.out.println(response.then().log().body());
        Assert.assertEquals(200,response.statusCode());
    }

    @org.testng.annotations.Test(dataProvider = "IDs")
    public void PostClear_Test(Integer id)
    {
        Response response=messageSender.clearRequest("/list/"+id.toString()+"/clear");
        System.out.println(response.then().log().body());
        Assert.assertEquals(200,response.statusCode());
    }

    @org.testng.annotations.Test(dataProvider = "IDs")
    public void DeleteList_Test(Integer id)
    {
        Response response=messageSender.deleteRequest("/list/"+id.toString());
        System.out.println(response.then().log().body());
        Assert.assertEquals(200,response.statusCode());
    }

    @DataProvider(name ="Lists")
    public Object[][] listData(){
        Random random = new Random();

        return new Object[][]{
                {"Lista 1","Lista primer prueba","EN"},
                {"Lista 2","Lista segunda prueba","ES"},
                {"Lista 3","Lista ultima prueba","ES"}
        };
    }

    @org.testng.annotations.Test(dataProvider = "Lists")
    public void PostCreate_Test(String name, String desc, String lang)
    {
        ListBody body = new ListBody(name,desc,lang);
        Response response=messageSender.createListRequest("/list",body);
        System.out.println(response.then().log().body());
        Assert.assertEquals(201,response.statusCode());
    }
}
