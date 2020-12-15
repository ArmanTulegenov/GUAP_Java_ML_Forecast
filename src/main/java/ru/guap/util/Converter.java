package ru.guap.util;

import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.guap.exception.GuapJavaMachineLearningInvalidParameters;
import ru.guap.model.asset.Asset;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Converter {
    private static final Logger LOGGER = LoggerFactory.getLogger(Converter.class);
    public static final String ASSET_LIST_COULD_NOT_BE_NULL = "Asset List  could not be null";
    public static final String BATCH_SIZE_COULD_NOT_BE_LESS_THAN_ONE = "Batch size could not be less than one, batchSize = {}";

    public static Pair<Asset[][], Asset[]> getFeatureArray(List<Asset> assetList, int batchSize) {
        if (null == assetList) {
            LOGGER.error(ASSET_LIST_COULD_NOT_BE_NULL);
            throw new GuapJavaMachineLearningInvalidParameters();
        }

        if (batchSize < 1) {
            LOGGER.error(BATCH_SIZE_COULD_NOT_BE_LESS_THAN_ONE, batchSize);
            throw new GuapJavaMachineLearningInvalidParameters();
        }

        Asset[] originalArray = assetList.stream().sorted(Comparator.comparing(Asset::getDateTime)).collect(Collectors.toList()).toArray(new Asset[0]);
        int index = 0;

        Asset[][] featureResult = new Asset[originalArray.length - batchSize][batchSize];
        Asset[] labelResult = new Asset[originalArray.length - batchSize];

        while (index + batchSize - 1 < originalArray.length) {
            labelResult[index] = originalArray[index + batchSize];
            for (int i = 0; i < batchSize; i++) {
                featureResult[index][i] = originalArray[index + i];
            }
            index ++;
        }
        return Pair.of(featureResult, labelResult);
    }

}
