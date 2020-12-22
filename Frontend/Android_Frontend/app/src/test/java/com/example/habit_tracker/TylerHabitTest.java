package com.example.habit_tracker;
import com.example.habit_tracker.Logic.HabitLogic.HabitHelper;
import com.example.habit_tracker.Logic.HabitLogic.HabitLogic;
import com.example.habit_tracker.Logic.HabitLogic.Habits;
import com.example.habit_tracker.Logic.LoginLogic;
import com.example.habit_tracker.Network.ServerRequest;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//Mockito tests by Tyler O'Hare
public class TylerHabitTest {
    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

//test that getreq method will return proper JSON string
    @Test
    public void getHabitList(){
        HabitLogic test = mock(HabitLogic.class);
        HabitActivity act = new HabitActivity();

        String resp = "{\"username\":\"cow\"," +
                "\"projects\":null," +
                "\"skills\":null," +
                "\"habits\":\"[{\\\"c\\\": {\\\"year\\\": 2020, \\\"month\\\": 9, \\\"minute\\\": 49, \\\"second\\\": 18, \\\"hourOfDay\\\": 19, \\\"dayOfMonth\\\": 25}, \\\"tag\\\": \\\"\\\", \\\"date\\\": \\\"Oct 25, 2020\\\", \\\"name\\\": \\\"momom\\\", \\\"dueDate\\\": \\\"Oct 26, 2020\\\", \\\"habitid\\\": 38, \\\"favorite\\\": false, \\\"username\\\": \\\"cow\\\", \\\"frequency\\\": \\\"Every day\\\"}, {\\\"c\\\": {\\\"year\\\": 2020, \\\"month\\\": 9, \\\"minute\\\": 49, \\\"second\\\": 43, \\\"hourOfDay\\\": 19, \\\"dayOfMonth\\\": 25}, \\\"tag\\\": \\\"\\\", \\\"date\\\": \\\"Oct 25, 2020\\\", \\\"name\\\": \\\"bhhj\\\", \\\"dueDate\\\": \\\"Monday, October 26, 2020\\\", \\\"habitid\\\": 39, \\\"favorite\\\": false, \\\"username\\\": \\\"cow\\\", \\\"frequency\\\": \\\"Every day\\\"}, {\\\"c\\\": {\\\"year\\\": 2020, \\\"month\\\": 9, \\\"minute\\\": 36, \\\"second\\\": 21, \\\"hourOfDay\\\": 1, \\\"dayOfMonth\\\": 30}, \\\"tag\\\": \\\"New\\\", \\\"date\\\": \\\"Oct 27, 2020\\\", \\\"name\\\": \\\"BestHabitYet\\\", \\\"dueDate\\\": \\\"Fri Oct 30\\\", \\\"habitid\\\": 41, \\\"favorite\\\": true, \\\"username\\\": \\\"cow\\\", \\\"frequency\\\": \\\"Every day\\\"}, {\\\"c\\\": {\\\"year\\\": 2020, \\\"month\\\": 9, \\\"minute\\\": 50, \\\"second\\\": 16, \\\"hourOfDay\\\": 1, \\\"dayOfMonth\\\": 27}, \\\"tag\\\": \\\"\\\", \\\"date\\\": \\\"Oct 27, 2020\\\", \\\"name\\\": \\\"final boss WEEK\\\", \\\"dueDate\\\": \\\"Tue Nov 03\\\", \\\"habitid\\\": 46, \\\"favorite\\\": false, \\\"username\\\": \\\"cow\\\", \\\"frequency\\\": \\\"Every Week\\\"}]\",\"settings\":null}";
       // final HabitHelper habitHelper = new HabitHelper();
        when(test.updateHabit("GET",null)).thenReturn(resp);

        Assert.assertEquals(act.tryGetReqm(test),resp);


    }
//Test if JSON string fails it will also return a empty string
    @Test
    public void getHabitListFail(){
        HabitLogic test = mock(HabitLogic.class);
        HabitActivity act = new HabitActivity();

        String resp = "";
        when(test.updateHabit("GET",null)).thenReturn(resp);

        Assert.assertEquals(act.tryGetReqm(test),resp);

    }




}
