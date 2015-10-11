package io.github.netgeek.asciiwarehouse.activity;

import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import io.github.netgeek.asciiwarehouse.R;
import io.github.netgeek.asciiwarehouse.constant.Constants;
import io.github.netgeek.asciiwarehouse.fragment.ProductSelectionFragment;

/**
 * Product Selection Activity that handles the product selection fragment and search fragment
 */
public class ProductSelectionActivity extends AppCompatActivity {

    ProductSelectionFragment productFragment;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private Menu mMenu;
    private int mSpanCount = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_selection);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        productFragment = (ProductSelectionFragment) getSupportFragmentManager().findFragmentById(R.id.product_fragment);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sharedPreferences.edit();

        mSpanCount = sharedPreferences.getInt(Constants.spanCountKey, 2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_product_selection, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchItem.getIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);

        if (mSpanCount == 2){
            menu.findItem(R.id.list_view).setVisible(true);
            menu.findItem(R.id.grid_view).setVisible(false);
            productFragment.setSpanCount(2);
        } else {
            menu.findItem(R.id.list_view).setVisible(false);
            menu.findItem(R.id.grid_view).setVisible(true);
            productFragment.setSpanCount(1);
        }

        MenuItemCompat.setOnActionExpandListener(menu.findItem(R.id.action_search), new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                return true;
            }
        });

        this.mMenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        MenuItem listViewItem = mMenu.findItem(R.id.list_view);
        MenuItem gridViewItem = mMenu.findItem(R.id.grid_view);
        MenuItem settingsViewItem = mMenu.findItem(R.id.action_settings);

        if (item == listViewItem) {
            productFragment.setSpanCount(1);
            listViewItem.setVisible(false);
            gridViewItem.setVisible(true);
            editor.putInt(Constants.spanCountKey, 1);
            editor.commit();
        } else if (item == gridViewItem) {
            productFragment.setSpanCount(2);
            gridViewItem.setVisible(false);
            listViewItem.setVisible(true);
            editor.putInt(Constants.spanCountKey, 2);
            editor.commit();
        } else if (item == settingsViewItem) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
