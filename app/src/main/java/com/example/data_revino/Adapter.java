    package com.example.data_revino;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ListViewHolder>{


    private ArrayList<Client_Data> dataList;
    private OnItemClickListener mListener;
    private Context mContext;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public Adapter(Context mContext, ArrayList<Client_Data> dataList) {
        this.mContext = mContext;
        this.dataList = dataList;
    }

    @Override
    public ListViewHolder onCreateViewHolder (ViewGroup parent,int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_list, parent, false);
        return new ListViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder (ListViewHolder holder,int position){
        holder.profile_name.setText(dataList.get(position).getName());
        holder.profile_number.setText(dataList.get(position).getPhone());
        holder.profile_id.setText(dataList.get(position).getId());
        holder.profile_time.setText(dataList.get(position).getTime());
        holder.profile_order.setText(dataList.get(position).getOrder());
        holder.profile_payment.setText(dataList.get(position).getPayment());
    }

    @Override
    public int getItemCount () {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        private TextView profile_name, profile_number,profile_id,profile_time,profile_order,profile_payment;
        private RelativeLayout relativeLayout;

        public ListViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            profile_name = itemView.findViewById(R.id.profile_name_list);
            profile_number = itemView.findViewById(R.id.profile_phone_list);
            profile_id = itemView.findViewById(R.id.profile_id_list);
            profile_time = itemView.findViewById(R.id.profile_time_list);
            profile_order = itemView.findViewById(R.id.profile_order_list);
            profile_payment = itemView.findViewById(R.id.profile_payment_list);

            relativeLayout = itemView.findViewById(R.id.rv_layout_list);

            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
