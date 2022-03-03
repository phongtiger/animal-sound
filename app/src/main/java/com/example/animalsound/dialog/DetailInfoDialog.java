package com.example.animalsound.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;

import com.example.animalsound.R;
import com.example.animalsound.databinding.ViewDetailInfoBinding;
import com.example.animalsound.model.Animal;

public class DetailInfoDialog extends Dialog implements View.OnClickListener {
    private final ViewDetailInfoBinding binding;
    private final Animal animal;
    private Context context;

    public DetailInfoDialog(@NonNull Context context, Animal animal) {
        super(context, R.style.Theme_Dialog);
        this.animal = animal;
        this.context = context;
        binding = ViewDetailInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initView() {
        binding.ivBack.setOnClickListener(this);
        binding.ivTitle.setText(animal.getName());

        binding.webView.getSettings().setAllowContentAccess(true);
        binding.webView.getSettings().setJavaScriptEnabled(true);
        binding.webView.getSettings().setBuiltInZoomControls(true);
        binding.webView.getSettings().setAllowFileAccess(true);
        binding.webView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        binding.webView.getSettings().setDomStorageEnabled(true);
        binding.webView.getSettings().setSupportZoom(true);
        binding.webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });
        binding.webView.setWebChromeClient(new WebChromeClient());
        binding.webView.loadUrl("https://en.wikipedia.org/wiki/" + animal.getName());
    }

    @Override
    public void onClick(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
        if (view.getId() == R.id.iv_back) {
            dismiss();
        }

    }

    @Override
    public void dismiss() {
        if (!binding.webView.canGoBack()) {
            super.dismiss();
            return;
        }
        binding.webView.goBack();
    }
}
