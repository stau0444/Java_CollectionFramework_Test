package Logging;

import Logging.Exception.NameFormatException;
import Logging.Exception.NoPropertyException;

public class LoggingTest {
    public static void main(String[] args) {
        LoggerCustom logger = LoggerCustom.getInstance();
        Product product = null;
        String productName = "asdasdasdasdasdasd";
        try {
            product = new Product(productName);
        }catch (NoPropertyException npe){
            logger.warning(npe.getMessage());
        }catch (NameFormatException nle){
            logger.warning(nle.getMessage());
        }

    }
}
