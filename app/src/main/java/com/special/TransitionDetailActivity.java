package com.special;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;
import com.special.appslattur.DatabaseHelper.DataBaseHelper;
import com.special.utils.UICircularImage;
import com.special.utils.UIParallaxScroll;

import java.util.ArrayList;

import static com.nineoldandroids.view.ViewPropertyAnimator.animate;

public class TransitionDetailActivity extends Activity {

	//Configuration
    public static final int DURATION = 500; // in ms
    public static final String PACKAGE = "IDENTIFY";
    
    //UI Elements
    private UICircularImage mImageView;
    private TextView mTextView;
    private RelativeLayout mLayoutContainer;
    private FrameLayout mNavigationTop;
	private TextView mNavigationTitle;
	private Button mNavigationBackBtn;
	private TextView mTitleView;
    private TextView longDetailView;
	private UICircularImage discountImg;


    //Db helper
    private DataBaseHelper dbHelper;

    //Vars
    private int delta_top;
    private int delta_left;
    private float scale_width;
    private float scale_height;
	String title;
	int imgId;
    int dbId;
	
	String lor1 = "Pellentesque in luctus dui, non egestas nisl. Donec sapien ante, faucibus a sem at, tincidunt dictum quam. Sed vel blandit neque. Maecenas tincidunt at sem vel sodales. Nullam dignissim eros id tellus commodo, eu vulputate massa accumsan.<br><br>Ut eget volutpat turpis. Praesent ac auctor nisi, sed imperdiet augue. Aenean consequat est vel odio molestie pellentesque. Suspendisse rhoncus velit dolor, at ultrices nulla ullamcorper a. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Vivamus nec felis elit. Mauris at erat euismod leo sagittis gravida in id magna.";
	String lor2 = "Donec ornare eleifend turpis. Cras consectetur at neque sit amet bibendum. Nulla metus dui, porta vel mollis vitae, ornare sit amet lectus. Integer imperdiet quam eleifend nisl dictum vehicula. Suspendisse pharetra aliquet porttitor. Maecenas nec pharetra purus. Sed scelerisque suscipit faucibus. Etiam hendrerit tellus risus, et interdum tortor facilisis quis.";
	String lor3 = "Etiam tristique, sapien non rhoncus vestibulum, erat augue suscipit velit, vestibulum viverra justo nibh ut nibh. Vivamus pulvinar pharetra scelerisque. Curabitur ullamcorper tristique lacus.";
	String lor4 = "Maecenas id tortor sed purus ultricies tempor. In vulputate feugiat iaculis. Phasellus sem turpis, adipiscing sit amet lacus in, aliquet aliquet eros. Integer euismod, orci et tincidunt iaculis, odio odio vulputate lectus, sed rutrum sapien odio eget sem.";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_transition);
        dbHelper = new DataBaseHelper(getBaseContext());

        ((UIParallaxScroll) findViewById(R.id.scroller)).setOnScrollChangedListener(mOnScrollChangedListener);

        mImageView = (UICircularImage) findViewById(R.id.image_view);
        mTextView = (TextView) findViewById(R.id.contact);
	    mNavigationTop = (FrameLayout) findViewById(R.id.layout_top);   
	    mNavigationTitle = (TextView) findViewById(R.id.titleBar);
        mLayoutContainer = (RelativeLayout) findViewById(R.id.bg_layout);
	    mTitleView = (TextView) findViewById(R.id.title);
	    mNavigationBackBtn = (Button) findViewById(R.id.title_bar_left_menu);
	    TextView mSum = (TextView) findViewById(R.id.sumary);
	    discountImg = (UICircularImage) findViewById(R.id.afslatturImg);
        longDetailView = (TextView) findViewById(R.id.longDetails);





	    mNavigationTop.getBackground().setAlpha(0);   
	    mNavigationTitle.setVisibility(View.INVISIBLE);
	    
        mImageView.bringToFront();
	    
        Bundle bundle = getIntent().getExtras();

        final int top = bundle.getInt(PACKAGE + ".top");
        final int left = bundle.getInt(PACKAGE + ".left");
        final int width = bundle.getInt(PACKAGE + ".width");
        final int height = bundle.getInt(PACKAGE + ".height");     
	    title = bundle.getString("title");
	    String sum = bundle.getString("descr");
	    imgId = bundle.getInt("img");
        dbId = bundle.getInt("dbId");
        /*
        Setup image discount, using 10% as placeholder
         */
        discountImg.setImageResource(R.drawable.discount_10);

	    //Our Animation initialization
        ViewTreeObserver observer = mImageView.getViewTreeObserver();
        observer.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
        	@Override
        	public boolean onPreDraw() {

                    mImageView.getViewTreeObserver().removeOnPreDrawListener(this);

                    int[] screen_location = new int[2];
                    mImageView.getLocationOnScreen(screen_location);

                    delta_left = left - screen_location[0];
                    delta_top = top - screen_location[1];

                    scale_width = (float) width / mImageView.getWidth();
                    scale_height = (float) height / mImageView.getHeight();

                    runEnterAnimation();

                    return true;
                }
        });
        



	    mTitleView.setText(title);
	    mSum.setText(sum);
	    mImageView.setImageResource(imgId);
	    mNavigationTitle.setText(title);

        longDetailView.setText(dbHelper.getLongDescById(dbId));


	    mNavigationBackBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                onBackPressed();
            }

        });
	    



	      

    }
	
    @Override
    public void onBackPressed() {

        runExitAnimation(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        });

    }

    @Override
    public void finish() {

        super.finish();
        overridePendingTransition(0, 0);
    }
    
    private ArrayList<ListItem> generateData(){
        ArrayList<ListItem> items = new ArrayList<ListItem>();


 
        return items;
    }
   
    private void runEnterAnimation() {

        ViewHelper.setPivotX(mImageView, 0.f);
        ViewHelper.setPivotY(mImageView, 0.f);
        ViewHelper.setScaleX(mImageView, scale_width);
        ViewHelper.setScaleY(mImageView, scale_height);
        ViewHelper.setTranslationX(mImageView, delta_left);
        ViewHelper.setTranslationY(mImageView, delta_top);

        animate(mImageView).
                setDuration(DURATION).
                scaleX(1.f).
                scaleY(1.f).
                translationX(0.f).
                translationY(0.f).
                setInterpolator(new DecelerateInterpolator()).
        		setListener(new AnimatorListenerAdapter() {  

        			@Override
	        	    public void onAnimationEnd(Animator animation) {        	    
        			}
        		});

        ObjectAnimator bg_anim = ObjectAnimator.ofFloat(mLayoutContainer, "alpha", 0f, 1f);
        bg_anim.setDuration(DURATION);
        bg_anim.start();

    }

    private void runExitAnimation(final Runnable end_action) {

    	ViewHelper.setPivotX(mImageView, 0.f);
    	ViewHelper.setPivotY(mImageView, 0.f);
    	ViewHelper.setScaleX(mImageView, 1.f);
    	ViewHelper.setScaleY(mImageView, 1.f);
    	ViewHelper.setTranslationX(mImageView, 0.f);
    	ViewHelper.setTranslationY(mImageView, 0.f);

    	animate(mImageView).
                setDuration(DURATION).
                scaleX(scale_width).
                scaleY(scale_height).
                translationX(delta_left).
                translationY(delta_top).
                setInterpolator(new DecelerateInterpolator()).
                setListener(new AnimatorListenerAdapter() {  

                	@Override
                	    public void onAnimationEnd(Animator animation) {
                	    end_action.run();
                	}
                });

        ObjectAnimator bg_anim = ObjectAnimator.ofFloat(mLayoutContainer, "alpha", 1f, 0f);
        bg_anim.setDuration(DURATION);
        bg_anim.start();

    }
    
    private UIParallaxScroll.OnScrollChangedListener mOnScrollChangedListener = new UIParallaxScroll.OnScrollChangedListener() {
        public void onScrollChanged(ScrollView who, int l, int t, int oldl, int oldt) {
        	//Difference between the heights, important to not add margin or remove mNavigationTitle.
        	final float headerHeight = ViewHelper.getY(mTitleView) - (mNavigationTop.getHeight() - mTitleView.getHeight());
            final float ratio = (float) Math.min(Math.max(t, 0), headerHeight) / headerHeight;
            final int newAlpha = (int) (ratio * 255);
            mNavigationTop.getBackground().setAlpha(newAlpha);
              
            Animation animationFadeIn = AnimationUtils.loadAnimation(TransitionDetailActivity.this,R.anim.fadein);
            Animation animationFadeOut = AnimationUtils.loadAnimation(TransitionDetailActivity.this,R.anim.fadeout);
            
            if (newAlpha == 255 && mNavigationTitle.getVisibility() != View.VISIBLE && !animationFadeIn.hasStarted()){
            	mNavigationTitle.setVisibility(View.VISIBLE);
            	mNavigationTitle.startAnimation(animationFadeIn);
            } else if (newAlpha < 255 && !animationFadeOut.hasStarted() && mNavigationTitle.getVisibility() != View.INVISIBLE)  { 	
            	mNavigationTitle.startAnimation(animationFadeOut);
            	mNavigationTitle.setVisibility(View.INVISIBLE);
            	
            }
           
        }
    };
   
}