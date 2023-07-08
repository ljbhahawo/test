package com.tedu.element;

import com.tedu.manager.GameLoad;

import java.awt.Graphics;
import java.util.Random;

import javax.swing.ImageIcon;

public class Enemy extends ElementObj{
	private int range=100;	// 移动范围
	private boolean right=false;	// 向右移动
	private boolean left=false;		//向左移动

	public Enemy(){
		this.right=true;	// 默认向右移动
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


		Random ran=new Random();
		int x=ran.nextInt(700);
		int y=ran.nextInt(500);
		this.setX(x);
		this.setY(y);
		this.setW(icon2.getIconWidth());
		this.setH(icon2.getIconHeight());
		this.setIcon(new ImageIcon("image/tank/bot/bot_right.png"));
		return this;
	}


	@Override
	protected void move() {
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
}
