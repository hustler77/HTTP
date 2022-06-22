import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        upvotedFacts(jsonToList(getData()));
    }

    private static void upvotedFacts(List<Cats> list) {
        for (Cats fact : list) {
            if (fact.getUpvotes() != 0) {
                System.out.println(fact);
            }
        }
    }

    private static String getData() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setSocketTimeout(30000)
                        .setRedirectsEnabled(false)
                        .build())
                .build();

        HttpGet request = new HttpGet("https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats");
        CloseableHttpResponse response = httpClient.execute(request);

        return new String(response.getEntity().getContent().readAllBytes(), Charset.defaultCharset());

    }

    private static List<Cats> jsonToList(String json) {
        List<Cats> list = new ArrayList<>();
        JSONParser jp = new JSONParser();
        GsonBuilder gb = new GsonBuilder();
        Gson gson = gb.create();
        try {
            JSONArray jsonArray = (JSONArray) jp.parse(json);
            for (Object obj : jsonArray) {
                Cats cat = gson.fromJson(String.valueOf(obj), Cats.class);
                list.add(cat);
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return list;
    }
}



