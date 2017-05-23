package com.framgia.forder.screen.managerdetail;

import android.support.annotation.IntDef;

import static com.framgia.forder.screen.managerdetail.ManagerStatusCode.ACTIVE_CODE;
import static com.framgia.forder.screen.managerdetail.ManagerStatusCode.BLOCKED_CODE;
import static com.framgia.forder.screen.managerdetail.ManagerStatusCode.WAIT_CODE;

/**
 * Created by levutantuan on 5/23/17.
 */
@IntDef({ WAIT_CODE, ACTIVE_CODE, BLOCKED_CODE })
public @interface ManagerStatusCode {
    int WAIT_CODE = 0;
    int ACTIVE_CODE = 1;
    int BLOCKED_CODE = 2;
}
