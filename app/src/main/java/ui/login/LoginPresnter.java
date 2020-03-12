package ui.login;

import models.model.ResponseLogin;
import models.service.RetrofitConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresnter implements LoginContract.Presenter{
    private LoginContract.View view;

    public LoginPresnter(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void loginClick(String name, String password) {
        if(name.isEmpty()){
            view.showMessage("Digite CPF ou email.");
            return;
        }
        if(password.isEmpty()){
            view.showMessage("Digite sua senha.");
            return;
        }
        RetrofitConfig retrofitConfig = new RetrofitConfig();
        retrofitConfig.getService().login(name, password).enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                view.acessHistoric(response.body().getUserAccount());
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                view.showMessage("Não tem sinal de internet!");
            }
        });
    }
    @Override
    public boolean validateEmailCPF(String user) {
        if (user.matches("[0-9]+")) {
            if (user.length() != 11) {
                view.showMessage("CPF inválido");
                //name.setError("CPF inválido");
                view.enableButton(false);
                view.enableButton(false);
                return false;
            }
            view.enableButton(true);
            return true;
        } else {
            if (!user.matches("[a-zA-Z0-9\\+\\.\\_\\&\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+")) {

                view.showMessage("Digite email ou CPF válido!");
                view.enableButton(false);
                return false;

            }
            view.enableButton(true);
            return true;
        }

    }
    @Override
    public boolean validadePassword(String password) {
        if(!password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d](?=.*[!@#$*_%^&+=])" +
                "(?=\\S+$)(?=\\S+$).{4,}$")) {

            view.enableButton(true);
            view.showMessage("Senha tem que conter maiúsculo minúsculo " +
                    "e caracteres especiais e números.");
            return false;
        }
        view.enableButton(true);
        return true;
    }
}
