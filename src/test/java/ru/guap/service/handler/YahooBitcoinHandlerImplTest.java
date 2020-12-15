package ru.guap.service.handler;

import org.junit.Assert;
import org.junit.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class YahooBitcoinHandlerImplTest {

    @Test
    public void testGetAssets() {

        YahooBitcoinHandlerImpl assetHandler = new YahooBitcoinHandlerImpl();

        assetHandler.getAssets("BTC-USD", "1d", LocalDateTime.now().minusYears(1), LocalDateTime.now());

    }

    @Test
    public void testTimeZone() {

        var expectedTimestamp = 1572508989;
        var actualTimestamp = LocalDateTime.from(Instant.ofEpochSecond(expectedTimestamp).atZone(ZoneId.of("UTC"))).toInstant(ZoneOffset.UTC).getEpochSecond();
        Assert.assertEquals(expectedTimestamp, actualTimestamp);

    }

    @Test
    public void getUrl() {

        var expectedUrl = "https://query1.finance.yahoo.com/v7/finance/download/BTC-USD?period1=1572508989&period2=1572508989&interval=1d&events=history&includeAdjustedClose=true&includeTimestamps=true";

        YahooBitcoinHandlerImpl assetHandler = new YahooBitcoinHandlerImpl();
        var timestamp1 = LocalDateTime.from(Instant.ofEpochSecond(1572508989).atZone(ZoneId.of("UTC")));
        var timestamp2 = LocalDateTime.from(Instant.ofEpochSecond(1572508989).atZone(ZoneId.of("UTC")));
        String actualUrl = assetHandler.getUrl("BTC-USD", "1d", timestamp1, timestamp2);

        Assert.assertEquals(expectedUrl, actualUrl);

    }
}