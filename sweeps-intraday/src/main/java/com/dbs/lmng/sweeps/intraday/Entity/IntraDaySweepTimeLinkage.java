package com.dbs.lmng.sweeps.intraday.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class IntraDaySweepTimeLinkage {
    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride( name = "linkageKey.bankId", column = @Column(name = "linkage_bank_id"))
    })
    IntraDaySweepTimeLinkageKey intraDaySweepTimeLinkageKey;

//    @ManyToOne
//    @JoinColumns({
//            @JoinColumn(name="mapped_linkage_id"),
//            @JoinColumn(name="mapped_linkage_bank_id")
//    })
//    Linkage linkage;

//    @ManyToOne
////    @MapsId("sweepTime")
//    @JoinColumns({
//            @JoinColumn(name="sweep_time"),
//            @JoinColumn(name="linkage_bank_id")
//    })
//    IntraDaySweepTime intraDaySweepTime;
//
//    @ManyToOne
//    @MapsId("linkageId")
////    @JoinColumns({
////            @JoinColumn(name="linkage_id"),
////            @JoinColumn(name="linkage_bank_id")
////    })
//    Linkage linkage;
}
