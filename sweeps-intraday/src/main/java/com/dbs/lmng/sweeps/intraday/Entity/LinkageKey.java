package com.dbs.lmng.sweeps.intraday.Entity;

import java.io.Serializable;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class LinkageKey extends BankId implements Serializable {
    @Column(name = "linkage_id")
    Long linkageId;
}
