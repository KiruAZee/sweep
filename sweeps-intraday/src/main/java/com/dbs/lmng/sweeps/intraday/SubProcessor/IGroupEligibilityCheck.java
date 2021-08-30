package com.dbs.lmng.sweeps.intraday.SubProcessor;

import com.dbs.lmng.sweeps.intraday.Entity.SweepGroup;
import com.dbs.lmng.sweeps.intraday.Entity.SweepGroupKey;

public interface IGroupEligibilityCheck {
    Boolean check(SweepGroupKey group);
}
