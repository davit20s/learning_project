package com.magento.task.objects.ui.pages.homepage;

import com.codeborne.selenide.SelenideElement;
import com.magento.task.objects.ui.controls.SelectWithDropdown;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.magento.task.objects.ui.controls.MenuWithElements;
import com.magento.task.framework.localization.var1_locme.LocMe;
import com.magento.task.objects.ui.pages.BasePage;

import static com.codeborne.selenide.Selenide.*;

@EqualsAndHashCode(callSuper = true)
@Data
public class HomePage extends BasePage {

    public final static String NAME = "Home page";

    private final SelenideElement pageId = $x("//a[@class='block-promo home-main']");

    private final MenuWithElements gearMenu = new MenuWithElements($x("//a[@id='ui-id-6']/.."));
    public LocMe GEAR_MENU_BAGS_MENU_ITEM;

    private final MenuWithElements menMenu = new MenuWithElements($x("//a[@id='ui-id-5']/.."));
    public LocMe MEN_MENU_TOPS_MENU_ITEM;
    public LocMe MEN_MENU_TOPS_MENU_JACKETS_MENU_ITEM;
    public LocMe MEN_MENU_BOTTOMS_MENU_ITEM;
    public LocMe MEN_MENU_BOTTOMS_MENU_PANTS_MENU_ITEM;


}