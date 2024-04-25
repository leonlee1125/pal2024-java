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
import java.util.Vector;



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


class MStack {
  private Vector<String> mstack;
  private int mtop;

  public MStack() {
    mstack = new Vector<String>();
    mtop = -1; // 初始化時堆疊是空的
  } // MStack()

  // 元素入棧
  public void Push( String item ) {
    mstack.add( item );
    mtop++;
  } // Push()

  // 元素出棧
  public String Pop() {
    String item = mstack.remove( mtop );
    mtop--;
    return item;
  } // Pop()

  // 查看棧頂元素
  public String Peek() {
    return mstack.get( mtop );
  } // Peek()

  // 判斷堆疊是否為空
  public boolean IsEmpty() {
    return mtop == -1;
  } // IsEmpty()

  // 獲取堆疊的大小
  public int Size() {
    return mtop + 1;
  } // Size()
} // class MStack



class Iddata {
  String mstr;
  Float mnum;
  String mtype ;
  int msize ;

  // 構造函數
  public Iddata( String str, float num, String type, int size ) {
    this.mstr = str;
    this.mnum = num;
    this.mtype = type ;
    this.msize = size ;
  } // Iddata()

  // 獲取 String
  public String GetStr() {
    return mstr;
    
  } // GetStr()

  // 獲取 Float
  public Float GetNum() {
    return mnum;
    
  } // GetNum()
  
  public String Gettype() {
    return mtype;
    
  } // Gettype() 
  
  public void SetNum( float num ) {
    this.mnum = num;
  } // SetNum()

  public void SetType( String type ) {
    this.mtype = type;
  } // SetType()
  
  
} // class Iddata()


class Stringdata {
  String mstr;
  String mitem ;


  // 構造函數
  public Stringdata( String str, String item ) {
    this.mstr = str;
    this.mitem = item;
    
  } // Stringdata()

  // 獲取 String
  public String GetStr() {
    return mstr;
    
  } // GetStr()
  
  public String Getitem() {
    return mitem;
    
  } // Getitem()
  
  public void Setitem( String item ) {
    this.mitem = item;
    
  } // Setitem()
  
  
} // class Stringdata()



class Functiondata {
  String mstr;
  String mitem ;

  // 構造函數
  public Functiondata( String str, String item ) {
    this.mstr = str;
    this.mitem = item;
    
  } // Stringdata()

  // 獲取 String
  public String GetStr() {
    return mstr;
    
  } // GetStr()
  
  public String Getitem() {
    return mitem;
    
  } // Getitem()
  
  public void Setitem( String item ) {
    this.mitem = item;
    
  } // Setitem()
  
  
} // class Functiondata()





class Main { // 注意類別名稱需要跟.java檔名相同
  
  static public ArrayList<Iddata> sgdefinename = new ArrayList<Iddata>();
  
  static public ArrayList<Stringdata> sgdefinestring = new ArrayList<Stringdata>();
  
  static public ArrayList<Functiondata> sgdefinefunction = new ArrayList<Functiondata>();

  static public ListNode scommandhead ;
  
  static public int readline = 0 ;
  
  static public boolean isfirst = true ;
  
  static public ListNode schecksynuse ;
  
  private static Scanner scanner = new Scanner( System.in ); // 创建全局Scanner实例
  
  static public boolean Checkisint( String token ) {  // 檢查int
    boolean allnum = true ;
    int intnum = 0 ;

    if ( token.charAt( 0 ) == '+' || token.charAt( 0 ) == '-' ) {
      for ( int i = 1 ; i < token.length() ; i++ ) {
        if ( token.charAt( i ) < 48 || token.charAt( i ) > 57 ) allnum = false ;
        else intnum++;
      } // for
    } // if
    else {
      for ( int i = 0 ; i < token.length() ; i++ ) {
        if ( token.charAt( i ) < 48 || token.charAt( i ) > 57 ) allnum = false ;
        else intnum++;
      } // for
    } // else

    if ( allnum && intnum != 0 ) return true ;
    else return false ;
  } // Checkisint()

  
  
  static boolean Checkisfloat( String token ) {  // 檢查float
    boolean hasdot = false ;
    boolean allnum = true ;
    int countnum = 0 ;

    
    if ( token.charAt( 0 ) == '+' || token.charAt( 0 ) == '-' ) {
      for ( int i = 1 ; i < token.length() ; i++ ) {
        if ( hasdot && token.charAt( i ) == 46 ) return false ;
        if ( token.charAt( i ) == 46 ) hasdot = true ;
        if ( ( token.charAt( i ) < 47 || token.charAt( i ) > 58 ) && 
             token.charAt( i ) != 46 ) allnum = false ;
        if ( token.charAt( i ) > 47 && token.charAt( i ) < 58 ) countnum++;
      } // for
    } // if
    else {
      for ( int i = 0 ; i < token.length() ; i++ ) {
        if ( hasdot && token.charAt( i ) == 46 ) return false ;
        if ( token.charAt( i ) == 46 ) hasdot = true ;
        if ( ( token.charAt( i ) < 47 || token.charAt( i ) > 58 ) && 
             token.charAt( i ) != 46 ) allnum = false ;
        if ( token.charAt( i ) > 47 && token.charAt( i ) < 58 ) countnum++;
      } // for
    } // else
    

    if ( allnum && hasdot && countnum != 0 ) return true ;
    else return false ;
  } // Checkisfloat()
  
  
  static public boolean Checkisallenglish( String token ) {  // 檢查英文
      // 正则表达式匹配字符串仅包含英文大小写字母
    return token.matches("[a-zA-Z][a-zA-Z0-9]*");
  } // Checkisallenglish()
  
