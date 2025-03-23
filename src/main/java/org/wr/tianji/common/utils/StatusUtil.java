package org.wr.tianji.common.utils;

import org.wr.tianji.common.constant.StatusConstant;

public class StatusUtil {
    public static String convertStatus(int status) {
        if (status == 100) {
            return StatusConstant.TOBECONFIRMING;
        } else if (status == 101) {
            return StatusConstant.IDENTIFIED;
        } else if (status == 102) {
            return StatusConstant.PEDNDING;
        } else if (status == 103) {
            return StatusConstant.TOBECONFIRMED;
        } else if (status == 104) {
            return StatusConstant.OVERRULED;
        } else if (status == 200) {
            return StatusConstant.PROCESSED;
        } else {
            return StatusConstant.DISMISSED;
        }
    }
}
