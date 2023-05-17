package br.univali.contacts;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import br.univali.contacts.databinding.ActivityContactListBinding;

public class ContactListActivity extends AppCompatActivity {
    private AppBarConfiguration appBarConfiguration;
    private NavController navController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityContactListBinding binding = ActivityContactListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        navController = Navigation.findNavController(findViewById(R.id.nav_host));
        navController.addOnDestinationChangedListener((controller, destination, bundle) -> {
            if (destination.getId() == R.id.ContactListFragment) binding.fab.show();
            else binding.fab.hide();
        });
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        binding.fab.setOnClickListener(view -> {
            navController.navigate(R.id.action_from_contact_list_to_contact_details);
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }
}
