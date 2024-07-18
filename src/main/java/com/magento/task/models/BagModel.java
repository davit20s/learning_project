package com.magento.task.models;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class BagModel extends ItemModel {

    private String title;
    private Double rating;
    private MoneyModel price;

}
