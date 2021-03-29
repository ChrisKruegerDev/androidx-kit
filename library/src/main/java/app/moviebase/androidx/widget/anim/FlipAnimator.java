package app.moviebase.androidx.widget.anim;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.view.View;
import app.moviebase.androidx.R;

public class FlipAnimator {

  /**
   * Performs flip animation on two views
   */
  public static void flipView(Context context, final View back, final View front, boolean showFront) {
    if (showFront) {
      AnimatorSet leftIn = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.card_flip_left_in);
      AnimatorSet rightOut = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.card_flip_right_out);
      leftIn.setTarget(back);
      rightOut.setTarget(front);

      AnimatorSet showFrontAnim = new AnimatorSet();
      showFrontAnim.playTogether(leftIn, rightOut);
      showFrontAnim.start();
    }
    else {
      AnimatorSet leftOut = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.card_flip_left_out);
      AnimatorSet rightIn = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.card_flip_right_in);

      leftOut.setTarget(back);
      rightIn.setTarget(front);

      AnimatorSet showBackAnim = new AnimatorSet();
      showBackAnim.playTogether(rightIn, leftOut);
      showBackAnim.start();
    }
  }


  /**
   * Performs flip animation on two views
   */
  public static void flipSingleView(Context context, final View alphaView, boolean out, View flipView) {
    if (out) {
      AnimatorSet rightOut = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.card_flip_right_out_360);
      AnimatorSet leftIn = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.card_alpha_in);
      leftIn.setTarget(alphaView);
      rightOut.setTarget(flipView);

      AnimatorSet showFrontAnim = new AnimatorSet();
      showFrontAnim.playTogether(leftIn, rightOut);
      showFrontAnim.start();
    }
    else {
      AnimatorSet leftOut = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.card_alpha_out);
      AnimatorSet rightIn = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.card_flip_right_in_360);
      leftOut.setTarget(alphaView);
      rightIn.setTarget(flipView);

      AnimatorSet showBackAnim = new AnimatorSet();
      showBackAnim.playTogether(rightIn, leftOut);
      showBackAnim.start();
    }
  }
}
