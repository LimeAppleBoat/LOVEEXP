package com.jab125.limeappleboat.loveexp.util;

import org.jetbrains.annotations.Nullable;

public class LoveExpFormat {
    private int exp;
    private int gold;

    public LoveExpFormat() {
        this.exp = 0;
        this.gold = 0;
    }
    public LoveExpFormat(int exp, int gold) {
        this.exp = exp;
        this.gold = gold;
    }

    public LoveExpFormat(int exp) {
        this.exp = exp;
        this.gold = 0;
    }

    public int getExp() {
        return exp;
    }

    public int getGold() {
        return gold;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }
}
