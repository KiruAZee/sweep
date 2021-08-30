package com.dbs.lmng.sweeps.intraday.SubProcessor.Impl;

import com.dbs.lmng.sweeps.intraday.Entity.SweepGroupKey;
import com.dbs.lmng.sweeps.intraday.SubProcessor.ISweepService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class sweepService implements ISweepService {
    @Override
    public Boolean sweepStatusUpdate(SweepGroupKey group) {
        log.info("sweepStatusUpdate is: " + group);
        return true;
    }

    @Override
    public Boolean sweepPostProcessing(SweepGroupKey group) {
        log.info("sweepPostProcessing is: " + group);
        return true;
    }
}
