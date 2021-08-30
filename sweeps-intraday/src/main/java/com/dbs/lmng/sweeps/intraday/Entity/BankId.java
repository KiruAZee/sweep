package com.dbs.lmng.sweeps.intraday.Entity;

import javax.persistence.MappedSuperclass;
import lombok.*;

import java.io.Serializable;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public abstract class BankId implements Serializable {
    String bankId;
}
