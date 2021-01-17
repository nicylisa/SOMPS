package com.ecnu.stldemo.elements.element.predicates_Type;

import com.ecnu.stldemo.elements.abstracts.Predicates;

public class GEPredicate extends Predicates {
    /**
     * ">="
     * @param sigName
     * @param num
     */
    public GEPredicate(String sigName, double num) {
        super(sigName, num);
    }
}
