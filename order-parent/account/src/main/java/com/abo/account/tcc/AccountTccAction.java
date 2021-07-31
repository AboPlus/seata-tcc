package com.abo.account.tcc;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

import java.math.BigDecimal;

/**
 * @author Abo
 */
@LocalTCC
public interface AccountTccAction {
    @TwoPhaseBusinessAction(name = "AccountTccAction")
    boolean prepare(BusinessActionContext ctx,
                    @BusinessActionContextParameter(paramName = "userId") Long userId,
                    @BusinessActionContextParameter(paramName = "money") BigDecimal money);

    boolean commit(BusinessActionContext ctx);
    boolean rollback(BusinessActionContext ctx);
}
