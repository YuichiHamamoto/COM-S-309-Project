package com.example.habit_tracker;

import com.example.habit_tracker.Logic.InitializeUserLogic;
import com.example.habit_tracker.Logic.LoginLogic;
import com.example.habit_tracker.Logic.SignUpLogic;

import org.json.JSONException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SignUpTest {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void whenSuccessfullyInitialized() throws JSONException, InterruptedException {
        InitializeUserLogic test = mock(InitializeUserLogic.class);
        SignUpActivity testInitializationSuccess = new SignUpActivity();

        String usernameCorrect = "cow";

        when(test.initializeUser(usernameCorrect)).thenReturn(true);
        Assert.assertEquals(testInitializationSuccess.tryInitializeUser("cow",test),true);

    }

    @Test
    public void whenNotSuccessfullyInitialized() throws JSONException, InterruptedException {
        InitializeUserLogic test = mock(InitializeUserLogic.class);
        SignUpActivity testInitializationSuccess = new SignUpActivity();

        String usernameIncorrect = "asshole";

        when(test.initializeUser(usernameIncorrect)).thenReturn(false);
        Assert.assertEquals(testInitializationSuccess.tryInitializeUser("asshole",test),false);
    }

    @Test
    public void whenSuccessfullySignUp() throws JSONException, InterruptedException {
        SignUpLogic test = mock(SignUpLogic.class);
        SignUpActivity testSignUpSuccess = new SignUpActivity();

        String usernameCorrect = "cow";
        String passwordCorrect = "password";
        String passwordVerCorrect = "password";
        String emailCorrect = "email@email.com";

        when(test.signup(usernameCorrect,passwordCorrect,passwordVerCorrect,emailCorrect)).thenReturn(true);
        Assert.assertEquals(testSignUpSuccess.trySignUp("cow","password","password","email@email.com",test),true);

    }

    @Test
    public void whenNotSuccessfullySignUp() throws JSONException, InterruptedException {
        SignUpLogic test = mock(SignUpLogic.class);
        SignUpActivity testSignUpSuccess = new SignUpActivity();

        String usernameIncorrect = "a";
        String passwordIncorrect = "a";
        String passwordVerIncorrect = "a";
        String emailIncorrect = "a";

        when(test.signup(usernameIncorrect,passwordIncorrect,passwordVerIncorrect,emailIncorrect)).thenReturn(false);
        Assert.assertEquals(testSignUpSuccess.trySignUp("a","a","a","a",test),false);

    }



}
