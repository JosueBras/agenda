package br.univali.contacts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static br.univali.contacts.ContactListAdapter.ViewHolder;

public class ContactListAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final List<Contact> contacts;

    public ContactListAdapter(List<Contact> contacts) {
        contacts.sort(new Contact.Comparator());
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.contact_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(contacts.get(position).getName());
        holder.phone.setText(contacts.get(position).getPhone());
        holder.listTile.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("contact", contacts.get(position));
            Navigation
                    .findNavController(holder.itemView)
                    .navigate(R.id.action_from_contact_list_to_contact_details, bundle);
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout listTile;
        private TextView name;
        private TextView phone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            listTile = itemView.findViewById(R.id.list_tile);
            name = itemView.findViewById(R.id.name);
            phone = itemView.findViewById(R.id.phone);
        }
    }
}
