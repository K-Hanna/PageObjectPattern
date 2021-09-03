import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ParametrizedGroups {

    WebDriver driver;
    private SignUpPage signUpPage;

    @BeforeTest(groups={"A","B"})
    public void launchBrowser(){

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.get("http://www.demo.guru99.com/V4/");

        signUpPage = new SignUpPage(driver);
        Assert.assertTrue(signUpPage.isInitialized());
    }

    @Test(dataProvider = "DataProvider", groups="A")
    public void correctData(String user, String password){
        signUpPage.enterLogin(user);
        signUpPage.enterPassword(password);

        ReceiptPage receiptPage = signUpPage.submit();
        Assert.assertEquals("Guru99 Bank Manager HomePage", receiptPage.confirmationHeader());

        receiptPage.logOut();
        Assert.assertEquals("You Have Succesfully Logged Out!!", receiptPage.getLogOutAlert());
    }

    @Test(dataProvider = "DataProvider", groups="B")
    public void incorrectData(String user, String password){
        signUpPage.enterLogin(user);
        signUpPage.enterPassword(password);

        ReceiptPage receiptPage = signUpPage.submit();
        Assert.assertEquals("User or Password is not valid", receiptPage.getInvalidAlert());
    }

    @DataProvider(name = "DataProvider")
    public Object[][] getDataFromDataProvider(ITestContext c){
        Object[][] groupArray = null;
        for (String group : c.getIncludedGroups()) {
            if(group.equalsIgnoreCase("A")){
                groupArray = new Object[][] {
                        {"mngr347475", "AvYrybu"}
                };
                break;
            }
            else if(group.equalsIgnoreCase("B"))
            {
                groupArray = new Object[][] {
                        {"mngr347475", "aaa"},
                        {"aaa", "AvYrybu"},
                        {"bbb", "bbb"}
                };
            }
            break;
        }
        return groupArray;
    }
}