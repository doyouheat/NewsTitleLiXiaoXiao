package lixiaoxiao.bwie.com.newstitlelixiaoxiao.fragment;

import android.net.Uri;
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

public class SecondFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    private View view;
    String[]   video=new  String[]{"热点视频","娱乐视频","搞笑视频","精品视频"};

    private ArrayList<Fragment> fmlist=new ArrayList<>();
    private TabLayout tab;
    private ViewPager vp;

    public SecondFragment() {
        // Required empty public constructor
    }

    public static SecondFragment newInstance(String param1, String param2) {
        SecondFragment fragment = new SecondFragment();
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
        view = inflater.inflate(R.layout.fragment_second, container, false);
        getViews();
        createFragment();
        setAdapter();
        tab.setupWithViewPager(vp);
        return view;
    }

    private void createFragment() {
        for (int i = 0; i <video.length; i++) {
            fmlist.add(new VideoFragment());
        }
    }
    private void getViews() {
        tab = (TabLayout) view.findViewById(R.id.tablayout);
        tab.setTabMode(TabLayout.MODE_SCROLLABLE);
        vp = (ViewPager) view.findViewById(R.id.vp);

    }
    private void setAdapter() {
        vp.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
               VideoFragment   videoFragment = VideoFragment .newInstance(position+"", null);
                return   videoFragment;
            }
            @Override
            public int getCount() {
                return  fmlist.size();
            }
            @Override
            public CharSequence getPageTitle(int position) {
                return  video[position];
            }
        });
    }

}
