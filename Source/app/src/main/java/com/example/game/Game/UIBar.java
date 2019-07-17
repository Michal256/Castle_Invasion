package com.example.game.Game;


public class UIBar extends GameObject implements TouchAvailability {
    public UIBar(int objectId) {
        super(objectId);
    }

    @Override
    public void onTouchAction() {
        System.out.println("Hello");
    }

}