  public static boolean IsLetterDigit( char c ) {
    return Character.isLetter( c ) || Character.isDigit( c );
  } // IsLetterDigit()
  
  
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
    else if ( item.charAt( 0 ) == '\"'  ) type = 10 ;
    else if ( item.charAt( 0 ) == '['  ) type = 11 ;
    else type = 12 ; 
    
    // System.out.println( type );

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
  
  
  
  
  
  static public String slineleft = "" ;
  
  static public void Readcommendandstore() {
    boolean hasend = false ;
    boolean inquotation = false ;
    
    
    String line = "" ;
    String save = "" ;
    
    // System.out.println( slineleft );
    

    if ( !slineleft.trim().isEmpty() ) {
      line = slineleft ;
      slineleft = "" ;
    } // if
    else {
      if ( scanner.hasNext() ) {
        line = scanner.nextLine();
        readline++ ;
      } // if  
    } // else
    
    
    while ( !hasend || inquotation ) { // 主要
      // System.out.println(line);
      
      for ( int i = 0; i < line.length() && !hasend || i < line.length() && inquotation ; i++ ) {
        char temp = line.charAt( i ); // 获取位置i的字符
        if ( temp == ' ' ) {
          if ( save != "" ) { 
            Addtovector( save ) ;
            save = "" ;
          } // if
        } // if
        else if ( temp == ';' ) {
          if ( save != "" ) { 
            Addtovector( save ) ;
            save = "" ;
          } // if
          
          hasend = true ;
          if ( i != line.length()-1 ) slineleft = line.substring( i+1, line.length() );
          // System.out.println( slineleft );
          Addtovector( ";" ) ;
        } // else if
        else if ( temp == ':' ) { // 宣告
          if ( i+1 < line.length() && line.charAt( i+1 ) == '=' ) {
            i++;
            Addtovector( ":=" ) ;
          } // if
          else {
            if ( save != "" ) { 
              Addtovector( save ) ;
              save = "" ;
            } // if
            
            Addtovector( ":" ) ;
          } // else
        } // else if
        else if ( temp == '\"' ) {
          if ( save != "" ) { 
            Addtovector( save ) ;
            save = "" ;
          } // if
          
          int firstchar = i ;
          i++;
          char findend = line.charAt( i );
          while ( findend != '\"' ) {
            i++ ;
            findend = line.charAt( i );
          } // while

          Addtovector( line.substring( firstchar, i ) ) ;
        } // else if
        else if ( temp == '[' ) {
          if ( save != "" ) { 
            Addtovector( save ) ;
            save = "" ;
          } // if
          
          int firstchar = i ;
          i++;
          char findend = line.charAt( i );
          while ( findend != ']' ) {
            i++ ;
            findend = line.charAt( i );
          } // while

          Addtovector( line.substring( firstchar, i ) ) ;
        } // else if
        else if ( temp == '=' ) {
          if ( save != "" ) { 
            Addtovector( save ) ;
            save = "" ;
          } // if
          
          Addtovector( "=" ) ;
        } // else if
        else if ( temp == '+' ) {
          if ( save != "" ) { 
            Addtovector( save ) ;
            save = "" ;
          } // if
          
          Addtovector( "+" ) ;
        } // else if
        else if ( temp == '-' ) {
          if ( save != "" ) { 
            Addtovector( save ) ;
            save = "" ;
          } // if
          
          if ( i+1 < line.length() && line.charAt( i+1 ) == ' ' ) Addtovector( "-" ) ;
          else if ( i+1 < line.length() && !Character.isDigit( line.charAt( i+1 ) ) ) Addtovector( "-" ) ;
          else save = "-" ;
          
        } // else if
        else if ( temp == '*' ) {
          if ( save != "" ) { 
            Addtovector( save ) ;
            save = "" ;
          } // if
          
          Addtovector( "*" ) ;
        } // else if
        else if ( temp == '/' ) {
          if ( i+1 < line.length() && ( line.charAt( i+1 ) == '/' ) ) {
            
            line = scanner.nextLine();
            readline++ ;
            while ( line.trim().isEmpty() ) {
              line = scanner.nextLine(); 
              readline++;
            }
            i = -1 ;
            
          } // if
          else {
            if ( save != "" ) { 
              Addtovector( save ) ;
              save = "" ;
            } // if
            
            Addtovector( "/" ) ;
          } // else 
        } // else if
        else if ( temp == ')' ) {
          if ( save != "" ) { 
            Addtovector( save ) ;
            save = "" ;
          } // if
          
          Addtovector( ")" ) ;
        } // else if
        else if ( temp == '(' ) {
          if ( save != "" ) { 
            Addtovector( save ) ;
            save = "" ;
          } // if
          
          Addtovector( "(" ) ;
        } // else if
        else if ( temp == '{' ) {
          if ( save != "" ) { 
            Addtovector( save ) ;
            save = "" ;
          } // if
          
          inquotation = true ;
          Addtovector( "{" ) ;
        } // else if
        else if ( temp == '}' ) {
          if ( save != "" ) { 
            Addtovector( save ) ;
            save = "" ;
          } // if
          
          if ( i != line.length()-1 ) slineleft = line.substring( i+1, line.length() );
          hasend = true ;
          inquotation = false ;
          Addtovector( "}" ) ;
        } // else if
        else if ( temp == '>' ) { // op
          if ( i+1 < line.length() && ( line.charAt( i+1 ) == '>' ) ) {
            i++ ;
            Addtovector( ">>" ) ;
          } // if
          else {
            if ( save != "" ) { 
              Addtovector( save ) ;
              save = "" ;
            } // if
            
            Addtovector( ">" ) ;
          } // else 
        } // else if
        else if ( temp == '<' ) { // op
          if ( i+1 < line.length() &&  line.charAt( i+1 ) == '=' ) {
            Addtovector( "<=" ) ;
            i++ ;
          } // if
          else if ( i+1 < line.length() &&  line.charAt( i+1 ) == '>' ) {
            Addtovector( "<>" ) ;
            i++ ;
          } // else if
          else if ( i+1 < line.length() &&  line.charAt( i+1 ) == '<' ) {
            Addtovector( "<<" ) ;
            i++ ;
          } // else if
          else {
            if ( save != "" ) { 
              Addtovector( save ) ;
              save = "" ;
            } // if
            
            Addtovector( "<" ) ;
          } // else
        } // else if
        else {
          if ( IsLetterDigit( temp ) ) save = save + line.charAt( i ) ;
        } // else
        
        
        if ( hasend && !inquotation )  { 
          if ( i != line.length()-1 ) slineleft = line.substring( i+1, line.length() );
        } // if
        
      } // for
      
      
      
      // System.out.println( word );
      // System.out.println( save );
      
      if ( save != "" ) {
        // System.out.println( save );
        Addtovector( save ) ;
        save = "" ;
      } // if
      
      if ( !hasend && !slineleft.trim().isEmpty() || inquotation && !slineleft.trim().isEmpty() ) {
        line = slineleft;
        slineleft = "" ;
        
      } // if
      else if ( !hasend && scanner.hasNext() || inquotation && scanner.hasNext() ) {
        line = scanner.nextLine();
        readline++;
        while ( line.trim().isEmpty() ) {
          line = scanner.nextLine(); 
          readline++;
        }
        // System.out.println( line );
      } // if
      
    } // while
    // System.out.println( "finishread" );
  } // Readcommendandstore()
  
  
  
  
  
