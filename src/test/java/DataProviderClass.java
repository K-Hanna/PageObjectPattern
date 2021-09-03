import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

public class DataProviderClass {

    @DataProvider(name = "GivenData")
    static public Object[][] getData(Method method){
        if(method.getName().equals("correctData")){
            return new Object[][]{
                    {"mngr347475", "AvYrybu"}
            };
        } else {
            return new Object[][]{
                    {"mngr347475", "aaa"},
                    {"aaa", "AvYrybu"},
                    {"bbb", "bbb"}
            };
        }
    }
}
