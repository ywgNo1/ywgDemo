import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		String string=input.nextLine();
		input.close();
		boolean flag=true;
		if (string.endsWith("true")||string.endsWith("false")) {
			if (string.contains("true")) {
				string=string.substring(0, string.length()-5);
			}else{
				string=string.substring(0, string.length()-6);
				flag=false;
			}
		}
		String str=string;
		String[] strs=str.split("");
		if (!flag) {
			string=string.toLowerCase();
		}
		Map<String, Integer> map1=new HashMap<>();
		Map<String, Integer> map2=new HashMap<>();
		String[] arrays=string.split("");
		for (int i = 0; i < arrays.length; i++) {
			if (map1.get(arrays[i])!=null) {
				map1.put(arrays[i], map1.get(arrays[i])+1);
			}else{
				map1.put(arrays[i],1);
			}
			map2.put(arrays[i], i);
		}
		List<String> list=new ArrayList<>();
		for (String string2 : map1.keySet()) {
			if (map1.get(string2)==1) {
				list.add(string2);
			}
		}
		
		if (list.size()==0) {
			System.out.println();
		}else if (list.size()==1) {
			System.out.println(strs[map2.get(list.get(0))]);
		}else{
			int current=0;
			int or=map2.get(list.get(current));
			for (int i = 1; i < list.size(); i++) {
				if (map2.get(list.get(i))<map2.get(list.get(current))) {
					current=i;
					or=map2.get(list.get(i));
				}
			}
			System.out.println(strs[or]);
		}
		
	}
}
