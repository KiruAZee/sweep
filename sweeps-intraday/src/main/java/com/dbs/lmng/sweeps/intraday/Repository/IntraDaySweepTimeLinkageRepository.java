package com.dbs.lmng.sweeps.intraday.Repository;

import com.dbs.lmng.sweeps.intraday.Entity.IntraDaySweepTimeLinkage;
import com.dbs.lmng.sweeps.intraday.Entity.IntraDaySweepTimeLinkageKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntraDaySweepTimeLinkageRepository extends JpaRepository<IntraDaySweepTimeLinkage, IntraDaySweepTimeLinkageKey> {
    @Query(value = "SELECT l.linkage_id FROM sweep_linkage_maint_table l, " +
            " INTRA_DAY_SWEEP_TIME_LINKAGE u WHERE " +
            " u.sweep_time = :sweepTime and u.bank_id = :bankId " +
            " AND u.linkage_id = l.linkage_id and u.linkage_bank_id = l.bank_id ",
            nativeQuery = true)
    List<Long> findBySweepTimeAndBankId(@Param("sweepTime")String sweepTime,
                                                    @Param("bankId")String bankId);
}
