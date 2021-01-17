package com.ecnu.stldemo.visit_elements;

import com.ecnu.stldemo.elements.abstracts.Expression;
import com.ecnu.stldemo.elements.abstracts.ExpressionOne;
import com.ecnu.stldemo.elements.element.Formula;
import com.ecnu.stldemo.elements.element.ParenExpression;
import com.ecnu.stldemo.elements.element.Perturbation;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProgramProcessor {
    Formula formula;
    List<Perturbation> perts;
    public Map<String, Double> perturbation;//存储变量

    public ProgramProcessor(Formula formula, List<Perturbation> perts) {
        this.formula = formula;
        this.perts = perts;

        perturbation = new HashMap<String, Double>();
        for (Perturbation pert : perts) {
            perturbation.put(pert.sigName.replace("_pert", ""), pert.num);
        }
    }

    public String generateMatlab()
    {
        return getEvalResult(formula.getExpression());
    }
    private String getEvalResult(Expression expression) {
        /**formula.getName();
        Expression expression = formula.getExpression();**/
        String ans="";
        if (expression instanceof Eventually) {
            /**
             * ans中已经含有的是subsystem Phi
             * phi和F对应的块相连
             */
            double timeDif = ((Eventually) expression).getEnd()-((Eventually) expression).getStart();
            double start = ((Eventually) expression).getStart();
            ans+="addline(Eventually)";
            ans+="add_block('DSP System Toolbox/Statistics/Moving Maximum', '目标位置')\n";
            ans+="set_param('Test/Test_Exists/随机号', 'Window length', timeDif)\n";
            ans+= "add_block('simulink/Discrete/Unit Delay', 'Test文件名/Test_Exists/-1208916169Delay块名')\n";
            ans+="set_param('Test/Test_Exists/随机号', 'X0', start)\n";
            ans=getEvalResult(((Eventually) expression).getExpression())+ans;
        }
        else if(expression instanceof ParenExpression)
        {
            ans+="Paren\n";
            ans=getEvalResult(((ParenExpression)expression).getExpr());
        }
        else if (expression instanceof Global) {
            double timeDif = ((Global) expression).getEnd()-((Global) expression).getStart();
            double start = ((Global) expression).getStart();
            ans+="addline(Global)\n";
            ans+="add_block('DSP System Toolbox/Statistics/Moving Minimum', '目标位置')\n";
            ans+="set_param('Test/Test_Exists/随机号', 'Window length', timeDif)\n";
            ans+= "add_block('simulink/Discrete/Unit Delay', 'Test文件名/Test_Exists/-1208916169Delay块名')\n";
            ans+="set_param('Test/Test_Exists/随机号', 'X0', start)\n";
            ans=getEvalResult(((Global) expression).getExpression())+ans;
        } else if (expression instanceof NotExpression) {
            ans+="NOT\n";
            ans+="add_block('simulink/Math Operations/Gain', '模块名')\n";
            ans+="set_param('模块名', 'Gain', '-1')\n";
            ans="addline(Not)\n"+ ans;
            ans=getEvalResult(((NotExpression) expression).getExpression())+ans;
        }
        /***
         * 表达式A OR 表达式B
         * 要求表达式A的ansA，表达式B的ansB
         * 再连接
         * ansA和ansB得到的块作为新加block的输入（需要考虑）
         */
        else if (expression instanceof Until) {
            /**
             * 过于复杂，暂时空着
             */
        } else if (expression instanceof Imply) {
            /**
             * Left --> Right 等价于 not Left or Right
             */


        } else if (expression instanceof And) {
            And and = (And) expression;
            String ansA = getEvalResult(and.getLeft());
            String ansB = getEvalResult(and.getRight());
            ans = ansA;
            ans+="And\n";
            ans+="add_block('simulink/Math Operations/MinMax', 'Test/Test_Exists/-1208916169')\n" +
                    "set_param('Test/Test_Exists/-1208916169', 'Function', 'min')\n";
            ans+=ansB;
        } else if (expression instanceof Or) {
            Or or = (Or) expression;
            String ansA = getEvalResult(or.getLeft());
            String ansB = getEvalResult(or.getRight());
            ans = ansA;
            ans+="Or\n";
            ans+="add_block('simulink/Math Operations/MinMax', 'Test/Test_Exists/-1208916169')\n" +
                    "set_param('Test/Test_Exists/-1208916169', 'Function', 'max')\n";
            ans+=ansB;
        } else if (expression instanceof GEPredicate) {
            if(perturbation.get(((GEPredicate) expression).sigName)>=0)
            {
                ans+="add input--signal PredicateGE\n";
                ans+="add constant signalVal,set param=expression.num\n";
                ans+="add constant pert,set param=perturbation.get(((GEPredicate) expression).sigName)\n";
                ans+="add line to add_block\n";
                ans+="add_block('simulink/Math Operations/Add','targetname')\n"+
                "set_param('targetname', 'Inputs', '+--')\n";
            }
            else
            {
                ans+="add input--signalGE\n";
                ans+="add constant signalVal,set param=expression.num\n";
                ans+="add constant pert,set param=perturbation.get(((GEPredicate) expression).sigName)\n";
                ans+="add line to add_block\n";
                ans+="add_block('simulink/Math Operations/Add','targetname')\n"+
                        "set_param('targetname', 'Inputs', '+-+')\n";

            }

        } else if (expression instanceof GTPredicate) {
            if(perturbation.get(((GTPredicate) expression).sigName)>=0)
            {
                ans+="add input--signal PredicateGT\n";
                ans+="add constant signalVal,set param=expression.num\n";
                ans+="add constant pert,set param=perturbation.get(((GEPredicate) expression).sigName)\n";
                ans+="add line to add_block\n";
                ans+="add_block('simulink/Math Operations/Add','targetname')\n"+
                        "set_param('targetname', 'Inputs', '+--')\n";
            }
            else
            {
                ans+="add input--signal PredicateGT\n";
                ans+="add constant signalVal,set param=expression.num\n";
                ans+="add constant pert,set param=perturbation.get(((GEPredicate) expression).sigName)\n";
                ans+="add line to add_block\n";
                ans+="add_block('simulink/Math Operations/Add','targetname')\n"+
                        "set_param('targetname', 'Inputs', '+-+')\n";

            }
        } else if (expression instanceof LEPredicate) {
            if(perturbation.get(((LEPredicate) expression).sigName)>=0)
            {
                ans+="add input--signal PredicateLE\n";
                ans+="add constant signalVal,set param=expression.num\n";
                ans+="add constant pert,set param=perturbation.get(((GEPredicate) expression).sigName)\n";
                ans+="add line to add_block\n";
                ans+="add_block('simulink/Math Operations/Add','targetname')\n"+
                        "set_param('targetname', 'Inputs', '-+-')\n";
            }
            else
            {
                ans+="add input--signal PredicateLE\n";
                ans+="add constant signalVal,set param=expression.num\n";
                ans+="add constant pert,set param=perturbation.get(((GEPredicate) expression).sigName)\n";
                ans+="add line to add_block\n";
                ans+="add_block('simulink/Math Operations/Add','targetname')\n"+
                        "set_param('targetname', 'Inputs', '-++')\n";

            }


        } else if (expression instanceof LTPredicate) {
            if(perturbation.get(((LTPredicate) expression).sigName)>=0)
            {
                ans+="add input--signal PredicateLT\n";
                ans+="add constant signalVal,set param=expression.num\n";
                ans+="add constant pert,set param=perturbation.get(((GEPredicate) expression).sigName)\n";
                ans+="add line to add_block\n";
                ans+="add_block('simulink/Math Operations/Add','targetname')\n"+
                        "set_param('targetname', 'Inputs', '-+-')\n";
            }
            else
            {
                ans+="add input--signal PredicateLT\n";
                ans+="add constant signalVal,set param=expression.num\n";
                ans+="add constant pert,set param=perturbation.get(((GEPredicate) expression).sigName)\n";
                ans+="add line to add_block\n";
                ans+="add_block('simulink/Math Operations/Add','targetname')\n"+
                        "set_param('targetname', 'Inputs', '-++')\n";

            }
        }
        return ans;
    }

}
