package com.example.doctor_pad;

import android.content.Context;
import android.media.Image;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context mContext;
    String mname[],mdescription[],mlocation[];
    int mimgs[];

    public MyAdapter(Context context,String doctorname[],String descriptions[],String location[],int imgs[])
    {
        mContext = context;
        mname = doctorname;
        mdescription = descriptions;
        mlocation = location;
        mimgs = imgs;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.my_row_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        holder.mtv.setText(mname[position]);
        holder.mtv2.setText(mdescription[position]);
        holder.mtv3.setText(mlocation[position]);
        holder.miv.setImageResource(mimgs[position]);
    }

    @Override
    public int getItemCount() {
        return mname.length;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView mtv,mtv2,mtv3;
        ImageView miv;
        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            mtv = itemView.findViewById(R.id.textView3);
            mtv2 = itemView.findViewById(R.id.textView4);
            miv = itemView.findViewById(R.id.imageView2);
            mtv3 = itemView.findViewById(R.id.textView);
        }
    }
}

