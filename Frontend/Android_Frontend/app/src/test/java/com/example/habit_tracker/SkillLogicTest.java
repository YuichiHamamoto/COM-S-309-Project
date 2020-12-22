package com.example.habit_tracker;
import com.example.habit_tracker.Logic.SkillsLogic.SkillsLogic;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//--Zahydee M.
public class SkillLogicTest {
    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

    //test that getreq method will return proper JSON string
    @Test
    public void getSkillList(){
        SkillsLogic test = mock(SkillsLogic.class);
        SkillActivity act = new SkillActivity();

        String resp = "{\"username\":\"tyler\",\"projects\":null,\"skills\":\"[{\\\"groupTag\\\":\\\"Languages\\" +
                "\",\\\"skillTag\\\":\\\"C++\\\"},{\\\"groupTag\\\":\\\"Languages\\\",\\\"resources\\\":[{\\" +
                "\"link\\\":\\\"https:\\/\\/codingbat.com\\/java\\\",\\\"name\\\":\\\"codingBat\\\"},{\\\"link" +
                "\\\":\\\"https:\\/\\/codingbat.com\\/python\\\",\\\"name\\\":\\\"codingbat\\\"}],\\\"skillTag\\" +
                "\":\\\"Python\\\"}]\",\"habits\":\"[{\\\"tag\\\": \\\"Python\\\", \\\"name\\\": \\\"CodingBat " +
                "Practice\\\", \\\"habitid\\\": -1, \\\"favorite\\\": false, \\\"username\\\": \\\"cow\\\"}, {\\\"tag\\\":" +
                " \\\"C++\\\", \\\"name\\\": \\\"Read Docs\\\", \\\"habitid\\\": -1, \\\"favorite\\\": false, \\\"username\\\": " +
                "\\\"cow\\\"}, {\\\"tag\\\": \\\"Python\\\", \\\"name\\\": \\\"Work on HW\\\", \\\"habitid\\\": -1, " +
                "\\\"favorite\\\": false, \\\"username\\\": \\\"cow\\\"}, {\\\"tag\\\": \\\"Python\\\", \\\"name\\\": " +
                "\\\"Read PythonForDummies\\\", \\\"habitid\\\": -1, \\\"favorite\\\": false, \\\"username\\\": \\\"cow\\\"}]\"," +
                "\"settings\":null}";

        when(test.updateSkills("GET",null)).thenReturn(resp);

        Assert.assertEquals(act.tryGetReqm(test),resp);


    }
    //Test if JSON string fails it will also return a empty string
    @Test
    public void getSkillListFail(){
        SkillsLogic test = mock(SkillsLogic.class);
        SkillActivity act = new SkillActivity();

        String resp = "";
        when(test.updateSkills("GET",null)).thenReturn(resp);

        Assert.assertEquals(act.tryGetReqm(test),resp);

    }

}
