package com.gildedrose;

import static com.google.common.collect.Range.*;
import static com.google.common.collect.Range.atLeast;

public class RuleSet {

    public static final Rules DEFAULT_RULES =
            new Rules(
                    new Rule(
                            atLeast(0),
                            item -> item.decreaseQuality(1)),
                    new Rule(
                            atMost(-1),
                            item -> item.decreaseQuality(2)));
    public static final Rules AGED_BRIE_RULES =
            new Rules(
                    new Rule(
                            atLeast(0),
                            item -> item.increaseQuality(1)),
                    new Rule(
                            atMost(-1),
                            item -> item.increaseQuality(2)));
    public static final Rules BACKSTAGE_RULES =
            new Rules(
                    new Rule(
                            atLeast(10),
                            item -> item.increaseQuality(1)),
                    new Rule(
                            closed(5,9),
                            item -> item.increaseQuality(2)),
                    new Rule(
                            closed(0,4),
                            item -> item.increaseQuality(3)),
                    new Rule(
                            atMost(-1),
                            item -> item.decreaseQuality(item.quality)));
    public static final Rules CONJURED_RULES =
            new Rules(
                    new Rule(
                            atLeast(0),
                            item -> item.decreaseQuality(2)),
                    new Rule(
                            atMost(-1),
                            item -> item.decreaseQuality(4)));
    public static final Rules WINE_RULES =
            new Rules(
                    new Rule(
                            atMost(-101),
                            item -> item.decreaseQuality(1)),
                    new Rule(
                            closed(-100, -1),
                            item -> item.increaseQuality(1)),
                    new Rule(
                            atLeast(0),
                            item -> item.decreaseQuality(0)));


}
