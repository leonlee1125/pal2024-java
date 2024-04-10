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
  int mtype;
  String mitem;
  ListNode mnext;

  ListNode( int type, String item ) {
    this.mtype = type;
    this.mitem = item;
    this.mnext = null;
  } // ListNode()
} // class ListNode


class Iddata {
  String mstr;
  Float mnum;

  // 構造函數
  public Iddata( String str, Float num ) {
    this.mstr = str;
    this.mnum = num;
    
  } // Iddata()

  // 獲取 String
  public String GetStr() {
    return mstr;
    
  } // GetStr()

  // 獲取 Float
  public Float GetNum() {
    return mnum;
    
  } // GetNum()
} // class Iddata()



class Main { // 注意類別名稱需要跟.java檔名相同
  
  static public ArrayList<Iddata> sgdefinename = new ArrayList<Iddata>();
  
  static public ListNode scommandhead ;
  
  private static Scanner scanner = new Scanner( System.in ); // 创建全局Scanner实例
  
  static public boolean Checkisint( String token ) {  // 檢查int
    boolean allnum = true ;
    int intnum = 0 ;

    for ( int i = 0 ; i < token.length() ; i++ ) {
      if ( token.charAt( i ) < 48 || token.charAt( i ) > 57 ) allnum = false ;
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
      if ( hasdot && token.charAt( i ) == 46 ) return false ;
      if ( token.charAt( i ) == 46 ) hasdot = true ;
      if ( ( token.charAt( i ) < 47 || token.charAt( i ) > 58 ) && token.charAt( i ) != 46 ) allnum = false ;
      if ( token.charAt( i ) > 47 && token.charAt( i )  < 58 ) countnum++;
    } // for

    if ( allnum && hasdot && countnum != 0 ) return true ;
    else return false ;
  } // Checkisfloat()
  
  
  static public boolean Checkisallenglish( String token ) {  // 檢查英文
      // 正则表达式匹配字符串仅包含英文大小写字母
    return token.matches( "[a-zA-Z]+" );
  } // Checkisallenglish()
  
  
  static public void Addtovector( String item ) {
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
    if ( scommandhead == null ) {
      scommandhead = newNode;
    } // if 
    else {
      ListNode last = scommandhead ;
      while ( last.mnext != null ) {
        last = last.mnext;
      } // while
      
      last.mnext = newNode;
    } // else
  } // Addtovector()
  
  
  static public void Readcommendandstore() {
    boolean hasend = false ;

    String word = "" ;
    
    if ( scanner.hasNext() ) {
      word = scanner.next();
    } // if
    
    String save = "" ;

    while ( !hasend ) { // 主要
    // System.out.println(word);

      for ( int i = 0; i < word.length() ; i++ ) {
        char temp = word.charAt( i ); // 获取位置i的字符
        if ( temp == ';' ) {
          hasend = true ;
          Addtovector( ";" ) ;
        } // if
        else if ( temp == ':' ) { // 宣告
          if ( i+1 < word.length() && word.charAt( i+1 ) == '=' ) Addtovector( ":=" ) ;
          else {
            if ( save != "" ) Addtovector( save ) ;
            Addtovector( ":" ) ;
          } // else
        } // else if
        else if ( temp == '=' ) {
          if ( save != "" ) Addtovector( save ) ;
          Addtovector( "=" ) ;
        } // else if
        else if ( temp == '+' ) {
          if ( save != "" ) Addtovector( save ) ;
          Addtovector( "+" ) ;
        } // else if
        else if ( temp == '-' ) {
          if ( save != "" ) Addtovector( save ) ;
          Addtovector( "-" ) ;
        } // else if
        else if ( temp == '*' ) {
          if ( save != "" ) Addtovector( save ) ;
          Addtovector( "*" ) ;
        } // else if
        else if ( temp == '/' ) {
          if ( i+1 < word.length() && ( word.charAt( i+1 ) == '/' ) ) {
            if ( save != "" ) Addtovector( save ) ;
            scanner.nextLine();
          } // if
          else {
            if ( save != "" ) Addtovector( save ) ;
            Addtovector( "/" ) ;
          } // else 
        } // else if
        else if ( temp == ')' ) {
          if ( save != "" ) Addtovector( save ) ;
          Addtovector( ")" ) ;
        } // else if
        else if ( temp == '(' ) {
          if ( save != "" ) Addtovector( save ) ;
          Addtovector( "(" ) ;
        } // else if
        else if ( temp == '(' ) {
          if ( save != "" ) Addtovector( save ) ;
          Addtovector( "(" ) ;
        } // else if
        else if ( temp == '>' ) { // op
          if ( i+1 < word.length() && ( word.charAt( i+1 ) == '=' ) ) Addtovector( ">=" ) ;
          else {
            if ( save != "" ) Addtovector( save ) ;
            Addtovector( ">" ) ;
          } // else 
        } // else if
        else if ( temp == '<' ) { // op
          if ( i+1 < word.length() &&  word.charAt( i+1 ) == '=' ) Addtovector( ">=" ) ;
          else if ( i+1 < word.length() &&  word.charAt( i+1 ) == '>' ) Addtovector( "<>" ) ;
          else {
            if ( save != "" ) Addtovector( save ) ;
            Addtovector( "<" ) ;
          } // else
        } // else if
        else {
          save = save + word.charAt( i ) ;
        } // else
      } // for
      
      if ( save != "" ) {
        Addtovector( save ) ;
        save = "" ;
      } // if
        
      if ( !hasend && scanner.hasNext() ) word = scanner.next();
    } // while
  } // Readcommendandstore()
  
  
  static public void Test() {
    Scanner scanner = new Scanner( System.in );
    boolean hasend = false ;

    String word = "" ;
    
    if ( scanner.hasNext() ) {
      word = scanner.next();
    } // if

    System.out.println( word );

    scanner.close();
  } // Test()
  
  
  
