package com.java08.quanlituyendung.calendar;

import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.java08.quanlituyendung.converter.EventRequestConverter;
import com.java08.quanlituyendung.dto.CalendarAddRequestDTO;
import com.java08.quanlituyendung.dto.EventPayload.LinkResponse;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class CalendarService {
    private static final String APPLICATION_NAME = "Google Calendar API Java Quickstart";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR_EVENTS + " " + CalendarScopes.CALENDAR);
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";
    private static NetHttpTransport httpTransport;
    private LocalServerReceiver receiver;
    private GoogleAuthorizationCodeFlow getFlow() throws GeneralSecurityException, IOException {
        httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
                new InputStreamReader(Objects.requireNonNull(CalendarService.class.getResourceAsStream(CREDENTIALS_FILE_PATH))));
        return new GoogleAuthorizationCodeFlow.Builder(
                httpTransport, JSON_FACTORY, clientSecrets, SCOPES)
                .setAccessType("online")
                .build();
    }
    private Credential getCredentials() throws IOException,GeneralSecurityException {
        receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        Credential credential = new AuthorizationCodeInstalledApp(getFlow(), receiver).authorize("user");
        stopLocalServerReceiver();
        return credential;
    }
    public LinkResponse getURL() throws IOException,GeneralSecurityException {
        receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        AuthorizationCodeRequestUrl authorizationUrl = getFlow().newAuthorizationUrl()
                .setRedirectUri(receiver.getRedirectUri());
        var link = LinkResponse.builder().link(authorizationUrl.build()).build();
        stopLocalServerReceiver();
        return  link;
    }
    public Event createEvent(CalendarAddRequestDTO requestDTO) throws IOException, GeneralSecurityException {
        Calendar calendar = new Calendar.Builder(httpTransport, JSON_FACTORY, getCredentials())
                .setApplicationName(APPLICATION_NAME)
                .build();
        Event event = EventRequestConverter.convertToEventCalendar(requestDTO);
        String calendarId = "primary";
        return calendar.events().insert(calendarId, event)
                .setSendNotifications(true)
                .setConferenceDataVersion(1)
                .execute();
    }
    public void stopLocalServerReceiver() {
        if (receiver != null) {
            try {
                receiver.stop();
            } catch (IOException ignored) {
            }
        }
    }
}
