package com.ecnu.stldemo.elements.element.expressiontwo;

import com.ecnu.stldemo.elements.abstracts.Expression;
import com.ecnu.stldemo.elements.abstracts.IntervalTwo;

public class Until extends IntervalTwo {
    public Until(Expression left, Expression right, double start, double end) {
        super(left, right, start, end);
    }
}
