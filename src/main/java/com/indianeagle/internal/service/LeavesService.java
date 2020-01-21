/*
 * Copyright (c) 2012 by Yana Software (P) Limited.
 *
 * All rights reserved. These materials are confidential and proprietary to Yana Software (P) Limited.
 *
 * No part of this code may be reproduced, published in any form by any means (electronic or mechanical, including photocopy or any information storage or retrieval system), nor may the materials be disclosed to third parties, or used in derivative works without the express written authorization of Yana Software (P) Limited.
 */

package com.indianeagle.internal.facade;

import com.yana.internal.dto.Leaves;

/**
 * User: anish
 * Date: 5/29/12
 * Time: 4:06 PM
 */
public interface LeavesService {

    Leaves findLeaveByEmployeeId(String employeeId);

}
