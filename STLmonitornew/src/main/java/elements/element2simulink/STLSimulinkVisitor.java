package elements.element2simulink;

import elements.abstracts.ExpressionOne;
import elements.element.Formula;
import elements.element.ParenExpression;
import elements.element.Perturbation;
import elements.element.Program;
import elements.element.expressionone.Eventually;
import elements.element.expressionone.Global;
import elements.element.expressionone.NotExpression;
import elements.element.expressiontwo.And;
import elements.element.expressiontwo.Imply;
import elements.element.expressiontwo.Or;
import elements.element.expressiontwo.Until;
import elements.element.predicates_Type.GEPredicate;
import elements.element.predicates_Type.GTPredicate;
import elements.element.predicates_Type.LEPredicate;
import elements.element.predicates_Type.LTPredicate;
import elements.visitor.STLElementVisitor;

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
