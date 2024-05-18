package PL112_10920131;

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
  String mitem ;
  int msize ;
  boolean minfunction ;

  // 構造函數
  public Iddata( String str, float num, String type, int size, String item, boolean infunction ) {
    this.mstr = str;
    this.mnum = num;
    this.mtype = type ;
    this.msize = size ;
    this.mitem = item ;
    this.minfunction = infunction;
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
  
  public int Getsize() {
    return msize;
    
  } // Getsize() 
  
  public boolean Getinfunction() {
    return minfunction;
    
  } // Getinfunction() 
  
  public void SetNum( float num ) {
    this.mnum = num;
  } // SetNum()

  public void SetType( String type ) {
    this.mtype = type;
  } // SetType()

  public String Getitem() {
    return mitem;
    
  } // Getitem()
  
  public void Setitem( String item ) {
    this.mitem = item;
    
  } // Setitem()
  
} // class Iddata()



  
class Functiondata {
  String mstr;
  String mtype ;
  ArrayList<String> minput ;
  ArrayList<String> mitem ;
  
  // 構造函數
  public Functiondata( String str, String type ) {
    this.mstr = str ;
    this.mtype = type ;
    this.minput = new ArrayList<String>();
    this.mitem = new ArrayList<String>();
  } // Functiondata()
  
  public void Addinput( String item ) {
    minput.add( item );
  } // Addinput()
  
  public void Additem( String item ) {
    mitem.add( item );
  } // Additem()
  
  public ArrayList<String> Getinput() {
    return minput;
  } // Getinput()
  
  public ArrayList<String> Getitem() {
    return mitem;
  } // Getitem()
  
  public String GetStr() {
    return mstr;
  } // GetStr()
  
  public String Gettype() {
    return mtype;
  } // Gettype()
  
} // class Functiondata()


class Main { // 注意類別名稱需要跟.java檔名相同
  
  static public ArrayList<Iddata> sgdefinename = new ArrayList<Iddata>();
  
  static public ArrayList<Functiondata> sgdefinefunction = new ArrayList<Functiondata>();

  static public ListNode scommandhead ; 
  
  static public ListNode checkhead  ; // 檢查文法

  static public int sreadline = 0 ; // wrong line
  
  static public boolean[] spreif = new boolean[5]; // if 是否是對的
  
  static public int sifhasprint = 0 ; // if 有印>
  
  static public boolean sisfirst = true ; // 第一個token
  
  static public boolean sissetcommand = false ;  // 是否為定義句
  
  static public int sreadcomma = 0 ; // 檢查括號
  
  static public int sreadbigcomma = 0 ; // 檢查大括號
  
  static public boolean sinquotation = false ; // 大括號內
  
  static public int slayer = 0 ; // 第幾層if
  
  static public boolean spreiscin = false ; // error of op
  static public boolean specialcheck = false ; // error of op
  
  static public boolean sisfirstofstate = true ; // 
  
  static public ListNode spredo ;
  
  static public ListNode spredoend ;
  
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
  
  static public boolean CheckisConstant( String token ) {
    if ( Checkisint( token ) ) return true ;
    if ( Checkisfloat( token ) ) return true ;
    if ( token.charAt( 0 ) == '"' && token.charAt( token.length() - 1 ) == '"' ) return true ;
    if ( token.charAt( 0 ) == '\'' && token.charAt( token.length() - 1 ) == '\'' ) return true ;
    if ( token.equals( "true" ) ) ;
    if ( token.equals( "false" ) ) ;
    
    return false ;
  } // CheckisConstant()
  
 
  static public boolean Checkisoktoken( char c ) {  // 
    if ( c == '(' || c == ')' || c == '[' || c == ']' || c == '{' || c == '}' ) return true ; 
    if ( c == '+' || c == '-' || c == '*' || c == '/' || c == '%' || c == '^' ) return true ; 
    if ( c == '>' || c == '<' || c == '&' || c == '|' || c == '=' || c == '!' ) return true ; 
    if ( c == ';' || c == ',' || c == '?' || c == ':' || c == ' ' || c == '.' ) return true ; 
    if ( c == '"' || c == '\'' ) return true ; 

    if ( IsLetterDigit( c ) ) return true ;
    
    return false ;
  } // Checkisoktoken()
  
  
  static public boolean CheckisIdentifier( String token ) {  // 檢查英文
      // 正则表达式匹配字符串仅包含英文大小写字母
    return token.matches( "[a-zA-Z][a-zA-Z0-9]*" );
  } // Checkisallenglish()
  
  
  public static boolean Isallnum( String str ) {
    return str.matches( "\\d+" );  // "\\d+"匹配一個或多個數字
  } // Isallnum()
  
  
  public static boolean IsLetterDigit( char c ) {
    return Character.isLetter( c ) || Character.isDigit( c );
  } // IsLetterDigit()
  // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
  // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

  static public boolean Addtovector( String item ) {
    int type = 0 ;
       
    if ( item.equals( "int" ) ) type = 3  ;   
    else if ( item.equals( "float" ) ) type = 4 ;
    else if ( item.equals( "char" ) ) type = 5 ;
    else if ( item.equals( "bool" ) ) type = 6 ;    
    else if ( item.equals( "string" ) ) type = 7 ;    
    else if ( item.equals( "void" ) ) type = 8 ;    
    else if ( item.equals( "if" ) ) type = 9 ;    
    else if ( item.equals( "else" ) ) type = 10 ;
    else if ( item.equals( "while" ) ) type = 11 ;
    else if ( item.equals( "do" ) ) type = 12 ;
    else if ( item.equals( "return" ) ) type = 13 ;    
    else if ( CheckisIdentifier( item ) ) type = 1 ;
    else if ( CheckisConstant( item ) ) type = 2  ; 
    else if ( item.equals( "(" ) ) type = 14 ;
    else if ( item.equals( ")" ) ) type = 15 ;
    else if ( item.equals( "[" ) ) type = 16 ;
    else if ( item.equals( "]" ) ) type = 17 ;
    else if ( item.equals( "{" ) ) type = 18 ;
    else if ( item.equals( "}" ) ) type = 19 ;
    else if ( item.equals( "+" ) ) type = 20 ;
    else if ( item.equals( "-" ) ) type = 21 ;
    else if ( item.equals( "*" ) ) type = 22 ;
    else if ( item.equals( "/" ) ) type = 23 ;
    else if ( item.equals( "%" ) ) type = 24 ;
    else if ( item.equals( "^" ) ) type = 25 ;
    else if ( item.equals( ">" ) ) type = 26 ;
    else if ( item.equals( "<" ) ) type = 27 ;
    else if ( item.equals( ">=" ) ) type = 28 ;
    else if ( item.equals( "<=" ) ) type = 29 ;
    else if ( item.equals( "==" ) ) type = 30 ;
    else if ( item.equals( "!=" ) ) type = 31 ;
    else if ( item.equals( "&" ) ) type = 32 ;
    else if ( item.equals( "|" ) ) type = 33 ;
    else if ( item.equals( "=" ) ) type = 34 ;
    else if ( item.equals( "!" ) ) type = 35 ;
    else if ( item.equals( "&&" ) ) type = 36 ;
    else if ( item.equals( "||" ) ) type = 37 ;
    else if ( item.equals( "+=" ) ) type = 38 ;
    else if ( item.equals( "-=" ) ) type = 39 ;
    else if ( item.equals( "*=" ) ) type = 40 ;
    else if ( item.equals( "/=" ) ) type = 41 ;
    else if ( item.equals( "%=" ) ) type = 42 ;
    else if ( item.equals( "++" ) ) type = 43 ;
    else if ( item.equals( "--" ) ) type = 44 ;
    else if ( item.equals( ">>" ) ) type = 45 ;
    else if ( item.equals( "<<" ) ) type = 46 ;
    else if ( item.equals( ";" ) ) type = 47 ;
    else if ( item.equals( "," ) ) type = 48 ;
    else if ( item.equals( "?" ) ) type = 49 ;
    else if ( item.equals( ":" ) ) type = 50 ;
    else {
      System.out.println( "> Line " + sreadline + " : undefined identifier : '" + item + "'" ) ;
      return false ;
    } // else 
    
    // System.out.println( item );
    
    if ( spreiscin ) {
      if (  type == 12 ) spreiscin = false ;
      else {
        if ( !specialcheck && !item.equals( ">>" ) && !item.equals( "<<" ) && type != 11 ) {
          System.out.println( "> Line " + sreadline + " : unexpected token : '" + item + "'" ) ;
          return false ;
        } // if
        
        if ( item.equals( ">>" ) || item.equals( "<<" ) ) specialcheck = true ;
        else specialcheck = false ;
      } // else 
    } // if
      
    
    if ( item.equals( "else" ) && sifhasprint == 0 ) {
      System.out.println( "> Line " + sreadline + " : unexpected token : 'else'" ) ;
      return false ;
    } // if
    
        
    if ( !sisfirst && type == 3 && !sissetcommand ) {
      if ( !Checkexist( item ) && !Checkfunctionexist( item ) && !Checkspecialword( item ) ) {
        System.out.println( "> Line " + sreadline + " : undefined identifier : '" + item + "'" ) ;
        
        Cleanall() ;
        Cleaninfunction() ;
        return false;
      } // if
    } // if
    else if ( sisfirst && type == 3 ) {
      if ( !item.equals( "else" ) && sisfirstofstate ) {
        if ( sifhasprint > 0 ) sifhasprint-- ;
        if ( slayer > 0 ) slayer-- ;
      } // if
      
      // System.out.println( " 123" ) ;
      if ( item.equals( "cin" ) || item.equals( "cout" ) ) spreiscin = true ;
      sisfirst = false ;
      sisfirstofstate = false ;
      if ( !Checkspecialword( item ) ) {
        if ( !Checkexist( item ) && !Checkfunctionexist( item ) ) {
          System.out.println( "> Line " + sreadline + " : undefined identifier : '" + item + "'" ) ;
          return false;
        } // if
      } // if
      else if ( Iscommmandfirst( item ) )  sissetcommand = true ;
    } // else if
    
    if ( item.equals( "else" )  ) sifhasprint--;
    if ( item.equals( "if" )  ) sifhasprint++;
    
    
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
    
    System.out.println( item + type ) ;
    
    checkhead = scommandhead ;
    if ( !checkgrammer() ) {
      System.out.println( "> Line " + sreadline + " : undefined identifier : '" + item + "'" ) ;
      return false ;
    } // if
    
    if ( Checkspecialword( item ) ) spredo = newNode ;
    if ( type == 12 || type == 7 ) spredoend = newNode ;
    return true ;
  } // Addtovector()
  
  
  static public boolean Checkspecialword( String item ) {
    if ( item.equals( "int" ) ) return true ;
    if ( item.equals( "char" ) ) return true ;
    if ( item.equals( "float" ) ) return true ;
    if ( item.equals( "cin" ) ) return true ;
    if ( item.equals( "cout" ) ) return true ;
    if ( item.equals( "string" ) ) return true ;
    if ( item.equals( "void" ) ) return true ;
    if ( item.equals( "Done" ) ) return true ;
    if ( item.equals( "if" ) ) return true ;
    if ( item.equals( "else" ) ) return true ;
    if ( item.equals( "while" ) ) return true ;
    if ( item.equals( "return" ) ) return true ;

    
    return false ;
  } // Checkspecialword()
  
  
  static public boolean Iscommmandfirst( String item ) {
    if ( item.equals( "int" ) ) return true ;
    if ( item.equals( "char" ) ) return true ;
    if ( item.equals( "float" ) ) return true ;
    if ( item.equals( "string" ) ) return true ;
    if ( item.equals( "void" ) ) return true ;

    return false ;
  } // Iscommmandfirst()
  
  
  static public void Cleanall() {

    Cleaninfunction() ;
    scommandhead = null ;
    sreadline = 0 ;
    sisfirst = true ; 
    sissetcommand = false ;
    sreadcomma = 0 ;
    sreadbigcomma = 0 ;
    sinquotation = false ;
    sifhasprint = 0 ;
    sisfirstofstate = true ;
    spreiscin = false ;
    
    
  } // Cleanall()
  
  
  static public void Cleanhalf() {
    scommandhead = null ;
    sisfirst = true ; 
    sissetcommand = false ;
    sreadcomma = 0 ;
    sreadbigcomma = 0 ;
    sinquotation = false ;
    sisfirstofstate = false ;
    spreiscin = false ;
    // Cleandepend() ;

  } // Cleanhalf()
  
  static public String slineleft = "" ;
  
