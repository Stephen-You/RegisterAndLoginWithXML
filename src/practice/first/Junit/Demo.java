package practice.first.Junit;

import org.junit.Test;

public class Demo {

	@Test
	public void test(){
		String str1 = "abc";
		String str2 = new String("abc");
		System.out.println(str1 != str2);
	}
}
