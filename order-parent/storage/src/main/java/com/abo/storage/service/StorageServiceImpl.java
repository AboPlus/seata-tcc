package com.abo.storage.service;

import com.abo.storage.mapper.StorageMapper;
import com.abo.storage.tcc.StorageTccAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Abo
 */
@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    private StorageTccAction storageTccAction;
    @Autowired
    private StorageMapper storageMapper;

    @Override
    public void decrease(Long productId, Integer count) {
        //storageMapper.decrease(productId, count);
        storageTccAction.prepare(null, productId, count);
    }
}
