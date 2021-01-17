package com.ecnu.stldemo.controller;

import com.ecnu.stldemo.elements.element.Program;
import com.ecnu.stldemo.elements.visitor.AntlrtoProgram;
import com.ecnu.stldemo.entity.Result;
import com.ecnu.stldemo.gen.STLLexer;
import com.ecnu.stldemo.gen.STLParser;
import com.ecnu.stldemo.visit_elements.ProgramProcessor;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class STLController {
    @RequestMapping("/stlreq")
    public Result stlreq(String type,String text){
        /*type: 真值结果 鲁棒性
         *text：公式Formula
         */
        CodePointCharStream inputStream = CharStreams.fromString(text);
        STLLexer lexer = new STLLexer(inputStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        STLParser parser = new STLParser(tokenStream);
        ParseTree parseTree = parser.prog();



        AntlrtoProgram visitor = new AntlrtoProgram();
        Program program = visitor.visit(parseTree);

        ProgramProcessor processor = new ProgramProcessor(program.getFormula(), program.getPerts());
        // 生成脚本
        String answer = processor.generateMatlab();
        // 生成Dot文件
        String dot = "";

        // 创建Result对象
        Result result = new Result(answer,dot);
        return result;
    }
}
