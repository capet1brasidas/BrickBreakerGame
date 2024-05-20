package com.jga.util.entity.script;

public interface EntityScript<T> {

    void added(T entity);

    void removed(T removed);

    void update(float delta);
    boolean isFinished();

}
