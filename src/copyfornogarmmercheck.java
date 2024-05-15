 /*package PL112_10920131;


1 int
2 float   
3 string   
4 +-
5 operater
6 (
7 )
8 / *
9 := df
10 '"'
11 [
12  other 




VOID = 1, INT = 2, CHAR = 3, FLOAT = 4, STRING = 5, BOOL = 6;
RETURN = 7, IF = 8, ELSE = 9, WHILE = 10, DO = 11;
IDENTIFIER = 12, CONSTANT = 13;
EQ = 14, NEQ = 15, LE = 16, GE = 17, AND = 18, OR = 19;
PP = 20, MM = 21, PLUS = 22, MINUS = 23, MUL = 24, DIV = 25;
LPAREN = 26, RPAREN = 27, LBRACE = 28, RBRACE = 29;
LBRACKET = 30, RBRACKET = 31, SEMICOLON = 32, COMMA = 33;
ASSIGN = 34, NOT = 35;




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
  
  static public int sreadline = 0 ; // wrong line
  
  static public boolean[] spreif = new boolean[5]; // if 是否是對的
  
  static public int sifhasprint = 0 ; // if 有印>
  
  static public boolean sisfirst = true ; // 第一個token
  
  static public boolean sissetcommand = false ;  // 是否為定義句
  
  static public int sreadcomma = 0 ; // 檢查括號
  
  static public int sreadbigcomma = 0 ; // 檢查大括號
  
  static public boolean sinquotation = false ; // 大括號內
  
  static public int slayer = 0 ; // 第幾層if
  
  static public boolean spreisop = false ; // error of op
  
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
  
  static public boolean Checkisoktoken( char c ) {  // 
    if ( c == '(' || c == ')' || c == '[' || c == ']' || c == '{' || c == '}' ) return true ; 
    if ( c == '+' || c == '-' || c == '*' || c == '/' || c == '%' || c == '^' ) return true ; 
    if ( c == '>' || c == '<' || c == '&' || c == '|' || c == '=' || c == '!' ) return true ; 
    if ( c == ';' || c == ',' || c == '?' || c == ':' || c == ' ' || c == '.' ) return true ; 
    if ( c == '"' || c == '\'' ) return true ; 

    if ( IsLetterDigit( c ) ) return true ;
    
    return false ;
  } // Checkisoktoken()
  
  
  static public boolean Checkisallenglish( String token ) {  // 檢查英文
      // 正则表达式匹配字符串仅包含英文大小写字母
    return token.matches( "[a-zA-Z][a-zA-Z0-9]*" );
  } // Checkisallenglish()
  
  public static boolean Isallnum( String str ) {
    return str.matches( "\\d+" );  // "\\d+"匹配一個或多個數字
  } // Isallnum()
  
  public static boolean IsLetterDigit( char c ) {
    return Character.isLetter( c ) || Character.isDigit( c );
  } // IsLetterDigit()
  
  
  static public boolean Addtovector( String item ) {
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
    else if ( item == ";"  ) type = 12 ;
    else type = 13 ; 
    
    // System.out.println( item );
    if ( type == 4 || type == 8 ) spreisop = true ;
    else if ( type != 12 ) spreisop = false ;
    
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
      
    // System.out.println( item + spreisop ) ;
    if ( type == 12 && spreisop == true ) {
      System.out.println( "> Line " + sreadline + " : unexpected token : ';'" ) ;
      return false ;
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

          Cleanall() ;
          Cleaninfunction() ;
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
    spreisop = false ;
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
    spreisop = false ;
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
            Dealwithprocess( spredo, spredoend, false, false ) ;

          
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
          
          int firstchar = i ;
          i++;
          char findend = line.charAt( i );
          while ( findend != ']' ) {
            i++ ;
            findend = line.charAt( i );
          } // while

          if ( noerror ) noerror = Addtovector( line.substring( firstchar, i ) ) ;
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
  
  
  
  
  
  public static void main( String[] args ) {
    System.out.println( "Our-C running ..." );
  
    int testnum = scanner.nextInt();
    scanner.nextLine();

    if ( testnum != 0 ) {
      Addinifunction() ;
      Handlegrammer() ;
      ListNode end = scommandhead ;
      while ( end.mnext != null ) end = end.mnext ;
      while ( Dealwithprocess( scommandhead, end, true, true ) ) {
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
*/