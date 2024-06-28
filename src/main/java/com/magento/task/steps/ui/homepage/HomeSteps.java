package com.magento.task.steps.ui.homepage;

import com.magento.task.objects.ui.pages.homepage.HomePage;
import com.magento.task.steps.ui.BaseStepsUi;
import com.magento.task.steps.ui.bagspage.BagsSteps;
import com.magento.task.steps.ui.jacketspage.JacketsSteps;
import io.qameta.allure.Step;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;

@EqualsAndHashCode(callSuper = true)
@Data
public class HomeSteps extends BaseStepsUi {
    private final HomePage page = new HomePage();

    @Step(HomePage.NAME + ": Wait for page to be opened ...")
    public HomeSteps waitForPageToBeOpened() {
        page.getPageId().shouldBe(visible, Duration.of(10, ChronoUnit.SECONDS));
        return this;
    }
    @Step(HomePage.NAME + ": Navigate to Bags page")
    public BagsSteps navigateToBagsPage() {
        page.getGearMenu().selectMenuItemByText(page.GEAR_MENU_BAGS_MENU_ITEM.get());
        return bagsSteps.waitForPageToBeOpened();
    }


//    @Step(HomePage.NAME + ": Navigate to Men page")
//    public void navigateToMenPage(){
//        page.getMenMenu().selectMenuItemByText(page.MEN_MENU_TOPS_MENU_ITEM.get());
//        page.getTopMenMenu().selectMenuItemByText(page.MEN_MENU_TOPS_MENU_JACKETS_MENU_ITEM.get());
//        //return bagsSteps.waitForPageToBeOpened();
//    }

    @Step(HomePage.NAME + ": Navigate to Men page")
    public JacketsSteps navigateToJackets() {
        page.getMenMenu().selectNestedMenuItem(page.MEN_MENU_TOPS_MENU_ITEM.get(), page.MEN_MENU_TOPS_MENU_JACKETS_MENU_ITEM.get());
        return jacketsSteps.waitForPageToBeOpened();
    }

//    @Step(HomePage.NAME + ": Navigate to Men page")
//    public void navigateToPants() {
//        LinkedList<String> strings = new LinkedList<>(List.of(
//                page.MEN_MENU_BOTTOMS_MENU_ITEM.get(),
//                page.MEN_MENU_BOTTOMS_MENU_PANTS_MENU_ITEM.get()
//        ));
//        page.getMenMenu().selectNestedMenuItem(strings);
//    }


}