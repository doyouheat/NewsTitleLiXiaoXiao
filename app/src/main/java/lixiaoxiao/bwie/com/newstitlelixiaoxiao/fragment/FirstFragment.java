package lixiaoxiao.bwie.com.newstitlelixiaoxiao.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import lixiaoxiao.bwie.com.newstitlelixiaoxiao.R;

public class FirstFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
   String[]  titname=new String[]{"社会","娱乐","科技","财经","电影","笑话","时尚","情感","旅游","军事","亲子","游戏"};
    private ArrayList<Fragment>  fmlist=new ArrayList<>();
    private String mParam1;
    private String mParam2;
    private View view;
    private TabLayout tab;
    private ViewPager vp;
    public FirstFragment() {
        // Required empty public constructor
    }


    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_first, container, false);
        getViews();
        createFragment();
        setAdapter();
        tab.setupWithViewPager(vp);
        return view;
    }

    private void setAdapter() {
        vp.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                    HomeFragment fragmentNews = HomeFragment.newInstance(position+"", null);
                    return   fragmentNews;
            }
            @Override
            public int getCount() {
                return  fmlist.size();
            }
            @Override
            public CharSequence getPageTitle(int position) {
                return titname[position];
            }
        });
    }

    private void createFragment() {
        for (int i = 0; i < titname.length; i++) {
            fmlist.add(new HomeFragment());
        }
    }


    private void getViews() {
        tab = (TabLayout) view.findViewById(R.id.tablayout);
        tab.setTabMode(TabLayout.MODE_SCROLLABLE);
        vp = (ViewPager) view.findViewById(R.id.vp);

    }

}
