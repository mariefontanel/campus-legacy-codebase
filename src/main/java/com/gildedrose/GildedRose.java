package com.gildedrose;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.gildedrose.RuleSet.*;

public class GildedRose {
    // Item types
    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String CONJURED = "Conjured Mana Cake";
    public static final String WINE = "Aging Red Wine";

    Item[] items;

    private Logger logger = LoggerFactory.getLogger(GildedRose.class);

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public Item[] getItems() {
        return items;
    }

    public void updateQuality() {
        for (Item item : items) {
            int oldQuality = item.quality;
            int oldSellIn = item.sellIn;
            coreWork(item);
            generateLogs(oldQuality, oldSellIn, item);
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

        selectRules(name).apply(item);
    }

    private Rules selectRules(String name) {
        switch (name){
            case AGED_BRIE:
                return AGED_BRIE_RULES;
            case BACKSTAGE:
                return BACKSTAGE_RULES;
            case CONJURED:
                return CONJURED_RULES;
            case WINE:
                return WINE_RULES;
            default:
                return DEFAULT_RULES;
        }
    }

    private void generateLogs(int oldQuality, int oldSellIn, Item item) {
        logger.info("Name is : " + item.name + "\n" +
                "Old quality was : " + oldQuality + "\n" +
                "Old sellIn was : " + oldSellIn + "\n" +
                "New quality is : " + item.quality + "\n" +
                "new sellIn is : " + item.sellIn);
    }
}
