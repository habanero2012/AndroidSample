package jp.live.sato1101.ui;

import jp.live.sato1101.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

public class AnimationByXMLActivity extends Activity {

	private ImageButton mFlipImage;
	private Animation mShrink, mGrow;

    private boolean mIsHead;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_animation_by_code_activity);
		
		createAnimation();
	}
	
	private void createAnimation() {
		mFlipImage = (ImageButton) findViewById(R.id.ui_animation_flipper_icon);
		mGrow = AnimationUtils.loadAnimation(this, R.anim.grow);
		mShrink = AnimationUtils.loadAnimation(this, R.anim.shrink);
		mShrink.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {}
			
			@Override
			public void onAnimationRepeat(Animation animation) {}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				if(mIsHead) {
					mFlipImage.setImageResource(android.R.drawable.arrow_up_float);
					mIsHead = false;
				} else {
					mFlipImage.setImageResource(R.drawable.ic_launcher);
					mIsHead = true;
				}
				mFlipImage.startAnimation(mGrow);
			}
		});
		mIsHead = true;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getAction() == MotionEvent.ACTION_DOWN) {
			mFlipImage.startAnimation(mShrink);
		    return true;	
		}
		
		return super.onTouchEvent(event);
	}
}
