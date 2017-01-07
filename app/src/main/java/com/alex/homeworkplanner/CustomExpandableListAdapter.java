package com.alex.homeworkplanner;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Alex on 12/27/2016.
 */

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<String> classTitles;
    private ArrayList<String> expandedItems;

    public CustomExpandableListAdapter(Context context, ArrayList<String> title, ArrayList<String> items){
        this.context = context;
        classTitles = title;
        expandedItems = items;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition){
        return expandedItems.get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition){
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition, boolean isLastChild,
                             View convertView, ViewGroup parent){
        final String expandedListText = (String)getChild(listPosition,expandedListPosition);

        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.class_item,null);
        }

        TextView expandedListTextView = (TextView) convertView.findViewById(R.id.expanded_class_item);
        expandedListTextView.setText(expandedListText);
        return convertView;
    }


    @Override
    public int getChildrenCount(int listPosition){
        return expandedItems.size();
    }

    @Override
    public Object getGroup(int listPosition){
        return this.classTitles.get(listPosition);
    }

    @Override
    public int getGroupCount(){
        return this.expandedItems.size();
    }

    @Override
    public long getGroupId(int listPosition){
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded, View convertView, ViewGroup parent){
        String listTitle = (String) getGroup(listPosition);
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.class_group,null);
        }

        ImageView imageView = (ImageView) convertView.findViewById(R.id.expand_icon);
        if(isExpanded){
            imageView.setImageResource(R.drawable.expand_icon);
        }else{
            imageView.setImageResource(R.drawable.collapse_icon);
        }

        TextView listTitleTextView = (TextView) convertView.findViewById(R.id.class_title_info);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);
        return convertView;
    }

    @Override
    public boolean hasStableIds(){
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition){
        return true;
    }



}
