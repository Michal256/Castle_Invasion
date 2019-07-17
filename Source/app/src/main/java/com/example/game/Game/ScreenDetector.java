package com.example.game.Game;

import static com.example.game.Game.UserSettingsBinder.screenHeight;

import static com.example.game.Game.UserSettingsBinder.screenWidth;

public class ScreenDetector
{
    /**
     * Method returns points for zTranslate=-1!
     * @param xInPixels
     * @return points x axis
     */
    public static float xInPoints(float xInPixels)
    {
        if(screenWidth!=0) {
            float tempRatio = (float) xInPixels / screenWidth;
            return ((tempRatio * 2.0f)-1.0f);
        }
        else
        {
            return 0;
        }
    }

    /**
     * Method returns points for zTranslate=-1!
     * @param yInPixels
     * @return points y axis
     */
    public static float yInPoints(float yInPixels)
    {
        if(screenHeight!=0) {
            float tempRatio = (float) yInPixels / screenHeight;
            return ((-1)*((tempRatio * 1.0f)-0.5f));
        }
        else
        {
            return 0;
        }
    }


}
