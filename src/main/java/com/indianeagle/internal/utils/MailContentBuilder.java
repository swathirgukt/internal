package com.indianeagle.internal.utils;

import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;


/**
 * Utility class to merge html mail template content to string
 * by evaluating dynamic values.
 *
 * @author Taymur
 * since: 13 Jan 2020 5:00 PM
 */
@Component
public class MailContentBuilder {
    public static String build(TemplateEngine templateEngine, String template, Map<String, Object> model) {
        Context context = new Context();
        for (Map.Entry<String, Object> entry : model.entrySet()) {
            context.setVariable(entry.getKey(), entry.getValue());
        }
        return templateEngine.process(template, context);
    }

}