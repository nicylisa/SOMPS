package elements.abstracts;
// 二元运算符 抽象类
public abstract class ExpressionTwo extends Expression{
    Expression left;
    Expression right;

    public ExpressionTwo(Expression left,Expression right){
        this.left = left;
        this.right = right;
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }
}
