package app.moviebase.androidx.widget.appbar;

import android.widget.ImageView;

public class ImageScaleAppBarStateChangeListener extends PercentAppBarStateChangeListener {

  public static final int DEFAULT_PERCENTAGE_TO_ANIMATE = 40;

  private final ImageView imageView;
  private final int percentageToAnimate;
  private boolean isShown = true;

  public ImageScaleAppBarStateChangeListener(ImageView imageView, int percentageToAnimate) {
    this.imageView = imageView;
    this.percentageToAnimate = percentageToAnimate;
  }

  @Override
  public void onChanged(int percentage) {
    if (percentage >= percentageToAnimate && isShown) {
      isShown = false;

      imageView.animate()
        .scaleY(0.5f).scaleX(0.5f)
        .setDuration(400)
        .start();
    }

    if (percentage <= percentageToAnimate && !isShown) {
      isShown = true;

      imageView.animate()
        .scaleY(1).scaleX(1)
        .start();
    }
  }

}

