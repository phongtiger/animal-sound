package com.example.animalsound.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.animalsound.App;
import com.example.animalsound.R;
import com.example.animalsound.databinding.FragmentM002DetailBinding;
import com.example.animalsound.model.Animal;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public class M002DetailFrg extends BaseFragment<FragmentM002DetailBinding> {
    public static final String TAG = M002DetailFrg.class.getName();
    private int index = 0;

    @Override
    protected FragmentM002DetailBinding initViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentM002DetailBinding.inflate(inflater, container, false);
    }

    @Override
    protected void initView() {
        Animal animal = (Animal) data;
        index = getListAnimal().indexOf(animal);

        updateUI(animal);

        binding.ivNext.setOnClickListener(this);
        binding.ivPrevious.setOnClickListener(this);
        binding.ivPlay.setOnClickListener(this);
        binding.ivSearch.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void clickView(View view) {
        switch (view.getId()) {
            case R.id.iv_previous:
                index--;
                if (index < 0) index = getListAnimal().size() - 1;
                break;
            case R.id.iv_next:
                index++;
                if (index > getListAnimal().size() - 1) index = 0;
                break;
            case R.id.iv_play:
                Animal animal = getListAnimal().get(index);
                MediaPlayer.create(context, animal.getIdSound()).start();
                break;
            case R.id.iv_search:
                searchAnimal(getListAnimal().get(index).getName());
        }
        updateUI(getListAnimal().get(index));
    }

    private void searchAnimal(String name) {
        try {
            String word = URLEncoder.encode(name, "UTF-8");
            Uri uri = Uri.parse("https://www.google.com/search?hl=en&q="+ word);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    private void updateUI(Animal animal) {
        binding.tvTitle.setText(animal.getName());
        binding.ivAnimal.setImageResource(animal.getIdPhoto());
    }

    private List<Animal> getListAnimal() {
        return App.getInstance().getStorage().lstAnimal;
    }
}