  static public boolean Command( ListNode checkitem ) {
    ListNode copy = checkitem ;
    if ( checkitem.mitem == "quit" ) return true ;
    if ( NOT_ID_StartArithExpOrBexp( checkitem ) ) {
      while ( copy.mnext != null ) copy = copy.mnext ;
      if ( copy.mitem == ";" ) return true ;
    } // if
    
    if ( checkitem.mtype == 3 ) {
      copy = checkitem.mnext;
      if ( copy.mtype == 9 ) {
        copy = copy.mnext ;
        if ( ArithExp( copy ) ) {
          while ( copy.mnext != null ) copy = copy.mnext ;
          if ( copy.mitem == ";" ) return true ;
        } // if
      } // if
      
      copy = checkitem.mnext;
      if ( IDlessArithExpOrBexp( copy ) ) {
        while ( copy.mnext != null ) copy = copy.mnext ;
        if ( copy.mitem == ";" ) return true ;
      } // if
    } // if

    return false ;
  } // Command()
  
  
  static public boolean IDlessArithExpOrBexp( ListNode checkitem ) {
    while ( checkitem.mtype == 8 || checkitem.mtype == 4 ) {
      checkitem = checkitem.mnext;
      if ( checkitem.mtype == 4 && !Term( checkitem ) ) return false ;
      if ( checkitem.mtype == 8 && !Factor( checkitem ) ) return false ; 
    } // while
    
    checkitem = checkitem.mnext;
    if ( checkitem == null ) return true ;
    if ( !BooleanOpratorwithArithExp( checkitem ) ) return false ;
    
    return true ;
  } // IDlessArithExpOrBexp()
  
  
  static public boolean BooleanOprator( ListNode checkitem ) {
    if ( checkitem.mtype == 5 ) return true;
    else return false ;
  } // BooleanOprator()
  
  
  static public boolean BooleanOpratorwithArithExp( ListNode checkitem ) {
    if ( !BooleanOprator( checkitem ) ) return false ;
    checkitem = checkitem.mnext;
    
    if ( !ArithExp( checkitem ) ) return false ;
    else return true ;
  } // BooleanOpratorwithArithExp()
  

