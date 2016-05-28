package com.gemma.web.payment;

import com.braintreegateway.BraintreeGateway;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public class BraintreeGatewayFactory {
	
    public static BraintreeGateway fromConfigMapping(Map<String, String> mapping) {
        return new BraintreeGateway(
            mapping.get("BT_ENVIRONMENT"),
            mapping.get("BT_MERCHANT_ID"),
            mapping.get("BT_PUBLIC_KEY"),
            mapping.get("BT_PRIVATE_KEY")
        );
    }

    public static BraintreeGateway fromConfigFile(File configFile) throws SecurityException, IOException, IllegalArgumentException {
        InputStream inputStream = null;
        Properties properties = new Properties();

        try {
            inputStream = new FileInputStream(configFile);
            properties.load(inputStream);
        } catch (SecurityException | IOException | IllegalArgumentException e) {
            throw e;
        } finally {
            try { inputStream.close(); }
            catch (IOException ie) { throw ie; }
        }

        return new BraintreeGateway(
            properties.getProperty("BT_ENVIRONMENT"),
            properties.getProperty("BT_MERCHANT_ID"),
            properties.getProperty("BT_PUBLIC_KEY"),
            properties.getProperty("BT_PRIVATE_KEY")
        );
    }
}
