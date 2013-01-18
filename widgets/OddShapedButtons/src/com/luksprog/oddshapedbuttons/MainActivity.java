package com.luksprog.oddshapedbuttons;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	private OnClickListener mListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			if (v.getId() == R.id.whiteBtn) {
				Toast.makeText(MainActivity.this, "Click on white!!!",
						Toast.LENGTH_SHORT).show();
			} else if (v.getId() == R.id.blackBtn) {
				Toast.makeText(MainActivity.this, "Click on black!!!",
						Toast.LENGTH_SHORT).show();
			}

		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// place some OnClickListeners
		findViewById(R.id.whiteBtn).setOnClickListener(mListener);
		findViewById(R.id.blackBtn).setOnClickListener(mListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public static class PassThroughButton extends Button {

		public PassThroughButton(Context context, AttributeSet attrs) {
			super(context, attrs);
		}

		@Override
		public boolean onTouchEvent(MotionEvent event) {
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				final StateListDrawable bkg = (StateListDrawable) getBackground();
				final Bitmap b = ((BitmapDrawable) bkg.getCurrent())
						.getBitmap();
				int color = b.getPixel((int) event.getX(), (int) event.getY());
				if (color == 0) {
					return false;
				}
			}
			return super.onTouchEvent(event);
		}

	}

}
