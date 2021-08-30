package com.dbs.lmng.sweeps.intraday.Elements;

import javax.persistence.Embeddable;
import java.time.LocalTime;

import lombok.*;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class SystemAudit {
    LocalTime createdOn;
    String createdBy;
}
