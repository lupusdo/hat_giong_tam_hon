package brand.com.hatgiongtamhon;

import android.app.Activity;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class Control {
    private Activity activity;

    public Control(Activity activity) {
        this.activity = activity;
    }
    public void loadBannerAd(LinearLayout linearLayout)
    {
        MobileAds.initialize(activity, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdView adview = new AdView(activity);
        adview.setAdUnitId(activity.getResources().getString(R.string.banner_id));
        adview.setAdSize(AdSize.BANNER);
        linearLayout.addView(adview);
        adview.setAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                //Toast.makeText(activity, loadAdError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        AdRequest adRequest = new AdRequest.Builder().build();
        adview.loadAd(adRequest);


    }
}
