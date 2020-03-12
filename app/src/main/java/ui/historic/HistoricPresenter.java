package ui.historic;

import models.model.TransactionList;
import models.service.RetrofitConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoricPresenter implements HistoricContract.Presenter {

    private HistoricContract.View view;
    public HistoricPresenter(HistoricContract.View view) {
        this.view = view;
    }

    RetrofitConfig retrofitConfig = new RetrofitConfig();
    @Override
    public void getList(int userId) {
        retrofitConfig.getService().listHistoric(userId).enqueue(new Callback<TransactionList>() {
            @Override
            public void onResponse(Call<TransactionList> call, Response<TransactionList> response) {
                //view.ShowHistoric((TransactionList) response.body().getStatementList());

                view.listHistoricRecycle(response.body());

            }

            @Override
            public void onFailure(Call<TransactionList> call, Throwable t) {
                view.showMessageList("Não tem sinal de internet!");
                System.out.println(t.getMessage());

            }
        });
    }
}
