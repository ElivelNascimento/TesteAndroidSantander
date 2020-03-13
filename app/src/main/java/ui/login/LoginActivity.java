package ui.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.elivelnascimento.testeandroidsantander.R;

import models.model.RequestLogin;
import models.model.UserAccount;
import ui.historic.HistoricActivity;

public class LoginActivity extends AppCompatActivity implements LoginContract.View{

    private LoginContract.Presenter presenter;
    private EditText name;
    private EditText password;
    private Button enter;
    private ProgressBar progressBar;
    private static final String PREFERENCE_FILE = "preference_file";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        name = findViewById(R.id.edit_login);
        password = findViewById(R.id.edit_password);
        progressBar = findViewById(R.id.progress_bar);

        presenter = new LoginPresnter(this);

        name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    presenter.validateEmailCPF(name.getText().toString());
                }
            }
        });

        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                presenter.validadePassword(password.getText().toString());
            }
        });

        AcessLogin();
        clickAcess();
        name.requestFocus();
    }

    private void clickAcess() {
        progressBar.setVisibility(View.GONE);
        this.enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(presenter.validateEmailCPF(name.getText().toString()))

                    if(presenter.validadePassword(password.getText().toString())){
                        RequestLogin requestLogin = new RequestLogin(name.getText().toString(),
                                password.getText().toString());
                        presenter.loginClick(name.getText().toString(),
                                password.getText().toString().trim());
                    }

                SharedPreferences preferences = getSharedPreferences(PREFERENCE_FILE, 0);
                SharedPreferences.Editor editor = preferences.edit();
                String user = name.getText().toString();
                editor.putString("name", user);
                editor.commit();
                name.setText(user);
            }
        });

        SharedPreferences preferences = getSharedPreferences(PREFERENCE_FILE, 0);
        String nome = preferences.getString("name", "");
        name.setText(nome);
    }

    @Override
    public void showMessage(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }


    @Override
    public void acessHistoric(UserAccount userAccount) {progressBar.setVisibility(View.VISIBLE);
        Intent intent = new Intent(LoginActivity.this, HistoricActivity.class);
        intent.putExtra("user", userAccount);
        startActivity(intent);
    }

    @Override
    public void enableButton(boolean b) {
        enter.setEnabled(b);
    }

    void AcessLogin() {
        name = findViewById(R.id.edit_login);
        password = findViewById(R.id.edit_password);
        enter = findViewById(R.id.button_login);
    }
}
