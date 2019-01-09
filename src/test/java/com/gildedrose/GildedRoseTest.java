package com.gildedrose;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class GildedRoseTest {
    private Item[] items = new Item[]{
            new Item("+5 Dexterity Vest", 10, 20), //
            new Item("Aged Brie", 2, 0), //
            new Item("Aged Brie", 0, 0), //
            new Item("Elixir of the Mongoose", 5, 7), //
            new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
            new Item("Sulfuras, Hand of Ragnaros", -1, 80),
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
            // this conjured item does not work properly yet
            new Item("Conjured Mana Cake", 3, 6),
            new Item("Conjured Mana Cake", 3, 3),
            new Item("Conjured Mana Cake", 20, 10)};

    @Test
    void checkName() {
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].name).isEqualTo("+5 Dexterity Vest");
        assertThat(app.items[1].name).isEqualTo("Aged Brie");
        assertThat(app.items[2].name).isNotEqualTo("Camembert");
        assertThat(app.items[3].name).isEqualTo("Elixir of the Mongoose");
        assertThat(app.items[4].name).isNotEqualTo("TOTO");
        assertThat(app.items[5].name).isEqualTo("Sulfuras, Hand of Ragnaros");
        assertThat(app.items[6].name).isEqualTo("Backstage passes to a TAFKAL80ETC concert");
        assertThat(app.items[7].name).isEqualTo("Backstage passes to a TAFKAL80ETC concert");
        assertThat(app.items[8].name).isEqualTo("Backstage passes to a TAFKAL80ETC concert");
    }

    @Test
    void checkSellInt() {
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].sellIn).isEqualTo(9);
        assertThat(app.items[1].sellIn).isEqualTo(1);
        assertThat(app.items[2].sellIn).isEqualTo(-1);
        for (int i = 0; i < 10; i++) {
            app.updateQuality();
        }
        assertThat(app.items[8].quality).isEqualTo(0);
    }

    @Test
    void checkQuality() {
        GildedRose app = new GildedRose(items);
        for (int i = 0; i <= 32; i++) {
            app.updateQuality();
        }
        //Less is 0
        assertThat(app.items[0].quality).isEqualTo(0);
        //max is 50
        assertThat(app.items[1].quality).isEqualTo(50);
        //Legendary never move
        assertThat(app.items[5].quality).isEqualTo(80);
    }

}