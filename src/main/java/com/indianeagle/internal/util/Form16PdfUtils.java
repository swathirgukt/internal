package com.indianeagle.internal.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.commons.lang.StringUtils;
import org.thymeleaf.TemplateEngine;

import java.io.OutputStream;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The util class for Form16 Generation
 *
 * User: kalesha
 * Date: 8/16/2017
 */
public class Form16PdfUtils {

    public static void generateForm16Pdf(TemplateEngine templateEngine, OutputStream arrayOutputStream, String templateLocation, Object... dataObjects) {
        List<Element> elementList = null;
        Document document = new Document(PageSize.A4.rotate(), 20, 20, 50, 50);
        try {
            /* PDF Write copy every Element added to this Document will be written to the outputStream. */
            PdfWriter writer = PdfWriter.getInstance(document, arrayOutputStream);
            document.open();
            Map<String, Object> dataMap = new HashMap<String, Object>();
            for (Object data : dataObjects) {
                dataMap.put(StringUtils.uncapitalize(data.getClass().getSimpleName()), data);
            }
            dataMap.put(DateFormatter.class.getSimpleName(), DateFormatter.class);
            dataMap.put(Form16Utils.class.getSimpleName(), Form16Utils.class);
            String htmlFileContent = MailContentBuilder.build(templateEngine, templateLocation, dataMap);
            elementList = HTMLWorker.parseToList(new StringReader(htmlFileContent), null);
            for (Element element : elementList) {
                document.add(element);
            }
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
