package com.gildedrose;

public class GildedRose {
    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String CONJURED = "Conjured Mana Cake";
    public static final String WINE = "Aging Red Wine";
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public Item[] getItems() {
        return items;
    }

    public void updateQuality() {
        for (Item item : items) {
            coreWork(item);
        }
    }

    private void coreWork(Item item) {
        if (item.name.equals(SULFURAS)) {
            return;
        }
        item.sellIn--;

        String name = item.name;
        if (item.name.startsWith("Conjured")) {
            name = CONJURED;
        }

        switch (name){
            case AGED_BRIE:
                item.agedBrieMethod();
                break;
            case BACKSTAGE:
                item.backstageMethod();
                break;
            case CONJURED:
                item.conjuredMethod();
                break;
            case WINE:
                item.agingRedWineMethod();
                break;
            default:
                item.defaultMethod();
        }
    }
}