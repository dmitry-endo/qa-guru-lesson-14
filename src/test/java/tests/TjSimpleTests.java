package tests;

import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.MainPage;

@Tag("tj_simple_tests")
@DisplayName("Тесты для сайта Т-Ж")
public class TjSimpleTests extends TestBase {

    private final MainPage mainPage = new MainPage();

    @Test
    @Owner("dmitry_endo")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Новостная лента должна быть видна на главной странице и кликабельна")
    void newsFeedShouldBeVisibleAndClickable() {
        String label = "Новости";
        String paragraph = "Рассказываем все важные новости и объясняем, как они влияют на жизнь";

        mainPage.openPage()
                .newsFeedVisibleAndHasText(label)
                .clickOnNewsLabel(label)
                .checkNewsFeedTitleAndParagraph(label, paragraph);
    }

    @Test
    @Owner("dmitry_endo")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Проверка появления модального окна входа в профиль")
    void loginModalWindowShouldAppear() {

        mainPage.openPage()
                .loginModalWindowShouldNotBeVisible()
                .clickProfileIcon()
                .loginModalWindowShouldBeVisible();
    }

    @Test
    @Owner("dmitry_endo")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Проверка названия и количества основных вкладок навигации в хедере")
    void headerNavigationTabsShouldHaveExactLabelsAndSize() {

        mainPage.openPage()
                .checkNavTabsSize(3)
                .checkNavTabsLabels("Учебник", "Приложение", "Сообщество");
    }

    @Test
    @Owner("dmitry_endo")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверка появления окошка с уведомлениями")
    void clickOnBellIconShouldOpenNotificationWindow() {

        mainPage.openPage()
                .notificationsWindowShouldNotBeVisible()
                .clickNotificationBell()
                .notificationsWindowShouldBeVisible();
    }

    @Test
    @Owner("dmitry_endo")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверка основных категорий на странице Потоки")
    void flowsPageShouldHaveExpectedCategories() {
        String title = "Потоки";
        String paragraph = "Все темы, о которых мы пишем в журнале";
        String[] categories = new String[]{"Инвестиции", "Путешествия", "Эмиграция", "Что делать?",
                "Дневники", "Россия", "Недвижимость", "Бизнес",
                "Правовая грамотность", "Спорт и фитнес", "Поп-культура", "Общество"};

        mainPage.openPage()
                .clickShowAll()
                .checkFlowsPageTitleAndParagraph(title, paragraph)
                .checkFlowsPageCategories(categories);
    }
}
