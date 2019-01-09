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
            new Item("Conjured Mana Cake", 3, 6),
            new Item("Conjured Mana Cake", 3, 3),
            new Item("Conjured Mana Cake", 20, 10),
            // new items for new specification :
            new Item("Aging Red Wine", 5, 35),
            new Item("Aging Red Wine", 0, 20),
            new Item("Aging Red Wine", -102, 35),
            new Item("Aging Red Wine", -10, 30),
    };

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
        assertThat(app.items[12].name).isEqualTo("Aging Red Wine");
        assertThat(app.items[13].name).isEqualTo("Aging Red Wine");
        assertThat(app.items[14].name).isEqualTo("Aging Red Wine");
    }

    @Test
    void checkSellInt() {
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].sellIn).isEqualTo(9);
        assertThat(app.items[1].sellIn).isEqualTo(1);
        assertThat(app.items[2].sellIn).isEqualTo(-1);
        assertThat(app.items[12].quality).isEqualTo(35);
        assertThat(app.items[13].quality).isEqualTo(20);
        assertThat(app.items[14].quality).isEqualTo(34);
        for (int i = 0; i < 10; i++) {
            app.updateQuality();
        }
        assertThat(app.items[8].quality).isEqualTo(0);
        assertThat(app.items[15].quality).isEqualTo(41);
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
    @Test
    void VerifyIfItemSellIndecreaseUnderZero() {
        Item[] items = new Item[] { new Item("Foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].sellIn).isEqualTo(-1);
    }
    @Test
    void VerifyIfQualityItemIsNeverNegative() {
        Item[] items = new Item[] { new Item("Foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(0);
    }
    @Test
    void VerifyIfQualityItemIsNeverMoreFifty() {
        Item[] items = new Item[] { new Item("Foo", 14, 49) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(48);
    }
    @Test
    void VerifyIfConjuredItemDecreaseRightWhenSellInIsPositive() {
        Item[] items = new Item[] { new Item("Conjured", 3, 8) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(7);
    }
    @Test
    void VerifyIfConjuredItemDecreaseRightWhenSellInIsNegative() {
        Item[] items = new Item[] { new Item("Conjured", -1, 8) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].sellIn).isEqualTo(-2);
        assertThat(app.items[0].quality).isEqualTo(6);
    }
    @Test
    void VerifyIfItemNameWithConjuredNotAtStartIsSkipped() {
        Item[] items = new Item[] { new Item("Foo Conjured", -1, 8) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(6);
    }
    @Test
    void VerifyIfAgedBrieActuallyIncreasesInQualityTheOlderItGets() {
        Item[] items = new Item[] { new Item("Aged Brie", -1, 8) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(10);
    }
    @Test
    void VerifyIfBackstageQualityDecreaseCorrectlyDuringLastTenDays() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 10, 8) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(10);
    }
    @Test
    void VerifyIfBackstageQualityDecreaseCorrectlyDuringLastFiveDays() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 8) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(11);
    }
    @Test
    void VerifyIfBackstageQualityEqualsToZeroWhenSellInIsOver() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", -1, 8) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(0);
    }
    @Test
    void VerifyIfBackstageQualityUpCorrectlyWhenMoreThan10() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 15, 8) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(9);
    }
    @Test
    void VerifyIfAgingRedWineQualityNotChangeAboveZero() {
        Item[] items = new Item[] { new Item("Aging Red Wine", 15, 8) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(8);
    }
    @Test
    void VerifyIfAgingRedWineGainQualityUnderZero() {
        Item[] items = new Item[] { new Item("Aging Red Wine", -5, 8) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(9);
    }
    @Test
    void VerifyIfAgingRedWineGainQualityUnderMinus100() {
        Item[] items = new Item[] { new Item("Aging Red Wine", -120, 8) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(7);
    }
    @Test
    void VerifyIfExceptionProc() {
        Item[] items = new Item[] { new Item("Aging Red Wine", -117, -7) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(0);
    }
}