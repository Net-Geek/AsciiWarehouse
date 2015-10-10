package io.github.netgeek.asciiwarehouse.activity;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import io.github.netgeek.asciiwarehouse.R;
import io.github.netgeek.asciiwarehouse.fragment.ProductSelectionFragment;

public class ProductSelectionActivity extends AppCompatActivity {

    ProductSelectionFragment productFragment;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_selection);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

         productFragment = (ProductSelectionFragment) getSupportFragmentManager().findFragmentById(R.id.product_fragment);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_product_selection, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchItem.getIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);
        menu.findItem(R.id.grid_view).setVisible(false);

        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        MenuItem listViewItem = menu.findItem(R.id.list_view);
        MenuItem gridViewItem = menu.findItem(R.id.grid_view);
        MenuItem settingsViewItem = menu.findItem(R.id.action_settings);

        if (item == listViewItem){
            Log.e("list","view");
            productFragment.setSpanCount(1);
            listViewItem.setVisible(false);
            gridViewItem.setVisible(true);
        } else if (item == gridViewItem){
            Log.e("grid","view");
            productFragment.setSpanCount(2);
            gridViewItem.setVisible(false);
            listViewItem.setVisible(true);
        } else if (item == settingsViewItem){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
