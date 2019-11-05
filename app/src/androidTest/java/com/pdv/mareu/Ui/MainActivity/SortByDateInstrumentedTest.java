package com.pdv.mareu.Ui.MainActivity;


import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.google.common.collect.Ordering;
import com.pdv.mareu.Model.Meeting;
import com.pdv.mareu.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SortByDateInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void sortByDateInstrumentedTest() {
        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.menu_activity_item_sort_by), withContentDescription("sort_by"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.activity_main_toolbar),
                                        1),
                                0),
                        isDisplayed()));
        actionMenuItemView.perform(click());

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.title), withText("Trier par date"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView.perform(click());

        onView(withId(R.id.meeting_recycler_view)).check(matches(isSortedByDate()));
    }

    /**
     * @return List of meeting Ordered by Date
     * Get the items in recyclerview then check if they are Ordered by place.
     * Using Guava Ordering Library.
     */
    private static Matcher<View> isSortedByDate(){
        return new TypeSafeMatcher<View>() {
            private final List<String> mMeetingListDate = new ArrayList<>();
            @Override
            protected boolean matchesSafely(View item) {
                RecyclerView recyclerView = (RecyclerView) item;
                MeetingItemRecyclerViewAdapter meetingAdapter = (MeetingItemRecyclerViewAdapter) recyclerView.getAdapter();
                mMeetingListDate.clear();
                mMeetingListDate.addAll(getMeetingPlace(meetingAdapter.mMeetingList));
                return Ordering.natural().isOrdered(mMeetingListDate);
            }
            private List<String> getMeetingPlace(List<Meeting> meetingList) {
                List<String> meetingsDate = new ArrayList<>();
                for (Meeting meeting : meetingList){
                    meetingsDate.add(meeting.getDate());
                }
                return meetingsDate;
            }
            @Override
            public void describeTo(Description description) {
                description.appendText("has items sorted alphabetically: " + mMeetingListDate);
            }
        };
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
