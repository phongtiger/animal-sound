package com.example.animalsound.fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import com.example.animalsound.R;
import com.example.animalsound.databinding.FragmentM000SplashBinding;


public class M000SplashFrg extends BaseFragment<FragmentM000SplashBinding> {
    public static final String TAG = M000SplashFrg.class.getName();

    @Override
    protected FragmentM000SplashBinding initViewBinding(LayoutInflater inflater, ViewGroup viewGroup) {
        return FragmentM000SplashBinding.inflate(inflater, viewGroup, false);
    }

    @Override
    protected void initView() {
        binding.frIcon.startAnimation(AnimationUtils.loadAnimation(context, R.anim.abc_slide_in_left));
        binding.tvTitle.startAnimation(AnimationUtils.loadAnimation(context,R.anim.abc_slide_in_right));
        new Handler().postDelayed(this::goToMainScreen, 2500);
    }

    private void goToMainScreen() {
        callBack.showFragment(M001MainFrg.TAG, null, false);
    }
}
