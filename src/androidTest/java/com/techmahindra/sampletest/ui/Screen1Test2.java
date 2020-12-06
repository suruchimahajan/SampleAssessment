package com.techmahindra.sampletest.ui;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.techmahindra.sampletest.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class Screen1Test2 {

    @Rule
    public ActivityTestRule<Screen1> mActivityTestRule = new ActivityTestRule<>(Screen1.class);

    @Test
    public void screen1Test2() {
        ViewInteraction constraintLayout = onView(
                allOf(withId(R.id.ct_userinfo),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.screen1_recyclerview),
                                        0),
                                0),
                        isDisplayed()));
        constraintLayout.perform(click());

        ViewInteraction constraintLayout2 = onView(
                allOf(withId(R.id.screen2_ctlayout),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.screen2_recyclerview),
                                        1),
                                0),
                        isDisplayed()));
        constraintLayout2.perform(click());

        pressBack();

        pressBack();

        pressBack();
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
