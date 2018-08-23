package br.com.developer.allefsousa.izagrocadastro.operacaoUsuario;

import android.content.Intent;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.developer.allefsousa.izagrocadastro.R;
import br.com.developer.allefsousa.izagrocadastro.postUsuario.postUsuarioView;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

/**
 * Created by allef on 23/08/2018.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class opUsuarioViewTest {

    @Rule
    public ActivityTestRule<opUsuarioView> activityRule = new ActivityTestRule<>(opUsuarioView.class);

    @Test
    public void verificaVisibilidadeElementos(){
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()));
        onView(withId(R.id.fabAdd)).check(matches(isDisplayed()));
    }

    /**
     * teste abre outra activity se tudo correr bem o teste Passa
     */
    @Test
    public void VerificaIntentClickBotao() {
        Intents.init();

        Matcher<Intent> matcher = hasComponent(postUsuarioView.class.getName());

        onView(withId(R.id.fabAdd)).perform(click());

        intended(matcher);

        Intents.release();
    }
    @Test
    public void VerificaClick() {
        onView(withId(R.id.recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }@Test
    public void VerificaLongClick() {
        onView(withId(R.id.recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, longClick()));
    }




}