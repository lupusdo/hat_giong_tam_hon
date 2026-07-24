package brand.com.hatgiongtamhon;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.os.LocaleListCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity2 extends AppCompatActivity {
    ImageButton btnBack, btnHome, btnNext;
    ImageView imgHeader;
    ViewPager2 viewPager2_2;
    List<String>  ndList,ttList;
    PagerAdapter2 pagerAdapter2;
    List<DataRecycler> dataRecyclerList;
    Toolbar toolbar2;
    int order, ordertab;
    FloatingActionButton fab2;
    private Boolean musicon = false, isEnglish ;
    SwitchCompat switchCompat2;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    private final int BACK_GROUND_1 = 1;
    private final int BACK_GROUND_2 = 2;
    private int currentBackGround = BACK_GROUND_2;
    private final int FONT_DEFAULT = 3;
    private final int FONT_PHISI = 4;
    private int currentFont = FONT_DEFAULT;
    private Control control;
    private LinearLayout bannerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        anhXa();
        getData();
        setData();
        hideBackNext();
        //  imageheader
        imgHeader.setImageResource(dataRecyclerList.get(order).getImg_recycler());
        initViewPager();
        // vuốt viewpager2
        viewPager2_2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                order = position;
                imgHeader.setImageResource(dataRecyclerList.get(order).getImg_recycler());
                if(order==0)
                {
                    btnBack.setVisibility(View.INVISIBLE);
                } else if (order== (ttList.size()-1))
                {
                    btnNext.setVisibility(View.INVISIBLE);
                } else
                {
                    btnBack.setVisibility(View.VISIBLE);
                    btnNext.setVisibility(View.VISIBLE);
                }
                super.onPageSelected(position);
            }
        });


        // set onclick button home
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(MainActivity2.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("key3", ordertab);
                bundle.putBoolean("key5",musicon);
                i2.putExtras(bundle);
                startActivity(i2);
            }
        });

        //set onclick button back
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnNext.setVisibility(View.VISIBLE);
                if (order >0)
                {
                    order = order-1;
                    imgHeader.setImageResource(dataRecyclerList.get(order).getImg_recycler());
                    viewPager2_2.setCurrentItem(order);
                    if (order==0){
                        btnBack.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });

        // set onclick button next
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnBack.setVisibility(View.VISIBLE);
                if (order < ndList.size()-1){
                    order = order+1;
                    imgHeader.setImageResource(dataRecyclerList.get(order).getImg_recycler());
                    viewPager2_2.setCurrentItem(order);
                    if (order==(ndList.size()-1)){
                        btnNext.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });

        //set floating action button
        if (musicon){
            fab2.setImageResource(R.drawable.music_off);
        } else {
            fab2.setImageResource(R.drawable.music_on);
        }

        // set onclick floating action button
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(MainActivity2.this, MyService.class);
                startService(i1);
                if ( musicon == true){
                    fab2.setImageResource(R.drawable.music_on);
                    musicon = false;
                }else {
                    fab2.setImageResource(R.drawable.music_off);
                    musicon = true;
                }
            }
        });
        control = new Control(this);
        control.loadBannerAd(bannerLayout);
    }
    private void setData(){
        dataRecyclerList= new ArrayList<>();

        dataRecyclerList.add(new DataRecycler(R.drawable.img1_dtdtg,0,getString(R.string.t1),0));
        dataRecyclerList.add(new DataRecycler(R.drawable.img2_gtctg,1,getString(R.string.t2),0));
        dataRecyclerList.add(new DataRecycler(R.drawable.img3_cncstt,2,getString(R.string.t3),0));
        dataRecyclerList.add(new DataRecycler(R.drawable.img4_nvdcm,3,getString(R.string.t4),0));
        dataRecyclerList.add(new DataRecycler(R.drawable.img5_tqgncd,4,getString(R.string.t5),0));
        dataRecyclerList.add(new DataRecycler(R.drawable.img6_cmkcm,5,getString(R.string.t6),0));
        dataRecyclerList.add(new DataRecycler(R.drawable.img7_bhvstt,6,getString(R.string.t7),0));
        dataRecyclerList.add(new DataRecycler(R.drawable.img8_mlco,7,getString(R.string.t8),0));
        dataRecyclerList.add(new DataRecycler(R.drawable.img9_bhvmnvb,8,getString(R.string.t9),0));
        dataRecyclerList.add(new DataRecycler(R.drawable.img10_hpod,9,getString(R.string.t10),0));
        dataRecyclerList.add(new DataRecycler(R.drawable.img30_ttl,10,getString(R.string.t30),0));
        dataRecyclerList.add(new DataRecycler(R.drawable.img31_dbgtbum,11,getString(R.string.t31),0));
        dataRecyclerList.add(new DataRecycler(R.drawable.img32_mnlmmq,12,getString(R.string.t32),0));
        dataRecyclerList.add(new DataRecycler(R.drawable.img33_ncdm,13,getString(R.string.t33),0));
        dataRecyclerList.add(new DataRecycler(R.drawable.img37_kkhlt,14,getString(R.string.t37),0));
        dataRecyclerList.add(new DataRecycler(R.drawable.img38_bhtne,15,getString(R.string.t38),0));
        dataRecyclerList.add(new DataRecycler(R.drawable.img39_cgpm,16,getString(R.string.t39),0));
        dataRecyclerList.add(new DataRecycler(R.drawable.img40_hsvum,17,getString(R.string.t40),0));
        dataRecyclerList.add(new DataRecycler(R.drawable.img41_smcln,18,getString(R.string.t41),0));
        dataRecyclerList.add(new DataRecycler(R.drawable.img42_ln,19,getString(R.string.t42),0));
        dataRecyclerList.add(new DataRecycler(R.drawable.img43_hvgxx,20,getString(R.string.t43),0));
        dataRecyclerList.add(new DataRecycler(R.drawable.img47_ccchhm,21,getString(R.string.t47),0));
        dataRecyclerList.add(new DataRecycler(R.drawable.img48_vdqt,22,getString(R.string.t48),0));
        dataRecyclerList.add(new DataRecycler(R.drawable.img50_hbl,23,getString(R.string.t50),0));
        dataRecyclerList.add(new DataRecycler(R.drawable.img51_bhttdv,24,getString(R.string.t51),0));

        dataRecyclerList.add(new DataRecycler(R.drawable.img52_dpcthk,25,getString(R.string.t52),0));
        dataRecyclerList.add(new DataRecycler(R.drawable.img53_cvn,26,getString(R.string.t53),0));
        dataRecyclerList.add(new DataRecycler(R.drawable.img54_gtccs,27,getString(R.string.t54),0));
        dataRecyclerList.add(new DataRecycler(R.drawable.img55_bnt,28,getString(R.string.t55),0));
        dataRecyclerList.add(new DataRecycler(R.drawable.img56_ntvnttc,29,getString(R.string.t56),0));
        dataRecyclerList.add(new DataRecycler(R.drawable.img57_kvcnv,30,getString(R.string.t57),0));
        dataRecyclerList.add(new DataRecycler(R.drawable.img58_m,31,getString(R.string.t58),0));
        dataRecyclerList.add(new DataRecycler(R.drawable.img59_kntgdmnc,32,getString(R.string.t59),0));
        dataRecyclerList.add(new DataRecycler(R.drawable.img60_nnsqt,33,getString(R.string.t60),0));
        dataRecyclerList.add(new DataRecycler(R.drawable.img61_lncnk,34,getString(R.string.t61),0));
        dataRecyclerList.add(new DataRecycler(R.drawable.img62_dltclhv,35,getString(R.string.t62),0));
        dataRecyclerList.add(new DataRecycler(R.drawable.img63_bcnk,36,getString(R.string.t63),0));
        dataRecyclerList.add(new DataRecycler(R.drawable.img64_tccgpka,37,getString(R.string.t64),0));

        dataRecyclerList.add(new DataRecycler(R.drawable.img11_cnpnt,38,getString(R.string.t11),1));
        dataRecyclerList.add(new DataRecycler(R.drawable.img12_cn,39,getString(R.string.t12),1));
        dataRecyclerList.add(new DataRecycler(R.drawable.img13_ct,40,getString(R.string.t13),1));
        dataRecyclerList.add(new DataRecycler(R.drawable.img14_cbmc,41,getString(R.string.t14),1));
        dataRecyclerList.add(new DataRecycler(R.drawable.img15_cctmmgcbk,42,getString(R.string.t15),1));
        dataRecyclerList.add(new DataRecycler(R.drawable.img16_ccdovcn,43,getString(R.string.t16),1));
        dataRecyclerList.add(new DataRecycler(R.drawable.img22_tm,44,getString(R.string.t22),1));
        dataRecyclerList.add(new DataRecycler(R.drawable.img23_xdlmk,45,getString(R.string.t23),1));
        dataRecyclerList.add(new DataRecycler(R.drawable.img24_hhtm,46,getString(R.string.t24),1));
        dataRecyclerList.add(new DataRecycler(R.drawable.img25_nmax,47,getString(R.string.t25),1));
        dataRecyclerList.add(new DataRecycler(R.drawable.img34_bdcb,48,getString(R.string.t34),1));
        dataRecyclerList.add(new DataRecycler(R.drawable.img44_dbtm,49,getString(R.string.t44),1));
        dataRecyclerList.add(new DataRecycler(R.drawable.img49_pqtn,50,getString(R.string.t49),1));

        dataRecyclerList.add(new DataRecycler(R.drawable.img65_btdtnvs,51,getString(R.string.t65),1));
        dataRecyclerList.add(new DataRecycler(R.drawable.img66_ct,52,getString(R.string.t66),1));
        dataRecyclerList.add(new DataRecycler(R.drawable.img67_lnvhd,53,getString(R.string.t67),1));
        dataRecyclerList.add(new DataRecycler(R.drawable.img68_mlbc,54,getString(R.string.t68),1));
        dataRecyclerList.add(new DataRecycler(R.drawable.img69_ktdtrnc,55,getString(R.string.t69),1));
        dataRecyclerList.add(new DataRecycler(R.drawable.img70_bvct,56,getString(R.string.t70),1));
        dataRecyclerList.add(new DataRecycler(R.drawable.img71_cba,57,getString(R.string.t71),1));
        dataRecyclerList.add(new DataRecycler(R.drawable.img72_bt,58,getString(R.string.t72),1));
        dataRecyclerList.add(new DataRecycler(R.drawable.img73_ndtv,59,getString(R.string.t73),1));
        dataRecyclerList.add(new DataRecycler(R.drawable.img74_dctkn,60,getString(R.string.t74),1));
        dataRecyclerList.add(new DataRecycler(R.drawable.img75_nllq,61,getString(R.string.t75),1));
        dataRecyclerList.add(new DataRecycler(R.drawable.img76_ndkgtt,62,getString(R.string.t76),1));

        dataRecyclerList.add(new DataRecycler(R.drawable.img17_bsal,63,getString(R.string.t17),2));
        dataRecyclerList.add(new DataRecycler(R.drawable.img18_mvtdnncynl,64,getString(R.string.t18),2));
        dataRecyclerList.add(new DataRecycler(R.drawable.img19_tb,65,getString(R.string.t19),2));
        dataRecyclerList.add(new DataRecycler(R.drawable.img20_asctb,66,getString(R.string.t20),2));
        dataRecyclerList.add(new DataRecycler(R.drawable.img21_nb,67,getString(R.string.t21),2));
        dataRecyclerList.add(new DataRecycler(R.drawable.img26_ccn,68,getString(R.string.t26),2));
        dataRecyclerList.add(new DataRecycler(R.drawable.img27_bcct,69,getString(R.string.t27),2));
        dataRecyclerList.add(new DataRecycler(R.drawable.img28_mtgmt,70,getString(R.string.t28),2));
        dataRecyclerList.add(new DataRecycler(R.drawable.img29_dt,71,getString(R.string.t29),2));
        dataRecyclerList.add(new DataRecycler(R.drawable.img35_lnnmm,72,getString(R.string.t35),2));
        dataRecyclerList.add(new DataRecycler(R.drawable.img36_yhky,73,getString(R.string.t36),2));
        dataRecyclerList.add(new DataRecycler(R.drawable.img45_cbv,74,getString(R.string.t45),2));
        dataRecyclerList.add(new DataRecycler(R.drawable.img46_tydk,75,getString(R.string.t46),2));

        dataRecyclerList.add(new DataRecycler(R.drawable.img77_tydt,76,getString(R.string.t77),2));
        dataRecyclerList.add(new DataRecycler(R.drawable.img78_ttmd,77,getString(R.string.t78),2));
        dataRecyclerList.add(new DataRecycler(R.drawable.img79_ndv,78,getString(R.string.t79),2));
        dataRecyclerList.add(new DataRecycler(R.drawable.img80_hpvb,79,getString(R.string.t80),2));
        dataRecyclerList.add(new DataRecycler(R.drawable.img81_tthh,80,getString(R.string.t81),2));
        dataRecyclerList.add(new DataRecycler(R.drawable.img82_tcpm,81,getString(R.string.t82),2));
        dataRecyclerList.add(new DataRecycler(R.drawable.img83_nchg,82,getString(R.string.t83),2));
        dataRecyclerList.add(new DataRecycler(R.drawable.img84_dctt,83,getString(R.string.t84),2));
        dataRecyclerList.add(new DataRecycler(R.drawable.img85_ccmnbt,84,getString(R.string.t85),2));
        dataRecyclerList.add(new DataRecycler(R.drawable.img86_mls,85,getString(R.string.t86),2));
        dataRecyclerList.add(new DataRecycler(R.drawable.img87_mpltbn,86,getString(R.string.t87),2));
        dataRecyclerList.add(new DataRecycler(R.drawable.img88_mxl,87,getString(R.string.t88),2));
        dataRecyclerList.add(new DataRecycler(R.drawable.img89_ccthhd,88,getString(R.string.t89),2));
        dataRecyclerList.add(new DataRecycler(R.drawable.img90_actoemlk,89,getString(R.string.t90),2));
        dataRecyclerList.add(new DataRecycler(R.drawable.img91_dvyntn,90,getString(R.string.t91),2));
        dataRecyclerList.add(new DataRecycler(R.drawable.img92_nltkdtl,91,getString(R.string.t92),2));
        dataRecyclerList.add(new DataRecycler(R.drawable.img93_tb,92,getString(R.string.t93),2));
        dataRecyclerList.add(new DataRecycler(R.drawable.img94_ttmm,93,getString(R.string.t94),2));
        dataRecyclerList.add(new DataRecycler(R.drawable.img95_smcnc,94,getString(R.string.t95),2));
        dataRecyclerList.add(new DataRecycler(R.drawable.img96_cccntvek,95,getString(R.string.t96),2));
        dataRecyclerList.add(new DataRecycler(R.drawable.img97_hctbnbyla,96,getString(R.string.t97),2));
        dataRecyclerList.add(new DataRecycler(R.drawable.img98_hmgyt,97,getString(R.string.t98),2));
        dataRecyclerList.add(new DataRecycler(R.drawable.img99_acctb,98,getString(R.string.t99),2));
        dataRecyclerList.add(new DataRecycler(R.drawable.img100_btkng,99,getString(R.string.t100),2));

        ndList = new ArrayList<>();

        ndList.add(getString(R.string.nd1));
        ndList.add(getString(R.string.nd2));
        ndList.add(getString(R.string.nd3));
        ndList.add(getString(R.string.nd4));
        ndList.add(getString(R.string.nd5));
        ndList.add(getString(R.string.nd6));
        ndList.add(getString(R.string.nd7));
        ndList.add(getString(R.string.nd8));
        ndList.add(getString(R.string.nd9));
        ndList.add(getString(R.string.nd10));
        ndList.add(getString(R.string.nd30));
        ndList.add(getString(R.string.nd31));
        ndList.add(getString(R.string.nd32));
        ndList.add(getString(R.string.nd33));
        ndList.add(getString(R.string.nd37));
        ndList.add(getString(R.string.nd38));
        ndList.add(getString(R.string.nd39));
        ndList.add(getString(R.string.nd40));
        ndList.add(getString(R.string.nd41));
        ndList.add(getString(R.string.nd42));
        ndList.add(getString(R.string.nd43));
        ndList.add(getString(R.string.nd47));
        ndList.add(getString(R.string.nd48));
        ndList.add(getString(R.string.nd50));
        ndList.add(getString(R.string.nd51));

        ndList.add(getString(R.string.nd52));
        ndList.add(getString(R.string.nd53));
        ndList.add(getString(R.string.nd54));
        ndList.add(getString(R.string.nd55));
        ndList.add(getString(R.string.nd56));
        ndList.add(getString(R.string.nd57));
        ndList.add(getString(R.string.nd58));
        ndList.add(getString(R.string.nd59));
        ndList.add(getString(R.string.nd60));
        ndList.add(getString(R.string.nd61));
        ndList.add(getString(R.string.nd62));
        ndList.add(getString(R.string.nd63));
        ndList.add(getString(R.string.nd64));

        ndList.add(getString(R.string.nd11));
        ndList.add(getString(R.string.nd12));
        ndList.add(getString(R.string.nd13));
        ndList.add(getString(R.string.nd14));
        ndList.add(getString(R.string.nd15));
        ndList.add(getString(R.string.nd16));
        ndList.add(getString(R.string.nd22));
        ndList.add(getString(R.string.nd23));
        ndList.add(getString(R.string.nd24));
        ndList.add(getString(R.string.nd25));
        ndList.add(getString(R.string.nd34));
        ndList.add(getString(R.string.nd44));
        ndList.add(getString(R.string.nd49));

        ndList.add(getString(R.string.nd65));
        ndList.add(getString(R.string.nd66));
        ndList.add(getString(R.string.nd67));
        ndList.add(getString(R.string.nd68));
        ndList.add(getString(R.string.nd69));
        ndList.add(getString(R.string.nd70));
        ndList.add(getString(R.string.nd71));
        ndList.add(getString(R.string.nd72));
        ndList.add(getString(R.string.nd73));
        ndList.add(getString(R.string.nd74));
        ndList.add(getString(R.string.nd75));
        ndList.add(getString(R.string.nd76));

        ndList.add(getString(R.string.nd17));
        ndList.add(getString(R.string.nd18));
        ndList.add(getString(R.string.nd19));
        ndList.add(getString(R.string.nd20));
        ndList.add(getString(R.string.nd21));
        ndList.add(getString(R.string.nd26));
        ndList.add(getString(R.string.nd27));
        ndList.add(getString(R.string.nd28));
        ndList.add(getString(R.string.nd29));
        ndList.add(getString(R.string.nd35));
        ndList.add(getString(R.string.nd36));
        ndList.add(getString(R.string.nd45));
        ndList.add(getString(R.string.nd46));

        ndList.add(getString(R.string.nd77));
        ndList.add(getString(R.string.nd78));
        ndList.add(getString(R.string.nd79));
        ndList.add(getString(R.string.nd80));
        ndList.add(getString(R.string.nd81));
        ndList.add(getString(R.string.nd82));
        ndList.add(getString(R.string.nd83));
        ndList.add(getString(R.string.nd84));
        ndList.add(getString(R.string.nd85));
        ndList.add(getString(R.string.nd86));
        ndList.add(getString(R.string.nd87));
        ndList.add(getString(R.string.nd88));
        ndList.add(getString(R.string.nd89));
        ndList.add(getString(R.string.nd90));
        ndList.add(getString(R.string.nd91));
        ndList.add(getString(R.string.nd92));
        ndList.add(getString(R.string.nd93));
        ndList.add(getString(R.string.nd94));
        ndList.add(getString(R.string.nd95));
        ndList.add(getString(R.string.nd96));
        ndList.add(getString(R.string.nd97));
        ndList.add(getString(R.string.nd98));
        ndList.add(getString(R.string.nd99));
        ndList.add(getString(R.string.nd100));

        ttList = new ArrayList<>();

        ttList.add(getString(R.string.t1));
        ttList.add(getString(R.string.t2));
        ttList.add(getString(R.string.t3));
        ttList.add(getString(R.string.t4));
        ttList.add(getString(R.string.t5));
        ttList.add(getString(R.string.t6));
        ttList.add(getString(R.string.t7));
        ttList.add(getString(R.string.t8));
        ttList.add(getString(R.string.t9));
        ttList.add(getString(R.string.t10));
        ttList.add(getString(R.string.t30));
        ttList.add(getString(R.string.t31));
        ttList.add(getString(R.string.t32));
        ttList.add(getString(R.string.t33));
        ttList.add(getString(R.string.t37));
        ttList.add(getString(R.string.t38));
        ttList.add(getString(R.string.t39));
        ttList.add(getString(R.string.t40));
        ttList.add(getString(R.string.t41));
        ttList.add(getString(R.string.t42));
        ttList.add(getString(R.string.t43));
        ttList.add(getString(R.string.t47));
        ttList.add(getString(R.string.t48));
        ttList.add(getString(R.string.t50));
        ttList.add(getString(R.string.t51));

        ttList.add(getString(R.string.t52));
        ttList.add(getString(R.string.t53));
        ttList.add(getString(R.string.t54));
        ttList.add(getString(R.string.t55));
        ttList.add(getString(R.string.t56));
        ttList.add(getString(R.string.t57));
        ttList.add(getString(R.string.t58));
        ttList.add(getString(R.string.t59));
        ttList.add(getString(R.string.t60));
        ttList.add(getString(R.string.t61));
        ttList.add(getString(R.string.t62));
        ttList.add(getString(R.string.t63));
        ttList.add(getString(R.string.t64));


        ttList.add(getString(R.string.t11));
        ttList.add(getString(R.string.t12));
        ttList.add(getString(R.string.t13));
        ttList.add(getString(R.string.t14));
        ttList.add(getString(R.string.t15));
        ttList.add(getString(R.string.t16));
        ttList.add(getString(R.string.t22));
        ttList.add(getString(R.string.t23));
        ttList.add(getString(R.string.t24));
        ttList.add(getString(R.string.t25));
        ttList.add(getString(R.string.t34));
        ttList.add(getString(R.string.t44));
        ttList.add(getString(R.string.t49));

        ttList.add(getString(R.string.t65));
        ttList.add(getString(R.string.t66));
        ttList.add(getString(R.string.t67));
        ttList.add(getString(R.string.t68));
        ttList.add(getString(R.string.t69));
        ttList.add(getString(R.string.t70));
        ttList.add(getString(R.string.t71));
        ttList.add(getString(R.string.t72));
        ttList.add(getString(R.string.t73));
        ttList.add(getString(R.string.t74));
        ttList.add(getString(R.string.t75));
        ttList.add(getString(R.string.t76));

        ttList.add(getString(R.string.t17));
        ttList.add(getString(R.string.t18));
        ttList.add(getString(R.string.t19));
        ttList.add(getString(R.string.t20));
        ttList.add(getString(R.string.t21));
        ttList.add(getString(R.string.t26));
        ttList.add(getString(R.string.t27));
        ttList.add(getString(R.string.t28));
        ttList.add(getString(R.string.t29));
        ttList.add(getString(R.string.t35));
        ttList.add(getString(R.string.t36));
        ttList.add(getString(R.string.t45));
        ttList.add(getString(R.string.t46));

        ttList.add(getString(R.string.t77));
        ttList.add(getString(R.string.t78));
        ttList.add(getString(R.string.t79));
        ttList.add(getString(R.string.t80));
        ttList.add(getString(R.string.t81));
        ttList.add(getString(R.string.t82));
        ttList.add(getString(R.string.t83));
        ttList.add(getString(R.string.t84));
        ttList.add(getString(R.string.t85));
        ttList.add(getString(R.string.t86));
        ttList.add(getString(R.string.t87));
        ttList.add(getString(R.string.t88));
        ttList.add(getString(R.string.t89));
        ttList.add(getString(R.string.t90));
        ttList.add(getString(R.string.t91));
        ttList.add(getString(R.string.t92));
        ttList.add(getString(R.string.t3));
        ttList.add(getString(R.string.t94));
        ttList.add(getString(R.string.t95));
        ttList.add(getString(R.string.t96));
        ttList.add(getString(R.string.t97));
        ttList.add(getString(R.string.t98));
        ttList.add(getString(R.string.t99));
        ttList.add(getString(R.string.t100));
    }
    private void getData(){
        //lay du lieu tu main activity
        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        order = bundle.getInt("key1");
        ordertab = bundle.getInt("key2");
        musicon = bundle.getBoolean("key4");
    }
    private void anhXa(){
        btnBack = findViewById(R.id.btn_back);
        btnNext = findViewById(R.id.btn_next);
        btnHome = findViewById(R.id.btn_home);
        imgHeader = findViewById(R.id.img_2);
        viewPager2_2 = findViewById(R.id.viewPager2_2);
        toolbar2 = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar2);
        fab2 = findViewById(R.id.fab2);
        bannerLayout= findViewById(R.id.bannerLayout2);
    }
    private void initViewPager()
    {
        pagerAdapter2 = new PagerAdapter2(this,ndList,ttList);
        viewPager2_2.setAdapter(pagerAdapter2);
        viewPager2_2.setCurrentItem(order);
    }
    private void hideBackNext()
    {
        if (order == 0){
            btnBack.setVisibility(View.INVISIBLE);
        }
        if (order == (ndList.size()-1)){
            btnNext.setVisibility(View.INVISIBLE);
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_option, menu);
        if (currentBackGround == BACK_GROUND_1)
        {
            MenuItem item = menu.findItem(R.id.background_1);
            item.setChecked(true);
        } else if (currentBackGround == BACK_GROUND_2) {
            MenuItem item = menu.findItem(R.id.background_2);
            item.setChecked(true);
        }
        if (currentFont == FONT_DEFAULT) {
            MenuItem item = menu.findItem(R.id.default_font);
            item.setChecked(true);
        } else if (currentFont == FONT_PHISI) {
            MenuItem item = menu.findItem(R.id.philosopher_font);
            item.setChecked(true);
        }
        MenuItem itemSwitch = menu.findItem(R.id.language);
        switchCompat2 = (SwitchCompat) itemSwitch.getActionView();
        switchCompat2.setTextOff("vi");
        switchCompat2.setTextOn("en");
        switchCompat2.setShowText(true);
        switchCompat2.setTextColor(getResources().getColor(R.color.white,getResources().newTheme()));
        switchCompat2.setThumbTintList(ColorStateList
                .valueOf(ContextCompat.getColor(getApplicationContext(), R.color.my_dark_primary)));
        switchCompat2.setTrackTintList(ColorStateList
                .valueOf(ContextCompat.getColor(getApplicationContext(), R.color.my_light_primary)));
        readFile();
        switchCompat2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if( isChecked){
                LocaleListCompat appLocales = LocaleListCompat.forLanguageTags("en");
                AppCompatDelegate.setApplicationLocales(appLocales);
            } else {
                LocaleListCompat appLocales = LocaleListCompat.forLanguageTags("vi");
                AppCompatDelegate.setApplicationLocales(appLocales);
            }
            saveFile();
        });
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
         if (item.getItemId()==R.id.increase_text)
         {
             pagerAdapter2.increaseText();
         }
         else if (item.getItemId()==R.id.decrease_text)
         {
             pagerAdapter2.decreaseText();
         }
         else if (item.getItemId()==R.id.background_1) {
             if (currentBackGround != BACK_GROUND_1)
             {
                 viewPager2_2.setBackgroundResource(R.drawable.greenbackground2);
                 currentBackGround = BACK_GROUND_1;
                 item.setChecked(true);
             }
         }
         else if (item.getItemId()==R.id.background_2) {
             if ( currentBackGround != BACK_GROUND_2)
             {
                 viewPager2_2.setBackgroundResource(R.drawable.background);
                 currentBackGround=BACK_GROUND_2;
                 item.setChecked(true);
             }
         } else if (item.getItemId()==R.id.default_font) {
             if (currentFont != FONT_DEFAULT)
             {
                 // change font default
                 pagerAdapter2.changeDefaultFont();
                 currentFont = FONT_DEFAULT;
                 item.setChecked(true);
             }
         } else if (item.getItemId()==R.id.philosopher_font) {
             if(currentFont!=FONT_PHISI){
                 // change philosopher font
                 pagerAdapter2.changePhilosopherFont();
                 currentFont= FONT_PHISI;
                 item.setChecked(true);
             }
         }
        return true;
    }
    private void readFile()
    {
        sharedPref = getSharedPreferences("myfile",MODE_PRIVATE);
        isEnglish = sharedPref.getBoolean("keylanguage", false);
        switchCompat2.setChecked(isEnglish);
    }
    private void saveFile()
    {
        isEnglish = switchCompat2.isChecked();
        sharedPref = getSharedPreferences("myfile",MODE_PRIVATE);
        editor = sharedPref.edit();
        editor.putBoolean("keylanguage",isEnglish);
        editor.apply();
    }
    private void changeLanguage(Activity activity, String languge)
    {
        Locale locale = new Locale(languge);
        locale.setDefault(locale);
        Resources resources = activity.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics() );
    }
}