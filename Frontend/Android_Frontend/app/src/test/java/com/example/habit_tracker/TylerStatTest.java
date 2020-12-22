//package com.example.habit_tracker;
//
//import com.example.habit_tracker.Logic.HabitLogic.HabitLogic;
//import com.example.habit_tracker.Logic.HabitLogic.HabitStatLogic;
//import com.example.habit_tracker.Network.ServerRequest;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.junit.Assert;
//import org.junit.Rule;
//import org.junit.Test;
//import org.mockito.junit.MockitoJUnit;
//import org.mockito.junit.MockitoRule;
//
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//
//
////Mockito tests by Tyler O'Hare
//public class TylerStatTest {
//    @Rule
//    public MockitoRule mockitoRule = MockitoJUnit.rule();
//
//    //Test if stat that is returned with a value that will be incremented by 1
//    @Test
//    public void TestStatParse() {
//        HabitStatLogic test23 = mock(HabitStatLogic.class);
//        HabitActivity act = new HabitActivity();
//
//        when(test23.getStats("s10102020")).thenReturn("1");
//
//
//        Assert.assertEquals(act.updateStat(test23,"s10102020"),"2");
//
//    }
//
//    //Test if stat that is returned with no value that will be changed to a value 1
//    @Test
//    public void TestStatParseNull() {
//        HabitStatLogic test23 = mock(HabitStatLogic.class);
//        HabitActivity act = new HabitActivity();
//
//        when(test23.getStats("s10102020")).thenReturn("null");
//
//
//        Assert.assertEquals(act.updateStat(test23,"s10102020"),"1");
//
//    }
//
//
//
//
//}
