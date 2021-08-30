package com.dbs.lmng.sweeps.intraday.Entity;

import java.io.Serializable;
import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class IntraDaySweepTimeLinkageKey implements Serializable {
    @Embedded
    IntraDaySweepTimeKey intraDaySweepTimeKey;

    @Embedded
    LinkageKey linkageKey;
}
