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

	
	
	
	public ListNode commandhead ;
	
	public Queue<String> input = new LinkedList<>();
	
	
	
  public boolean Checkisint( String token ) {  // 檢查int
  	boolean allnum = true ;
    int intnum = 0 ;

    for ( int i = 0 ; i < token.length() ; i++ ) {
      if ( token.charAt(i) < 48 || token.charAt(i) > 57 ) allnum = false ;
      else intnum++;
    } // for

    if ( allnum && intnum != 0 ) return true ;
    else return false ;
  } // Checkisint()

  
  
  boolean Checkisfloat( String token ) {  // 檢查float
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
  
  
  public void addtovector( String item ) {
  	int type = 0 ;
  	if ( Checkisint( item ) ) type = 1 ;
  	else if ( Checkisfloat( item ) ) type = 2  ;      	
  	else type = 3  ; 	
  	
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
  
  
  public void readcommendandstore() {
    Scanner scanner = new Scanner(System.in);
    boolean hasend = false ;
  	
  	String word = scanner.next();
  	String save = "" ;
  	while( !hasend ) { // 主要
      for (int i = 0; i < word.length(); i++) {
      	char temp = word.charAt(i); // 获取位置i的字符
        if ( temp == ';' ) hasend = true ;
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
        else {
        	save = save + word.charAt(i) ;
        }
      }
      
      if( save != "" ) addtovector( save ) ;
      	
  		if( !hasend ) word = scanner.next();
  	}
    scanner.close();
  }
  
  
  public boolean syntaxcheck() {
  
  
    return false ;
  }
  
  public boolean checkidexist() {
  	
  	
    return false ;

  }
  
  
  public void scanner () {
  	
  	readcommendandstore() ;
  	syntaxcheck() ;
  	checkidexist() ;
  	
  	
  	
  	
  }
  
  
  public void process() {
  	
  	
  	
  }
  
  
	public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
		int testnum = scanner.nextInt();
		
		ArrayList<ArrayList<String>> gdefinename = new ArrayList<>(); // 创建一个ArrayList
		
		
		
    scanner.close();

		System.out.println("Hello Java~~");
	}
}
