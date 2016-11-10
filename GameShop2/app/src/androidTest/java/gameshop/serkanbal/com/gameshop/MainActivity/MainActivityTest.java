package gameshop.serkanbal.com.gameshop.MainActivity;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import gameshop.serkanbal.com.gameshop.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {
        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.search), withContentDescription("Search"), isDisplayed()));
        actionMenuItemView.perform(click());

        ViewInteraction searchAutoComplete = onView(
                allOf(withId(R.id.search_src_text),
                        withParent(allOf(withId(R.id.search_plate),
                                withParent(withId(R.id.search_edit_frame)))),
                        isDisplayed()));
        searchAutoComplete.perform(replaceText("ps4"), closeSoftKeyboard());

        ViewInteraction searchAutoComplete2 = onView(
                allOf(withId(R.id.search_src_text), withText("ps4"),
                        withParent(allOf(withId(R.id.search_plate),
                                withParent(withId(R.id.search_edit_frame)))),
                        isDisplayed()));
        searchAutoComplete2.perform(pressImeActionButton());

        ViewInteraction editText = onView(
                allOf(withId(R.id.search_src_text), withText("ps4"),
                        isDisplayed()));
        editText.check(matches(withText("ps4")));

        ViewInteraction textView = onView(
                allOf(withText("Number of Items: 6"),
                        isDisplayed()));

        ViewInteraction imageButton = onView(
                allOf(withContentDescription("Collapse"),
                        isDisplayed()));
        imageButton.check(matches(isDisplayed()));

        ViewInteraction imageView = onView(
                allOf(withId(R.id.search_close_btn), withContentDescription("Clear query"),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));

        ViewInteraction imageView2 = onView(
                allOf(withId(R.id.filterbutton),
                        childAtPosition(
                                allOf(withId(R.id.content_main),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                                1)),
                                1),
                        isDisplayed()));
        imageView2.check(matches(isDisplayed()));

        ViewInteraction imageView3 = onView(
                allOf(withId(R.id.filterbutton),
                        childAtPosition(
                                allOf(withId(R.id.content_main),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                                1)),
                                1),
                        isDisplayed()));
        imageView3.check(matches(isDisplayed()));

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Collapse"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.action_goCart), withContentDescription("My Cart"),
                        isDisplayed()));
        textView2.check(matches(withText("")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.action_goWishlist), withContentDescription("My Heart"),
                        isDisplayed()));
        textView3.check(matches(withText("")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.search), withContentDescription("Search"),
                        isDisplayed()));
        textView4.check(matches(withText("")));

        ViewInteraction textView5 = onView(
                allOf(withText("Game Shop"),
                        isDisplayed()));
        textView5.check(matches(withText("Game Shop")));

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.resultSize), withText("Number of Items: 16"),
                        isDisplayed()));
        textView6.check(matches(withText("Number of Items: 16")));

        ViewInteraction actionMenuItemView2 = onView(
                allOf(withId(R.id.action_goWishlist), withContentDescription("My Heart"), isDisplayed()));
        actionMenuItemView2.perform(click());

        ViewInteraction imageButton2 = onView(
                allOf(withContentDescription("Navigate up"),
                        isDisplayed()));
        imageButton2.check(matches(isDisplayed()));

        ViewInteraction textView13 = onView(
                allOf(withText("My Wishlist"),
                        isDisplayed()));
        textView13.check(matches(withText("My Wishlist")));

        ViewInteraction textView14 = onView(
                allOf(withId(R.id.action_goCart), withContentDescription("My Cart"),
                        isDisplayed()));
        textView14.check(matches(withText("")));

        ViewInteraction textView15 = onView(
                allOf(withId(R.id.textWishlistSize), withText("Wish List is empty"),
                        isDisplayed()));
        textView15.check(matches(withText("Wish List is empty")));

        ViewInteraction imageView5 = onView(
                allOf(withId(R.id.wishListBigHeart),
                        isDisplayed()));
        imageView5.check(matches(isDisplayed()));

        ViewInteraction imageView6 = onView(
                allOf(withId(R.id.wishListBigHeart),
                        isDisplayed()));
        imageView6.check(matches(isDisplayed()));

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withContentDescription("Navigate up"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

        ViewInteraction actionMenuItemView3 = onView(
                allOf(withId(R.id.action_goCart), withContentDescription("My Cart"), isDisplayed()));
        actionMenuItemView3.perform(click());

        ViewInteraction imageButton3 = onView(
                allOf(withContentDescription("Navigate up"),
                        isDisplayed()));
        imageButton3.check(matches(isDisplayed()));

        ViewInteraction textView16 = onView(
                allOf(withText("My Cart"),
                        isDisplayed()));
        textView16.check(matches(withText("My Cart")));

        ViewInteraction textView17 = onView(
                allOf(withId(R.id.action_goWishlist), withContentDescription("My Heart"),
                        isDisplayed()));
        textView17.check(matches(withText("")));

        ViewInteraction textView18 = onView(
                allOf(withId(R.id.textWishlistSize), withText("Cart is empty"),
                        isDisplayed()));
        textView18.check(matches(withText("Cart is empty")));

        ViewInteraction imageView7 = onView(
                allOf(withId(R.id.cartBigCart),
                        isDisplayed()));
        imageView7.check(matches(isDisplayed()));

        ViewInteraction imageButton4 = onView(
                allOf(withId(R.id.cartFab),
                        isDisplayed()));
        imageButton4.check(matches(isDisplayed()));

        ViewInteraction imageButton5 = onView(
                allOf(withId(R.id.cartFab),
                        isDisplayed()));
        imageButton5.check(matches(isDisplayed()));

        ViewInteraction appCompatImageButton3 = onView(
                        isDisplayed());

        ViewInteraction cardView = onView(
                allOf(withId(R.id.card_view), isDisplayed()));

        ViewInteraction textView19 = onView(
                allOf(withId(R.id.action_goWishlist), withContentDescription("My Heart"),
                        isDisplayed()));

        ViewInteraction textView20 = onView(
                allOf(withContentDescription("My Cart"),
                        isDisplayed()));

        ViewInteraction imageButton6 = onView(
                allOf(withContentDescription("Navigate up"),
                        isDisplayed()));
        imageButton6.check(matches(isDisplayed()));

        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.fabDetail), isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.buttonAddToWishlist), isDisplayed()));
        appCompatImageView.perform(click());

        ViewInteraction imageView12 = onView(
                allOf(withId(R.id.buttonAddToWishlist),
                        isDisplayed()));
        imageView12.check(matches(isDisplayed()));

        ViewInteraction actionMenuItemView4 = onView(
                allOf(withId(R.id.action_goWishlist), withContentDescription("My Heart"), isDisplayed()));
        actionMenuItemView4.perform(click());

        ViewInteraction textView28 = onView(
                allOf(withId(R.id.textWishlistSize), withText("Number of items: 1"),
                        isDisplayed()));
        textView28.check(matches(withText("Number of items: 1")));

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.wishlistrecyclerview),
                        isDisplayed()));
        recyclerView.check(matches(isDisplayed()));

        ViewInteraction imageView13 = onView(
                allOf(withId(R.id.cartImage),
                        isDisplayed()));
        imageView13.check(matches(isDisplayed()));

        ViewInteraction textView29 = onView(
                allOf(withId(R.id.cartName), withText("The Last of Us: Remastered"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.cartCardView),
                                        0),
                                1),
                        isDisplayed()));
        textView29.check(matches(withText("The Last of Us: Remastered")));

        ViewInteraction textView30 = onView(
                allOf(withId(R.id.wishlistRating), withText("Rated 10.0"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.cartCardView),
                                        0),
                                2),
                        isDisplayed()));
        textView30.check(matches(withText("Rated 10.0")));

        ViewInteraction textView31 = onView(
                allOf(withId(R.id.cartPlatform), withText("PS4"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.cartCardView),
                                        0),
                                3),
                        isDisplayed()));
        textView31.check(matches(withText("PS4")));

        ViewInteraction textView32 = onView(
                allOf(withId(R.id.cartPrice), withText("$19.99"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.cartCardView),
                                        0),
                                4),
                        isDisplayed()));
        textView32.check(matches(withText("$19.99")));

        ViewInteraction textView33 = onView(
                allOf(withId(R.id.cartPrice), withText("$19.99"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.cartCardView),
                                        0),
                                4),
                        isDisplayed()));
        textView33.check(matches(withText("$19.99")));

        ViewInteraction appCompatImageButton4 = onView(
                allOf(withContentDescription("Navigate up"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton4.perform(click());

        ViewInteraction actionMenuItemView5 = onView(
                allOf(withId(R.id.action_goCart), withContentDescription("My Cart"), isDisplayed()));
        actionMenuItemView5.perform(click());

        ViewInteraction textView34 = onView(
                allOf(withId(R.id.textWishlistSize), withText("Cart total is: $19.99"),
                        childAtPosition(
                                allOf(withId(R.id.activity_cart),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                                1)),
                                0),
                        isDisplayed()));
        textView34.check(matches(withText("Cart total is: $19.99")));

        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.cartrecyclerview),
                        childAtPosition(
                                allOf(withId(R.id.activity_cart),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                                1)),
                                1),
                        isDisplayed()));
        recyclerView2.check(matches(isDisplayed()));

        ViewInteraction imageView14 = onView(
                allOf(withId(R.id.cartImage),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.cartCardView),
                                        0),
                                0),
                        isDisplayed()));
        imageView14.check(matches(isDisplayed()));

        ViewInteraction textView35 = onView(
                allOf(withId(R.id.cartName), withText("The Last of Us: Remastered"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.cartCardView),
                                        0),
                                1),
                        isDisplayed()));
        textView35.check(matches(withText("The Last of Us: Remastered")));

        ViewInteraction textView36 = onView(
                allOf(withId(R.id.cartCompany), withText("by Naughty Dog"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.cartCardView),
                                        0),
                                2),
                        isDisplayed()));
        textView36.check(matches(withText("by Naughty Dog")));

        ViewInteraction textView37 = onView(
                allOf(withId(R.id.cartPlatform), withText("PS4"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.cartCardView),
                                        0),
                                3),
                        isDisplayed()));
        textView37.check(matches(withText("PS4")));

        ViewInteraction textView38 = onView(
                allOf(withId(R.id.cartPrice), withText("$19.99"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.cartCardView),
                                        0),
                                4),
                        isDisplayed()));
        textView38.check(matches(withText("$19.99")));

        ViewInteraction textView39 = onView(
                allOf(withId(R.id.cartPrice), withText("$19.99"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.cartCardView),
                                        0),
                                4),
                        isDisplayed()));
        textView39.check(matches(withText("$19.99")));

        ViewInteraction appCompatImageButton5 = onView(
                allOf(withContentDescription("Navigate up"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton5.perform(click());

        ViewInteraction appCompatImageButton6 = onView(
                allOf(withContentDescription("Navigate up"),
                        withParent(allOf(withId(R.id.toolbar),
                                withParent(withId(R.id.toolbar_layout)))),
                        isDisplayed()));
        appCompatImageButton6.perform(click());

        ViewInteraction cardView2 = onView(
                allOf(withId(R.id.card_view), isDisplayed()));
        cardView2.perform(click());

        ViewInteraction floatingActionButton2 = onView(
                allOf(withId(R.id.fabDetail), isDisplayed()));
        floatingActionButton2.perform(click());

        ViewInteraction appCompatImageView2 = onView(
                allOf(withId(R.id.buttonAddToWishlist), isDisplayed()));
        appCompatImageView2.perform(click());

        ViewInteraction actionMenuItemView6 = onView(
                allOf(withId(R.id.action_goWishlist), withContentDescription("My Heart"), isDisplayed()));
        actionMenuItemView6.perform(click());

        ViewInteraction textView40 = onView(
                allOf(withId(R.id.textWishlistSize), withText("Number of items: 2"),
                        childAtPosition(
                                allOf(withId(R.id.content_wishlist),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                                1)),
                                0),
                        isDisplayed()));
        textView40.check(matches(withText("Number of items: 2")));

        ViewInteraction actionMenuItemView7 = onView(
                allOf(withId(R.id.action_goCart), withContentDescription("My Cart"), isDisplayed()));
        actionMenuItemView7.perform(click());

        ViewInteraction textView41 = onView(
                allOf(withId(R.id.textWishlistSize), withText("Cart total is: $79.98"),
                        childAtPosition(
                                allOf(withId(R.id.activity_cart),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                                1)),
                                0),
                        isDisplayed()));
        textView41.check(matches(withText("Cart total is: $79.98")));

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
