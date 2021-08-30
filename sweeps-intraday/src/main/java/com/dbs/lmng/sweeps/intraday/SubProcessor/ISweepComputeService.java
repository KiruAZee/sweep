package com.dbs.lmng.sweeps.intraday.SubProcessor;

import com.dbs.lmng.sweeps.intraday.Entity.SweepGroupKey;

public interface ISweepComputeService {
    boolean sweepUp(SweepGroupKey group);
    boolean sweepDown(SweepGroupKey group);
}
