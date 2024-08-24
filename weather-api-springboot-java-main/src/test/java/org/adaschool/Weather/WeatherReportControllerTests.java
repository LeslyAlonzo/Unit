package org.adaschool.Weather;

import org.adaschool.Weather.controller.WeatherReportController;
import org.adaschool.Weather.data.WeatherReport;
import org.adaschool.Weather.service.WeatherReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WeatherReportControllerTest {

    @Mock
    private WeatherReportService weatherReportService;

    @InjectMocks
    private WeatherReportController weatherReportController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetWeatherReport() {
        double latitude = 14.6349;
        double longitude = -90.5069;
        WeatherReport mockResponse = new WeatherReport();
        mockResponse.setTemperature(25.0);
        mockResponse.setHumidity(60.0);

        when(weatherReportService.getWeatherReport(latitude, longitude)).thenReturn(mockResponse);


        WeatherReport response = weatherReportController.getWeatherReport(latitude, longitude);

        assertNotNull(response);
        assertEquals(mockResponse.getTemperature(), response.getTemperature());
        assertEquals(mockResponse.getHumidity(), response.getHumidity());
    }
}
