package com.pdv.mareu.Ui.MainActivity;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.pdv.mareu.ApiService.MeetingApiService;
import com.pdv.mareu.R;
import com.pdv.mareu.Repository.MeetingRepository;
import com.pdv.mareu.Ui.MainActivity.MainActivity;
import com.pdv.mareu.Utils.DeleteAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.pdv.mareu.Utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class DeleteMeetingInstrumentedTest {

    private MeetingRepository mMeetingRepository;
    private static int ITEMS_COUNT;
    private MainActivity mActivity;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule(MainActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
        mMeetingRepository = new MeetingRepository(new MeetingApiService());
        ITEMS_COUNT = mMeetingRepository.getMeetingsList().size();
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void MeetingList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(ViewMatchers.withId(R.id.meeting_recycler_view))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void meetingList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position selected
        onView(ViewMatchers.withId(R.id.meeting_recycler_view)).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(ViewMatchers.withId(R.id.meeting_recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteAction()));
        // Then : the number of element have one less.
        onView(ViewMatchers.withId(R.id.meeting_recycler_view)).check(withItemCount(ITEMS_COUNT-1));
    }
}
