package com.bananaj.banant.web;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class TcmController {

    @Autowired
    protected Renderer renderer;

    public Renderer getRenderer() {
        return renderer;
    }

    public void setRenderer(Renderer renderer) {
        this.renderer = renderer;
    }
}
