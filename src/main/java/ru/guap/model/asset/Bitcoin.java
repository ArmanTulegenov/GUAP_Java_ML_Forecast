package ru.guap.model.asset;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Bitcoin implements Currency {

    private static final String NAME = "BTC";

    // Date,Open,High,Low,Close,Adj Close,Volume
    private final String interval;
    private final LocalDateTime dateTime;
    private final BigDecimal openPrice;
    private final BigDecimal highPrice;
    private final BigDecimal lowPrice;
    private final BigDecimal closePrice;
    private final BigDecimal adjClose;
    private final BigDecimal volume;

    public Bitcoin(String interval, LocalDateTime dateTime, BigDecimal openPrice, BigDecimal highPrice, BigDecimal lowPrice, BigDecimal closePrice, BigDecimal adjClose, BigDecimal volume) {
        this.interval = interval;
        this.dateTime = dateTime;
        this.openPrice = openPrice;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.closePrice = closePrice;
        this.adjClose = adjClose;
        this.volume = volume;
    }

    public String getInterval() {
        return interval;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public BigDecimal getOpenPrice() {
        return openPrice;
    }

    public BigDecimal getHighPrice() {
        return highPrice;
    }

    public BigDecimal getLowPrice() {
        return lowPrice;
    }

    public BigDecimal getClosePrice() {
        return closePrice;
    }

    public BigDecimal getAdjClose() {
        return adjClose;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bitcoin bitcoin = (Bitcoin) o;
        return interval.equals(bitcoin.interval) &&
                dateTime.equals(bitcoin.dateTime) &&
                openPrice.equals(bitcoin.openPrice) &&
                highPrice.equals(bitcoin.highPrice) &&
                lowPrice.equals(bitcoin.lowPrice) &&
                closePrice.equals(bitcoin.closePrice) &&
                adjClose.equals(bitcoin.adjClose) &&
                volume.equals(bitcoin.volume);
    }

    @Override
    public int hashCode() {
        return Objects.hash(interval, dateTime, openPrice, highPrice, lowPrice, closePrice, adjClose, volume);
    }

}
