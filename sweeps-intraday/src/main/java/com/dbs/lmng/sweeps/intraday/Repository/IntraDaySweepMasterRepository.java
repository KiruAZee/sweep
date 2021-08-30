package com.dbs.lmng.sweeps.intraday.Repository;

import com.dbs.lmng.sweeps.intraday.Entity.IntraDaySweepMaster;
import com.dbs.lmng.sweeps.intraday.Entity.IntraDaySweepMasterKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntraDaySweepMasterRepository extends JpaRepository<IntraDaySweepMaster, IntraDaySweepMasterKey> {
}
