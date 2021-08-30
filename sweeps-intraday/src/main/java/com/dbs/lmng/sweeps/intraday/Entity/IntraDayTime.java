package com.dbs.lmng.sweeps.intraday.Entity;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Embeddable
public class IntraDayTime extends BankId implements Serializable {
    private IntraDayBusinessTime intraDayBusinessTime;
    private LocalDateTime systemDateTime;
}
