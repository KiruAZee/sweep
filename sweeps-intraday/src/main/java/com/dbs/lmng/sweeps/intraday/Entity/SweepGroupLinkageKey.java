package com.dbs.lmng.sweeps.intraday.Entity;

import javax.persistence.Embeddable;
import java.io.Serializable;
import lombok.*;

@Embeddable
@NoArgsConstructor
//@ToString
@Getter
@Setter
@AllArgsConstructor
public class SweepGroupLinkageKey extends BankId implements Serializable {
    Long linkageId;
//    @Embedded
//    IntraDayBusinessTime intraDayBusinessTime;
    String processId;
}
