package io.xuandanh.springframework.common.querydsl;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Operation;
import com.querydsl.core.types.Operator;

import java.util.List;

public class JsonOperation<T> extends JsonExpression<T> implements Operation<T> {
    private final Operation<T> operation;

    public JsonOperation(Operation<T> operation) {
        super(operation);
        this.operation = operation;
    }

    public Expression<?> getArg(int index) {
        return this.operation.getArg(index);
    }

    public List<Expression<?>> getArgs() {
        return this.operation.getArgs();
    }

    public Operator getOperator() {
        return this.operation.getOperator();
    }
}
