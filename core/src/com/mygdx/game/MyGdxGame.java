package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture fondo;
	Texture box;
	Texture player;
	Texture coin;
	Texture enemy;
	Texture bee[];
	int player_x;
	int player_y;
	int coin_x;
	int coin_y;
	boolean coin_visible;
	int current_bee;
	int frame;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		fondo = new Texture("fondo.png");
		player = new Texture("player.png");
		box = new Texture("box.png");
		coin = new Texture("coin.png");
		enemy = new Texture("enemy.png");
		bee = new Texture[2];
		bee[0] = new Texture("bee0.png");
		bee[1] = new Texture("bee1.png");
		player_x=0;
		player_y=0;
		coin_x=200;
		coin_y=0;
		coin_visible=true;
		current_bee=0;
		frame=0;
		///Gdx.graphics.setDisplayMode(100,200,false);
		System.out.println("Funcion create");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(.1f, .7f, 0.5f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(fondo, 0, 0);
		batch.draw(player, player_x, player_y);
		batch.draw(box, 200, 250);
		if(coin_visible)
			batch.draw(coin, coin_x, coin_y);
		batch.draw(enemy, 400, 0);
		batch.draw(bee[current_bee], 300, 120);
		batch.end();

		if(frame%10==0)
		{
			current_bee++;
			if (current_bee >= bee.length)
				current_bee = 0;
		}

		if(Gdx.input.isKeyPressed(Input.Keys.A))
			player_x--;
		if(Gdx.input.isKeyPressed(Input.Keys.D))
			player_x++;

		if(Gdx.input.isTouched())
		{
			player_x = Gdx.input.getX() - player.getWidth()/2;
			player_y = Gdx.graphics.getHeight() - Gdx.input.getY() - player.getHeight()/2;
		}

		Rectangle rect_coin = new Rectangle(coin_x,coin_y,coin.getWidth(),coin.getHeight());
		Rectangle rect_player = new Rectangle(player_x,player_y,player.getWidth(),player.getHeight());

		if(rect_coin.overlaps(rect_player))
		{
			coin_visible=false;
		}

		frame++;
	}
}
