package ru.guap.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AssetHandler<Asset> {

    Optional<List<Asset>> getAssets(String asset, String interval, LocalDateTime from, LocalDateTime to);
}
