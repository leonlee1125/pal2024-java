package PL112_10920131;

/*
1 int
2 float   
3 string   
4 mark
*/


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Main { // 注意類別名稱需要跟.java檔名相同

	static class ListNode { // 基本架構
		int type;
		String item;
		ListNode next;

		ListNode(int type, String item) {
			this.type = type;
			this.item = item;
			this.next = null;
		}
	}

	
	
	
	static public ListNode commandhead ;
	
	static public Queue<String> input = new LinkedList<>();
	
	private static Scanner scanner = new Scanner(System.in); // 创建全局Scanner实例
	
	static public boolean Checkisint( String token ) {  // 檢查int
  	boolean allnum = true ;
    int intnum = 0 ;

    for ( int i = 0 ; i < token.length() ; i++ ) {
      if ( token.charAt(i) < 48 || token.charAt(i) > 57 ) allnum = false ;
      else intnum++;
    } // for

    if ( allnum && intnum != 0 ) return true ;
    else return false ;
  } // Checkisint()

  
  
  static boolean Checkisfloat( String token ) {  // 檢查float
  	boolean hasdot = false ;
  	boolean allnum = true ;
    int countnum = 0 ;

    for ( int i = 0 ; i < token.length() ; i++ ) {
      if ( hasdot && token.charAt(i) == 46 ) return false ;
      if ( token.charAt(i) == 46 ) hasdot = true ;
      if ( ( token.charAt(i) < 47 || token.charAt(i) > 58 ) && token.charAt(i) != 46 ) allnum = false ;
      if ( token.charAt(i) > 47 && token.charAt(i) < 58 ) countnum++;
    } // for

    if ( allnum && hasdot && countnum != 0 ) return true ;
    else return false ;
  } // Checkisfloat()
  
  
	static public boolean Checkisallenglish( String token ) {  // 檢查英文
      // 正则表达式匹配字符串仅包含英文大小写字母
      return token.matches("[a-zA-Z]+");
  } // Checkisint()
  
	
  static public void addtovector( String item ) {
  	int type = 0 ;
  	if ( Checkisint( item ) ) type = 1 ;
  	else if ( Checkisfloat( item ) ) type = 2  ;      	
  	else if ( Checkisallenglish( item ) )type = 3  ; 	
  	else type = 4 ;
  	
    ListNode newNode = new ListNode( type, item );
    if (commandhead == null) {
    	commandhead = newNode;
    } else {
        ListNode last = commandhead ;
        while (last.next != null) {
            last = last.next;
        }
        last.next = newNode;
    }
  }
  
  
  static public void readcommendandstore() {
    boolean hasend = false ;

    String word = "" ;
    
    if (scanner.hasNext()) {
      word = scanner.next();
    }
    
  	String save = "" ;

  	while( !hasend ) { // 主要
  		System.out.println(word);

      for (int i = 0; i < word.length(); i++) {
      	char temp = word.charAt(i); // 获取位置i的字符
        if ( temp == ';' ) {
        	hasend = true ;
        	addtovector( ";" ) ;
        }
        else if ( temp == ':' ) { // 宣告
        	if( save != "" ) addtovector( save ) ;
        	addtovector( ":" ) ;
        }
        else if ( temp == '=' ) {
        	if( save != "" ) addtovector( save ) ;
        	addtovector( "=" ) ;
        }
        else if ( temp == '+' ) {
        	if( save != "" ) addtovector( save ) ;
        	addtovector( "+" ) ;
        }
        else if ( temp == '-' ) {
        	if( save != "" ) addtovector( save ) ;
        	addtovector( "-" ) ;
        }
        else if ( temp == '*' ) {
        	if( save != "" ) addtovector( save ) ;
        	addtovector( "*" ) ;
        }
        else if ( temp == '/' ) {
        	if( save != "" ) addtovector( save ) ;
        	addtovector( "/" ) ;
        }
        else if ( temp == ')' ) {
        	if( save != "" ) addtovector( save ) ;
        	addtovector( ")" ) ;
        }
        else if ( temp == '(' ) {
        	if( save != "" ) addtovector( save ) ;
        	addtovector( "(" ) ;
        }
        else {
        	save = save + word.charAt(i) ;
        }
      }
      
      if( save != "" ) {
      	addtovector( save ) ;
      	save = "" ;
      }
      	
  		if( !hasend && scanner.hasNext()) word = scanner.next();
  	}
  }
  
  
  static public void test() {
    Scanner scanner = new Scanner(System.in);
    boolean hasend = false ;

    String word = "" ;
    
    if (scanner.hasNext()) {
      word = scanner.next();
    }

  	System.out.println(word);

	
    scanner.close();
  }
  
  static public boolean checkcommand( ListNode checkitem ) {
  	
  	
  	
  	return false ;
  }
  
  
  static public boolean statement( ListNode checkitem ) {
  	
  	
  	
  	return false ;
  }
  
  
  static public boolean booleanexp( ListNode checkitem ) {
  	
  	
  	
  	return false ;
  }
  
  
  static public boolean arithexp( ListNode checkitem ) {
  	
  	
  	
  	return false ;
  }
  
  
  static public boolean term( ListNode checkitem ) {
  	
  	
  	
  	return false ;
  }
  
  
  static public boolean factor( ListNode checkitem ) {
  	
  	
  	
  	return false ;
  }
  
  
  static public boolean syntaxcheck( ListNode head ) {
  	if ( head == null) return false;
  	if ( checkcommand( head )) return true ;
  	else return false ;
  }
  
  
  static public boolean checkidexist() {
  	
  	
    return false ;
  }
  
  
  static public void scanner () {
		System.out.println("0");

  	readcommendandstore() ;
  	// output() ;
		System.out.println("1");

  	//syntaxcheck() ;
		System.out.println("2");

  	//checkidexist() ;
  	
  }
  
  
  static public void output() {
  	while ( commandhead != null ) {
  	  System.out.println(commandhead.item);
  	  commandhead = commandhead.next ; 
    }
  	
  	commandhead = null;
  }
  
  
  
  static public boolean process() {
  	if( commandhead != null ) {
  		output();
  		return true ;
  	}
  	else return false ;
  }
  
  
  
  
  
  public static void main(String[] args) {
		System.out.println("please input");
	
		int testnum = scanner.nextInt();
    	
		ArrayList<ArrayList<String>> gdefinename = new ArrayList<>(); // 创建一个ArrayList

		scanner() ;
		while( process() ) scanner() ;
		
    scanner.close();	
		System.out.println("finish");
	}
}
