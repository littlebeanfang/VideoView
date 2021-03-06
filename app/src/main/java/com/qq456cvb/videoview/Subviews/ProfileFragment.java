package com.qq456cvb.videoview.Subviews;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.littlebeanfang.comment.CommentListFragment;
import com.qq456cvb.videoview.R;
import com.qq456cvb.videoview.Subviews.Profile.ProfileConfigFragment;
import com.qq456cvb.videoview.Subviews.Profile.ProfileImageFragment;
import com.qq456cvb.videoview.Subviews.Profile.ProfileVideoFragment;

import jp.wasabeef.sample.CommentPanelRetSwitcher;

/**
 * Created by qq456cvb on 8/19/15.
 */
public class ProfileFragment extends Fragment {

    public interface OnProfileListener {
        void clearFragments();
    }

    private ImageView profileVideoButton;
    private ImageView profileReviewButton;
    private ImageView profileConfigButton;
    private ImageView profilePictureButton;
    private ImageView profileLocalButton;
    private OnProfileListener onProfileListener;
//    private ProfileReviewFragment profileReviewFragment;
    public CommentListFragment profileCommentFragment = new CommentListFragment();
    public ProfileImageFragment profileImageFragment = new ProfileImageFragment();
    private ProfileConfigFragment profileConfigFragment = new ProfileConfigFragment();
    public ProfileVideoFragment profileVideoFragment;
    private View mView;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        onProfileListener = (OnProfileListener) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        mView = inflater.inflate(R.layout.profile, container, false);
        findViews();
        bindOnClickListeners();
        setDefaultFragment();
        return mView;
    }

    private void setDefaultFragment()
    {
        // add all the fragments
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.content_middle_and_right, profileCommentFragment);
        transaction.add(R.id.content_middle_and_right, profileImageFragment);
        transaction.add(R.id.content_middle_and_right, profileConfigFragment);
        transaction.hide(profileCommentFragment);
        transaction.hide(profileImageFragment);
        transaction.hide(profileConfigFragment);
        transaction.commit();
    }

    private void findViews() {
        profileVideoButton = (ImageView)mView.findViewById(R.id.profile_video_img);
        profileReviewButton = (ImageView)mView.findViewById(R.id.profile_article_img);
        profileConfigButton = (ImageView)mView.findViewById(R.id.profile_config_img);
        profilePictureButton = (ImageView)mView.findViewById(R.id.profile_picture_img);
        profileLocalButton = (ImageView)mView.findViewById(R.id.profile_local_img);
    }

    private void bindOnClickListeners() {
        profileVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onProfileListener.clearFragments();
                clearFragments();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putBoolean("online", true);
                profileVideoFragment = new ProfileVideoFragment();
                profileVideoFragment.setArguments(bundle);
                transaction.add(R.id.content_middle_and_right, profileVideoFragment);
                transaction.commit();
            }
        });

        profileReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onProfileListener.clearFragments();
                clearFragments();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.show(profileCommentFragment);
                transaction.commit();
                CommentPanelRetSwitcher cprs=(CommentPanelRetSwitcher)getActivity();
                cprs.getCommentList();
            }
        });

        profileConfigButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onProfileListener.clearFragments();
                clearFragments();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.show(profileConfigFragment);
                transaction.commit();
            }
        });

        profilePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onProfileListener.clearFragments();
                clearFragments();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.show(profileImageFragment);
                transaction.commit();
            }
        });

        profileLocalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onProfileListener.clearFragments();
                clearFragments();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putBoolean("online", false);
                profileVideoFragment = new ProfileVideoFragment();
                profileVideoFragment.setArguments(bundle);
                transaction.add(R.id.content_middle_and_right, profileVideoFragment);
                transaction.commit();
            }
        });
    }

    public void toggleFullscreen(boolean fullscreen) {
        if (profileVideoFragment != null) {
            profileVideoFragment.toggleFullscreen(fullscreen);
        }
    }

    public void clearFragments() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.hide(profileCommentFragment);
        transaction.hide(profileImageFragment);
        transaction.hide(profileConfigFragment);
        if (profileVideoFragment != null) {
            transaction.remove(profileVideoFragment);
        }
        transaction.commit();
    }
}