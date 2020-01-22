/*
 * Copyright (c) 2012 by Yana Software (P) Limited.
 *
 * All rights reserved. These materials are confidential and proprietary to Yana Software (P) Limited.
 *
 * No part of this code may be reproduced, published in any form by any means (electronic or mechanical, including photocopy or any information storage or retrieval system), nor may the materials be disclosed to third parties, or used in derivative works without the express written authorization of Yana Software (P) Limited.
 */

package com.indianeagle.internal.dao;

import com.indianeagle.internal.dto.Leaves;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Jpa Respository for Leaves
 *
 * @author : Praveen
 * Date: 22/01/2020
 */
@Repository
public interface LeavesRepository extends JpaRepository<Leaves, Long> {


    /**
     * This method to find all employee Leaves
     *
     * @return Leaves
     */
    @Query("select l from Leaves l where l.employee.relieveDate is null OR l.employee.resignDate is null")
    List<Leaves> findAllEmployeeLeaves();


}
