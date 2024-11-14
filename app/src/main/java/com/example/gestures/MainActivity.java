package com.example.gestures;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private GestureDetector gestureDetector;
    private TextView gestureText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gestureText = findViewById(R.id.gestureText);

        gestureDetector = new GestureDetector(this, new GestureListener());

        Button authorBtn = findViewById(R.id.author);
        authorBtn.setOnClickListener(v -> showToast("Разработала Смердина Анастасия"));

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event) || super.onTouchEvent(event);
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            gestureText.setText("Одинарное нажатие");
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            gestureText.setText("Двойное нажатие");
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (e1.getY() < e2.getY()) {
                gestureText.setText("Смахивание вниз");
            } else if (e1.getY() > e2.getY()) {
                gestureText.setText("Смахивание вверх");
            }
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            gestureText.setText("Долгое нажатие");
        }
    }

    private void showToast(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
