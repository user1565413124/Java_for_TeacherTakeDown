package collection;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 集合存放的是元素的"引用"
 * @author adminitartor
 *
 */
public class CollectionDemo4 {
	public static void main(String[] args) {
		Collection c = new ArrayList();		
		Point p = new Point(1,2);		
		c.add(p);		
		System.out.println("p:"+p);//(1,2)
		System.out.println("c:"+c);//[(1,2)]
		
		p.setX(2);
		System.out.println("p:"+p);//(2,2)
		System.out.println("c:"+c);//[(?,2)]
	}
}





