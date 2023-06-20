package model.statement;

import exceptions.ADTException;
import exceptions.ExpressionEvaluationException;
import exceptions.InterpreterException;
import model.expression.IExpression;
import model.programState.ProgramState;
import model.type.Type;
import model.utils.MyIDictionary;
import model.utils.MyIList;
import model.value.Value;

public class PrintStatement implements IStatement {
    IExpression expression;

    public PrintStatement(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws InterpreterException {
        expression.typeCheck(typeEnv);
        return typeEnv;
    }

    @Override
    public ProgramState execute(ProgramState state) throws ExpressionEvaluationException, ADTException, InterpreterException {
        MyIList<Value> out = state.getOut();
        out.add(expression.eval(state.getSymTable(), state.getHeap()));
        state.setOut(out);
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new PrintStatement(expression.deepCopy());
    }

    @Override
    public String toString() {
        return String.format("Print(%s)", expression.toString());
    }
}