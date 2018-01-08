package xiehuang.com.android.xiehuangmusic.jingcl.test_rc_liuj;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;

import xiehuang.com.android.xiehuangmusic.R;

@SuppressLint("AppCompatCustomView")
public class HeaderHintView extends TextView {

    AnimatorSet mAnimSet;
    public HeaderHintView(Context context) {
        this(context, null);
    }

    public HeaderHintView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HeaderHintView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        String text = context.getResources().getString(R.string.discover_video_updata_complete);
        int color = context.getResources().getColor(R.color.header_hint_view_text_color);
        int textSize = context.getResources().getDimensionPixelSize(R.dimen.header_hint_view_textsize);
        setTextColor(color);
        setText(text);
        setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        setGravity(TEXT_ALIGNMENT_CENTER);
    }

    @Override
    public void setVisibility(int visibility) {
        if(VISIBLE == visibility){
            if(mAnimSet != null){
                mAnimSet.cancel();
                mAnimSet = null;
            }

            ObjectAnimator scaleX1 = ObjectAnimator.ofFloat(this, "scaleX", 0.6f, 1.02f);
            ObjectAnimator scaleY1 = ObjectAnimator.ofFloat(this, "scaleY", 0.6f, 1.02f);
            scaleX1.setDuration(300);
            scaleY1.setDuration(300);
            AnimatorSet scale1 = new AnimatorSet();
            scale1.setInterpolator(new AccelerateDecelerateInterpolator());
            scale1.playTogether(scaleX1,scaleY1);

            final ObjectAnimator scaleX2 = ObjectAnimator.ofFloat(this, "scaleX", 1.02f, 1f);
            final ObjectAnimator scaleY2 = ObjectAnimator.ofFloat(this, "scaleY", 1.02f, 1f);
            scaleX2.setDuration(200);
            scaleY2.setDuration(200);
            AnimatorSet scale2 = new AnimatorSet();
            scale2.setInterpolator(new AccelerateDecelerateInterpolator());
            scale2.playTogether(scaleX2,scaleY2);

            AnimatorSet scale = new AnimatorSet();
            scale.playSequentially(scale1, scale2);

            ObjectAnimator alpha1 = ObjectAnimator.ofFloat(this,"alpha",0f,0.85f);
            alpha1.setDuration(66);
            ObjectAnimator alpha2 = ObjectAnimator.ofFloat(this,"alpha",0.85f,1f);
            alpha2.setDuration(234);

            AnimatorSet alpha = new AnimatorSet();
            alpha.setInterpolator(new AccelerateDecelerateInterpolator());
            alpha.playSequentially(alpha1, alpha2);

            mAnimSet = new AnimatorSet();
            mAnimSet.playTogether(scale, alpha);
            mAnimSet.start();
        }


        super.setVisibility(visibility);
    }
}
