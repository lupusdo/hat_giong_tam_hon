package brand.com.hatgiongtamhon;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.os.LocaleListCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import androidx.activity.OnBackPressedCallback;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private TabLayout mTablayout;
    private ImageView imgHeader;
    private ViewPager2 mViewPager2;
    private List<DataRecycler> dataRecyclerList,dataRecyclerList2,dataRecyclerList3;
    private List<DataPager> dataPagerList;
    private PagerAdapter viewPager2Adapter;
    int order_tab;
    FloatingActionButton fab;
    Boolean musicon, isEnglish ;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    SwitchCompat switchCompat1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        anhXa();
        ViewCompat.setOnApplyWindowInsetsListener(appBarLayout, (v, insets) -> {
                    Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());

                    // Đẩy Padding Top của AppBarLayout xuống để né Status Bar
                    v.setPadding(0, systemBars.top, 0, 0);

                    return insets;
        });


        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Kiểm tra nếu DrawerLayout đang mở thì đóng nó lại
                if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    // Nếu Drawer đã đóng, thoát ứng dụng hoặc quay lại màn hình trước
                    setEnabled(false); // Vô hiệu hóa callback này để tránh lặp vô tận
                    getOnBackPressedDispatcher().onBackPressed(); // Gọi lệnh back hệ thống
                    setEnabled(true); // Kích hoạt lại cho lần sau
                }
            }
        });
        setSupportActionBar(toolbar);

        //thêm Toggle cho navigation bar
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout,toolbar,R.string.open_nav,R.string.close_nav);
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        //lấy dữ liệu
        setData();
        getData();
        switchCompat1.setText(R.string.language_vietnamese);
        readFile();

        //set floating action button
        if (musicon){
            fab.setImageResource(R.drawable.music_off);
        } else {
            fab.setImageResource(R.drawable.music_on);
        }

        // onclick listener Floating action button
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(MainActivity.this, MyService.class);
                startService(i1);
                if ( musicon == true){
                    fab.setImageResource(R.drawable.music_on);
                    musicon = false;
                }else {
                    fab.setImageResource(R.drawable.music_off);
                    musicon = true;
                }
            }
        });

        viewPager2Adapter= new PagerAdapter(this, dataPagerList, new IClickItemListener() {
            @Override
            public void onClickItem(int order, int ordertab) {
                Intent i = new Intent(MainActivity.this, MainActivity2.class);
                Bundle bundle= new Bundle();
                bundle.putInt("key1",order);
                bundle.putInt("key2",ordertab);
                bundle.putBoolean("key4",musicon);
                i.putExtras(bundle);
                startActivity(i);
            }
        });
        mViewPager2.setAdapter(viewPager2Adapter);

        // đồng bộ tablayout và viewpager
        new TabLayoutMediator(mTablayout, mViewPager2,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        if (position == 0){
                            tab.setText(R.string.tab_0);
                        } else if (position ==1) {
                            tab.setText(R.string.tab_1);
                        } else if (position ==2) {
                            tab.setText(R.string.tab_2);
                        }
                    }
                }).attach();
        mViewPager2.setCurrentItem(order_tab);

        // onclick switch
        switchCompat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (switchCompat1.isChecked())
                {
                    LocaleListCompat appLocales = LocaleListCompat.forLanguageTags("en");
                    AppCompatDelegate.setApplicationLocales(appLocales);
                } else {
                    LocaleListCompat appLocales = LocaleListCompat.forLanguageTags("vi");
                    AppCompatDelegate.setApplicationLocales(appLocales);
                }
                saveFile();
            }
        });


    }
    private void getData()
    {
        //lay du lieu tu activity 2
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle == null){
            order_tab =0;
            musicon = false;
        } else {
        order_tab = bundle.getInt("key3");
        musicon = bundle.getBoolean("key5");
        }

    }
    private void setData()
    {
        dataRecyclerList= new ArrayList<>();
        dataRecyclerList2= new ArrayList<>();
        dataRecyclerList3  = new ArrayList<>();
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


        dataRecyclerList2.add(new DataRecycler(R.drawable.img11_cnpnt,38,getString(R.string.t11),1));
        dataRecyclerList2.add(new DataRecycler(R.drawable.img12_cn,39,getString(R.string.t12),1));
        dataRecyclerList2.add(new DataRecycler(R.drawable.img13_ct,40,getString(R.string.t13),1));
        dataRecyclerList2.add(new DataRecycler(R.drawable.img14_cbmc,41,getString(R.string.t14),1));
        dataRecyclerList2.add(new DataRecycler(R.drawable.img15_cctmmgcbk,42,getString(R.string.t15),1));
        dataRecyclerList2.add(new DataRecycler(R.drawable.img16_ccdovcn,43,getString(R.string.t16),1));
        dataRecyclerList2.add(new DataRecycler(R.drawable.img22_tm,44,getString(R.string.t22),1));
        dataRecyclerList2.add(new DataRecycler(R.drawable.img23_xdlmk,45,getString(R.string.t23),1));
        dataRecyclerList2.add(new DataRecycler(R.drawable.img24_hhtm,46,getString(R.string.t24),1));
        dataRecyclerList2.add(new DataRecycler(R.drawable.img25_nmax,47,getString(R.string.t25),1));
        dataRecyclerList2.add(new DataRecycler(R.drawable.img34_bdcb,48,getString(R.string.t34),1));
        dataRecyclerList2.add(new DataRecycler(R.drawable.img44_dbtm,49,getString(R.string.t44),1));
        dataRecyclerList2.add(new DataRecycler(R.drawable.img49_pqtn,50,getString(R.string.t49),1));

        dataRecyclerList2.add(new DataRecycler(R.drawable.img65_btdtnvs,51,getString(R.string.t65),1));
        dataRecyclerList2.add(new DataRecycler(R.drawable.img66_ct,52,getString(R.string.t66),1));
        dataRecyclerList2.add(new DataRecycler(R.drawable.img67_lnvhd,53,getString(R.string.t67),1));
        dataRecyclerList2.add(new DataRecycler(R.drawable.img68_mlbc,54,getString(R.string.t68),1));
        dataRecyclerList2.add(new DataRecycler(R.drawable.img69_ktdtrnc,55,getString(R.string.t69),1));
        dataRecyclerList2.add(new DataRecycler(R.drawable.img70_bvct,56,getString(R.string.t70),1));
        dataRecyclerList2.add(new DataRecycler(R.drawable.img71_cba,57,getString(R.string.t71),1));
        dataRecyclerList2.add(new DataRecycler(R.drawable.img72_bt,58,getString(R.string.t72),1));
        dataRecyclerList2.add(new DataRecycler(R.drawable.img73_ndtv,59,getString(R.string.t73),1));
        dataRecyclerList2.add(new DataRecycler(R.drawable.img74_dctkn,60,getString(R.string.t74),1));
        dataRecyclerList2.add(new DataRecycler(R.drawable.img75_nllq,61,getString(R.string.t75),1));
        dataRecyclerList2.add(new DataRecycler(R.drawable.img76_ndkgtt,62,getString(R.string.t76),1));

        dataRecyclerList3.add(new DataRecycler(R.drawable.img17_bsal,63,getString(R.string.t17),2));
        dataRecyclerList3.add(new DataRecycler(R.drawable.img18_mvtdnncynl,64,getString(R.string.t18),2));
        dataRecyclerList3.add(new DataRecycler(R.drawable.img19_tb,65,getString(R.string.t19),2));
        dataRecyclerList3.add(new DataRecycler(R.drawable.img20_asctb,66,getString(R.string.t20),2));
        dataRecyclerList3.add(new DataRecycler(R.drawable.img21_nb,67,getString(R.string.t21),2));
        dataRecyclerList3.add(new DataRecycler(R.drawable.img26_ccn,68,getString(R.string.t26),2));
        dataRecyclerList3.add(new DataRecycler(R.drawable.img27_bcct,69,getString(R.string.t27),2));
        dataRecyclerList3.add(new DataRecycler(R.drawable.img28_mtgmt,70,getString(R.string.t28),2));
        dataRecyclerList3.add(new DataRecycler(R.drawable.img29_dt,71,getString(R.string.t29),2));
        dataRecyclerList3.add(new DataRecycler(R.drawable.img35_lnnmm,72,getString(R.string.t35),2));
        dataRecyclerList3.add(new DataRecycler(R.drawable.img36_yhky,73,getString(R.string.t36),2));
        dataRecyclerList3.add(new DataRecycler(R.drawable.img45_cbv,74,getString(R.string.t45),2));
        dataRecyclerList3.add(new DataRecycler(R.drawable.img46_tydk,75,getString(R.string.t46),2));

        dataRecyclerList3.add(new DataRecycler(R.drawable.img77_tydt,76,getString(R.string.t77),2));
        dataRecyclerList3.add(new DataRecycler(R.drawable.img78_ttmd,77,getString(R.string.t78),2));
        dataRecyclerList3.add(new DataRecycler(R.drawable.img79_ndv,78,getString(R.string.t79),2));
        dataRecyclerList3.add(new DataRecycler(R.drawable.img80_hpvb,79,getString(R.string.t80),2));
        dataRecyclerList3.add(new DataRecycler(R.drawable.img81_tthh,80,getString(R.string.t81),2));
        dataRecyclerList3.add(new DataRecycler(R.drawable.img82_tcpm,81,getString(R.string.t82),2));
        dataRecyclerList3.add(new DataRecycler(R.drawable.img83_nchg,82,getString(R.string.t83),2));
        dataRecyclerList3.add(new DataRecycler(R.drawable.img84_dctt,83,getString(R.string.t84),2));
        dataRecyclerList3.add(new DataRecycler(R.drawable.img85_ccmnbt,84,getString(R.string.t85),2));
        dataRecyclerList3.add(new DataRecycler(R.drawable.img86_mls,85,getString(R.string.t86),2));
        dataRecyclerList3.add(new DataRecycler(R.drawable.img87_mpltbn,86,getString(R.string.t87),2));
        dataRecyclerList3.add(new DataRecycler(R.drawable.img88_mxl,87,getString(R.string.t88),2));
        dataRecyclerList3.add(new DataRecycler(R.drawable.img89_ccthhd,88,getString(R.string.t89),2));
        dataRecyclerList3.add(new DataRecycler(R.drawable.img90_actoemlk,89,getString(R.string.t90),2));
        dataRecyclerList3.add(new DataRecycler(R.drawable.img91_dvyntn,90,getString(R.string.t91),2));
        dataRecyclerList3.add(new DataRecycler(R.drawable.img92_nltkdtl,91,getString(R.string.t92),2));
        dataRecyclerList3.add(new DataRecycler(R.drawable.img93_tb,92,getString(R.string.t93),2));
        dataRecyclerList3.add(new DataRecycler(R.drawable.img94_ttmm,93,getString(R.string.t94),2));
        dataRecyclerList3.add(new DataRecycler(R.drawable.img95_smcnc,94,getString(R.string.t95),2));
        dataRecyclerList3.add(new DataRecycler(R.drawable.img96_cccntvek,95,getString(R.string.t96),2));
        dataRecyclerList3.add(new DataRecycler(R.drawable.img97_hctbnbyla,96,getString(R.string.t97),2));
        dataRecyclerList3.add(new DataRecycler(R.drawable.img98_hmgyt,97,getString(R.string.t98),2));
        dataRecyclerList3.add(new DataRecycler(R.drawable.img99_acctb,98,getString(R.string.t99),2));
        dataRecyclerList3.add(new DataRecycler(R.drawable.img100_btkng,99,getString(R.string.t100),2));

        dataPagerList = new ArrayList<>();
        dataPagerList.add(new DataPager(dataRecyclerList,0));
        dataPagerList.add(new DataPager(dataRecyclerList2,1));
        dataPagerList.add(new DataPager(dataRecyclerList3,2));
    }
    private void anhXa()
    {
        mDrawerLayout=findViewById(R.id.drawerLayout);
        appBarLayout=findViewById(R.id.appBarLayout);
        toolbar=findViewById(R.id.toolbar);
        navigationView=findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);
        mTablayout=findViewById(R.id.tabLayout);
        mViewPager2=findViewById(R.id.viewPager2);
        fab = findViewById(R.id.fab);

        imgHeader = findViewById(R.id.img_toolbar);
        switchCompat1 = findViewById(R.id.switch_nav);
        switchCompat1.setThumbTintList(ColorStateList
                .valueOf(ContextCompat.getColor(getApplicationContext(), R.color.my_dark_primary)));
        switchCompat1.setTrackTintList(ColorStateList
                .valueOf(ContextCompat.getColor(getApplicationContext(), R.color.my_light_primary)));

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        int id  = item.getItemId();

        if (id == R.id.rateUs) {
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
            } catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
            }
        }
        else if (id ==R.id.donate)
        {
            Intent idonate = new Intent(MainActivity.this,DonateActivity.class);
            startActivity(idonate);
        }
        else if (id ==R.id.moreApp)
        {
            Toast.makeText(this, "more app", Toast.LENGTH_SHORT).show();
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=pub:NguyenXuanTruong91")));
            } catch (android.content.ActivityNotFoundException anfe) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/developer?id=NguyenXuanTruong91")));
            }
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    private void readFile()
    {
        sharedPref = getSharedPreferences("myfile",MODE_PRIVATE);
        isEnglish = sharedPref.getBoolean("keylanguage", false);
        switchCompat1.setChecked(isEnglish);
    }
    private void saveFile()
    {
        isEnglish = switchCompat1.isChecked();
        sharedPref = getSharedPreferences("myfile",MODE_PRIVATE);
        editor = sharedPref.edit();
        editor.putBoolean("keylanguage",isEnglish);
        editor.apply();
    }



}