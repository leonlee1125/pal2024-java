package PL112_10920131;

/*
1 int
2 float   
3 string   
4 +-
5 operater
6 (
7 )
8 / *
9 := df
10 other
*/


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;


class ListNode {  // 基本架構 ;
  int type;
  String item;
  ListNode next;

  ListNode(int type, String item) {
    this.type = type;
    this.item = item;
    this.next = null;
  }
} 


class iddata {
  String str;
  Float num;

  // 構造函數
  public iddata(String str, Float num) {
      this.str = str;
      this.num = num;
  }

  // 獲取 String
  public String getStr() {
      return str;
  }

  // 獲取 Float
  public Float getNum() {
      return num;
  }
}



class Main { // 注意類別名稱需要跟.java檔名相同
  
  static public ArrayList<iddata> gdefinename = new ArrayList<iddata>();
  
  static public ListNode commandhead ;
  
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
    else if ( Checkisallenglish( item ) ) type = 3  ;   
    else if ( item == "+" || item == "-" ) type = 4 ;
    else if ( item == "=" || item == "<>" || item == ">" || item == "<" || item == ">=" 
      || item == "<=" ) type = 5 ;
    else if ( item == "(" ) type = 6 ;    
    else if ( item == ")" ) type = 7 ;    
    else if ( item == "*" || item == "/" ) type = 8 ;    
    else if ( item == ":=" ) type = 9 ;    
    else type = 10 ; 
    
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
    // System.out.println(word);

