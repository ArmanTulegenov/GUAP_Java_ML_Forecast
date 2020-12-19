package ru.guap.util;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;
import org.nd4j.common.io.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.guap.model.asset.Asset;
import ru.guap.model.asset.Bitcoin;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class ConverterTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConverterTest.class);


    @Test(expected = ru.guap.exception.GuapJavaMachineLearningInvalidParameters.class)
    public void getArrayWithWrongBatchSize() {
        Converter.getFeatureArray(Arrays.asList(), -1);
    }

    @Test(expected = ru.guap.exception.GuapJavaMachineLearningInvalidParameters.class)
    public void getArrayWithNullList() {
        Converter.getFeatureArray(null, 10);
    }

    @Test
    public void getArray() {
        Pair<Asset[][], Asset[][]> result = Converter.getFeatureArray(getAssets(), 5);

        Asset[][] featureArray = result.getLeft();
        Asset[][] labelArray = result.getRight();

        for (int i = 0; i < featureArray.length; i ++) {
            for (int j = 0; j < featureArray[i].length; j ++) {
               LOGGER.info("{}, {}, {}, {}", i, j, featureArray[i][j], labelArray[i][j]);
            }
            LOGGER.info("---------------");

        }

        Converter.saveFiles(Pair.of(featureArray, labelArray));

        Assert.notNull(featureArray);
        Assert.isTrue(featureArray.length == 7);
    }

    private List<Asset> getAssets() {

        Bitcoin bitcoin1 = getBitcoin(100);
        Bitcoin bitcoin2 = getBitcoin(101);
        Bitcoin bitcoin3 = getBitcoin(102);
        Bitcoin bitcoin4 = getBitcoin(103);
        Bitcoin bitcoin5 = getBitcoin(104);
        Bitcoin bitcoin6 = getBitcoin(105);
        Bitcoin bitcoin7 = getBitcoin(106);
        Bitcoin bitcoin8 = getBitcoin(107);
        Bitcoin bitcoin9 = getBitcoin(108);
        Bitcoin bitcoin10 = getBitcoin(109);
        Bitcoin bitcoin11 = getBitcoin(110);
        Bitcoin bitcoin12= getBitcoin(111);
        Bitcoin bitcoin13 = getBitcoin(112);

        return Arrays.asList(bitcoin1, bitcoin2, bitcoin3, bitcoin4, bitcoin5, bitcoin6,
                bitcoin7, bitcoin8, bitcoin9, bitcoin10, bitcoin11, bitcoin12, bitcoin13);
    }

    private Bitcoin getBitcoin(int value) {
        return new Bitcoin("1d", LocalDateTime.now(), BigDecimal.valueOf(value),BigDecimal.valueOf(value),BigDecimal.valueOf(value),BigDecimal.valueOf(value),BigDecimal.valueOf(value),BigDecimal.valueOf(value) );
    }


}
