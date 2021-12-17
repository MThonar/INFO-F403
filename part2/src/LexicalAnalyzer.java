/* The following code was generated by JFlex 1.7.0 *//*


*/
/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.7.0
 * from the specification file <tt>src/LexicalAnalyzer.flex</tt>
 *//*

class LexicalAnalyzer {

  */
/** This character denotes the end of file *//*

  public static final int YYEOF = -1;

  */
/** initial size of the lookahead buffer *//*

  private static final int ZZ_BUFFERSIZE = 16384;

  */
/** lexical states *//*

  public static final int YYINITIAL = 0;

  */
/**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   *//*

  private static final int ZZ_LEXSTATE[] = { 
     0, 0
  };

  */
/**
   * Translates characters to character classes
   *//*

  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\13\1\7\1\10\1\10\1\10\22\0\1\13\7\0\1\22"+
    "\1\23\1\20\1\16\1\0\1\17\1\0\1\21\1\3\11\4\1\14"+
    "\1\24\1\47\1\15\1\46\2\0\2\1\1\11\13\1\1\12\13\1"+
    "\6\0\1\41\1\42\1\5\1\36\1\31\1\26\1\43\1\30\1\25"+
    "\2\2\1\33\1\45\1\32\1\6\1\40\1\2\1\37\1\34\1\27"+
    "\2\2\1\35\1\2\1\44\1\2\12\0\1\10\u1fa2\0\1\10\1\10"+
    "\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\udfe6\0";

  */
/**
   * Translates characters to character classes
   *//*

  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  */
/**
   * Translates DFA states to action switch labels.
   *//*

  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\2\1\2\2\1\1\1\3\1\1\1\0\1\4"+
    "\1\5\1\6\1\7\1\10\1\11\1\12\1\13\12\1"+
    "\1\14\1\15\1\1\1\0\1\16\1\17\2\1\1\20"+
    "\5\1\1\21\3\1\1\22\1\3\1\0\1\23\2\1"+
    "\1\24\1\1\1\25\4\1\1\3\1\26\1\27\3\1"+
    "\1\30\1\1\1\31\2\1\1\32\2\1\1\33\1\34"+
    "\1\35\1\36\2\1\1\37";

  private static int [] zzUnpackAction() {
    int [] result = new int[79];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       */
/* index in packed string  *//*

    int j = offset;  */
/* index in unpacked array *//*

    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  */
/**
   * Translates a state to a row index in the transition table
   *//*

  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\50\0\120\0\50\0\170\0\240\0\50\0\310"+
    "\0\360\0\50\0\50\0\50\0\50\0\50\0\50\0\50"+
    "\0\50\0\u0118\0\u0140\0\u0168\0\u0190\0\u01b8\0\u01e0\0\u0208"+
    "\0\u0230\0\u0258\0\u0280\0\50\0\50\0\u02a8\0\u02d0\0\50"+
    "\0\120\0\u02f8\0\u0320\0\120\0\u0348\0\u0370\0\u0398\0\u03c0"+
    "\0\u03e8\0\120\0\u0410\0\u0438\0\u0460\0\120\0\u0488\0\u04b0"+
    "\0\120\0\u04d8\0\u0500\0\u0528\0\u0550\0\120\0\u0578\0\u05a0"+
    "\0\u05c8\0\u05f0\0\u02d0\0\120\0\120\0\u0618\0\u0640\0\u0668"+
    "\0\120\0\u0690\0\120\0\u06b8\0\u06e0\0\120\0\u0708\0\u0730"+
    "\0\120\0\120\0\120\0\120\0\u0758\0\u0780\0\120";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[79];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  */
/* index in packed string  *//*

    int j = offset;  */
/* index in unpacked array *//*

    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  */
/**
   * The transition table of the DFA
   *//*

  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\0\1\2\1\3\1\4\1\5\1\6\1\3\1\7"+
    "\1\0\1\10\1\2\1\7\1\11\1\12\1\13\1\14"+
    "\1\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24"+
    "\1\3\1\25\1\26\2\3\1\27\1\30\1\31\1\32"+
    "\1\3\1\33\3\3\1\34\1\35\51\0\6\3\2\0"+
    "\2\3\12\0\21\3\5\0\2\5\44\0\5\3\1\36"+
    "\2\0\2\3\12\0\21\3\14\0\1\37\52\0\1\40"+
    "\33\0\6\3\2\0\2\3\12\0\1\3\1\41\17\3"+
    "\3\0\5\3\1\42\2\0\2\3\12\0\12\3\1\43"+
    "\6\3\3\0\5\3\1\44\2\0\2\3\12\0\3\3"+
    "\1\45\15\3\3\0\6\3\2\0\2\3\12\0\5\3"+
    "\1\46\1\47\12\3\3\0\5\3\1\50\2\0\2\3"+
    "\12\0\21\3\3\0\6\3\2\0\2\3\12\0\3\3"+
    "\1\51\15\3\3\0\5\3\1\52\2\0\2\3\12\0"+
    "\21\3\3\0\6\3\2\0\2\3\12\0\4\3\1\53"+
    "\14\3\3\0\6\3\2\0\2\3\12\0\12\3\1\54"+
    "\6\3\3\0\6\3\2\0\2\3\12\0\4\3\1\55"+
    "\12\3\1\56\1\3\2\0\1\57\6\36\2\0\2\36"+
    "\12\57\21\36\2\57\7\37\2\0\1\60\36\37\1\0"+
    "\6\3\2\0\2\3\12\0\12\3\1\61\6\3\3\0"+
    "\5\3\1\62\2\0\2\3\12\0\21\3\3\0\6\3"+
    "\2\0\2\3\12\0\4\3\1\63\14\3\3\0\6\3"+
    "\2\0\2\3\12\0\11\3\1\64\7\3\3\0\6\3"+
    "\2\0\2\3\12\0\7\3\1\65\11\3\3\0\6\3"+
    "\2\0\2\3\12\0\2\3\1\66\16\3\3\0\6\3"+
    "\2\0\2\3\12\0\1\67\20\3\3\0\6\3\2\0"+
    "\2\3\12\0\14\3\1\70\4\3\3\0\6\3\2\0"+
    "\2\3\12\0\1\71\20\3\3\0\6\3\2\0\2\3"+
    "\12\0\16\3\1\72\2\3\2\0\7\57\2\0\37\57"+
    "\7\37\2\0\1\60\1\73\35\37\1\0\6\3\2\0"+
    "\2\3\12\0\20\3\1\74\3\0\6\3\2\0\2\3"+
    "\12\0\5\3\1\75\13\3\3\0\6\3\2\0\2\3"+
    "\12\0\1\76\1\77\6\3\1\100\10\3\3\0\6\3"+
    "\2\0\2\3\12\0\4\3\1\101\14\3\3\0\6\3"+
    "\2\0\2\3\12\0\6\3\1\102\12\3\3\0\6\3"+
    "\2\0\2\3\12\0\11\3\1\103\7\3\3\0\6\3"+
    "\2\0\2\3\12\0\5\3\1\104\13\3\3\0\6\3"+
    "\2\0\2\3\12\0\1\105\20\3\3\0\6\3\2\0"+
    "\2\3\12\0\1\3\1\106\17\3\3\0\5\3\1\107"+
    "\2\0\2\3\12\0\21\3\3\0\6\3\2\0\2\3"+
    "\12\0\3\3\1\110\15\3\3\0\6\3\2\0\2\3"+
    "\12\0\4\3\1\111\14\3\3\0\6\3\2\0\2\3"+
    "\12\0\2\3\1\112\16\3\3\0\6\3\2\0\2\3"+
    "\12\0\5\3\1\113\13\3\3\0\6\3\2\0\2\3"+
    "\12\0\12\3\1\114\6\3\3\0\6\3\2\0\2\3"+
    "\12\0\1\115\20\3\3\0\6\3\2\0\2\3\12\0"+
    "\6\3\1\116\12\3\3\0\6\3\2\0\2\3\12\0"+
    "\4\3\1\117\14\3\2\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[1960];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       */
/* index in packed string  *//*

    int j = offset;  */
/* index in unpacked array *//*

    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  */
/* error codes *//*

  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  */
/* error messages for the codes above *//*

  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  */
/**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   *//*

  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\1\1\1\11\2\1\1\11\1\1\1\0"+
    "\10\11\12\1\2\11\1\1\1\0\1\11\17\1\1\0"+
    "\37\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[79];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       */
/* index in packed string  *//*

    int j = offset;  */
/* index in unpacked array *//*

    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  */
/** the input device *//*

  private java.io.Reader zzReader;

  */
/** the current state of the DFA *//*

  private int zzState;

  */
/** the current lexical state *//*

  private int zzLexicalState = YYINITIAL;

  */
/** this buffer contains the current text to be matched and is
      the source of the yytext() string *//*

  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  */
/** the textposition at the last accepting state *//*

  private int zzMarkedPos;

  */
/** the current text position in the buffer *//*

  private int zzCurrentPos;

  */
/** startRead marks the beginning of the yytext() string in the buffer *//*

  private int zzStartRead;

  */
/** endRead marks the last character in the buffer, that has been read
      from input *//*

  private int zzEndRead;

  */
/** number of newlines encountered up to the start of the matched text *//*

  private int yyline;

  */
/** the number of characters up to the start of the matched text *//*

  private int yychar;

  */
/**
   * the number of characters from the last newline up to the start of the 
   * matched text
   *//*

  private int yycolumn;

  */
/**
   * zzAtBOL == true iff the scanner is currently at the beginning of a line
   *//*

  private boolean zzAtBOL = true;

  */
/** zzAtEOF == true iff the scanner is at the EOF *//*

  private boolean zzAtEOF;

  */
/** denotes if the user-EOF-code has already been executed *//*

  private boolean zzEOFDone;
  
  */
/**
   * The number of occupied positions in zzBuffer beyond zzEndRead.
   * When a lead/high surrogate has been read from the input stream
   * into the final zzBuffer position, this will have a value of 1;
   * otherwise, it will have a value of 0.
   *//*

  private int zzFinalHighSurrogate = 0;


  */
/**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   *//*

  LexicalAnalyzer(java.io.Reader in) {
    this.zzReader = in;
  }


  */
/**
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   *//*

  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x110000];
    int i = 0;  */
/* index in packed string  *//*

    int j = 0;  */
/* index in unpacked array *//*

    while (i < 154) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  */
/**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   *//*

  private boolean zzRefill() throws java.io.IOException {

    */
/* first: make room (if you can) *//*

    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      */
/* translate stored positions *//*

      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    */
/* is the buffer big enough? *//*

    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      */
/* if not: blow it up *//*

      char newBuffer[] = new char[zzBuffer.length*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    */
/* fill the buffer with new input *//*

    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    */
/* not supposed to occur according to specification of java.io.Reader *//*

    if (numRead == 0) {
      throw new java.io.IOException("Reader returned 0 characters. See JFlex examples for workaround.");
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      */
/* If numRead == requested, we might have requested to few chars to
         encode a full Unicode character. We assume that a Reader would
         otherwise never return half characters. *//*

      if (numRead == requested) {
        if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        }
      }
      */
/* potentially more input available *//*

      return false;
    }

    */
/* numRead < 0 ==> end of stream *//*

    return true;
  }

    
  */
/**
   * Closes the input stream.
   *//*

  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            */
/* indicate end of file *//*

    zzEndRead = zzStartRead;  */
/* invalidate buffer    *//*


    if (zzReader != null)
      zzReader.close();
  }


  */
/**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader   the new input stream 
   *//*

  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    zzFinalHighSurrogate = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE)
      zzBuffer = new char[ZZ_BUFFERSIZE];
  }


  */
/**
   * Returns the current lexical state.
   *//*

  public final int yystate() {
    return zzLexicalState;
  }


  */
/**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   *//*

  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  */
/**
   * Returns the text matched by the current regular expression.
   *//*

  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  */
/**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   *//*

  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  */
/**
   * Returns the length of the matched text region.
   *//*

  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  */
/**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   *//*

  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  */
/**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   *//*

  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  */
/**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   *//*

  public Symbol nextToken() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':  // fall through
        case '\u000C':  // fall through
        case '\u0085':  // fall through
        case '\u2028':  // fall through
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn += zzCharCount;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
          { 		return new Symbol(LexicalUnit.END_OF_STREAM, yyline, yycolumn, yytext());
 }
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1: 
            { return new Symbol(LexicalUnit.VARNAME, yyline, yycolumn, yytext());
            } 
            // fall through
          case 32: break;
          case 2: 
            { return new Symbol(LexicalUnit.NUMBER, yyline, yycolumn, yytext());
            } 
            // fall through
          case 33: break;
          case 3: 
            { 
            } 
            // fall through
          case 34: break;
          case 4: 
            { return new Symbol(LexicalUnit.EQUAL, yyline, yycolumn, yytext());
            } 
            // fall through
          case 35: break;
          case 5: 
            { return new Symbol(LexicalUnit.PLUS, yyline, yycolumn, yytext());
            } 
            // fall through
          case 36: break;
          case 6: 
            { return new Symbol(LexicalUnit.MINUS, yyline, yycolumn, yytext());
            } 
            // fall through
          case 37: break;
          case 7: 
            { return new Symbol(LexicalUnit.TIMES, yyline, yycolumn, yytext());
            } 
            // fall through
          case 38: break;
          case 8: 
            { return new Symbol(LexicalUnit.DIVIDE, yyline, yycolumn, yytext());
            } 
            // fall through
          case 39: break;
          case 9: 
            { return new Symbol(LexicalUnit.LPAREN, yyline, yycolumn, yytext());
            } 
            // fall through
          case 40: break;
          case 10: 
            { return new Symbol(LexicalUnit.RPAREN, yyline, yycolumn, yytext());
            } 
            // fall through
          case 41: break;
          case 11: 
            { return new Symbol(LexicalUnit.SEMICOLON, yyline, yycolumn, yytext());
            } 
            // fall through
          case 42: break;
          case 12: 
            { return new Symbol(LexicalUnit.GREATER, yyline, yycolumn, yytext());
            } 
            // fall through
          case 43: break;
          case 13: 
            { return new Symbol(LexicalUnit.SMALLER, yyline, yycolumn, yytext());
            } 
            // fall through
          case 44: break;
          case 14: 
            { return new Symbol(LexicalUnit.ASSIGN, yyline, yycolumn, yytext());
            } 
            // fall through
          case 45: break;
          case 15: 
            { return new Symbol(LexicalUnit.IF, yyline, yycolumn, yytext());
            } 
            // fall through
          case 46: break;
          case 16: 
            { return new Symbol(LexicalUnit.TO, yyline, yycolumn, yytext());
            } 
            // fall through
          case 47: break;
          case 17: 
            { return new Symbol(LexicalUnit.DO, yyline, yycolumn, yytext());
            } 
            // fall through
          case 48: break;
          case 18: 
            { return new Symbol(LexicalUnit.BY, yyline, yycolumn, yytext());
            } 
            // fall through
          case 49: break;
          case 19: 
            { return new Symbol(LexicalUnit.FOR, yyline, yycolumn, yytext());
            } 
            // fall through
          case 50: break;
          case 20: 
            { return new Symbol(LexicalUnit.END, yyline, yycolumn, yytext());
            } 
            // fall through
          case 51: break;
          case 21: 
            { return new Symbol(LexicalUnit.NOT, yyline, yycolumn, yytext());
            } 
            // fall through
          case 52: break;
          case 22: 
            { return new Symbol(LexicalUnit.FROM, yyline, yycolumn, yytext());
            } 
            // fall through
          case 53: break;
          case 23: 
            { return new Symbol(LexicalUnit.THEN, yyline, yycolumn, yytext());
            } 
            // fall through
          case 54: break;
          case 24: 
            { return new Symbol(LexicalUnit.ELSE, yyline, yycolumn, yytext());
            } 
            // fall through
          case 55: break;
          case 25: 
            { return new Symbol(LexicalUnit.READ, yyline, yycolumn, yytext());
            } 
            // fall through
          case 56: break;
          case 26: 
            { return new Symbol(LexicalUnit.ENDIF, yyline, yycolumn, yytext());
            } 
            // fall through
          case 57: break;
          case 27: 
            { return new Symbol(LexicalUnit.WHILE, yyline, yycolumn, yytext());
            } 
            // fall through
          case 58: break;
          case 28: 
            { return new Symbol(LexicalUnit.PRINT, yyline, yycolumn, yytext());
            } 
            // fall through
          case 59: break;
          case 29: 
            { return new Symbol(LexicalUnit.BEG, yyline, yycolumn, yytext());
            } 
            // fall through
          case 60: break;
          case 30: 
            { return new Symbol(LexicalUnit.ENDFOR, yyline, yycolumn, yytext());
            } 
            // fall through
          case 61: break;
          case 31: 
            { return new Symbol(LexicalUnit.ENDWHILE, yyline, yycolumn, yytext());
            } 
            // fall through
          case 62: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }


}
*/
