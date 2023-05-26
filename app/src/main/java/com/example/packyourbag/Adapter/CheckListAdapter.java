package com.example.packyourbag.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.packyourbag.Constants.MyConstants;
import com.example.packyourbag.Database.RoomDB;
import com.example.packyourbag.Models.Items;
import com.example.packyourbag.R;

import java.util.List;


public class CheckListAdapter extends RecyclerView.Adapter<CheckListViewHolder> {

    Context context;
    List<Items> itemsList;
    RoomDB database;
    String show;

    public CheckListAdapter() {
    }

    public CheckListAdapter(Context context, List<Items> itemsList, RoomDB database, String show) {
        this.context = context;
        this.itemsList = itemsList;
        this.database = database;
        this.show = show;

        //if you have no lists when you press a category, then sow a toast
        if (itemsList.size() == 0) {
            Toast.makeText(context.getApplicationContext(), "", Toast.LENGTH_SHORT).show();
        }
    }

    @NonNull
    @Override
    public CheckListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CheckListViewHolder(LayoutInflater.from(context).inflate(R.layout.check_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CheckListViewHolder holder, int position) {
        holder.checkBox.setText(itemsList.get(position).getItemname()); //we get the items name
        holder.checkBox.setChecked(itemsList.get(position).getChecked());
        //we get the status of the item

        if (MyConstants.FALSE_STRING.equals(show)) {  //must watch video 2:02:30
            holder.btnDelete.setVisibility(View.GONE);
            holder.layout.setBackground(context.getResources().getDrawable(R.drawable.border_one));
        } else {
            //this block is used for removing border and changing the row color of a row when a list item is checked
            if (itemsList.get(position).getChecked()) {
                holder.layout.setBackgroundColor(Color.parseColor("#8e546f"));
            } else {
                holder.layout.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.border_one));
            }
        }

        //whenever we click on the checkbox we have to update the stuff, for that the below code is used

        holder.checkBox.setOnClickListener(new View.OnClickListener() { //this will update the check variable of the item
            @Override
            public void onClick(View view) {
                Boolean check = holder.checkBox.isChecked();
                database.mainDao().checkUncheck(itemsList.get(position).getID(), check);
                if (MyConstants.FALSE_STRING.equals(show)) {
                    itemsList = database.mainDao().getAllSelected(true);
                    notifyDataSetChanged();
                } else {
                    itemsList.get(position).setChecked(check);
                    notifyDataSetChanged();
                    Toast tostMessage = null;

                    if (tostMessage != null) {
                        tostMessage.cancel();
                    }
                    if (itemsList.get(position).getChecked()) {
                        tostMessage = Toast.makeText(context, "(" + holder.checkBox.getText() + ") Packed", Toast.LENGTH_SHORT);
                    } else {
                        tostMessage = Toast.makeText(context, "(" + holder.checkBox.getText() + ") Un-Packed", Toast.LENGTH_SHORT);
                    }
                    tostMessage.show();
                }
            }
        });

        //We have worked on checked and unchecked stuff, now we have to work on delete button
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {    //used to show the alert when you press on delete button
                new AlertDialog.Builder(context)
                        .setTitle("Delete ( " + itemsList.get(position).getItemname()+" )")
                        .setMessage("Are you sure?")
                        .setPositiveButton("confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                database.mainDao().delete(itemsList.get(position)); //used to remove the item from database
                                itemsList.remove(itemsList.get(position)); //used to remove the item from the recyclear view
                                notifyDataSetChanged();
                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(context, "Cancelled", Toast.LENGTH_SHORT).show();
                            }
                        }).setIcon(R.drawable.ic_delete)
                        .show();
            }
        });

    }

    @Override
    public int getItemCount() { //This will give how many rows we want, if we have 10 items, then we need 10 rows
        return itemsList.size();
    }
}

//here we connect the backend with the front page
class CheckListViewHolder extends RecyclerView.ViewHolder {

    LinearLayout layout;
    CheckBox checkBox;
    Button btnDelete;

    public CheckListViewHolder(@NonNull View itemView) {
        super(itemView);
        layout = itemView.findViewById(R.id.linearLayout);
        checkBox = itemView.findViewById(R.id.checkbox);
        btnDelete = itemView.findViewById(R.id.btnDelete);
    }
}