  static public void Readcommendandstore() {
    boolean hasend = false ;
    boolean noerror = true ; 
    
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
        sreadline++ ;
      } // if  
    } // else
    
    
    while ( !hasend || sinquotation ) { // 主要
      // System.out.println(line);
      
      for ( int i = 0; noerror && ( i < line.length() && !hasend || i < line.length() && sinquotation ) ; 
            i++ ) {
        char temp = line.charAt( i ); // 获取位置i的字符
        

        
        if ( !Checkisoktoken( temp ) ) {
          System.out.println( "> Line " + sreadline + " : unexpected token : '" + temp + "'" ) ;
          noerror = false ;
        } // if 
        else if ( temp == ' ' ) {
          if ( save != "" ) { 
            noerror = Addtovector( save ) ;
            // System.out.println(noerror);
            save = "" ;
          } // if
        } // else if
        else if ( temp == ';' ) {
          if ( save != "" ) { 
            noerror = Addtovector( save ) ;
            save = "" ;
          } // if
          
          
          if ( i != line.length()-1 ) slineleft = line.substring( i+1, line.length() );
          // System.out.println( slineleft );
          if ( noerror ) noerror = Addtovector( ";" ) ;
          if ( sinquotation && sissetcommand && noerror )  // 括號中預先宣告  防undefine ;
            justpass( spredo, spredoend, false, false ) ;

          
          if ( noerror ) hasend = true ;
          sisfirst = true ;
          sisfirstofstate = true ;
          sissetcommand = false;
        } // else if
        else if ( temp == ':' ) { // 宣告
          if ( i+1 < line.length() && line.charAt( i+1 ) == '=' ) {
            i++;
            noerror = Addtovector( ":=" ) ;
          } // if
          else {
            if ( save != "" ) { 
              noerror = Addtovector( save ) ;
              save = "" ;
            } // if
            
            if ( noerror ) noerror = Addtovector( ":" ) ;
          } // else
        } // else if
        else if ( temp == '\"' ) {
          if ( save != "" ) { 
            noerror = Addtovector( save ) ;
            save = "" ;
          } // if
          
          int firstchar = i ;
          i++;
          char findend = line.charAt( i );
          while ( findend != '\"' ) {
            i++ ;
            findend = line.charAt( i );
          } // while

          if ( noerror ) noerror = Addtovector( line.substring( firstchar, i ) ) ;
        } // else if
        else if ( temp == '[' ) {
          if ( save != "" ) { 
            noerror = Addtovector( save ) ;
            save = "" ;
          } // if
          
          if ( noerror ) noerror = Addtovector( "[" ) ;
          
          int firstchar = i ;
          i++;
          char findend = line.charAt( i );
          while ( findend != ']' ) {
            i++ ;
            findend = line.charAt( i );
          } // while

          if ( noerror ) noerror = Addtovector( line.substring( firstchar+1, i ) ) ;
          if ( noerror ) noerror = Addtovector( "]" ) ;
        } // else if
        else if ( temp == '=' ) {
          if ( save != "" ) { 
            noerror = Addtovector( save ) ;
            save = "" ;
          } // if
          
          if ( noerror && i+1 < line.length() && line.charAt( i+1 ) == '='  ) 
            noerror = Addtovector( "==" ) ;
          else if ( noerror ) noerror = Addtovector( "=" ) ;
        } // else if
        else if ( temp == '+' ) {
          if ( save != "" ) { 
            noerror = Addtovector( save ) ;
            save = "" ;
          } // if
          
          if ( noerror && i+1 < line.length() && line.charAt( i+1 ) == '+' ) {
            i++;
            noerror = Addtovector( "++" ) ;
          } // if
          else if ( noerror ) noerror = Addtovector( "+" ) ;
        } // else if
        else if ( temp == '-' ) {
          if ( save != "" ) { 
            noerror = Addtovector( save ) ;
            save = "" ;
          } // if
          
          if ( i+1 < line.length() && line.charAt( i+1 ) == ' ' ) noerror = Addtovector( "-" ) ;
          else if ( i+1 < line.length() && line.charAt( i+1 ) == '-' ) {
            i++;
            noerror = Addtovector( "--" ) ;
          } // if
          else if ( i+1 < line.length() && !Character.isDigit( line.charAt( i+1 ) ) ) {
            if ( noerror ) noerror = Addtovector( "-" ) ;
          } // else if
          else save = "-" ;
        } // else if
        else if ( temp == '*' ) {
          if ( save != "" ) { 
            noerror = Addtovector( save ) ;
            save = "" ;
          } // if
          
          if ( noerror ) noerror = Addtovector( "*" ) ;

        } // else if
        else if ( temp == '/' ) {
          if ( i+1 < line.length() && ( line.charAt( i+1 ) == '/' ) ) {
            
            line = scanner.nextLine();
            sreadline++ ;
            while ( line.trim().isEmpty() ) {
              line = scanner.nextLine(); 
              sreadline++;
            } // while
            
            i = -1 ;
            
          } // if
          else {
            if ( save != "" ) { 
              noerror = Addtovector( save ) ;
              save = "" ;
            } // if
            
            if ( noerror ) noerror = Addtovector( "/" ) ;

          } // else 
        } // else if
        else if ( temp == ')' ) {
          if ( save != "" ) { 
            noerror = Addtovector( save ) ;
            save = "" ;
          } // if
          
          sreadcomma--;
          if ( sreadcomma < 0 ) {
            System.out.println( "> Line " + sreadline + " : unexpected token : ')'" ) ;
            
            noerror = false ;
            sreadcomma = 0 ;
          } // if
          
          if ( noerror ) noerror = Addtovector( ")" ) ;
          if ( sissetcommand && noerror )  // 括號中預先宣告  防undefine ;
            Dealwithprocess( spredo, spredoend, false, false ) ;
        } // else if
        else if ( temp == '(' ) {
          if ( save != "" ) { 
            noerror = Addtovector( save ) ;
            save = "" ;
          } // if
          
          sreadcomma++;
          if ( noerror ) noerror = Addtovector( "(" ) ;
          sissetcommand = false ;
          sisfirst = true ;
        } // else if
        else if ( temp == '{' ) {
          if ( save != "" ) { 
            noerror = Addtovector( save ) ;
            save = "" ;
          } // if
          // System.out.println( "123" ) ;
          
          if ( noerror ) noerror = Addtovector( "{" ) ;
          sreadbigcomma++;
          sinquotation = true ;
          sissetcommand = false;
          sisfirst = true ;
          sisfirstofstate = true ;
        } // else if
        else if ( temp == '}' ) {
          sreadbigcomma--;
          if ( sreadbigcomma < 0 ) {
            System.out.println( "> Line " + sreadline + " : unexpected token : '}'" ) ;
            noerror = false ;
          } // if
          else {
            if ( save != "" ) { 
              noerror = Addtovector( save ) ;
              save = "" ;
            } // if
            
            if ( i != line.length()-1 ) slineleft = line.substring( i+1, line.length() );
            if ( sreadbigcomma == 0 ) hasend = true ;
            if ( sreadbigcomma == 0 ) sinquotation = false ;
            if ( noerror ) noerror = Addtovector( "}" ) ;
          } // else
        } // else if
        else if ( temp == '>' ) { // op
          if ( i+1 < line.length() && ( line.charAt( i+1 ) == '>' ) ) {
            if ( save != "" ) { 
              noerror = Addtovector( save ) ;
              save = "" ;
            } // if
            
            i++ ;
            noerror = Addtovector( ">>" ) ;
          } // if
          else if ( i+1 < line.length() && ( line.charAt( i+1 ) == '=' ) ) {
            if ( save != "" ) { 
              noerror = Addtovector( save ) ;
              save = "" ;
            } // if
            
            i++ ;
            noerror = Addtovector( ">=" ) ;
          } // else if
          else {
            if ( save != "" ) { 
              noerror = Addtovector( save ) ;
              save = "" ;
            } // if
            
            if ( noerror ) noerror = Addtovector( ">" ) ;
          } // else
        } // else if
        else if ( temp == '<' ) { // op
          if ( i+1 < line.length() &&  line.charAt( i+1 ) == '=' ) {
            if ( save != "" ) { 
              noerror = Addtovector( save ) ;
              save = "" ;
            } // if
            
            noerror = Addtovector( "<=" ) ;
            i++ ;
          } // if
          else if ( i+1 < line.length() &&  line.charAt( i+1 ) == '<' ) {
            if ( save != "" ) { 
              noerror = Addtovector( save ) ;
              save = "" ;
            } // if
            
            noerror = Addtovector( "<<" ) ;
            i++ ;
          } // else if
          else {
            if ( save != "" ) { 
              noerror = Addtovector( save ) ;
              save = "" ;
            } // if
            
            if ( noerror ) noerror = Addtovector( "<" ) ;
          } // else
        } // else if
        else if ( temp == '&' ) {
          if ( i+1 < line.length() &&  line.charAt( i+1 ) == '&' ) {
            if ( save != "" ) { 
              noerror = Addtovector( save ) ;
              save = "" ;
            } // if
            
            noerror = Addtovector( "&&" ) ;
            i++ ;      
          } // if
          else {
            if ( save != "" ) { 
              noerror = Addtovector( save ) ;
              save = "" ;
            } // if
            
            if ( noerror ) noerror = Addtovector( "&" ) ;
          } // else
        } // else if 
        else if ( temp == '|' ) {
          if ( i+1 < line.length() &&  line.charAt( i+1 ) == '|' ) {
            if ( save != "" ) { 
              noerror = Addtovector( save ) ;
              save = "" ;
            } // if
            
            noerror = Addtovector( "||" ) ;
            i++ ;      
          } // if
          else {
            if ( save != "" ) { 
              noerror = Addtovector( save ) ;
              save = "" ;
            } // if
            
            if ( noerror ) noerror = Addtovector( "|" ) ;
          } // else
        } // else if 
        else if ( temp == ',' ) {
          if ( save != "" ) { 
            noerror = Addtovector( save ) ;
            save = "" ;
          } // if
          
          if ( noerror ) noerror = Addtovector( "," ) ;
        } // else if
        else {
          if ( IsLetterDigit( temp ) ) save = save + line.charAt( i ) ;
        } // else
        
        
        if ( noerror && hasend && !sinquotation )  { 
          if ( i != line.length()-1 ) slineleft = line.substring( i+1, line.length() );
        } // if
        
      } // for
      
      
      
      // System.out.println( word );
      // System.out.println( save );
      
      if ( noerror && save != "" ) {
        // System.out.println( save );
        noerror = Addtovector( save ) ;
        save = "" ;
      } // if
      
      if ( !noerror ) {
        
        Cleanall() ;
        hasend = false ;
        noerror = true ;
        Cleaninfunction() ;
        
      } // if
      
      // System.out.println( hasend ) ;
      // System.out.println( sinquotation ) ;

      
      
      if ( !hasend && !slineleft.trim().isEmpty() || sinquotation && !slineleft.trim().isEmpty() ) {
        line = slineleft;
        slineleft = "" ;
        
      } // if
      else if ( !hasend && scanner.hasNext() || sinquotation && scanner.hasNext() ) {
        line = scanner.nextLine();
        sreadline++;
        while ( line.trim().isEmpty() ) {
          line = scanner.nextLine(); 
          sreadline++;
        } // while
        // System.out.println( line );
      } // if
      
    } // while
    
    // for ( ListNode tmp = scommandhead; tmp != null ; tmp = tmp.mnext ) 
     // System.out.println( tmp.mitem ) ;
    
    // System.out.println( "finishread" );
  } // Readcommendandstore()
  
  
  // ======================================================================================================

  static public boolean checkgrammer() { // ok
    return userInput();
  } // parse()

  static public boolean userInput() { // ok
    System.out.println( "userInput" ) ;
    if ( definitionOrStatement() ) {
      if ( checkhead.mnext == null ) return true ;
      else {
        checkhead = checkhead.mnext ;
        while ( checkhead.mnext != null ) {
          if ( !definitionOrStatement() ) return false ;
        } // while 
        return true ;
      } // else 
    } // if
    else return false;  
  } // userInput()

  static public boolean definitionOrStatement() {
    System.out.println( "definitionOrStatement" ) ;
    ListNode temp = checkhead ; // 進來的位置
    if ( definition() ) {
      if ( checkhead.mnext == null ) return true ;
      else checkhead = checkhead.mnext ;
      
      if ( definitionOrStatement() ) return true ;
      else return false  ;
    } // if
    else checkhead = temp ;
      
    if ( statement() ) {
      if ( checkhead.mnext == null ) return true ;
      else checkhead = checkhead.mnext ;
      
      if ( definitionOrStatement() ) return true ;
      else return false ;
    } // if
    return false ;
  } // definitionOrStatement()

  static public boolean definition() {
    System.out.println( "definition" ) ;
    if ( checkhead == null ) return true ;
    if ( checkhead.mtype == 8 ) {
      if ( checkhead.mnext == null ) return true ;
      else checkhead = checkhead.mnext ;
      
      if ( checkhead.mtype == 1 ) {
        if ( checkhead.mnext == null ) return true ;
        else checkhead = checkhead.mnext ;
        
        if ( functionDefinitionWithoutID() ) return true ;
        else return false ; 
      } // if
      else return false ;
    } // if 
    else if ( typeSpecifier() ) {
      if ( checkhead.mnext == null ) return true ;
      else checkhead = checkhead.mnext ;
      
      if ( checkhead.mtype == 1 ) {
        if ( checkhead.mnext == null ) return true ;
        else checkhead = checkhead.mnext ;
        
        if ( functionDefinitionOrDeclarators() ) return true ;
        else return false ; 
      } // if
      else return false ;
    } // else if
    
    return false;
  } // definition()

  static public boolean typeSpecifier() {
    System.out.println( "typeSpecifier" ) ;
    if ( checkhead == null ) return true ;
    if ( checkhead.mtype == 3 || checkhead.mtype == 5 || checkhead.mtype == 4 ||
         checkhead.mtype == 7 || checkhead.mtype == 6 ) return true;

    return false;
  } // typeSpecifier()

  static public boolean functionDefinitionOrDeclarators() {
    System.out.println( "functionDefinitionOrDeclarators" ) ;
    ListNode temp = checkhead ; // 進來的位置
    if ( functionDefinitionWithoutID() ) return true ;
    else checkhead = temp ;
      
    if ( restOfDeclarators() ) return true ;
    return false ;
    
  } // functionDefinitionOrDeclarators()

  static public boolean restOfDeclarators() {
    System.out.println( "restOfDeclarators" ) ;
    if ( checkhead.mtype == 47 ) return true ;
    
    
    if ( checkhead.mtype == 16 ) {
      if ( checkhead.mnext == null ) return true ;
      else checkhead = checkhead.mnext ;
      
      if ( checkhead.mtype == 2 ) {
        if ( checkhead.mnext == null ) return true ;
        else checkhead = checkhead.mnext ;
        
        if ( checkhead.mtype != 17 ) return false ;
        else {
          if ( checkhead.mnext == null ) return true ;
          else checkhead = checkhead.mnext ;
        } // else 
      } // if
      else return false ;
    } // if

    while ( checkhead.mtype != 47 ) {
      if ( checkhead.mnext == null ) return true ;
      else checkhead = checkhead.mnext ;
      
      if ( checkhead.mtype == 48 ) {
        if ( checkhead.mnext == null ) return true ;
        else checkhead = checkhead.mnext ;
        
        if ( checkhead.mtype == 1 ) {
          if ( checkhead.mnext == null ) return true ;
          else checkhead = checkhead.mnext ;
          
          if ( checkhead.mtype == 16 ) {
            if ( checkhead.mnext == null ) return true ;
            else checkhead = checkhead.mnext ;
            
            if ( checkhead.mtype == 2 ) {
              if ( checkhead.mnext == null ) return true ;
              else checkhead = checkhead.mnext ;
              
              if ( checkhead.mtype != 17 ) return false ;
              else {
                if ( checkhead.mnext == null ) return true ;
                else checkhead = checkhead.mnext ;
              } // else 
            } // if
            else return false ;
          } // if
        } // if
      } // if
    } // while
    if ( checkhead.mtype == 47 ) return true ;

    return false ;
  } // restOfDeclarators()

  static public boolean functionDefinitionWithoutID() {
    System.out.println( "functionDefinitionWithoutID" ) ;
    if ( checkhead.mtype == 14 ) {
      if ( checkhead.mnext == null ) return true ;
      else checkhead = checkhead.mnext ;
      
      if ( checkhead.mtype != 15 ) {
        if ( checkhead.mtype == 8 ) {
          if ( checkhead.mnext == null ) return true ;
          else checkhead = checkhead.mnext ;
        } // if
        else  if ( formalParameterList() ) {
          if ( checkhead.mnext == null ) return true ;
          else checkhead = checkhead.mnext ;
        } // else if
        else return false ;
      } // if
      else {
        if ( checkhead.mnext == null ) return true ;
        else checkhead = checkhead.mnext ;
      } // else 
      
      if ( compoundStatement() ) return true ;
      else return false ;
    } // if
    
    return false ;
  } // functionDefinitionWithoutID()

  static public boolean formalParameterList() {
    System.out.println( "formalParameterList" ) ;
    if ( typeSpecifier() ) {
      if ( checkhead.mnext == null ) return true ;
      else checkhead = checkhead.mnext ;
      
      if ( checkhead.mtype == 32 ) {
        if ( checkhead.mnext == null ) return true ;
        else checkhead = checkhead.mnext ;
      } // if
      
      if ( checkhead.mtype == 1 ) {
        if ( checkhead.mnext == null ) return true ;
        else checkhead = checkhead.mnext ;  
        
        if ( checkhead.mtype == 16 ) {
          if ( checkhead.mnext == null ) return true ;
          else checkhead = checkhead.mnext ;
          
          if ( checkhead.mtype == 2 ) {
            if ( checkhead.mnext == null ) return true ;
            else checkhead = checkhead.mnext ;
            
            if ( checkhead.mtype != 17 ) return false ;
          } // if
          else return false ;
        } // if
        
        if ( checkhead.mnext == null ) return true ;
        else if ( checkhead.mnext.mtype != 48 ) return true ;
        else {
          while ( checkhead.mtype == 48 ) {
            if ( typeSpecifier() ) {
              if ( checkhead.mnext == null ) return true ;
              else checkhead = checkhead.mnext ;
              
              if ( checkhead.mtype == 32 ) {
                if ( checkhead.mnext == null ) return true ;
                else checkhead = checkhead.mnext ;
              } // if
              
              if ( checkhead.mtype == 1 ) {
                if ( checkhead.mnext == null ) return true ;
                else checkhead = checkhead.mnext ;  
                
                if ( checkhead.mtype == 16 ) {
                  if ( checkhead.mnext == null ) return true ;
                  else checkhead = checkhead.mnext ;
                  
                  if ( checkhead.mtype == 2 ) {
                    if ( checkhead.mnext == null ) return true ;
                    else checkhead = checkhead.mnext ;
                    
                    if ( checkhead.mtype != 17 ) return false ;
                    else {
                      if ( checkhead.mnext == null ) return true ;
                      else {
                        if ( checkhead.mnext == null ) return true ;
                        else if ( checkhead.mnext.mtype != 48 ) return true ;
                        else checkhead = checkhead.mnext ;
                      } // else 
                    } // else 
                  } // if
                  else return false ;
               
                } // if
              } // if
              else return false ;
              
            } // if
          } // while
        } // else 
      }// if
    } // if
    return false ; 
  } // formalParameterList()

  static public boolean compoundStatement() {
    System.out.println( "compoundStatement" ) ;
    if ( checkhead.mtype == 18 ) {
      if ( checkhead.mnext == null ) return true ;
      else checkhead = checkhead.mnext ;
      
      if ( checkhead.mtype == 19 ) return true ;
      while ( definitionOrStatement() ) {
        if ( checkhead.mnext == null ) return true ;
        else checkhead = checkhead.mnext ;
        
        if ( checkhead.mtype == 19 ) return true ;
      } // while 
      
    } // if
    return false;
  } // compoundStatement()

  static public boolean declaration() {
    System.out.println( "declaration" ) ;
    if ( typeSpecifier() ) {
      if ( checkhead.mnext == null ) return true ;
      else checkhead = checkhead.mnext ;
      
      if ( checkhead.mtype == 1 ) {
        if ( checkhead.mnext == null ) return true ;
        else checkhead = checkhead.mnext ;
        
        if ( restOfDeclarators() ) return true ;
        else return false ;
      } // if
      else return false ;
    } // if
    return false ;
  } // declaration()

  static public boolean statement() {
    System.out.println( "statement" ) ;
    ListNode temp = checkhead ;
    if ( checkhead.mtype == 47 ) return true ;
    else if ( expression() ) {
      if ( checkhead.mnext == null ) return true ;
      else checkhead = checkhead.mnext ;
      
      if ( checkhead.mtype == 47 ) return true ;
    } // else if 
    
    checkhead = temp ;
    if ( checkhead.mtype == 13 ) { // return 
      if ( checkhead.mnext == null ) return true ;
      else checkhead = checkhead.mnext ;
      
      if ( checkhead.mtype == 47 ) return true;
      else if ( expression() ) {
        if ( checkhead.mnext == null ) return true ;
        else checkhead = checkhead.mnext ;
        
        if ( checkhead.mtype == 47 ) return true;
        else return false ;
      } // if
      else return false ; 
    } // else if 
    
    checkhead = temp ;
    // System.out.println( checkhead.mitem ) ;
    if ( compoundStatement() ) return true;
    
    checkhead = temp ;
    if ( checkhead.mtype == 9 ) { // if
      if ( checkhead.mnext == null ) return true ;
      else checkhead = checkhead.mnext ;
      
      if ( checkhead.mtype == 14 ) {
        if ( checkhead.mnext == null ) return true ;
        else checkhead = checkhead.mnext ;
        
        if ( expression() ) {
          if ( checkhead.mnext == null ) return true ;
          else checkhead = checkhead.mnext ;
          
          if ( checkhead.mtype == 15 ) {
            if ( checkhead.mnext == null ) return true ;
            else checkhead = checkhead.mnext ;
          
            if ( statement() ) {
              if ( checkhead.mnext == null ) return true ;
              else if ( checkhead.mnext.mtype != 10 ) return true ;
              else {
                checkhead = checkhead.mnext ; // == 10 else 
                if ( checkhead.mnext == null ) return true ;
                else checkhead = checkhead.mnext ;
                
                if ( statement() ) return true ;
                else return false ;
                
              } // else 
            }// if
            else return false ;
          } // if
          else return false ;
        } // if
        else return false ;
      } // if
      else return false ;
    } // if 
    
    checkhead = temp ;
    if ( checkhead.mtype == 11 ) {
      if ( checkhead.mnext == null ) return true ;
      else checkhead = checkhead.mnext ;
      
      if ( checkhead.mtype == 14 ) {
        if ( checkhead.mnext == null ) return true ;
        else checkhead = checkhead.mnext ;
        
        if ( expression() ) {
          if ( checkhead.mnext == null ) return true ;
          else checkhead = checkhead.mnext ;
          
          if ( checkhead.mtype == 15 ) {
            if ( checkhead.mnext == null ) return true ;
            else checkhead = checkhead.mnext ;
            
            if ( statement() ) return true ;
            else return false ;

          } // if  
          else return false ;
        } // if
        else return false ;
      } // if
      else return false ;
    } // if 
    
    checkhead = temp ;
    if ( checkhead.mtype == 12 ) {
      if ( checkhead.mnext == null ) return true ;
      else checkhead = checkhead.mnext ;
      
      if ( statement() ) {
        if ( checkhead.mnext == null ) return true ;
        else checkhead = checkhead.mnext ;
        
        if ( checkhead.mtype == 11 ) { // while
          if ( checkhead.mnext == null ) return true ;
          else checkhead = checkhead.mnext ;
          
          if ( checkhead.mtype == 14 ) {
            if ( checkhead.mnext == null ) return true ;
            else checkhead = checkhead.mnext ;
            
            if ( expression() ) {
              if ( checkhead.mnext == null ) return true ;
              else checkhead = checkhead.mnext ;
              
              if ( checkhead.mtype == 15 ) {
                if ( checkhead.mnext == null ) return true ;
                else checkhead = checkhead.mnext ;
                
                if ( checkhead.mtype == 47 ) return true ; // ;
                else return false ;
              } // if  
              else return false ;
            } // if
            else return false ;
          } // if
          else return false ;
        } // if
        else return false ;
      } // if
      else return false ;
    } // else if
    return false ;
  } // statement()

  static public boolean expression() {
    System.out.println( "expression" ) ;
    if ( basicExpression() ) {
      if ( checkhead.mnext == null ) return true ;
      else if ( checkhead.mnext.mtype != 48 ) return true  ;
      else {
        checkhead = checkhead.mnext ;        
        while ( checkhead.mtype == 48 ) { 
          if ( checkhead.mnext == null ) return true ;
          else checkhead = checkhead.mnext ;
          
          if ( !basicExpression() ) return false ;
          else {
            if ( checkhead.mnext == null ) return true ;
            else if ( checkhead.mnext.mtype != 48 ) return true ;
            else checkhead = checkhead.mnext ;
          } // else 
        } // while
      } // else 
    } // if
    return false ;
  } // expression()

  static public boolean basicExpression() {
    System.out.println( "basicExpression" ) ;
    if ( checkhead.mtype == 1 ) {
      if ( checkhead.mnext == null ) return true ;
      else checkhead = checkhead.mnext ;
      if ( restOfIdentifierStartedBasicExp() ) return true ;
      else return false ;
    } // if 
    else if ( checkhead.mtype == 43 || checkhead.mtype == 44 ) {
      if ( checkhead.mnext == null ) return true ;
      else checkhead = checkhead.mnext ;
      if ( checkhead.mtype == 1 ) {
        if ( checkhead.mnext == null ) return true ;
        else checkhead = checkhead.mnext ;
        if ( restOfPPMMIdentifierStartedBasicExp() ) return true ;
        else return false ;
      } // if
      else return false ;
    } // else if 
    else if ( sign() ) {
      if ( checkhead.mnext == null ) return true ;
      else checkhead = checkhead.mnext ;
      while ( sign() ) {
        if ( checkhead.mnext == null ) return true ;
        else checkhead = checkhead.mnext ;
      } // while
      
      if ( signedUnaryExp() ) {
        if ( checkhead.mnext == null ) return true ;
        else checkhead = checkhead.mnext ;
        
        if ( restOfMaybeConditionalExpAndRestOfMaybeLogicalORExp() )  return true ;
        else return false ;
        
      } // if
      else return false ;
    } // else if 
    else if ( checkhead.mtype == 2  ) {
      if ( checkhead.mnext == null ) return true ;
      else checkhead = checkhead.mnext ;
      
      if ( restOfMaybeConditionalExpAndRestOfMaybeLogicalORExp() ) return true ;
      else return false ;
    } // else if 
    else if ( checkhead.mtype == 14 ) {
      if ( checkhead.mnext == null ) return true ;
      else checkhead = checkhead.mnext ;
      
      if ( expression() ) {
        if ( checkhead.mnext == null ) return true ;
        else checkhead = checkhead.mnext ;
        
        if ( checkhead.mtype == 15 ) {
          if ( checkhead.mnext == null ) return true ;
          else checkhead = checkhead.mnext ;
          
          if ( restOfMaybeConditionalExpAndRestOfMaybeLogicalORExp() ) return true ;
          else return false ;
        } // if  
        else return false ;
      } // if
      else return false ;
    } // else if
    return false;
  } // basicExpression()

  static public boolean restOfIdentifierStartedBasicExp() {
    System.out.println( "restOfIdentifierStartedBasicExp" ) ;
    ListNode temp = checkhead ;
    if ( checkhead.mtype == 16 ) {
      if ( checkhead.mnext == null ) return true ;
      else checkhead = checkhead.mnext ;
      
      if ( expression() ) {
        if ( checkhead.mnext == null ) return true ;
        else checkhead = checkhead.mnext ;
        
        if ( checkhead.mtype == 17 ) {
          if ( checkhead.mnext == null ) return true ;
          else checkhead = checkhead.mnext ;
          
          temp = checkhead ;
          if ( checkhead.mtype == 43 || checkhead.mtype == 44 ) {
            if ( checkhead.mnext == null ) return true ;
            else checkhead = checkhead.mnext ;
            
            if ( restOfMaybeConditionalExpAndRestOfMaybeLogicalORExp() ) return true ;
            else return false ;
          } // if
          
          if ( assignmentOperator() ) {
            if ( checkhead.mnext == null ) return true ;
            else checkhead = checkhead.mnext ;
            
            if ( basicExpression() ) return true ;
            else return false ;
          } // if
          
          checkhead = temp;
          if ( restOfMaybeConditionalExpAndRestOfMaybeLogicalORExp() ) return true ;
          else return false ;
          
        } // if  
        else return false ;
      } // if
      else return false ;
    } // if
    else if ( checkhead.mtype == 14 ) {
      if ( checkhead.mnext == null ) return true ;
      else checkhead = checkhead.mnext ;
      
      
      
      if ( checkhead.mtype != 15 ) { // )
        if ( !actualParameterList() ) return false ;
        else {
          if ( checkhead.mnext == null ) return true ;
          else checkhead = checkhead.mnext ;
        }
      } // if
      
      if ( checkhead.mtype == 15 ) { // )
        if ( checkhead.mnext == null ) return true ;
        else checkhead = checkhead.mnext ;
        if ( restOfMaybeConditionalExpAndRestOfMaybeLogicalORExp() ) return true ;
        else return false ;
      } // if
      else return false ;
  
    } // else if
    else {
      temp = checkhead ;
      if ( checkhead.mtype == 43 || checkhead.mtype == 44 ) {
        if ( checkhead.mnext == null ) return true ;
        else checkhead = checkhead.mnext ;
        
        if ( restOfMaybeConditionalExpAndRestOfMaybeLogicalORExp() ) return true ;
        else return false ;
      } // if
      
      if ( assignmentOperator() ) {
        if ( checkhead.mnext == null ) return true ;
        else checkhead = checkhead.mnext ;
        
        if ( basicExpression() ) return true ;
        else return false ;
      } // if
      
      checkhead = temp;
      if ( restOfMaybeConditionalExpAndRestOfMaybeLogicalORExp() ) return true ;
      else return false ;
    } // else 

  } // restOfIdentifierStartedBasicExp()

  static public boolean restOfPPMMIdentifierStartedBasicExp() {
    System.out.println( "restOfPPMMIdentifierStartedBasicExp" ) ;
    if ( checkhead.mtype == 16 ) {
      if ( checkhead.mnext == null ) return true ;
      else checkhead = checkhead.mnext ;
      
      if ( expression() ) {
        if ( checkhead.mnext == null ) return true ;
        else checkhead = checkhead.mnext ;
        
        if ( checkhead.mtype == 17 ) {
          if ( checkhead.mnext == null ) return true ;
          else checkhead = checkhead.mnext ;
          
          if ( restOfMaybeConditionalExpAndRestOfMaybeLogicalORExp() ) return true ;
          else return false ;
          
        } // if  
        else return false ;
      } // if
      else return false ;
    } // if
    else {
      if ( restOfMaybeConditionalExpAndRestOfMaybeLogicalORExp() ) return true ;
      else return false ;
    } // else
  } // restOfPPMMIdentifierStartedBasicExp()

  static public boolean sign() {
    System.out.println( "sign" ) ;
    if ( checkhead.mtype == 20 || checkhead.mtype == 21 || checkhead.mtype == 35 ) return true ;
    else return false ;
  } // sign()

  static public boolean actualParameterList() {
    System.out.println( "actualParameterList" ) ;
    if ( basicExpression() ) {
      if ( checkhead.mnext == null ) return true ;
      else if ( checkhead.mnext.mtype != 48 ) return true  ;
      else {
        checkhead = checkhead.mnext ;        
        while ( checkhead.mtype == 48 ) { 
          if ( checkhead.mnext == null ) return true ;
          else checkhead = checkhead.mnext ;
          
          if ( !basicExpression() ) return false ;
          else {
            if ( checkhead.mnext == null ) return true ;
            else if ( checkhead.mnext.mtype != 48 ) return true ;
            else checkhead = checkhead.mnext ;
          } // else 
        } // while
      } // else 
    } // if
    return false ;
  } //  actualParameterList()

  static public boolean assignmentOperator() {
    System.out.println( "assignmentOperator" ) ;
    if ( checkhead.mtype == 34 || checkhead.mtype == 38 || checkhead.mtype == 39 || checkhead.mtype == 40 ||
         checkhead.mtype == 41 || checkhead.mtype == 42 ) return true ;
    else return false ;
  } // assignmentOperator()

  static public boolean restOfMaybeConditionalExpAndRestOfMaybeLogicalORExp() {
    System.out.println( "restOfMaybeConditionalExpAndRestOfMaybeLogicalORExp" ) ;
    if ( restOfMaybeLogicalORExp() ) {
      if ( checkhead.mnext == null ) return true ;
      else if ( checkhead.mnext.mtype != 49 ) return true  ;
      else {
        checkhead = checkhead.mnext ;       // ?  
        if ( checkhead.mnext == null ) return true ;
        else checkhead = checkhead.mnext ;
        if ( basicExpression() ) {
          if ( checkhead.mnext == null ) return true ;
          else checkhead = checkhead.mnext ;
          if ( checkhead.mtype != 50 ) { // :
            if ( checkhead.mnext == null ) return true ;
            else checkhead = checkhead.mnext ;
            
            if ( basicExpression() ) return true ;
            else return false ;  
          } // if
          else return false ;
        } // if
        else return false ;
      } // else 
    } // if
    else return false ;
  } // restOfMaybeConditionalExpAndRestOfMaybeLogicalORExp()

  static public boolean restOfMaybeLogicalORExp() {
    System.out.println( "restOfMaybeLogicalORExp" ) ;
    if ( restOfMaybeLogicalANDExp() ) {
      if ( checkhead.mtype != 37 ) return true  ;
      else {
        while ( checkhead.mtype == 37 ) { 
          if ( checkhead.mnext == null ) return true ;
          else checkhead = checkhead.mnext ;
          
          if ( !maybeLogicalANDExp() ) return false ;
          else if ( checkhead.mtype != 37 ) return true ;
        } // while
      } // else
    } // if
    return false ;
  } // restOfMaybeLogicalORExp()

  static public boolean maybeLogicalANDExp() {
    System.out.println( "maybeLogicalANDExp" ) ;
    if ( maybeBitORExp() ) {
      if ( checkhead.mnext == null ) return true ;
      else if ( checkhead.mnext.mtype != 36 ) return true  ;
      else {
        checkhead = checkhead.mnext ;       // and    
        while ( checkhead.mtype == 36 ) { 
          if ( checkhead.mnext == null ) return true ;
          else checkhead = checkhead.mnext ;
          
          if ( !maybeBitORExp() ) return false ;
          else {
            if ( checkhead.mnext == null ) return true ;
            else if ( checkhead.mnext.mtype != 36 ) return true ;
            else checkhead = checkhead.mnext ;
          } // else 
        } // while
      } // else
    } // if
    return false ;
  } // maybeLogicalANDExp()

  static public boolean restOfMaybeLogicalANDExp() {
    System.out.println( "restOfMaybeLogicalANDExp" ) ;
    if ( restOfMaybeBitORExp() ) {
      if ( checkhead.mtype != 36 ) return true  ;
      else {
        while ( checkhead.mtype == 36 ) { 
          if ( checkhead.mnext == null ) return true ;
          else checkhead = checkhead.mnext ;
          
          if ( !maybeBitORExp() ) return false ;
          else if ( checkhead.mtype != 36 ) return true ;
        } // while
      } // else
    } // if
    return false ;
  } // restOfMaybeLogicalANDExp()

  static public boolean maybeBitORExp() {
    System.out.println( "maybeBitORExp" ) ;
    if ( maybeBitEXORExp() ) {
      if ( checkhead.mnext == null ) return true ;
      else if ( checkhead.mnext.mtype != 33 ) return true  ;
      else {
        checkhead = checkhead.mnext ;       // | 
        while ( checkhead.mtype == 33 ) { 
          if ( checkhead.mnext == null ) return true ;
          else checkhead = checkhead.mnext ;
          
          if ( !maybeBitEXORExp() ) return false ;
          else {
            if ( checkhead.mnext == null ) return true ;
            else if ( checkhead.mnext.mtype != 33 ) return true ;
            else checkhead = checkhead.mnext ;
          } // else 
        } // while
      } // else
    } // if
    return false ;
  } // maybeBitORExp()

  static public boolean restOfMaybeBitORExp() {
    System.out.println( "restOfMaybeBitORExp" ) ;
    if ( restOfMaybeBitEXORExp() ) {
      if ( checkhead.mtype != 33 ) return true  ;
      else {
        while ( checkhead.mtype == 33 ) { 
          if ( checkhead.mnext == null ) return true ;
          else checkhead = checkhead.mnext ;
          
          if ( !maybeBitEXORExp() ) return false ;
          else if ( checkhead.mtype != 33 ) return true ;
        } // while
      } // else
    } // if
    return false ;
  } // restOfMaybeBitORExp()

  static public boolean maybeBitEXORExp() {
    System.out.println( "maybeBitEXORExp" ) ;
    if ( maybeBitANDExp() ) {
      if ( checkhead.mnext == null ) return true ;
      else if ( checkhead.mnext.mtype != 25 ) return true  ;
      else {
        checkhead = checkhead.mnext ;       // ^
        while ( checkhead.mtype == 25 ) { 
          if ( checkhead.mnext == null ) return true ;
          else checkhead = checkhead.mnext ;
          
          if ( !maybeBitANDExp() ) return false ;
          else {
            if ( checkhead.mnext == null ) return true ;
            else if ( checkhead.mnext.mtype != 25 ) return true ;
            else checkhead = checkhead.mnext ;
          } // else 
        } // while
      } // else
    } // if
    return false ;
  } // maybeBitEXORExp()

  static public boolean restOfMaybeBitEXORExp() {
    System.out.println( "restOfMaybeBitEXORExp" ) ;
    if ( restOfMaybeBitANDExp() ) {
      if ( checkhead.mtype != 25 ) return true  ;
      else {
        while ( checkhead.mtype == 25 ) { 
          if ( checkhead.mnext == null ) return true ;
          else checkhead = checkhead.mnext ;
          
          if ( !maybeBitANDExp() ) return false ;
          else if ( checkhead.mtype != 25 ) return true ;
        } // while
      } // else
    } // if
    return false ;
  } // restOfMaybeBitEXORExp()

  static public boolean maybeBitANDExp() {
    System.out.println( "maybeBitANDExp" ) ;
    if ( maybeEqualityExp() ) {
      if ( checkhead.mnext == null ) return true ;
      else if ( checkhead.mnext.mtype != 32 ) return true  ;
      else {
        checkhead = checkhead.mnext ;       // &
        while ( checkhead.mtype == 32 ) { 
          if ( checkhead.mnext == null ) return true ;
          else checkhead = checkhead.mnext ;
          
          if ( !maybeEqualityExp() ) return false ;
          else {
            if ( checkhead.mnext == null ) return true ;
            else if ( checkhead.mnext.mtype != 32 ) return true ;
            else checkhead = checkhead.mnext ;
          } // else 
        } // while
      } // else
    } // if
    return false ;
  } // maybeBitANDExp()

  static public boolean restOfMaybeBitANDExp() {
    System.out.println( "restOfMaybeBitANDExp" ) ;
    if ( restOfMaybeEqualityExp() ) {
      if ( checkhead.mtype != 32 ) return true  ;
      else {
        while ( checkhead.mtype == 32 ) { 
          if ( checkhead.mnext == null ) return true ;
          else checkhead = checkhead.mnext ;
          
          if ( !maybeEqualityExp() ) return false ;
          else if ( checkhead.mtype != 32 ) return true ;
        } // while
      } // else
    } // if
    return false ;
  } // restOfMaybeBitANDExp()

  static public boolean maybeEqualityExp() {
    System.out.println( "maybeEqualityExp" ) ;
    if ( maybeRelationalExp() ) {
      if ( checkhead.mnext == null ) return true ;
      else if ( checkhead.mnext.mtype != 30 && checkhead.mnext.mtype != 31 ) return true  ;
      else {
        checkhead = checkhead.mnext ;       // == !=
        while ( checkhead.mtype == 30 || checkhead.mtype == 31 ) { 
          if ( checkhead.mnext == null ) return true ;
          else checkhead = checkhead.mnext ;
          
          if ( !maybeRelationalExp() ) return false ;
          else {
            if ( checkhead.mnext == null ) return true ;
            else if ( checkhead.mnext.mtype != 30 && checkhead.mnext.mtype != 31 ) return true ;
            else checkhead = checkhead.mnext ;
          } // else 
        } // while
      } // else
    } // if
    return false ;
  } // maybeEqualityExp()

  static public boolean restOfMaybeEqualityExp() {
    System.out.println( "restOfMaybeEqualityExp" ) ;
    if ( restOfMaybeRelationalExp() ) {
      if ( checkhead.mtype != 30 && checkhead.mtype != 31 ) return true  ;
      else {
        while ( checkhead.mtype == 30 || checkhead.mtype == 31 ) { 
          if ( checkhead.mnext == null ) return true ;
          else checkhead = checkhead.mnext ;
          
          if ( !maybeRelationalExp() ) return false ;
          else if ( checkhead.mtype != 30 && checkhead.mtype != 31 ) return true ;
        } // while
      } // else
    } // if
    return false ;
  } // restOfMaybeEqualityExp()

  static public boolean maybeRelationalExp() {
    System.out.println( "maybeRelationalExp" ) ;
    if ( maybeShiftExp() ) {
      if ( checkhead.mnext == null ) return true ;
      else if ( checkhead.mnext.mtype != 26 && checkhead.mnext.mtype != 27 && checkhead.mnext.mtype != 28
                && checkhead.mnext.mtype != 29 ) return true  ;
      else {
        checkhead = checkhead.mnext ;       // == !=
        while ( checkhead.mtype == 26 || checkhead.mtype == 27 || checkhead.mtype == 28 || 
                checkhead.mtype == 29 ) { 
          if ( checkhead.mnext == null ) return true ;
          else checkhead = checkhead.mnext ;
          
          if ( !maybeShiftExp() ) return false ;
          else {
            if ( checkhead.mnext == null ) return true ;
            else if ( checkhead.mnext.mtype != 26 && checkhead.mnext.mtype != 27 && checkhead.mnext.mtype != 28
                      && checkhead.mnext.mtype != 29 ) return true ;
            else checkhead = checkhead.mnext ;
          } // else 
        } // while
      } // else
    } // if
    return false ;
  } // maybeRelationalExp()

  static public boolean restOfMaybeRelationalExp() {
    System.out.println( "restOfMaybeRelationalExp" ) ;
    if ( restOfMaybeShiftExp() ) {
      if ( checkhead.mtype != 26 && checkhead.mtype != 27 && checkhead.mtype != 28 && checkhead.mtype != 29 ) return true  ;
      else {
        while ( checkhead.mtype == 26 || checkhead.mtype == 27 || checkhead.mtype == 28 || checkhead.mtype == 29 ) { 
          if ( checkhead.mnext == null ) return true ;
          else checkhead = checkhead.mnext ;
          
          if ( !maybeShiftExp() ) return false ;
          else if ( checkhead.mtype != 26 && checkhead.mtype != 27 && checkhead.mtype != 28 && checkhead.mtype != 29 ) return true ;
        } // while
      } // else
    } // if
    return false ;
  } // restOfMaybeRelationalExp()

  static public boolean maybeShiftExp() {
    System.out.println( "maybeShiftExp" ) ;
    if ( maybeAdditiveExp() ) {
      if ( checkhead.mnext == null ) return true ;
      else if ( checkhead.mnext.mtype != 45 && checkhead.mnext.mtype != 46 ) return true  ;
      else {
        checkhead = checkhead.mnext ;       //  >>  <<     
        while ( checkhead.mtype == 45 || checkhead.mtype == 46 ) { 
          if ( checkhead.mnext == null ) return true ;
          else checkhead = checkhead.mnext ;
          
          if ( !maybeAdditiveExp() ) return false ;
          else {
            if ( checkhead.mnext == null ) return true ;
            else if ( checkhead.mnext.mtype != 45 && checkhead.mnext.mtype != 46 ) return true ;
            else checkhead = checkhead.mnext ;
          } // else 
        } // while
      } // else
    } // if
    return false ;
  } // maybeShiftExp()

  static public boolean restOfMaybeShiftExp() {
    System.out.println( "restOfMaybeShiftExp" ) ;
    if ( restOfMaybeAdditiveExp() ) {
      if ( checkhead.mtype != 45 && checkhead.mtype != 46 ) return true  ;
      else {
        while ( checkhead.mtype == 45 || checkhead.mtype == 46 ) { 
          if ( checkhead.mnext == null ) return true ;
          else checkhead = checkhead.mnext ;
          
          if ( !maybeAdditiveExp() ) return false ;
          else if ( checkhead.mtype != 45 && checkhead.mtype != 46 ) return true ;
        } // while
      } // else
    } // if
    return false ;
  } // restOfMaybeShiftExp()

  static public boolean maybeAdditiveExp() {
    System.out.println( "maybeAdditiveExp" ) ;
    if ( maybeMultExp() ) {
      if ( checkhead.mnext == null ) return true ;
      else if ( checkhead.mnext.mtype != 20 && checkhead.mnext.mtype != 21 ) return true  ;
      else {
        checkhead = checkhead.mnext ;       //  >>  <<     
        while ( checkhead.mtype == 20 || checkhead.mtype == 21 ) { 
          if ( checkhead.mnext == null ) return true ;
          else checkhead = checkhead.mnext ;
          
          if ( !maybeMultExp() ) return false ;
          else {
            if ( checkhead.mnext == null ) return true ;
            else if ( checkhead.mnext.mtype != 20 && checkhead.mnext.mtype != 21 ) return true ;
            else checkhead = checkhead.mnext ;
          } // else 
        } // while
      } // else
    } // if
    return false ;
  } // maybeAdditiveExp()

  static public boolean restOfMaybeAdditiveExp() {
    System.out.println( "restOfMaybeAdditiveExp" ) ;

    if ( restOfMaybeMultExp() ) {
      if ( checkhead.mtype != 20 && checkhead.mtype != 21 ) return true  ;
      else {
        while ( checkhead.mtype == 20 || checkhead.mtype == 21 ) { 
          if ( checkhead.mnext == null ) return true ;
          else checkhead = checkhead.mnext ;
          
          if ( !maybeMultExp() ) return false ;
          else if ( checkhead.mtype != 20 && checkhead.mtype != 21 ) return true ;
        } // while
      } // else
    } // if
    return false ;
  } // restOfMaybeAdditiveExp()

  static public boolean maybeMultExp() {
    System.out.println( "maybeMultExp" ) ;
    if ( unaryExp() ) {
      if ( checkhead.mnext == null ) return true ;
      else checkhead = checkhead.mnext ;
      
      if ( restOfMaybeMultExp() ) return true ;
      else return false ;
    }
    return false ;
  } // maybeMultExp()

  static public boolean restOfMaybeMultExp() {
    System.out.println( "restOfMaybeMultExp" ) ;
    if ( checkhead.mtype == 22 || checkhead.mtype == 23 || checkhead.mtype == 24 ) {
      if ( checkhead.mnext == null ) return true ;
      else checkhead = checkhead.mnext ;
      
      if ( !unaryExp() ) return false ;
      else {
        if ( checkhead.mnext == null ) return true ;
        else if ( checkhead.mnext.mtype != 22 && checkhead.mnext.mtype != 23 && checkhead.mnext.mtype != 24 ) return true  ;
        else {
          checkhead = checkhead.mnext ;       //  * / %
          while ( checkhead.mtype == 22 || checkhead.mtype == 23 || checkhead.mtype == 24 ) { 
            if ( checkhead.mnext == null ) return true ;
            else checkhead = checkhead.mnext ;
            
            if ( !unaryExp() ) return false ;
            else {
              if ( checkhead.mnext == null ) return true ;
              else if ( checkhead.mnext.mtype != 22 && checkhead.mnext.mtype != 23 && checkhead.mnext.mtype != 24 ) return true ;
              else checkhead = checkhead.mnext ;
            } // else 
          } // while
        } // else
      } // else 
    } // if
    return true ;
  } // restOfMaybeMultExp()

  static public boolean unaryExp() {
    System.out.println( "unaryExp" ) ;
    if ( sign() ) {
      if ( checkhead.mnext == null ) return true ;
      else checkhead = checkhead.mnext ;
      
      while ( sign() ) {
        if ( checkhead.mnext == null ) return true ;
        else checkhead = checkhead.mnext ;
      } // while
      
      if ( signedUnaryExp() ) return true ;
      else return false ;
      
    } // if
    else if ( unsignedUnaryExp() )  return true ;
    else if ( checkhead.mtype == 43 || checkhead.mtype == 44 ) {
      if ( checkhead.mnext == null ) return true ;
      else checkhead = checkhead.mnext ;
        
      if ( checkhead.mtype == 1 ) {
        if ( checkhead.mnext == null ) return true ;
        else if ( checkhead.mnext.mtype != 16 ) return true  ;
        else {
          checkhead = checkhead.mnext ; // [
          if ( checkhead.mnext == null ) return true ;
          else checkhead = checkhead.mnext ;
          
          if ( expression() ) {
            if ( checkhead.mnext == null ) return true ;
            else checkhead = checkhead.mnext ;
            
            if ( checkhead.mtype == 17 ) return true ;
            else return false ;
          } // if
          else return false ;
        } // else 
      } // if
      else return false ;  
    } // else if
    return false ; 
  } // unaryExp()

  static public boolean signedUnaryExp() {
    System.out.println( "signedUnaryExp" ) ;
    if ( checkhead.mtype == 1 ) {
      if ( checkhead.mnext == null ) return true ;
      else checkhead = checkhead.mnext ;
      
      if ( checkhead.mnext == null ) return true ;
      else if ( checkhead.mnext.mtype != 14 && checkhead.mnext.mtype != 16 ) return true  ;
      else {
        checkhead = checkhead.mnext ; // 
        if ( checkhead.mtype == 14 ) {
          if ( checkhead.mnext == null ) return true ;
          else checkhead = checkhead.mnext ;
          
          if ( checkhead.mtype == 15 ) return false ;
          else {
            if ( actualParameterList() ) {
              if ( checkhead.mnext == null ) return true ;
              else checkhead = checkhead.mnext ;
              
              if ( checkhead.mtype == 15 ) return true ;
              else return false ;
            } // if
            else return false ;
          } // else
        } // if
        
        if ( checkhead.mtype == 16 ) {
          if ( checkhead.mnext == null ) return true ;
          else checkhead = checkhead.mnext ;
          
          if ( expression() ) {
            if ( checkhead.mnext == null ) return true ;
            else checkhead = checkhead.mnext ;
            
            if ( checkhead.mtype == 17 )  return true ;
            else return false ;
          } //if
          else return false ;
        } // if
      } // else
    } // if
    else if ( checkhead.mtype == 2 )  return true ;
    else if ( checkhead.mtype == 14 ) {
      if ( checkhead.mnext == null ) return true ;
      else checkhead = checkhead.mnext ;

      if ( expression() ) {
        if ( checkhead.mnext == null ) return true ;
        else checkhead = checkhead.mnext ;
        
        if ( checkhead.mtype == 15 ) return true ;
        else return false ;
      } // if
      else return false ;
    } // else if
    return false ; 
  } // signedUnaryExp()

  static public boolean unsignedUnaryExp() {
    System.out.println( "unsignedUnaryExp" ) ;
    if ( checkhead.mtype == 1 ) {
      if ( checkhead.mnext == null ) return true ;
      else checkhead = checkhead.mnext ;
      
      if ( checkhead.mnext == null ) return true ;
      else if ( checkhead.mnext.mtype != 14 && checkhead.mnext.mtype != 16 ) return true  ;
      else {
        checkhead = checkhead.mnext ; // 
        if ( checkhead.mtype == 14 ) {
          if ( checkhead.mnext == null ) return true ;
          else checkhead = checkhead.mnext ;
          
          if ( checkhead.mtype == 15 ) return false ;
          else {
            if ( actualParameterList() ) {
              if ( checkhead.mnext == null ) return true ;
              else checkhead = checkhead.mnext ;
              
              if ( checkhead.mtype == 15 ) return true ;
              else return false ;
            } // if
            else return false ;
          } // else
        } // if
        
        if ( checkhead.mtype == 16 ) {
          if ( checkhead.mnext == null ) return true ;
          else checkhead = checkhead.mnext ;
          
          if ( expression() ) {
            if ( checkhead.mnext == null ) return true ;
            else checkhead = checkhead.mnext ;
            
            if ( checkhead.mtype == 17 )  {
              if ( checkhead.mnext == null ) return true ;
              else if ( checkhead.mnext.mtype != 43 && checkhead.mnext.mtype != 44 ) return true  ;
              else {
                checkhead = checkhead.mnext ;
                
                if ( checkhead.mtype == 43 || checkhead.mtype == 44 ) return true ;
                else return false ;
              } // else 
            } // if
            else return false ;
          } //if
          else return false ;
        } // if
      } // else
    } // if
    else if ( checkhead.mtype == 2 )  return true ;
    else if ( checkhead.mtype == 14 ) {
      if ( checkhead.mnext == null ) return true ;
      else checkhead = checkhead.mnext ;

      if ( expression() ) {
        if ( checkhead.mnext == null ) return true ;
        else checkhead = checkhead.mnext ;
        
        if ( checkhead.mtype == 15 ) return true ;
        else return false ;
      } // if
      else return false ;
    } // else if
    return false ; 
  } // unsignedUnaryExp()


  
  // =====================================================================================================
  

  static public void Handlegrammer() {
    // System.out.println( "pp0" );
    if ( sifhasprint > 0 ) Cleanhalf() ;
    else {
      Cleaninfunction() ;
      Cleanall() ;
    } // else
    
    Readcommendandstore() ;
    // if ( !Grammercheck() ) Readcommendandstore() ;
    
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
  

  static public void Addfunctioninput( String key, String item ) {
    Functiondata ds = Finddata( key );
    if ( ds != null ) {
      ds.Addinput( item );
    } // if
  } // Addfunctioninput()

  static public void Addfunctionitem( String key, String item ) {
    Functiondata ds = Finddata( key );
    if ( ds != null ) {
      ds.Additem( item );
    } // if 
  } // Addfunctionitem()

  static public ArrayList<String> Getinput( String key ) {
    Functiondata ds = Finddata( key );
    return  ds.Getinput() ;
  } // Getinput()

  static public ArrayList<String> Getitem( String key ) {
    Functiondata ds = Finddata( key );
    return ds.Getitem() ;
  } // Getitem()

  static public String Gettype( String key ) {
    Functiondata ds = Finddata( key );
    // System.out.println( key ) ;
    return ds.Gettype() ;
  } // Gettype()
  
  static public Functiondata Finddata( String mstr ) {
    for ( int i = 0 ; i < sgdefinefunction.size() ; i++ ) {
      Functiondata ds = sgdefinefunction.get( i );
      if ( ds.GetStr().equals( mstr ) ) {
        return ds;
      } // if
    } // for
  
  
    return null;
  } // Finddata()

  static public void Displayall() {
    ArrayList<String> list = new ArrayList<String>();
    for ( int i = sgdefinefunction.size() - 1 ; i > 0  ; i-- ) {
      Functiondata ds = sgdefinefunction.get( i ) ;
      if ( !ds.mstr.equals( "ListAllVariables" ) && !ds.mstr.equals( "ListAllFunctions" ) 
           && !ds.mstr.equals( "ListVariable" ) && !ds.mstr.equals( "ListFunction" )  )
        list.add( ds.mstr ) ;
    } // for
    
    // System.out.println( list.size() ) ;
    for ( int i = 1; i < list.size() ; i++ ) {
      String key = list.get( i );
      int j = i - 1;

      while ( j >= 0 && list.get( j ).compareTo( key ) > 0 ) {
        list.set( j + 1, list.get( j ) );
        j = j - 1;
      } // while

      list.set( j + 1, key );
    } // for
    
    for ( int i = 0 ; i < list.size() ; i++ ) { 
      System.out.println( list.get( i ) + "()" ) ;
    } // for
    
  } // Displayall()
  
  
  static public boolean Checkfunctionexist( String item ) {
    for ( int i = 0; i < sgdefinefunction.size() ; i++ ) {
      Functiondata pair = sgdefinefunction.get( i );
      // System.out.println( pair.GetStr() ) ;
      if ( pair.GetStr().equals( item ) ) {
        return true;
      } // if
    } // for
    // 沒有找到匹配的字符串，返回 null 或拋出異常
    return false ; // 或者可以選擇拋出一個異常
  } // Checkfunctionexist()
  
  
  static public boolean Removefunction( String item ) {
    for ( int i = 0; i < sgdefinefunction.size() ; i++ ) {
      Functiondata pair = sgdefinefunction.get( i );
      // System.out.println( pair.GetStr() ) ;
      if ( pair.GetStr().equals( item ) ) {
        sgdefinefunction.remove( i ) ;
      } // if
    } // for
    // 沒有找到匹配的字符串，返回 null 或拋出異常
    return false ; // 或者可以選擇拋出一個異常
  } // Removefunction()
  
  static public void Addfunction( String str, String type ) { 
    boolean hasbf = false;
    
    for ( int i = 0; i < sgdefinefunction.size() ; i++ ) {
      Functiondata pair = sgdefinefunction.get( i );
      if ( pair.GetStr().equals( str ) ) {
        // System.out.println( pair.GetNum() ) ;
        // pair.Setitem( item );
        hasbf = true ;
      } // if
    } // for
    
    if ( !hasbf ) {
      Functiondata pair = new Functiondata( str, type );
      // System.out.println( pair.GetNum() ) ;
      sgdefinefunction.add( pair );
    } // if
  } // Addfunction()
  
  
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
  
  
  static public int Getsize( String item ) {
    for ( int i = 0; i < sgdefinename.size() ; i++ ) {
      Iddata pair = sgdefinename.get( i );
      if ( pair.GetStr().equals( item ) ) {
        return pair.Getsize();
      } // if
    } // for
    // 沒有找到匹配的字符串，返回 null 或拋出異常
    return 0 ; // 或者可以選擇拋出一個異常
  } // Getsize()
  
  
  static public void AddData( String str, float num, String type, 
                              int size, String item, boolean infunction ) {
    boolean hasbf = false;
    
    for ( int i = 0; i < sgdefinename.size() ; i++ ) {
      Iddata pair = sgdefinename.get( i );
      if ( pair.GetStr().equals( str ) ) {
        // System.out.println( pair.GetNum() ) ;
        if ( pair.Gettype().equals( "string" ) ) pair.Setitem( item );
        else pair.SetNum( num );
        hasbf = true ;
      } // if
    } // for
    
    
    if ( !hasbf ) {
      Iddata pair = new Iddata( str, num, type, size, item, infunction );
      // System.out.println( pair.GetNum() ) ;
      sgdefinename.add( pair );
    } // if
  } // AddData()
  
  
  static public String Findtype( String str ) {
    
    for ( int i = 0; i < sgdefinename.size() ; i++ ) {
      Iddata pair = sgdefinename.get( i );
      if ( pair.GetStr().equals( str ) ) {
        // System.out.println( pair.Gettype() ) ;
        return pair.Gettype();
      } // if
    } // for
    
    // System.out.println( "123" ) ;
    return "nofind" ;
  } // Findtype()
  
  
  
  static public String Getstring( String str ) {
    
    for ( int i = 0; i < sgdefinename.size() ; i++ ) {
      Iddata pair = sgdefinename.get( i );
      if ( pair.GetStr().equals( str ) ) {
        // System.out.println( pair.GetNum() + pair.Gettype() + pair.Getinfunction() ) ;
        return pair.Getitem();
      } // if
    } // for
    
    return "nofind" ;
  } // Getstring()
  
  
  
  static public void Cleaninfunction() {
    for ( int i = 0; i < sgdefinename.size() ; i++ ) {
      Iddata pair = sgdefinename.get( i );
      if ( pair.Getinfunction() ) {
        sgdefinename.remove( i ) ;
      } // if
    } // for
  } // Cleaninfunction()
  
  

  static public void Addinifunction() {
    Addfunction( "ListAllVariables", "void" ) ;
    Addfunction( "ListAllFunctions", "void" ) ;
    Addfunction( "ListVariable", "void" ) ;
    Addfunction( "ListFunction", "void" ) ;
  } // Addinifunction()
  
  
  
  private static boolean Handlefunction( String functionname, String item ) {
    Vector<String> sorting = new Vector<String>() ;
    String temp = "" ;
    
    if ( functionname.equals( "ListAllVariables" ) ) {
      
      for ( int i = 0; i < sgdefinename.size() ; i++ ) {
        Iddata pair = sgdefinename.get( i );
        sorting.add( pair.GetStr() ) ;
      } // for
      
      for ( int i = 0; i < sorting.size() - 1 ; i++ ) {
        for ( int j = 0; j < sorting.size() - i - 1 ; j++ ) {
          if ( sorting.get( j ).compareTo( sorting.get( j + 1 ) ) > 0 ) {
            // 交換元素
            temp = sorting.get( j );
            sorting.set( j, sorting.get( j + 1 ) );
            sorting.set( j + 1, temp );
          } // if
        } // for 
      } // for
      
      for ( int i = 0; i < sorting.size() ; i++ ) {
        System.out.println( sorting.get( i ) );
      } // for
      
    } // if
    else if ( functionname.equals( "ListVariable" ) ) {
      temp = Findtype( item ) ;
      // System.out.println( temp ) ;
      if ( temp.equals( "int" ) || temp.equals( "char" ) || temp.equals( "float" ) ) {
        if ( temp.equals( "int" ) ) System.out.println( "int " + item + " ;" );
        if ( temp.equals( "char" ) ) System.out.println( "char " + item + " ;" );
        if ( temp.equals( "float" ) ) System.out.println( "float " + item + " ;" );
      } // if
      else if ( temp.equals( "intarray" ) || temp.equals( "chararray" ) || temp.equals( "floatarray" ) ) {
        if ( temp.equals( "intarray" ) ) 
          System.out.println( "int " + item + "[ " + Getsize( item ) + " ] ;" ) ;
        if ( temp.equals( "chararray" ) ) 
          System.out.println( "char " + item + "[ " + Getsize( item ) + " ] ;" );
        if ( temp.equals( "floatarray" ) ) 
          System.out.println( "float " + item + "[ " + Getsize( item ) + " ] ;" );
      } // else if
      else if ( temp == "string" ) System.out.println( "string " + item + " ;" );
      else if ( temp == "stringarray" ) 
        System.out.println( "string " + item + "[ " + Getsize( item ) + " ] ;" );

    } // if
    else if ( functionname.equals( "ListAllFunctions" ) ) {
      Displayall();
    } // if
    else if ( functionname.equals( "ListFunction" ) ) {
      Prettyprint( item, Gettype( item ), Getinput( item ), Getitem( item ) ) ; 
    } // if
    else {
      
    } // else 
      
    return false ;
  } // Handlefunction()
  
  
  private static void Prettyprint( String name, String type, ArrayList<String> input, 
                                   ArrayList<String> item ) {    
    System.out.print( type + " " + name ) ;
    String space = "" ;
    boolean first = true ;
    
    for ( int i = 0 ; i < input.size() ; i++ ) {
      
      if ( input.get( i ).charAt( 0 ) == '[' ) {
        System.out.print( "[ " + input.get( i ).substring( 1, input.get( i ).length() ) + " ]" ) ;  
      } // if
      else {
        if ( input.get( i ).charAt( 0 ) == '(' || input.get( i ).charAt( 0 ) == '[' ) 
          System.out.print( input.get( i ) ) ;
        else System.out.print( " " + input.get( i ) ) ;
      } // else
    } // for
    
    System.out.print( " " ) ;
    // ==========================================================
    
    for ( int i = 0 ; i < item.size() ; i++ ) {
      
      if ( item.get( i ).equals( "}" ) )  space = space.substring( 2, space.length() ) ;
      
      if ( first ) System.out.print( space ) ;
      
      if ( !first && item.get( i ).charAt( 0 ) != '[' && item.get( i ) != "++" &&  // error
           item.get( i ) != "--" && ! ( item.get( i ) == "(" &&  Checkfunctionexist( item.get( i-1 ) ) 
                                        || item.get( i-1 ) == "if" || item.get( i-1 ) == "while" ) )  {
        System.out.print( " " ) ;
      } // if
     
      
      if ( item.get( i ).charAt( 0 ) == '[' ) {
        System.out.print( "[ " + item.get( i ).substring( 1, item.get( i ).length() ) + " ]" ) ;  
      } // if
      else System.out.print( item.get( i ) ) ;
      
      first = false ;
      
      if ( item.get( i ).equals( "{" ) ) {
        space = space + "  " ;
        System.out.println( "" ) ;
        first = true ;
      } // if
      
      if ( item.get( i ).equals( "}" ) ) {
        System.out.println( "" ) ;
        first = true ;
      } // if
      
      if ( item.get( i ).equals( ";" ) ) {
        System.out.println( "" ) ;
        first = true ;
      } // if
      
    } // for
    
  } // Prettyprint()
  
  
  private static String Calculatestring( ListNode start, ListNode end ) { // pb
    String ans = "" ;
    
    for ( ListNode current = start; current != end ; current = current.mnext ) { 
      // System.out.println( current.mitem + current.mtype );
      if ( current.mtype == 10 ) ans = ans + current.mitem.substring( 1, current.mitem.length() ) ; 
      if ( current.mtype == 3 ) {
        if ( Checkexist( current.mitem ) ) {

          ans = ans + Getstring( current.mitem ) ;       // 可以拿array......
        } // if
        else {
          System.out.println( "> Line " + sreadline + " : undefined identifier : '" + 
                              current.mitem + "'" ) ;
          return "error" ;
        } // else
      } // if
       
        
    } // for
    
    return ans ;
  } // Calculatestring()

  
  private static String Calculateans( ListNode start, ListNode end ) { //
    
    // for ( ListNode current = start; current != end ; current = current.mnext ) 
    // System.out.println( current.mitem ) ;

    if ( start == end ) return "0" ;        
    
    MStack cal = new MStack();
    
    for ( ListNode current = start; current != end ; current = current.mnext ) { // 處理括號
      if ( current.mitem.equals( ")" )  ) {
        MStack temp = new MStack();
        while ( cal.Size() != 0 && !cal.Peek().equals( "(" ) ) { 
          temp.Push( cal.Pop() );
        } // while
        
        cal.Pop();
        cal.Push( Calculate( temp ) );
      } // if
      else {
        if ( current.mtype == 3 ) { // error 添加
          if ( Checkfunctionexist( current.mitem ) ) {
            while ( !current.mitem.equals( ")" ) ) current = current.mnext ;
            cal.Push( "0" ) ;
          } // if
          else if ( Checkexist( current.mitem ) )
            cal.Push( String.valueOf( Getnumber( current.mitem ) ) );
          else {
            System.out.println( "> Line " + sreadline + " : undefined identifier : '" + 
                                current.mitem + "'" ) ;
            return "error" ;
          } // else
        } // if
        else if ( current.mtype != 11 ) cal.Push( current.mitem ); 
      } // else
    } // for
      
    if ( cal.Size() == 1 ) { // 计算完括号里面的，如果栈中只剩下一个元素
      return cal.Pop() ;
    } // if
    else { // 计算完括号里面的，栈中还有多个元素，继续计算
    // 但是此时栈中的元素是正序排列的，需要变成倒序再计算
      MStack last = new MStack();
      while ( !cal.IsEmpty() ) {
        last.Push( cal.Pop() );
      } // while
      
      // System.out.print( "> " );
      
      String checktype = Calculate( last ) ;

      
      if ( checktype.equals( "true" )  ) {
        return "true" ;
      } // if
      else if ( checktype.equals( "false" ) ) {
        return "false" ;
      } // else if
      else {
        float number = Float.parseFloat( checktype );
      
        if ( number == ( int ) number ) {
          int i = ( int ) number ;
          return String.valueOf( i ); // 如果是整數，轉型為int並印出
        } // if
        else {
          return String.valueOf( number ) ;
        } // else
      } // else
    } // else
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
      if ( next == ">" || next == ">=" || next == "<" || next == "<=" || next == "==" ) {
        i++; 
        bigsmall = true ;
        prenum = result ;
        result = Float.parseFloat( arrayList.get( i ) ) ;
        op = next ;
      } // if
      else if ( next.equals( "+" ) ) {
        i++; 
        // System.out.println(arrayList.get( i )) ;
        result += Float.parseFloat( arrayList.get( i ) );
      } // if
      else if ( next.equals( "-" ) ) {
        i++;
        result -= Float.parseFloat( arrayList.get( i ) );
      } // if   
      else {
        // System.out.println(arrayList.get( i )) ;
        result += Float.parseFloat( arrayList.get( i ) );
      } // else
    } // for
    
    
     // System.out.println(prenum);
     // System.out.println(result);
    if ( bigsmall ) {
      if ( op == ">" ) {
        if ( prenum > result ) return "true" ;
        else return "false" ;
      } // if
      else if ( op == "<=" ) {
        if ( prenum <= result ) return "true" ;
        else return "false" ;
      } // else if
      else if ( op == "<" ) {
        if ( prenum < result ) return "true" ;
        else return "false" ;
      } // else if
      else if ( op == ">=" ) {
        if ( prenum >= result ) return "true" ;
        else return "false" ;
      } // else if
      else if ( op == "==" ) {
        if ( prenum == result ) return "true" ;
        else return "false" ;
      } // else if
      // else if ( op == "<=" ) {       
      // } // else if 
    } // if
    
    
    return Float.toString( result );
    
  } // Calculate()
  
  
  private static boolean Checktrue( ListNode start, ListNode end ) {
    ListNode current = start.mnext ;
    ListNode temp = start.mnext ;
    boolean ans = false ;

    // for ( ListNode tmp = start; tmp != end ; tmp = tmp.mnext ) 
      // System.out.println( tmp.mitem ) ;

    // System.out.println( "" ) ;
    
    while ( current != end ) {
      if ( current.mtype == 1 || current.mtype == 2 || current.mtype == 3 ) {
        
        if ( Calculateans( current, end ).equals( "true" ) ) return true ;
        else return false ;
      } // if
      
      
      if ( current.mitem.equals( "(" ) ) {
        int count = 0 ;
        temp = current ;

        while ( !current.mitem.equals( ")" ) || count != 1 ) {
          if ( current.mitem.equals( "(" ) ) count++ ;
          if ( current.mitem.equals( ")" ) )  count-- ; 
          
          current = current.mnext ;
        } // while
        
        ans = Checktrue( temp, current ) ;
      } // if
        
      if ( current.mitem.equals( "&&" ) )  {
        // System.out.println("213") ;
        temp = current.mnext ;
        current = current.mnext ;
        while ( !current.mitem.equals( ")" ) ) current = current.mnext ;
        
        if ( ans && Checktrue( temp, current ) ) return true;
        else return false ;
      } // if
      
      if ( current.mitem.equals( "||" ) )  {
        temp = current.mnext;
        current = current.mnext ;
        while ( !current.mitem.equals( ")" ) ) current = current.mnext ;
        
        
        if ( ans || Checktrue( temp, current ) ) return true;
        else return false ;
      } // if
        
      current = current.mnext ;  
    } // while
    
    return ans ;
  } // Checktrue()
  
  
  public static boolean Dealwithprocess( ListNode start, ListNode end, 
                                         boolean notininquotation, boolean needprint ) {
    ListNode temp ;
    ListNode temp2 ; // use for check
    boolean checkhasext = false ;
    String tempstr ;
    float tempfloat = 0 ; 
    boolean isfunction = false ;
    
    // for ( ListNode tmp = start; tmp != end ; tmp = tmp.mnext ) 
      // System.out.println( tmp.mitem ) ;
     
    if ( start == null ) return false ;
    // System.out.println( " " ) ;
    if ( notininquotation && needprint && sifhasprint == 0 ) System.out.print( "> " ) ;
    
    ListNode startcopy = start ;
    
    if ( start.mitem.equals( ";" ) ) {
      if ( notininquotation && needprint ) System.out.println( "Statement executed ..." ) ;
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
    for ( ListNode current = start; !finish && current != null && current != end  ; 
          current = current.mnext ) {
      // System.out.println( current.mitem ) ;
      // System.out.println( end.mitem + "000" ) ;
      
      
      if ( current.mitem.equals( "{" ) ) { // many command
        current = current.mnext ;
        temp = current ;
        while ( current != null && current != end ) {
          while ( !temp.mitem.equals( ";" ) && temp.mnext != end ) temp = temp.mnext ;
          Dealwithprocess( current, temp, false, false ) ;
          // System.out.println( " " ) ;
          current = temp.mnext ;
          temp = temp.mnext ;
        } // while
        
        Cleaninfunction() ;
        if ( notininquotation && needprint ) System.out.println( "Statement executed ..." ) ;
        finish = true ;
      } // if
      else if ( current.mitem.equals( "float" ) || current.mitem.equals( "char" ) ||
                current.mitem.equals( "int" ) || current.mitem.equals( "void" ) ) {
       
        
        tempstr = current.mitem ; // type
        current = current.mnext ;
        
        while ( !isfunction && current != null && current != end ) {
          if ( current.mtype == 3 ) {
            checkhasext = Checkexist( current.mitem ) ;
            
            if ( current.mnext.mtype == 11 ) {
              temp = current.mnext ;
              String size = temp.mitem.substring( 1, temp.mitem.length() ).trim() ;
              if ( Isallnum( size ) ) {
                if ( notininquotation )
                  AddData( current.mitem, 0, tempstr + "array",
                           Integer.parseInt( size ), "", false ) ;
                else
                  AddData( current.mitem, 0, tempstr + "array",
                           Integer.parseInt( size ), "", true ) ;
              } // if
              else { // 該找   偷懶
                if ( notininquotation )
                  AddData( current.mitem, 0, tempstr + "array",
                           5, "", false ) ;
                else
                  AddData( current.mitem, 0, tempstr + "array",
                           5, "", true ) ;
              } // else 
            } // if
            else if ( current.mnext.mitem.equals( "," ) ) {
              if ( notininquotation )
                AddData( current.mitem, 0, tempstr, 1, "", false );
              else  
                AddData( current.mitem, 0, tempstr, 1, "", true );
            } // else if
            else if ( current.mnext.mitem.equals( "(" ) ) { // add function
              // System.out.println( "123" ) ;
              
              if ( Checkfunctionexist( current.mitem ) ) { 
                Removefunction( current.mitem ) ;
                checkhasext = true ;
              } // if
              
              
              Addfunction( current.mitem, tempstr );
              
              temp = current.mnext ;
              while ( !temp.mitem.equals( ")" ) ) {
                Addfunctioninput( current.mitem, temp.mitem ) ;
                // System.out.println( temp.mitem ) ;
                temp = temp.mnext ;
              } // while
              
              Addfunctioninput( current.mitem, temp.mitem ) ;
              
              int checker = 0 ; // count big comma
              temp2 = temp.mnext ; // {
              temp = temp.mnext ;
              while ( !temp2.mitem.equals( "}" ) || checker != 1 ) {
                // System.out.println( checker ) ;
                if ( temp2.mitem.equals( "}" ) ) checker--;
                if ( temp2.mitem.equals( "{" ) ) checker++;          
                temp2 = temp2.mnext ;
              } // while
              
              /*
              while ( temp != temp2 ) {
                System.out.println( temp.mitem ) ;
                temp = temp.mnext ;
              }
              */
            
              if ( Dealwithprocess( temp, temp2, false, false ) ) {
                
                Addfunctionitem( current.mitem, temp.mitem ) ;
                
                temp = temp.mnext ; // {
                temp2 = temp.mnext ;
                    
                while ( !temp.mitem.equals( "}" ) || checker != 1 ) {
                  if ( temp.mitem.equals( "}" ) ) checker--;
                  if ( temp.mitem.equals( "{" ) ) checker++;     
                  Addfunctionitem( current.mitem, temp.mitem ) ;
                  temp = temp.mnext ;
                } // while
                
                Addfunctionitem( current.mitem, temp.mitem ) ;
                // System.out.println( "123" ) ;
                current.mitem = current.mitem + "()" ;
                isfunction = true ;
              } // if
              else {
                Removefunction( current.mitem ) ;
              } // else 
              
              finish = true ; 
            } // else if
            else {
              if ( notininquotation )
                AddData( current.mitem, Float.parseFloat( Calculateans( current.mnext, end ) ), 
                         tempstr, 1, "", false );
              else  
                AddData( current.mitem, Float.parseFloat( Calculateans( current.mnext, end ) ),
                         tempstr, 1, "", true );
            } // else
            
            
            if ( notininquotation && needprint && !checkhasext ) 
              System.out.println( "Definition of " + current.mitem + " entered ..." );
            if ( notininquotation && needprint && checkhasext ) 
              System.out.println( "New definition of " + current.mitem + " entered ..." );
            if ( current.mnext != null && current.mnext.mtype == 11 ) current = current.mnext ;
            
          } // if
          
          current = current.mnext ;
        } // while
        
        finish = true ;
      } // else if
      else if ( current.mitem.equals( "string" ) ) { 
        current = current.mnext ;
        
        
        
        
        while ( current != end ) {
          if ( current.mtype == 3 ) {
            checkhasext = Checkexist( current.mitem ) ;
            
            if ( current.mnext.mtype == 11 ) {
              temp = current.mnext ;
              if ( notininquotation )
                AddData( current.mitem, 0, "stringarray",
                         Integer.parseInt( temp.mitem.substring( 1, temp.mitem.length() ) ), "", false ) ;
              else
                AddData( current.mitem, 0, "stringarray",
                         Integer.parseInt( temp.mitem.substring( 1, temp.mitem.length() ) ), "", true ) ;
            } // if
            else if ( current.mnext.mitem.equals( "," ) ) {
              if ( notininquotation )
                AddData( current.mitem, 0, "string", 1, "", false ) ; 
              else  
                AddData( current.mitem, 0, "string", 1, "", true ) ; 
            } // else if
            else {
              if ( notininquotation )
                AddData( current.mitem, 0, "string", 1, Calculatestring( current.mnext, end ), false ); 
              else  
                AddData( current.mitem, 0, "string", 1, Calculatestring( current.mnext, end ), true ); 
            } // else
                   

            if ( notininquotation && !checkhasext ) 
              System.out.println( "Definition of " + current.mitem + " entered ..." );
            if ( notininquotation && checkhasext ) 
              System.out.println( "New definition of " + current.mitem + " entered ..." );
            if ( current.mnext.mtype == 11 ) current = current.mnext ;
            
          } // if
          
          current = current.mnext ;
        } // while
        
        finish = true ;
        // System.out.println( "in" );
      } // else if
      else if ( current.mitem.equals( "cout" ) || current.mitem.equals( "cin" ) ) {
        finish = true ;
        
        if ( notininquotation && needprint ) 
          System.out.println( "Statement executed ..." ) ;
      } // else if
      else if ( current.mitem.equals( "if" ) ) {
        int counter = 0 ;
        temp = current ;
        
        while ( !temp.mitem.equals( ")" ) || counter != 1 ) {
          
          if ( temp.mitem.equals( "(" ) ) counter++ ;
          if ( temp.mitem.equals( ")" ) )  counter-- ; 
          
          temp = temp.mnext ;
        } // while
        
        
        if ( Checktrue( current.mnext, temp ) ) {
          current = temp.mnext ;
          temp = temp.mnext ;
          while ( !temp.mitem.equals( ";" ) ) temp = temp.mnext ;
          spreif[slayer] = true ;
          slayer++ ;
          
          Dealwithprocess( current, temp, true, false ) ;
          
          if ( notininquotation && needprint ) System.out.println( "Statement executed ..." ) ;
          finish = true ;
        } // if
        else {
          spreif[slayer] = false ;
          finish = true ;
        } // else
        
      } // else if
      else if ( current.mitem.equals( "else" ) ) {
        // sifhasprint = 1
        // slayer = 0

        if ( slayer == sifhasprint && !spreif[slayer]  )  {
          // System.out.println( "123" ) ;
          
          if ( current.mnext.mitem.equals( "if" ) ) {
            Dealwithprocess( current.mnext, end, true, true ) ;         
          } // if
          else {
            Dealwithprocess( current.mnext, end, true, false ) ;
            if ( notininquotation && needprint && sifhasprint == 0 ) 
              System.out.println( "Statement executed ..." ) ;
          } // else 
          
          finish = true ;
          if ( slayer > 0 ) slayer--;
        } // if
        else {
          spreif[slayer] = false ;
          finish = true ;
        } // else
        
      } // else if
      else if ( current.mitem.equals( "while" ) ) {
        int counter = 0 ;
        temp = current ;
        
        while ( !temp.mitem.equals( ")" ) || counter != 1 ) {
          if ( temp.mitem.equals( "(" ) ) counter++ ;
          if ( temp.mitem.equals( ")" ) )  counter-- ; 
          
          temp = temp.mnext ;
        } // while
        
        // System.out.println( current.mitem ) ;
        if ( Checktrue( current.mnext, temp )  ) {
          current = temp.mnext ;  
          // System.out.println(current.mitem ) ;

          Dealwithprocess( current.mnext, end, true, false ) ;
        } // if
        
        if ( notininquotation && needprint ) System.out.println( "Statement executed ..." ) ;
        finish = true ;
      } // else if
      else if ( current.mtype == 1 || current.mtype == 2 ) {


        // AddData( current.mitem, Calculateans( current.mnext, end ), "int" ) ; 
        finish = true ;

        
        if ( notininquotation && needprint ) 
          System.out.println( "Statement executed ..." ) ;
        
      } // else if
      else if ( current.mitem.equals( "return" ) ) {
        finish = true ;
      } // else if 
      else if ( current.mtype == 3 ) { // 
        // System.out.println( current.mitem + current.mnext.mitem ) ;
        if ( !Checkexist( current.mitem ) && !Checkfunctionexist( current.mitem ) ) {
          System.out.println( "Line " + sreadline + " : undefined identifier : '" + current.mitem + "'" ) ;
          finish = true ;
          sreadline = 0 ;
        } // if
        else {  
          temp = current ;
          current = current.mnext ;
          if ( current.mitem.equals( "=" ) ) {
            // System.out.println( Findtype( temp.mitem ) ) ;
            if ( Findtype( temp.mitem ) == "string" ) {
              tempstr = Calculatestring( current.mnext, end ) ;
              if ( !tempstr.equals( "error" ) ) AddData( temp.mitem, 0, "string", 1, tempstr, false ) ; 
                // System.out.println( tempstr ) ;
            } // if
            else if ( Findtype( temp.mitem ) == "int" || Findtype( temp.mitem ) == "float" ) {
              
              tempfloat = Float.parseFloat( Calculateans( current.mnext, end ) ) ;
              if ( tempfloat != -999 ) AddData( temp.mitem, tempfloat, "int", 1, "", false ) ; 
            } // else if
            
            finish = true;
          } // if
          else if ( current.mitem.equals( "(" ) ) {
            if ( current.mnext != null ) current = current.mnext ;
            if ( current.mitem.equals( ")" ) ) {
              if ( current.mnext != null ) current = current.mnext ;
              if ( current.mitem.equals( ";" ) ) Handlefunction( temp.mitem, "" ) ;
            } // if
            else if ( current.mtype == 10 ) {
              // System.out.println( temp.mitem +  current.mitem.substring( 1, current.mitem.length() )) ;
              Handlefunction( temp.mitem, current.mitem.substring( 1, current.mitem.length() ) ) ;
              // System.out.println( "in" ) ;
            } // else if 
            else {
              
            } // else 
          } // else if 
          else if ( current.mitem.equals( "++" ) ) {
            AddData( temp.mitem, Getnumber( temp.mitem ) + 1, Findtype( temp.mitem ), 1, "", false ) ; 
          } // else if
          else if ( current.mitem.equals( "--" ) ) {
            AddData( temp.mitem, Getnumber( temp.mitem ) - 1, Findtype( temp.mitem ), 1, "", false ) ; 
          } // else if          
          
          if ( notininquotation && needprint ) 
            System.out.println( "Statement executed ..." ) ;
        } // else
        
        finish = true ;
      } // else if
      // System.out.println( "0000" ) ;
    } // for
    
    // System.out.println( "0000" ) ;
    return true ;
  } // Dealwithprocess()
  
  // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
  public static boolean justpass( ListNode start, ListNode end, 
                                         boolean notininquotation, boolean needprint ) {
    ListNode temp ;
    ListNode temp2 ; // use for check
    boolean checkhasext = false ;
    String tempstr ;
    float tempfloat = 0 ; 
    boolean isfunction = false ;
    
    // for ( ListNode tmp = start; tmp != end ; tmp = tmp.mnext ) 
      // System.out.println( tmp.mitem ) ;
     
    if ( start == null ) return false ;
    // System.out.println( " " ) ;
    if ( notininquotation && needprint && sifhasprint == 0 ) System.out.print( "> " ) ;
    
    ListNode startcopy = start ;
    
    if ( start.mitem.equals( ";" ) ) {
      if ( notininquotation && needprint ) System.out.println( "Statement executed ..." ) ;
      return true ;
    } // if
    
    if ( startcopy.mitem.equals( "Done" ) ) {
      if ( startcopy.mnext != null ) startcopy = startcopy.mnext ;
      if ( startcopy.mitem.equals( "(" ) ) {
        if ( startcopy.mnext != null ) startcopy = startcopy.mnext ;
        if ( startcopy.mitem.equals( ")" ) ) {
          if ( startcopy.mnext != null ) startcopy = startcopy.mnext ;
          if ( startcopy.mitem.equals( ";" ) ) return false ;
        }
      } // if
    } // if
    // System.out.println( "123" ) ;
    
    boolean finish = false ;
    for ( ListNode current = start; !finish && current != null && current != end  ; 
          current = current.mnext ) {
      // System.out.println( current.mitem ) ;
      // System.out.println( end.mitem + "000" ) ;
      
      
      if ( current.mitem.equals( "{" ) ) { // many command
        current = current.mnext ;
        temp = current ;
        while ( current != null && current != end ) {
          while ( !temp.mitem.equals( ";" ) && temp.mnext != end ) temp = temp.mnext ;
          justpass( current, temp, false, false ) ;
          // System.out.println( " " ) ;
          current = temp.mnext ;
          temp = temp.mnext ;
        } // while
        
        Cleaninfunction() ;
        if ( notininquotation && needprint ) System.out.println( "Statement executed ..." ) ;
        finish = true ;
      } // if
      else if ( current.mtype == 3 || current.mtype == 4 || current.mtype == 5 || current.mtype == 6 ||
                current.mtype == 7 || current.mtype == 8 ) { // define
              
        tempstr = current.mitem ; // type
        current = current.mnext ;
        
        while ( !isfunction && current != null && current != end ) {
          if ( current.mtype == 3 ) {
            checkhasext = Checkexist( current.mitem ) ;
            
            if ( current.mnext.mtype == 11 ) {
              temp = current.mnext ;
              String size = temp.mitem.substring( 1, temp.mitem.length() ).trim() ;
              if ( Isallnum( size ) ) {
                if ( notininquotation )
                  AddData( current.mitem, 0, tempstr + "array",
                           Integer.parseInt( size ), "", false ) ;
                else
                  AddData( current.mitem, 0, tempstr + "array",
                           Integer.parseInt( size ), "", true ) ;
              } // if
              else { // 該找   偷懶
                if ( notininquotation )
                  AddData( current.mitem, 0, tempstr + "array",
                           5, "", false ) ;
                else
                  AddData( current.mitem, 0, tempstr + "array",
                           5, "", true ) ;
              } // else 
            } // if
            else if ( current.mnext.mitem.equals( "," ) ) {
              if ( notininquotation )
                AddData( current.mitem, 0, tempstr, 1, "", false );
              else  
                AddData( current.mitem, 0, tempstr, 1, "", true );
            } // else if
            else if ( current.mnext.mitem.equals( "(" ) ) { // add function
              // System.out.println( "123" ) ;
              
              if ( Checkfunctionexist( current.mitem ) ) { 
                Removefunction( current.mitem ) ;
                checkhasext = true ;
              } // if
              
              
              Addfunction( current.mitem, tempstr );
              
              temp = current.mnext ;
              while ( !temp.mitem.equals( ")" ) ) {
                Addfunctioninput( current.mitem, temp.mitem ) ;
                // System.out.println( temp.mitem ) ;
                temp = temp.mnext ;
              } // while
              
              Addfunctioninput( current.mitem, temp.mitem ) ;
              
              int checker = 0 ; // count big comma
              temp2 = temp.mnext ; // {
              temp = temp.mnext ;
              while ( !temp2.mitem.equals( "}" ) || checker != 1 ) {
                // System.out.println( checker ) ;
                if ( temp2.mitem.equals( "}" ) ) checker--;
                if ( temp2.mitem.equals( "{" ) ) checker++;          
                temp2 = temp2.mnext ;
              } // while
              
              /*
              while ( temp != temp2 ) {
                System.out.println( temp.mitem ) ;
                temp = temp.mnext ;
              }
              */
            
              if ( justpass( temp, temp2, false, false ) ) {
                
                Addfunctionitem( current.mitem, temp.mitem ) ;
                
                temp = temp.mnext ; // {
                temp2 = temp.mnext ;
                    
                while ( !temp.mitem.equals( "}" ) || checker != 1 ) {
                  if ( temp.mitem.equals( "}" ) ) checker--;
                  if ( temp.mitem.equals( "{" ) ) checker++;     
                  Addfunctionitem( current.mitem, temp.mitem ) ;
                  temp = temp.mnext ;
                } // while
                
                Addfunctionitem( current.mitem, temp.mitem ) ;
                // System.out.println( "123" ) ;
                current.mitem = current.mitem + "()" ;
                isfunction = true ;
              } // if
              else {
                Removefunction( current.mitem ) ;
              } // else 
              
              finish = true ; 
            } // else if
            else {
              if ( notininquotation )
                AddData( current.mitem, Float.parseFloat( Calculateans( current.mnext, end ) ), 
                         tempstr, 1, "", false );
              else  
                AddData( current.mitem, Float.parseFloat( Calculateans( current.mnext, end ) ),
                         tempstr, 1, "", true );
            } // else
            
            
            if ( notininquotation && needprint && !checkhasext ) 
              System.out.println( "Definition of " + current.mitem + " entered ..." );
            if ( notininquotation && needprint && checkhasext ) 
              System.out.println( "New definition of " + current.mitem + " entered ..." );
            if ( current.mnext != null && current.mnext.mtype == 11 ) current = current.mnext ;
            
          } // if
          
          current = current.mnext ;
        } // while
        
        finish = true ;
      } // else if
      else if ( current.mitem.equals( "cout" ) ) {
        current = current.mnext ;
        checkhasext = true ;
        boolean specialcheck = true ;
        
        while ( current != end && checkhasext ) {
          if ( specialcheck && current.mitem.equals( "<<" ) ) {
            specialcheck = false ;
            current = current.mnext ;          
          } // if
          else if ( specialcheck && !current.mitem.equals( "<<" ) ) { // error
            System.out.println( "Line " + sreadline + " : undefined identifier : '" + 
                                current.mitem + "'" ) ;

            return false ;
          } // else if
          else {
            if ( !Checkexist( current.mitem ) && current.mtype != 10 ) {
              System.out.println( "Line " + sreadline + " : undefined identifier : '" + 
                                  current.mitem + "'" ) ;

              return false ;
            } // if
            else {
              if ( current.mnext != null ) {
                temp = current.mnext ;  
                if ( temp.mitem.charAt( 0 ) == '[' ) {
                  current = current.mnext ;
                } // if
              } // if
            } // else 
            
            specialcheck = true ;
            current = current.mnext ;  
          } // else
        } // while
        
        
        finish = true ;
        
        if ( notininquotation && needprint && checkhasext ) 
          System.out.println( "Statement executed ..." ) ;
        
      } // else if
      else if ( current.mitem.equals( "cin" ) ) {
        current = current.mnext ;
        checkhasext = true ;
        boolean specialcheck = true ;
        
        while ( current != end && checkhasext ) {
          if ( specialcheck && current.mitem.equals( ">>" ) ) {
            specialcheck = false ;
            current = current.mnext ;          
          } // if
          else if ( specialcheck && !current.mitem.equals( ">>" ) ) { // error
            System.out.println( "Line " + sreadline + " : undefined identifier : '" + 
                                current.mitem + "'" ) ;

            return false ;

          } // else if
          else {
            if ( !Checkexist( current.mitem ) && current.mtype != 10 ) {
              System.out.println( "Line " + sreadline + " : undefined identifier : '" + 
                                  current.mitem + "'" ) ;

              return false ;
            } // if
            else {
              if ( current.mnext != null ) {
                temp = current.mnext ;  
                if ( temp.mitem.charAt( 0 ) == '[' ) {
                  current = current.mnext ;
                } // if
              } // if
            } // else 
            
            specialcheck = true ;
            current = current.mnext ;  
          } // else
        } // while
        
        
        finish = true ;
        
        if ( notininquotation && needprint && checkhasext ) 
          System.out.println( "Statement executed ..." ) ;
        
      } // else if
      else if ( current.mitem.equals( "if" ) ) {
        int counter = 0 ;
        temp = current ;
        
        while ( !temp.mitem.equals( ")" ) || counter != 1 ) {
          
          if ( temp.mitem.equals( "(" ) ) counter++ ;
          if ( temp.mitem.equals( ")" ) )  counter-- ; 
          
          temp = temp.mnext ;
        } // while
        
        
        if ( Checktrue( current.mnext, temp ) ) {
          current = temp.mnext ;
          temp = temp.mnext ;
          while ( !temp.mitem.equals( ";" ) ) temp = temp.mnext ;
          spreif[slayer] = true ;
          slayer++ ;
          
          justpass( current, temp, true, false ) ;
          
          if ( notininquotation && needprint ) System.out.println( "Statement executed ..." ) ;
          finish = true ;
        } // if
        else {
          spreif[slayer] = false ;
          finish = true ;
        } // else
        
      } // else if
      else if ( current.mitem.equals( "else" ) ) {
        // sifhasprint = 1
        // slayer = 0

        if ( slayer == sifhasprint && !spreif[slayer]  )  {
          // System.out.println( "123" ) ;
          
          if ( current.mnext.mitem.equals( "if" ) ) {
            justpass( current.mnext, end, true, true ) ;         
          } // if
          else {
            justpass( current.mnext, end, true, false ) ;
            if ( notininquotation && needprint && sifhasprint == 0 ) 
              System.out.println( "Statement executed ..." ) ;
          } // else 
          
          finish = true ;
          if ( slayer > 0 ) slayer--;
        } // if
        else {
          spreif[slayer] = false ;
          finish = true ;
        } // else
        
      } // else if
      else if ( current.mitem.equals( "while" ) ) {
        int counter = 0 ;
        temp = current ;
        
        while ( !temp.mitem.equals( ")" ) || counter != 1 ) {
          if ( temp.mitem.equals( "(" ) ) counter++ ;
          if ( temp.mitem.equals( ")" ) )  counter-- ; 
          
          temp = temp.mnext ;
        } // while
        
        // System.out.println( current.mitem ) ;
        if ( Checktrue( current.mnext, temp )  ) {
          current = temp.mnext ;  
          // System.out.println(current.mitem ) ;

          justpass( current.mnext, end, true, false ) ;
        } // if
        
        if ( notininquotation && needprint ) System.out.println( "Statement executed ..." ) ;
        finish = true ;
      } // else if
      else if ( current.mtype == 1 || current.mtype == 2 ) {


        // AddData( current.mitem, Calculateans( current.mnext, end ), "int" ) ; 
        finish = true ;

        
        if ( notininquotation && needprint ) 
          System.out.println( "Statement executed ..." ) ;
        
      } // else if
      else if ( current.mitem.equals( "return" ) ) {
        finish = true ;
      } // else if 
      else if ( current.mtype == 3 ) { // 
        // System.out.println( current.mitem + current.mnext.mitem ) ;
        if ( !Checkexist( current.mitem ) && !Checkfunctionexist( current.mitem ) ) {
          System.out.println( "Line " + sreadline + " : undefined identifier : '" + current.mitem + "'" ) ;
          finish = true ;
          sreadline = 0 ;
        } // if
        else {  
          temp = current ;
          current = current.mnext ;
          if ( current.mitem.equals( "=" ) ) {
            // System.out.println( Findtype( temp.mitem ) ) ;
            if ( Findtype( temp.mitem ) == "string" ) {
              tempstr = Calculatestring( current.mnext, end ) ;
              if ( !tempstr.equals( "error" ) ) AddData( temp.mitem, 0, "string", 1, tempstr, false ) ; 
                // System.out.println( tempstr ) ;
            } // if
            else if ( Findtype( temp.mitem ) == "int" || Findtype( temp.mitem ) == "float" ) {
              
              tempfloat = Float.parseFloat( Calculateans( current.mnext, end ) ) ;
              if ( tempfloat != -999 ) AddData( temp.mitem, tempfloat, "int", 1, "", false ) ; 
            } // else if
            
            finish = true;
          } // if
          else if ( current.mitem.equals( "(" ) ) {
            if ( current.mnext != null ) current = current.mnext ;
            if ( current.mitem.equals( ")" ) ) {
              if ( current.mnext != null ) current = current.mnext ;
              if ( current.mitem.equals( ";" ) ) Handlefunction( temp.mitem, "" ) ;
            } // if
            else if ( current.mtype == 10 ) {
              // System.out.println( temp.mitem +  current.mitem.substring( 1, current.mitem.length() )) ;
              Handlefunction( temp.mitem, current.mitem.substring( 1, current.mitem.length() ) ) ;
              // System.out.println( "in" ) ;
            } // else if 
            else {
              
            } // else 
          } // else if 
          else if ( current.mitem.equals( "++" ) ) {
            AddData( temp.mitem, Getnumber( temp.mitem ) + 1, Findtype( temp.mitem ), 1, "", false ) ; 
          } // else if
          else if ( current.mitem.equals( "--" ) ) {
            AddData( temp.mitem, Getnumber( temp.mitem ) - 1, Findtype( temp.mitem ), 1, "", false ) ; 
          } // else if          
          
          if ( notininquotation && needprint ) 
            System.out.println( "Statement executed ..." ) ;
        } // else
        
        finish = true ;
      } // else if
      // System.out.println( "0000" ) ;
    } // for
    
    // System.out.println( "0000" ) ;
    return true ;
  } // justpass()
  
  
  
  public static void main( String[] args ) {
    System.out.println( "Our-C running ..." );
  
    int testnum = scanner.nextInt();
    scanner.nextLine();

    if ( testnum != 0 ) {
      Addinifunction() ;
      Handlegrammer() ;
      ListNode end = scommandhead ;
      while ( end.mnext != null ) end = end.mnext ;
      while ( justpass( scommandhead, end, true, true ) ) {
        scommandhead = null ;
        Handlegrammer() ;
        end = scommandhead ;
        while ( end != null && end.mnext != null ) end = end.mnext ;
      } // while
    } // if
    
    scanner.close();  
    System.out.println( "Our-C exited ..." );
  } // main()
} // class Main()
