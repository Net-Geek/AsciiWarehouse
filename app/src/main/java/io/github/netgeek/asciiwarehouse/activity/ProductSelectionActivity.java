package io.github.netgeek.asciiwarehouse.activity;

import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import io.github.netgeek.asciiwarehouse.R;
import io.github.netgeek.asciiwarehouse.constant.Constants;
import io.github.netgeek.asciiwarehouse.fragment.ProductSearchFragment;
import io.github.netgeek.asciiwarehouse.fragment.ProductSelectionFragment;

/**
 * Product Selection Activity that handles the product selection fragment and search fragment
 */
public class ProductSelectionActivity extends AppCompatActivity {

    ProductSelectionFragment productFragment;
    ProductSearchFragment productSearchFragment;
    private Menu mMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_selection);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        productFragment = (ProductSelectionFragment) getSupportFragmentManager().findFragmentById(R.id.product_selection_fragment);
        productSearchFragment = new ProductSearchFragment();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_product_selection, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchItem.getIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);

        if (getSpanCount() == 2){
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
                setMenuItemsVisibility(menu, item, getSpanCount() == 2 ? menu.findItem(R.id.grid_view) : menu.findItem(R.id.list_view), false);
                getSupportFragmentManager().beginTransaction().replace(R.id.product_selection_fragment, productSearchFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(null).commit();
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                setMenuItemsVisibility(menu, item, getSpanCount() == 2 ? menu.findItem(R.id.grid_view) : menu.findItem(R.id.list_view), true);
                onBackPressed();
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
            setSpanCount(1);
        } else if (item == gridViewItem) {
            productFragment.setSpanCount(2);
            gridViewItem.setVisible(false);
            listViewItem.setVisible(true);
            setSpanCount(2);
        } else if (item == settingsViewItem) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setMenuItemsVisibility(Menu menu, MenuItem searchItem, MenuItem viewTypeItem, boolean visible) {
        for (int i=0; i<menu.size(); ++i) {
            MenuItem item = menu.getItem(i);
            if (!visible && item != searchItem){
                item.setVisible(false);
            } else if (visible && item != searchItem && item != viewTypeItem) {
                item.setVisible(true);
            }
        }
    }

    private int getSpanCount() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        return sharedPreferences.getInt(Constants.spanCountKey, 2);
    }

    private void setSpanCount(int spanCount) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit();
        editor.putInt(Constants.spanCountKey, spanCount);
        editor.commit();
    }
}
