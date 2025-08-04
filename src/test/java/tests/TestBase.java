package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import config.ProjectConfig;
import config.web.WebConfig;
import config.ConfigReader;
import helpers.Attachments;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {

    public static final WebConfig webConfig = ConfigReader.getWebConfig();

    @BeforeAll
    static void baseConfigurationSetup() {
        ProjectConfig projectConfig = new ProjectConfig(webConfig);
        projectConfig.setWebConfig();
    }

    @BeforeEach
    void allureListenerSetup() {
        SelenideLogger.addListener("allureListener", new AllureSelenide());
    }

    @AfterEach
    void shutDown() {
        Attachments.screenshotAs("Final test step screenshot");
        Attachments.pageSource();
        Attachments.browserConsoleLogs();
        if (webConfig.isRemote()) {
            Attachments.addVideo();
        }
        closeWebDriver();
    }
}