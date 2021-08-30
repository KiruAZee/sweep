package com.dbs.lmng.sweeps.intraday.Entity;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.*;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class IntraDayBusinessTime implements Serializable {
    LocalDate businessDate;
    String businessTime;
}