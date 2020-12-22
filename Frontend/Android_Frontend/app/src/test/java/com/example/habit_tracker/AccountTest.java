package com.example.habit_tracker;

import com.example.habit_tracker.Logic.ChangePasswordLogic;
import com.example.habit_tracker.Logic.SetEmailLogic;
import com.example.habit_tracker.Logic.VertifyPasswordLogic;

import org.json.JSONException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountTest {
    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void whenUpdatePasswordValid(){
        VertifyPasswordLogic test = mock(VertifyPasswordLogic.class);
        AccountActivity testVerifyPasswordSuccess = new AccountActivity();

        String correctPassword = "password";

        when(test.vertifyPassword(correctPassword)).thenReturn(true);
        Assert.assertEquals(testVerifyPasswordSuccess.tryVerifyPassword("password",test),true);

    }

    @Test
    public void whenUpdatePasswordIsNotValid(){
        VertifyPasswordLogic test = mock(VertifyPasswordLogic.class);
        AccountActivity testVerifyPasswordFail = new AccountActivity();

        String wrongPassword1 = "Password";

        when(test.vertifyPassword(wrongPassword1)).thenReturn(false);
        Assert.assertEquals(testVerifyPasswordFail.tryVerifyPassword("Password",test),false);

        String wrongPassword2 = "assword";
        when(test.vertifyPassword(wrongPassword2)).thenReturn(false);
        Assert.assertEquals(testVerifyPasswordFail.tryVerifyPassword("assword",test),false);

    }

    @Test
    public void whenSetEmailValid() throws JSONException {
        SetEmailLogic test = mock(SetEmailLogic.class);
        AccountActivity testVerifySetEmailSuccess = new AccountActivity();

        String correctEmail = "email@email.com";
        String correctEmailVer = "email@email.com";
        String corectPassword = "password";

        when(test.setEmail(correctEmail,correctEmailVer,corectPassword)).thenReturn(true);
        Assert.assertEquals(testVerifySetEmailSuccess.trySetEmail("email@email.com","email@email.com","password",test),true);
    }

    @Test
    public void whenSetEmailIsNotValid() throws JSONException {
        SetEmailLogic test = mock(SetEmailLogic.class);
        AccountActivity testVerifySetEmailSuccess = new AccountActivity();

        String wrongEmail = "email@email.com";
        String wrongEmailVer = "Email@email.com";
        String wrongPassword = "password";

        when(test.setEmail(wrongEmail,wrongEmailVer,wrongPassword)).thenReturn(false);
        Assert.assertEquals(testVerifySetEmailSuccess.trySetEmail("email@email.com","Email@email.com","password",test),false);
    }
}
