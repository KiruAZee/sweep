package com.dbs.lmng.sweeps.intraday.SubProcessor;

import com.dbs.lmng.sweeps.intraday.Entity.SweepGroupKey;

public interface IBalanceService {
    Boolean syncBalanceFetch(SweepGroupKey group);
}
