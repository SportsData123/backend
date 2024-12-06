package com.sports.backend.common;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseApiClient<T> {
    protected final RestTemplate restTemplate;

    public BaseApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    protected abstract URI makeUri(String serviceKey, int pageNo, int numOfRows);

    protected abstract T mapToDto(JSONObject item);

    public List<T> fetchData(String serviceKey, int pageNo, int numOfRows) {
        try {
            URI uri = makeUri(serviceKey, pageNo, numOfRows);
            String jsonString = restTemplate.getForObject(uri, String.class);

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonString);
            JSONArray jsonItemList = extractItems(jsonObject);

            if (jsonItemList == null) {
                return new ArrayList<>();
            }

            List<T> result = new ArrayList<>();
            for (Object o : jsonItemList) {
                JSONObject item = (JSONObject) o;
                T dto = mapToDto(item);
                if (dto != null) {
                    result.add(dto);
                }
            }
            return result;

        } catch (ParseException e) {
            return new ArrayList<>();
        }
    }

    private JSONArray extractItems(JSONObject jsonObject) {
        try {
            JSONObject response = (JSONObject) jsonObject.get("response");
            JSONObject body = (JSONObject) response.get("body");
            JSONObject items = (JSONObject) body.get("items");
            return (JSONArray) items.get("item");
        } catch (Exception e) {
            return null;
        }
    }
}