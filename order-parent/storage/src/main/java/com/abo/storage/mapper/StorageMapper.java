package com.abo.storage.mapper;

import com.abo.storage.entity.Storage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author Abo
 */
public interface StorageMapper extends BaseMapper<Storage> {

    void decrease(Long productId, Integer count);

    // 查询库存
    Storage selectByProductId(Long productId);

    // 可用 -> 冻结
    void updateResidueToFrozen(Long productId, Integer count);

    // 冻结 -> 已售出
    void updateFrozenToUsed(Long productId, Integer count);

    // 冻结 -> 可用
    void updateFrozenToResidue(Long productId, Integer count);

}
