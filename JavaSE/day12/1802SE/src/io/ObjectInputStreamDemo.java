package io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * 使用对象输入流完成对象"反序列化"
 * @author adminitartor
 *
 */
public class ObjectInputStreamDemo {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		FileInputStream fis
			= new FileInputStream("person.obj");
		
		ObjectInputStream ois
			= new ObjectInputStream(fis);
		
		Person p = (Person)ois.readObject();
		System.out.println(p);
		
		ois.close();
	}
}









