package com.techmahindra.sampletest.ui;

import android.app.Activity;
import android.app.Instrumentation;
import android.view.View;

import com.techmahindra.sampletest.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAction;
import androidx.test.rule.ActivityTestRule;


import static androidx.test.InstrumentationRegistry.getInstrumentation;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class Screen1Test {
    @Rule
    public ActivityTestRule<Screen1> screen1ActivityTestRule=new ActivityTestRule<Screen1>(Screen1.class);
    private Screen1 screen1=null;
    Instrumentation.ActivityMonitor monitor=getInstrumentation().addMonitor(Screen2.class.getName(),null,false);

    @Before
    public void setUp() throws Exception {
        screen1=screen1ActivityTestRule.getActivity();
    }

    /* test to check the activity screen1 launching and launching of the other activity screen2*/
    @Test
    public void testActivityCommunication(){
        View view =screen1.findViewById(R.id.screen1_recyclerview);
        assertNotNull(view);
       Espresso.onView(withId(R.id.screen1_recyclerview)).perform(click());
       Activity screen2=getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
       assertNotNull(screen2);
    }
    @After
    public void tearDown() throws Exception {
        screen1=null;
    }
}