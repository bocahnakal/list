/* The List powered by Creative Commons

   Copyright (C) 2014, 2015 Creative Commons

   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU Affero General Public License as published by
   the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU Affero General Public License for more details.

   You should have received a copy of the GNU Affero General Public License
   along with this program.  If not, see <http://www.gnu.org/licenses/>.

*/

package org.creativecommons.thelist.misc;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.creativecommons.thelist.R;
import org.creativecommons.thelist.adapters.CategoryListItem;
import org.creativecommons.thelist.layouts.CheckableRelativeLayout;

import java.util.List;

public class CategoryListAdapter extends BaseAdapter {
    public static final String TAG = CategoryListAdapter.class.getSimpleName();

    private Activity activity;
    private LayoutInflater inflater;
    private List<CategoryListItem> categoryListItems;

    public CategoryListAdapter(Activity activity, List<CategoryListItem> categoryItems) {
        this.activity = activity;
        this.categoryListItems = categoryItems;
    }

    @Override
    public int getCount() {
        return categoryListItems.size();
    }

    @Override
    public Object getItem(int location) {
        return categoryListItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (inflater == null) {
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_category, parent, false);
            holder = new ViewHolder();
            holder.checkableLayout = (CheckableRelativeLayout)convertView.findViewById(R.id.checkable_layout);
            holder.categoryNameLabel = (TextView)convertView.findViewById(R.id.category_title);
            holder.categoryCheckIcon = (ImageView)convertView.findViewById(R.id.category_checkmark);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        //Getting Data for the row
        CategoryListItem c = categoryListItems.get(position);
        //Item Name
        holder.categoryNameLabel.setText(c.getCategoryName().toUpperCase());

        //Item Background Color
        if(c.getCategoryColour() != null){
            holder.checkableLayout.setBackgroundColor((Color.parseColor(c.getCategoryColour())));
        } else {
            holder.checkableLayout.setBackgroundColor(activity.getResources().getColor(R.color.category_default));
        }

        //Set checkmarkView visibility
        if(c.getCategoryChecked()){
            holder.checkableLayout.getBackground().setAlpha(200);
            holder.categoryNameLabel.setTextColor(activity.getResources().getColor(R.color.secondary_text_material_dark));
            holder.categoryCheckIcon.setVisibility(View.VISIBLE);

        } else {
            holder.checkableLayout.getBackground().setAlpha(255);
            holder.categoryNameLabel.setTextColor(activity.getResources().getColor(R.color.primary_text_default_material_dark));
            holder.categoryCheckIcon.setVisibility(View.INVISIBLE);
        }

        return convertView;
    }

    private static class ViewHolder {
        CheckableRelativeLayout checkableLayout;
        TextView categoryNameLabel;
        ImageView categoryCheckIcon;
    }
}