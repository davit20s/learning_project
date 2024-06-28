package com.magento.task.objects.ui.pages.jacketspage;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.magento.task.models.BagModel;
import com.magento.task.models.JacketModel;
import com.magento.task.models.MoneyModel;
import lombok.Getter;

import java.time.Duration;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.interactable;

@Getter
public class JacketCard {

    private final String NOT_AVAILABLE = "<not available>"; // in case some control is not available

    private final SelenideElement card;
    private final SelenideElement image;
    private final SelenideElement title;
    private final SelenideElement rating;
    private final SelenideElement price;
    private final ElementsCollection size;
    private final ElementsCollection color;
    private final SelenideElement addToCardButton;

    public JacketCard(SelenideElement card) {
        this.card = card;
        this.image = this.getCard().$x(".//span[@class='product-image-container']//img");
        this.title = this.getCard().$x(".//a[@class='product-item-link']");
        this.rating = this.getCard().$x(".//div[@class='rating-summary']//*[@class='rating-result']/span/span");
        this.price = this.getCard().$x(".//*[@data-role='priceBox']//*[@class='price']");
//        this.size = this.getCard().$x(".//div[@class='swatch-attribute size']//*[@class='swatch-attribute-options clearfix']");
        this.size = this.getCard().$$x(".//div[contains(@id,'option-label-size-')]");
        this.color = this.getCard().$$x(".//div[@class='swatch-attribute color']//*[@class='swatch-attribute-options clearfix']");
        this.addToCardButton = this.getCard().$x(".//div[@class='actions-primary']//button[@type='submit']/span");
    }

    public JacketModel getModel() {
        this.scrollTo();
        return JacketModel
                .builder()
                .title(this.getTitle().getText())
                .rating(getRatingAmount())
                .price(MoneyModel.parse(this.getPrice().getText()))
                .build();
    }

    public Double getRatingAmount() {
        double rating = 0.0;
        if (getRating().isDisplayed()) {
            rating = Double.parseDouble(getRating().getText().replace("%", ""));
        }
        return rating;
    }

    public JacketCard scrollTo() {
        this
                .getImage()
                .scrollTo();
        return this;
    }

    public JacketCard clickOnCard() {
        return click(this
                .scrollTo()
                .getImage());
    }

    public JacketCard clickOnRandomSize() {
        this
                .scrollTo()
                .getCard()
                .hover(); // to show buttons
        ElementsCollection sizesList = this.getSize();
        return click(sizesList.get(0));
    }

    public JacketCard clickOnRandomColor() {
        this
                .scrollTo()
                .getCard()
                .hover(); // to show buttons
        ElementsCollection colorsList = this.getColor();
        return click(colorsList.get(0));
    }

    public JacketCard clickOnAddToCard() {
        this
                .scrollTo()
                .getCard()
                .hover(); // to show buttons
        return click(this.getAddToCardButton());
    }

    private JacketCard click(SelenideElement element) {
        element
                .shouldBe(interactable, Duration.ofSeconds(5))
                .shouldBe(enabled, Duration.ofSeconds(5))
                .click();
        return this;
    }
}
