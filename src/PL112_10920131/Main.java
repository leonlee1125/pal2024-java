package PL112_10920131;

class Main { // 注意類別名稱需要跟.java檔名相同

	static class ListNode {
		int type;
		String item;
		ListNode next;

		ListNode(int type, String item) {
			this.type = type;
			this.item = item;
			this.next = null;
		}
	}

	private ListNode commandhead ;
	
	
  public boolean Checkisint( String token ) {
  	boolean allnum = true ;
    int intnum = 0 ;
    if ( token.charAt(0) == '+' || token.charAt(0) == '-' ) {
      for ( int i = 1 ; i < token.length() ; i++ ) {
        if ( token.charAt(i) < 48 || token.charAt(i) > 57 ) allnum = false ;
        else intnum++;
      } // for
    } // if
    else {
      for ( int i = 0 ; i < token.length() ; i++ ) {
        if ( token.charAt(i) < 48 || token.charAt(i) > 57 ) allnum = false ;
        else intnum++;
      } // for
    } // else

    if ( allnum && intnum != 0 ) return true ;
    else return false ;
  } // Checkisint()

  
  
  boolean Checkisfloat( String token ) {
  	boolean hasdot = false ;
  	boolean allnum = true ;
    int countnum = 0 ;
    if ( token.charAt(0) == '+' || token.charAt(0)== '-' ) {
      for ( int i = 1 ; i < token.length() ; i++ ) {
        if ( hasdot && token.charAt(i) == 46 ) return false ;
        if ( token.charAt(i) == 46 ) hasdot = true ;
        if ( ( token.charAt(i) < 47 || token.charAt(i) > 58 ) && token.charAt(i) != 46 ) allnum = false ;
        if ( token.charAt(i) > 47 && token.charAt(i) < 58 ) countnum++;
      } // for
    } // if
    else {
      for ( int i = 0 ; i < token.length() ; i++ ) {
        if ( hasdot && token.charAt(i) == 46 ) return false ;
        if ( token.charAt(i) == 46 ) hasdot = true ;
        if ( ( token.charAt(i) < 47 || token.charAt(i) > 58 ) && token.charAt(i) != 46 ) allnum = false ;
        if ( token.charAt(i) > 47 && token.charAt(i) < 58 ) countnum++;
      } // for
    } // else

    if ( allnum && hasdot && countnum != 0 ) return true ;
    else return false ;
  } // Checkisfloat()
  
  
  
  
  
  
  
  
  public void scanner () {
  	
  	
  	
  	
  }
  
	public static void main(String[] args) {
		System.out.println("Hello Java~~");
	}
}
