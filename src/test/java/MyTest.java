
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

@Listeners(ListenerTest.class)

public class MyTest{

    private WebDriver driver;
    private SignUpPage signUpPage;

    @BeforeTest
    public void launchBrowser(){

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://www.demo.guru99.com/V4/");

        signUpPage = new SignUpPage(driver);
        Assert.assertTrue(signUpPage.isInitialized());
    }

    @Test
    public void signUp(){
        signUpPage.enterLogin("mngr347475");
        signUpPage.enterPassword("AvYrybu");

        ReceiptPage receiptPage = signUpPage.submit();
        System.out.println("Welcome: " + receiptPage.getManagerId());
        Assert.assertEquals("Guru99 Bank Manager HomePage", receiptPage.confirmationHeader());

        receiptPage.logOut();
        Assert.assertEquals("You Have Succesfully Logged Out!!", receiptPage.getLogOutAlert());
    }

    @Test(priority = 1)
    public void invalidUser(){
        signUpPage.enterLogin("mngr");
        signUpPage.enterPassword("AvYrybu");

        ReceiptPage receiptPage = signUpPage.submit();
        Assert.assertEquals("User or Password is not valid", receiptPage.getInvalidAlert());
    }

    @Test(priority = 2)
    public void invalidPassword(){
        signUpPage.enterLogin("mngr347475");
        signUpPage.enterPassword("AvYr");

        ReceiptPage receiptPage = signUpPage.submit();
        Assert.assertEquals("User or Password is not valid", receiptPage.getInvalidAlert());
    }

    @Test(priority = 3)
    public void invalidUserAndPassword(){
        signUpPage.enterLogin("mngr");
        signUpPage.enterPassword("AvYr");

        ReceiptPage receiptPage = signUpPage.submit();
        Assert.assertEquals("User or Password is not valid", receiptPage.getInvalidAlert());
    }

    @Test(priority = 4)
    public void TestToFail(){
        System.out.println("This test is to be failed.");
        Assert.fail();
    }

    @AfterTest
    public void closeBrowser(){
        driver.close();
    }
}
