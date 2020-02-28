/*
 * ---------------------------------------------------------------------------
 * COPYRIGHT NOTICE
 * Copyright (c) 2011 by Yana Software (P) Limited.
 * All rights reserved. These materials are confidential and proprietary to
 * Yana Software (P) Limited.  No part of this code may be reproduced, published
 * in any form by any means (electronic or mechanical, including photocopy or
 * any information storage or retrieval system), nor may the materials be
 * disclosed to third parties, or used in derivative works without the
 * express written authorization of Yana Software (P) Limited.
 * ---------------------------------------------------------------------------
 */
package com.indianeagle.internal.enums;

/**
 * This enum contains template names and path
 *
 * @author krishna
 * @since Mar 1, 2012
 */
public enum TemplateNames {

    MAIL_TEMPLATE_PATH("mail/"),

    IE_Payslip,
    form16Pdf;

    private String path;

    /**
     * Default Constructor
     */
    TemplateNames() {
    }

    /**
     * Parameter Constructor
     */
    TemplateNames(String path) {
        this.path = path;
    }

    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }
}
