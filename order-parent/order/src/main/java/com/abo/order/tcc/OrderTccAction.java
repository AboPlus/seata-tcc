package com.abo.order.tcc;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

import java.math.BigDecimal;

/**
 * 这个接口用来包含TCC三个操作方法
 *  第一阶段：Try  -->  prepare()
 *  第二阶段：
 *      Confirm  -->  commit()
 *      Cancel  -->  rollback()
 * @author Abo
 */
@LocalTCC
public interface OrderTccAction {
    /**
     * 为了避开seata的一个bug，这里不使用封装对象，而是一个一个传输
     * @param businessActionContext
     * @return
     */
    @TwoPhaseBusinessAction(name = "OrderTccAction")
    boolean prepare(
                    BusinessActionContext businessActionContext,
                    @BusinessActionContextParameter(paramName = "id") Long id,
                    Long userId,
                    Long productId,
                    Integer count,
                    BigDecimal money
                    );

    boolean commit(BusinessActionContext businessActionContext);
    boolean rollback(BusinessActionContext businessActionContext);
}
