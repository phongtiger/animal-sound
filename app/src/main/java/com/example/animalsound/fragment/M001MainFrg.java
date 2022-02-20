package com.example.animalsound.fragment;

import android.speech.tts.TextToSpeech;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;

import com.example.animalsound.App;
import com.example.animalsound.R;
import com.example.animalsound.databinding.FragmentM001MainBinding;
import com.example.animalsound.databinding.FragmentM002DetailBinding;
import com.example.animalsound.model.Animal;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class M001MainFrg extends BaseFragment<FragmentM001MainBinding> {
    public static final String TAG = M001MainFrg.class.getName();
    private TextToSpeech tts;

    @Override
    protected FragmentM001MainBinding initViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentM001MainBinding.inflate(inflater, container, false);
    }

    @Override
    protected void initView() {
        initData();
        initAnimalView();
        tts = new TextToSpeech(context, status -> {
            if (status != TextToSpeech.ERROR) {
                tts.setLanguage(Locale.US);
            }
        });

    }

    private void initAnimalView() {
        ;
        for (int i = 0; i <= App.getInstance().getStorage().lstAnimal.size() - 3; i += 3) {
            Animal animal1 = App.getInstance().getStorage().lstAnimal.get(i);
            Animal animal2 = App.getInstance().getStorage().lstAnimal.get(i + 1);
            Animal animal3 = App.getInstance().getStorage().lstAnimal.get(i + 2);
            View v1 = LayoutInflater.from(context).inflate(R.layout.item_animal, null);
            View v2 = LayoutInflater.from(context).inflate(R.layout.item_animal, null);
            View v3 = LayoutInflater.from(context).inflate(R.layout.item_animal, null);
            ImageView ivAnimal1 = v1.findViewById(R.id.iv_animal);
            ImageView ivAnimal2 = v2.findViewById(R.id.iv_animal);
            ImageView ivAnimal3 = v3.findViewById(R.id.iv_animal);
            ivAnimal1.setImageResource(animal1.getIdPhoto());
            ivAnimal2.setImageResource(animal2.getIdPhoto());
            ivAnimal3.setImageResource(animal3.getIdPhoto());

            ivAnimal1.setTag(animal1);
            ivAnimal2.setTag(animal2);
            ivAnimal3.setTag(animal3);
            ivAnimal1.setOnClickListener(this);
            ivAnimal2.setOnClickListener(this);
            ivAnimal3.setOnClickListener(this);

            TableRow tr = new TableRow(context);
            tr.setGravity(Gravity.CENTER);
            tr.addView(v1, new TableRow.LayoutParams(380, 540));
            tr.addView(v2, new TableRow.LayoutParams(380, 540));
            tr.addView(v3, new TableRow.LayoutParams(380, 540));
            binding.lnAnimalList.addView(tr, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
        }
    }

    private void initData() {
        App.getInstance().getStorage().lstAnimal = new ArrayList<>();
        App.getInstance().getStorage().lstAnimal.add(new Animal(R.drawable.ic_bear, "Bear", R.raw.bear));
        App.getInstance().getStorage().lstAnimal.add(new Animal(R.drawable.ic_bee, "Bee", R.raw.bee));
        App.getInstance().getStorage().lstAnimal.add(new Animal(R.drawable.ic_bird, "Bird", R.raw.bird));
        App.getInstance().getStorage().lstAnimal.add(new Animal(R.drawable.ic_camel, "Camel", R.raw.camel));
        App.getInstance().getStorage().lstAnimal.add(new Animal(R.drawable.ic_cat, "Cat", R.raw.cat));
        App.getInstance().getStorage().lstAnimal.add(new Animal(R.drawable.ic_chick, "Chick", R.raw.chick));
        App.getInstance().getStorage().lstAnimal.add(new Animal(R.drawable.ic_cow, "Cow", R.raw.cow));
        App.getInstance().getStorage().lstAnimal.add(new Animal(R.drawable.ic_crocodile, "Crocodile", R.raw.crocodile));
        App.getInstance().getStorage().lstAnimal.add(new Animal(R.drawable.ic_dog, "Dog", R.raw.dog));
    }

    @Override
    protected void clickView(View view) {
        Animal animal = (Animal) view.getTag();
        goToDetailScreen(animal);
    }

    private void goToDetailScreen(Animal animal) {
        tts.speak(animal.getName(), TextToSpeech.QUEUE_FLUSH, null);
        callBack.showFragment(M002DetailFrg.TAG, animal, true);
    }

    @Override
    public void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}
