package com.fieldServiceV2.assignment.service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Service
@Slf4j
public class GoogleMapsService {

    private final RestTemplate restTemplate;
    private final String apiKey;

    public GoogleMapsService(RestTemplate restTemplate, @Value("${google.maps.api.key}") String apiKey) {
        this.restTemplate = restTemplate;
        this.apiKey = apiKey;
    }

    public String getAddressFromCoordinates(double latitude, double longitude) {
        String url = String.format(
                "https://maps.googleapis.com/maps/api/geocode/json?latlng=%s,%s&key=%s",
                latitude, longitude, apiKey
        );
        log.info("Calling Google Maps API: {}", url);

        GoogleGeocodeResponse response = restTemplate.getForObject(url, GoogleGeocodeResponse.class);
        log.info("Google Maps API Response: {}", response);

        if (response != null) {
            log.info("Response status: {}", response.getStatus());
            log.info("Response results size: {}", response.getResults().size());
            if (response.getResults().size() > 0) {
                String formattedAddress = response.getResults().get(0).getFormattedAddress();
                log.info("Formatted address: {}", formattedAddress);
                return formattedAddress != null ? formattedAddress : "Address not found";
            }
        }
        log.warn("Address not found for coordinates: {}, {}", latitude, longitude);
        return "Address not found";
    }

    // Inner classes to map Google Geocode API response
    @Data
    private static class GoogleGeocodeResponse {
        private String status;
        private List<Result> results;
    }

    @Data
    private static class Result {
        private String formatted_address;

        public String getFormattedAddress() {
            return formatted_address;
        }
    }

//    public String getAddressFromCoordinates(double latitude, double longitude) {
//        String url = String.format(
//                "https://maps.googleapis.com/maps/api/geocode/json?latlng=%s,%s&key=%s",
//                latitude, longitude, apiKey
//        );
//        log.info("Calling Google Maps API: {}", url);
//
//        GoogleGeocodeResponse response = restTemplate.getForObject(url, GoogleGeocodeResponse.class);
//
//        // Log full response
//        log.info("Google Maps API Response: {}", response);
//
//        if (response != null && response.getResults().size() > 0) {
//            log.info("Address found: {}", response.getResults().getFirst().getFormattedAddress());
//            return response.getResults().getFirst().getFormattedAddress();
//        }
//        log.warn("Address not found for coordinates: {}, {}", latitude, longitude);
//        return "Address not found";
//    }

//    public String getAddressFromCoordinates(double latitude, double longitude) {
//        String url = String.format(
//                "https://maps.googleapis.com/maps/api/geocode/json?latlng=%s,%s&key=%s",
//                latitude, longitude, apiKey
//        );
//
//        GoogleGeocodeResponse response = restTemplate.getForObject(url, GoogleGeocodeResponse.class);
//        if (response != null && response.getResults().size() > 0) {
//            return response.getResults().get(0).getFormattedAddress();
//        }
//        return "Address not found";
//    }

    // Inner classes to map Google Geocode API response
//    private static class GoogleGeocodeResponse {
//        private List<Result> results;
//
//        public List<Result> getResults() {
//            return results;
//        }
//
//        public void setResults(List<Result> results) {
//            this.results = results;
//        }
//    }
//
//    private static class Result {
//        private String formatted_address;
//
//        public String getFormattedAddress() {
//            return formatted_address;
//        }
//
//        public void setFormattedAddress(String formatted_address) {
//            this.formatted_address = formatted_address;
//        }
//    }

}