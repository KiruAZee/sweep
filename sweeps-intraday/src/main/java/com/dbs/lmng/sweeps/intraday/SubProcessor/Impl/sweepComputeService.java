package com.dbs.lmng.sweeps.intraday.SubProcessor.Impl;

import com.dbs.lmng.sweeps.intraday.Entity.SweepGroupKey;
import com.dbs.lmng.sweeps.intraday.SubProcessor.ISweepComputeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class sweepComputeService implements ISweepComputeService {
    @Override
    public boolean sweepUp(SweepGroupKey group) {
       log.info("sweepUp is: " + group);
       return true;
    }

    @Override
    public boolean sweepDown(SweepGroupKey group) {
        log.info("sweepDown is: " + group);
        return true;
    }
}
