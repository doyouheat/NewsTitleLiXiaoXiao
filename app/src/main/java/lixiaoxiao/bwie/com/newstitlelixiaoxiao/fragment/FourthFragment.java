package lixiaoxiao.bwie.com.newstitlelixiaoxiao.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.shareboard.SnsPlatform;

import java.util.ArrayList;
import java.util.Map;

import lixiaoxiao.bwie.com.newstitlelixiaoxiao.R;
import lixiaoxiao.bwie.com.newstitlelixiaoxiao.activity.QQLoginActivity;
public class FourthFragment extends Fragment implements View.OnClickListener{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public ArrayList<SnsPlatform> platforms = new ArrayList<SnsPlatform>();
    private SHARE_MEDIA[] list = {SHARE_MEDIA.QQ};
    private String mParam1;
    private String mParam2;
    private View view;
    private RelativeLayout phone_login;
    private RelativeLayout weixin_login;
    private RelativeLayout qq_login;
    private ImageView more_login;
    private RelativeLayout collect;
    private UMAuthListener authListener;


    public FourthFragment() {
        // Required empty public constructor
    }

    public static FourthFragment newInstance(String param1, String param2) {
        FourthFragment fragment = new FourthFragment();
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
        view = inflater.inflate(R.layout.fragment_fourth, container, false);
        getViews();
        return  view;
    }
    private void getViews() {
        phone_login = (RelativeLayout) view.findViewById(R.id.phone_login);
        weixin_login = (RelativeLayout) view.findViewById(R.id.weixin_login);
        qq_login = (RelativeLayout) view.findViewById(R.id.QQ_login);
        more_login = (ImageView) view.findViewById(R.id.more_login);
        collect = (RelativeLayout) view.findViewById(R.id.collect);
        RelativeLayout    history= (RelativeLayout) view.findViewById(R.id.history);
        RelativeLayout   night= (RelativeLayout) view.findViewById(R.id.night);
        RelativeLayout    message= (RelativeLayout) view.findViewById(R.id.message);
        RelativeLayout   top_mall= (RelativeLayout) view.findViewById(R.id.top_mall);
        RelativeLayout   JD_supply= (RelativeLayout) view.findViewById(R.id.JD_supply);
        RelativeLayout   tip_off= (RelativeLayout) view.findViewById(R.id.tip_off);
        RelativeLayout   couple_back= (RelativeLayout) view.findViewById(R.id.couple_back);
        RelativeLayout   preferences= (RelativeLayout) view.findViewById(R.id.preferences);
        phone_login.setOnClickListener(this);
        weixin_login.setOnClickListener(this);
        qq_login.setOnClickListener(this);
        more_login.setOnClickListener(this);
        collect.setOnClickListener(this);history.setOnClickListener(this);night.setOnClickListener(this);
        message.setOnClickListener(this);top_mall.setOnClickListener(this);JD_supply.setOnClickListener(this);tip_off.setOnClickListener(this);couple_back.setOnClickListener(this);
        preferences.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.phone_login:
                Toast.makeText(getActivity(), "暂为实现", Toast.LENGTH_SHORT).show();
                break;
            case R.id.weixin_login:
                break;
            case R.id.QQ_login:
                Toast.makeText(getActivity(), ""+"QQ登录", Toast.LENGTH_SHORT).show();
                initPlatforms();
                loginM();
                break;
            case R.id.more_login:
                break;
            case R.id.collect:
                break;
            case R.id.history:
                break;
            case R.id.night:
                break;
            case R.id.message:
                Toast.makeText(getActivity(), "暂为实现", Toast.LENGTH_SHORT).show();
                break;
            case R.id.top_mall:
                break;
            case R.id.JD_supply:
                break;
            case R.id.tip_off:
                break;
            case R.id.couple_back:
                break;
            case R.id.preferences:
                break;
        }
    }
    private void loginM() {
        //登录
        final boolean isauth = UMShareAPI.get(getActivity()).isAuthorize(getActivity(), platforms.get(0).mPlatform);
        if (isauth) {
            Toast.makeText(getActivity(), "授权成功", Toast.LENGTH_SHORT).show();
            UMShareAPI.get(getActivity()).deleteOauth(getActivity(), platforms.get(0).mPlatform, authListener);
        } else {
            Toast.makeText(getActivity(), "登录成功", Toast.LENGTH_SHORT).show();
            UMShareAPI.get(getActivity()).doOauthVerify(getActivity(), platforms.get(0).mPlatform, authListener);
        }
        UMShareAPI.get(getActivity()).getPlatformInfo(getActivity(), platforms.get(0).mPlatform, authListener);
    }
    private void initPlatforms() {
        for (SHARE_MEDIA e : list) {
            if (!e.toString().equals(SHARE_MEDIA.GENERIC.toString())) {
                platforms.add(e.toSnsPlatform());
            }


            //授权监听
            // SocializeUtils.safeShowDialog(dialog);
//  SocializeUtils.safeCloseDialog(dialog);
//Log.i("xxx", data.toString());
//SocializeUtils.safeCloseDialog(dialog);
//  SocializeUtils.safeCloseDialog(dialog);
                 authListener = new UMAuthListener() {
                @Override
                public void onStart(SHARE_MEDIA platform) {
                    // SocializeUtils.safeShowDialog(dialog);
                }

                @Override
                public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
                    //  SocializeUtils.safeCloseDialog(dialog);
                    Toast.makeText(getActivity(), "成功了", Toast.LENGTH_LONG).show();
                    //Log.i("xxx", data.toString());
                    for (String key : data.keySet()) {


                    }
                    String profile_image_url = data.get("profile_image_url");
                    String name = data.get("name");
                    Log.i("xxx", "profile_image_url:" + profile_image_url.toString());
                    Log.i("xxx", "name:" + name.toString());

                }

                @Override
                public void onError(SHARE_MEDIA platform, int action, Throwable t) {
                    //SocializeUtils.safeCloseDialog(dialog);
                    Toast.makeText(getActivity(), "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();

                }

                @Override
                public void onCancel(SHARE_MEDIA platform, int action) {
                    //  SocializeUtils.safeCloseDialog(dialog);
                    Toast.makeText(getActivity(), "取消了", Toast.LENGTH_LONG).show();

                }
            };
        }
    }
}
