package com.dbs.lmng.sweeps.intraday.Entity;

import java.util.ArrayList;
import java.util.List;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class IntraDaySweepTime {
    @EmbeddedId
    IntraDaySweepTimeKey intraDaySweepTimeKey;

    /*// Addition. Remove, if troubled.
    @OneToMany(mappedBy = "intraDaySweepTimeLinkageKey.intraDaySweepTimeKey")
    List<IntraDaySweepTimeLinkage> intraDaySweepTimeLinkageList = new ArrayList<>();*/

    @ManyToMany(mappedBy = "intraDaySweepTimeList",
            cascade={CascadeType.ALL, CascadeType.ALL},fetch = FetchType.LAZY)
    List<Linkage> linkageList = new ArrayList<>();
}
