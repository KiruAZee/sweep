package com.dbs.lmng.sweeps.intraday.SubProcessor.Impl;

import com.dbs.lmng.sweeps.intraday.Entity.SweepGroupKey;
import com.dbs.lmng.sweeps.intraday.SubProcessor.IBalanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class balanceService implements IBalanceService {
    @Override
    public Boolean syncBalanceFetch(SweepGroupKey group) {
        log.info("syncBalanceFetch : " + group);
        return true;
    }
}
