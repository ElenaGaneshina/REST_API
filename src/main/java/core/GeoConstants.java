package core;

public class GeoConstants {

static final public String GEO_API_URI = "https://geocode-maps.yandex.ru/1.x/";
public static final String PARAM_GEOCODE = "geocode";
public static final String PARAM_FORMAT ="format";
public static final String geocodeTest = "Москва, улица Новый Арбат, дом 24";
public static final String requestResp ="\"request\":\"Москва, улица Новый Арбат, дом 24\"";
public static final String foundResp = "\"found\":\"1\"";
public static final String resultsResp = "\"results\":\"10\"";

public enum Formats {
        xml("xml"),
        json("json");
        String formatCode;

        private Formats(String format) {
            this.formatCode = format;
        }
    }


}
