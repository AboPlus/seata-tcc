package com.abo.account.service;

import com.abo.account.mapper.AccountMapper;
import com.abo.account.tcc.AccountTccAction;
import com.baomidou.mybatisplus.core.executor.MybatisBatchExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author Abo
 */
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountTccAction accountTccAction;
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public void decrease(Long userId, BigDecimal money) {
        //accountMapper.decrease(userId, money);
        accountTccAction.prepare(null, userId, money);
    }
}
