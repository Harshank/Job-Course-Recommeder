package linkedincoursera.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import linkedincoursera.model.QuesSof;
import linkedincoursera.model.QuestionCountSOF;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by harsh on 4/24/15.
 */
@Component
public class StackoverflowService {
    QuesSof sof = new QuesSof();
    public List<QuestionCountSOF> fetchMostAskedQuestionsStackoverflow() throws Exception{
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet("https://api.stackexchange.com/2.2/tags?order=desc&sort=popular&site=stackoverflow");
        HttpResponse response = httpClient.execute(httpGet);
        try {
            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject)parser.parse(result.toString());
            JSONArray items = (JSONArray)obj.get("items");
            ArrayList<QuestionCountSOF> listQtns = new Gson().fromJson(items.toString(), new TypeToken<List<QuestionCountSOF>>(){}.getType());
            for(int i =0;i<listQtns.size();i++) {
                listQtns.get(i).setId(i);
            }
            sof.setItems(listQtns);
            for(Object item : items) {
                JSONObject itemObj = (JSONObject)item;
//                System.out.println(itemObj.get("name") + ": " + itemObj.get("count"));
            }
        } finally {
            return sof.getItems();
        }
    }
}
