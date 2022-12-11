package io.xuandanh.springframework.common.querydsl;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;

import java.lang.reflect.AnnotatedElement;

public class JsonPath<T> extends JsonExpression<T> implements Path<T> {
    public JsonPath(Path<T> mixin) {
        super(mixin);
    }

    public JsonPath(PathMetadata pathMetadata) {
        this((Path<T>) ExpressionUtils.path(Object.class, pathMetadata));
    }

    public PathMetadata getMetadata() {
        return ((Path)this.mixin).getMetadata();
    }

    public Path<?> getRoot() {
        return ((Path)this.mixin).getRoot();
    }

    public AnnotatedElement getAnnotatedElement() {
        return ((Path)this.mixin).getAnnotatedElement();
    }
}
