package com.dbs.lmng.sweeps.intraday.Processor;

import com.dbs.lmng.sweeps.intraday.Entity.IntraDayTime;

public interface IIntraDayPostProcess {
    void handle(IntraDayTime intraDayTime);
}
