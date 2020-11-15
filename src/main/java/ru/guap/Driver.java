package ru.guap;

import ru.guap.model.asset.Asset;
import ru.guap.service.AssetHandler;
import ru.guap.service.AssetHandlerImpl;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

public class Driver {

    public static void main(String[] args) {


        AssetHandler assetHandler = new AssetHandlerImpl();

        var timestamp1 = LocalDateTime.from(Instant.ofEpochSecond(1572508989).atZone(ZoneId.of("UTC")));
        var timestamp2 = LocalDateTime.now().minusDays(1);

        Optional<List<Asset>> assetList = assetHandler.getAssets("BTC-USD", "1d", timestamp1, timestamp2);

        System.out.println(assetList);
    }
}
