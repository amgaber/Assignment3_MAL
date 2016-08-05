package com.mal.android.assignmentthree;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mal.android.assignmentthree.model.Facebook;
import com.squareup.picasso.Picasso;


import java.util.List;

/**
 * Created by toshiba1 on 8/4/2016.
 */
public class FacebookAdapter extends RecyclerView.Adapter<FacebookAdapter.ViewHolder> {

    private static final String TAG = FacebookAdapter.class.getSimpleName();
    private final List<Facebook> parsedFacebookDataList;
    private final FragmentActivity activity;

    public FacebookAdapter(FragmentActivity activity, List<Facebook> facebookData) {
        this.parsedFacebookDataList=facebookData;
        this.activity=activity;
        Log.v(TAG,"JSON LIST: "+facebookData);
    }

    @Override
    public FacebookAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        // create a new view for the adapter
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.facebook_list_row, viewGroup, false);


        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(FacebookAdapter.ViewHolder viewHolder, int i) {

        Facebook facebookData = parsedFacebookDataList.get(i);

        viewHolder.txtUserName.setText(facebookData.getUserName());
        viewHolder.txtPostTime.setText(facebookData.getPostTime());
        viewHolder.txtPostText.setText(facebookData.getPostText());

        Picasso.with(activity).load(facebookData.getUserPic()).fit().centerCrop()
                .placeholder(R.drawable.abc_ic_star_black_36dp)
                .error(R.drawable.abc_ic_star_black_36dp).into(viewHolder.imgUserPic);
        Picasso.with(activity).load(facebookData.getPostImage()).fit().centerCrop()
                .placeholder(R.drawable.abc_ic_star_black_36dp)
                .error(R.drawable.abc_ic_star_black_36dp).into(viewHolder.imgPostImage);

        viewHolder.txtLike.setText(facebookData.getLikes());
        viewHolder.txtComment.setText(facebookData.getComments());
        viewHolder.txtShare.setText(facebookData.getShares());

    }

    @Override
    public int getItemCount() {
        return parsedFacebookDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView txtUserName;
        public final TextView txtPostTime;
        public final TextView txtPostText;
        public final ImageView imgPostImage;
        public final ImageView imgUserPic;
        public final TextView txtShare;
        public final TextView txtLike;
        public final TextView txtComment;


        public ViewHolder(View itemView) {
            super(itemView);

            txtUserName = (TextView) itemView.findViewById(R.id.txtUserName);
            txtPostTime = (TextView) itemView.findViewById(R.id.txtPostTime);
            txtPostText = (TextView) itemView.findViewById(R.id.txtPostText);
            imgUserPic = (ImageView) itemView.findViewById(R.id.imgUserPic);
            imgPostImage = (ImageView) itemView.findViewById(R.id.imgPostImage);

            txtLike = (TextView) itemView.findViewById(R.id.txtLike);
            txtComment = (TextView) itemView.findViewById(R.id.txtComment);
            txtShare = (TextView) itemView.findViewById(R.id.txtShare);
        }
    }
}
