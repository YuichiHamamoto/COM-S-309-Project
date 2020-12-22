package com.example.habit_tracker;

import com.example.habit_tracker.Logic.NotificationLogic.CheckNotificationSettingLogic;
import com.example.habit_tracker.Logic.SignUpLogic;

import org.json.JSONException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NotificationTest {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void whenSuccessfullyChecked() throws JSONException, InterruptedException {
        CheckNotificationSettingLogic test = mock(CheckNotificationSettingLogic.class);
        SecurityActivity testSignUpSuccess = new SecurityActivity();

        String usernameCorrect = "cow";

        when(test.checkNotificationSetting(usernameCorrect)).thenReturn(true);
        Assert.assertEquals(testSignUpSuccess.tryCheckNotification("cow",test),true);

    }
    @Test
    public void whenNotSuccessfullyChecked() throws JSONException, InterruptedException {
        CheckNotificationSettingLogic test = mock(CheckNotificationSettingLogic.class);
        SecurityActivity testSignUpSuccess = new SecurityActivity();

        String usernameIncorrect = "asshole";

        when(test.checkNotificationSetting(usernameIncorrect)).thenReturn(false);
        Assert.assertEquals(testSignUpSuccess.tryCheckNotification("asshole",test),false);

    }
}
