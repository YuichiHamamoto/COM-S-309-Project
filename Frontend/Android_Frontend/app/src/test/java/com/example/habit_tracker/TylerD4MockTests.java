package com.example.habit_tracker;

import com.example.habit_tracker.Logic.HabitLogic.HabitHelper;
import com.example.habit_tracker.Logic.HabitLogic.HabitLogic;
import com.example.habit_tracker.Logic.HabitLogic.HabitStatLogic;
import com.example.habit_tracker.Logic.HabitLogic.HabitStatPostLogic;
import com.example.habit_tracker.Logic.HabitLogic.Habits;
import com.example.habit_tracker.Logic.LoginLogic;
import com.example.habit_tracker.Network.ServerRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.text.ParseException;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//Mockito tests by Tyler O'Hare
public class TylerD4MockTests {
    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();


        //Mock date generation for Habits
    @Test
    public void TestGenDueDateDaily() throws ParseException {
                HabitActivity test1 = mock(HabitActivity.class);
                Habits act = new Habits();
                act.setFrequency("Every day");
//                when(act.genNewDueDate("Never","Wed Nov 11")).thenReturn("null");

                Assert.assertEquals(act.genNewDueDate(act.getFrequency(),"Wed Nov 11"),"Thu Nov 12");

                Assert.assertEquals(act.genNewDueDate(act.getFrequency(),"Fri Nov 13"),"Sat Nov 14");

                Assert.assertEquals(act.genNewDueDate(act.getFrequency(),"Mon Nov 30"),"Tue Dec 01");




    }
    @Test
    public void TestGenDueDateWeekly() throws ParseException {
        HabitActivity test1 = mock(HabitActivity.class);
        Habits act = new Habits();;
        act.setFrequency("Every Week");
//                when(test1..thenReturn("null");

        Assert.assertEquals(act.genNewDueDate(act.getFrequency(),"Wed Nov 11"),"Wed Nov 18");

        Assert.assertEquals(act.genNewDueDate(act.getFrequency(),"Fri Nov 13"),"Fri Nov 20");

        Assert.assertEquals(act.genNewDueDate(act.getFrequency(),"Mon Nov 30"),"Mon Dec 07");



    }

    @Test
    public void PostReqStatMock() throws ParseException, JSONException {
        HabitStatPostLogic test3 = mock(HabitStatPostLogic.class);

        HabitActivity act = new HabitActivity();
        String s = "{\"username\":\"cow\",\"s10172020\":null,\"s10182020\":null,\"s10192020\":\"2\",\"s10202020\":\"1\",\"s10212020\":\"2\",\"s10222020\":\"0\",\"s10232020\":null,\"s10242020\":\"5\",\"s10252020\":\"3\",\"s10262020\":\"1\",\"s10272020\":\"0\",\"s10282020\":\"3\",\"s10292020\":\"2\",\"s10302020\":null,\"s11012020\":null,\"s11022020\":null,\"s11032020\":null,\"s11042020\":null,\"s11052020\":null,\"s11062020\":null,\"s11072020\":null,\"s11082020\":null,\"s11092020\":null,\"s11102020\":null,\"s11112020\":null,\"s11122020\":null,\"s11132020\":null,\"s11142020\":null,\"s11152020\":null,\"s11162020\":null,\"s11172020\":null,\"s11182020\":\"23\",\"s11192020\":null,\"s11202020\":null,\"s11212020\":null,\"s11222020\":null,\"s11232020\":null,\"s11242020\":null,\"s11252020\":null,\"s11262020\":null,\"s11272020\":null,\"s11282020\":null,\"s11292020\":null,\"s11302020\":null}";
//        JSONObject object = mock(JSONObject.class);
                JSONObject object = new JSONObject("{\"username\":\"cow\",\"s10172020\":null,\"s10182020\":null,\"s10192020\":\"2\",\"s10202020\":\"1\",\"s10212020\":\"2\",\"s10222020\":\"0\",\"s10232020\":null,\"s10242020\":\"5\",\"s10252020\":\"3\",\"s10262020\":\"1\",\"s10272020\":\"0\",\"s10282020\":\"3\",\"s10292020\":\"2\",\"s10302020\":null,\"s11012020\":null,\"s11022020\":null,\"s11032020\":null,\"s11042020\":null,\"s11052020\":null,\"s11062020\":null,\"s11072020\":null,\"s11082020\":null,\"s11092020\":null,\"s11102020\":null,\"s11112020\":null,\"s11122020\":null,\"s11132020\":null,\"s11142020\":null,\"s11152020\":null,\"s11162020\":null,\"s11172020\":null,\"s11182020\":\"23\",\"s11192020\":null,\"s11202020\":null,\"s11212020\":null,\"s11222020\":null,\"s11232020\":null,\"s11242020\":null,\"s11252020\":null,\"s11262020\":null,\"s11272020\":null,\"s11282020\":null,\"s11292020\":null,\"s11302020\":null}");

//        JSONObject object;
        when(test3.updateHabitStat("POST",object)).thenReturn(true);



        when(test3.updateHabitStat("GET",object)).thenReturn(false);

        Assert.assertEquals(test3.updateHabitStat("POST",object),true);

        Assert.assertEquals(test3.updateHabitStat("GET",object),false);



    }

    @Test
    public void testDateFormat() throws ParseException {
        homeActivity test1 = mock(homeActivity.class);
        Habits act = new Habits();



        when(test1.setTodo()).thenReturn("11/17/2020");

        test1.setTodo();

        Assert.assertEquals(act.setDueDate(test1.setTodo()),"Tue Nov 17");

        when(test1.setTodo()).thenReturn("11/18/2020");

        test1.setTodo();

        Assert.assertEquals(act.setDueDate(test1.setTodo()),"Wed Nov 18");

        when(test1.setTodo()).thenReturn("11/30/2020");

        test1.setTodo();

        Assert.assertEquals(act.setDueDate(test1.setTodo()),"Mon Nov 30");


    }










}
