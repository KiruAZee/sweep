package com.dbs.lmng.sweeps.intraday.Elements;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Amount {
    BigDecimal asset;
    String denomination;
}
