package io.xuandanh.springframework.common.querydsl;

import com.querydsl.core.types.Operator;

public enum CommonOps implements Operator {
    CAST(Object.class);

    private final Class<?> type;

    private CommonOps(Class type) {
        this.type = type;
    }

    public Class<?> getType() {
        return this.type;
    }
}
