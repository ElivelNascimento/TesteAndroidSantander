package ui.historic;

import models.model.TransactionList;

public interface HistoricContract {

    interface View{
        void listHistoricRecycle(TransactionList transactionList);
        void showHistoric(TransactionList transactionList);
        void showMessageList(String error);
    }

    interface Presenter{

        void getList(int id);
    }
}
