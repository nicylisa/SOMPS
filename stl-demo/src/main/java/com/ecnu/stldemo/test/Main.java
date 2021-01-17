package com.ecnu.stldemo.test;


import com.ecnu.stldemo.elements.element.Program;
import com.ecnu.stldemo.elements.visitor.AntlrtoProgram;
import com.ecnu.stldemo.gen.STLLexer;
import com.ecnu.stldemo.gen.STLParser;
import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import com.ecnu.stldemo.visit_elements.ProgramProcessor;

import javax.imageio.ImageIO;
import javax.print.PrintException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, PrintException {
        String text = "werr = G_[1,2] ((s(t)>1 OR F_[0.2,3] r(t)<=-0.2))\ns(t)_pert = 0.9\n r(t)_pert = 2";
        CodePointCharStream inputStream = CharStreams.fromString(text);
        STLLexer lexer = new STLLexer(inputStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        STLParser parser = new STLParser(tokenStream);

        ParseTree parseTree = parser.prog();

        AntlrtoProgram visitor = new AntlrtoProgram();
        Program program = visitor.visit(parseTree);

        ProgramProcessor processor = new ProgramProcessor(program.getFormula(), program.getPerts());
        String result = processor.generateMatlab();
        //show AST in console
        //System.out.println(parseTree.toStringTree(parser));
        List list = Arrays.asList(parser.getRuleNames());
        TreeViewer viewer = new TreeViewer(Arrays.asList(
                parser.getRuleNames()),parseTree);
        //viewer.getSize().width  viewer.getSize().height

        viewer.open();
        BufferedImage bi = new BufferedImage(300, 400, 2);
        Graphics g = bi.createGraphics();
        viewer.paint(g);
        g.dispose();
        File file = new File("antlr_tree.png");
        ImageIO.write(bi, "png", file);

    }

}
