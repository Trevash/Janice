package com.bignerdranch.android.shared.models;

import com.bignerdranch.android.shared.models.colors.cardColorEnum;

public class trainCardModel {
    private cardColorEnum color;

    public trainCardModel(cardColorEnum color){
        this.color = color;
    }

    public cardColorEnum getColor() {
        return color;
    }
}
