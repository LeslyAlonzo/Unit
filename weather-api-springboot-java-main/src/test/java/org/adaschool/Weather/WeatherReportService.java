package org.adaschool.Weather;

import org.adaschool.Weather.data.WeatherApiResponse;
import org.adaschool.Weather.data.WeatherReport;
import org.adaschool.Weather.service.WeatherReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WeatherReportServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private WeatherReportService weatherReportService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetWeatherReport() {

        double latitude = 14.6349;
        double longitude = -90.5069;
        WeatherApiResponse mockApiResponse = new WeatherApiResponse();
        WeatherApiResponse.Main main = new WeatherApiResponse.Main();
        main.setTemperature(25.0);
        main.setHumidity(60.0);
        mockApiResponse.setMain(main);

        String url = "https://api.openweathermap.org/data/2.5/weather?lat=" + latitude + "&lon=" + longitude + "&appid=7a136c449811b763e00de98d02082e40";
        when(restTemplate.getForObject(eq(url), eq(WeatherApiResponse.class))).thenReturn(mockApiResponse);

        WeatherReport response = weatherReportService.getWeatherReport(latitude, longitude);

        assertNotNull(response);
        assertEquals(25.0, response.getTemperature());
        assertEquals(60.0, response.getHumidity());
    }
}
