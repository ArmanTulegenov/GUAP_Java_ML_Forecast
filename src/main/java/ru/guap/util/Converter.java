package ru.guap.util;

import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.guap.exception.GuapJavaMachineLearningInternalError;
import ru.guap.exception.GuapJavaMachineLearningInvalidParameters;
import ru.guap.model.asset.Asset;

import java.io.*;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Converter {
    private static final Logger LOGGER = LoggerFactory.getLogger(Converter.class);
    public static final String ASSET_LIST_COULD_NOT_BE_NULL = "Asset List  could not be null";
    public static final String BATCH_SIZE_COULD_NOT_BE_LESS_THAN_ONE = "Batch size could not be less than one, batchSize = {}";
    public static final String OUTPUT_PATH_NOT_FOUND = "Output path not found {}";
    public static final String FILE_COULD_NOT_BE_WRITTEN = "File could not be written  {}";



    public static Pair<Asset[][], Asset[][]> getFeatureArray(List<Asset> assetList, int batchSize) {
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

        Asset[][] featureResult = new Asset[originalArray.length - batchSize - 1][batchSize];
        Asset[][] labelResult = new Asset[originalArray.length - batchSize - 1][batchSize];

        while (index + batchSize + 1  < originalArray.length) {

            for (int i = 0; i < batchSize; i++) {
                featureResult[index][i] = originalArray[index + i];
                labelResult[index][i] = originalArray[index + i + 1];

            }

            index++;
        }
        return Pair.of(featureResult, labelResult);
    }


    public static void saveFiles(Pair<Asset[][], Asset[][]> input) {

        if (null == input.getLeft() || null == input.getRight()) {
            return;
        }

        URL rootResourceURL = Converter.class.getResource("/bitcoin");

        if (null == rootResourceURL) {
            throw new GuapJavaMachineLearningInternalError("Output rootPath not found");
        }

        String rootPath = rootResourceURL.getPath();

        // TODO need to check all folders
        for (int i = 0; i < input.getLeft().length; i++) {

            String featureFilePath = rootPath + File.separator + "features" + File.separator + (i + 1) + ".csv";
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(featureFilePath))) {
                bufferedWriter.write("open,high,low,close,volume");
                bufferedWriter.newLine();
                for(int j = 0; j < input.getLeft()[i].length; j ++) {
                    bufferedWriter.write(input.getLeft()[i][j].toString());
                    bufferedWriter.newLine();
                }
            } catch (FileNotFoundException e) {
                LOGGER.error(OUTPUT_PATH_NOT_FOUND, rootPath, e);
                throw new GuapJavaMachineLearningInternalError("Output rootPath not found");
            } catch (IOException e) {
                LOGGER.error(FILE_COULD_NOT_BE_WRITTEN, rootPath, e);
                throw new GuapJavaMachineLearningInternalError("File could not be written");
            }
        }

    }

}
