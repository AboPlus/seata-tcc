package com.abo.account.mapper;

import com.abo.account.entity.Account;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.math.BigDecimal;

/**
 * @author Abo
 */
public interface AccountMapper extends BaseMapper<Account> {

    void decrease(Long userId, BigDecimal money);

    // 查询账户金额
    Account selectByUserId(Long userId);

    // 可用 -> 冻结
    void updateResidueToFrozen(Long userId, BigDecimal money);

    // 冻结 -> 已使用
    void updateFrozenToUsed(Long userId, BigDecimal money);

    // 冻结 -> 可用
    void updateFrozenToResidue(Long userId, BigDecimal money);


}
