package com.app.entertainment.movies.ui.movieDetails;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.app.entertainment.movies.R;
import com.app.entertainment.movies.utils.CommonMethods;


public class ViewPagerAdapter extends PagerAdapter {

    private final Context context;
    private String[] imagesSourceArray;

    public ViewPagerAdapter(Context context, String[] imagesSourceArray) {
        this.context = context;
        this.imagesSourceArray = imagesSourceArray;
    }

    @Override
    public int getCount() {
        return imagesSourceArray.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        CommonMethods.renderImageInView(imagesSourceArray[position],imageView);

        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view, 0);
        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);

    }
}
