package com.dbs.lmng.sweeps.intraday.Entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.time.LocalDateTime;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class IntraDaySweepMaster {
    @EmbeddedId
    IntraDaySweepMasterKey intraDaySweepMasterKey;

    String processId;
    LocalDateTime startTime;
    LocalDateTime endTime;
    String status;
}
