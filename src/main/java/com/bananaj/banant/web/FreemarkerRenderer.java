package com.bananaj.banant.web;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class FreemarkerRenderer implements Renderer {

    private String templateSuffix = ".ftl";

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    private Configuration configuration;

    private Map<String, Object> baseObjects = new HashMap<String, Object>();

    public Map<String, Object> getBaseObjects() {
        return baseObjects;
    }

    public void setBaseObjects(Map<String, Object> baseObjects) {
        this.baseObjects = baseObjects;
    }

    public FreemarkerRenderer() throws IOException {

    }

    @Override
    public String renderTemplate(String templateName) {
        return renderTemplate(templateName, getBaseContext());
    }

    public String renderTemplate(String templateName, Map<String, Object> context) {
        String returnValue = "";
        try {
            Template template = configuration.getTemplate(templateName + getTemplateSuffix());

            Writer out = new StringWriter();
            template.process(context, out);
            returnValue = ((StringWriter)out).toString();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return returnValue;
    }

    @Override
    public Map<String, Object> getBaseContext() {
        Map<String, Object> returnValue = new HashMap<String, Object>();
        returnValue.putAll(getBaseObjects());
        return returnValue;
    }

    public String getTemplateSuffix() {
        return templateSuffix;
    }

    public void setTemplateSuffix(String templateSuffix) {
        this.templateSuffix = templateSuffix;
    }
}
