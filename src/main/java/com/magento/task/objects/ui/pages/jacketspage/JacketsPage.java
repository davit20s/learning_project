package com.magento.task.objects.ui.pages.jacketspage;

import com.codeborne.selenide.ElementsCollection;
import com.magento.task.framework.localization.var1_locme.LocMe;
import com.magento.task.framework.utils.NumberUtils;
import com.magento.task.models.JacketModel;
import com.magento.task.objects.ui.pages.BasePage;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static com.codeborne.selenide.Selenide.$$x;

@EqualsAndHashCode(callSuper = true)
@Data
public class JacketsPage extends BasePage {
    public static final String NAME = "Jackets page";

    public LocMe TITLE_TEXT;
    public LocMe YOU_ADDED_TO_YOUR_SHOPPING_CART_MESSAGE;

    public ElementsCollection getCards() {
        // card blocks
        return $$x("//ol[contains(@class, 'product-items')]//li//div[contains(@data-container,'product-grid')]");
    }

    public int getRandomCardIndex() {
        return NumberUtils.getRandomNumber(0, getCardsAmount() - 1);
    }

    public JacketCard getCardModelByIndex(int index){
        return new JacketCard(getCards().get(index));
    }

    public JacketCard getRandomJacketCard(){
        int cardIndex = getRandomCardIndex();
        return getCardModelByIndex(cardIndex);
    }

    public JacketModel getRandomJacketCardModel(){
        JacketCard card = getRandomJacketCard();
        return card.getModel();
    }

    public List<JacketCard> getCardsModelList(){
        List<JacketCard> cards = new ArrayList<>();
        for (int i =0; i< getCards().size();i++){
            cards.add(getCardModelByIndex(i));
        }
        return cards;
    }

    public int getCardsAmount(){
        return getCardsModelList().size();
    }

    public JacketCard getCardFromModel (JacketModel jacketModel){
        List<JacketCard> jacketCards =
                getCardsModelList()
                        .stream()
                        .filter(c->c.getTitle().getText().equalsIgnoreCase(jacketModel.getTitle().trim()))
                        .toList();
        return jacketCards
                .stream()
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Unable to find card element for model: " + jacketModel));
    }

}
