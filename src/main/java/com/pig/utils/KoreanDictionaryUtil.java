package com.pig.utils;

import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class KoreanDictionaryUtil {

    @Value("${korean.key}")
    private String KEY;

    String urlStr = "https://krdict.korean.go.kr/api/search?"
            + "key=" + KEY
            + "&num=100"
            + "&method=include"
            + "&part=word"
            + "&advanced=y"
            + "&type1=word"
            + "&letter_s=1"
            + "&letter_e=2";

    BufferedReader br = null;
    URL url;
    {
        try {
            url = new URL(urlStr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
