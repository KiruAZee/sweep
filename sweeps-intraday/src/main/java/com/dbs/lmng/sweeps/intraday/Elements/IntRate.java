package com.dbs.lmng.sweeps.intraday.Elements;

import lombok.Data;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Data
@Embeddable
public class IntRate {
    BigDecimal rate;
}
