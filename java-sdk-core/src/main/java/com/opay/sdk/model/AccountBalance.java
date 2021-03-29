package com.opay.sdk.model;

import lombok.Data;

/**
 * 帐户信息相关响应类
 *
 * @author duan_lizhi
 * @date 2019/11/26 11:17
 */
@Data
public class AccountBalance {
    /**
     * 现金户
     */
    private Amount cashBalance;
    /**
     * 奖励金户
     */
    private Amount bonusBalance;
    /**
     * oWealth理财户
     */
    private Amount oWealthBalance;
    /**
     * 冻结金额
     */
    private Amount freezeBalance;
    /**
     * cashBack金额
     */
    private Amount cashBackBalance;
    /**
     * commission金额
     */
    private Amount commissionBalance;
}
