package com.bananaj.common.web;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.sitemesh.content.tagrules.decorate.DecoratorTagRuleBundle;
import org.sitemesh.content.tagrules.html.*;

/**
 * Created by cio on 10/02/15.
 */
public class MgrSiteMeshFilter extends ConfigurableSiteMeshFilter {


    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {

        builder.addDecoratorPath("/page/*", "/decorator.html");
        builder.addTagRuleBundle(new DivExtractingTagRuleBundle());
        builder.addTagRuleBundle(new CoreHtmlTagRuleBundle());
        builder.addTagRuleBundle(new DecoratorTagRuleBundle());

    }


}