package linkedincoursera.services;

import linkedincoursera.model.Elements;
import linkedincoursera.model.QuesSof;
import linkedincoursera.model.QuestionCountSOF;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by harsh on 4/24/15.
 */
@Component
public class StackoverflowService {
    public RestTemplate restTemplate = new RestTemplate();

    public List<QuestionCountSOF> fetchMostAskedQuestionsStackoverflow() throws Exception{
        HttpHeaders headers = new HttpHeaders();
//        final Map<String, String> parameterMap = new HashMap<String, String>(4);
//        parameterMap.put("charset", "utf-8");
//        headers.setContentType(
//                new MediaType("application","json", parameterMap));
        HttpEntity<String> stringHttpEntity = new HttpEntity<String>(headers);
        try{
            ResponseEntity<QuesSof> response = restTemplate.exchange("https://api.stackexchange.com/2.2/tags?order=desc&sort=popular&site=stackoverflow" , HttpMethod.GET, stringHttpEntity, QuesSof.class);
            QuesSof qtns = response.getBody();
            return qtns.getItems();
        }catch(HttpClientErrorException e){
            throw new Exception(e);
        }

    }
}
