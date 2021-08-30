package com.dbs.lmng.sweeps.intraday.Entity;

import com.dbs.lmng.sweeps.intraday.Elements.Account;
import com.dbs.lmng.sweeps.intraday.Elements.SystemAudit;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Table(name = "sweep_group_table")
public class SweepGroup implements Serializable {
    @EmbeddedId
    SweepGroupKey sweepGroupKey;

//    @Embedded
//    SweepGroupDet sweepGroupDet;
//    @Embedded
//    IntraDayBusinessTime intraDayBusinessTime;
    String processId;
    String subProcessId;

    LocalDateTime systemDateTime;

    // Linkage List is ==> LinkageId1|LinkageId2
//    String linkageList;
    // Dependency List is ==> groupId1|groupId2
//    String dependencyList;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "sweepGroup",
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    List<SweepGroupLinkage> sweepGroupLinkageList = new ArrayList<>();

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "acctId", column = @Column(name = "holding_group_acct_id")),
            @AttributeOverride( name = "acctCntry", column = @Column(name = "holding_group_cntry"))
    })
    Account holdingGroupId;

    String status;

    @Embedded
    SystemAudit systemAudit;

    public void addLinkage(SweepGroupLinkage linkage) {
        sweepGroupLinkageList.add(linkage);
        SweepGroup sweepGroup = new SweepGroup();
        sweepGroup.setSweepGroupKey(this.sweepGroupKey);
        linkage.setSweepGroup(sweepGroup);
    }

    public void addLinkages(List<SweepGroupLinkage> linkages) {
        SweepGroup sweepGroup = new SweepGroup();
        sweepGroup.setSweepGroupKey(this.sweepGroupKey);
        linkages.forEach( (linkage) -> {
            linkage.setSweepGroup(sweepGroup);
        });
        sweepGroupLinkageList.addAll(linkages);
    }
}