      for (int i = 0; i < word.length(); i++) {
        char temp = word.charAt(i); // 获取位置i的字符
        if ( temp == ';' ) {
          hasend = true ;
          addtovector( ";" ) ;
        }
        else if ( temp == ':' ) { // 宣告
          if( i+1 < word.length() && word.charAt(i+1) == '=' ) addtovector( ":=" ) ;
          else {
            if( save != "" ) addtovector( save ) ;
            addtovector( ":" ) ;
          }
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
          if( i+1 < word.length() && ( word.charAt(i+1) == '/' ) ) {
          if( save != "" ) addtovector( save ) ;
          scanner.nextLine();
          }
          else {
            if( save != "" ) addtovector( save ) ;
            addtovector( "/" ) ;
          }
        }
        else if ( temp == ')' ) {
          if( save != "" ) addtovector( save ) ;
          addtovector( ")" ) ;
        }
        else if ( temp == '(' ) {
          if( save != "" ) addtovector( save ) ;
          addtovector( "(" ) ;
        }
        else if ( temp == '(' ) {
          if( save != "" ) addtovector( save ) ;
          addtovector( "(" ) ;
        }
        else if ( temp == '>' ) { // op
          if( i+1 < word.length() && ( word.charAt(i+1) == '=' ) ) addtovector( ">=" ) ;
          else {
            if( save != "" ) addtovector( save ) ;
            addtovector( ">" ) ;
          }
        }
        else if ( temp == '<' ) { // op
          if( i+1 < word.length() &&  word.charAt(i+1) == '=' ) addtovector( ">=" ) ;
          else if ( i+1 < word.length() &&  word.charAt(i+1) == '>' ) addtovector( "<>" ) ;
          else {
            if( save != "" ) addtovector( save ) ;
            addtovector( "<" ) ;
          }
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
  
  
  
  static public boolean command( ListNode checkitem ) {
    ListNode copy = checkitem ;
    if( checkitem.item == "quit" ) return true ;
    if( NOT_ID_StartArithExpOrBexp( checkitem ) ) {
    while( copy.next != null) copy = copy.next ;
    if( copy.item == ";" ) return true ;
    }
    if( checkitem.type == 3 ) {
    copy = checkitem.next;
    if( copy.type == 9 ) {
      copy = copy.next ;
      if ( ArithExp( copy )) {
        while( copy.next != null) copy = copy.next ;
        if( copy.item == ";" ) return true ;
      }
    }
    copy = checkitem.next;
    if ( IDlessArithExpOrBexp( copy )) {
      while( copy.next != null) copy = copy.next ;
      if( copy.item == ";" ) return true ;
    }
    }

    return false ;
  }
  
  
  static public boolean IDlessArithExpOrBexp( ListNode checkitem ) {
    while( checkitem.type == 8 || checkitem.type == 4 ) {
    checkitem = checkitem.next;
    if ( checkitem.type == 4 && !Term( checkitem ) ) return false ;
    if ( checkitem.type == 8 && !Factor( checkitem ) ) return false ; 
    }
    
    checkitem = checkitem.next;
    if( checkitem == null ) return true ;
    if( !BooleanOpratorwithArithExp( checkitem ) ) return false ;
    
    return true ;
  }
  
  
  static public boolean BooleanOprator( ListNode checkitem ) {
    if( checkitem.type == 5 ) return true;
    else return false ;
  }
  
  
  static public boolean BooleanOpratorwithArithExp( ListNode checkitem ) {
    if( !BooleanOprator( checkitem ) ) return false ;
    checkitem = checkitem.next;
    
    if( !ArithExp( checkitem ) ) return false ;
    else return true ;
  }
  

  static public boolean NOT_ID_StartArithExpOrBexp( ListNode checkitem ) {
    if( !NOT_ID_StartArithExp( checkitem ) ) return false ;
    checkitem = checkitem.next;

    if( checkitem == null ) return true ;
    if( !BooleanOpratorwithArithExp( checkitem ) ) return false ;

    return true;
  }
  
  
  static public boolean NOT_ID_StartArithExp( ListNode checkitem ) {
    if( !NOT_ID_StartTerm( checkitem ) ) return false ;
    checkitem = checkitem.next;
    
    if ( checkitem.type == 4 ) {
    while( checkitem.type == 4 ) {
      checkitem = checkitem.next;
      if( !Term( checkitem ) ) return false ;
      else checkitem = checkitem.next;
    }
    
    return true;
    }
    else return true ;
  }
  
  
  static public boolean NOT_ID_StartTerm( ListNode checkitem ) {
    if( !NOT_ID_StartFactor( checkitem ) ) return false ;
    checkitem = checkitem.next;
    
    if ( checkitem.type == 8 ) {
    while( checkitem.type == 8 ) {
      checkitem = checkitem.next;
      if( !Factor( checkitem ) ) return false ;
      else checkitem = checkitem.next;
    }
    
    return true;
    }
    else return true ;
  }
  
  
  static public boolean NOT_ID_StartFactor( ListNode checkitem ) {
    if ( checkitem.type == 4 ) {
    if ( checkitem.next.type == 1 || checkitem.next.type == 2 ) return true; 
    else return false;
    }
    else if ( checkitem.next.type == 1 || checkitem.next.type == 2 ) return true; 
    else if ( checkitem.next.type == 5 ) {
    if( ArithExpwithend( checkitem.next ) ) return true;
    else return false ;
    }
    else return false ;
  }
  
  
  static public boolean ArithExp( ListNode checkitem ) {
    if( !Term( checkitem ) ) return false ;
    checkitem = checkitem.next;
    
    if ( checkitem.type == 4 ) {
    while( checkitem.type == 4 ) {
      checkitem = checkitem.next;
      if( !Term( checkitem ) ) return false ;
      else checkitem = checkitem.next;
    }
    
    return true;
    }
    else return true ;
  }
  
  
  static public boolean ArithExpwithend( ListNode checkitem ) {
    if( !Term( checkitem ) ) return false ;
    checkitem = checkitem.next;
    
    if ( checkitem.type == 4 ) {
    while( checkitem.type == 4 ) {
      checkitem = checkitem.next;
      if( !Term( checkitem ) ) return false ;
      else checkitem = checkitem.next;
    }
    
    if( checkitem.type == 7 ) return true;
    else return false ;
    }
    else if( checkitem.type == 7 ) return true;
    else return false ;
  }
  
  
  static public boolean Term( ListNode checkitem ) {
    if( !Factor( checkitem ) ) return false ;
    checkitem = checkitem.next;
    
    if ( checkitem.type == 8 ) {
    while( checkitem.type == 8 ) {
      checkitem = checkitem.next;
      if( !Factor( checkitem ) ) return false ;
      else checkitem = checkitem.next;
    }
    
    return true;
    }
    else return true ;
  }
  
  
  static public boolean Factor( ListNode checkitem ) {
    if ( checkitem.type == 3 ) return true ;
    else if ( checkitem.type == 4 ) {
    if ( checkitem.next.type == 1 || checkitem.next.type == 2 ) return true; 
    else return false;
    }
    else if ( checkitem.next.type == 1 || checkitem.next.type == 2 ) return true; 
    else if ( checkitem.next.type == 5 ) {
    if( ArithExpwithend( checkitem.next ) ) return true;
    else return false ;
    }
    else return false ;
  }
  
  
  static public boolean syntaxcheck( ListNode head ) {
    if ( head == null) return false;
    if ( command( head )) return true ;
    else return false ;
  }
  
  
  static public boolean isStringInList(String searchStr) {
    for (iddata pair : gdefinename) {
        if (pair.getStr().equals(searchStr)) {
            return true; // 找到匹配的字符串，返回 true
        }
    }
    return false; // 沒有找到匹配的項目，返回 false
  }
  
  
  static public boolean checkidexist( ListNode head ) {
    head = head.next ;
    while( head != null ) {
    if ( !isStringInList (head.item) ) return false ;
    head = head.next ;
    }
    
    return true ;
  }
  
  
  static public void scanner () {
  System.out.println("pp0");

    readcommendandstore() ;
    // output() ;
  System.out.println("pp1");
    // test() ;
    syntaxcheck( commandhead ) ;
  System.out.println("pp2");

    checkidexist(commandhead) ;
    
  }
  
  
  static public void output() {
    while ( commandhead != null ) {
      System.out.println(commandhead.item);
      commandhead = commandhead.next ; 
    }
    
    commandhead = null;
  }
  
  static public float getnumber( String item ) {
    for (iddata pair : gdefinename) {
      if (pair.getStr().equals(item)) {
          return pair.getNum();
      }
    }
    // 沒有找到匹配的字符串，返回 null 或拋出異常
    return -999; // 或者可以選擇拋出一個異常
  }
  
  
  static public void addData(String str, Float num) {
    // 創建一個 StringFloatPair 對象
    iddata pair = new iddata(str, num);
    // 將對象添加到列表中
    gdefinename.add(pair);
  }
  
  
  private static float process(ListNode start, ListNode end) { //
    float result = 0;
    float lastNumber = 0;
    String lastOp = "+";
    if ("quit".equals(start.item)) {
      return Float.NaN; // 遇到"quit"时，返回特殊值
    }
    
    for (ListNode current = start; current != end; current = current.next) {
      System.out.println(current.item);
      if ( current.type == 1 || current.type == 2 ) { // Number
          lastNumber = Float.parseFloat(current.item);
      } 
      else if (current.type == 3) { // Variable
        lastNumber = getnumber(current.item);
      }
      
      if (lastOp.equals("+")) {
        result += lastNumber;
      } 
      else if (lastOp.equals("-")) {
        result -= lastNumber;
      } 
      else if (lastOp.equals("*")) {
        result *= lastNumber;
      } 
      else if (lastOp.equals("/")) {
        result /= lastNumber;
      }
      
      if (current.type == 6 || current.type == 7 || current.type == 4 
          || current.type == 8 ) { // Operator or parentheses
        if (current.item.equals("(")) {
          // Find corresponding closing parenthesis
          int balance = 1;
          ListNode temp = current.next;
          while (temp != null && balance != 0) {
            if (temp.item.equals("(")) balance++;
            else if (temp.item.equals(")")) balance--;
            temp = temp.next;
          }
          lastNumber = process(current.next, temp); // temp is null or the closing parenthesis
          current = temp; // Skip to the closing parenthesis

          // Apply the last operation
          if (lastOp.equals("+")) {
            result += lastNumber;
          } else if (lastOp.equals("-")) {
            result -= lastNumber;
          } else if (lastOp.equals("*")) {
            result *= lastNumber;
          } else if (lastOp.equals("/")) {
            result /= lastNumber;
          }
        } 
        else {
          lastOp = current.item;
        }
      } // else if
      
      lastNumber = 0 ;
    } // for
  System.out.println(result);
    return result;
  }
  
  
  public static void main(String[] args) {
  System.out.println("please input");
  
  int testnum = scanner.nextInt();

  scanner() ;
  ListNode end = commandhead ;
  while ( end.next != null ) end = end.next ;
  while( process( commandhead, end ) != Float.NaN ) {
    commandhead = null ;
    scanner() ;
    end = commandhead ;
    while ( end.next != null ) end = end.next ;
  }

    scanner.close();  
  System.out.println("finish");
  }
}
