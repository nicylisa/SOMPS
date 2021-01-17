package com.ecnu.stldemo.elements.element2simulink;


import com.ecnu.stldemo.elements.abstracts.ExpressionOne;
import com.ecnu.stldemo.elements.element.Formula;
import com.ecnu.stldemo.elements.element.ParenExpression;
import com.ecnu.stldemo.elements.element.Perturbation;
import com.ecnu.stldemo.elements.element.Program;
import com.ecnu.stldemo.elements.element.expressionone.Eventually;
import com.ecnu.stldemo.elements.element.expressionone.Global;
import com.ecnu.stldemo.elements.element.expressionone.NotExpression;
import com.ecnu.stldemo.elements.element.expressiontwo.And;
import com.ecnu.stldemo.elements.element.expressiontwo.Imply;
import com.ecnu.stldemo.elements.element.expressiontwo.Or;
import com.ecnu.stldemo.elements.element.expressiontwo.Until;
import com.ecnu.stldemo.elements.element.predicates_Type.GEPredicate;
import com.ecnu.stldemo.elements.element.predicates_Type.GTPredicate;
import com.ecnu.stldemo.elements.element.predicates_Type.LEPredicate;
import com.ecnu.stldemo.elements.element.predicates_Type.LTPredicate;
import com.ecnu.stldemo.elements.visitor.STLElementVisitor;

/**
 * @author wangyao2221
 * @date 2020/10/28 14:33
 */
public class STLSimulinkVisitor implements STLElementVisitor {
    @Override
    public String visitProgram(Program program) {
        return null;
    }

    @Override
    public String visitFormula(Formula formula) {
        return null;
    }

    @Override
    public String visitImply(Imply imply) {
        return null;
    }

    @Override
    public String visitUtil(Until until) {
        return null;
    }

    @Override
    public String visitAnd(And and) {
        return null;
    }

    @Override
    public String visitUnaryExpression(ExpressionOne expressionOne) {
        return null;
    }

    @Override
    public String visitParens(ParenExpression parens) {
        return null;
    }

    @Override
    public String visitOr(Or or) {
        return null;
    }

    @Override
    public String visitEventually(Eventually eventually) {
        return null;
    }

    @Override
    public String visitGlobal(Global global) {
        return null;
    }

    @Override
    public String visitNot(NotExpression not) {
        return null;
    }

    @Override
    public String visitGT(GTPredicate gt) {
        return null;
    }

    @Override
    public String visitLT(LTPredicate lt) {
        return null;
    }

    @Override
    public String visitGE(GEPredicate ge) {
        return null;
    }

    @Override
    public String visitLE(LEPredicate le) {
        return null;
    }

    @Override
    public String visitPerturbation(Perturbation perturbation) {
        return null;
    }
}
