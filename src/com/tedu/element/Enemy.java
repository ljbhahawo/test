package com.tedu.element;

import com.tedu.manager.GameLoad;

import java.awt.Graphics;
import java.util.Random;

import javax.swing.ImageIcon;

public class Enemy extends ElementObj{
	private int range=100;	// 移动范围
	private boolean right=false;	// 向右移动
	private boolean left=false;		//向左移动
	private boolean up=false;		//向上移动
	private boolean down=false;		//向下移动
	private int type;	//敌人类型 1为左右移动 2为上下移动


	public Enemy(){}

	public void setType(int type){
		this.type=type;
	}

	@Override
	public void showElement(Graphics g) {
		g.drawImage(this.getIcon().getImage(), 
				this.getX(), this.getY(), 
				this.getW(), this.getH(), null);
	}
	@Override
	public ElementObj createElement(String str) {
		String[] split = str.split(",");
		ImageIcon icon2 = GameLoad.imgMap.get(split[2]);
		String enemyT=split[3];
		System.out.println(split[3]);
		if (enemyT.equals("1")){
			this.type=1;
			this.right=true;
			this.setIcon(new ImageIcon("image/tank/bot/bot_right.png"));
		}else if(enemyT.equals("2")){
			this.type=2;
			this.up=true;
			this.setIcon(new ImageIcon("image/tank/play1/player1_up.png"));
		}

		Random ran=new Random();
		int x=ran.nextInt(700);
		int y=ran.nextInt(500);
		this.setX(x);
		this.setY(y);
		this.setW(icon2.getIconWidth());
		this.setH(icon2.getIconHeight());

		return this;
	}


	@Override
	protected void move() {
		if (this.type==1){
			if (this.left && this.getX()>20) {
				this.setX(this.getX() - 1);
				if (this.getX() == 20){
					this.left = false;
					this.right = true;
					this.setIcon(new ImageIcon("image/tank/bot/bot_right.png"));
				}
			}
			if (this.right && this.getX()<800-this.getW()-20) {  //坐标的跳转由大家来完成
				this.setX(this.getX() + 1);
				if (this.getX() == 800-this.getW()-20){
					this.left = true;
					this.right = false;
					this.setIcon(new ImageIcon("image/tank/bot/bot_left.png"));
				}
			}
		}
		else if(type==2){
			if (this.up  && this.getY()>20) {
				this.setY(this.getY() - 1);
				if (this.getY() == 20){
					this.up = false;
					this.down = true;
					this.setIcon(new ImageIcon("image/tank/play1/player1_down.png"));
				}
			}

			if (this.down && this.getY()<600-this.getH()-20) {
				this.setY(this.getY() + 1);
				if (this.getY() == 550-this.getH()-20){
					this.up = true;
					this.down = false;
					this.setIcon(new ImageIcon("image/tank/play1/player1_up.png"));
				}
			}
		}
	}
}
