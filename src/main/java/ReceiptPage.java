import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ReceiptPage extends PageObject {

    @FindBy(xpath = "/html/body/table/tbody/tr/td/table/tbody/tr[3]/td")
    WebElement managerId;

    @FindBy(xpath = "/html/body/div[3]/div/ul/li[15]/a")
    WebElement logOutBtn;

    public ReceiptPage(WebDriver driver) {
        super(driver);
    }

    public String confirmationHeader(){
        return driver.getTitle();
    }

    public String getInvalidAlert(){
        Alert alert = driver.switchTo().alert();

        String alertInvalidMessage= driver.switchTo().alert().getText();
        alert.accept();

        return alertInvalidMessage;
    }

    public String getManagerId(){
        return this.managerId.getText();
    }

    public void logOut(){
        this.logOutBtn.click();
    }

    public String getLogOutAlert(){

        Alert alert = driver.switchTo().alert();

        String logOutAlertMessage = driver.switchTo().alert().getText();
        alert.accept();

        return logOutAlertMessage;
    }
}
