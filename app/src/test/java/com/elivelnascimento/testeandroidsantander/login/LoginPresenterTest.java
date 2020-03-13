package com.elivelnascimento.testeandroidsantander.login;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ui.login.LoginContract;
import ui.login.LoginPresnter;

public class LoginPresenterTest {

    @Mock
    private LoginContract.View view;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testPasswordTrue() {
        LoginPresnter loginPresenter = new LoginPresnter(view);
        boolean validate = loginPresenter.validadePassword("A@Co12");
        Assert.assertEquals(true,validate);

    }

    @Test
    public void testPassswordOnlyNumbers() {
        LoginPresnter loginPresenter = new LoginPresnter(view);
        boolean validate = loginPresenter.validadePassword("123312");
        Assert.assertEquals(false,validate);

    }

    @Test
    public void testPasswordPoints() {
        LoginPresnter loginPresenter = new LoginPresnter(view);
        boolean validate = loginPresenter.validadePassword(".....");
        Assert.assertEquals(false,validate);

    }

    @Test
    public void testPassworCapitalLettersOnly() {
        LoginPresnter loginPresenter = new LoginPresnter(view);
        boolean validate = loginPresenter.validadePassword("ABCDEFGHIJ");
        Assert.assertEquals(false,validate);

    }

    @Test
    public void testPassworOnlySpecialCharacters() {
        LoginPresnter loginPresenter = new LoginPresnter(view);
        boolean validate = loginPresenter.validadePassword("!@#$%¨&*");
        Assert.assertEquals(false,validate);

    }


    @Test
    public void testPassworLowercaseLettersOnly() {
        LoginPresnter loginPresenter = new LoginPresnter(view);
        boolean validate = loginPresenter.validadePassword("abcdejfhi");
        Assert.assertEquals(false,validate);

    }

    @Test
    public void testLoginCPF() {
        LoginPresnter loginPresenter = new LoginPresnter(view);
        boolean validate = loginPresenter.validateEmailCPF("12345678910");
        Assert.assertEquals(true,validate);

    }

    @Test
    public void testLoginEmail() {
        LoginPresnter loginPresenter = new LoginPresnter(view);
        boolean validate = loginPresenter.validateEmailCPF("teste@teste.com");
        Assert.assertEquals(true,validate);

    }

    @Test
    public void testLoginEmailValitation(){
        LoginPresnter loginPresnter = new LoginPresnter(view);
        boolean validate = loginPresnter.validateEmailCPF("teste@");
        Assert.assertEquals(false,validate);
    }
}