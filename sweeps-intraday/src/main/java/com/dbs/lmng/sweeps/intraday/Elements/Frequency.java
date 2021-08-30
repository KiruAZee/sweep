package com.dbs.lmng.sweeps.intraday.Elements;

import lombok.*;

import javax.persistence.Embeddable;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Frequency {
    char freq;
    int freqDay;
    char freqWeekDay;
    char hldyFlg;
}
