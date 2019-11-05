package com.pdv.mareu.Utils;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;

import com.pdv.mareu.R;

import org.hamcrest.Matcher;

public class DeleteAction implements ViewAction {

    @Override
    public Matcher<View> getConstraints() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void perform(UiController uiController, View view) {
        View button = view.findViewById(R.id.delete_item_iv);
        // Maybe check for null
        button.performClick();
    }
}
