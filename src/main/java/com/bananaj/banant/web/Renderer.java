package com.bananaj.banant.web;

import java.util.Map;

public interface Renderer {

    public String renderTemplate(String templateName);

    public String renderTemplate(String templateName, Map<String, Object> context);

    public Map<String, Object> getBaseContext();

}