  // =====================================================================================================
  
  
  static public boolean Command() {
    System.out.println( "Command" );
    if ( schecksynuse.mitem == "quit" ) return true ;
    else if ( NOT_ID_StartArithExpOrBexp() ) {
      // schecksynuse = schecksynuse.mnext ;
      if ( schecksynuse.mitem == ";" ) return true ;
    } // if
    else if ( schecksynuse.mtype == 3 ) {
      schecksynuse = schecksynuse.mnext;
      if ( schecksynuse.mtype == 9 ) {
        schecksynuse = schecksynuse.mnext ;
        if ( ArithExp() ) {
          while ( schecksynuse.mnext != null ) schecksynuse = schecksynuse.mnext ;
          if ( schecksynuse.mitem == ";" ) return true ;
        } // if
      } // if
      
      schecksynuse = schecksynuse.mnext;
      if ( IDlessArithExpOrBexp() ) {
        while ( schecksynuse.mnext != null ) schecksynuse = schecksynuse.mnext ;
        if ( schecksynuse.mitem == ";" ) return true ;
      } // if
    } // if

    return false ;
  } // Command()
  
  
  static public boolean IDlessArithExpOrBexp() {
    System.out.println( "IDlessArithExpOrBexp" );
    while ( schecksynuse.mtype == 8 || schecksynuse.mtype == 4 ) {
      if ( schecksynuse.mtype == 4 ) {
        schecksynuse = schecksynuse.mnext;
        if ( !Term() ) return false ;
      } // if
      
      if ( schecksynuse.mtype == 8 ) {
        schecksynuse = schecksynuse.mnext;
        if ( !Factor() ) return false ;
      } // if
      
      schecksynuse = schecksynuse.mnext;
    } // while
    

    if ( schecksynuse.mitem == ";" ) return true ;
    if ( !BooleanOpratorwithArithExp() ) return false ;
    
    return true ;
  } // IDlessArithExpOrBexp()
  
  
  static public boolean BooleanOprator() {
    System.out.println( "BooleanOprator" );
    if ( schecksynuse.mtype == 5 ) return true;
    else return false ;
  } // BooleanOprator()
  
  
  static public boolean BooleanOpratorwithArithExp() {
    System.out.println( "BooleanOpratorwithArithExp" );
    if ( !BooleanOprator() ) return false ;
    schecksynuse = schecksynuse.mnext;
    
    if ( !ArithExp() ) return false ;
    else return true ;
  } // BooleanOpratorwithArithExp()
  

