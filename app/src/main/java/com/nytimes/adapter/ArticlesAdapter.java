package com.nytimes.adapter;

import android.app.FragmentManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.nytimes.NYTimesArticleList;
import com.nytimes.R;
import com.nytimes.fragment.ArticleDetailFragment;
import com.nytimes.model.Response;
import com.nytimes.utils.Constant;
import com.nytimes.utils.FragmentHelper;



public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ViewHolder> {
    private Response response;
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtHeader;
        public TextView date;
        final public ImageView img_article_icon;
        public View layout;
        public TextView source;
        public TextView byLine;


        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader =  v.findViewById(R.id.title);
            img_article_icon = v.findViewById(R.id.img_article_icon);
            date = v.findViewById(R.id.date);
            source = v.findViewById(R.id.source);
            byLine = v.findViewById(R.id.byLine);


        }
    }

    public FragmentManager fragmentManager;
    public NYTimesArticleList nyTimesArticleList;
    public ArticlesAdapter(Response response, NYTimesArticleList nyTimesArticleList, FragmentManager fragmentManager) {
        this.response = response;
        this.fragmentManager = fragmentManager;
        this.nyTimesArticleList = nyTimesArticleList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.layout_fragment_row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArticleDetailFragment articleDetailFragment = new ArticleDetailFragment();
                Bundle bundle = new Bundle();
                bundle.putString(Constant.BUNDLE_ARTICLE_URL,response.getResults().get(position).getUrl());
                articleDetailFragment.setArguments(bundle);
                FragmentHelper.addAndInitFragmentWithBackStack(articleDetailFragment,R.id.fragment_content_container,fragmentManager);

            }
        });

        final String name = response.getResults().get(position).getTitle();
        holder.txtHeader.setText(name);
        holder.date.setText(response.getResults().get(position).getPublishedDate());

        holder.source.setText(response.getResults().get(position).getSource());
        holder.byLine.setText(response.getResults().get(position).getByline());


        Glide.with(nyTimesArticleList).load(response.getResults().get(position).getMedia().get(0).getMediaMetadata().get(0).getUrl()).asBitmap().centerCrop().into(new BitmapImageViewTarget(holder.img_article_icon) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(nyTimesArticleList.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                holder.img_article_icon.setImageDrawable(circularBitmapDrawable);
            }
        });

    }

    @Override
    public int getItemCount() {
        return response.getResults().size();
    }

}
