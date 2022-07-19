package ae.proctected.covenant.Seleniumautomation.Webdriver;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.SimpleThreadScope;

import java.util.Objects;

public class WebDriverScope extends SimpleThreadScope {
    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        Object object = super.get(name, objectFactory);

        if (Objects.isNull(((RemoteWebDriver) object).getSessionId())) {
            super.remove(name);
            object = super.get(name, objectFactory);
        }
        return object;

    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {

    }
}
