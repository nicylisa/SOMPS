package com.ecnu.stldemo.elements.element;


import com.ecnu.stldemo.elements.abstracts.Expression;

public class ParenExpression extends Expression {
    Expression expr;

    public ParenExpression(Expression expr) {
        this.expr = expr;
    }

    public Expression getExpr() {
        return expr;
    }
}
