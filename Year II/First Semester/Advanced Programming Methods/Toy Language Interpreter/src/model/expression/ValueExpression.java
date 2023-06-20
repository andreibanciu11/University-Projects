package model.expression;
import exceptions.InterpreterException;
import model.type.Type;
import model.utils.MyIDictionary;
import model.value.Value;
import model.utils.MyIHeap;

public class ValueExpression implements IExpression {
    Value value;

    public ValueExpression(Value value) {
        this.value = value;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> symTable, MyIHeap heap) {
        return this.value;
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws InterpreterException {
        return value.getType();
    }

    @Override
    public IExpression deepCopy() {
        return new ValueExpression(value);
    }

    @Override
    public String toString() {
        return this.value.toString();
    }
}