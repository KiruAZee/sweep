package com.dbs.lmng.sweeps.intraday.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "sweep_group_linkage_table")
public class SweepGroupLinkage {
    @EmbeddedId
    SweepGroupLinkageKey sweepGroupLinkageKey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name="group_structure_id"),
            @JoinColumn(name="group_bank_id"),
            @JoinColumn(name="group_acct_cntry"),
            @JoinColumn(name="group_acct_id")
    })
    SweepGroup sweepGroup;

    String status;
}
