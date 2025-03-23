package org.wr.tianji.common.constant;

import java.util.Map;

public class StatusConstant {
    public static final String TOBECONFIRMING = "工单录入";
    public static final String IDENTIFIED = "待确认风险";
    public static final String PEDNDING= "待处置风险";
    public static final String TOBECONFIRMED = "待确认处置结果";
    public static final String OVERRULED = "驳回风险";
    public static final String PROCESSED = "已处置";
    public static final String DISMISSED = "已驳回";

    public static final Map<String, Integer> STATUSMAP = Map.of(
            TOBECONFIRMING, 100,
            IDENTIFIED, 101,
            PEDNDING, 102,
            TOBECONFIRMED, 103,
            OVERRULED, 104,
            PROCESSED, 200,
            DISMISSED, 201
    );
}
