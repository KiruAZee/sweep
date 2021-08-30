package com.dbs.lmng.sweeps.intraday.Repository;

import com.dbs.lmng.sweeps.intraday.Entity.SweepGroupLinkage;
import com.dbs.lmng.sweeps.intraday.Entity.SweepGroupLinkageKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SweepGroupLinkageRepository extends JpaRepository<SweepGroupLinkage, SweepGroupLinkageKey> {
}
