package com.ywg.httpclient;

import java.util.ArrayList;
import java.util.List;

public class HelloJVM {

	//在JVM运行的时候会通过反射的方式到Method区域找到入口方法main
		public static void main(String[] args) {//main方法也是放在Method方法区域中的
			List list=new ArrayList<Student>();
			while(true) {
			/**
			 * student(小写的)是放在主线程中的Stack区域中的
			 * Student对象实例是放在所有线程共享的Heap区域中的
			 */
			Student student = new Student("spark");
			/**
			 * 首先会通过student指针（或句柄）（指针就直接指向堆中的对象，句柄表明有一个中间的,student指向句柄，句柄指向对象）
			 * 找Student对象，当找到该对象后会通过对象内部指向方法区域中的指针来调用具体的方法去执行任务
			 */
			
			student.sayHello();
			list.add(student);
			
			SSLSocketFactory 
			
		}
		}
	}
	 
	class Student {
		// name本身作为成员是放在stack区域的但是name指向的String对象是放在Heap中
		private String name;
		public Student(String name) {
			this.name = name;
		}
		//sayHello这个方法是放在方法区中的
		public void sayHello() {
		System.out.println("Hello, this is " + this.name);
		}
}
