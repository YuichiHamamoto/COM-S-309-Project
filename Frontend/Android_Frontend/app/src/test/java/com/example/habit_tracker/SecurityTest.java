package com.example.habit_tracker;

import com.example.habit_tracker.Logic.ChangePasswordLogic;
import com.example.habit_tracker.Logic.VertifyPasswordLogic;

import org.json.JSONException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SecurityTest {
    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void whenUpdatePasswordValid(){
        VertifyPasswordLogic test = mock(VertifyPasswordLogic.class);
        SecurityActivity testVerifyPasswordSuccess = new SecurityActivity();

        String correctPassword = "password";

        when(test.vertifyPassword(correctPassword)).thenReturn(true);
        Assert.assertEquals(testVerifyPasswordSuccess.tryCheckPassword("password",test),true);

    }

    @Test
    public void whenUpdatePasswordIsNotValid(){
        VertifyPasswordLogic test = mock(VertifyPasswordLogic.class);
        SecurityActivity testVerifyPasswordFail = new SecurityActivity();

        String wrongPassword1 = "Password";

        when(test.vertifyPassword(wrongPassword1)).thenReturn(false);
        Assert.assertEquals(testVerifyPasswordFail.tryCheckPassword("Password",test),false);

        String wrongPassword2 = "assword";
        when(test.vertifyPassword(wrongPassword2)).thenReturn(false);
        Assert.assertEquals(testVerifyPasswordFail.tryCheckPassword("assword",test),false);

    }

    @Test
    public void whenChangePasswordValid() throws JSONException {
        ChangePasswordLogic test = mock(ChangePasswordLogic.class);
        SecurityActivity testChangePasswordSuccess = new SecurityActivity();

        String correctPassword = "password";
        String correctVerPassword = "password";

        when(test.changePassword(correctPassword,correctVerPassword)).thenReturn(true);

        testChangePasswordSuccess.isPasswordCorrect = true;
        Assert.assertEquals(testChangePasswordSuccess.tryUpdatePassword("password","password",test),true);

    }

    @Test
    public void whenChangePasswordIsNotValid() throws JSONException {
        ChangePasswordLogic test = mock(ChangePasswordLogic.class);
        SecurityActivity testChangePasswordSuccess = new SecurityActivity();

        String wrongPassword = "Password";
        String wrongVerPassword = "password";

        when(test.changePassword(wrongPassword,wrongVerPassword)).thenReturn(false);

        testChangePasswordSuccess.isPasswordCorrect = true;
        Assert.assertEquals(testChangePasswordSuccess.tryUpdatePassword("Password","password",test),false);
    }
}
