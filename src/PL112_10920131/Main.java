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
  boolean mspecialkeep ;

  // 構造函數
  public Iddata( String str, float num, String type, int size, String item, boolean infunction, 
                 boolean keep ) {
    this.mstr = str;
    this.mnum = num;
    this.mtype = type ;
    this.msize = size ;
    this.mitem = item ;
    this.minfunction = infunction;
    this.mspecialkeep = keep;
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
  
  public boolean Getkeep() {
    return mspecialkeep;
    
  } // Getkeep() 
  
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
  
  static public boolean sprintroad = false ;
  
  static public ArrayList<Iddata> sgdefinename = new ArrayList<Iddata>();
  
  static public ArrayList<Functiondata> sgdefinefunction = new ArrayList<Functiondata>();

  static public ListNode scommandhead ; 
  
  static public ListNode scheckhead  ; // 檢查文法
  
  static public int scopytestnum = 0 ;

  static public int sreadline = 0 ; // wrong line
  
  static public boolean shasend = false ;
  
  static public boolean sisfirst = true ; // 第一個token
  
  static public boolean sissetcommand = false ;  // 是否為定義句
  
  static public int sreadcomma = 0 ; // 檢查括號
  
  static public int sreadbigcomma = 0 ; // 檢查大括號
  
  static public boolean sinquotation = false ; // 大括號內
  
  static public boolean sinsmallquotation = false ; // 小括號內
  
  static public boolean sinif = false ; // if statment
  
  static public int skipstat = 0 ; // if statment skip
  
  static public int sifcount = 0 ; // 計算離開大if
  
  static public boolean sneedretain = false ;
  
  static public boolean sindo = false ; // 進入 do
  
  static public int sdocount = 0 ; // 計算do 結束
  
  static public String sretain = "" ;
  
  static public ListNode spredo ;
  
  static public ListNode spredoend ;
  
  static public ListNode spreif ;
  
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
    // System.out.println( token + "0000" ) ;
    if ( Checkisint( token ) ) return true ;
    if ( Checkisfloat( token ) ) return true ;
    if ( token.charAt( 0 ) == '"' && token.charAt( token.length() - 1 ) == '"' ) return true ;
    if ( token.charAt( 0 ) == '\'' && token.charAt( token.length() - 1 ) == '\'' ) return true ;
    if ( token.equals( "true" ) ) return true ;
    if ( token.equals( "false" ) ) return true ;
    
    return false ;
  } // CheckisConstant()
  
 
  static public boolean Checkisoktoken( char c ) {  // 
    if ( c == '(' || c == ')' || c == '[' || c == ']' || c == '{' || c == '}' ) return true ; 
    if ( c == '+' || c == '-' || c == '*' || c == '/' || c == '%' || c == '^' ) return true ; 
    if ( c == '>' || c == '<' || c == '&' || c == '|' || c == '=' || c == '!' ) return true ; 
    if ( c == ';' || c == ',' || c == '?' || c == ':' || c == ' ' || c == '.' ) return true ; 
    if ( c == '"' || c == '\'' || c == '\t' ) return true ; 

    if ( IsLetterDigit( c ) ) return true ;
    
    return false ;
  } // Checkisoktoken()
  
  
  static public boolean CheckisIdentifier( String token ) {  // 檢查英文
      // 正则表达式匹配字符串仅包含英文大小写字母
    return token.matches( "[a-zA-Z][a-zA-Z0-9]*" );
  } // CheckisIdentifier()
  
  
  public static boolean Isallnum( String str ) {
    return str.matches( "\\d+" );  // "\\d+"匹配一個或多個數字
  } // Isallnum()
  
  
  public static boolean IsLetterDigit( char c ) {
    if ( Character.isLetter( c ) || Character.isDigit( c ) ) return true ;
    else return false ;
  } // IsLetterDigit()
  // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
  // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

  static public boolean Addtovector( String item ) {
    int type = 0 ;
    // System.out.println( item );
    // System.out.println( item + "  " + shasend + sinquotation + sinif + "   " + slineleft );
    // System.out.println( item + "  " + sinquotation + "  " + sreadbigcomma + sneedretain ) ;
    
    boolean onedot = false ;
    for ( int i = 0; item.charAt( 0 ) != '\"' && i < item.length() ; i++ ) {
      // System.out.println( item.charAt( i ) ) ;
      if ( item.charAt( i ) == '.' ) {
        if ( onedot ) {
          int secondDot = item.indexOf( '.', i + 1 ) ; // 找到第三個點的位置
          if ( secondDot == -1 ) secondDot = item.length();
          if ( sreadline != 0 )
            System.out.println( "> Line " + sreadline + " : unexpected token : '" + 
                                item.substring( i, secondDot ) + "'" ) ;
          else System.out.println( "> Line 1 : unexpected token : '" + 
                                   item.substring( i, secondDot ) + "'" ) ;
          return false ;
        } // if
        
        onedot = true  ;
      } // if
    } // for
    
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
    else if ( CheckisConstant( item ) ) type = 2  ; 
    else if ( CheckisIdentifier( item ) ) type = 1 ;
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
      if ( sreadline != 0 )  
        System.out.println( "> Line " + sreadline + " : undefined identifier : '" + item + "'" ) ;
      else System.out.println( "> Line 1 : undefined identifier : '" + item + "'" ) ;
      return false ;
    } // else 
    
    // System.out.println( item + type ) ;
    //   item +  sisfirst  + sinif + "  " + skipstat + "   " + shasend 
    if ( sisfirst && !item.equals( "else" ) && sifcount == 0 && sinif && !sinsmallquotation ) { // 
      // System.out.println( "213" ) ;
      if ( skipstat > 0 ) skipstat-- ;
      if ( skipstat == 0 ) {
        sinif = false ;
        sneedretain = true ; // 保留
        // System.out.println( "123" ) ;
        if ( !sinquotation ) {
          ListNode end = scommandhead ;
          while ( end != null && end.mnext != null ) {
            end = end.mnext ; 
          } // while
          
          // System.out.println( "123" ) ;
          Justpass( scommandhead, end, true, true, false ) ;
          // System.out.println( "321" ) ;

          scommandhead = null ;
          sreadline = 1 ; 
          shasend = false ;
        } // if
      } // if
    } // if
    
    if ( sisfirst && item.equals( "else" ) && sifcount == 0 && sinif ) {
      // System.out.println( "567" ) ;
      if ( skipstat > 0 ) skipstat-- ;
      if ( skipstat == 0 ) sinif = false ;
    } // if
    
    
    if ( !sneedretain ) {
      if ( ( item.equals( "{" ) || item.equals( "}" ) ) && sinif ) { // check if end
        if ( item.equals( "{" ) ) sifcount++ ;
        if ( item.equals( "}" ) ) sifcount-- ;
        if ( skipstat > 0 ) skipstat-- ;
      } // if
          
      if ( ( item.equals( "{" ) || item.equals( "}" ) ) && sindo ) { // check if end
        if ( item.equals( "{" ) ) sdocount++ ;
        if ( item.equals( "}" ) ) sdocount-- ;
      } // if
      
      if ( !sisfirst && ( type == 1 || type == 3 || type == 4 || type == 5 || type == 6 || type == 7 || 
                          type == 8 || type == 9 || type == 10 || type == 11 || type == 12 || type == 13 ) 
           && !sissetcommand ) {
        if ( !Checkexist( item ) && !Checkfunctioncout( item ) && !Checkspecialword( item ) ) {
          if ( sreadline != 0 ) System.out.println( "> Line " + sreadline + 
                                                    " : undefined identifier : '" + item + "'" ) ;
          else System.out.println( "> Line 1 : undefined identifier : '" + item + "'" ) ;
          
  
          Cleanall() ;
          Cleaninfunction() ;
          return false;
        } // if
      } // if
      else if ( sisfirst && ( type == 1 || type == 3 || type == 4 || type == 5 || type == 6 || type == 7 || 
                              type == 8 || type == 9 || type == 10 || type == 11 || type == 12 
                              || type == 13 || item.equals( "true" ) || item.equals( "false" ) ) ) {
        
        sisfirst = false ;
        if ( !Checkspecialword( item ) ) {
          if ( !Checkexist( item ) && !Checkfunctionexist( item ) ) {
            if ( sreadline != 0 ) System.out.println( "> Line " + sreadline + 
                                                      " : undefined identifier : '" + item + "'" ) ;
            else System.out.println( "> Line 1 : undefined identifier : '" + item + "'" ) ;
            return false;
          } // if
        } // if
        else if ( Iscommmandfirst( item ) )  sissetcommand = true ;
        
      } // else if
      else if ( sisfirst ) sisfirst = false ; 
    
    
      // System.out.println( item ) ;
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
      if ( ( type == 15 || type == 47 || type == 48 ) ) spredoend = newNode ;
      if ( type == 9 && !sinif && Checkpreisnotelse( newNode ) ) {
        spreif = newNode ;
      } // if
      
      if ( item.equals( "else" )  ) {
        shasend = false ;
      } // if 
      
      if ( item.equals( "if" )  ) {
        skipstat++ ;
        sinif = true ;
      } // if
   
      if ( item.equals( "do" ) ) {
        sindo = true ;
      } // if
      
      if ( sprintroad ) System.out.println( item + type + "!!!!!!!!!!!!!" ) ;
      
      scheckhead = scommandhead ;
      /*
      for ( ListNode temp5 = scheckhead ; temp5 != null ; temp5 = temp5.mnext ) {
        System.out.println( temp5.mitem ) ;
      } // for
      */
      if ( scheckhead != null && !Checkgrammer() ) {
        if ( sreadline != 0 ) 
          System.out.println( "> Line " + sreadline + " : unexpected token : '" + item + "'" ) ;
        else System.out.println( "> Line 1 : unexpected token : '" + item + "'" ) ;
        Cleaninfunction() ;
        return false ;
      } // if
    } // if
    else {
      if ( sretain.equals( "" ) ) sretain = item ;
      else sretain = sretain + " " + item ;
    } // else 
    
    
    return true ;
  } // Addtovector()
  
  
  static public boolean Checkspecialword( String item ) {
    if ( item.equals( "int" ) ) return true ;
    if ( item.equals( "char" ) ) return true ;
    if ( item.equals( "float" ) ) return true ;
    if ( item.equals( "cin" ) ) return true ;
    if ( item.equals( "cout" ) ) return true ;
    if ( item.equals( "bool" ) ) return true ;
    if ( item.equals( "string" ) ) return true ;
    if ( item.equals( "void" ) ) return true ;
    if ( item.equals( "if" ) ) return true ;
    if ( item.equals( "else" ) ) return true ;
    if ( item.equals( "while" ) ) return true ;
    if ( item.equals( "return" ) ) return true ;
    if ( item.equals( "do" ) ) return true ;
    if ( item.equals( "Done" ) ) return true ;
    if ( item.equals( "true" ) ) return true ;
    if ( item.equals( "false" ) ) return true ;
    
    
    return false ;
  } // Checkspecialword()
  
  
  static public boolean Iscommmandfirst( String item ) {
    if ( item.equals( "int" ) ) return true ;
    if ( item.equals( "char" ) ) return true ;
    if ( item.equals( "float" ) ) return true ;
    if ( item.equals( "string" ) ) return true ;
    if ( item.equals( "bool" ) ) return true ;
    if ( item.equals( "void" ) ) return true ;

    return false ;
  } // Iscommmandfirst()
  
  
  static public void Cleanall() {
    scommandhead = null ;
    scheckhead = null ;
    sreadline = 0 ;
    sisfirst = true ; 
    sissetcommand = false ;
    sreadcomma = 0 ;
    sreadbigcomma = 0 ;
    sinquotation = false ;
    sinsmallquotation = false ;
    skipstat = 0 ;
    sifcount = 0 ;
    sinif = false ;
    sdocount = 0 ;
    sindo = false ;
    sneedretain = false ;
    sretain = "" ;

    
  } // Cleanall()
  
  static public String slineleft = "" ;
  
  static public void Readcommendandstore() {
    shasend = false ;
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
      else return ;
    } // else
    
    
    
    while ( !shasend || sinquotation || sinif ) { // 主要
      // System.out.println(line);
      
      for ( int i = 0; noerror && i < line.length() && ( !shasend || sinquotation || sinif ) ; i++ ) {
        char temp = line.charAt( i ); // 获取位置i的字符
        // System.out.println(line ) ;
        
        if ( !Checkisoktoken( temp ) ) {
          if ( sreadline != 0 )
            System.out.println( "> Line " + sreadline + 
                                " : unrecognized token with first char : '" + temp + "'" ) ;
          else System.out.println( "> Line 1 : unrecognized token with first char : '" + temp + "'" ) ;
          noerror = false ;
        } // if 
        else if ( temp == '\t' ) {
          if ( save != "" ) { 
            noerror = Addtovector( save ) ;
            save = "" ;
          } // if
        } // else if
        else if ( temp == ' ' ) {
          if ( save != "" ) { 
            noerror = Addtovector( save ) ;
            save = "" ;
          } // if
        } // else if
        else if ( temp == ';' ) {
          if ( save != "" ) { 
            noerror = Addtovector( save ) ;
            save = "" ;
          } // if
          
          if ( noerror ) noerror = Addtovector( ";" ) ;
          if ( !sneedretain ) {
            if (  noerror && i != line.length()-1 && !sinquotation ) 
              slineleft = line.substring( i+1, line.length() );
            // System.out.println( "123" +slineleft+ "123" );
            
            if ( ( sinquotation || sinsmallquotation ) && sissetcommand && noerror ) { // 括號中預先宣告 防undefine ;
              Justpass( spredo, spredoend, false, false, false ) ;
              // for( ; spredo != spredoend ; spredo = spredo.mnext ) System.out.println( spredo.mitem ) ;
            } // if
            
            if ( noerror && !sinif ) shasend = true ;
            // System.out.println( shasend + "" + sinquotation + sinif + "" ) ;
            sisfirst = true ;
            sissetcommand = false;
          } // if
        } // else if
        else if ( temp == ':' ) { // 宣告
          if ( save != "" ) { 
            noerror = Addtovector( save ) ;
            save = "" ;
          } // if
          
          if ( noerror ) noerror = Addtovector( ":" ) ;
        } // else if
        else if ( temp == '\"' ) {
          if ( save != "" ) { 
            noerror = Addtovector( save ) ;
            save = "" ;
          } // if
          
          int firstchar = i ;
          i++;
          char findend = line.charAt( i );
          while ( findend != '\"' && i < line.length() ) {
            i++ ;
            findend = line.charAt( i );
          } // while


          if ( noerror ) noerror = Addtovector( line.substring( firstchar, i+1 ) ) ;

        } // else if
        else if ( temp == '[' ) {
          if ( save != "" ) { 
            noerror = Addtovector( save ) ;
            save = "" ;
          } // if
          
          if ( noerror ) noerror = Addtovector( "[" ) ;

        } // else if
        else if ( temp == ']' ) {
          if ( save != "" ) { 
            noerror = Addtovector( save ) ;
            save = "" ;
          } // if
          
          if ( noerror ) noerror = Addtovector( "]" ) ;
        } // else if
        else if ( temp == '=' ) {
          if ( save != "" ) { 
            noerror = Addtovector( save ) ;
            save = "" ;
          } // if
          
          if ( noerror && i+1 < line.length() && line.charAt( i+1 ) == '='  ) {
            noerror = Addtovector( "==" ) ;
            i++ ;
          } // if
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
          else if ( noerror && i+1 < line.length() && line.charAt( i+1 ) == '=' ) {
            i++;
            noerror = Addtovector( "+=" ) ;
          } // else if 
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
          else if ( noerror && i+1 < line.length() && line.charAt( i+1 ) == '=' ) {
            i++;
            noerror = Addtovector( "-=" ) ;
          } // else if 
          else if ( noerror ) noerror = Addtovector( "-" ) ;

        } // else if
        else if ( temp == '*' ) {
          if ( save != "" ) { 
            noerror = Addtovector( save ) ;
            save = "" ;
          } // if
          
          if ( noerror && i+1 < line.length() && line.charAt( i+1 ) == '=' ) {
            i++;
            noerror = Addtovector( "*=" ) ;
          } // else if 
          else if ( noerror ) noerror = Addtovector( "*" ) ;

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
          else if ( noerror && i+1 < line.length() && line.charAt( i+1 ) == '=' ) {
            i++;
            noerror = Addtovector( "/=" ) ;
          } // else if {
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
          
          if ( noerror ) noerror = Addtovector( ")" ) ;
          
          if ( !sneedretain ) {
            sreadcomma--;
          
            if ( ( sinquotation || sinsmallquotation ) && sissetcommand && noerror ) { // 括號中預先宣告 防undefine ;
              Justpass( spredo, spredoend, false, false, true ) ;
              // for( ; spredo != spredoend ; spredo = spredo.mnext ) System.out.println( spredo.mitem ) ;
            } // if
            
            if ( sreadcomma == 0 ) sinsmallquotation = false ;  
          } // if
        } // else if
        else if ( temp == '(' ) {
          
          if ( save != "" ) { 
            noerror = Addtovector( save ) ;
            save = "" ;
          } // if
          
          if ( noerror ) noerror = Addtovector( "(" ) ;
          if ( !sneedretain ) {
            sreadcomma++;
            sissetcommand = false ;
            sisfirst = true ;
            sinsmallquotation = true ;
          } // if
        } // else if
        else if ( temp == '{' ) {
          if ( save != "" ) { 
            noerror = Addtovector( save ) ;
            save = "" ;
          } // if
          // System.out.println( "123" ) ;
          if ( noerror ) noerror = Addtovector( "{" ) ;
          
          if ( !sneedretain ) {
            sreadbigcomma++;
            sinquotation = true ;
            sissetcommand = false;
            sisfirst = true ;
          } // if
        } // else if
        else if ( temp == '}' ) {
          if ( save != "" ) { 
            noerror = Addtovector( save ) ;
            save = "" ;
          } // if
          
          if ( noerror ) noerror = Addtovector( "}" ) ;
          // System.out.println( noerror) ;
          
          if ( !sneedretain ) {
            sreadbigcomma--;
         
            if ( noerror && i != line.length()-1 && !sinif && !sindo && sreadbigcomma == 0 ) {
              // System.out.println( line.substring( i+1, line.length() ) ) ;
              slineleft = line.substring( i+1, line.length() );
              i = line.length() - 1 ;
            } // if
            
            if ( sreadbigcomma == 0 ) shasend = true ;
            if ( sindo && sdocount == 0 ) {
              // System.out.println( "000" ) ;
              sindo = false ;
              shasend = false ;
            } // if
            
            if ( sreadbigcomma == 0 ) sinquotation = false ;
            if ( sreadbigcomma == 0 ) Cleanallinfunction() ;
            sissetcommand = false;
            sisfirst = true ;
          } // if    
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
          if ( sinsmallquotation && sissetcommand && noerror )   // 括號中預先宣告  防undefine ;
            Justpass( spredo, spredoend, false, false, true ) ;
          
        } // else if
        else if ( temp == '?' ) {
          if ( save != "" ) { 
            noerror = Addtovector( save ) ;
            save = "" ;
          } // if
          
          if ( noerror ) noerror = Addtovector( "?" ) ;
        } // else if
        else if ( temp == '%' ) {
          if ( save != "" ) { 
            noerror = Addtovector( save ) ;
            save = "" ;
          } // if
          
          if ( noerror && i+1 < line.length() && line.charAt( i+1 ) == '=' ) {
            i++;
            noerror = Addtovector( "%=" ) ;
          } // else if 
          else if ( noerror ) noerror = Addtovector( "%" ) ;
        } // else if
        else if ( temp == '^' ) {
          if ( save != "" ) { 
            noerror = Addtovector( save ) ;
            save = "" ;
          } // if
          
          if ( noerror ) noerror = Addtovector( "^" ) ;
        } // else if
        else if ( temp == '!' ) {
          if ( save != "" ) { 
            noerror = Addtovector( save ) ;
            save = "" ;
          } // if
          
          if ( i+1 < line.length() &&  line.charAt( i+1 ) == '=' ) {   
            noerror = Addtovector( "!=" ) ;
            i++ ;
          } // if
          else if ( noerror ) noerror = Addtovector( "!" ) ;
        } // else if
        else if ( temp == '\'' ) { // '
          if ( save != "" ) { 
            noerror = Addtovector( save ) ;
            save = "" ;
          } // if
          
          int firstchar = i ;
          char check ;
          if ( i+2 < line.length() ) i = i + 2  ;

          check = line.charAt( i );

          if ( check != '\'' )  {
            if ( sreadline != 0 )
              System.out.println( "> Line " + sreadline + " : unrecognized token with first char : '''" ) ;
            else System.out.println( "> Line 1 : unrecognized token with first char : '''" ) ;
            noerror = false ;
          } // if
          
          if ( noerror ) noerror = Addtovector( line.substring( firstchar, i+1 ) ) ;

        } // else if
        else if ( temp == '.' ) { // '
          if ( Isallnum( save ) ) save = save + line.charAt( i ) ;
          else {
            noerror = false ;
            if ( sreadline != 0 )
              System.out.println( "> Line " + sreadline + " : unrecognized token with first char : '.'" ) ;
            else System.out.println( "> Line 1 : unrecognized token with first char : '.'" ) ;
          } // else 
        } // else if
        else {
          if ( IsLetterDigit( temp ) ) save = save + line.charAt( i ) ;
        } // else
        
        
        if ( noerror && shasend && !sinquotation && !sinif )  { 
          if ( i < line.length()-1 ) slineleft = line.substring( i+1, line.length() );
          // System.out.println( line.substring( i+1, line.length() ) ) ;
        } // if
        
        // System.out.println( sneedretain ) ;
        
        if ( noerror && sneedretain ) {
          // System.out.println( line ) ;
          temp = ' ' ; 
          // System.out.println( "===" + sretain + " " + line.substring( i +1 , line.length() ) + "===" ) ;
          // System.out.println(  "  0000" ) ;
          
          if ( line.length() == 1 ) slineleft = sretain ;
          else slineleft = sretain + " " + line.substring( i + 1, line.length() ) ;
          
          sretain = "" ;
          i = line.length() ;
          sisfirst = true ;
          sneedretain = false ;
        } // if
        
      } // for
      
      // System.out.println( shasend + "" + sinquotation + sinif + "" ) ;
      
      // System.out.println( noerror );
      // System.out.println( serrorkeep );
      
      if ( noerror && save != "" ) {
        // System.out.println( save );
        noerror = Addtovector( save ) ;
        save = "" ;
      } // if
      
      if ( !noerror ) {
        save = "" ;
        Cleanall() ;
        shasend = false ;
        noerror = true ;
        Cleaninfunction() ;
      } // if
      
      // System.out.println( shasend ) ;
      // System.out.println( sinquotation ) ;

      
      if ( ( !shasend || sinquotation || sinif ) && !slineleft.trim().isEmpty() ) {
        line = slineleft;
        slineleft = "" ;
      } // if
      else if ( ( !shasend || sinquotation || sinif ) && scanner.hasNext() ) {
        line = scanner.nextLine();
        sreadline++;
        while ( line.trim().isEmpty() ) {
          line = scanner.nextLine(); 
          sreadline++;
        } // while
      } // if
      else return ;
      
    } // while
  } // Readcommendandstore()
  
  static public boolean Checkpreisnotelse( ListNode node ) {
    ListNode temp = scommandhead ;
    while ( temp != null && temp != node && temp.mnext != node ) {
      temp = temp.mnext ;
    } // while
      
    // if ( temp != null ) System.out.println( temp.mitem ) ;
    if ( temp == null ) return true ;
    if ( temp == node ) return true ;
    if ( temp.mnext == node ) {
      if ( temp.mitem.equals( "else" ) ) return false ;
      else return true ;
    } // if
    
    return true ;
  } // Checkpreisnotelse()
    
  // ======================================================================================================
  static public void Gobackone() { // ok
    ListNode temp = scommandhead ;
    while ( temp != scheckhead && temp.mnext != scheckhead ) {
      temp = temp.mnext ;
    } // while
    
    scheckhead = temp ;
    
  } // Gobackone()
  
  
  static public boolean Checkgrammer() { // ok
    return UserInput();
  } // Checkgrammer()

  static public boolean UserInput() { // ok
    if ( sprintroad ) System.out.println( scheckhead.mitem + " UserInput" ) ;
    if ( DefinitionOrStatement() ) {
      if ( scheckhead.mnext == null ) return true ;
      else {
        scheckhead = scheckhead.mnext ;
        while ( scheckhead.mnext != null ) {
          if ( !DefinitionOrStatement() ) return false ;
        } // while 
        
        return true ;
      } // else 
    } // if
    else return false;  
  } // UserInput()

  static public boolean DefinitionOrStatement() {
    if ( sprintroad ) System.out.println( scheckhead.mitem + " DefinitionOrStatement" ) ;
    ListNode temp = scheckhead ; // 進來的位置
    if ( Definition() ) return true ;
    else scheckhead = temp ;
      
    if ( Statement() )  return true ;
    else scheckhead = temp ;
    
    return false ;
  } // DefinitionOrStatement()

  static public boolean Definition() {
    if ( sprintroad ) System.out.println( scheckhead.mitem + "Definition" ) ;
    if ( scheckhead == null ) return true ;
    if ( scheckhead.mtype == 8 ) {
      if ( scheckhead.mnext == null ) return true ;
      else scheckhead = scheckhead.mnext ;
      
      if ( scheckhead.mtype == 1 ) {
        if ( scheckhead.mnext == null ) return true ;
        else scheckhead = scheckhead.mnext ;
        
        if ( FunctionDefinitionWithoutID() ) return true ;
        else return false ; 
      } // if
      else return false ;
    } // if 
    else if ( TypeSpecifier() ) {
      if ( scheckhead.mnext == null ) return true ;
      else scheckhead = scheckhead.mnext ;
      
      if ( scheckhead.mtype == 1 ) {
        if ( scheckhead.mnext == null ) return true ;
        else scheckhead = scheckhead.mnext ;
        
        if ( FunctionDefinitionOrDeclarators() ) return true ;
        else return false ; 
      } // if
      else return false ;
    } // else if
    
    return false;
  } // Definition()

  static public boolean TypeSpecifier() {
    if ( sprintroad ) System.out.println( scheckhead.mitem + " TypeSpecifier" ) ;
    if ( scheckhead == null ) return true ;
    if ( scheckhead.mtype == 3 || scheckhead.mtype == 5 || scheckhead.mtype == 4 ||
         scheckhead.mtype == 7 || scheckhead.mtype == 6 ) return true;

    return false;
  } // TypeSpecifier()

  static public boolean FunctionDefinitionOrDeclarators() {
    if ( sprintroad ) System.out.println( scheckhead.mitem + " FunctionDefinitionOrDeclarators" ) ;
    ListNode temp = scheckhead ; // 進來的位置
    if ( FunctionDefinitionWithoutID() ) return true ;
    else scheckhead = temp ;
      
    if ( RestOfDeclarators() ) return true ;
    return false ;
    
  } // FunctionDefinitionOrDeclarators()

  static public boolean RestOfDeclarators() {
    if ( sprintroad ) System.out.println( scheckhead.mitem + " RestOfDeclarators" ) ;
    if ( scheckhead.mtype == 47 ) return true ;
    if ( scheckhead.mtype != 16 && scheckhead.mtype != 47 && scheckhead.mtype != 48 ) return false ;
    
    if ( scheckhead.mtype == 16 ) {
      if ( scheckhead.mnext == null ) return true ;
      else scheckhead = scheckhead.mnext ;
      
      if ( scheckhead.mtype == 2 ) {
        if ( scheckhead.mnext == null ) return true ;
        else scheckhead = scheckhead.mnext ;
        
        if ( scheckhead.mtype != 17 ) return false ;
        else {
          if ( scheckhead.mnext == null ) return true ;
          else scheckhead = scheckhead.mnext ;
        } // else 
      } // if
      else return false ;
    } // if
    
    if ( scheckhead.mtype == 47 ) return true ;

    if ( scheckhead.mtype == 48 ) {
      while ( scheckhead.mtype != 47 ) {
        
        if ( scheckhead.mtype == 48 ) {
          if ( scheckhead.mnext == null ) return true ;
          else scheckhead = scheckhead.mnext ;
          
          if ( scheckhead.mtype == 1 ) {
            if ( scheckhead.mnext == null ) return true ;
            else scheckhead = scheckhead.mnext ;
            
            if ( scheckhead.mtype == 16 ) {
              if ( scheckhead.mnext == null ) return true ;
              else scheckhead = scheckhead.mnext ;
              
              if ( scheckhead.mtype == 2 ) {
                if ( scheckhead.mnext == null ) return true ;
                else scheckhead = scheckhead.mnext ;
                
                if ( scheckhead.mtype != 17 ) return false ;
                else {
                  if ( scheckhead.mnext == null ) return true ;
                  else scheckhead = scheckhead.mnext ;
                } // else 
              } // if
              else return false ;
            } // if
            else if ( scheckhead.mtype != 47 && scheckhead.mtype != 48 ) return false ;
          } // if
          else return false ;
        } // if
        else return false ;
      } // while
    } // if
    
    if ( scheckhead.mtype == 47 ) return true ;

    return false ;
  } // RestOfDeclarators()

  static public boolean FunctionDefinitionWithoutID() {
    if ( sprintroad ) System.out.println( scheckhead.mitem + " FunctionDefinitionWithoutID" ) ;
    if ( scheckhead.mtype == 14 ) {
      if ( scheckhead.mnext == null ) return true ;
      else scheckhead = scheckhead.mnext ;
      
      if ( scheckhead.mtype != 15 ) {
        if ( scheckhead.mtype == 8 ) {
          if ( scheckhead.mnext == null ) return true ;
          else scheckhead = scheckhead.mnext ;
          
          if ( scheckhead.mtype != 15 ) return false ;
          else {
            if ( scheckhead.mnext == null ) return true ; // )
            else scheckhead = scheckhead.mnext ;
          } // else 
        } // if
        else  if ( FormalParameterList() ) {
          if ( sprintroad ) System.out.println( scheckhead.mitem + "FunctionDefinitionWithoutID 1174" ) ;
          if ( scheckhead.mnext == null ) return true ;
          else scheckhead = scheckhead.mnext ;
          
          if ( scheckhead.mtype != 15 ) return false ;
          else {
            if ( scheckhead.mnext == null ) return true ; // )
            else scheckhead = scheckhead.mnext ;
          } // else 
        } // else if
        else return false ;
      } // if
      else {
        if ( scheckhead.mnext == null ) return true ;
        else scheckhead = scheckhead.mnext ;
      } // else 
      
      if ( CompoundStatement() ) return true ;
      else return false ;
    } // if
    
    return false ;
  } // FunctionDefinitionWithoutID()

  static public boolean FormalParameterList() {
    if ( sprintroad ) System.out.println( scheckhead.mitem + " FormalParameterList" ) ;
    if ( TypeSpecifier() ) {
      // System.out.println( scheckhead.mitem + "FormalParameterList1212" ) ;
      if ( scheckhead.mnext == null ) return true ;
      else scheckhead = scheckhead.mnext ;
      
      if ( scheckhead.mtype == 32 ) {
        if ( scheckhead.mnext == null ) return true ;
        else scheckhead = scheckhead.mnext ;
      } // if
      
      if ( scheckhead.mtype == 1 ) {
        if ( scheckhead.mnext == null ) return true ;
        else scheckhead = scheckhead.mnext ;

        if ( scheckhead.mtype == 16 ) {
          if ( scheckhead.mnext == null ) return true ;
          else scheckhead = scheckhead.mnext ;
          
          if ( scheckhead.mtype == 2 ) {
            if ( scheckhead.mnext == null ) return true ;
            else scheckhead = scheckhead.mnext ;
            
            if ( scheckhead.mtype != 17 ) return false ;
          } // if
          else return false ;
        } // if
        else Gobackone() ;
        
        
        if ( scheckhead.mnext == null ) return true ;
        else if ( scheckhead.mnext.mtype != 48 ) return true ;
        else {
          scheckhead = scheckhead.mnext ;
          while ( scheckhead.mtype == 48 ) {
            if ( scheckhead.mnext == null ) return true ;
            else scheckhead = scheckhead.mnext ;
            
            if ( TypeSpecifier() ) {
              if ( scheckhead.mnext == null ) return true ;
              else scheckhead = scheckhead.mnext ;
              
              if ( scheckhead.mtype == 32 ) {
                if ( scheckhead.mnext == null ) return true ;
                else scheckhead = scheckhead.mnext ;
              } // if
              
              if ( scheckhead.mtype == 1 ) {
                if ( scheckhead.mnext == null ) return true ;
                else scheckhead = scheckhead.mnext ;
                
                if ( scheckhead.mtype == 16 ) {
                  if ( scheckhead.mnext == null ) return true ;
                  else scheckhead = scheckhead.mnext ;
                  
                  if ( scheckhead.mtype == 2 ) {
                    if ( scheckhead.mnext == null ) return true ;
                    else scheckhead = scheckhead.mnext ;
                    
                    if ( scheckhead.mtype != 17 ) return false ;
                    else {
                      if ( scheckhead.mnext == null ) return true ;
                      else {
                        if ( scheckhead.mnext == null ) return true ;
                        else if ( scheckhead.mnext.mtype != 48 ) return true ;
                        else scheckhead = scheckhead.mnext ;
                      } // else 
                    } // else 
                  } // if
                  else return false ;
               
                } // if
              } // if
              else return false ;
              
            } // if
          } // while
          
          // System.out.println( scheckhead.mitem + "FormalParameterList1290" ) ;
          Gobackone() ;
          return true ;
        } // else 
      } // if
      else return false ;
    } // if
    
    return false ; 
  } // FormalParameterList()

  static public boolean CompoundStatement() {
    if ( sprintroad ) System.out.println( scheckhead.mitem + " CompoundStatement" ) ;
    if ( scheckhead.mtype == 18 ) {
      if ( scheckhead.mnext == null ) return true ;
      else scheckhead = scheckhead.mnext ;
      
      if ( scheckhead.mtype == 19 ) return true ;
      while ( DefinitionOrStatement() ) {
        
        // System.out.println( scheckhead.mitem + " CompoundStatement 1424" ) ;
        if ( scheckhead.mnext == null ) return true ;
        else scheckhead = scheckhead.mnext ;
        // System.out.println( scheckhead.mitem + " CompoundStatement 1427" ) ;
        if ( scheckhead.mtype == 19 ) return true ;
      } // while 
      
    } // if
    
    return false;
  } // CompoundStatement()

  static public boolean Declaration() {
    if ( sprintroad ) System.out.println( scheckhead.mitem + " Declaration" ) ;
    if ( TypeSpecifier() ) {
      if ( scheckhead.mnext == null ) return true ;
      else scheckhead = scheckhead.mnext ;
      
      if ( scheckhead.mtype == 1 ) {
        if ( scheckhead.mnext == null ) return true ;
        else scheckhead = scheckhead.mnext ;
        
        if ( RestOfDeclarators() ) return true ;
        else return false ;
      } // if
      else return false ;
    } // if
    
    return false ;
  } // Declaration()

  static public boolean Statement() {
    if ( sprintroad ) System.out.println( scheckhead.mitem + " Statement" ) ;
    ListNode temp = scheckhead ;
    if ( scheckhead.mtype == 47 ) return true ;
    else if ( Expression() ) {
      // System.out.println( scheckhead.mitem +"1242"+ shasuse  ) ;
      if ( scheckhead.mnext == null ) return true ;
      else scheckhead = scheckhead.mnext ;
      
      if ( scheckhead.mtype == 47 ) return true ;
    } // else if 
    
    scheckhead = temp ;
    if ( scheckhead.mtype == 13 ) { // return 
      if ( scheckhead.mnext == null ) return true ;
      else scheckhead = scheckhead.mnext ;
      
      if ( scheckhead.mtype == 47 ) return true;
      else if ( Expression() ) {
        if ( scheckhead.mnext == null ) return true ;
        else scheckhead = scheckhead.mnext ;
        
        if ( scheckhead.mtype == 47 ) return true;
        else return false ;
      } // if
      else return false ; 
    } // if 
    
    scheckhead = temp ;
    // System.out.println( scheckhead.mitem ) ;
    if ( CompoundStatement() ) return true;
    
    scheckhead = temp ;
    if ( scheckhead.mtype == 9 ) { // if
      if ( scheckhead.mnext == null ) return true ;
      else scheckhead = scheckhead.mnext ;
      
      if ( scheckhead.mtype == 14 ) {
        if ( scheckhead.mnext == null ) return true ;
        else scheckhead = scheckhead.mnext ;
        
        if ( Expression() ) {
          if ( scheckhead.mnext == null ) return true ;
          else scheckhead = scheckhead.mnext ;
          
          if ( scheckhead.mtype == 15 ) {
            if ( scheckhead.mnext == null ) return true ;
            else scheckhead = scheckhead.mnext ;
          
            if ( Statement() ) {
              if ( scheckhead.mnext == null ) return true ;
              else if ( scheckhead.mnext.mtype != 10 ) return true ;
              else {
                scheckhead = scheckhead.mnext ; // == 10 else 
                if ( scheckhead.mnext == null ) return true ;
                else scheckhead = scheckhead.mnext ;
                
                if ( Statement() ) return true ;
                else return false ;
                
              } // else 
            } // if
            else return false ;
          } // if
          else return false ;
        } // if
        else return false ;
      } // if
      else return false ;
    } // if 
    
    scheckhead = temp ;
    if ( scheckhead.mtype == 11 ) {
      if ( scheckhead.mnext == null ) return true ;
      else scheckhead = scheckhead.mnext ;
      
      if ( scheckhead.mtype == 14 ) {
        if ( scheckhead.mnext == null ) return true ;
        else scheckhead = scheckhead.mnext ;
        
        if ( Expression() ) {
          if ( scheckhead.mnext == null ) return true ;
          else scheckhead = scheckhead.mnext ;
          
          if ( scheckhead.mtype == 15 ) {
            if ( scheckhead.mnext == null ) return true ;
            else scheckhead = scheckhead.mnext ;
            
            if ( Statement() ) return true ;
            else return false ;

          } // if  
          else return false ;
        } // if
        else return false ;
      } // if
      else return false ;
    } // if 
    
    scheckhead = temp ;
    if ( scheckhead.mtype == 12 ) {
      if ( scheckhead.mnext == null ) return true ;
      else scheckhead = scheckhead.mnext ;
      
      if ( Statement() ) {
        if ( scheckhead.mnext == null ) return true ;
        else scheckhead = scheckhead.mnext ;
        
        if ( scheckhead.mtype == 11 ) { // while
          if ( scheckhead.mnext == null ) return true ;
          else scheckhead = scheckhead.mnext ;
          
          if ( scheckhead.mtype == 14 ) {
            if ( scheckhead.mnext == null ) return true ;
            else scheckhead = scheckhead.mnext ;
            
            if ( Expression() ) {
              if ( scheckhead.mnext == null ) return true ;
              else scheckhead = scheckhead.mnext ;
              
              if ( scheckhead.mtype == 15 ) {
                if ( scheckhead.mnext == null ) return true ;
                else scheckhead = scheckhead.mnext ;
                
                if ( scheckhead.mtype == 47 ) return true ; // ;
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
    } // if
    
    return false ;
  } // Statement()

  static public boolean Expression() {
    if ( sprintroad ) System.out.println( scheckhead.mitem + " Expression" ) ;
    if ( BasicExpression() ) {
      // System.out.println( scheckhead.mitem + "1357" ) ;
      if ( scheckhead.mnext == null ) return true ;
      else if (  scheckhead.mnext.mtype != 48 ) return true  ;
      else {
        scheckhead = scheckhead.mnext ;
        while ( scheckhead.mtype == 48 ) { 

          if ( scheckhead.mnext == null ) return true ;
          else scheckhead = scheckhead.mnext ;
          
          if ( !BasicExpression() ) return false ;
          else {
            
            
            if ( scheckhead.mnext == null ) return true ;
            else if ( scheckhead.mnext.mtype != 48 ) return true ;
            else scheckhead = scheckhead.mnext ;

          } // else 
        } // while
      } // else 
    } // if
    
    return false ;
  } // Expression()

  static public boolean BasicExpression() {
    if ( sprintroad ) System.out.println( scheckhead.mitem + " BasicExpression" ) ;
    // System.out.println( scheckhead.mitem ) ;
    if ( scheckhead.mtype == 1 ) {
      if ( scheckhead.mnext == null ) return true ;
      else scheckhead = scheckhead.mnext ;
      if ( RestOfIdentifierStartedBasicExp() ) return true ;
      else return false ;
    } // if 
    else if ( scheckhead.mtype == 43 || scheckhead.mtype == 44 ) {
      if ( scheckhead.mnext == null ) return true ;
      else scheckhead = scheckhead.mnext ;
      if ( scheckhead.mtype == 1 ) {
        if ( scheckhead.mnext == null ) return true ;
        else scheckhead = scheckhead.mnext ;
        
        if ( RestOfPPMMIdentifierStartedBasicExp() ) return true ;
        else return false ;
      } // if
      else return false ;
    } // else if 
    else if ( Sign() ) {
      if ( scheckhead.mnext == null ) return true ;
      else scheckhead = scheckhead.mnext ;
      while ( Sign() ) {
        if ( scheckhead.mnext == null ) return true ;
        else scheckhead = scheckhead.mnext ;
      } // while
      
      if ( SignedUnaryExp() ) {
        if ( scheckhead.mnext == null ) return true ;
        else scheckhead = scheckhead.mnext ;
        
        if ( RestOfMaybeConditionalExpAndRestOfMaybeLogicalORExp() )  return true ;
        else return false ;
        
      } // if
      else return false ;
    } // else if 
    else if ( scheckhead.mtype == 2 ) {
      if ( scheckhead.mnext == null ) return true ;
      else scheckhead = scheckhead.mnext ;
      
      if ( RestOfMaybeConditionalExpAndRestOfMaybeLogicalORExp() ) {
        // System.out.println( scheckhead.mitem + "1430") ;
        return true ;
      } // if
      else return false ;
    } // else if 
    else if ( scheckhead.mtype == 14 ) {
      if ( scheckhead.mnext == null ) return true ;
      else scheckhead = scheckhead.mnext ;
      
      if ( Expression() ) {
        // System.out.println( scheckhead.mitem + "1434" + shasuse ) ;
        if ( scheckhead.mnext == null ) return true ;
        else scheckhead = scheckhead.mnext ;
        
        if ( scheckhead.mtype == 15 ) {
          
          if ( scheckhead.mnext == null ) return true ;
          else scheckhead = scheckhead.mnext ;
          
          if ( RestOfMaybeConditionalExpAndRestOfMaybeLogicalORExp() ) {
            if ( sprintroad ) System.out.println( scheckhead.mitem +" 1450" ) ;
            return true ;
          } // if
          else return false ;
        } // if  
        else return false ;
      } // if
      else return false ;
    } // else if
    
    return false;
  } // BasicExpression()

  static public boolean RestOfIdentifierStartedBasicExp() {
    if ( sprintroad ) System.out.println( scheckhead.mitem + " RestOfIdentifierStartedBasicExp" ) ;
    // System.out.println( scheckhead.mitem ) ;
    ListNode temp = scheckhead ;
    if ( scheckhead.mtype == 16 ) {
      if ( scheckhead.mnext == null ) return true ;
      else scheckhead = scheckhead.mnext ;
      
      if ( Expression() ) {
        if ( scheckhead.mnext == null ) return true ;
        else scheckhead = scheckhead.mnext ;
        
        if ( scheckhead.mtype == 17 ) {
          if ( scheckhead.mnext == null ) return true ;
          else scheckhead = scheckhead.mnext ;
          
          temp = scheckhead ;
          if ( scheckhead.mtype == 43 || scheckhead.mtype == 44 ) {
            if ( scheckhead.mnext == null ) return true ;
            else scheckhead = scheckhead.mnext ;
            
            if ( RestOfMaybeConditionalExpAndRestOfMaybeLogicalORExp() ) return true ;
            else return false ;
          } // if
          
          if ( AssignmentOperator() ) {
            if ( scheckhead.mnext == null ) return true ;
            else scheckhead = scheckhead.mnext ;
            
            if ( BasicExpression() ) {
              // System.out.println( "1493" ) ;
              return true ;
            } // if
            else return false ;
          } // if
          
          scheckhead = temp;
          if ( RestOfMaybeConditionalExpAndRestOfMaybeLogicalORExp() ) return true ;
          else return false ;
          
        } // if  
        else return false ;
      } // if
      else return false ;
    } // if
    else if ( scheckhead.mtype == 14 ) {
      if ( scheckhead.mnext == null ) return true ;
      else scheckhead = scheckhead.mnext ;
      
      
      
      if ( scheckhead.mtype != 15 ) { // )
        if ( !ActualParameterList() ) return false ;
        else {
          if ( scheckhead.mnext == null ) return true ;
          else scheckhead = scheckhead.mnext ;
        } // else
      } // if
      
      if ( scheckhead.mtype == 15 ) { // )
        if ( scheckhead.mnext == null ) return true ;
        else scheckhead = scheckhead.mnext ;
        
        if ( RestOfMaybeConditionalExpAndRestOfMaybeLogicalORExp() ) return true ;
        else return false ;
      } // if
      else return false ;
  
    } // else if
    else {
      if ( scheckhead.mtype == 43 || scheckhead.mtype == 44 ) {
        if ( scheckhead.mnext == null ) return true ;
        else scheckhead = scheckhead.mnext ;
        
        if ( RestOfMaybeConditionalExpAndRestOfMaybeLogicalORExp() ) return true ;
        else return false ;
      } // if
      
      if ( AssignmentOperator() ) {
        if ( scheckhead.mnext == null ) return true ;
        else scheckhead = scheckhead.mnext ;
        
        if ( BasicExpression() ) return true ;
        else return false ;
      } // if
      
      if ( RestOfMaybeConditionalExpAndRestOfMaybeLogicalORExp() ) return true ;
      else return false ;
    } // else 

  } // RestOfIdentifierStartedBasicExp()

  static public boolean RestOfPPMMIdentifierStartedBasicExp() {
    if ( sprintroad ) System.out.println( scheckhead.mitem + " RestOfPPMMIdentifierStartedBasicExp" ) ;
    if ( scheckhead.mtype == 16 ) {
      if ( scheckhead.mnext == null ) return true ;
      else scheckhead = scheckhead.mnext ;
      
      if ( Expression() ) {
        if ( scheckhead.mnext == null ) return true ;
        else scheckhead = scheckhead.mnext ;
        
        if ( scheckhead.mtype == 17 ) {
          if ( scheckhead.mnext == null ) return true ;
          else scheckhead = scheckhead.mnext ;
          
          if ( RestOfMaybeConditionalExpAndRestOfMaybeLogicalORExp() ) return true ;
          else return false ;
          
        } // if  
        else return false ;
      } // if
      else return false ;
    } // if
    else {
      if ( RestOfMaybeConditionalExpAndRestOfMaybeLogicalORExp() ) return true ;
      else return false ;
    } // else
  } // RestOfPPMMIdentifierStartedBasicExp()

  static public boolean Sign() {
    if ( sprintroad ) System.out.println( scheckhead.mitem + " Sign" ) ;
    if ( scheckhead.mtype == 20 || scheckhead.mtype == 21 || scheckhead.mtype == 35 ) return true ;
    else return false ;
  } // Sign()

  static public boolean ActualParameterList() {
    if ( sprintroad ) System.out.println( scheckhead.mitem + " ActualParameterList" ) ;
    if ( BasicExpression() ) {
      if ( scheckhead.mnext == null ) return true ;
      else if ( scheckhead.mnext.mtype != 48 ) return true  ;
      else {
        scheckhead = scheckhead.mnext ;        
        while ( scheckhead.mtype == 48 ) { 
          if ( scheckhead.mnext == null ) return true ;
          else scheckhead = scheckhead.mnext ;
          
          if ( !BasicExpression() ) return false ;
          else {
            if ( scheckhead.mnext == null ) return true ;
            else if ( scheckhead.mnext.mtype != 48 ) return true ;
            else scheckhead = scheckhead.mnext ;
          } // else 
        } // while
      } // else 
    } // if
    
    return false ;
  } // ActualParameterList()

  static public boolean AssignmentOperator() {
    if ( sprintroad ) System.out.println( scheckhead.mitem + " AssignmentOperator" ) ;
    if ( scheckhead.mtype == 34 || scheckhead.mtype == 38 || scheckhead.mtype == 39 
         || scheckhead.mtype == 40 || scheckhead.mtype == 41 || scheckhead.mtype == 42 ) return true ;
    else return false ;
  } // AssignmentOperator()

  static public boolean RestOfMaybeConditionalExpAndRestOfMaybeLogicalORExp() {
    if ( sprintroad ) 
      System.out.println( scheckhead.mitem + " RestOfMaybeConditionalExpAndRestOfMaybeLogicalORExp1606" ) ;
    if ( RestOfMaybeLogicalORExp() ) {
      // System.out.println( "test1609" ) ;
      if ( sprintroad ) System.out.println( scheckhead.mitem +"RestOfMaybeAdditiveExp 1654" ) ;
      
      if ( scheckhead.mnext == null ) return true ;
      else if ( scheckhead.mnext.mtype != 49 ) return true  ;
      else {
        scheckhead = scheckhead.mnext ;       // ?  
        if ( scheckhead.mnext == null ) return true ;
        else scheckhead = scheckhead.mnext ;
        if ( BasicExpression() ) {
          if ( scheckhead.mnext == null ) return true ;
          else scheckhead = scheckhead.mnext ;
          if ( scheckhead.mtype == 50 ) { // :
            if ( scheckhead.mnext == null ) return true ;
            else scheckhead = scheckhead.mnext ;
            
            if ( BasicExpression() ) return true ;
            else return false ;  
          } // if
          else return false ;
        } // if
        else return false ;
      } // else 
    } // if
    else return false ;
  } // RestOfMaybeConditionalExpAndRestOfMaybeLogicalORExp()

  static public boolean RestOfMaybeLogicalORExp() {
    if ( sprintroad ) System.out.println( scheckhead.mitem + " RestOfMaybeLogicalORExp" ) ;
    if ( RestOfMaybeLogicalANDExp() ) {
      if ( scheckhead.mnext == null ) return true ;
      else if ( scheckhead.mnext.mtype != 37 ) return true  ;
      else {
        scheckhead = scheckhead.mnext ;
        while ( scheckhead.mtype == 37 ) { 
          if ( scheckhead.mnext == null ) return true ;
          else scheckhead = scheckhead.mnext ;
          
          if ( !MaybeLogicalANDExp() ) return false ;
          
          if ( scheckhead.mnext == null ) return true ;
          else if ( scheckhead.mnext.mtype != 37 ) return true ;
          else scheckhead = scheckhead.mnext ;
        } // while
      } // else
    } // if
    
    return false ;
  } // RestOfMaybeLogicalORExp()

  static public boolean MaybeLogicalANDExp() {
    if ( sprintroad ) System.out.println( scheckhead.mitem + " MaybeLogicalANDExp" ) ;
    if ( MaybeBitORExp() ) {
      if ( scheckhead.mnext == null ) return true ;
      else if ( scheckhead.mnext.mtype != 36 ) return true  ;
      else {
        scheckhead = scheckhead.mnext ;       // and    
        while ( scheckhead.mtype == 36 ) { 
          if ( scheckhead.mnext == null ) return true ;
          else scheckhead = scheckhead.mnext ;
          
          if ( !MaybeBitORExp() ) return false ;
          else {
            if ( scheckhead.mnext == null ) return true ;
            else if ( scheckhead.mnext.mtype != 36 ) return true ;
            else scheckhead = scheckhead.mnext ;
          } // else 
        } // while
      } // else
    } // if
    
    return false ;
  } // MaybeLogicalANDExp()

  static public boolean RestOfMaybeLogicalANDExp() {
    if ( sprintroad ) System.out.println( scheckhead.mitem + " RestOfMaybeLogicalANDExp" ) ;
    if ( RestOfMaybeBitORExp() ) {
      if ( scheckhead.mnext == null ) return true ;
      else if ( scheckhead.mnext.mtype != 36 ) return true  ;
      else {
        scheckhead = scheckhead.mnext ;
        while ( scheckhead.mtype == 36 ) { 
          if ( scheckhead.mnext == null ) return true ;
          else scheckhead = scheckhead.mnext ;
          
          if ( !MaybeBitORExp() ) return false ;
          
          if ( scheckhead.mnext == null ) return true ;
          else if ( scheckhead.mnext.mtype != 36 ) return true ;
          else scheckhead = scheckhead.mnext ;
        } // while
      } // else
    } // if
    
    return false ;
  } // RestOfMaybeLogicalANDExp()

  static public boolean MaybeBitORExp() {
    if ( sprintroad ) System.out.println( scheckhead.mitem + " MaybeBitORExp" ) ;
    if ( MaybeBitEXORExp() ) {
      if ( scheckhead.mnext == null ) return true ;
      else if ( scheckhead.mnext.mtype != 33 ) return true  ;
      else {
        scheckhead = scheckhead.mnext ;       // | 
        while ( scheckhead.mtype == 33 ) { 
          if ( scheckhead.mnext == null ) return true ;
          else scheckhead = scheckhead.mnext ;
          
          if ( !MaybeBitEXORExp() ) return false ;
          else {
            if ( scheckhead.mnext == null ) return true ;
            else if ( scheckhead.mnext.mtype != 33 ) return true ;
            else scheckhead = scheckhead.mnext ;
          } // else 
        } // while
      } // else
    } // if
    
    return false ;
  } // MaybeBitORExp()

  static public boolean RestOfMaybeBitORExp() {
    if ( sprintroad ) System.out.println( scheckhead.mitem + " RestOfMaybeBitORExp" ) ;
    if ( RestOfMaybeBitEXORExp() ) {
      if ( scheckhead.mnext == null ) return true ;
      else if ( scheckhead.mnext.mtype != 33 ) return true  ;
      else {
        scheckhead = scheckhead.mnext ;
        while ( scheckhead.mtype == 33 ) { 
          if ( scheckhead.mnext == null ) return true ;
          else scheckhead = scheckhead.mnext ;
          
          if ( !MaybeBitEXORExp() ) return false ;
          
          if ( scheckhead.mnext == null ) return true ;
          else if ( scheckhead.mnext.mtype != 33 ) return true ;
          else scheckhead = scheckhead.mnext ;
        } // while
      } // else
    } // if
    
    return false ;
  } // RestOfMaybeBitORExp()

  static public boolean MaybeBitEXORExp() {
    if ( sprintroad ) System.out.println( scheckhead.mitem + " MaybeBitEXORExp" ) ;
    if ( MaybeBitANDExp() ) {
      if ( scheckhead.mnext == null ) return true ;
      else if ( scheckhead.mnext.mtype != 25 ) return true  ;
      else {
        scheckhead = scheckhead.mnext ;       // ^
        while ( scheckhead.mtype == 25 ) { 
          if ( scheckhead.mnext == null ) return true ;
          else scheckhead = scheckhead.mnext ;
          
          if ( !MaybeBitANDExp() ) return false ;
          else {
            if ( scheckhead.mnext == null ) return true ;
            else if ( scheckhead.mnext.mtype != 25 ) return true ;
            else scheckhead = scheckhead.mnext ;
          } // else 
        } // while
      } // else
    } // if
    
    return false ;
  } // MaybeBitEXORExp()

  static public boolean RestOfMaybeBitEXORExp() {
    if ( sprintroad ) System.out.println( scheckhead.mitem + " RestOfMaybeBitEXORExp" ) ;
    if ( RestOfMaybeBitANDExp() ) {
      if ( sprintroad ) System.out.println( scheckhead.mitem +"RestOfMaybeAdditiveExp 1785" ) ;
      if ( scheckhead.mnext == null ) return true ;
      else if ( scheckhead.mnext.mtype != 25 ) return true  ;
      else {
        scheckhead = scheckhead.mnext ;
        while ( scheckhead.mtype == 25 ) { 
          if ( scheckhead.mnext == null ) return true ;
          else scheckhead = scheckhead.mnext ;
          
          if ( !MaybeBitANDExp() ) return false ;
          
          if ( scheckhead.mnext == null ) return true ;
          else if ( scheckhead.mnext.mtype != 25 ) return true ;
          else scheckhead = scheckhead.mnext ;
        } // while
      } // else
    } // if
    
    return false ;
  } // RestOfMaybeBitEXORExp()

  static public boolean MaybeBitANDExp() {
    if ( sprintroad ) System.out.println( scheckhead.mitem + " MaybeBitANDExp" ) ;
    if ( MaybeEqualityExp() ) {
      if ( scheckhead.mnext == null ) return true ;
      else if ( scheckhead.mnext.mtype != 32 ) return true  ;
      else {
        scheckhead = scheckhead.mnext ;       // &
        while ( scheckhead.mtype == 32 ) { 
          if ( scheckhead.mnext == null ) return true ;
          else scheckhead = scheckhead.mnext ;
          
          if ( !MaybeEqualityExp() ) return false ;
          else {
            if ( scheckhead.mnext == null ) return true ;
            else if ( scheckhead.mnext.mtype != 32 ) return true ;
            else scheckhead = scheckhead.mnext ;
          } // else 
        } // while
      } // else
    } // if
    
    return false ;
  } // MaybeBitANDExp()

  static public boolean RestOfMaybeBitANDExp() {
    if ( sprintroad ) System.out.println( scheckhead.mitem + " RestOfMaybeBitANDExp" ) ;
    if ( RestOfMaybeEqualityExp() ) {
      
      if ( scheckhead.mnext == null ) return true ;
      else if ( scheckhead.mnext.mtype != 32 ) return true  ;
      else {
        scheckhead = scheckhead.mnext ;
        while ( scheckhead.mtype == 32 ) { 
          if ( scheckhead.mnext == null ) return true ;
          else scheckhead = scheckhead.mnext ;
          
          if ( !MaybeEqualityExp() ) return false ;
          
          if ( scheckhead.mnext == null ) return true ;
          else if ( scheckhead.mnext.mtype != 32 ) return true ;
          else scheckhead = scheckhead.mnext ;
        } // while
      } // else
    } // if
    
    return false ;
  } // RestOfMaybeBitANDExp()

  static public boolean MaybeEqualityExp() {
    if ( sprintroad ) System.out.println( scheckhead.mitem + " MaybeEqualityExp" ) ;
    if ( MaybeRelationalExp() ) {
      if ( scheckhead.mnext == null ) return true ;
      else if ( scheckhead.mnext.mtype != 30 && scheckhead.mnext.mtype != 31 ) return true  ;
      else {
        scheckhead = scheckhead.mnext ;       // == !=
        while ( scheckhead.mtype == 30 || scheckhead.mtype == 31 ) { 
          if ( scheckhead.mnext == null ) return true ;
          else scheckhead = scheckhead.mnext ;
          
          if ( !MaybeRelationalExp() ) return false ;
          else {
            if ( scheckhead.mnext == null ) return true ;
            else if ( scheckhead.mnext.mtype != 30 && scheckhead.mnext.mtype != 31 ) return true ;
            else scheckhead = scheckhead.mnext ;
          } // else 
        } // while
      } // else
    } // if
    
    return false ;
  } // MaybeEqualityExp()

  static public boolean RestOfMaybeEqualityExp() {
    if ( sprintroad ) System.out.println( scheckhead.mitem + " RestOfMaybeEqualityExp" ) ;
    if ( RestOfMaybeRelationalExp() ) {
      
      if ( scheckhead.mnext == null ) return true ;   
      else if ( scheckhead.mnext.mtype != 30 && scheckhead.mnext.mtype != 31 ) return true  ;
      else {
        scheckhead = scheckhead.mnext ;
        while ( scheckhead.mtype == 30 || scheckhead.mtype == 31 ) { 
          if ( scheckhead.mnext == null ) return true ;
          else scheckhead = scheckhead.mnext ;
          
          if ( !MaybeRelationalExp() ) return false ;
          
          if ( scheckhead.mnext == null ) return true ;
          else if ( scheckhead.mnext.mtype != 30 && scheckhead.mnext.mtype != 31 ) return true ;
          else scheckhead = scheckhead.mnext ;
        } // while
      } // else
    } // if
    
    return false ;
  } // RestOfMaybeEqualityExp()

  static public boolean MaybeRelationalExp() {
    if ( sprintroad ) System.out.println( scheckhead.mitem + " MaybeRelationalExp" ) ;
    if ( MaybeShiftExp() ) {
      if ( scheckhead.mnext == null ) return true ;
      else if ( scheckhead.mnext.mtype != 26 && scheckhead.mnext.mtype != 27 && scheckhead.mnext.mtype != 28
                && scheckhead.mnext.mtype != 29 ) return true  ;
      else {
        scheckhead = scheckhead.mnext ;       // == !=
        while ( scheckhead.mtype == 26 || scheckhead.mtype == 27 || scheckhead.mtype == 28 || 
                scheckhead.mtype == 29 ) { 
          if ( scheckhead.mnext == null ) return true ;
          else scheckhead = scheckhead.mnext ;
          
          if ( !MaybeShiftExp() ) return false ;
          else {
            if ( scheckhead.mnext == null ) return true ;
            else if ( scheckhead.mnext.mtype != 26 && scheckhead.mnext.mtype != 27 
                      && scheckhead.mnext.mtype != 28 && scheckhead.mnext.mtype != 29 ) return true ;
            else scheckhead = scheckhead.mnext ;
          } // else 
        } // while
      } // else
    } // if
    
    return false ;
  } // MaybeRelationalExp()

  static public boolean RestOfMaybeRelationalExp() {
    if ( sprintroad ) System.out.println( scheckhead.mitem + " RestOfMaybeRelationalExp" ) ;
    if ( RestOfMaybeShiftExp() ) {
      
      if ( scheckhead.mnext == null ) return true ;    
      else if ( scheckhead.mnext.mtype != 26 && scheckhead.mnext.mtype != 27 && 
                scheckhead.mnext.mtype != 28 && scheckhead.mnext.mtype != 29 ) return true  ;
      else {
        scheckhead = scheckhead.mnext ;
        while ( scheckhead.mtype == 26 || scheckhead.mtype == 27 || scheckhead.mtype == 28 
                || scheckhead.mtype == 29 ) { 
          if ( scheckhead.mnext == null ) return true ;
          else scheckhead = scheckhead.mnext ;
          
          if ( !MaybeShiftExp() ) return false ;
          
          if ( scheckhead.mnext == null ) return true ;
          else if ( scheckhead.mnext.mtype != 26 && scheckhead.mnext.mtype != 27 
                    && scheckhead.mnext.mtype != 28 && scheckhead.mnext.mtype != 29 ) return true ;
          else scheckhead = scheckhead.mnext ;
        } // while
      } // else
    } // if
    
    return false ;
  } // RestOfMaybeRelationalExp()

  static public boolean MaybeShiftExp() {
    if ( sprintroad ) System.out.println( scheckhead.mitem + " MaybeShiftExp" ) ;
    if ( MaybeAdditiveExp() ) {
      if ( scheckhead.mnext == null ) return true ;
      else if ( scheckhead.mnext.mtype != 45 && scheckhead.mnext.mtype != 46 ) return true  ;
      else {
        scheckhead = scheckhead.mnext ;       //  >>  <<     
        while ( scheckhead.mtype == 45 || scheckhead.mtype == 46 ) { 
          if ( scheckhead.mnext == null ) return true ;
          else scheckhead = scheckhead.mnext ;
          
          if ( !MaybeAdditiveExp() ) return false ;
          else {
            if ( scheckhead.mnext == null ) return true ;
            else if ( scheckhead.mnext.mtype != 45 && scheckhead.mnext.mtype != 46 ) return true ;
            else scheckhead = scheckhead.mnext ;
          } // else 
        } // while
      } // else
    } // if
    
    return false ;
  } // MaybeShiftExp()

  static public boolean RestOfMaybeShiftExp() {
    if ( sprintroad ) System.out.println( scheckhead.mitem + " RestOfMaybeShiftExp" ) ;
    if ( RestOfMaybeAdditiveExp() ) {
      if ( sprintroad ) System.out.println( scheckhead.mitem +"RestOfMaybeAdditiveExp 1975" ) ;
      
      if ( scheckhead.mnext == null ) return true ;
      else if ( scheckhead.mnext.mtype != 45 && scheckhead.mnext.mtype != 46 ) return true  ;
      else {
        scheckhead = scheckhead.mnext ;
        while ( scheckhead.mtype == 45 || scheckhead.mtype == 46 ) { 
          if ( scheckhead.mnext == null ) return true ;
          else scheckhead = scheckhead.mnext ;
          
          if ( !MaybeAdditiveExp() ) return false ;
          
          if ( scheckhead.mnext == null ) return true ;
          else if ( scheckhead.mnext.mtype != 45 && scheckhead.mnext.mtype != 46 ) return true ;
          else scheckhead = scheckhead.mnext ;
        } // while
      } // else
    } // if
    
    return false ;
  } // RestOfMaybeShiftExp()

  static public boolean MaybeAdditiveExp() {
    if ( sprintroad ) System.out.println( scheckhead.mitem + " MaybeAdditiveExp 2019" ) ;
    if ( MaybeMultExp() ) {
      if ( scheckhead.mnext == null ) return true ;
      else if ( scheckhead.mnext.mtype != 20 && scheckhead.mnext.mtype != 21 ) return true  ;
      else {
        scheckhead = scheckhead.mnext ;       //  >>  <<     
        while ( scheckhead.mtype == 20 || scheckhead.mtype == 21 ) { 
          if ( scheckhead.mnext == null ) return true ;
          else scheckhead = scheckhead.mnext ;
          
          if ( !MaybeMultExp() ) return false ;
          else {
            if ( scheckhead.mnext == null ) return true ;
            else if ( scheckhead.mnext.mtype != 20 && scheckhead.mnext.mtype != 21 ) return true ;
            else scheckhead = scheckhead.mnext ;
          } // else 
        } // while
      } // else
    } // if
    
    return false ;
  } // MaybeAdditiveExp()

  static public boolean RestOfMaybeAdditiveExp() {
    if ( sprintroad ) System.out.println( scheckhead.mitem +" RestOfMaybeAdditiveExp" ) ;

    if ( RestOfMaybeMultExp() ) {
      
      
      if ( scheckhead.mnext == null ) return true ;
      else if ( scheckhead.mnext.mtype != 20 && scheckhead.mnext.mtype != 21 )  return true  ;
      else {
        scheckhead = scheckhead.mnext ;
        while ( scheckhead.mtype == 20 || scheckhead.mtype == 21 ) { 
          
          if ( scheckhead.mnext == null ) return true ;
          else scheckhead = scheckhead.mnext ;
          // System.out.println( scheckhead.mitem + "2053" ) ;
          
          if ( !MaybeMultExp() ) return false ;
          
          // System.out.println( scheckhead.mitem + "2057" ) ;
          
          if ( scheckhead.mnext == null ) return true ;
          else if ( scheckhead.mnext.mtype != 20 && scheckhead.mnext.mtype != 21 ) return true ;
          else scheckhead = scheckhead.mnext ;
        } // while
      } // else
    } // if
    
    return false ;
  } // RestOfMaybeAdditiveExp()

  static public boolean MaybeMultExp() {
    if ( sprintroad ) System.out.println( scheckhead.mitem + " MaybeMultExp 2063"  ) ;
    if ( UnaryExp() ) {
      if ( sprintroad ) System.out.println( scheckhead.mitem + "MaybeMultExp 2061"  ) ;
      if ( scheckhead.mnext == null ) return true ;
      else scheckhead = scheckhead.mnext ;
      
      if ( sprintroad ) System.out.println( scheckhead.mitem + "MaybeMultExp 2065"  ) ;
      if ( RestOfMaybeMultExp() ) {
        // System.out.println( "2042" ) ;
        return true ;
      } // if
      else return false ;
    } // if
    
    return false ;
  } // MaybeMultExp()

  static public boolean RestOfMaybeMultExp() {
    if ( sprintroad ) System.out.println( scheckhead.mitem + " RestOfMaybeMultExp" ) ;
    if ( scheckhead.mtype == 22 || scheckhead.mtype == 23 || scheckhead.mtype == 24 ) {
      
      if ( scheckhead.mnext == null ) return true ;
      else scheckhead = scheckhead.mnext ;
      
      if ( !UnaryExp() ) return false ;
      else {
        // System.out.println( scheckhead.mitem + "2016" ) ;
        if ( scheckhead.mnext == null ) return true ;
        else if ( scheckhead.mnext.mtype != 22 && scheckhead.mnext.mtype != 23 
                  && scheckhead.mnext.mtype != 24 ) {

          return true  ;
        } // else if
        else {
          scheckhead = scheckhead.mnext ;       //  * / %
          while ( scheckhead.mtype == 22 || scheckhead.mtype == 23 || scheckhead.mtype == 24 ) { 
            if ( scheckhead.mnext == null ) return true ;
            else scheckhead = scheckhead.mnext ;
            
            if ( !UnaryExp() ) return false ;
            else {
              if ( scheckhead.mnext == null ) return true ;
              else if ( scheckhead.mnext.mtype != 22 && scheckhead.mnext.mtype != 23 
                        && scheckhead.mnext.mtype != 24 ) {
                return true ;
              } // else if
              else scheckhead = scheckhead.mnext ;
            } // else 
          } // while
        } // else
      } // else 
    } // if

    
    
    if ( sprintroad ) System.out.println( scheckhead.mitem + " RestOfMaybeMultExp2125" ) ;
    Gobackone() ;
    return true ;
  } // RestOfMaybeMultExp()

  static public boolean UnaryExp() {
    if ( sprintroad ) System.out.println( scheckhead.mitem + " UnaryExp 2112"  ) ;
    if ( Sign() ) {
      if ( scheckhead.mnext == null ) return true ;
      else scheckhead = scheckhead.mnext ;
      
      while ( Sign() ) {
        if ( scheckhead.mnext == null ) return true ;
        else scheckhead = scheckhead.mnext ;
      } // while
      
      if ( SignedUnaryExp() ) return true ;
      else return false ;
      
    } // if
    else if ( UnsignedUnaryExp() ) {
      // System.out.println( "2145" ) ;
      return true ;
    } // else if
    else if ( scheckhead.mtype == 43 || scheckhead.mtype == 44 ) {
      if ( scheckhead.mnext == null ) return true ;
      else scheckhead = scheckhead.mnext ;
        
      if ( scheckhead.mtype == 1 ) {
        if ( scheckhead.mnext == null ) return true ;
        else if ( scheckhead.mnext.mtype != 16 ) return true  ;
        else {
          scheckhead = scheckhead.mnext ; // [
          if ( scheckhead.mnext == null ) return true ;
          else scheckhead = scheckhead.mnext ;
          
          if ( Expression() ) {
            if ( scheckhead.mnext == null ) return true ;
            else scheckhead = scheckhead.mnext ;
            
            if ( scheckhead.mtype == 17 ) return true ;
            else return false ;
          } // if
          else return false ;
        } // else 
      } // if
      else return false ;  
    } // else if
    
    return false ; 
  } // UnaryExp()

  static public boolean SignedUnaryExp() {
    if ( sprintroad ) System.out.println( scheckhead.mitem + " SignedUnaryExp" ) ;
    if ( scheckhead.mtype == 1 ) {
      
      if ( scheckhead.mnext == null ) return true ;
      else if ( scheckhead.mnext.mtype != 14 && scheckhead.mnext.mtype != 16 ) return true  ;
      else {
        scheckhead = scheckhead.mnext ; // 
        if ( scheckhead.mtype == 14 ) {
          if ( scheckhead.mnext == null ) return true ;
          else scheckhead = scheckhead.mnext ;
          
          if ( scheckhead.mtype == 15 ) return true ;
          else {
            if ( ActualParameterList() ) {
              if ( scheckhead.mnext == null ) return true ;
              else scheckhead = scheckhead.mnext ;
              
              if ( scheckhead.mtype == 15 ) return true ;
              else return false ;
            } // if
            else return false ;
          } // else
        } // if
        
        if ( scheckhead.mtype == 16 ) {
          if ( scheckhead.mnext == null ) return true ;
          else scheckhead = scheckhead.mnext ;
          
          if ( Expression() ) {
            if ( scheckhead.mnext == null ) return true ;
            else scheckhead = scheckhead.mnext ;
            
            if ( scheckhead.mtype == 17 )  return true ;
            else return false ;
          } // if
          else return false ;
        } // if
      } // else
    } // if
    else if ( scheckhead.mtype == 2 )  return true ;
    else if ( scheckhead.mtype == 14 ) {

      if ( scheckhead.mnext == null ) return true ;
      else scheckhead = scheckhead.mnext ;

      if ( Expression() ) {
        if ( scheckhead.mnext == null ) return true ;
        else scheckhead = scheckhead.mnext ;
        
        if ( scheckhead.mtype == 15 ) return true ;
        else return false ;
      } // if
      else return false ;
    } // else if
    
    return false ; 
  } // SignedUnaryExp()

  static public boolean UnsignedUnaryExp() {
    if ( sprintroad ) System.out.println( scheckhead.mitem + " UnsignedUnaryExp2220" ) ;
    if ( scheckhead.mtype == 1 ) {
      
      if ( scheckhead.mnext == null ) return true ;
      else if ( scheckhead.mnext.mtype != 14 && scheckhead.mnext.mtype != 16 &&
                scheckhead.mnext.mtype != 43 && scheckhead.mnext.mtype != 44 ) return true  ;
      else {
        scheckhead = scheckhead.mnext ; // 
        if ( scheckhead.mtype == 14 ) {
          if ( scheckhead.mnext == null ) return true ;
          else scheckhead = scheckhead.mnext ;
          
          if ( scheckhead.mtype == 15 ) return true ;
          else {
            if ( ActualParameterList() ) {
              if ( scheckhead.mnext == null ) return true ;
              else scheckhead = scheckhead.mnext ;
              
              if ( scheckhead.mtype == 15 ) return true ;
              else return false ;
            } // if
            else return false ;
          } // else
        } // if
        
        if ( scheckhead.mtype == 16 ) {
          if ( scheckhead.mnext == null ) return true ;
          else scheckhead = scheckhead.mnext ;
          
          if ( Expression() ) {
            if ( scheckhead.mnext == null ) return true ;
            else scheckhead = scheckhead.mnext ;
            
            if ( scheckhead.mtype == 17 )  {
              if ( scheckhead.mnext == null ) return true ;
              else if ( scheckhead.mnext.mtype != 43 && scheckhead.mnext.mtype != 44 ) return true  ;
              else {
                scheckhead = scheckhead.mnext ;
                
                if ( scheckhead.mtype == 43 || scheckhead.mtype == 44 ) return true ;
                else return false ;
              } // else 
            } // if
            else return false ;
          } // if
          else return false ;
        } // if
        
        if ( scheckhead.mtype == 43 || scheckhead.mtype == 44 ) return true ;
        
        return false ;
      } // else
    } // if
    else if ( scheckhead.mtype == 2 ) return true ;
    else if ( scheckhead.mtype == 14 ) {
      
      if ( scheckhead.mnext == null ) return true ;
      else scheckhead = scheckhead.mnext ;

      if ( Expression() ) {
        if ( scheckhead.mnext == null ) return true ;
        else scheckhead = scheckhead.mnext ;
        
        if ( scheckhead.mtype == 15 ) return true ;
        else return false ;
      } // if
      else return false ;
    } // else if
    
    return false ; 
  } // UnsignedUnaryExp()


  
  // =====================================================================================================
  

  static public void Handlegrammer() {
    // System.out.println( "pp0" );

    Cleaninfunction() ;
    Cleanall() ;
    // System.out.println( slineleft + "==============" );
    Readcommendandstore() ;

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
    for ( int i = sgdefinefunction.size() - 1 ; i >= 0 ; i-- ) {
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
  
  static public boolean Checkfunctioncout( String item ) {
    for ( int i = 0; i < sgdefinefunction.size() ; i++ ) {
      Functiondata pair = sgdefinefunction.get( i );
      // System.out.println( pair.GetStr() ) ;
      if ( !item.equals( "ListAllVariables" ) && !item.equals( "ListVariable" ) &&
           !item.equals( "ListAllFunctions" ) && !item.equals( "ListFunction" ) &&
           pair.GetStr().equals( item ) ) {
        return true;
      } // if
    } // for
    // 沒有找到匹配的字符串，返回 null 或拋出異常
    return false ; // 或者可以選擇拋出一個異常
  } // Checkfunctioncout()
  
  
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
    for ( int i = sgdefinename.size() - 1 ; i >= 0 ; i-- ) {
      Iddata pair = sgdefinename.get( i );
      if ( pair.GetStr().equals( item ) ) {
        return pair.GetNum();
      } // if
    } // for
    // 沒有找到匹配的字符串，返回 null 或拋出異常
    return -999; // 或者可以選擇拋出一個異常
  } // Getnumber()
  
  
  static public int Getsize( String item ) {
    for ( int i = sgdefinename.size() - 1 ; i >= 0 ; i-- ) {
      Iddata pair = sgdefinename.get( i );
      if ( pair.GetStr().equals( item ) ) {
        return pair.Getsize();
      } // if
    } // for
    // 沒有找到匹配的字符串，返回 null 或拋出異常
    return 0 ; // 或者可以選擇拋出一個異常
  } // Getsize()
  
  
  static public void AddData( String str, float num, String type, 
                              int size, String item, boolean infunction, boolean keep ) {
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
      Iddata pair = new Iddata( str, num, type, size, item, infunction, keep );
      // System.out.println( pair.GetNum() ) ;
      sgdefinename.add( pair );
    } // if
  } // AddData()
  
  
  static public String Findtype( String str ) {
    
    for ( int i = sgdefinename.size() - 1 ; i >= 0 ; i-- ) {
      Iddata pair = sgdefinename.get( i ) ;
      if ( pair.GetStr().equals( str ) ) {
        // System.out.println( pair.Gettype() ) ;
        return pair.Gettype();
      } // if
    } // for
    
    // System.out.println( "123" ) ;
    return "nofind" ;
  } // Findtype()
  
  
  
  static public String Getstring( String str ) {
    
    for ( int i = sgdefinename.size() - 1 ; i >= 0 ; i-- ) {
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
      if ( pair.Getinfunction() && !pair.Getkeep() ) {
        sgdefinename.remove( i ) ;
      } // if
    } // for
  } // Cleaninfunction()
  
  
  static public void Cleanallinfunction() {
    for ( int i = 0; i < sgdefinename.size() ; i++ ) {
      Iddata pair = sgdefinename.get( i );
      if ( pair.Getinfunction() ) {
        sgdefinename.remove( i ) ;
      } // if
    } // for
  } // Cleanallinfunction()
  

  static public void Addinifunction() {
    Addfunction( "ListAllVariables", "void" ) ;
    Addfunction( "ListAllFunctions", "void" ) ;
    Addfunction( "ListVariable", "void" ) ;
    Addfunction( "ListFunction", "void" ) ;
  } // Addinifunction()
  
  
  
  private static boolean Handlefunction( String functionname, String item ) {
    Vector<String> sorting = new Vector<String>() ;
    String temp = "" ;
    // System.out.println( item ) ;
    
    if ( functionname.equals( "ListAllVariables" ) ) {
      
      for ( int i = 0; i < sgdefinename.size() ; i++ ) {
        Iddata pair = sgdefinename.get( i );
        if ( !pair.mspecialkeep ) 
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
      // System.out.println( item.length() );
      if ( item.length() >= 3  && item.charAt( 0 ) == '"' && item.charAt( item.length() - 1 ) == '"' ) 
        item = item.substring( 1, item.length() -1 ) ;
      
      // System.out.println( item ) ;
      temp = Findtype( item ) ;
      // System.out.println( temp ) ;
      if ( temp.equals( "int" ) || temp.equals( "char" ) || temp.equals( "float" ) ||
           temp.equals( "string" ) || temp.equals( "bool" ) ) {
        if ( temp.equals( "int" ) ) System.out.println( "int " + item + " ;" );
        if ( temp.equals( "char" ) ) System.out.println( "char " + item + " ;" );
        if ( temp.equals( "float" ) ) System.out.println( "float " + item + " ;" );
        if ( temp.equals( "string" ) ) System.out.println( "string " + item + " ;" );
        if ( temp.equals( "bool" ) ) System.out.println( "bool " + item + " ;" );
      } // if
      else if ( temp.equals( "intarray" ) || temp.equals( "chararray" ) || temp.equals( "floatarray" ) ||
                temp.equals( "stringarray" ) || temp.equals( "boolarray" ) ) {
        if ( temp.equals( "intarray" ) ) 
          System.out.println( "int " + item + "[ " + Getsize( item ) + " ] ;" ) ;
        if ( temp.equals( "chararray" ) ) 
          System.out.println( "char " + item + "[ " + Getsize( item ) + " ] ;" );
        if ( temp.equals( "floatarray" ) ) 
          System.out.println( "float " + item + "[ " + Getsize( item ) + " ] ;" );
        if ( temp.equals( "stringarray" ) ) 
          System.out.println( "string " + item + "[ " + Getsize( item ) + " ] ;" );
        if ( temp.equals( "boolarray" ) ) 
          System.out.println( "bool " + item + "[ " + Getsize( item ) + " ] ;" );
      } // else if
    } // if
    else if ( functionname.equals( "ListAllFunctions" ) ) {
      Displayall();
    } // if
    else if ( functionname.equals( "ListFunction" ) ) {
      if ( item.length() >= 3  && item.charAt( 0 ) == '"' && item.charAt( item.length() - 1 ) == '"' ) 
        item = item.substring( 1, item.length() -1 ) ;
        
      if ( Checkfunctionexist( item ) )
        Prettyprint( item, Gettype( item ), Getinput( item ), Getitem( item ) ) ; 

    } // if
      
    return false ;
  } // Handlefunction()
  
  
  private static void Prettyprint( String name, String type, ArrayList<String> input, 
                                   ArrayList<String> item ) {    
    
    System.out.print( type + " " + name ) ;
    int check = 0 ;
    
    for ( int i = 0 ; i < input.size() ; i++ ) {
      if ( input.get( i ).charAt( 0 ) == '(' ) check = 0 ;
      else check++ ;
    
      if ( input.get( i ).charAt( 0 ) == '(' || input.get( i ).charAt( 0 ) == '[' ) 
        System.out.print( input.get( i ) ) ;
      else if ( check == 1 && input.get( i ).charAt( 0 ) == ')' ) System.out.print( input.get( i ) ) ;
      else if ( input.get( i ).charAt( 0 ) == ',' ) System.out.print( input.get( i ) ) ;
      else System.out.print( " " + input.get( i ) ) ;
    } // for
    
    System.out.print( " " ) ;
    // ============================================================== 中間
    String space = "" ;
    boolean first = true ;
    boolean preif = false ;
    int preifcount = 0 ;
    boolean prewhile = false ;
    int prewhilecount = 0 ;
    
    boolean needspecialspace = false ; // 處理 if 後 沒{}
    int specialspace = 0 ; // 處理 if 後 沒{} 幾個
    boolean needspecialspacebig = false ;
    int countforspecialspace = 0 ;
    boolean specialskip = false ;
    int canspecialskip = 0;

    
    for ( int i = 0 ; i < item.size() ; i++ ) {
      
      // System.out.println(  "" + item.get( i ) + "  " +  ifelsecounter + "  00000" );
      
      if ( preif ) {
        if ( item.get( i ).equals( "(" ) ) preifcount++ ;
        if ( item.get( i ).equals( ")" ) ) preifcount-- ;
      } // if
      
      if ( prewhile ) {
        if ( item.get( i ).equals( "(" ) ) prewhilecount++ ;
        if ( item.get( i ).equals( ")" ) ) prewhilecount-- ;
      } // if
       
      if ( item.get( i ).equals( "if" ) ) preif = true ; 
      
      if ( item.get( i ).equals( "while" ) ) prewhile = true ; 
        
      if ( item.get( i ).equals( "}" ) )  space = space.substring( 2, space.length() ) ;
      
      if ( first ) System.out.print( space ) ;   
      
      if ( first && ( needspecialspace || needspecialspacebig ) ) {
        // System.out.print( specialspace ) ; 
        for ( int x = 0 ; x < specialspace ; x++ ) 
          System.out.print( "  " ) ; 
      } // if
        
      
      if ( !first && item.get( i ).charAt( 0 ) != '['  && item.get( i ) != "," &&
           ! ( item.get( i ) == "(" &&  Checkfunctionexist( item.get( i-1 ) ) 
               || item.get( i-1 ) == "if" || item.get( i-1 ) == "while" ) )  {
        
        if ( item.get( i ) == "++" || item.get( i ) == "--" && i != 0  ) {
          if ( !CheckisIdentifier( item.get( i - 1 ) ) ) 
            System.out.print( " " ) ;
        } // if
        else System.out.print( " " ) ;
      } // if
      
      first = false ;
      
      if ( preif && item.get( i ).equals( ")" ) && preifcount == 0 ) { // 印出   處理no {}
        preif = false ; 
        if ( i + 1 < item.size() && !item.get( i + 1 ).equals( "{" ) && !item.get( i + 1 ).equals( ";" ) ) {
          System.out.println( item.get( i ) ) ; // )
          // System.out.println( specialskip ) ; // )
          first = true ;
          needspecialspace = true ;
          specialspace++ ;
        } // if
        else System.out.print( item.get( i ) ) ;
      } // if
      else if ( prewhile && item.get( i ).equals( ")" ) && prewhilecount == 0 ) { // 印出
        prewhile = false ; 
        if ( i + 1 < item.size() && !item.get( i + 1 ).equals( "{" ) && !item.get( i + 1 ).equals( ";" ) ) {
          System.out.println( item.get( i ) ) ;
          first = true ;
          needspecialspace = true ;
          specialspace++ ; 
        } // if
        else System.out.print( item.get( i ) ) ;
      } // else if
      else if ( i + 1 < item.size() && item.get( i ).equals( "else" ) && !item.get( i + 1 ).equals( "{" ) &&
                !item.get( i + 1 ).equals( ";" )  ) {

        // System.out.println( specialspace ) ;
        for ( int x = 0 ; x < specialspace && !needspecialspacebig ; x++ ) 
          System.out.print( "  " ) ; 
        System.out.println( item.get( i ) ) ;
        
        if ( item.get( i + 1 ).equals( "if" ) ) {
          specialskip = true ;
          specialspace++ ; 
          first = true ;
          canspecialskip = 1 ;
          
          for ( int x = 0 ; x < specialspace ; x++ ) 
            System.out.print( "  " ) ; 
        } // if
        else if ( !needspecialspacebig ) {
          for ( int x = 0 ; x < specialspace + 1 ; x++ ) 
            System.out.print( "  " ) ; 
        } // else if
        else {
          for ( int x = 0 ; x < specialspace ; x++ ) 
            System.out.print( "  " ) ; 
        } // else 

        
        first = true ;
      } // if
      else System.out.print( item.get( i ) ) ;
      
      
      
      if ( ( item.get( i ) == "++" || item.get( i ) == "--" ) &&  i + 1 < item.size() ) { // 處理++--
        if ( CheckisIdentifier( item.get( i + 1 ) ) ) {
          i++ ;
          System.out.print( item.get( i ) ) ;
        } // if
      } // if
      
      if ( item.get( i ) == "("  &&  i + 1 < item.size() ) { // 處理()
        if ( item.get( i + 1 ).equals( ")" ) )  {
          if ( preif ) preifcount-- ;
          if ( prewhile ) prewhilecount-- ;
          i++ ;
          System.out.print( item.get( i ) ) ;
        } // if
      } // if
      
      if ( item.get( i ).equals( "{" ) ) {
        space = space + "  " ;
        System.out.println( "" ) ;
        first = true ;
        if ( needspecialspace ) {
          needspecialspace = false ;
          needspecialspacebig = true ;
        } // if
        
        if ( needspecialspacebig ) countforspecialspace++;
      } // if
      
      if ( item.get( i ).equals( "}" ) ) {
        System.out.println( "" ) ;
        first = true ;
        if ( needspecialspacebig ) countforspecialspace-- ;
        if ( needspecialspacebig && countforspecialspace == 0 ) {
          needspecialspacebig = false ;
          needspecialspace = false ;
          specialspace-- ;
        } // if
      } // if
      
      if ( item.get( i ).equals( ";" ) ) {
        System.out.println( "" ) ;
        first = true ;
        if ( specialskip && i + 1 < item.size() && !item.get( i + 1 ).equals( "else" ) ) {
          // System.out.println( specialspace + "12312313" ) ;
          specialskip = false ;
          specialspace-- ;
          canspecialskip = 0 ;
        } // if
        else if ( specialskip && i + 1 < item.size() && item.get( i + 1 ).equals( "else" ) 
                  && canspecialskip == 1 ) {
          // System.out.println( specialspace + "12312313" ) ;
          canspecialskip = 0 ;
        } // else if 
        else if ( specialskip && i + 1 < item.size() && item.get( i + 1 ).equals( "else" ) 
                  && canspecialskip == 0 ) {
          // System.out.println( specialspace + "12312313" ) ;
          specialskip = false ;
          if ( specialspace >= 3 ) specialspace = specialspace - 2  ;
          else specialspace = 1 ;
          canspecialskip = 0 ;
        } // else if
        
        // System.out.print( "===" + space + "===" ) ;   
        
        if ( specialspace > 0 && !needspecialspacebig ) { 
          specialspace-- ;
        } // if
        
        
        if ( needspecialspacebig && needspecialspace ) specialspace-- ;
        
        needspecialspace = false ;
          
      } // if
      
    } // for
    
  } // Prettyprint()
  
  
  
  // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
  public static boolean Justpass( ListNode start, ListNode end, 
                                  boolean notininquotation, boolean needprint, boolean mspecialkeep ) {
    ListNode temp ;
    boolean checkhasext = false ;
    String tempstr ;
    String tempstr2 ;
    boolean isfunction = false ;
    
    if ( start == null && scanner.hasNext() ) {
      return true ;
    } // if
    // System.out.println( start.mitem + "0000000000000000000000000") ;
    // for ( ListNode tmp = start; tmp != end ; tmp = tmp.mnext ) 
      // System.out.println( tmp.mitem ) ;
     
    if ( start == null ) return false ;
    // System.out.println( " " ) ;
    if ( notininquotation && needprint ) System.out.print( "> " ) ;
    
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
        } // if
      } // if
    } // if
    // System.out.println( "123" ) ;
    
    boolean finish = false ;
    for ( ListNode current = start; !finish && current != null && current != end  ; 
          current = current.mnext ) {
      // System.out.println( current.mitem ) ;

      if ( current.mtype == 3 || current.mtype == 4 || current.mtype == 5 || 
           current.mtype == 6 || current.mtype == 7 || current.mtype == 8 ) { // define
  
        tempstr = current.mitem ; // type
        current = current.mnext ;
        
        // System.out.println( current.mitem ) ;
        while ( !isfunction && current != null && current != end ) {
          
          if ( current.mtype == 1 ) {
            checkhasext = Checkexist( current.mitem ) ;
            
            if ( current.mnext.mtype == 16 ) {
              
              temp = current.mnext ; // [
              temp = temp.mnext ; // size

              String size = temp.mitem ;

              if ( Isallnum( size ) ) {
                AddData( current.mitem, 0, tempstr + "array",
                         Integer.parseInt( size ), "", !notininquotation, mspecialkeep ) ;
              } // if
              else { // 該找   偷懶
                AddData( current.mitem, 0, tempstr + "array",
                         5, "", !notininquotation, mspecialkeep ) ;
              } // else 
            } // if
            else if ( current.mnext.mitem.equals( "," ) ) {
              AddData( current.mitem, 0, tempstr, 1, "", !notininquotation, mspecialkeep );
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
              
              Addfunctioninput( current.mitem, temp.mitem ) ; // input
               
              temp = temp.mnext ; // {
                  
              while ( temp != null ) {
                Addfunctionitem( current.mitem, temp.mitem ) ; // process
                temp = temp.mnext ;
              } // while
              

              current.mitem = current.mitem + "()" ;
              
              isfunction = true ;      
              finish = true ; 
            } // else if
            else {
              AddData( current.mitem, 0, tempstr, 1, "", !notininquotation, mspecialkeep );
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
      } // if
      else if ( current.mtype == 1 && Checkfunctionexist( current.mitem ) ) { // 
        tempstr = current.mitem ; // functionname
        
        if ( current.mnext != null ) current = current.mnext ;
        if ( current.mitem.equals( "(" ) ) {
          if ( current.mnext != null ) current = current.mnext ;
          if ( current.mitem.equals( ")" ) ) {
            if ( current.mnext != null ) current = current.mnext ;
            if ( current.mitem.equals( ";" ) ) {
              // System.out.println( "CompoundStatement" ) ;
              Handlefunction( tempstr, "" ) ;
            } // if
          } // if
          else if ( current.mtype == 2 || current.mtype == 1 ) {
            tempstr2 = current.mitem ;
            if ( current.mnext != null ) current = current.mnext ;
            if ( current.mitem.equals( ")" ) ) {
              if ( current.mnext != null ) current = current.mnext ;
              if ( current.mitem.equals( ";" ) ) {
                // System.out.println( "CompoundStatement" ) ;
                Handlefunction( tempstr, tempstr2 ) ;
              } // if
            } // if
          } // else if
        } // if
        
        finish = true ;
        
        if ( notininquotation && needprint ) System.out.println( "Statement executed ..." ) ;
      } // else if
      else {
        finish = true ;
        
        if ( notininquotation && needprint ) 
          System.out.println( "Statement executed ..." ) ;
        
      } // else 
    } // for
    
    // System.out.println( "0000" ) ;
    return true ;
  } // Justpass()
  
  
  
  public static void main( String[] args ) {
    System.out.println( "Our-C running ..." );
  
    int testnum = scanner.nextInt();
    scanner.nextLine();
    sprintroad = false ; 
    scopytestnum = testnum;

    if ( testnum != 0 ) {
      Addinifunction() ;
      Handlegrammer() ;
      ListNode end = scommandhead ;
      while ( end != null && end.mnext != null ) end = end.mnext ;
      while ( Justpass( scommandhead, end, true, true, false ) ) {
        scommandhead = null ;
        Handlegrammer() ;
        if ( scommandhead != null ) end = scommandhead ;
        while ( end != null && end.mnext != null ) end = end.mnext ;
      } // while
    } // if
    
    scanner.close();  
    System.out.println( "Our-C exited ..." );
  } // main()
} // class Main()
