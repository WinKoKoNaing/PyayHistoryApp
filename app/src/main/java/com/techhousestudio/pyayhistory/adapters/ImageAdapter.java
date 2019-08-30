package com.techhousestudio.pyayhistory.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterInside;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.techhousestudio.pyayhistory.R;
import com.techhousestudio.pyayhistory.models.Education;

import java.util.List;

import timber.log.Timber;

public class ImageAdapter extends PagerAdapter {
    private Context context;

    private List<Education> viewPagerImages;

    ImageAdapter(Context context, List<Education> viewPagerImages) {
        Timber.i("Call ImageAdapter");
        this.context = context;
        this.viewPagerImages = viewPagerImages;
    }

    @Override
    public int getCount() {
        Timber.i("Call getCount");
        return viewPagerImages.size();

    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        Timber.i("Call isViewFromObject");
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Timber.i("Call instantiateItem");
        View view = LayoutInflater.from(context).inflate(R.layout.view_pager_image_row, container,false);
        assert view != null;
        ImageView ivArticleImage = view.findViewById(R.id.ivArticleImageUri);
        TextView title = view.findViewById(R.id.tvTitles);
        title.setText(viewPagerImages.get(position).name);
        Glide.with(context).load(viewPagerImages.get(position).imageUri)
                .transform(new CenterInside(), new RoundedCorners(20))

                .into(ivArticleImage);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout) object);
    }
}
