package com.ecnu.stldemo.elements.visitor;

import com.ecnu.stldemo.elements.abstracts.Expression;
import com.ecnu.stldemo.elements.element.Perturbation;
import com.ecnu.stldemo.gen.STLBaseVisitor;
import com.ecnu.stldemo.gen.STLParser;

public class PerturbationVisitor extends STLBaseVisitor<Perturbation> {
    @Override
    public Perturbation visitPerturbation_(STLParser.Perturbation_Context ctx) {
        String name = ctx.getChild(0).getText();
        double num = Double.parseDouble(ctx.getChild(2).getText());
        return new Perturbation(name,num);
    }
}
