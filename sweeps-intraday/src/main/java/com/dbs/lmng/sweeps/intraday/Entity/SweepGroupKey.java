package com.dbs.lmng.sweeps.intraday.Entity;

import com.dbs.lmng.sweeps.intraday.Elements.Account;
import com.dbs.lmng.sweeps.intraday.Elements.StructureId;
import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.io.Serializable;

@Embeddable
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SweepGroupKey implements Serializable {
    Account sweepGroupId;

    @Embedded
//    @AttributeOverrides({
//            @AttributeOverride( name = "bankId", column = @Column(name = "structure_bank_id"))
//    })
    StructureId structureId;
}