  static public boolean NOT_ID_StartArithExpOrBexp( ListNode checkitem ) {
    if ( !NOT_ID_StartArithExp( checkitem ) ) return false ;
    checkitem = checkitem.mnext;

    if ( checkitem == null ) return true ;
    if ( !BooleanOpratorwithArithExp( checkitem ) ) return false ;

    return true;
  } // NOT_ID_StartArithExpOrBexp()
  
  
  static public boolean NOT_ID_StartArithExp( ListNode checkitem ) {
    if ( !NOT_ID_StartTerm( checkitem ) ) return false ;
    checkitem = checkitem.mnext;
    
    if ( checkitem.mtype == 4 ) {
      while ( checkitem.mtype == 4 ) {
        checkitem = checkitem.mnext;
        if ( !Term( checkitem ) ) return false ;
        else checkitem = checkitem.mnext;
      } // while
    
      return true;
    } // if
    else return true ;
  } // NOT_ID_StartArithExp()
  
  
  static public boolean NOT_ID_StartTerm( ListNode checkitem ) {
    if ( !NOT_ID_StartFactor( checkitem ) ) return false ;
    checkitem = checkitem.mnext;
    
    if ( checkitem.mtype == 8 ) {
      while ( checkitem.mtype == 8 ) {
        checkitem = checkitem.mnext;
        if ( !Factor( checkitem ) ) return false ;
        else checkitem = checkitem.mnext;
      } // while
    
      return true;
    } // if
    else return true ;
  } // NOT_ID_StartTerm()
  
  
  static public boolean NOT_ID_StartFactor( ListNode checkitem ) {
    if ( checkitem.mtype == 4 ) {
      if ( checkitem.mnext.mtype == 1 || checkitem.mnext.mtype == 2 ) return true; 
      else return false;
    } // if
    else if ( checkitem.mnext.mtype == 1 || checkitem.mnext.mtype == 2 ) return true; 
    else if ( checkitem.mnext.mtype == 5 ) {
      if ( ArithExpwithend( checkitem.mnext ) ) return true;
      else return false ;
    } // else if
    else return false ;
  } // NOT_ID_StartFactor()
  
  
  static public boolean ArithExp( ListNode checkitem ) {
    if ( !Term( checkitem ) ) return false ;
    checkitem = checkitem.mnext;
    
    if ( checkitem.mtype == 4 ) {
      while ( checkitem.mtype == 4 ) {
        checkitem = checkitem.mnext;
        if ( !Term( checkitem ) ) return false ;
        else checkitem = checkitem.mnext;
      } // while
    
      return true;
    } // if
    else return true ;
  } // ArithExp()
  
  
  static public boolean ArithExpwithend( ListNode checkitem ) {
    if ( !Term( checkitem ) ) return false ;
    checkitem = checkitem.mnext;
    
    if ( checkitem.mtype == 4 ) {
      while ( checkitem.mtype == 4 ) {
        checkitem = checkitem.mnext;
        if ( !Term( checkitem ) ) return false ;
        else checkitem = checkitem.mnext;
      } // while
    
      if ( checkitem.mtype == 7 ) return true;
      else return false ;
    } // if
    else if ( checkitem.mtype == 7 ) return true;
    else return false ;
  } // ArithExpwithend()
  
  
  static public boolean Term( ListNode checkitem ) {
    if ( !Factor( checkitem ) ) return false ;
    checkitem = checkitem.mnext;
    
    if ( checkitem.mtype == 8 ) {
      while ( checkitem.mtype == 8 ) {
        checkitem = checkitem.mnext;
        if ( !Factor( checkitem ) ) return false ;
        else checkitem = checkitem.mnext;
      } // while
    
      return true;
    } // if
    else return true ;
  } // Term()
  
  
  static public boolean Factor( ListNode checkitem ) {
    if ( checkitem.mtype == 3 ) return true ;
    else if ( checkitem.mtype == 4 ) {
      if ( checkitem.mnext.mtype == 1 || checkitem.mnext.mtype == 2 ) return true; 
      else return false;
    } // else if ()
    else if ( checkitem.mnext.mtype == 1 || checkitem.mnext.mtype == 2 ) return true; 
    else if ( checkitem.mnext.mtype == 5 ) {
      if ( ArithExpwithend( checkitem.mnext ) ) return true;
      else return false ;
    } // else if()
    else return false ;
  } // Factor()
  
  
  static public boolean Syntaxcheck( ListNode head ) {
    if ( head == null ) return false;
    if ( Command( head ) ) return true ;
    else return false ;
  } // Syntaxcheck()
  
  
  static public boolean IsStringInList( String searchStr ) {
    for ( int i = 0; i < sgdefinename.size() ; i++ ) {
      Iddata pair = sgdefinename.get( i );
      if ( pair.GetStr().equals( searchStr ) ) {
        return true; // 找到匹配的字符串，返回 true
      } // if
    } // for
    
    return false; // 沒有找到匹配的項目，返回 false
  } // IsStringInList()
  
  
  static public boolean Checkidexist( ListNode head ) {
    head = head.mnext ;
    while ( head != null ) {
      if ( !IsStringInList( head.mitem ) ) return false ;
      head = head.mnext ;
    } // while
    
    return true ;
  } // Checkidexist()
  
  
  static public void Handlegrammer() {
    System.out.println( "pp0" );

    Readcommendandstore() ;
    // Output() ;
    System.out.println( "pp1" );
    // test() ;
    Syntaxcheck( scommandhead ) ;
    System.out.println( "pp2" );

    Checkidexist( scommandhead ) ;
    
  } // Handlegrammer()
  
  
  static public void Output() {
    while ( scommandhead != null ) {
      System.out.println( scommandhead.mitem );
      scommandhead = scommandhead.mnext ; 
    } // while
    
    scommandhead = null;
  } // Output()
  
