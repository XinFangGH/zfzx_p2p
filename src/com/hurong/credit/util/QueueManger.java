package com.hurong.credit.util;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;
/**
 * 1.入队列 offer(obj); 
2.出队列 poll(); 
3.增加一元素 add(obj); 
4.增加一集合 addAll(c); 
5.读头元素 peek() or element() 
6.取队列长度 size(); 
7.清队列 clear() 
 * @author Administrator
 */
public class QueueManger {
	public static Queue<Object> q = new LinkedList<Object>();	
	/**
	 * 对象加入队列
	 * @param o
	 */
	public static void  QueueOffer(Object o){
		q.add(o);
	}
	/**
	 * 对象加入队列 offer  //队列满就返回false
	 * @param o
	 */
	public static boolean  QueueOffer1(Object o){
		return q.offer(o);
	}
	/**
	 * 集合加入队列
	 * @param o
	 */
	public static void  QueueOffer(Collection c){
		q.addAll(c);
	}
	/**
	 * 获取一个对象
	 * @param o
	 */
	public static Object  QueuePeek(){
		return q.peek();
	}
	/**
	 * 出队列
	 * @param o
	 */
	public static Object  QueuePoll(){
		 return q.poll();
	}
	/**
	 * 清空队列
	 * @param o
	 */
	public static void  QueueClear(){
		 q.clear();
	}
	/**
	 * 队列长度
	 * @param o
	 */
	public static int  QueueLen(){
		return q.size();
	}
	
/*	public static void main(String[] args) {
		String a[] = new String[] { "a", "b", "c", "d", "e", "f", "g", "h",
				"i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
				"u", "v", "w", "x", "y", "z" };
		Collection<String> c = new ArrayList<String>();
		c.add("1");
		c.add("2");
		c.add("3");

		// Putting the element into queue
		for (int i = 0; i < a.length; i++) {
			q.offer(a[i]);
		}
		// Getting the head element of queue
		System.out.println(q.peek());
		System.out.println(q.element());
		q.add("0");
		q.addAll(c);
		// Getting the size of queue
		System.out.println(q.size());
		// Pop the element
		while (q.size() > 0) {
			System.out.print(q.poll() + ",");
			System.out.println(q.peek() );
		}
		// Remove all element of queue
		q.clear();
	}*/
}
