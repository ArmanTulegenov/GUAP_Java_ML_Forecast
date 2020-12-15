package ru.guap.model.asset;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface Asset {

    String getName();

    LocalDateTime getDateTime();

    BigDecimal getOpenPrice();

    BigDecimal getHighPrice();

    BigDecimal getLowPrice();

    BigDecimal getClosePrice();

    BigDecimal getAdjClose();

    BigDecimal getVolume();

}
