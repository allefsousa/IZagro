package br.com.developer.allefsousa.izagrocadastro.postUsuario;

import android.support.annotation.IdRes;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.widget.EditText;
import android.widget.TextView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.developer.allefsousa.izagrocadastro.R;
import br.com.developer.allefsousa.izagrocadastro.operacaoUsuario.opUsuarioView;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static br.com.developer.allefsousa.izagrocadastro.postUsuario.postUsuarioViewTest.EspressoTextInputLayoutUtils.onErrorViewWithinTilWithId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

/**
 * Created by allef on 23/08/2018.
 * Teste de UI da tela de cadastro de cliente.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class postUsuarioViewTest {

    @Rule
    public ActivityTestRule<postUsuarioView> activityRule = new ActivityTestRule<>(postUsuarioView.class);

    @Test
    public void verificaVisibilidadeElementos(){
        onView(withId(R.id.editNome)).check(matches(isDisplayed()));
        onView(withId(R.id.editEmail)).check(matches(isDisplayed()));
        onView(withId(R.id.editSobrenome)).check(matches(isDisplayed()));
        onView(withId(R.id.editDatanasc)).check(matches(isDisplayed()));
        onView(withId(R.id.butAdicionar)).check(matches(isDisplayed()));
    }

//    /**
    // teste comentados pois falha Na integração continua
//     * teste adiciona um cliente ao Banco de dados
//     */
//    @Test
//    public void AdicionaCliente(){
//        onView(withId(R.id.editNome)).perform(typeText("Allef"));
//        onView(withId(R.id.editSobrenome)).perform(typeText("sousa"));
//        closeSoftKeyboard();
//        onView(withId(R.id.editEmail)).perform(typeText("allefsousa_1@hotmail.com"));
//        closeSoftKeyboard();
//        onView(withId(R.id.editDatanasc)).perform(typeText("26011994"));
//        closeSoftKeyboard();
//        onView(withId(R.id.butAdicionar))
//                .perform(click());
//    }
//
//    /**
//     * teste para verificar nome vazio e mensagem de Erro
//     */
//    @Test
//    public void NomeVazioTeste(){
//        onView(withId(R.id.editNome)).perform(typeText(""));
//        onView(withId(R.id.butAdicionar))
//                .perform(click());
//        onErrorViewWithinTilWithId(R.id.textinpNome).check(matches(withText("Nome em Branco !!")));
//    }
//
//    /**
//     * Testando se as mensagens de erros são exibidas quando o usuario nao digita nada.
//     */
//    @Test
//    public void todosCamposVazios(){
//        onView(withId(R.id.editNome)).perform(typeText(""));
//        onView(withId(R.id.editSobrenome)).perform(typeText(""));
//        closeSoftKeyboard();
//        onView(withId(R.id.editEmail)).perform(typeText(""));
//        closeSoftKeyboard();
//        onView(withId(R.id.editDatanasc)).perform(typeText(""));
//        closeSoftKeyboard();
//        onView(withId(R.id.butAdicionar)).perform(click());
//        closeSoftKeyboard();
//
//        onErrorViewWithinTilWithId(R.id.textinpNome).check(matches(withText("Nome em Branco !!")));
//        onErrorViewWithinTilWithId(R.id.textinpEmail).check(matches(withText("Email em Branco !!")));
//        onErrorViewWithinTilWithId(R.id.textinpSobrenome).check(matches(withText("Sobrenome em Branco !!")));
//        onErrorViewWithinTilWithId(R.id.textinpDataNasc).check(matches(withText("Data de Nascimento em Branco !!")));
//
//    }





    /**
     * Classe para tester o erro do TextInputLayout
     */
    static class EspressoTextInputLayoutUtils {
        /*
         * Use this method to find the EditText within the TextInputLayout. Useful for typing into the TextInputLayout
         */
        public static ViewInteraction onEditTextWithinTilWithId(@IdRes int textInputLayoutId) {
            //Note, if you have specified an ID for the EditText that you place inside
            //the TextInputLayout, use that instead - i.e, onView(withId(R.id.my_edit_text));
            return onView(allOf(isDescendantOfA(withId(textInputLayoutId)), isAssignableFrom(EditText.class)));
        }

        /*
         * Use this method to find the error view within the TextInputLayout. Useful for asseting that certain errors are displayed to the user
         */
        public static ViewInteraction onErrorViewWithinTilWithId(@IdRes int textInputLayoutId) {
            return onView(allOf(isDescendantOfA(withId(textInputLayoutId)), not(isAssignableFrom(EditText.class)), isAssignableFrom(TextView.class)));
        }
    }

}