package com.example.game.Game;

public abstract class GameObject {
    private int objectId;

    public GameObject(int objectId)
    {
        this.objectId=objectId;
    }


    public int getObjectId() {
        return objectId;
    }

}
