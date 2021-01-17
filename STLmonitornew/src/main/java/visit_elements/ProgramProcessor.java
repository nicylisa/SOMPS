package visit_elements;

import elements.abstracts.Expression;
import elements.abstracts.ExpressionOne;
import elements.element.Formula;
import elements.element.ParenExpression;
import elements.element.Perturbation;
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

        String ans = "";
        ans+="new_system(‘"+formula.getName()+"’)\n";//create blank simulink model
        ans+="open_system(‘"+formula.getName()+"’)\n";//open model
        ans+="add_block('simulink/Commonly Used Blocks/Subsystem',"+formula.getName()+")\n";//can set position
        ans+= getEvalResult(formula.getExpression());
        ans+="save_system('"+formula.getName()+"')\n";//save model file
        return ans;

    }
    private String getEvalResult(Expression expression) {
        /**formula.getName();
         * Expression expression = formula.getExpression();
         **/
        /**
         * Question
         * model name port name
         *
         */
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
            { //typeI
                ans+="%%add input--signal PredicateGE\n";
                ans+="add_block('simulink/Commonly Used Blocks/Constant','"+formula.getName()+"/"+"modelname"+")";
                ans+="set_param('"+formula.getName()+"/"+"modelname , "+((GEPredicate) expression).num+"')\n";
                ans+="add_block('simulink/Commonly Used Blocks/Constant','"+formula.getName()+"/"+"modelname"+")";
                ans+="set_param('"+formula.getName()+"/"+"modelname , "+perturbation.get(((GEPredicate) expression).sigName)+"')\n";
                ans+="add_block('simulink/Ports & Subsystems/In1', ' modelName /" +((GEPredicate) expression).sigName + "/OutRes')\n";
                ans+="add_block('simulink/Math Operations/Add','targetname')";
                ans+="set_param('targetname', 'Inputs', '+--')\n";
                ans+="add_line('modelname','in1/1','add/1')\n";
                ans+="add_line('modelname','constant1exp/1','add/2')\n";
                ans+="add_line('modelname','constant1pert/1','add/3')\n";
            }
            else
            {
                //typeII
                ans+="%%add input--signal PredicateGT\n";
                ans+="add_block('simulink/Commonly Used Blocks/Constant','"+formula.getName()+"/"+"modelname"+")";
                ans+="set_param('"+formula.getName()+"/"+"modelname , "+((GEPredicate) expression).num+"')\n";
                ans+="add_block('simulink/Commonly Used Blocks/Constant','"+formula.getName()+"/"+"modelname"+")";
                ans+="set_param('"+formula.getName()+"/"+"modelname , "+perturbation.get(((GEPredicate) expression).sigName)+"')\n";
                ans+="add_block('simulink/Ports & Subsystems/In1', 'modelName + /" +((GEPredicate) expression).sigName + "/OutRes')\n";
                ans+="add_block('simulink/Math Operations/Add','targetname')";
                ans+="set_param('targetname', 'Inputs', '+-+')\n";
                ans+="add_line('modelname','in1/1','add/1')\n";
                ans+="add_line('modelname','constant1exp/1','add/2')\n";
                ans+="add_line('modelname','constant1pert/1','add/3')\n";

            }

        } else if (expression instanceof GTPredicate) {
            if(perturbation.get(((GTPredicate) expression).sigName)>=0)
            {
                //typeI
                ans+="%%add input--signal PredicateGT\n";
                ans+="add_block('simulink/Commonly Used Blocks/Constant','"+formula.getName()+"/"+"modelname"+")";
                ans+="set_param('"+formula.getName()+"/"+"modelname , "+((GTPredicate) expression).num+"')\n";
                ans+="add_block('simulink/Commonly Used Blocks/Constant','"+formula.getName()+"/"+"modelname"+")";
                ans+="set_param('"+formula.getName()+"/"+"modelname , "+perturbation.get(((GTPredicate) expression).sigName)+"')\n";
                ans+="add_block('simulink/Ports & Subsystems/In1', '.modelName + " +((GTPredicate) expression).sigName + "/OutRes')\n";
                ans+="add_block('simulink/Math Operations/Add','targetname')";
                ans+="set_param('targetname', 'Inputs', '+--')\n";
                ans+="add_line('modelname','in1/1','add/1')\n";
                ans+="add_line('modelname','constant1exp/1','add/2')\n";
                ans+="add_line('modelname','constant1pert/1','add/3')\n";
            }
            else
            {
                //typeII
                ans+="%%add input--signal PredicateGT\n";
                ans+="add_block('simulink/Commonly Used Blocks/Constant','"+formula.getName()+"/"+"modelname"+")";
                ans+="set_param('"+formula.getName()+"/"+"modelname , "+((GTPredicate) expression).num+"')\n";
                ans+="add_block('simulink/Commonly Used Blocks/Constant','"+formula.getName()+"/"+"modelname"+")";
                ans+="set_param('"+formula.getName()+"/"+"modelname , "+perturbation.get(((GTPredicate) expression).sigName)+"')\n";
                ans+="add_block('simulink/Ports & Subsystems/In1', '.modelName + /" +((GTPredicate) expression).sigName + "OutRes')\n";
                ans+="add_block('simulink/Math Operations/Add','targetname')";
                ans+="set_param('targetname', 'Inputs', '+-+')\n";
                ans+="add_line('modelname','in1/1','add/1')\n";
                ans+="add_line('modelname','constant1exp/1','add/2')\n";
                ans+="add_line('modelname','constant1pert/1','add/3')\n";
            }
        } else if (expression instanceof LEPredicate) {
            if(perturbation.get(((LEPredicate) expression).sigName)>=0)
            {
                //typeII
                ans+="%%add input--signal PredicateLE\n";
                ans+="add_block('simulink/Commonly Used Blocks/Constant','"+formula.getName()+"/"+"modelname"+")";
                ans+="set_param('"+formula.getName()+"/"+"modelname , "+((LEPredicate) expression).num+"')\n";
                ans+="add_block('simulink/Commonly Used Blocks/Constant','"+formula.getName()+"/"+"modelname"+")";
                ans+="set_param('"+formula.getName()+"/"+"modelname , "+perturbation.get(((LEPredicate) expression).sigName)+"')\n";
                ans+="add_block('simulink/Ports & Subsystems/In1', 'modelName + /" +((LEPredicate) expression).sigName + "/OutRes')\n";
                ans+="add_block('simulink/Math Operations/Add','targetname')";
                ans+="set_param('targetname', 'Inputs', '-++')\n";
                ans+="add_line('modelname','in1/1','add/1')\n";
                ans+="add_line('modelname','constant1exp/1','add/2')\n";
                ans+="add_line('modelname','constant1pert/1','add/3')\n";
            }
            else
            {
                //typeI
                ans+="%%add input--signal PredicateLE\n";
                ans+="add_block('simulink/Commonly Used Blocks/Constant','"+formula.getName()+"/"+"modelname"+")";
                ans+="set_param('"+formula.getName()+"/"+"modelname , "+((LEPredicate) expression).num+"')\n";
                ans+="add_block('simulink/Commonly Used Blocks/Constant','"+formula.getName()+"/"+"modelname"+")";
                ans+="set_param('"+formula.getName()+"/"+"modelname , "+perturbation.get(((LEPredicate) expression).sigName)+"')\n";
                ans+="add_block('simulink/Ports & Subsystems/In1', 'modelName + /" +((LEPredicate) expression).sigName + "/OutRes')\n";
                ans+="add_block('simulink/Math Operations/Add','targetname')";
                ans+="set_param('targetname', 'Inputs', '-+-')\n";
                ans+="add_line('modelname','in1/1','add/1')\n";
                ans+="add_line('modelname','constant1exp/1','add/2')\n";
                ans+="add_line('modelname','constant1pert/1','add/3')\n";

            }


        } else if (expression instanceof LTPredicate) {
            if(perturbation.get(((LTPredicate) expression).sigName)>=0)
            {
                //typeII
                ans+="%%add input--signal PredicateLT\n";
                ans+="add_block('simulink/Commonly Used Blocks/Constant','"+formula.getName()+"/"+"modelname"+")";
                ans+="set_param('"+formula.getName()+"/"+"modelname , "+((LTPredicate) expression).num+"')\n";
                ans+="add_block('simulink/Commonly Used Blocks/Constant','"+formula.getName()+"/"+"modelname"+")";
                ans+="set_param('"+formula.getName()+"/"+"modelname , "+perturbation.get(((LTPredicate) expression).sigName)+"')\n";
                ans+="add_block('simulink/Ports & Subsystems/In1', 'modelName + /" +((LTPredicate) expression).sigName + "/OutRes')\n";
                ans+="add_block('simulink/Math Operations/Add','targetname')";
                ans+="set_param('targetname', 'Inputs', '-++')\n";
                ans+="add_line('modelname','in1/1','add/1')\n";
                ans+="add_line('modelname','constant1exp/1','add/2')\n";
                ans+="add_line('modelname','constant1pert/1','add/3')\n";
            }
            else
            {
                //typeI
                ans+="%%add input--signal PredicateLT\n";
                ans+="add_block('simulink/Commonly Used Blocks/Constant','"+formula.getName()+"/"+"modelname"+")";
                ans+="set_param('"+formula.getName()+"/"+"modelname , "+((LTPredicate) expression).num+"')\n";
                ans+="add_block('simulink/Commonly Used Blocks/Constant','"+formula.getName()+"/"+"modelname"+")";
                ans+="set_param('"+formula.getName()+"/"+"modelname , "+perturbation.get(((LTPredicate) expression).sigName)+"')\n";
                ans+="add_block('simulink/Ports & Subsystems/In1', 'modelName + /" +((LTPredicate) expression).sigName + "/OutRes')\n";
                ans+="add_block('simulink/Math Operations/Add','targetname')";
                ans+="set_param('targetname', 'Inputs', '-+-')\n";
                ans+="add_line('modelname','in1/1','add/1')\n";
                ans+="add_line('modelname','constant1exp/1','add/2')\n";
                ans+="add_line('modelname','constant1pert/1','add/3')\n";
            }
        }
        return ans;
    }

}
