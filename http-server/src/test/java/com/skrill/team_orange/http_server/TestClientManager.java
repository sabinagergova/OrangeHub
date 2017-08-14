package com.skrill.team_orange.http_server;

import static org.mockito.Mockito.*;
import junit.framework.TestCase;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestClientManager extends TestCase {
    private ClientManager cm;

    @BeforeMethod
    public void start() {
        cm = new ClientManager(null, null, null);
        cm = mock(cm.getClass(), withSettings()
                .spiedInstance(cm)
                .defaultAnswer(RETURNS_SMART_NULLS));

    }

    @Test
    public void givenValidCookieWhenValidateCookieThenReturnTrue() {
        String exampleCookie = "sdfsdf";
        when(cm.validateCookie(exampleCookie)).thenReturn(true);
        boolean result = cm.validateCookie(exampleCookie);

        assertTrue(result);

    }

}