  static public boolean NOT_ID_StartArithExpOrBexp() {
    System.out.println( "NOT_ID_StartArithExpOrBexp" );
    if ( !NOT_ID_StartArithExp() ) return false ;
    schecksynuse = schecksynuse.mnext;

    System.out.println( schecksynuse.mitem );
    
    if ( schecksynuse.mitem == ";" ) return true ;
    if ( !BooleanOpratorwithArithExp() ) return false ;

    return true;
  } // NOT_ID_StartArithExpOrBexp()
  
  
  static public boolean NOT_ID_StartArithExp() {
    System.out.println( "NOT_ID_StartArithExp" );
    if ( !NOT_ID_StartTerm() ) return false ;
    schecksynuse = schecksynuse.mnext;
    
    if ( schecksynuse.mtype == 4 ) {
      while ( schecksynuse.mtype == 4 ) {
        schecksynuse = schecksynuse.mnext;
        if ( !Term() ) return false ;
        else schecksynuse = schecksynuse.mnext;
      } // while
    
      return true;
    } // if
    else return true ;
  } // NOT_ID_StartArithExp()
  
  
  static public boolean NOT_ID_StartTerm() {
    System.out.println( "NOT_ID_StartTerm" );
    if ( !NOT_ID_StartFactor() ) return false ;
    schecksynuse = schecksynuse.mnext;
    
    if ( schecksynuse.mtype == 8 ) {
      while ( schecksynuse.mtype == 8 ) {
        schecksynuse = schecksynuse.mnext;
        if ( !Factor() ) return false ;
        else schecksynuse = schecksynuse.mnext;
      } // while
    
      return true;
    } // if
    else return true ;
  } // NOT_ID_StartTerm()
  
  
  static public boolean NOT_ID_StartFactor() {
    System.out.println( "NOT_ID_StartFactor" );
    if ( schecksynuse.mtype == 4 ) {
      if ( schecksynuse.mnext.mtype == 1 || schecksynuse.mnext.mtype == 2 ) return true; 
      else return false;
    } // if
    else if ( schecksynuse.mtype == 1 || schecksynuse.mtype == 2 ) return true; 
    else if ( schecksynuse.mtype == 5 ) {
      if ( ArithExpwithend() ) return true;
      else return false ;
    } // else if
    else return false ;
  } // NOT_ID_StartFactor()
  
  
  static public boolean ArithExp() {
    System.out.println( "ArithExp" );
    if ( !Term() ) return false ;
    schecksynuse = schecksynuse.mnext;
    
    if ( schecksynuse.mtype == 4 ) {
      while ( schecksynuse.mtype == 4 ) {
        schecksynuse = schecksynuse.mnext;
        if ( !Term() ) return false ;
        else schecksynuse = schecksynuse.mnext;
      } // while
    
      return true;
    } // if
    else return true ;
  } // ArithExp()
  
  
  static public boolean ArithExpwithend() {
    System.out.println( "ArithExpwithend" );
    if ( !Term() ) return false ;
    schecksynuse = schecksynuse.mnext;
    
    if ( schecksynuse.mtype == 4 ) {
      while ( schecksynuse.mtype == 4 ) {
        schecksynuse = schecksynuse.mnext;
        if ( !Term() ) return false ;
        else schecksynuse = schecksynuse.mnext;
      } // while
    
      if ( schecksynuse.mtype == 7 ) return true;
      else return false ;
    } // if
    else if ( schecksynuse.mtype == 7 ) return true;
    else return false ;
  } // ArithExpwithend()
  
  
  static public boolean Term() {
    System.out.println( "Term" );
    if ( !Factor() ) return false ;
    schecksynuse = schecksynuse.mnext;
    
    if ( schecksynuse.mtype == 8 ) {
      while ( schecksynuse.mtype == 8 ) {
        schecksynuse = schecksynuse.mnext;
        if ( !Factor() ) return false ;
        else schecksynuse = schecksynuse.mnext;
      } // while
    
      return true;
    } // if
    else return true ;
  } // Term()
  
  
  static public boolean Factor() {
    System.out.println( "Factor" );
    if ( schecksynuse.mtype == 3 ) return true ;
    else if ( schecksynuse.mtype == 1 || schecksynuse.mtype == 2 ) return true; 
    else if ( schecksynuse.mtype == 6 ) {
      if ( ArithExpwithend() ) return true;
      else return false ;
    } // else if()
    else return false ;
  } // Factor()
  
  
  static public boolean Syntaxcheck() {
    System.out.println( "Syntaxcheck" );
    if ( schecksynuse == null ) return false;
    if ( Command() ) return true ;
    else return false ;
  } // Syntaxcheck()
  
  
  static public boolean IsStringInList( String searchStr ) {
    System.out.println( "IsStringInList" );
    for ( int i = 0; i < sgdefinename.size() ; i++ ) {
      Iddata pair = sgdefinename.get( i );
      if ( pair.GetStr().equals( searchStr ) ) {
        return true; // 找到匹配的字符串，返回 true
      } // if
    } // for
    
    return false; // 沒有找到匹配的項目，返回 false
  } // IsStringInList()
  
  
  static public boolean Checkidexist( ListNode head ) {
    System.out.println( "Checkidexist" );
    head = head.mnext ;
    while ( head != null ) {
      if ( !IsStringInList( head.mitem ) ) return false ;
      head = head.mnext ;
    } // while
    
    return true ;
  } // Checkidexist()
  
  
  static public void Handlegrammer() {
    // System.out.println( "pp0" );
    readline = 0 ;
    isfirst = true ;
    Readcommendandstore() ;
    // Output() ;
    // System.out.println( "pp1" );
    // test() ;
    
    // schecksynuse = scommandhead ;
    
    // if ( !Syntaxcheck() ) System.out.println( "123" );
    /*
    while( !Syntaxcheck( scommandhead ) ) {
      Readcommendandstore() ;
    }
    */
    // System.out.println( "pp2" );

    // Checkidexist( scommandhead ) ;
    
  } // Handlegrammer()
  

  
  // =======================================================================================================
  
