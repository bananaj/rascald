package com.bananaj.common.web;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Validator;

public abstract class MgrController {

    @Autowired
    protected Renderer renderer;

    @Autowired
    protected Validator validator;

    public Renderer getRenderer() {
        return renderer;
    }

    public void setRenderer(Renderer renderer) {
        this.renderer = renderer;
    }
}
