package com.dbs.lmng.sweeps.intraday.Entity;

import com.dbs.lmng.sweeps.intraday.Elements.StructureId;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SweepGroupDet implements Serializable {
    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "bankId", column = @Column(name = "structure_bank_id"))
    })
    StructureId structureId;
}
