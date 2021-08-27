import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignUpPage extends PageObject {

    @FindBy(name = "uid")
    private WebElement user;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(name = "btnLogin")
    private WebElement logIn;

    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    public boolean isInitialized() {
        return user.isDisplayed();
    }

    public void enterLogin(String userName){
        this.user.clear();
        this.user.sendKeys(userName);
    }

    public void enterPassword(String pass){
        this.password.clear();
        this.password.sendKeys(pass);
    }

    public ReceiptPage submit(){
        logIn.click();
        return new ReceiptPage(driver);
    }
}
