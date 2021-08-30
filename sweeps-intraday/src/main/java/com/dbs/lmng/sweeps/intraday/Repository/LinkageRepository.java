package com.dbs.lmng.sweeps.intraday.Repository;

import com.dbs.lmng.sweeps.intraday.Entity.Linkage;
import com.dbs.lmng.sweeps.intraday.Entity.LinkageKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkageRepository extends JpaRepository<Linkage, LinkageKey> {
}
