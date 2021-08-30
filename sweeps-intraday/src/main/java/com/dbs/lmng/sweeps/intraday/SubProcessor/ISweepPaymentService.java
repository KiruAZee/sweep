package com.dbs.lmng.sweeps.intraday.SubProcessor;

import com.dbs.lmng.sweeps.intraday.Entity.SweepGroupKey;

public interface ISweepPaymentService {
    Boolean sweepUpPayment(SweepGroupKey group);
    Boolean sweepDownPayment(SweepGroupKey group);
}
