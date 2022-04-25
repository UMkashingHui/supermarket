package supermarket;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.math.BigDecimal;

public class Customer {
	public static double add(double v1, double v2)
	{
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}
	public void init() throws IOException{
		Scanner sc = new Scanner(System.in);
		System.out.println("                                       欢迎光临水果超市");
		System.out.println("请输入你的顾客Name:");
		System.out.print("欢迎您，顾客" + sc.next());		
		shop();
	}
	
	
	public void shop() throws IOException {
		Scanner sc = new Scanner(System.in);
		ArrayList<Fruit> list = new ArrayList<Fruit>();
		check(list);
		while (true) {
			System.out.println("                                       欢迎光临水果超市");
			System.out.println("请输入你的操作:(1.查看水果   2.购买水果   3.查看购物车   4.结账   5.退出)");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				// 查看水果
				print(list);
				break;
			case 2:
				// 购买水果
				buy(list);
				break;
			case 3:
				checkList(list);
				break;
			case 4:
				// 结账
				checkOut(list);
				break;
			case 5:
				// 退出
				return;
			default:
				System.out.println("你输入的操作有误!");
			}
 
		}
 
	}
	
	
	//结账
	private void checkOut(ArrayList<Fruit> list) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("                                       请选择结账模式");
			System.out.println("请输入你的操作:(1.普通结账   2.草莓八折结账   3.满100减10结账   4.退出)");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				// 普通结账
				checkOut_normal(list);
				System.out.println("欢迎下次光临！");
				return;
			case 2:
				// 草莓八折结账
				checkOut_promotion_strawberry(list);
				System.out.println("欢迎下次光临！");
				return;
			case 3:
				// 满100减10结账
				checkOut_promotion_reduction(list);
				System.out.println("欢迎下次光临！");
				return;
			case 4:
				// 退出
				return;
			default:
				System.out.println("你输入的操作有误!");
			}
		}

	}
	 
	// 普通结账
	private void checkOut_normal(ArrayList<Fruit> list) {
		
		double sum = 0;
		for (int i = 0; i < list.size(); i++) {
			Fruit f = list.get(i);
			sum += f.getMoney();
		}
		System.out.println("金额：" + sum+"元");
		//结完账后，将数量清0
		for (int i = 0; i < list.size(); i++) {
			Fruit f = list.get(i);
			f.setWeight(0);
		}
		
	}
	
	// 草莓八折结账
	private void checkOut_promotion_strawberry(ArrayList<Fruit> list) {
		double sum = 0;
		double p_money = 0;
		boolean promotion_flag = false;
		for (int i = 0; i < list.size(); i++) {
			Fruit f = list.get(i);
			if(f.getId().equals("2") && f.getWeight() > 0) {
				p_money = f.getMoney() * 0.2;
				sum += f.getMoney() * 0.8;
				promotion_flag = true;
				continue;
			}
			sum += f.getMoney();
		}
		
		if(promotion_flag == true){
			System.out.println("金额：" + add(sum, p_money) + "元, 优惠价格："+ sum +"元");
		}else{
			System.out.println("金额：" + sum +"元");
		}
		
		//结完账后，将数量清0
		for (int i = 0; i < list.size(); i++) {
			Fruit f = list.get(i);
			f.setWeight(0);
		}
	}
	
	// 满100减10结账
	private void checkOut_promotion_reduction(ArrayList<Fruit> list) {
		int sum = 0;
		int p_money = 0;
		for (int i = 0; i < list.size(); i++) {
			Fruit f = list.get(i);
			sum += f.getMoney();
		}
		int newSum = sum;
    	double c= sum / 100;
        System.out.println(c);
        
		if(sum >= 100) {
			for(int i = 0; i < c; i++) {
				p_money += 10;
				newSum	= (int) (newSum - 10);
			}
			 

		}
		System.out.println("金额：" + sum+ "元, 优惠价格："+ newSum+"元");
		
		//结完账后，将数量清0
		for (int i = 0; i < list.size(); i++) {
			Fruit f = list.get(i);
			f.setWeight(0);
		}
		
	}
	
	// 购买水果
	public void buy(ArrayList<Fruit> list) throws IOException {
		Scanner sc1 = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		print(list);
		while (true) {		
			System.out.println("请输入想要购买的水果的ID：(如果不想购买，请输入-1退出)");			
			String id = sc1.nextLine();
			if ("-1".equals(id)) {
				System.out.println("购买已结束，请去结账 ");
				return;
			} else {
				boolean flag = false;
				for (int i = 0; i < list.size(); i++) {
					Fruit f = list.get(i);
					if(f.getId().equals(id)) {
						System.out.println("请输入购买" + f.getName() + "重量： ");
						int num = sc2.nextInt();
						f.setWeight(num);
						flag = true;
					}
				}
				if(!flag){
					System.out.println("你输入的水果ID不正确,请重新输入");
				}
			}
 
		}
 
	}
	 
	
	// 查看购物车
	private void checkList(ArrayList<Fruit> list) {
		double sum = 0;
		System.out.println("ID\t水果\t单价\t重量\t总价");
		for (int i = 0; i < list.size(); i++) {
			Fruit f = list.get(i);
			System.out.println(f.getId() + "\t" + f.getName() + "\t" + f.getPrice() + "\t" + f.getWeight() + "\t" + f.getMoney());
			sum += f.getMoney();
		}
		System.out.println("总价："+sum);

	}
		
	// 查看水果
	public void check(ArrayList<Fruit> list) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("resources/fruit.txt"));
		String line;
		while ((line = br.readLine()) != null) {
			String[] str = line.split(" ");
			Fruit f = new Fruit(str[0], str[1], Integer.parseInt(str[2]), str[3]); // id, name, price, weight
			list.add(f);
		}
		br.close();
	}
 
	public void print(ArrayList<Fruit> list) {
		System.out.println("ID\t水果\t价格\t单位");
		for (int i = 0; i < list.size(); i++) {
			Fruit f = list.get(i);
			System.out.println(f.getId() + "\t" + f.getName() + "\t" + f.getPrice() + "\t" + f.getUnit());
		}
	}

}
