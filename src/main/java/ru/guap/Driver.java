package ru.guap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.guap.model.asset.Bitcoin;
import ru.guap.service.AssetHandler;
import ru.guap.service.AssetHandlerImpl;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

public class Driver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Driver.class);


    public static void main(String[] args) {

        AssetHandler<Bitcoin> assetHandler = new AssetHandlerImpl();

        var timestamp1 = LocalDateTime.from(Instant.ofEpochSecond(1572508989).atZone(ZoneId.of("UTC")));
        var timestamp2 = LocalDateTime.now().minusDays(1);

        Optional<List<Optional<Bitcoin>>> assetList = assetHandler.getAssets("BTC-USD", "1d", timestamp1, timestamp2);

        assetList.ifPresent(
                listOfBitcoins ->
                        listOfBitcoins.stream().filter(Optional::isPresent).map(Optional::get).forEach(asset ->
                                LOGGER.info("{} - {}", asset.getDateTime(), asset.getOpenPrice())
                        )
        );
        LOGGER.info("{}", assetList);
    }
}
