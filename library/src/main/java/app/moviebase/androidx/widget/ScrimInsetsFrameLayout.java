package app.moviebase.androidx.widget;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.R.style;
import com.google.android.material.R.styleable;

public class ScrimInsetsFrameLayout extends FrameLayout {
  Drawable insetForeground;
  Rect insets;
  private Rect tempRect;

  public ScrimInsetsFrameLayout(Context context) {
    this(context, (AttributeSet) null);
  }

  public ScrimInsetsFrameLayout(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public ScrimInsetsFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    this.tempRect = new Rect();
    TypedArray a = ThemeEnforcement.obtainStyledAttributes(context, attrs, styleable.ScrimInsetsFrameLayout, defStyleAttr, style.Widget_Design_ScrimInsetsFrameLayout, new int[0]);
    this.insetForeground = a.getDrawable(styleable.ScrimInsetsFrameLayout_insetForeground);
    a.recycle();
    this.setWillNotDraw(true);
    ViewCompat.setOnApplyWindowInsetsListener(this, new androidx.core.view.OnApplyWindowInsetsListener() {
      public WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets) {
        if (null == ScrimInsetsFrameLayout.this.insets) {
          ScrimInsetsFrameLayout.this.insets = new Rect();
        }

        ScrimInsetsFrameLayout.this.insets.set(insets.getSystemWindowInsetLeft(), insets.getSystemWindowInsetTop(), insets.getSystemWindowInsetRight(), insets.getSystemWindowInsetBottom());
        ScrimInsetsFrameLayout.this.onInsetsChanged(insets);
        ScrimInsetsFrameLayout.this.setWillNotDraw(!insets.hasSystemWindowInsets() || ScrimInsetsFrameLayout.this.insetForeground == null);
        ViewCompat.postInvalidateOnAnimation(ScrimInsetsFrameLayout.this);
        return insets.consumeSystemWindowInsets();
      }
    });
  }

  public void draw(@NonNull Canvas canvas) {
    super.draw(canvas);
    int width = this.getWidth();
    int height = this.getHeight();
    if (this.insets != null && this.insetForeground != null) {
      int sc = canvas.save();
      canvas.translate((float) this.getScrollX(), (float) this.getScrollY());
      this.tempRect.set(0, 0, width, this.insets.top);
      this.insetForeground.setBounds(this.tempRect);
      this.insetForeground.draw(canvas);
      this.tempRect.set(0, height - this.insets.bottom, width, height);
      this.insetForeground.setBounds(this.tempRect);
      this.insetForeground.draw(canvas);
      this.tempRect.set(0, this.insets.top, this.insets.left, height - this.insets.bottom);
      this.insetForeground.setBounds(this.tempRect);
      this.insetForeground.draw(canvas);
      this.tempRect.set(width - this.insets.right, this.insets.top, width, height - this.insets.bottom);
      this.insetForeground.setBounds(this.tempRect);
      this.insetForeground.draw(canvas);
      canvas.restoreToCount(sc);
    }

  }

  protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    if (this.insetForeground != null) {
      this.insetForeground.setCallback(this);
    }

  }

  protected void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    if (this.insetForeground != null) {
      this.insetForeground.setCallback((Callback) null);
    }

  }

  protected void onInsetsChanged(WindowInsetsCompat insets) {
  }


}
