/*
 * Copyright (c) 2012 by Yana Software (P) Limited.
 *
 * All rights reserved. These materials are confidential and proprietary to Yana Software (P) Limited.
 *
 * No part of this code may be reproduced, published in any form by any means (electronic or mechanical, including photocopy or any information storage or retrieval system), nor may the materials be disclosed to third parties, or used in derivative works without the express written authorization of Yana Software (P) Limited.
 */

package com.indianeagle.internal.service.impl;

import com.indianeagle.internal.dao.repository.LeavesRepository;
import com.indianeagle.internal.dto.Leaves;
import com.indianeagle.internal.service.LeavesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: anish
 * Date: 5/29/12
 * Time: 4:07 PM
 */
@Service
public class LeavesServiceImpl implements LeavesService {
    @Autowired
    private LeavesRepository leavesRepository;

    @Override
    public Leaves findLeaveByEmployeeId(String employeeId) {

        if (leavesRepository.findLeavesByEmployeeId(employeeId).size() > 0) {
            return leavesRepository.findLeavesByEmployeeId(employeeId).get(0);
        } else {
            return new Leaves();
        }
    }

    public LeavesRepository getLeavesDao() {
        return leavesRepository;
    }

    public void setLeavesDao(LeavesRepository leavesDao) {
        this.leavesRepository = leavesDao;
    }
}
