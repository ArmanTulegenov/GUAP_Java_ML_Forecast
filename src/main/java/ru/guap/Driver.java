package ru.guap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.guap.model.asset.Bitcoin;
import ru.guap.service.handler.AssetHandler;
import ru.guap.service.handler.YahooBitcoinHandlerImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class Driver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Driver.class);
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) {

        if (null == args || 4 != args.length) {
            System.err.println("Params not found.");
            System.out.println(getUsage());
            System.exit(-1);
        }

        AssetHandler<Bitcoin> assetHandler = new YahooBitcoinHandlerImpl();

        var assetName = args[0];
        var interval = args[1];
        String dateTimeFromArg = args[2];
        String dateTimeToArg = args[3];

        var dateTimeFrom = convertStringToLocalDateTime(dateTimeFromArg);
        var dateTimeTo = convertStringToLocalDateTime(dateTimeToArg);

        Optional<List<Optional<Bitcoin>>> assetList = assetHandler.getAssets(assetName, interval, dateTimeFrom, dateTimeTo);

        assetList.ifPresent(
                listOfBitcoins ->
                        listOfBitcoins.stream().filter(Optional::isPresent).map(Optional::get).forEach(asset ->
                                LOGGER.info("{} - {}", asset.getDateTime(), asset.getClosePrice())
                        )
        );
        // LOGGER.info("{}", assetList);
    }

    private static String getUsage() {

        return "Usage: \n" +
                "      2020_10-all.jar <assetName> <interval> <ISO datetime from> <ISO datetime to> \n" +
                "      e.g: java -jar 2020_10-all.jar BTC-USD 1d 2000-01-01 2020-12-12";
    }

    private static LocalDateTime convertStringToLocalDateTime(String dateTimeFromArg) {
        return LocalDate.from(DATE_TIME_FORMATTER.parse(dateTimeFromArg)).atStartOfDay();
    }
}
