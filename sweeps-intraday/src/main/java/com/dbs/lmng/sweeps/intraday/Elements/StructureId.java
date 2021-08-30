package com.dbs.lmng.sweeps.intraday.Elements;

import com.dbs.lmng.sweeps.intraday.Entity.BankId;
import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class StructureId extends BankId implements Serializable {
    String structureId;
}