  static public boolean Checkexist( String item ) {
    for ( int i = 0; i < sgdefinename.size() ; i++ ) {
      Iddata pair = sgdefinename.get( i );
      // System.out.println( pair.GetStr() ) ;
      if ( pair.GetStr().equals( item ) ) {
        return true;
      } // if
    } // for
    // 沒有找到匹配的字符串，返回 null 或拋出異常
    return false ; // 或者可以選擇拋出一個異常
  } // Checkexist()
  
  
  static public boolean Checkstrexist( String item ) {
    for ( int i = 0; i < sgdefinestring.size() ; i++ ) {
      Stringdata pair = sgdefinestring.get( i );
      // System.out.println( pair.GetStr() ) ;
      if ( pair.GetStr().equals( item ) ) {
        return true;
      } // if
    } // for
    // 沒有找到匹配的字符串，返回 null 或拋出異常
    return false ; // 或者可以選擇拋出一個異常
  } // Checkstrexist()
  

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
  
  
  static public void AddData( String str, float num, String type, int size ) {
    boolean hasbf = false;
    
    for ( int i = 0; i < sgdefinename.size() ; i++ ) {
      Iddata pair = sgdefinename.get( i );
      if ( pair.GetStr().equals( str ) ) {
        // System.out.println( pair.GetNum() ) ;
        pair.SetNum( num );
        hasbf = true ;
      } // if
    } // for
    
    
    if ( !hasbf ) {
      Iddata pair = new Iddata( str, num, type, size );
      // System.out.println( pair.GetNum() ) ;
      sgdefinename.add( pair );
    } // if
  } // AddData()
  
  
  static public void AddData( String str, String item ) {
    boolean hasbf = false;
    
    // System.out.println( "123" ) ;
    for ( int i = 0; i < sgdefinestring.size() ; i++ ) {
      Stringdata pair = sgdefinestring.get( i );
      if ( pair.GetStr().equals( str ) ) {
        // System.out.println( pair.GetNum() ) ;
        pair.Setitem( item );
        hasbf = true ;
      } // if
    } // for
    
    
    if ( !hasbf ) {
      Stringdata pair = new Stringdata( str, item );
      // System.out.println( pair.GetNum() ) ;
      sgdefinestring.add( pair );
    } // if
  } // AddData()
  
  
  static public void AddDatafunction( String str, String item ) { // 還沒好
    boolean hasbf = false;
    
    for ( int i = 0; i < sgdefinestring.size() ; i++ ) {
      Stringdata pair = sgdefinestring.get( i );
      if ( pair.GetStr().equals( str ) ) {
        // System.out.println( pair.GetNum() ) ;
        pair.Setitem( item );
        hasbf = true ;
      } // if
    } // for
    
    
    if ( !hasbf ) {
      Stringdata pair = new Stringdata( str, item );
      // System.out.println( pair.GetNum() ) ;
      sgdefinestring.add( pair );
    } // if
  } // AddData()
  
  
  static public String Findtype( String str ) {
    
    for ( int i = 0; i < sgdefinename.size() ; i++ ) {
      Iddata pair = sgdefinename.get( i );
      if ( pair.GetStr().equals( str ) ) {
        // System.out.println( pair.GetNum() ) ;
        return pair.Gettype();
      } // if
    } // for
    
    for ( int i = 0; i < sgdefinestring.size() ; i++ ) {
      Stringdata pair = sgdefinestring.get( i );
      if ( pair.GetStr().equals( str ) ) {
        // System.out.println( pair.GetNum() ) ;
        return "string" ; 
      } // if
    } // for
    
    
    System.out.println( "123" ) ;
    return "nofind" ;
  } // Findtype()
  
  
  
