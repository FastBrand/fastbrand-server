package com.example.demo.admin.service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.analyticsreporting.v4.AnalyticsReporting;
import com.google.api.services.analyticsreporting.v4.AnalyticsReportingScopes;
import com.google.api.services.analyticsreporting.v4.model.*;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class AnalyticsService {
    @Value("${application.name}")
    private String APPLICATION_NAME;
    @Value("${key.file.location}")
    private String KEY_FILE_LOCATION;
    @Value("${view.id}")
    private String VIEW_ID;

    private AnalyticsReporting analyticsReporting;

    @PostConstruct
    public void init() throws GeneralSecurityException, IOException {
        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        JsonFactory jsonFactory = GsonFactory.getDefaultInstance();
        GoogleCredentials credential = GoogleCredentials
                .fromStream(new FileInputStream(KEY_FILE_LOCATION))
                .createScoped(AnalyticsReportingScopes.all());
        HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(credential);
        analyticsReporting = new AnalyticsReporting.Builder(httpTransport, jsonFactory, requestInitializer)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public List<Long> getVisitorCount(String startDate, String endDate) throws IOException {
        Metric sessions = new Metric().setExpression("ga:sessions").setAlias("sessions");
        List<Metric> metrics = Arrays.asList(sessions);

        DateRange dateRange = new DateRange();
        dateRange.setStartDate(startDate);
        dateRange.setEndDate(endDate);

        ReportRequest reportRequest = new ReportRequest().setViewId(VIEW_ID).setDateRanges(Collections.singletonList(dateRange)).setMetrics(metrics);
        GetReportsRequest getReport = new GetReportsRequest().setReportRequests(Collections.singletonList(reportRequest));
        GetReportsResponse response = analyticsReporting.reports().batchGet(getReport).execute();

        List<Report> reportList = response.getReports();
        List<Long> visitorCountList = new ArrayList<>();
        for (Report report : reportList) {
            if (report.getData() != null && report.getData().getRows() != null) {
                List<MetricHeaderEntry> metricHeaderEntries = report.getColumnHeader().getMetricHeader().getMetricHeaderEntries();
                for (MetricHeaderEntry metricHeaderEntry : metricHeaderEntries) {
                    String metricName = metricHeaderEntry.getName();
                    if ("sessions".equalsIgnoreCase(metricName)) {
                        List<DateRangeValues> dateRangeValuesList = report.getData().getRows().get(0).getMetrics();
                        Long visitorCount = Long.parseLong(dateRangeValuesList.get(0).getValues().get(0));
                        visitorCountList.add(visitorCount);
                    }
                }
            }
        }
        return visitorCountList;
    }
}

