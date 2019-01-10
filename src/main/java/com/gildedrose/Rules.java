package com.gildedrose;

import java.util.Arrays;
import java.util.List;

public class Rules {
    List<Rule> theRules;

    public Rules(Rule... theRules) {
        this.theRules = Arrays.asList(theRules);
    }

    public void apply(Item item) {
        for (Rule rule : theRules) {
         if (rule.range.contains(item.sellIn) ) {
             rule.method.accept(item);
         }
        }
    }
}
