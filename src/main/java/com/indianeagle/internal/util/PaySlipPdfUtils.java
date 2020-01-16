package com.indianeagle.internal.util;

import com.indianeagle.internal.constants.StringConstants;
import com.indianeagle.internal.dto.Employee;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.commons.lang.StringUtils;
import org.thymeleaf.TemplateEngine;

import java.io.OutputStream;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The util class for paySlip generation
 * User: anish
 * Date: 3/26/14
 * Time: 10:06 PM
 */
public class PaySlipPdfUtils {

    /**
     * The method to generate the pdf document from vm file
     * @param templateEngine
     * @param arrayOutputStream
     * @param templateLocation
     * @param dataObjects
     */
    public static void generatePaySlipPdf(TemplateEngine templateEngine, OutputStream arrayOutputStream, String templateLocation, Object... dataObjects){
        List<Element> elementList = null;
    	Document document = new Document(PageSize.A4.rotate(), 20, 20, 50, 50);
    	try {
            /* PDF Write copy every Element added to this Document will be written to the outputStream. */
            PdfWriter writer = PdfWriter.getInstance(document, arrayOutputStream);
            Employee employee = null;
            for (Object data : dataObjects) {
                if(data instanceof Employee){
                     employee = (Employee)data;
                }
            }
            if(null != employee){
                writer.setEncryption(employee.getEmpId().getBytes(), employee.getEmpId().getBytes(), PdfWriter.AllowPrinting, PdfWriter.ENCRYPTION_AES_128);
                writer.createXmpMetadata();
            }
            document.open();
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put(DateFormatter.class.getSimpleName(), DateFormatter.class);
            dataMap.put(DateUtils.class.getSimpleName(), DateUtils.class);
            dataMap.put(StringConstants.class.getSimpleName(), StringConstants.class);
            dataMap.put(BigDecimal.class.getSimpleName(), BigDecimal.class);
            dataMap.put("hrMailId", StringConstants.HR_MAIL_ID);
            dataMap.put("companyName", StringConstants.COMPANY_NAME);
            for (Object data : dataObjects) {
                dataMap.put(StringUtils.uncapitalize(data.getClass().getSimpleName()), data);
            }
            String htmlFileContent = MailContentBuilder.build(templateEngine, templateLocation, dataMap);
            elementList = HTMLWorker.parseToList(new StringReader(htmlFileContent), null);
            for (Element element : elementList) {
                document.add(element);
            }
            document.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
