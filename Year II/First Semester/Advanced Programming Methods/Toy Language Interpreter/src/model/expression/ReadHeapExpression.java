package model.expression;

import exceptions.ADTException;
import exceptions.ExpressionEvaluationException;
import exceptions.InterpreterException;
import model.type.RefType;
import model.type.Type;
import model.utils.MyIDictionary;
import model.utils.MyIHeap;
import model.value.RefValue;
import model.value.Value;

public class ReadHeapExpression implements IExpression{
    private final IExpression expression;

    public ReadHeapExpression(IExpression expression) {
        this.expression = expression;
    }
    @Override
    public Value eval(MyIDictionary<String, Value> symTable, MyIHeap heap) throws ExpressionEvaluationException, ADTException, InterpreterException {
        Value value = expression.eval(symTable, heap);
        if (!(value instanceof RefValue))
            throw new ExpressionEvaluationException(String.format("%s not of RefType", value));
        RefValue refValue = (RefValue) value;
        return heap.get(refValue.getAddress());
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws InterpreterException {
        Type type = expression.typeCheck(typeEnv);
        if (type instanceof RefType refType) {
            return refType.getInner();
        } else
            throw new InterpreterException("The rH argument is not a RefType.");
    }

    @Override
    public IExpression deepCopy() {
        return new ReadHeapExpression(expression.deepCopy());
    }

    @Override
    public String toString() {
        return String.format("ReadHeap(%s)", expression);
    }
}