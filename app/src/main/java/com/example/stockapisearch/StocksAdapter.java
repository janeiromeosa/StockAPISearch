package com.example.stockapisearch;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class StocksAdapter extends RecyclerView.Adapter<StocksAdapter> {

    private final List<IexTradingStocks> data;

    private static OnStocksSelectedListener listener = null;

    public void setData(List<IexTradingStocks> newData) {
        data.clear();
        data.addAll(newData);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StocksAdapter onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull StocksAdapter stocksAdapter, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class StockViewHolder extends RecyclerView.ViewHolder{
        TextView symbol;
        TextView companyName;
        TextView primaryExchange;
        TextView iexRealTimePrice;


        public StockViewHolder(@NonNull View itemView) {
            super(itemView);
            symbol = itemView.findViewById(R.id.tvSymbol);
            companyName = itemView.findViewById(R.id.tvCompanyName);
            primaryExchange = itemView.findViewById(R.id.tvPrimaryExchange);
            iexRealTimePrice = itemView.findViewById(R.id.tvIexRealTimePrice);
            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {

                }
            });

        }
    }
}
