package com.dbs.lmng.sweeps.intraday.Repository;

import com.dbs.lmng.sweeps.intraday.Elements.Account;
import com.dbs.lmng.sweeps.intraday.Entity.SweepGroup;
import com.dbs.lmng.sweeps.intraday.Entity.SweepGroupKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SweepGroupRepository extends JpaRepository<SweepGroup, SweepGroupKey> {
    List<SweepGroup> findByHoldingGroupId(Account account);
}
