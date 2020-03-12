package ui.login;

import models.model.UserAccount;

public interface LoginContract {

    interface View{

        void showMessage(String error);
        void acessHistoric(UserAccount userAccount);
        void enableButton(boolean b);
    }

    interface Presenter{

        void loginClick(String name, String password);
        boolean validateEmailCPF(String user);
        boolean validadePassword(String password);
    }
}
