package com.timetelling.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.timetelling.game.TimeTellingGame;
import com.timetelling.gameobjects.Clock;
import com.timetelling.gameobjects.ProgressBar;
import com.timetelling.gameobjects.SimpleButton;
import com.timetelling.gameobjects.Time;
import com.timetelling.helper.AssetLoader;

public class TurtleJumpRenderer extends GameRenderer {

    private ProgressBar bar;
    private Time[] choices;
    private SimpleButton[] buttons;
    private TurtleJumpWorld world;
    private Skin skin;
    private Sprite clock;

    public TurtleJumpRenderer(GameWorld world, TimeTellingGame game) {
        super(world, game);
        this.world = (TurtleJumpWorld)world;
        clock = AssetLoader.clockSprite;
        bar = this.world.getBar();
        choices = this.world.getChoices();
        buttons = new SimpleButton[choices.length];

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new SimpleButton((i+1)*width/(buttons.length+2), width/3, width/(buttons.length+2), width/6);
        }
    }

    @Override
    public void render() {
        super.render();
        batcher.begin();
        drawButtons();
        batcher.draw(clock,0,0);
        batcher.end();
    }


    public void drawButtons() {
        for (int i = 0; i < buttons.length; i++) {
           buttons[i].setWord(choices[i].toString());
           buttons[i].draw(batcher);
        }
    }

    @Override
    public boolean touchDown(int screenX, int screenY) {
        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i].isClicked(screenX, screenY)) world.guess(choices[i]);
        }
        return super.touchDown(screenX, screenY);
    }
}
