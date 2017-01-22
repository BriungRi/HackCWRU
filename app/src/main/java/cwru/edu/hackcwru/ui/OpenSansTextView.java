package cwru.edu.hackcwru.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class OpenSansTextView extends TextView{

    public OpenSansTextView(Context context) {
        super(context);
        init();
    }

    public OpenSansTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public OpenSansTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface openSans = Typeface.createFromAsset(getContext().getAssets(), "font/OpenSans-Regular.ttf");
            setTypeface(openSans);
        }
    }
}
