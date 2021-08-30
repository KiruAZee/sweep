package com.dbs.lmng.sweeps.intraday.SubProcessor.Impl;

import com.dbs.lmng.sweeps.intraday.Entity.SweepGroup;
import com.dbs.lmng.sweeps.intraday.Entity.SweepGroupKey;
import com.dbs.lmng.sweeps.intraday.SubProcessor.IGroupEligibilityCheck;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class groupEligibilityCheck implements IGroupEligibilityCheck {
    @Override
    public Boolean check(SweepGroupKey group) {
        log.info("group is " + group);
        return true;
    }
}
