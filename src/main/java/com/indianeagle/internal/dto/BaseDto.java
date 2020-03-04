/*
 * ---------------------------------------------------------------------------
 * COPYRIGHT NOTICE
 * Copyright (c) 2020 by Indianeagle(P) Limited.
 * All rights reserved. These materials are confidential and proprietary to
 * Indianeagle (P) Limited.  No part of this code may be reproduced, published
 * in any form by any means (electronic or mechanical, including photocopy or
 * any information storage or retrieval system), nor may the materials be
 * disclosed to third parties, or used in derivative works without the
 * express written authorization of Indianeagle (P) Limited.
 * ---------------------------------------------------------------------------
 */
package com.indianeagle.internal.dto;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public abstract class BaseDto implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date lastUpdated;
    private Integer version;

    /**
     * A default no argument constructor
     */
    protected BaseDto() {
    }
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * This method returns the date-timestamp of when the record was last updated
     *
     * @return the Date-Timestamp of the record
     */
    @Column(name = "LAST_UPDATED", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getLastUpdated() {
        return lastUpdated;
    }

    /**
     * This method sets the date-timestamp while updating/creating the record
     *
     * @param lastUpdated the date-time the record was created/updated
     */
    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    /**
     * This method returns the version number of the record and uses versioning implementation provided by Hibernate
     * to provide with Optimistic locking
     *
     * @return the version number of the record
     */
    @Column(name = "OPTLOCK_VERSION")
    @Version
    public Integer getVersion() {
        return version;
    }

    /**
     * This method sets the version number for the record and only the system should invoke this method
     *
     * @param version the version number of the record
     */
    public void setVersion(Integer version) {
        this.version = version;
    }
}
