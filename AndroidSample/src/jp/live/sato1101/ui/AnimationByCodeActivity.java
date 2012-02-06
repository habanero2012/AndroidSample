/*
 * XMLではなく、コードでアニメーションを実装する
 * 
 * フェードイン、フェードアウトするサンプル
 * スケーリングとAnimationListenerで２つのアニメーションをつなげるサンプル
 */
package jp.live.sato1101.ui;

import jp.live.sato1101.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageButton;

public class AnimationByCodeActivity extends Activity {

	View mViewToAnimate;
	ImageButton mFlipImage;
	ScaleAnimation mShrink, mGrow;
	
	private boolean mIsHead;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_animation_by_code_activity);
		
		mViewToAnimate = findViewById(R.id.ui_animation_the_view);
		
		Button button = (Button) findViewById(R.id.ui_animation_slide_right_boggle_button);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(mViewToAnimate.getVisibility() == View.VISIBLE) {
					Animation out = AnimationUtils.makeOutAnimation(getApplicationContext(), true);
					mViewToAnimate.startAnimation(out);    // View(Viewを継承したクラス）に対してアニメーションできる
					mViewToAnimate.setVisibility(View.INVISIBLE);
				} else {
					Animation in = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in);
					mViewToAnimate.startAnimation(in);
					mViewToAnimate.setVisibility(View.VISIBLE);
				}
			}
		});
		
		button = (Button) findViewById(R.id.ui_animation_slide_up_boggle_button);
	    button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(mViewToAnimate.getVisibility() == View.VISIBLE) {
					Animation out = AnimationUtils.makeOutAnimation(getApplicationContext(), false);
					mViewToAnimate.startAnimation(out);
					mViewToAnimate.setVisibility(View.INVISIBLE);
				} else {
					Animation in = AnimationUtils.makeInChildBottomAnimation(getApplicationContext());
					mViewToAnimate.startAnimation(in);
					mViewToAnimate.setVisibility(View.VISIBLE);
				}
			}
		});
		
	    createAnimation();
	}
	
	private void createAnimation() {
		mFlipImage = (ImageButton) findViewById(R.id.ui_animation_flipper_icon);
		// x方向の大きさを1.0⇒0.0に、y方向の大きさは1.0のままにするアニメーション
		mShrink = new ScaleAnimation(1.0f, 0.0f, 1.0f, 1.0f,
				ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
				ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
		mShrink.setDuration(150);
		// x方向の大きさを0.0⇒1.0に、y方向の大きさは1.0のままにするアニメーション
		mGrow = new ScaleAnimation(0.0f, 1.0f, 1.0f, 1.0f,
				ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
				ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
		mGrow.setDuration(150);
		
		/*
		 * Animationの終了を通知されるListenerをセットして
		 * mShrinkが終わったらmGrowを実行して、２つのアニメーションをつなげる
		 */
		mShrink.setAnimationListener(new Animation.AnimationListener() {
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
