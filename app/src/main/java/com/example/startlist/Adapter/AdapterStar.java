package com.example.startlist.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.startlist.Bean.star;
import com.example.startlist.R;
import com.example.startlist.service.ListService;
import com.example.startlist.service.ListService;

import java.util.ArrayList;
import java.util.List;

public class AdapterStar extends RecyclerView.Adapter<AdapterStar.StartViewHolder> implements Filterable {

    private List<star> stars;
    private List<star> starsFull;
    private Context context;

    public AdapterStar(List<star> list, Context context) {
        this.stars = list;
        this.starsFull = new ArrayList<>(list);
        this.context = context;
    }

    @NonNull
    @Override
    public StartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.star_item, parent, false);
        final StartViewHolder holder = new StartViewHolder(v);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditDialog(v, holder);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull StartViewHolder holder, int position) {

        star currentStar = stars.get(position);


        holder.text.setText(currentStar.getName());
        holder.stars.setRating(currentStar.getStars());
        holder.idss.setText(String.valueOf(currentStar.getId()));


        Glide.with(context)
                .load(currentStar.getImg())
                .centerCrop()
                .error(R.drawable.hhh)
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return stars.size();
    }

    @Override
    public Filter getFilter() {
        return starFilter;
    }


    private Filter starFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<star> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(starsFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (star item : starsFull) {
                    if (item.getName().toLowerCase().startsWith(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            stars.clear();
            stars.addAll((List<star>) results.values);
            notifyDataSetChanged();
        }
    };

    private void showEditDialog(View v, final StartViewHolder holder) {
        View popup = LayoutInflater.from(context).inflate(R.layout.star_edit_item, null, false);
        final ImageView img = popup.findViewById(R.id.chfia);
        final RatingBar bar = popup.findViewById(R.id.stars);
        final TextView nom = popup.findViewById(R.id.nom);
        final TextView idss = popup.findViewById(R.id.idss);




        Bitmap bitmap = ((BitmapDrawable)((ImageView)v.findViewById(R.id.chfia)).getDrawable()).getBitmap();
        img.setImageBitmap(bitmap);
        bar.setRating(((RatingBar)v.findViewById(R.id.stars)).getRating());
        nom.setText(((TextView)v.findViewById(R.id.textViewss)).getText().toString());
        idss.setText(((TextView)v.findViewById(R.id.ids)).getText().toString());
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle("Notez :")
                .setMessage("Donner une note entre 1 et 5 :")
                .setView(popup)
                .setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        float s = bar.getRating();
                        int id=Integer.parseInt(idss.getText().toString());
                        star star = ListService.getInstance().findById(id);
                        star.setStars(s);
                        ListService.getInstance().update(star);
                        notifyItemChanged(holder.getAdapterPosition());
                    }
                })
                .setNegativeButton("Annuler", null)
                .create();

        dialog.show();
    }

    class StartViewHolder extends RecyclerView.ViewHolder {

        TextView text;
        TextView idss;
        ImageView img;
        RatingBar stars;
        RelativeLayout parent;

        public StartViewHolder(View itemView) {
            super(itemView);
            idss = itemView.findViewById(R.id.ids);
            text = itemView.findViewById(R.id.textViewss);
            img = itemView.findViewById(R.id.chfia);
            stars = itemView.findViewById(R.id.stars);
            parent = itemView.findViewById(R.id.delta);
        }
    }
}