  static public String Getstring( String str ) {
    
    for ( int i = 0; i < sgdefinestring.size() ; i++ ) {
      Stringdata pair = sgdefinestring.get( i );
      if ( pair.GetStr().equals( str ) ) {
        // System.out.println( pair.GetNum() ) ;
        return pair.Getitem();
      } // if
    } // for
    
    return "nofind" ;
  } // Getstring()
  

  
  private static String Calculatestring( ListNode start, ListNode end ) { //pb
    String ans = "" ;
    
    for ( ListNode current = start; current != end ; current = current.mnext ) { 
      // System.out.println( current.mitem + current.mtype );
      if ( current.mtype == 10 ) ans = ans + current.mitem.substring( 1, current.mitem.length() ) ; 
      if ( current.mtype == 3 ) {
        if ( Checkstrexist( current.mitem ) || Checkexist( current.mitem ) ) {

          ans = ans + Getstring( current.mitem ) ;       // 可以拿array......
        } // if
        else {
          System.out.println( "> Line " + readline + " : undefined identifier : '" + 
                              current.mitem + "'" ) ;
          return "error" ;
        } // else
      } // if
       
        
    } // for
    
    return ans ;
  } // Calculatestring()

  
  private static float Calculateans( ListNode start, ListNode end ) { //
    
    // for ( ListNode current = start; current != end ; current = current.mnext ) 
    // System.out.println( current.mitem ) ;

    if ( start == end ) return 0 ;      
    
    MStack cal = new MStack();
    
    for ( ListNode current = start; current != end ; current = current.mnext ) { // 處理括號
      if ( current.mtype == 7 ) {
        MStack temp = new MStack();
        while ( cal.Size() != 0 && !cal.Peek().equals( "(" ) ) { 
          temp.Push( cal.Pop() );
        } // while
        
        cal.Pop();
        cal.Push( Calculate( temp ) );
      } // if
      else {
        if ( current.mtype == 3 ) { // error 添加
          if ( Checkexist( current.mitem ) )
            cal.Push( String.valueOf( Getnumber( current.mitem ) ) );
          else {
            System.out.println( "> Line " + readline + " : undefined identifier : '" + 
                current.mitem + "'" ) ;
            return -999 ;
          } // else
        } // if
        else cal.Push( current.mitem ); 
      } // else
    } // for
      
    if ( cal.Size() == 1 ) { // 计算完括号里面的，如果栈中只剩下一个元素
      return Float.parseFloat( cal.Pop() );
    } // if
    else { // 计算完括号里面的，栈中还有多个元素，继续计算
    // 但是此时栈中的元素是正序排列的，需要变成倒序再计算
      MStack last = new MStack();
      while ( !cal.IsEmpty() ) {
        last.Push( cal.Pop() );
      } // while
      
      // System.out.print( "> " );
      
      String checktype = Calculate( last ) ;

      
      if ( checktype.equals( "true" ) || checktype.equals( "false" ) ) {
        System.out.println( checktype );
      } // if
      else {
        float number = Float.parseFloat( checktype );
      
        if ( number == ( int ) number ) {
          int i = ( int ) number ;
          return i; // 如果是整數，轉型為int並印出
        } // if
        else {
          return number ;
        } // else
      } // else
    } // else
    
    return 0 ;
  } // Calculateans()

  
  
  private static String Calculate( MStack commandstack ) { // 計算四則運算
    boolean bigsmall = false ;
    float prenum = 0 ;
    String op = "" ;
    ArrayList<String> arrayList = new ArrayList<String>();
    // 先算乘除法
    while ( !commandstack.IsEmpty() ) {
      if ( commandstack.Peek().equals( "*" ) || commandstack.Peek().equals( "/" ) ) {
        String operator = commandstack.Pop();
        String number = commandstack.Pop();
        String last = arrayList.remove( arrayList.size()-1 );
        if ( operator.equals( "*" ) )
          arrayList.add( String.valueOf( Float.parseFloat( last ) * Float.parseFloat( number ) ) );
        else 
          arrayList.add( String.format( "%.12f", Float.parseFloat( last ) / Float.parseFloat( number ) ) );
      } // if
      else arrayList.add( commandstack.Pop() );
    } // while 
    
    /*
    for (int i = 0; i < arrayList.size(); i++) {
      System.out.println("Element at index " + i + ": " + arrayList.get(i));
    }
    */
    
    // 再算加减法
    float result = Float.parseFloat( arrayList.remove( 0 ) );
    for ( int i = 0 ; i < arrayList.size() ; i++ ) {
      String next = arrayList.get( i ) ;
      if ( next == ">" || next == ">=" || next == "<" || next == "<=" || next == "=" ) {
        i++; 
        bigsmall = true ;
        prenum = result ;
        result = Float.parseFloat( arrayList.get( i ) ) ;
        op = next ;
      } // if
      else if ( next.equals( "+" ) ) {
        i++; 
        result += Float.parseFloat( arrayList.get( i ) );
      } // if
      else if ( next.equals( "-" ) ) {
        i++;
        result -= Float.parseFloat( arrayList.get( i ) );
      } // if
      else result += Float.parseFloat( arrayList.get( i ) );
    } // for
    
    
     // System.out.println(prenum);
     // System.out.println(result);

    return Float.toString( result );
    
  } // Calculate()
  
  
  
