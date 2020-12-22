package com.example.habit_tracker;
import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.habit_tracker.Logic.SkillsLogic.SkillsDetailsFragment;
import com.example.habit_tracker.Logic.SkillsLogic.SkillsLogic;
import com.example.habit_tracker.Logic.SkillsLogic.SkillsModel;
import com.google.android.material.textfield.TextInputEditText;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//--Zahydee M.
public class SkillsDetailsFragmentTest {
    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

    //test that getreq method will return proper JSON string
    @Test
    public void test_update_matching_resources(){
        SkillsDetailsFragment test = mock(SkillsDetailsFragment.class);
        test.recyclerView = mock(RecyclerView.class);
        SkillActivity.skillList = new ArrayList<>();
        SkillsModel firstSkill = new SkillsModel(
                "SkillTag1", "Group1");
        SkillsModel.LearningResources[] correctResources = {new SkillsModel.LearningResources(
                "book", "https://book.com")};
        firstSkill.setResources(correctResources);

        SkillsModel secondSkill = new SkillsModel(
                "SkillTag2", "Group2");
        SkillsModel.LearningResources[] incorrectResources = {new SkillsModel.LearningResources(
                "website", "https://website.com")};
        secondSkill.setResources(incorrectResources);
        SkillActivity.skillList.add(firstSkill);
        SkillActivity.skillList.add(secondSkill);
        doReturn("SkillTag1").when(test).getTagText();
        when(test.updateResources()).thenCallRealMethod();
        test.updateResources();

        Assert.assertEquals(test.res, Arrays.asList(correctResources));

    }
    //Test if JSON string fails it will also return a empty string
    @Test
    public void test_update_no_matching_resources(){
        SkillsDetailsFragment test = mock(SkillsDetailsFragment.class);
        test.recyclerView = mock(RecyclerView.class);
        SkillActivity.skillList = new ArrayList<>();
        SkillsModel firstSkill = new SkillsModel(
                "SkillTag2", "Group1");
        SkillsModel.LearningResources[] correctResources = {new SkillsModel.LearningResources(
                "book", "https://book.com")};
        firstSkill.setResources(correctResources);

        SkillActivity.skillList.add(firstSkill);
        doReturn("tag").when(test).getTagText();
        when(test.updateResources()).thenCallRealMethod();
        test.updateResources();

        Assert.assertEquals(test.res, new ArrayList<>());


    }

}
