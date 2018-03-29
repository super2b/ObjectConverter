package com.wblei.objectconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.wblei.converter.ObjectConvert2;
import com.wblei.vo.User;
import com.wblei.vo.UserWrapper;

public class MainActivity extends AppCompatActivity {
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        User user = new User("super2b", "super2b", 1);

        Object obj = ObjectConvert2.tryToConvert(UserWrapper.class.getName(), user);
        if (obj instanceof UserWrapper) {
          ((TextView)findViewById(R.id.content)).setText(obj.toString());
        }
      }
    });
  }
}
