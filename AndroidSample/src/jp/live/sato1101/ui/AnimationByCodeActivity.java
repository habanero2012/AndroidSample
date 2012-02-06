package jp.live.sato1101.ui;

import jp.live.sato1101.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class AnimationByCodeActivity extends Activity {

	View mViewToAnimate;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_animation_by_code_activity);
		
		Button button = (Button) findViewById(R.id.ui_animation_slide_right_boggle_button);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(mViewToAnimate.getVisibility() == View.VISIBLE) {
					Animation out = AnimationUtils.makeOutAnimation(getApplicationContext(), true);
					mViewToAnimate.startAnimation(out);
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
		
		mViewToAnimate = findViewById(R.id.ui_animation_the_view);
	}
}
