package portfolio.homework_week11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Stream;

public class VocManager{
	private String userName;
	private ArrayList<Word> voc = new ArrayList<>();

	static Scanner scan = new Scanner(System.in);
	
	VocManager(String userName){
		this.userName = userName;
	}
	
	void addWord(Word word) {
		voc.add(word);
	}
	
	void makeVoc(String fileName) {
		
		try(Scanner scan = new Scanner(new File(fileName))){
			while(scan.hasNextLine()) {
				String str = scan.nextLine();
				String[] temp = str.split("\t");
				this.addWord(new Word(temp[0].trim(), temp[1].trim()));
			}
			System.out.println(userName+"의 단어장이 생성되었습니다.");
			this.menu();
			
		}catch(FileNotFoundException e) {
			System.out.println(userName+"의 단어장이 생성되지 않았습니다. \n파일명을 확인하세요.");
		}
		
	}
	
	void menu() {		
		int choice = 0;
		while(choice != 4) {	
			System.out.println("\n------"+userName+"의 단어장 -------");
			System.out.println("1) 단어검색 2) 단어검색2 3) 빈출단어 4) 종료");			
			System.out.print("메뉴를 선택하세요 : ");
			choice = scan.nextInt();
			scan.nextLine();
			System.out.println();

			switch (choice) {
			case 1:
				searchVoc();
				break;
			case 2:
				searchVoc2();
				break;
			case 3:
				searchVoc3();
				break;
			case 4:				
				System.out.println(userName + "의 단어장 프로그램을 종료합니다.");
				break;
			}
				
		}		
	}
	
	public void searchVoc3() {
		Stream<Word> stream = voc.stream();
		voc.stream().sorted((o1, o2) -> (o1.num - o2.num)*-1).limit(5).forEach(word -> System.out.println(word.num + "번 -> " + word));
		
	}
	
	public void searchVoc2() {
		// TODO Auto-generated method stub	
		System.out.println("------ 단어 검색 ------");
		System.out.print("검색할 부분 단어를 입력하세요 (영단어) : ");
		String sWord = scan.nextLine();
		sWord = sWord.trim();
		for(Word word : voc) {
			if(word!=null) {
				if(word.eng.contains(sWord)) {
					System.out.println(word);
					word.num++;
				}
			}else {
				//System.out.println("단어장에 등록되어 있지 않습니다.");
				break;
			}
		}		
	}
	

	public void searchVoc() {
		// TODO Auto-generated method stub	
		System.out.println("------ 단어 검색 ------");
		System.out.print("검색할 단어를 입력하세요 (영단어) : ");
		String sWord = scan.nextLine();
		sWord = sWord.trim();
		for(Word word : voc) {
			if(word!=null) {
				if(word.eng.equals(sWord)) {
					System.out.println("단어의 뜻 : "+word.kor);
					word.num++;
					break;
				}
			}else {
				System.out.println("단어장에 등록되어 있지 않습니다.");
				break;
			}
		}		
	}
}








