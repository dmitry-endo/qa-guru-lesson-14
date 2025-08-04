package config;

import com.codeborne.selenide.Configuration;
import config.web.WebConfig;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;
import java.util.UUID;

public class ProjectConfig {

    private final WebConfig webConfig;

    public ProjectConfig(WebConfig webConfig) {
        this.webConfig = webConfig;
    }

    public void setWebConfig() {
        Configuration.baseUrl = webConfig.getBaseUrl();
        Configuration.browser = webConfig.getBrowser().toString();
        Configuration.browserVersion = webConfig.getBrowserVersion();
        Configuration.browserSize = webConfig.getBrowserSize();
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 10000;

        if (webConfig.isRemote()) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true,
                    "name", "Test: " + UUID.randomUUID()
            ));
            Configuration.remote = webConfig.getRemoteUrl();
            Configuration.browserCapabilities = capabilities;
        }
    }
}
