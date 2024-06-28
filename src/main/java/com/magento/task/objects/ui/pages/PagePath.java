package com.magento.task.objects.ui.pages;

import lombok.Getter;

public enum PagePath {
    HOME(""),
    BAGSPAGE("/gear/bags.html"),
    JACKETSPAGE("/men/tops-men/jackets-men.html");

    @Getter
    private final String key;
    PagePath(String key) {
        this.key = key;
    }
}
