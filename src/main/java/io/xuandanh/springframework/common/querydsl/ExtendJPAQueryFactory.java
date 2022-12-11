package io.xuandanh.springframework.common.querydsl;

import com.querydsl.core.types.EntityPath;
import com.querydsl.jpa.JPQLTemplates;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.function.Supplier;

public class ExtendJPAQueryFactory extends JPAQueryFactory {
    private final JPQLTemplates templates;
    private final Supplier<EntityManager> entityManager;

    public ExtendJPAQueryFactory(EntityManager entityManager) {
        super(entityManager);
        this.templates = null;
        this.entityManager = () -> {
            return entityManager;
        };
    }

    public ExtendJPAQueryFactory(JPQLTemplates templates, EntityManager entityManager) {
        super(templates, entityManager);
        this.templates = templates;
        this.entityManager = () -> {
            return entityManager;
        };
    }

    public ExtendJPAQueryFactory(Supplier<EntityManager> entityManager) {
        super((EntityManager) entityManager);
        this.templates = null;
        this.entityManager = entityManager;
    }

    public ExtendJPAQueryFactory(JPQLTemplates templates, Supplier<EntityManager> entityManager) {
        super(templates, (EntityManager) entityManager);
        this.templates = templates;
        this.entityManager = entityManager;
    }

    public ExtendJpaUpdateClause updateJson(EntityPath<?> path) {
        return this.templates != null ? new ExtendJpaUpdateClause((EntityManager)this.entityManager.get(), path, this.templates) : new ExtendJpaUpdateClause((EntityManager)this.entityManager.get(), path);
    }
}
