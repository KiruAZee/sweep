package com.dbs.lmng.sweeps.intraday.Elements;

import javax.persistence.Embeddable;
import java.time.LocalTime;
import lombok.*;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class UserAudit {
    LocalTime createdOn;
    String createdBy;
    LocalTime modifiedOn;
    String modifiedBy;
}
