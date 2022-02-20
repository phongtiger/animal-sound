package com.example.animalsound.act;

import com.example.animalsound.databinding.ActivityMainBinding;
import com.example.animalsound.fragment.M000SplashFrg;

public class MainActivity extends BaseAct<ActivityMainBinding> {


    @Override
    public void callBack(String key, Object data) {
        
    }

    @Override
    protected void initViews() {
        showFragment(M000SplashFrg.TAG, null, false);
    }

    @Override
    protected ActivityMainBinding initViewBinding() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }
}