package com.stankiewicz.newsserver.utils.client;

public class ClientExampleDataProvider {

    private ClientExampleDataProvider(){
    }

    public static String getJsonData(){
        return "{\n" +
                "   \"status\":\"ok\",\n" +
                "   \"totalResults\":1,\n" +
                "   \"articles\":[\n" +
                "      {\n" +
                "         \"source\":{\n" +
                "            \"id\":null,\n" +
                "            \"name\":\"SOURCE.COM\"\n" +
                "         },\n" +
                "         \"author\":\"author\",\n" +
                "         \"title\":\"title\",\n" +
                "         \"description\":\"description\",\n" +
                "         \"url\":\"http://article.url\",\n" +
                "         \"urlToImage\":\"http://image.url\",\n" +
                "         \"publishedAt\":\"1970-01-01T18:40:18Z\",\n" +
                "         \"content\":null\n" +
                "      }\n" +
                "   ]\n" +
                "}";
    }

    public static String getInvalidKeyJsonData() {
        return "{\n" +
                "\"status\": \"error\",\n" +
                "\"code\": \"apiKeyInvalid\",\n" +
                "\"message\": \"Your API key is invalid or incorrect. Check your key, or go to https://newsapi.org to create a free API key.\"\n" +
                "}";
    }

    public static String getMissingKeyJsonData() {
        return "{\n" +
                "\"status\": \"error\",\n" +
                "\"code\": \"apiKeyMissing\",\n" +
                "\"message\": \"Your API key is missing. Append this to the URL with the apiKey param, or use the x-api-key HTTP header.\"\n" +
                "}";
    }

    public static String getUnexpectedErrorJsonData() {
        return "{\n" +
                "\"status\": \"error\",\n" +
                "\"code\": \"unexpectedError\",\n" +
                "\"message\": \"Unexpected Error\"\n" +
                "}";
    }


}
