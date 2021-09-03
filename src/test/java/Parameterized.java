
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

@Listeners(ListenerTest.class)

public class Parameterized{

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

    @Test(dataProvider = "GivenData", dataProviderClass = DataProviderClass.class)
    public void correctData(String user, String password){
        signUpPage.enterLogin(user);
        signUpPage.enterPassword(password);

        ReceiptPage receiptPage = signUpPage.submit();
        Assert.assertEquals("Guru99 Bank Manager HomePage", receiptPage.confirmationHeader());

        receiptPage.logOut();
        Assert.assertEquals("You Have Succesfully Logged Out!!", receiptPage.getLogOutAlert());
    }

    @Test(dataProvider = "GivenData", dataProviderClass = DataProviderClass.class)
    public void incorrectData(String user, String password){
        signUpPage.enterLogin(user);
        signUpPage.enterPassword(password);

        ReceiptPage receiptPage = signUpPage.submit();
        Assert.assertEquals("User or Password is not valid", receiptPage.getInvalidAlert());
    }

    @AfterTest
    public void closeBrowser(){
        driver.close();
    }
}
