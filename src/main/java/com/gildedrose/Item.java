package com.gildedrose;

public class Item {

    String name;
    int sellIn;
    int quality;

    /**
     * constructor
     *
     * @param name
     * @param sellIn
     * @param quality
     */
    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    /**
     * isConjured ?
     * if name start with 'Conjured', return true
     *
     * @return boolean
     */
    boolean isConjured() {
        return this.name.startsWith("Conjured");
    }

    /**
     * isLegendary ?
     * if name equals 'Sulfuras, Hand of Ragnarso, return true
     *
     * @return boolean
     */
    boolean isLegendary() {
        return this.name.equals("Sulfuras, Hand of Ragnaros");
    }

    /**
     * isAPass ?
     * if name start with 'Backstage passes', return true
     *
     * @return boolean
     */
    boolean isAPass() {
        return this.name.startsWith("Backstage passes");
    }

    /**
     * isCheese ?
     * if name equals "Aged Brie", return true
     *
     * @return boolean
     */
    boolean isCheese() {
        return this.name.equals("Aged Brie");
    }

    /**
     * isSoldOut ?
     * if sellIn in less than 0, return true
     *
     * @return
     */
    boolean isSoldOut() {
        return this.sellIn < 0;
    }

    @Override
    public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }
}