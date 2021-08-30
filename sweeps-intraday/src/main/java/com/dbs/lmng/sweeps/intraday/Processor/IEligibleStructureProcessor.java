package com.dbs.lmng.sweeps.intraday.Processor;

import com.dbs.lmng.sweeps.intraday.Entity.IntraDayTime;
import org.flowable.engine.runtime.Execution;

public interface IEligibleStructureProcessor {
    Boolean prepareSweepTree(IntraDayTime intraDayTime, Execution execution);
}
