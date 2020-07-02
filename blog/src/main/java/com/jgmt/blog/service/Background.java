package com.jgmt.blog.service;

import com.alibaba.fastjson.JSONObject;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class Background {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${bingImageUrl}")
    private String bingImageUrl;

    /**
     * 获取必应官方背景图数据
     * @return
     */
    public JSONObject getBackground (String width, String height, Integer max) {

        String uri = bingImageUrl + "?format={format}&idx={idx}&n={n}&nc={nc}&pid={pid}&uhd={uhd}&uhdwidth={uhdwidth}&uhdheight={uhdheight}";

        Map<String, Object> param = new HashMap<String, Object>();

        param.put("format", "js");
        param.put("idx", "0");
        param.put("n", max);
        param.put("nc", new Date().getTime() + "");
        param.put("pid", "hp");
        param.put("uhd", "1");
        param.put("uhdwidth", width);
        param.put("uhdheight", height);

        String Body = restTemplate.getForObject(uri, String.class, param);

        return JSONObject.parseObject(Body);
    }
}