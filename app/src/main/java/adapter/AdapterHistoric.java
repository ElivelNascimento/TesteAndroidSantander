package adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.elivelnascimento.testeandroidsantander.R;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import models.model.StatementList;

public class AdapterHistoric extends RecyclerView.Adapter<AdapterHistoric.MyViewHolder> {

    private List<StatementList> historics;
    private Context context;

    public AdapterHistoric(List<StatementList> historics, Context context) {
        this.historics = historics;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View historics = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_adapter_historic, parent, false);
        return new MyViewHolder(historics);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        StatementList historic = historics.get(position);

        holder.text_payment.setText(historic.getTitle());
        holder.text_desc.setText(historic.getDesc());

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date = dateFormat.format(historic.getDate());
        holder.text_date.setText(date);

        Locale locale = new Locale("pt", "BR");
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
        String currency = format.format(historic.getValue());
        holder.text_value.setText(currency);
    }

    @Override
    public int getItemCount() {
        if(historics != null)
            return historics.size();
        return 0;

    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView text_payment;
        private TextView text_desc;
        private TextView text_date;
        private TextView text_value;

        public MyViewHolder(@NonNull View iteView) {
            super(iteView);
            text_payment = iteView.findViewById(R.id.text_payment);
            text_desc = iteView.findViewById(R.id.text_desc);
            text_date = iteView.findViewById(R.id.text_date);
            text_value = iteView.findViewById(R.id.text_value);
        }
    }
}
