package ui.historic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.elivelnascimento.testeandroidsantander.R;

import java.text.NumberFormat;
import java.util.List;

import adapter.AdapterHistoric;
import models.model.StatementList;
import models.model.TransactionList;
import models.model.UserAccount;
import ui.login.LoginActivity;

public class HistoricActivity extends AppCompatActivity implements HistoricContract.View {

    private HistoricContract.Presenter presenter;
    private List<StatementList> historics ;
    private AdapterHistoric adapterHistoric;
    private ImageView logo;
    private UserAccount userAccount;

    private TextView text_name_client;
    private TextView text_agency;
    private TextView text_current_balance;
    private TransactionList transactionList;
    private RecyclerView recycler_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historic);

        presenter = new HistoricPresenter(this);
        presenter.getList(1);
        logo = findViewById(R.id.image_view_advance);
        text_name_client = findViewById(R.id.text_name_client);
        text_agency = findViewById(R.id.text_agency);
        text_current_balance = findViewById(R.id.text_current_balance);

        recycler_list = findViewById(R.id.recycler_list);

        adapterHistoric = new AdapterHistoric(historics, this);

        if(getIntent().hasExtra("user"))

            userAccount = (UserAccount) getIntent().getSerializableExtra("user");

        text_name_client.setText(userAccount.getName());
        text_agency.setText(userAccount.getBankAccount() +" / "+userAccount.getAgency());
        text_current_balance.setText(formatReal(userAccount.getBalance()));
        retrieveHistory();

        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoricActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
    private void retrieveHistory(){ }
    private void configAdapter(List<StatementList> statementLists){
        AdapterHistoric adapterHistoric = new AdapterHistoric(statementLists,HistoricActivity.this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recycler_list.setLayoutManager(layoutManager);
        recycler_list.setHasFixedSize(true);
        recycler_list.setAdapter(adapterHistoric);
    }
    private String formatReal(Double value){
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        String format = nf.format (value);
        return format;
    }
    @Override
    public void listHistoricRecycle(TransactionList transactionList) {
//        Intent intent = new Intent(this, HistoricActivity.class);
//        intent.putExtra("list", transactionList);
        configAdapter(transactionList.getStatementList());

    }
    @Override
    public void showHistoric(TransactionList transactionList) {
//        Intent intent = new Intent(this, HistoricActivity.class);
//        intent.putExtra("list", transactionList);
        configAdapter(transactionList.getStatementList());

    }
    @Override
    public void showMessageList(String error) {
        showMessageList("Error");
    }
}
