package model.expression;

import exceptions.ADTException;
import exceptions.InterpreterException;
import model.type.Type;
import model.utils.MyIDictionary;
import model.value.Value;
import model.utils.MyIHeap;

public class VariableExpression implements IExpression {
    String key;

    public VariableExpression(String key) {
        this.key = key;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> symTable, MyIHeap heap) throws ADTException {
        return symTable.lookUp(key);
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws InterpreterException {
        try {
            return typeEnv.lookUp(key);
        } catch (ADTException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public IExpression deepCopy() {
        return new VariableExpression(key);
    }

    @Override
    public String toString() {
        return key;
    }
}