package com.example.animalsound.act;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewbinding.ViewBinding;

import com.example.animalsound.OnActionCallBack;
import com.example.animalsound.R;
import com.example.animalsound.fragment.BaseFragment;

import java.lang.reflect.Constructor;

public abstract class BaseAct<T extends ViewBinding>
        extends AppCompatActivity implements View.OnClickListener, OnActionCallBack {
    protected T binding;

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = initViewBinding();
        setContentView(binding.getRoot());
        initViews();
    }

    protected abstract void initViews();

    protected abstract T initViewBinding();

    @Override
    public void onClick(View v) {
        //do nothing
    }

    protected final void notify(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    protected final void notify(int msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFragment(String tag, Object data, boolean isBacked) {
        // Tag tao ra baseFragment;
        try {
            Class<?> clazz = Class.forName(tag);
            Constructor<?> constructor = clazz.getConstructor();
            BaseFragment<?> baseFragment = (BaseFragment<?>) constructor.newInstance();
            baseFragment.setCallBack(this);
            baseFragment.setData(data);
            FragmentTransaction trans = getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.ln_main, baseFragment, tag);
            if (isBacked) {
                trans.addToBackStack(null);
            }
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
