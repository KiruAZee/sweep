package com.dbs.lmng.sweeps.intraday.Entity;

import com.dbs.lmng.sweeps.intraday.Elements.StructureId;
import com.dbs.lmng.sweeps.intraday.Elements.UserAudit;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "sweep_master_maint_table")
public class Structure {
    @EmbeddedId
    StructureId structureId;

    @Embedded
    UserAudit userAudit;
}
