package com.adt.gateway.routers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UtilityHandler {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Value("${auth.service.url}")
	private String authServiveUrl;

	public boolean isInvalid(String token) {
		try {
			ResponseEntity<String> response = null;
			RestTemplate restTemplate = new RestTemplate();
			String tokens = token.substring(7, token.length());
			HttpHeaders headers = new HttpHeaders();
			headers.setBearerAuth(tokens);
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity httpEntity = new HttpEntity(headers);
			String isuservalidUrl = validateServiceUrl(authServiveUrl) + "api/user/isuservalid";
			LOGGER.info("isuservalidUrl - " + isuservalidUrl);
			response = restTemplate.exchange(isuservalidUrl, HttpMethod.GET, httpEntity, String.class);
			int respons = response.getStatusCodeValue();
			LOGGER.info("isuservalidUrl Response : " + respons);
			if (respons == 200) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			LOGGER.error("Exception: {}", e);
			return true;
		}
	}

	public static String validateServiceUrl(String url) {
		if (url != null && !url.endsWith("/")) {
			url = url + "/";
		}
		return url;
	}
}
