package com.pdv.mareu.Ui.MainActivity;


import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.pdv.mareu.ApiService.MeetingApiService;
import com.pdv.mareu.R;
import com.pdv.mareu.Repository.MeetingRepository;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.pdv.mareu.Utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddMeetingInstrumentedTest {

    public MeetingRepository mMeetingRepository;
    public int ITEM_COUNT;

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setup(){
        mMeetingRepository = new MeetingRepository(new MeetingApiService());
        ITEM_COUNT = mMeetingRepository.getMeetingsList().size();
    }

    @Test
    public void addMeetingInstrumentedTest() {
        //click on button to add meeting
        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.add_meeting_fab),
                        childAtPosition(
                                allOf(withId(R.id.activity_main),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        floatingActionButton.perform(click());

        //add text in the editText for the meeting name
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.create_meeting_subject_et),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.create_meeting_fragment),
                                        0),
                                1)));
        appCompatEditText.perform(scrollTo(), replaceText("Test"), closeSoftKeyboard());

        //select the date of meeting in dialog
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.date_selector_btn), withText("Selectioner la date"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        4),
                                0)));
        appCompatButton.perform(scrollTo(), click());

        //validate the date selected
        ViewInteraction appCompatButton2 = onView(
                allOf(withId(android.R.id.button1), withText("ok"),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.LinearLayout")),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                3)),
                                3),
                        isDisplayed()));
        appCompatButton2.perform(click());

        //select time of meeting
        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.time_selector_btn), withText("Selectioner l'heure"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        4),
                                1)));
        appCompatButton3.perform(scrollTo(), click());

        //validate the time
        ViewInteraction appCompatButton4 = onView(
                allOf(withId(android.R.id.button1), withText("ok"),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.LinearLayout")),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                3)),
                                3),
                        isDisplayed()));
        appCompatButton4.perform(click());

        //Open the dialog to add one email adress of contributor
        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.contributor_selector_btn), withText("Ajouter des participants"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.create_meeting_fragment),
                                        0),
                                10)));
        appCompatButton5.perform(scrollTo(), click());

        //add the email adress in edit text
        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.contributor_edit_txt),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.custom),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("toto@lamzon.com"), closeSoftKeyboard());

        //validate the choice
        ViewInteraction appCompatButton6 = onView(
                allOf(withId(android.R.id.button1), withText("Valider"),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.LinearLayout")),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                3)),
                                3),
                        isDisplayed()));
        appCompatButton6.perform(click());

        //create the meeting on click on create button
        ViewInteraction appCompatButton7 = onView(
                allOf(withId(R.id.create_meeting_btn), withText("Créer réunion"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        13),
                                1)));
        appCompatButton7.perform(scrollTo(), click());

        //check if the meeting is add in recycler view
        onView(ViewMatchers.withId(R.id.meeting_recycler_view)).check(withItemCount(ITEM_COUNT+1));
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
