package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;

public class MainPage {
    private final SelenideElement newsFeedWrapper = $("[data-level-type='news']");
    private final SelenideElement contentPageWrapper = $("[data-app-name='flow-page']");
    private final SelenideElement flowsPageWrapper = $("[data-app-name='flows-page']");
    private final SelenideElement sectionsWrapper = $("[aria-label='Разделы']");
    private final SelenideElement notificationsWindowWrapper = $("._notificationWrap_inie6_15");
    private final SelenideElement notificationsIcon = $("[aria-label='Уведомления']");
    private final SelenideElement loginModalWindowWrapper = $("[aria-labelledby='dialog-title']");
    private final SelenideElement profileIcon = $("[aria-label='Авторизоваться']");
    private final ElementsCollection headerNavTabs = $$("[aria-label='Основная навигация'] a");

    @Step("Открываем главную страницу (плюс удаляем баннеры)")
    public MainPage openPage() {
        open("/");

        return this;
    }

    @Step("Проверяем, что новостная лента видна и имеет заголовок")
    public MainPage newsFeedVisibleAndHasText(String value) {
        newsFeedWrapper.shouldBe(visible).shouldHave(text(value));

        return this;
    }

    @Step("Кликаем на новостную ленту")
    public MainPage clickOnNewsLabel(String value) {
        newsFeedWrapper.find(byText(value)).click();

        return this;
    }

    @Step("Проверяем заголовок страницы в новостной ленте")
    public MainPage checkNewsFeedTitleAndParagraph(String title, String paragraph) {
        contentPageWrapper.$("h1").shouldHave(text(title));
        contentPageWrapper.$("p").shouldHave(text(paragraph));

        return this;
    }

    @Step("Проверяем количество основных вкладок навигации в хедере")
    public MainPage checkNavTabsSize(int value) {
        headerNavTabs.shouldHave(size(value));

        return this;
    }

    @Step("Проверяем названия основных вкладок навигации в хедере")
    public MainPage checkNavTabsLabels(String label1, String label2, String label3) {
        headerNavTabs.shouldHave(exactTexts(label1, label2, label3));

        return this;
    }

    @Step("Проверяем, что окошко с уведомлениями скрыто")
    public MainPage notificationsWindowShouldNotBeVisible() {
        notificationsWindowWrapper.shouldNotBe(visible);

        return this;
    }

    @Step("Проверяем, что окошко с уведомлениями появилось")
    public MainPage notificationsWindowShouldBeVisible() {
        notificationsWindowWrapper.shouldBe(visible);

        return this;
    }

    @Step("Кликаем на колокольчик в хедере")
    public MainPage clickNotificationBell() {
        notificationsIcon.click();

        return this;
    }

    @Step("Открываем страницу Потоки")
    public MainPage clickShowAll() {
        sectionsWrapper.find(byTagAndText("a", "Смотреть все")).click();

        return this;
    }

    @Step("Проверяем заголовок страницы Потоки")
    public MainPage checkFlowsPageTitleAndParagraph(String title, String paragraph) {
        flowsPageWrapper.$("h1").shouldHave(text(title));
        flowsPageWrapper.$("p").shouldHave(text(paragraph));

        return this;
    }

    @Step("Проверяем, что присутствуют блоки с темами: {0}")
    public MainPage checkFlowsPageCategories(String[] values) {
        List<String> allTexts = flowsPageWrapper.$$("[class*='_container_'] div").texts();
        assertThat(allTexts).contains(values);

        return this;
    }

    @Step("Проверяем, что модальное окно для входа в профиль скрыто")
    public MainPage loginModalWindowShouldNotBeVisible() {
        loginModalWindowWrapper.shouldNotBe(visible);

        return this;
    }

    @Step("Проверяем, что модальное окно для входа в профиль появилось")
    public MainPage loginModalWindowShouldBeVisible() {
        loginModalWindowWrapper.shouldBe(visible);

        return this;
    }

    @Step("Открываем модальное окно для входа в профиль")
    public MainPage clickProfileIcon() {
        profileIcon.click();

        return this;
    }
}
