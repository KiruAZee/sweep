package com.dbs.lmng.sweeps.intraday.Elements;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Account implements Serializable {
    String acctId;
    String acctCntry;
}
