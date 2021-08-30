package com.dbs.lmng.sweeps.intraday.Repository;

import com.dbs.lmng.sweeps.intraday.Elements.StructureId;
import com.dbs.lmng.sweeps.intraday.Entity.Structure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StructureRepository extends JpaRepository<Structure, StructureId> {
}