  public static boolean Dealwithprocess( ListNode start, ListNode end, boolean notininquotation ) {
    ListNode temp ;
    boolean checkhasext = false ;
    String tempstr ;
    float tempfloat = 0 ;
    // for ( ListNode tmp = start; tmp != end ; tmp = tmp.mnext ) 
      // System.out.println( tmp.mitem ) ;
    
    // System.out.println( " " ) ;
    
    
    ListNode startcopy = start ;
    
    if ( start.mitem.equals( ";" ) ) {
      if ( notininquotation ) System.out.println( "> Statement executed ..." ) ;
      return true ;
    } // if
    
    if ( startcopy.mitem.equals( "Done" ) ) {
      if ( startcopy.mnext != null ) startcopy = startcopy.mnext ;
      if ( startcopy.mitem.equals( "(" ) ) {
        if ( startcopy.mnext != null ) startcopy = startcopy.mnext ;
        if ( startcopy.mitem.equals( ")" ) ) return false ; // 遇到"Done()"时，返回特殊值
      } // if
    } // if
    // System.out.println( "123" ) ;
    
    boolean finish = false ;
    for ( ListNode current = start; current != null && current != end && !finish ; 
          current = current.mnext ) {
      // System.out.println( current.mitem ) ;
      // System.out.println( end.mitem + "000" ) ;
      
      
      if ( current.mitem.equals( "{" ) ) { // many command
        current = current.mnext ;
        temp = current ;
        while ( current != null && current != end ) {
          while ( !temp.mitem.equals( ";" ) ) temp = temp.mnext ;
          Dealwithprocess( current, temp, false ) ;
          // System.out.println( " " ) ;
          current = temp.mnext ;
          temp = temp.mnext ;
        } // while
        
        if ( notininquotation ) System.out.println( "> Statement executed ..." ) ;
      } // if
      else if ( current.mitem.equals( "int" ) ) {
        current = current.mnext ;
        if ( current.mtype == 3 ) {
          checkhasext = Checkexist( current.mitem ) ;
          if ( notininquotation ) {
            if ( current.mnext != end && current.mnext.mtype == 11 ) {
              AddData( current.mitem, 0, "intarray",
                  Integer.parseInt( current.mnext.mitem.substring
                  ( 1, current.mnext.mitem.length() ) ) ) ;
            } // if
            else AddData( current.mitem, Calculateans( current.mnext, end ), "int", 1 ) ;
          } // if
          
          if ( notininquotation && !checkhasext ) 
            System.out.println( "> Definition of " + current.mitem + " entered ..." );
          if ( notininquotation && checkhasext ) 
            System.out.println( "> New definition of " + current.mitem + " entered ..." );
          
        } // if
        else System.out.println( "> error" );  
      } // else if
      else if ( current.mitem.equals( "char" ) ) {
        current = current.mnext ;
        if ( current.mtype == 3 ) {
          checkhasext = Checkexist( current.mitem ) ;
          
          if ( notininquotation ) {
            if ( current.mnext != end && current.mnext.mtype == 11 ) {
              AddData( current.mitem, 0, "chararray",
                       Integer.parseInt( current.mnext.mitem.substring
                       ( 1, current.mnext.mitem.length() ) ) ) ;
            } // if
            else AddData( current.mitem, Calculateans( current.mnext, end ), "char", 1 ) ;
          } // if
          
          if ( notininquotation && !checkhasext ) 
            System.out.println( "> Definition of " + current.mitem + " entered ..." );
          if ( notininquotation && checkhasext ) 
            System.out.println( "> New definition of " + current.mitem + " entered ..." );
        } // if
        else System.out.println( "> error" );  
      } // else if
      else if ( current.mitem.equals( "float" ) ) {
        current = current.mnext ;
        if ( current.mtype == 3 ) {
          checkhasext = Checkexist( current.mitem ) ;
          if ( notininquotation ) {
            if ( current.mnext != end && current.mnext.mtype == 11 ) {
              AddData( current.mitem, 0, "floatarray",
                  Integer.parseInt( current.mnext.mitem.substring
                  ( 1, current.mnext.mitem.length() ) ) ) ;
            } // if
            else AddData( current.mitem, Calculateans( current.mnext, end ), "float", 1 ) ;
          } // if
          
          if ( notininquotation && !checkhasext ) 
            System.out.println( "> Definition of " + current.mitem + " entered ..." );
          if ( notininquotation && checkhasext ) 
            System.out.println( "> New definition of " + current.mitem + " entered ..." );
        } // if
        else System.out.println( "> error" );  
      } // else if
      else if ( current.mitem.equals( "string" ) ) { 
        current = current.mnext ;
        if ( current.mtype == 3 ) {
          checkhasext = Checkstrexist( current.mitem ) ;
          if ( notininquotation )
            // System.out.println(Calculatestring( current.mnext, end ) ) ;
            AddData( current.mitem, Calculatestring( current.mnext, end ) ) ; 
          // System.out.println( Getstring( current.mitem ) + "123" ) ;
          if ( notininquotation && !checkhasext ) 
            System.out.println( "> Definition of " + current.mitem + " entered ..." );
          if ( notininquotation && checkhasext ) 
            System.out.println( "> New definition of " + current.mitem + " entered ..." );
        } // if
        else System.out.println( "> error" );  
      } // else if
      else if ( current.mitem.equals( "cout" ) ) {
        current = current.mnext ;
        checkhasext = true ;
        
        while ( current != end ) {
          if ( !current.mitem.equals( "<<" ) ) {
            if ( !Checkexist( current.mitem ) && !Checkstrexist( current.mitem ) ) {
              System.out.println( "> Line " + readline + " : undefined identifier : '" + 
                                  current.mitem + "'" ) ;
              checkhasext = false ;
            } // if
          } // if
          current = current.mnext ;  
        } // while
        
        
        finish = true ;
        
        if ( notininquotation && checkhasext ) 
          System.out.println( "> Statement executed ..." ) ;
        
      } // else if
      else if ( current.mitem.equals( "cin" ) ) {
        current = current.mnext ;
        checkhasext = true ;
        
        while ( current != end ) {
          if ( !current.mitem.equals( ">>" ) ) {
            if ( !Checkexist( current.mitem ) && !Checkstrexist( current.mitem ) ) {
              System.out.println( "> Line " + readline + " : undefined identifier : '" + 
                                  current.mitem + "'" ) ;
              checkhasext = false ;
            } // if
          } // if
          current = current.mnext ;  
        } // while
        
        
        finish = true ;
        
        if ( notininquotation && checkhasext ) 
          System.out.println( "> Statement executed ..." ) ;
        
      } // else if
      else if ( current.mtype == 1 || current.mtype == 2 ) {


        // AddData( current.mitem, Calculateans( current.mnext, end ), "int" ) ; 
        finish = true ;

        
        if ( notininquotation ) 
          System.out.println( "> Statement executed ..." ) ;
        
      } // else if
      else if ( current.mtype == 3 ) { // 
        if ( !Checkexist( current.mitem ) && !Checkstrexist( current.mitem ) ) {
          System.out.println( "> Line " + readline + " : undefined identifier : '" + current.mitem + "'" ) ;
          finish = true ;
          readline = 0 ;
        } // if
        else {  
          temp = current ;
          current = current.mnext ;
          if ( current.mitem.equals( "=" ) ) {
            // System.out.println( Findtype( temp.mitem ) ) ;
            if ( Findtype( temp.mitem ) == "string" ) {
                tempstr = Calculatestring( current.mnext, end ) ;
                if ( !tempstr.equals( "error" ) ) 
                  AddData( temp.mitem, tempstr ) ; 
                // System.out.println( tempstr ) ;
            } // if
            else if ( Findtype( temp.mitem ) == "int" || Findtype( temp.mitem ) == "float" ) {
              tempfloat = Calculateans( current.mnext, end ) ;
              if ( tempfloat != -999 ) AddData( temp.mitem, tempfloat, "int", 0 ) ; 
            } // else if
            finish = true;
          } // if
          
          if ( notininquotation ) 
            System.out.println( "> Statement executed ..." ) ;
        } // else
      } // else if
      // System.out.println( "0000" ) ;
    } // for
    
    // System.out.println( "0000" ) ;
    return true ;
  } // Dealwithprocess()
  
  
  
  
  
  public static void main( String[] args ) {
    System.out.println( "Our-C running ..." );
  
    int testnum = scanner.nextInt();
    scanner.nextLine();

    if ( testnum != 0 ) {
      Handlegrammer() ;
      ListNode end = scommandhead ;
      while ( end.mnext != null ) end = end.mnext ;
      while ( Dealwithprocess( scommandhead, end, true ) ) {
        scommandhead = null ;
        Handlegrammer() ;
        end = scommandhead ;
        while ( end.mnext != null ) end = end.mnext ;
      } // while
    } // if
    
    scanner.close();  
    System.out.println( "> Our-C exited ..." );
  } // main()
} // class Main()
