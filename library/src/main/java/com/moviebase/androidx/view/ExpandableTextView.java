package com.moviebase.androidx.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.AppCompatTextView;
import com.moviebase.androidx.R;


public class ExpandableTextView extends AppCompatTextView implements View.OnClickListener {

  private int maxLinesCollapsed;
  private boolean expanded = false;

  public ExpandableTextView(Context context) {
    super(context);
    maxLinesCollapsed = Integer.MAX_VALUE;
  }

  public ExpandableTextView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context, attrs);
  }

  public ExpandableTextView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context, attrs);
  }

  private void init(Context context, AttributeSet attrs) {
    setFocusable(true);
    setClickable(true);
    setOnClickListener(this);
    TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ExpandableTextView, 0, 0);
    try {
      maxLinesCollapsed = ta.getInt(R.styleable.ExpandableTextView_maxLinesCollapsed, Integer.MAX_VALUE);
    }
    finally {
      ta.recycle();
    }

    setMaxLines(maxLinesCollapsed);
  }

  @Override
  public void onClick(View view) {
    expanded = !expanded;

    if (expanded) {
      setMaxLines(Integer.MAX_VALUE);
      setEllipsize(null);
    }
    else {
      setMaxLines(maxLinesCollapsed);
      setEllipsize(TextUtils.TruncateAt.END);
    }
  }
}
