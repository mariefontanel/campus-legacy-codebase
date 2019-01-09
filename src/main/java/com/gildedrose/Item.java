package com.gildedrose;

public class Item {

    public String name;

    public int sellIn;

    public int quality;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    @Override
    public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }

    public void decreaseQuality() {
        if  (this.quality > 0) {
            this.quality--;
        }
    }

    public void increaseQuality() {
        if (this.quality < 50) {
            this.quality++;
        }
    }

    public void defaultMethod() {
        this.decreaseQuality();
        if (this.sellIn < 0) {
            this.decreaseQuality();
        }
    }

    public void agedBrieMethod() {
        this.increaseQuality();
        if (this.sellIn < 0) {
            this.increaseQuality();
        }
    }

    public void backstageMethod() {
        if (this.sellIn < 0 ) {
            this.quality = 0;
            return;
        }
        this.increaseQuality();
        if (this.sellIn < 10) {
            this.increaseQuality();
        }
        if (this.sellIn < 5) {
            this.increaseQuality();
        }
    }

    public void conjuredMethod() {
        this.defaultMethod();
        this.defaultMethod();
    }

    public void agingRedWineMethod() {
        if (this.sellIn < 0 && this.sellIn >= -100) {
            this.increaseQuality();
        }
        if (this.sellIn < -100) {
            this.decreaseQuality();
        }
    }
}