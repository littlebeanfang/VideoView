package com.qq456cvb.videoview.Adapters.Profile;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.GridView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.qq456cvb.videoview.CustomWidgets.BigImageDialog;
import com.qq456cvb.videoview.R;
import com.qq456cvb.videoview.Subviews.Profile.ProfileImageFragment;
import com.qq456cvb.videoview.Utils.UserImage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qq456cvb on 8/22/15.
 */
public class ProfileImageSetAdapter extends ArrayAdapter<UserImage> {

    private GridView gridView;
    private ProfileImageFragment profileImageFragment;
    public ProfileImageSetAdapter(ProfileImageFragment profileImageFragment, List<UserImage> imageGridWithTexts, GridView gridView1) {
        super(profileImageFragment.getActivity(), 0, imageGridWithTexts);
        this.profileImageFragment = profileImageFragment;
        this.gridView = gridView1;
    }

    class ViewHolder{
        public NetworkImageView imageView;
        public CheckBox checkBox;
    }


    public View getView(final int position, View convertView, ViewGroup parent) {
        final Activity activity = (Activity) getContext();
        final UserImage image = getItem(position);
        // Load the image and set it on the ImageView
        String imageUrl = image.getURL();
        imageUrl = imageUrl.replaceAll(" ", "%20");
        imageUrl = imageUrl.replaceAll("&amp;", "&");
        final String url = imageUrl;
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            // Inflate the views from XML
            LayoutInflater inflater = activity.getLayoutInflater();
            convertView = inflater.inflate(R.layout.profile_image_edit_grid_item, null);
            holder.imageView = (NetworkImageView) convertView.findViewById(R.id.edit_network_image_view);
            holder.checkBox = (CheckBox)convertView.findViewById(R.id.grid_item_edit_checkbox);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.checkBox.setText("");
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.checkBox.isChecked()) {
                    profileImageFragment.addDelete(image);
                } else {
                    profileImageFragment.popDelete(image);
                }
            }
        });

        final ImageLoader imLoader = new ImageLoader(profileImageFragment.mQueue, profileImageFragment.bitmapLruCache);
        holder.imageView.setDefaultImageResId(R.drawable.my_loading);
        holder.imageView.setErrorImageResId(R.drawable.error);
        holder.imageView.setImageUrl(imageUrl, imLoader);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> urls = new ArrayList<String>();
                for (int i = 0; i < profileImageFragment.detailList.size(); i++) {
                    urls.add(profileImageFragment.detailList.get(i).getURL());
                }
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("urls", urls);
                bundle.putInt("idx", position);
                DialogFragment dialogFragment = new BigImageDialog();
                dialogFragment.setArguments(bundle);
                dialogFragment.show(activity.getFragmentManager(), "bigImage");

            }
        });
        return convertView;
    }

}