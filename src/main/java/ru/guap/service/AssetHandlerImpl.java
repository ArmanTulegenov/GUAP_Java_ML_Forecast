package ru.guap.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.guap.model.asset.Asset;
import ru.guap.model.asset.Bitcoin;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class AssetHandlerImpl implements AssetHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(AssetHandlerImpl.class);

    @Override
    public Optional<List<Asset>> getAssets(String asset, String interval, LocalDateTime from, LocalDateTime to) {

        Optional<String> responseWithAssets = getAssetsFromServer(asset, interval, from, to);
        return parseAssetsFromResponse(interval, responseWithAssets);
    }

    private Optional<List<Asset>> parseAssetsFromResponse(String interval, Optional<String> responseWithAssets) {
        return responseWithAssets.map(response -> {
            String[] lines = response.split("\n");
            return Arrays.stream(lines).filter(line -> !line.startsWith("Date,Open")).map(line -> {
                String[] values = line.split(",");
                BigDecimal openPrice = BigDecimal.ZERO;
                BigDecimal highPrice = BigDecimal.ZERO;
                BigDecimal lowPrice = BigDecimal.ZERO;
                BigDecimal closePrice = BigDecimal.ZERO;
                BigDecimal adjClose = BigDecimal.ZERO;
                BigDecimal volume = BigDecimal.ZERO;
                try {
                    openPrice = new BigDecimal(values[1]);
                    highPrice = new BigDecimal(values[2]);
                    lowPrice = new BigDecimal(values[3]);
                    closePrice = new BigDecimal(values[4]);
                    adjClose = new BigDecimal(values[5]);
                    volume = new BigDecimal(values[6]);
                } catch (Exception ex) {
                    LOGGER.error(line, ex);
                }
                return new Bitcoin(interval, LocalDateTime.now(), openPrice, highPrice, lowPrice, closePrice, adjClose, volume);
            }).collect(Collectors.toList());
        });
    }

    private Optional<String> getAssetsFromServer(String asset, String interval, LocalDateTime from, LocalDateTime to) {
        HttpClient client = HttpClient.newHttpClient();
        String location = getUrl(Objects.requireNonNull(asset), Objects.requireNonNull(interval), Objects.requireNonNull(from), Objects.requireNonNull(to));
        URI uri = URI.create(location);
        var httpRequest = HttpRequest.newBuilder(uri).header("accept", "*/*").build();
        try {
            var httpResponse = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return Optional.of(httpResponse.body());
        } catch (IOException | InterruptedException e) {
            LOGGER.error("Unable to process HTTP request", e);
        }
        return Optional.empty();
    }

    protected String getUrl(String asset, String interval, LocalDateTime from, LocalDateTime to) {
        String baseUri = "https://query1.finance.yahoo.com";
        String path = "/v7/finance/download/";
        String params = String.format("%s?period1=%d&period2=%d&interval=%s&events=history&includeAdjustedClose=true&includeTimestamps=true", asset, getEpochSecond(from), getEpochSecond(to), interval);
        return baseUri + path + params;
    }

    private long getEpochSecond(LocalDateTime from) {
        return from.toInstant(ZoneOffset.UTC).getEpochSecond();
    }
}
