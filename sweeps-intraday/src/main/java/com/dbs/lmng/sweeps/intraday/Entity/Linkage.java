package com.dbs.lmng.sweeps.intraday.Entity;

import com.dbs.lmng.sweeps.intraday.Elements.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

import javax.persistence.*;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "sweep_linkage_maint_table")
public class Linkage{
    @EmbeddedId
    LinkageKey linkageKey;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "acctId", column = @Column(name = "parent_acct_id")),
            @AttributeOverride( name = "acctCntry", column = @Column(name = "parent_acct_cntry"))
    })
    LinkageDet linkageDet;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "acctId", column = @Column(name = "parent_acct_id")),
            @AttributeOverride( name = "acctCntry", column = @Column(name = "parent_acct_cntry"))
    })
    Account parentAcct;
    Integer priority;
    @Embedded
    Frequency frequency;
    LocalDate nextSweepDate;
    IntRate intRate;
    char sweepCentralMthd;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "asset", column = @Column(name = "from_balance")),
            @AttributeOverride( name = "denomination", column = @Column(name = "from_balance_crncy"))
    })
    Amount balanceFrom;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "asset", column = @Column(name = "to_balance")),
            @AttributeOverride( name = "denomination", column = @Column(name = "to_balance_crncy"))
    })
    Amount balanceTo;

    String p2pTTFee;
    char allowPartialSweep;
    char allowNCBalanceReset;
    char forceDebit;

    // Addition. Remove, if troubled.
   /* @OneToMany(mappedBy = "intraDaySweepTimeLinkageKey.linkageKey")
    List<IntraDaySweepTimeLinkage> intraDaySweepTimeLinkageList = new ArrayList<>();
    */

    @ManyToMany(cascade={CascadeType.ALL, CascadeType.ALL},fetch = FetchType.LAZY)
    @JoinTable(
            name = "linkage_time_mapping_table",
            joinColumns =  {
                    @JoinColumn(name="linkage_id"),
                    @JoinColumn(name="linkage_bank_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name="intra_day_time_id"),@JoinColumn(name="intra_day_bank_id")
            }
    )
    List<IntraDaySweepTime> intraDaySweepTimeList = new ArrayList<>();

   /* public void addSweepTime(IntraDaySweepTime intraDaySweepTime) {
        this.intraDaySweepTimeList.add(intraDaySweepTime);
       intraDaySweepTime.getLinkageList().add(this);
    }*/

    @Embedded
    UserAudit userAudit;
}
