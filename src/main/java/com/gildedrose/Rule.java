package com.gildedrose;

import com.google.common.collect.Range;

import java.util.function.Consumer;

public class Rule {
    public Range<Integer> range;
    public Consumer<Item> method;

    public Rule(Range<Integer> range, Consumer<Item> method) {
        this.range = range;
        this.method = method;
    }

}

