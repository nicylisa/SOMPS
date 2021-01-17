package test;


import elements.element.Program;
import elements.visitor.AntlrtoProgram;
import gen.STLLexer;
import gen.STLParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import visit_elements.ProgramProcessor;

public class Main {
    public static void main(String[] args) {

        CodePointCharStream inputStream = CharStreams.fromString("werr = G_[1,2] ((s(t)>1 OR F_[0.2,3] r(t)<=-0.2))\ns(t)_pert = 0.9\n" +
                "r(t)_pert = 2");
        STLLexer lexer = new STLLexer(inputStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        STLParser parser = new STLParser(tokenStream);
        ParseTree parseTree = parser.prog();
       // System.out.println();

        AntlrtoProgram visitor = new AntlrtoProgram();
        Program program = visitor.visit(parseTree);
       if(visitor.getSemanticErrors().isEmpty())
       {
           ProgramProcessor processor = new ProgramProcessor(program.getFormula(), program.getPerts());
           System.out.println(processor.generateMatlab());
       }
       else
       {
           for (String semanticError : visitor.getSemanticErrors()) {
               System.err.println(semanticError);
           }
       }
    }
}
