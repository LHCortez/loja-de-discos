package com.luiz.lhcdiscos.services;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.services.analytics.AnalyticsScopes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;

@Service
public class AnalyticsService {

    @Value("${JSON_KEY_FILE_LOCATION}")
    private String JSON_KEY_FILE_LOCATION;

    public String getToken() throws FileNotFoundException, IOException {

        GoogleCredential credential = GoogleCredential
                .fromStream(new FileInputStream(JSON_KEY_FILE_LOCATION))
                .createScoped(Collections.singleton(AnalyticsScopes.ANALYTICS_READONLY));
        credential.refreshToken();
        String access_token = credential.getAccessToken();
        return access_token;
    }

}
