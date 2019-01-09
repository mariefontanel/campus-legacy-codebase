package com.gildedrose;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GildedRoseTest {

    @Test
    void shouldDecreaseItemQualityByOne() {
        Item item = new Item("toto", 5, 10);
        GildedRose gildedRose = new GildedRose( new Item[]{item} );
        gildedRose.updateQuality();
        assertThat(item.quality).isEqualTo(9);
    }

    @Test
    void shouldDecreaseItemSellInByOne() {
        Item item = new Item("toto", 5, 10);
        GildedRose gildedRose = new GildedRose( new Item[]{item} );
        gildedRose.updateQuality();
        assertThat(item.sellIn).isEqualTo(4);
    }

    @Test
    void qualityShouldNotBeNegative() {
        Item item = new Item("toto", 5, 0);
        GildedRose gildedRose = new GildedRose( new Item[]{item} );
        gildedRose.updateQuality();
        assertThat(item.quality).isEqualTo(0);
    }

    @Test
    void qualityShouldDecreaseDoubleWhenExpired() {
        Item item = new Item("toto", -1, 10);
        GildedRose gildedRose = new GildedRose( new Item[]{item} );
        gildedRose.updateQuality();
        assertThat(item.quality).isEqualTo(8);
    }

    @Test
    void shouldIncreaseQualityAgedBrie() {
        Item item = new Item("Aged Brie", 10, 10);
        Item item2 = new Item("Aged Brie", 1, 12);
        GildedRose gildedRose = new GildedRose( new Item[]{item, item2} );
        gildedRose.updateQuality();
        assertThat(item.quality).isEqualTo(11);
        assertThat(item2.quality).isEqualTo(13);
    }

    @Test
    void qualityShouldNeverBeMoreThanFifty() {
        Item item = new Item("Aged Brie", 10, 50);
        GildedRose gildedRose = new GildedRose( new Item[]{item} );
        gildedRose.updateQuality();
        assertThat(item.quality).isEqualTo(50);
    }

    @Test
    void shouldIncreaseTwiceAgedBrieQuality() {
        Item item = new Item("Aged Brie", -2, 30);
        GildedRose gildedRose = new GildedRose( new Item[]{item} );
        gildedRose.updateQuality();
        assertThat(item.quality).isEqualTo(32);
    }

    @Test
    void sulfuraShouldNeverChange() {
        Item item = new Item("Sulfuras, Hand of Ragnaros", 15, 30);
        GildedRose gildedRose = new GildedRose( new Item[]{item} );
        gildedRose.updateQuality();
        assertThat(item.quality).isEqualTo(30);
        assertThat(item.sellIn).isEqualTo(15);
    }

    @Test
    void backstageShouldIncreaseByOne() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 15, 30);
        Item item2 = new Item("Backstage passes to a TAFKAL80ETC concert", 11, 30);
        GildedRose gildedRose = new GildedRose( new Item[]{item, item2} );
        gildedRose.updateQuality();
        assertThat(item.quality).isEqualTo(31);
        assertThat(item2.quality).isEqualTo(31);
    }

    @Test
    void backstageShouldIncreaseByTwo() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 8, 30);
        Item item2 = new Item("Backstage passes to a TAFKAL80ETC concert", 6, 30);
        GildedRose gildedRose = new GildedRose( new Item[]{item, item2} );
        gildedRose.updateQuality();
        assertThat(item.quality).isEqualTo(32);
        assertThat(item2.quality).isEqualTo(32);
    }

    @Test
    void backstageShouldIncreaseByThree() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 30);
        Item item2 = new Item("Backstage passes to a TAFKAL80ETC concert", 1, 30);
        GildedRose gildedRose = new GildedRose( new Item[]{item, item2} );
        gildedRose.updateQuality();
        assertThat(item.quality).isEqualTo(33);
        assertThat(item2.quality).isEqualTo(33);
    }

    @Test
    void backstageQualityDropsToZeroWhenExpired() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 0, 30);
        Item item2 = new Item("Backstage passes to a TAFKAL80ETC concert", -1, 30);
        GildedRose gildedRose = new GildedRose( new Item[]{item, item2} );
        gildedRose.updateQuality();
        assertThat(item.quality).isEqualTo(0);
        assertThat(item2.quality).isEqualTo(0);
    }

    @Test
    void conjuredQualityDecreaseTwiceAsFastAsDefault() {
        Item item = new Item("Conjured Mana Cake", 10, 30);
        Item item2 = new Item("Conjured Mana Cake", 0, 30);
        Item item3 = new Item("Conjured Mana Cake", 1, 30);
        GildedRose gildedRose = new GildedRose( new Item[]{item, item2, item3} );
        gildedRose.updateQuality();
        assertThat(item.quality).isEqualTo(28);
        assertThat(item2.quality).isEqualTo(26);
        assertThat(item3.quality).isEqualTo(28);
    }

    @Test
    void conjuredThingsQualityDecreaseTwiceAsFastAsDefault() {
        Item item = new Item("Conjured things", 10, 30);
        Item item2 = new Item("Conjured Cake", 0, 30);
        Item item3 = new Item("Conjured a Cake", 1, 30);
        GildedRose gildedRose = new GildedRose( new Item[]{item, item2, item3} );
        gildedRose.updateQuality();
        assertThat(item.quality).isEqualTo(28);
        assertThat(item2.quality).isEqualTo(26);
        assertThat(item3.quality).isEqualTo(28);
    }

    @Test
    void agingRedWineQualityShouldBeStable() {
        Item item = new Item("Aging Red Wine", 10, 30);
        Item item2 = new Item("Aging Red Wine", 1, 30);
        GildedRose gildedRose = new GildedRose( new Item[]{item, item2} );
        gildedRose.updateQuality();
        assertThat(item.quality).isEqualTo(30);
        assertThat(item2.quality).isEqualTo(30);
    }

    @Test
    void agingRedWineQualityIncreaseByOneWhenExpired() {
        Item item = new Item("Aging Red Wine", 0, 30);
        Item item2 = new Item("Aging Red Wine", -99, 30);
        GildedRose gildedRose = new GildedRose( new Item[]{item, item2} );
        gildedRose.updateQuality();
        assertThat(item.quality).isEqualTo(31);
        assertThat(item2.quality).isEqualTo(31);
    }

    @Test
    void agingRedWineQualityShouldDecreaseByOneWhenSellInBelowMinorOneHundred(){
        Item item = new Item("Aging Red Wine", -100, 30);
        Item item2 = new Item("Aging Red Wine", -110, 30);
        GildedRose gildedRose = new GildedRose( new Item[]{item, item2} );
        gildedRose.updateQuality();
        assertThat(item.quality).isEqualTo(29);
        assertThat(item2.quality).isEqualTo(29);
    }
}