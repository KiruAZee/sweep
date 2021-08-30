package com.dbs.lmng.sweeps.intraday.SubProcessor;

import com.dbs.lmng.sweeps.intraday.Entity.SweepGroupKey;

public interface ISweepService {
    Boolean sweepStatusUpdate(SweepGroupKey group);
    Boolean sweepPostProcessing(SweepGroupKey group);
}
