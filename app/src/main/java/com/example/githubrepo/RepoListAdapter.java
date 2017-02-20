package com.example.githubrepo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.githubrepo.models.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.text.format.DateUtils.FORMAT_SHOW_DATE;

/**
 * Created by sarahneo on 20/2/17.
 */

public class RepoListAdapter extends RecyclerView.Adapter<RepoListAdapter.ViewHolder> {

    private LayoutInflater inflater = null;
    private AppCompatActivity mActivity;
    private List<Repository> mRepoList;

    public RepoListAdapter(AppCompatActivity activity, List<Repository> repoList) {
        this.mActivity = activity;
        this.mRepoList = repoList;
        inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public RepoListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.list_item_repo, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final RepoListAdapter.ViewHolder holder, int position) {

        final Repository item = mRepoList.get(position);

        holder.tvFullName.setText(item.getFullName());
        holder.tvDesc.setText(item.getDesc());
        holder.tvLanguage.setText(item.getLanguage());
        holder.tvStars.setText(item.getStargazersCount());
        holder.tvLastUpdated.setText(DateUtils.getRelativeDateTimeString(
                mActivity,
                convertStringToTimeMillis(item.getUpdatedAt()),
                DateUtils.MINUTE_IN_MILLIS,
                DateUtils.WEEK_IN_MILLIS,
                FORMAT_SHOW_DATE));
    }

    @Override
    public int getItemCount() {
        return mRepoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_full_name) TextView tvFullName;
        @BindView(R.id.tv_desc) TextView tvDesc;
        @BindView(R.id.tv_language) TextView tvLanguage;
        @BindView(R.id.tv_stars) TextView tvStars;
        @BindView(R.id.tv_forks) TextView tvForks;
        @BindView(R.id.tv_last_updated) TextView tvLastUpdated;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

//        @OnClick(R.id.ll_repo_container)
//        void viewDetails() {
//            Repository repository = mRepoList.get(getAdapterPosition());
//
//
//        }

    }

    private long convertStringToTimeMillis(String dateString) {
        long milliseconds = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        try {
            Date d = sdf.parse(dateString);
            milliseconds = d.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return milliseconds;
    }
}
