package io.xuandanh.springframework.common.querydsl;

import com.querydsl.core.JoinType;
import com.querydsl.core.dml.UpdateClause;
import com.querydsl.core.support.QueryMixin;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAQueryMixin;
import com.querydsl.jpa.JPQLSerializer;
import com.querydsl.jpa.JPQLTemplates;
import com.querydsl.jpa.impl.JPAProvider;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExtendJpaUpdateClause implements UpdateClause<ExtendJpaUpdateClause> {
    private final QueryMixin<?> queryMixin;
    private final Map<Path<?>, Expression<?>> updates;
    private final EntityManager entityManager;
    private final JPQLTemplates templates;
    private LockModeType lockMode;

    public ExtendJpaUpdateClause(EntityManager em, EntityPath<?> entity) {
        this(em, entity, JPAProvider.getTemplates(em));
    }

    public ExtendJpaUpdateClause(EntityManager em, EntityPath<?> entity, JPQLTemplates templates) {
        this.queryMixin = new JPAQueryMixin();
        this.updates = new LinkedHashMap();
        this.entityManager = em;
        this.templates = templates;
        this.queryMixin.addJoin(JoinType.DEFAULT, entity);
    }

    public long execute() {
        JPQLSerializer serializer = new JPQLSerializer(this.templates, this.entityManager);
        serializer.serializeForUpdate(this.queryMixin.getMetadata(), this.updates);
        Query query = this.entityManager.createQuery(serializer.toString());
        if (this.lockMode != null) {
            query.setLockMode(this.lockMode);
        }

        //JPAUtil.setConstants(query, serializer.getConstants(), this.queryMixin.getMetadata().getParams());
        return (long)query.executeUpdate();
    }

    public <T> ExtendJpaUpdateClause set(Path<T> path, T value) {
        if (value != null) {
            this.updates.put(path, Expressions.constant(value));
        } else {
            this.setNull(path);
        }

        return this;
    }

    public <T> ExtendJpaUpdateClause set(Path<T> path, Expression<? extends T> expression) {
        if (expression != null) {
            this.updates.put(path, expression);
        } else {
            this.setNull(path);
        }

        return this;
    }

    public ExtendJpaUpdateClause set(JsonPath<?> path, Expression<?> expression) {
        if (expression != null) {
            this.updates.put(path, expression);
        } else {
            this.setNull(path);
        }

        return this;
    }

    public <T> ExtendJpaUpdateClause setNull(Path<T> path) {
        this.updates.put(path, Expressions.nullExpression(path));
        return this;
    }

    public ExtendJpaUpdateClause set(List<? extends Path<?>> paths, List<?> values) {
        for(int i = 0; i < paths.size(); ++i) {
            if (values.get(i) != null) {
                this.updates.put((Path)paths.get(i), Expressions.constant(values.get(i)));
            } else {
                this.updates.put((Path)paths.get(i), Expressions.nullExpression((Path)paths.get(i)));
            }
        }

        return this;
    }

    public ExtendJpaUpdateClause where(Predicate... o) {
        Predicate[] var2 = o;
        int var3 = o.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            Predicate p = var2[var4];
            this.queryMixin.where(p);
        }

        return this;
    }

    public ExtendJpaUpdateClause setLockMode(LockModeType lockMode) {
        this.lockMode = lockMode;
        return this;
    }

    public String toString() {
        JPQLSerializer serializer = new JPQLSerializer(this.templates, this.entityManager);
        serializer.serializeForUpdate(this.queryMixin.getMetadata(), this.updates);
        return serializer.toString();
    }

    public boolean isEmpty() {
        return this.updates.isEmpty();
    }
}
