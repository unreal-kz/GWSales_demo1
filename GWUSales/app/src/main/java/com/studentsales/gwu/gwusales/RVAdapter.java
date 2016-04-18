package com.studentsales.gwu.gwusales;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Baubek on 04/13/16.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ItemViewHolder> {

    private List<Items> items;

    public RVAdapter(List<Items> items) {
        this.items = items;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_template, parent, false);
        ItemViewHolder ivh = new ItemViewHolder(v);
        return ivh;
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        holder.itemNameTV.setText(items.get(position).itemName);
        holder.itemDescpTV.setText(items.get(position).itemDescp);
        holder.itemPicIV.setImageBitmap(items.get(position).itemPhotoId);

        holder.cardView.setClickable(true);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),
                        "Clicked: "+ items.get(position).itemId,
                        Toast.LENGTH_SHORT).show();
                v.getContext().startActivity(new Intent(v.getContext().getApplicationContext(), MapsActivity.class).putExtra("location",items.get(position).itemId));
            }
        });

        /*holder.itemPicIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Clicked"+v.getId(), Toast.LENGTH_SHORT).show();
                v.getContext().startActivity(new Intent(v.getContext().getApplicationContext(), MapsActivity.class));
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView itemNameTV;
        TextView itemDescpTV;
        ImageView itemPicIV;

        public ItemViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.cardView);
            itemNameTV = (TextView)itemView.findViewById(R.id.itemName);
            itemDescpTV = (TextView)itemView.findViewById(R.id.itemDescription);
            itemPicIV = (ImageView)itemView.findViewById(R.id.itemPic);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
