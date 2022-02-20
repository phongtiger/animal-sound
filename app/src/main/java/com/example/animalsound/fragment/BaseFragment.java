package com.example.animalsound.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import com.example.animalsound.OnActionCallBack;


public abstract class BaseFragment<V extends ViewBinding> extends Fragment implements View.OnClickListener {
    protected Object data;
    protected Context context;
    protected V binding;
    protected OnActionCallBack callBack;

    public void setData(Object data) {
        this.data = data;
    }

    public void setCallBack(OnActionCallBack callBack) {
        this.callBack = callBack;
    }


    @Override
    public final void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public final View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = initViewBinding(inflater, container);
        return binding.getRoot();
    }

    protected abstract V initViewBinding(LayoutInflater inflater, ViewGroup container);

    @Override
    public final void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    protected abstract void initView();

    @Override
    public final void onClick(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
        clickView(view);
    }

    protected void clickView(View view) {

    }

}
