package com.example.backend.data_access;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class UmoiqApiCaller {

    public static final String baseUrl = "https://webservices.umoiq.com/service/publicXMLFeed";

    /** Makes a GET request to the Umoiq API given a set of parameters and returns a normalized Document object
     *
     * @param parameters A 2D array of Strings that represent the parameters to be passed to the API
     * @return A normalized Document object that represents the XML response from the API
     */
    public static Document getRequest(String[][] parameters) throws InvalidRequestException {
        OkHttpClient client = new OkHttpClient();

        // Construct set of parameters for the request
        Map<String, String> params = new HashMap<>();
        params.put("a", "ttc");
        for (String[] parameter : parameters) {
            params.put(parameter[0], parameter[1]);
        }

        // Builds the URL with the parameters
        HttpUrl.Builder httpBuilder = HttpUrl.parse(baseUrl).newBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            httpBuilder.addQueryParameter(entry.getKey(), entry.getValue());
        }

        String url = httpBuilder.build().toString();

        // Build the request
        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            // Execute the request
            Response response = client.newCall(request).execute();

            // Convert the response to an XML document
            Document doc = convertStringToXMLDocument(response.body().string());

            // Normalize the XML response
            doc.getDocumentElement().normalize();

            // Copy the document to check for errors
            Document docCopy = (Document) doc.cloneNode(true);

            NodeList nodeList = docCopy.getElementsByTagName("Error");

            // If the xml response contains an error, throw an InvalidRequestException containing the error message
            if (nodeList.getLength() > 0) {
                throw new InvalidRequestException(nodeList.item(0).getTextContent());
            }

            return doc;

        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }

        return null;

    }

    private static Document convertStringToXMLDocument(String xmlString) {
        //Parser that produces DOM object trees from XML content
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        //API to obtain DOM Document instance
        DocumentBuilder builder = null;
        try {
            //Create DocumentBuilder with default configuration
            builder = factory.newDocumentBuilder();

            //Parse the content to Document object
            Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
