package com.jga.util.entity.script;

public abstract class EntityScriptBase<T> implements EntityScript<T>{
    //attributes
    private boolean finished=false;
    protected T entity;

    //public methods


    @Override
    public void added(T entity) {
        this.entity=entity;

    }

    @Override
    public void removed(T removed) {
        this.entity=null;
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public boolean isFinished() {
        return finished;
    }

    protected void finish(){
        finished=true;
    }
}
