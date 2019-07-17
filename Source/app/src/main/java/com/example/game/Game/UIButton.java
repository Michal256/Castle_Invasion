package com.example.game.Game;

public class UIButton extends GameObject implements TouchAvailability {

    public UIButton(int objectId){
        super(objectId);
    }


    @Override
    public void onTouchAction() {
        System.out.println("Hello");
    }

}
