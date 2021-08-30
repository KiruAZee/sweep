package com.dbs.lmng.sweeps.intraday.Processor.Impl;

import com.dbs.lmng.sweeps.intraday.Entity.IntraDayTime;
import com.dbs.lmng.sweeps.intraday.Processor.IIntraDayPostProcess;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class intraDayPostProcess implements IIntraDayPostProcess {
    @Override
    public void handle(IntraDayTime intraDayTime) {
        log.info("intraDayPostProcess: " + intraDayTime);
    }
}
