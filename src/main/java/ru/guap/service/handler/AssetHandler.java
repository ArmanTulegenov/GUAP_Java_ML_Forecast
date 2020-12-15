package ru.guap.service.handler;

import ru.guap.model.asset.Asset;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AssetHandler<T extends Asset> {

    Optional<List<Optional<T>>> getAssets(String asset, String interval, LocalDateTime from, LocalDateTime to);
}
