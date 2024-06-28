package com.magento.task.steps.ui.jacketspage;

import com.magento.task.models.JacketModel;
import com.magento.task.objects.ui.pages.jacketspage.JacketCard;
import com.magento.task.objects.ui.pages.jacketspage.JacketsPage;
import com.magento.task.steps.ui.BaseStepsUi;
import com.magento.task.steps.ui.shared.messagesbar.MessagesBarSteps;
import io.qameta.allure.Step;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;
import static com.magento.task.objects.ui.pages.shared.messagesbar.MessageType.SUCCESS_MESSAGE;

@EqualsAndHashCode(callSuper = true)
@Data
public class JacketsSteps extends BaseStepsUi {
    private final JacketsPage page = new JacketsPage();
    @Step(JacketsPage.NAME + ": Wait for 'Jackets Place' page is loaded...")
    public JacketsSteps waitForPageToBeOpened() {
        $x(String.format(page.PRODUCTS_TITLE_TEXT_XPATH, page.TITLE_TEXT.get())).shouldBe(visible, Duration.of(10, ChronoUnit.SECONDS));
        return this;
    }
    @Step(JacketsPage.NAME + ": Add one of the jackets to your cart")
    public JacketsSteps addRandomJacketToCart() {
        JacketModel cardModel = page.getRandomJacketCardModel();
        clickOnAddToCard(cardModel)
                .waitForPageToBeOpened()
                .waitForLoadingIsCompleted();
        while (messagesBarPage.getMessageType() != SUCCESS_MESSAGE && headerBarPage.getCartItemsAmount() == 0){
            messagesBarSteps
                    .verifyErrorMessageTextIfAvailable();
            cardModel = navigationSteps
                    .navigateToJacketsPage()
                    .getPage()
                    .getRandomJacketCardModel();
            clickOnAddToCard(cardModel)
                    .waitForPageToBeOpened()
                    .waitForLoadingIsCompleted();
        }
        messagesBarSteps
                .verifySuccessMessageText(cardModel);
        int expectedCardItems = 1;
        headerBarSteps
                .verifyCartItemsAmount(expectedCardItems)
                .verifyCartItemsAmountIcon(expectedCardItems);
        return this;
    }

    @Step(JacketsPage.NAME + ": Click on jacket card - {jacketModel}")
    public MessagesBarSteps clickOnAddToCard (JacketModel jacketModel){
        JacketCard card = page.getCardFromModel(jacketModel);
        card.
                scrollTo()
                .clickOnRandomSize()
                .clickOnRandomColor()
                .clickOnAddToCard();
        return  messagesBarSteps;
    }
}
