package com.dbs.lmng.sweeps.intraday.SubProcessor.Impl;

import com.dbs.lmng.sweeps.intraday.Entity.SweepGroupKey;
import com.dbs.lmng.sweeps.intraday.SubProcessor.ISweepPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class sweepPaymentService implements ISweepPaymentService {
    @Override
    public Boolean sweepUpPayment(SweepGroupKey group) {
        log.info("sweepUpPayment is " + group);
        return true;
    }

    @Override
    public Boolean sweepDownPayment(SweepGroupKey group) {
        log.info("sweepDownPayment is " + group);
        return true;
    }
}
