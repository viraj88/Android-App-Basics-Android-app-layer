package com.example.notify;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Books_RecyclerAdapter extends RecyclerView.Adapter<Books_RecyclerAdapter.MyViewHolder> {
    Context context;
    ArrayList<books> book = new ArrayList<>();

    public Books_RecyclerAdapter(Context context, ArrayList<books> book) {
        this.context = context;
        this.book = book;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.title.setText(book.get(position).getName());
        holder.id.setText(book.get(position).getId());
        holder.Author.setText(book.get(position).getAuthor());
        holder.year.setText(book.get(position).getYear());
        holder.image.setImageResource(book.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return book.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView id,title,Author,year;
        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            image=itemView.findViewById(R.id.bookImage);
            id = itemView.findViewById(R.id.bookID);
            title = itemView.findViewById(R.id.bookTitle);
            Author = itemView.findViewById(R.id.bookAuthor);
            year = itemView.findViewById(R.id.bookYear);

        }
    }
}

