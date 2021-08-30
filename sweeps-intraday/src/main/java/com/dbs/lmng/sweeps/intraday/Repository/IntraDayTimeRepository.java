package com.dbs.lmng.sweeps.intraday.Repository;

import com.dbs.lmng.sweeps.intraday.Entity.IntraDaySweepTime;
import com.dbs.lmng.sweeps.intraday.Entity.IntraDaySweepTimeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntraDayTimeRepository extends JpaRepository<IntraDaySweepTime, IntraDaySweepTimeKey> {
}
