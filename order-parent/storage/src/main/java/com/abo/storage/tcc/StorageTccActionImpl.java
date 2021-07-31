package com.abo.storage.tcc;

import com.abo.storage.entity.Storage;
import com.abo.storage.mapper.StorageMapper;
import io.seata.rm.tcc.api.BusinessActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Abo
 */
@Component
public class StorageTccActionImpl implements StorageTccAction {
    @Autowired
    private StorageMapper storageMapper;

    @Transactional
    @Override
    public boolean prepare(BusinessActionContext businessActionContext, Long productId, Integer count) {
        // 查询库存，判断是否有足够库存
        Storage storage = storageMapper.selectByProductId(productId);
        if ( storage.getResidue() < count ) {
            throw new RuntimeException("没有足够库存");
        }
        // 可用 -> 冻结
        storageMapper.updateResidueToFrozen(productId, count);
        ResultHolder.setResult(StorageTccAction.class, businessActionContext.getXid(), "p");
        return true;
    }

    // 冻结 -> 已使用
    @Transactional
    @Override
    public boolean commit(BusinessActionContext businessActionContext) {
        if (ResultHolder.getResult(StorageTccAction.class, businessActionContext.getXid()) == null){
            return true;
        }
        Long productId = Long.valueOf(businessActionContext.getActionContext("productId").toString());
        Integer count = Integer.valueOf(businessActionContext.getActionContext("count").toString());
        storageMapper.updateFrozenToUsed(productId, count);
        ResultHolder.removeResult(StorageTccAction.class, businessActionContext.getXid());
        return true;
    }

    // 冻结 -> 可用
    @Transactional
    @Override
    public boolean rollback(BusinessActionContext businessActionContext) {
        if (ResultHolder.getResult(StorageTccAction.class, businessActionContext.getXid()) == null){
            return true;
        }
        Long productId = Long.valueOf(businessActionContext.getActionContext("productId").toString());
        Integer count = Integer.valueOf(businessActionContext.getActionContext("count").toString());
        storageMapper.updateFrozenToResidue(productId, count);
        ResultHolder.removeResult(StorageTccAction.class, businessActionContext.getXid());
        return true;
    }
}