  static public float Getnumber( String item ) {
    for ( int i = 0; i < sgdefinename.size() ; i++ ) {
      Iddata pair = sgdefinename.get( i );
      if ( pair.GetStr().equals( item ) ) {
        return pair.GetNum();
      } // if
    } // for
    // 沒有找到匹配的字符串，返回 null 或拋出異常
    return -999; // 或者可以選擇拋出一個異常
  } // Getnumber()
  
  
  static public void AddData( String str, Float num ) {
    // 創建一個 StringFloatPair 對象
    Iddata pair = new Iddata( str, num );
    // 將對象添加到列表中
    sgdefinename.add( pair );
  } // AddData()
  
  
  private static float Process( ListNode start, ListNode end ) { //
    float result = 0;
    float lastNumber = 0;
    String lastOp = "+";
    if ( start.mitem.equals( "quit" ) ) {
      return -99999; // 遇到"quit"时，返回特殊值
    } // if
    
    for ( ListNode current = start; current != end ; current = current.mnext ) {
      System.out.println( current.mitem );
      if ( current.mtype == 1 || current.mtype == 2 ) { // Number
        lastNumber = Float.parseFloat( current.mitem );
      } // if
      else if ( current.mtype == 3 ) { // Variable
        lastNumber = Getnumber( current.mitem );
      } // else if
      
      if ( lastOp.equals( "+" ) ) {
        result += lastNumber;
      } // if 
      else if ( lastOp.equals( "-" ) ) {
        result -= lastNumber;
      } // else if
      else if ( lastOp.equals( "*" ) ) {
        result *= lastNumber;
      } // else if
      else if ( lastOp.equals( "/" ) ) {
        result /= lastNumber;
      } // else if
      
      if ( current.mtype == 6 || current.mtype == 7 || current.mtype == 4 
           || current.mtype == 8 ) { // Operator or parentheses
        if ( current.mitem.equals( "(" ) ) {
          // Find corresponding closing parenthesis
          int balance = 1;
          ListNode temp = current.mnext;
          while ( temp != null && balance != 0 ) {
            if ( temp.mitem.equals( "(" ) ) balance++;
            else if ( temp.mitem.equals( ")" ) ) balance--;
            temp = temp.mnext;
          } // while
          
          lastNumber = Process( current.mnext, temp ); // temp is null or the closing parenthesis
          current = temp; // Skip to the closing parenthesis

          // Apply the last operation
          if ( lastOp.equals( "+" ) ) {
            result += lastNumber;
          } // if
          else if ( lastOp.equals( "-" ) ) {
            result -= lastNumber;
          } // else if
          else if ( lastOp.equals( "*" ) ) {
            result *= lastNumber;
          } // else if 
          else if ( lastOp.equals( "/" ) ) {
            result /= lastNumber;
          } // else if
        } // if
        else {
          lastOp = current.mitem;
        } // else
      } // if
      
      lastNumber = 0 ;
    } // for
    
    System.out.println( result );
    return result;
  } // Process()
  
  
  public static void main( String[] args ) {
    System.out.println( "please input" );
  
    int testnum = scanner.nextInt();

    Handlegrammer() ;
    ListNode end = scommandhead ;
    while ( end.mnext != null ) end = end.mnext ;
    while ( Process( scommandhead, end ) != -99999 ) {
      scommandhead = null ;
      Handlegrammer() ;
      end = scommandhead ;
      while ( end.mnext != null ) end = end.mnext ;
    } // while

    scanner.close();  
    System.out.println( "finish" );
  } // main()
} // class Main()
