package com.pdv.mareu.Ui.MainActivity;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.pdv.mareu.Model.Meeting;
import com.pdv.mareu.R;
import com.pdv.mareu.Utils.DeleteAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static com.pdv.mareu.ApiService.MeetingGeneratorApi.FAKE_MEETING;
import static com.pdv.mareu.Utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(AndroidJUnit4.class)
public class DeleteMeetingInstrumentedTest {

    private MainActivity mActivity;
    private int ITEM_COUNT;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule(MainActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
        for (Meeting meeting: FAKE_MEETING) {
            mActivity.getMeetingRepository().addMeeting(meeting);
        }
        List<Meeting> meetingList = mActivity.getMeetingRepository().getMeetingsList();
        ITEM_COUNT = meetingList.size();
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
        onView(ViewMatchers.withId(R.id.meeting_recycler_view)).check(withItemCount(ITEM_COUNT));
        // When perform a click on a delete icon
        onView(ViewMatchers.withId(R.id.meeting_recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new DeleteAction()));
        // Then : the number of element have one less.
        onView(ViewMatchers.withId(R.id.meeting_recycler_view)).check(withItemCount(ITEM_COUNT -1));
    }
}
