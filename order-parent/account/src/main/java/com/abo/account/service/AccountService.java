package com.abo.account.service;

import java.math.BigDecimal;

/**
 * @author Abo
 */
public interface AccountService {
    void decrease(Long userId, BigDecimal money);
}
