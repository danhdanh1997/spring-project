package io.xuandanh.springframework.common.querydsl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.NullNode;
import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Ops;
import com.querydsl.core.types.Visitor;
import com.querydsl.core.types.dsl.*;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.jpa.TypedParameterValue;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JsonExpression<T> extends SimpleExpression<T> {
    private transient volatile BooleanExpression isnull;
    private transient volatile BooleanExpression isnotnull;

    public JsonExpression(Expression<T> mixin) {
        super(mixin);
    }

    public <R, C> @Nullable R accept(Visitor<R, C> v, @Nullable C context) {
        return this.mixin.accept(v, context);
    }

    public BooleanExpression containsKey(Expression<String> key) {
        return Expressions.booleanOperation(JsonOps.CONTAINS_KEY, new Expression[]{this.mixin, key});
    }

    public BooleanExpression containsKey(String key) {
        return this.containsKey(Expressions.constant(key));
    }

    public BooleanExpression contains(JsonExpression<?> object) {
        return Expressions.booleanOperation(JsonOps.CONTAINS, new Expression[]{this.mixin, object});
    }

    public BooleanExpression contains(Object object) {
        return this.contains(this.jsonbConstant(object));
    }

    public JsonOperation<JsonNode> get(final Expression<?> key) {
        return new JsonOperation<JsonNode>(Expressions.operation(JsonNode.class, JsonOps.GET, new Expression[]{this.mixin, key})) {
            public StringExpression asText() {
                return Expressions.stringOperation(JsonOps.GET_TEXT, new Expression[]{JsonExpression.this.mixin, key});
            }
        };
    }

    public JsonExpression<JsonNode> get(Integer index) {
        return this.get(Expressions.constant(index));
    }

    public JsonExpression<JsonNode> get(String... key) {
        String[] keys = (String[]) Arrays.stream(((String)Arrays.stream(key).collect(Collectors.joining("."))).split("\\.")).toArray((x$0) -> {
            return new String[x$0];
        });
        return this.get(this.arrayConstant(keys));
    }

    public StringExpression asText() {
        throw new UnsupportedOperationException();
    }

    public <A extends Number & Comparable<? super A>> NumberExpression<A> asNumber(Class<A> type) {
        return Expressions.numberOperation(type, Ops.NUMCAST, new Expression[]{this.mixin, ConstantImpl.create(type)});
    }

    public NumberExpression<Integer> asInteger() {
        return this.asNumber(Integer.class);
    }

    public BooleanExpression asBoolean() {
        return Expressions.predicate(CommonOps.CAST, new Expression[]{this.mixin, ConstantImpl.create("boolean")});
    }

    public NumberExpression<Long> asLong() {
        return this.asNumber(Long.class);
    }

    public JsonOperation<ArrayNode> concat(JsonExpression<?> other) {
        return new JsonOperation(Expressions.operation(ArrayNode.class, JsonOps.CONCAT, new Expression[]{this.mixin, other}));
    }

    public <A> JsonOperation<ArrayNode> concat(A... other) {
        return this.concat(this.jsonbConstant(List.of(other)));
    }

    public <A> JsonOperation<ArrayNode> concat(List<A> other) {
        return this.concat(this.jsonbConstant(other));
    }

    public final NumberExpression<Integer> size() {
        return Expressions.numberOperation(Integer.class, JsonOps.MAP_SIZE, new Expression[]{this.mixin});
    }

    public final StringExpression keys() {
        return Expressions.stringOperation(JsonOps.KEYS, new Expression[]{this.mixin});
    }

    public final JsonOperation<JsonNode> elements() {
        return new JsonOperation(Expressions.operation(JsonNode.class, JsonOps.ELEMENTS, new Expression[]{this.mixin}));
    }

    public BooleanExpression isNull() {
        if (this.isnull == null) {
            this.isnull = Expressions.anyOf(new BooleanExpression[]{super.isNull(), this.eq((T) NullNode.instance)});
        }

        return this.isnull;
    }

    public BooleanExpression isNotNull() {
        if (this.isnotnull == null) {
            this.isnotnull = Expressions.allOf(new BooleanExpression[]{super.isNotNull(), this.ne((T) NullNode.instance)});
        }

        return this.isnotnull;
    }

    public JsonExpression<?> jsonbConstant(Object object) {
        return new JsonExpression(Expressions.constant(new TypedParameterValue(JsonBinaryType.INSTANCE, object)));
    }

    public Expression<TypedParameterValue> arrayConstant(String... keys) {
        return Expressions.constant(new TypedParameterValue(StringArrayType.INSTANCE, keys));
    }
}