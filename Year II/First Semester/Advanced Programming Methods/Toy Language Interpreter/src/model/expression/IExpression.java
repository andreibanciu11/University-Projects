package model.expression;

import exceptions.ADTException;
import exceptions.ExpressionEvaluationException;
import exceptions.InterpreterException;
import model.type.Type;
import model.utils.MyIDictionary;
import model.value.Value;
import model.utils.MyIHeap;

public interface IExpression {
    Value eval(MyIDictionary<String, Value> symTable, MyIHeap heap) throws ExpressionEvaluationException, ADTException, InterpreterException;
    IExpression deepCopy();

    Type typeCheck(MyIDictionary<String, Type> typeEnv) throws InterpreterException;
}
