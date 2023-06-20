package model.statement;

import exceptions.ADTException;
import exceptions.ExpressionEvaluationException;
import exceptions.InterpreterException;
import exceptions.StatementExecutionException;
import model.programState.ProgramState;
import model.type.Type;
import model.utils.MyIDictionary;

public interface IStatement {
    ProgramState execute(ProgramState state) throws StatementExecutionException, ExpressionEvaluationException, ADTException, InterpreterException;
    IStatement deepCopy();

    MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws InterpreterException, ADTException;

}