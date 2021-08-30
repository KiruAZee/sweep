package com.dbs.lmng.sweeps.intraday.Entity;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.io.Serializable;
import lombok.*;

@Embeddable
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IntraDaySweepMasterKey extends BankId implements Serializable {
    @Embedded
    IntraDayBusinessTime intraDayBusinessTime;
}
