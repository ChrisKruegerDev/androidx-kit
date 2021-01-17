package com.moviebase.androidx.widget.pager;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

public class ScalePageTransformer implements ViewPager.PageTransformer {

  private static final float SCALE = 0.9f;
  private static final float SCALE_FACTOR = 0.15f;
  private static final float MAX_SCALE = SCALE + 1 * SCALE_FACTOR;

  private static final float MIN_ALPHA = 0.5f;

  @Override
  public void transformPage(@NonNull View view, float position) {
    if (position <= -1 || position >= 1) {
      view.setScaleX(SCALE);
      view.setScaleY(SCALE);
      view.setAlpha(MIN_ALPHA);
    }
    else {
      // -1 and 1
      // 1 - 0.4 = 0,6 * 0,1
      float normalize = 1 - Math.abs(position);
      float scaleFactor = Math.min(MAX_SCALE, SCALE + normalize * SCALE_FACTOR);
      view.setScaleX(scaleFactor);
      view.setScaleY(scaleFactor);

      float alpha = Math.min(1f, MIN_ALPHA + normalize);
      view.setAlpha(Math.max(MIN_ALPHA, alpha));

    }
  }

}
