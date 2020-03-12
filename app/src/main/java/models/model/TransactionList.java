package models.model;

import java.io.Serializable;
import java.util.List;

public class TransactionList implements Serializable {
    private List<StatementList> statementList;
    private Error error;

    public List<StatementList> getStatementList(){
        return statementList;
    }


    public void setStatementList(List<StatementList> statementList) {
        this.statementList = statementList;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }


}
