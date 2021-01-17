package elements.visitor;

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

/**
 * Visitor to visit STL expression elements(Not expression context generated by antlr)
 * @author wangyao2221
 * @date 2020/10/28 14:21
 */
public interface STLElementVisitor {
    String visitProgram(Program program);
    String visitFormula(Formula formula);
    String visitImply(Imply imply);
    String visitUtil(Until until);
    String visitAnd(And and);
    String visitUnaryExpression(ExpressionOne expressionOne);
    String visitParens(ParenExpression parens);
    String visitOr(Or or);
    String visitEventually(Eventually eventually);
    String visitGlobal(Global global);
    String visitNot(NotExpression not);
    String visitGT(GTPredicate gt);
    String visitLT(LTPredicate lt);
    String visitGE(GEPredicate ge);
    String visitLE(LEPredicate le);
    String visitPerturbation(Perturbation perturbation);
}