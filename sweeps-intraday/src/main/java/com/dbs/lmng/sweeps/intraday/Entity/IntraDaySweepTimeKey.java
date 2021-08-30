package com.dbs.lmng.sweeps.intraday.Entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import lombok.*;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class IntraDaySweepTimeKey extends BankId implements Serializable {
    @Column(name = "sweep_time")
    String sweepTime;
}
