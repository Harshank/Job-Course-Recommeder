package linkedincoursera.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import linkedincoursera.model.QuesSof;
import linkedincoursera.model.QuestionCountSOF;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by harsh on 4/24/15.
 */
@Component
public class StackoverflowService {
    public void fetchMostAskedQuestionsStackoverflow() throws Exception{
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
            ObjectMapper mapper = new ObjectMapper();
            QuesSof tags = mapper.readValue(result.toString(), QuesSof.class);
            for(QuestionCountSOF tag : tags.getItems()) {
                System.out.println(tag.getName() + ": " + tag.getCount());
            }
        } finally {
        }
    }
}
