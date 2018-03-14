package com.wblei.objectconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.wblei.converter_annotation.BindView;

public class MainActivity extends AppCompatActivity {
  @BindView(R.id.content) TextView textView;
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }
}
