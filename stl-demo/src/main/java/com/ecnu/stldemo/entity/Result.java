package com.ecnu.stldemo.entity;

public class Result {
    String answer;
    String astDot;
    public Result(String answer,String astDot){
        this.answer = answer;
        this.astDot = astDot;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAstDot() {
        return astDot;
    }

    public void setAstDot(String astDot) {
        this.astDot = astDot;
    }
}
