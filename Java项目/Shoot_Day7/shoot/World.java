package cn.tedu.shoot;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;
import java.util.Arrays;
/** 整个世界 */
public class World extends JPanel {
	public static final int WIDTH = 400;  //窗口的宽
	public static final int HEIGHT = 700; //窗口的高
	
	private Sky sky = new Sky(); //天空
	private Hero hero = new Hero(); //英雄机
	private FlyingObject[] enemies = {}; //敌人(小敌机、大敌机、小蜜蜂)数组
	private Bullet[] bullets = {}; //子弹数组
	
	/** 生成敌人(小敌机、大敌机、小蜜蜂)对象 */
	public FlyingObject nextOne(){
		Random rand = new Random();  //随机数对象
		int type = rand.nextInt(20); //0到19之间的随机数
		if(type<8){ //0到7时返回小敌机
			return new Airplane();
		}else if(type<14){ //8到13时返回大敌机
			return new BigAirplane();
		}else{ //14到19时返回小蜜蜂
			return new Bee();
		}
	}
	
	int enterIndex = 0; //敌人入场计数
	/** 敌人(小敌机、大敌机、小蜜蜂)入场 */
	public void enterAction(){ //每10毫秒走一次
		enterIndex++; //每10毫秒增1
		if(enterIndex%40==0){ //每400(10*40)毫秒走一次
			FlyingObject obj = nextOne(); //获取敌人对象
			enemies = Arrays.copyOf(enemies,enemies.length+1); //扩容
			enemies[enemies.length-1] = obj; //将敌人对象填充到enemies的最后一个元素上
		}
	}
	
	int shootIndex = 0; //子弹入场计数
	/** 子弹入场 */
	public void shootAction(){ //每10毫秒走一次
		shootIndex++; //每10毫秒增1
		if(shootIndex%30==0){ //每300(10*30)毫秒走一次
			Bullet[] bs = hero.shoot(); //获取英雄机发射出来的子弹对象
			bullets = Arrays.copyOf(bullets,bullets.length+bs.length); //扩容(bs有几个元素就扩大几个容量)
			System.arraycopy(bs,0,bullets,bullets.length-bs.length,bs.length); //数组的追加
		}
	}
	
	/** 飞行物移动 */
	public void stepAction(){ //每10毫秒走一次
		sky.step(); //天空移动
		for(int i=0;i<enemies.length;i++){ //遍历所有敌人
			enemies[i].step(); //敌人移动
		}
		for(int i=0;i<bullets.length;i++){ //遍历所有子弹
			bullets[i].step(); //子弹移动
		}
	}
	
	/** 启动程序的执行 */
	public void action(){
		Timer timer = new Timer(); //定时器对象
		int interval = 10; //定时间隔(以毫秒为单位)
		timer.schedule(new TimerTask(){
			public void run(){ //定时干的那个事(每10毫秒走一次)
				enterAction(); //敌人(小敌机、大敌机、小蜜蜂)入场
				shootAction(); //子弹入场
				stepAction();  //飞行物移动
				repaint();     //重画(重新调用paint()方法)
			}
		},interval,interval); //定时计划
	}
	
	/** 重写paint() g:画笔 */
	public void paint(Graphics g){
		sky.paintObject(g);  //画天空
		hero.paintObject(g); //画英雄机
		for(int i=0;i<enemies.length;i++){ //遍历所有敌人
			enemies[i].paintObject(g); //画敌人
		}
		for(int i=0;i<bullets.length;i++){ //遍历所有子弹
			bullets[i].paintObject(g); //画子弹
		}
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame(); //创建窗口
		World world = new World(); //创建面板
		frame.add(world); //将面板添加到窗口中
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //设置关闭窗口即退出程序
		frame.setSize(WIDTH,HEIGHT); //设置窗口大小
		frame.setLocationRelativeTo(null); //设置窗口居中显示
		frame.setVisible(true); //1)设置窗口可见  2)尽快调用paint()方法 
		
		world.action(); //启动程序的执行
	}

}






















