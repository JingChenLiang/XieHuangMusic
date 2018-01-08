package xiehuang.com.android.xiehuangmusic.jingcl.test_rc_liuj;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.RelativeLayout;


public class HeaderHintViewContainer extends RelativeLayout {
    private static final String TAG = HeaderHintViewContainer.class.getSimpleName();
    HeaderHintView mHeaderHintView;
    AnimatorSet mAnimationSet;
    public HeaderHintViewContainer(@NonNull Context context) {
        this(context, null);
    }

    public HeaderHintViewContainer(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HeaderHintViewContainer(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mHeaderHintView = new HeaderHintView(context);
        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.addRule(CENTER_IN_PARENT);
        addView(mHeaderHintView, lp);
    }

    public void setText(String text){
        if(TextUtils.isEmpty(text)){
            Log.e(TAG, "setText, text is empty, return.");
            return;
        }
        if(mHeaderHintView == null){
            Log.e(TAG, "setText, mHeaderHintView == null, return.");
            return;
        }
        mHeaderHintView.setText(text);
    }

    @Override
    public void setVisibility(int visibility) {
        if(VISIBLE == visibility){
            if(mAnimationSet != null){
                mAnimationSet.cancel();
                mAnimationSet = null;
            }
            setScaleX(0f);
            ObjectAnimator scaleX1 = ObjectAnimator.ofFloat(this, "scaleX", 0.8f, 1f);
            scaleX1.setDuration(234);

            ObjectAnimator alpha1 = ObjectAnimator.ofFloat(this,"alpha", 0f, 0.92f);
            alpha1.setDuration(66);
            alpha1.setInterpolator(new AccelerateDecelerateInterpolator());

            ObjectAnimator alpha2 = ObjectAnimator.ofFloat(this,"alpha", 0.92f, 0.9f);
            alpha2.setDuration(234);

            AnimatorSet beginAnim = new AnimatorSet();
            beginAnim.setInterpolator(new AccelerateDecelerateInterpolator());
            beginAnim.playTogether(scaleX1, alpha2);

            mAnimationSet = new AnimatorSet();
            mAnimationSet.playSequentially(alpha1, beginAnim);
            mAnimationSet.start();

            mHeaderHintView.setVisibility(VISIBLE);
        }
        super.setVisibility(visibility);
    }

}
