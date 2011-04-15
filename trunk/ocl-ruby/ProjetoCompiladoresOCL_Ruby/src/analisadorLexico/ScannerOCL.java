/* The following code was generated by JFlex 1.4.3 on 03/04/11 20:58 */

/*Secao de codigo do usuario*/

package analisadorLexico;

/*********************************************************
* Universidade Federal de Campina Grande - UFCG          *
* Centro de Engenharia Eletrica e Informatica - CEEI     *
* Departamento de Sistemas e Computacao - DSC            *
*                                                        *
* Projeto da disciplina de Compiladores, 2011.1          *
* Analisador Lexico para OCL                             *
*                                                        *
* Grupo: Francisco Demontie dos Santos Junior - 20911084 *
*        Izabela Vanessa de Almeida Melo - 20811018      *
*        Savyo Igor da Nobrega Santos - 20811034         *
*********************************************************/

import java_cup.runtime.*;
import java.util.List;
import java.util.ArrayList;
import analisadorSintatico.Symbols;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 03/04/11 20:58 from the specification file
 * <tt>C:/Users/Junior/Documents/COMPILADORES - Projeto/Projeto/ocl.flex</tt>
 */
public class ScannerOCL implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int STRING = 2;
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1, 1
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\1\1\3\1\0\1\1\1\3\22\0\1\1\6\0\1\36"+
    "\1\50\1\51\1\57\1\43\1\55\1\2\1\41\1\60\12\37\1\56"+
    "\1\0\1\62\1\63\1\61\1\0\1\32\1\13\1\25\1\27\1\40"+
    "\1\42\3\40\1\17\5\40\1\4\2\40\1\15\1\23\1\7\6\40"+
    "\1\0\1\33\2\0\1\40\1\0\1\16\1\34\1\5\1\47\1\12"+
    "\1\35\1\21\1\46\1\24\2\40\1\6\1\64\1\14\1\26\1\11"+
    "\1\30\1\22\1\44\1\20\1\31\2\40\1\45\1\10\1\40\1\52"+
    "\1\54\1\53\uff82\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\2\0\1\1\1\2\1\3\20\4\1\1\1\4\1\5"+
    "\1\6\1\7\1\10\2\4\1\11\1\12\1\13\1\14"+
    "\1\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24"+
    "\1\25\1\1\1\26\1\0\1\27\17\4\1\30\3\4"+
    "\1\31\1\4\1\0\1\4\1\0\1\32\2\4\1\33"+
    "\1\34\1\35\1\36\2\4\1\37\3\4\1\40\1\4"+
    "\1\41\4\4\1\42\5\4\1\0\1\4\1\43\1\4"+
    "\1\44\3\4\1\45\1\46\1\4\1\47\1\4\1\50"+
    "\1\51\6\4\1\52\1\0\1\53\3\4\1\54\7\4"+
    "\1\0\1\43\1\4\1\55\2\4\1\56\4\4\1\57"+
    "\1\4\1\60\3\4";

  private static int [] zzUnpackAction() {
    int [] result = new int[148];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\65\0\152\0\152\0\237\0\324\0\u0109\0\u013e"+
    "\0\u0173\0\u01a8\0\u01dd\0\u0212\0\u0247\0\u027c\0\u02b1\0\u02e6"+
    "\0\u031b\0\u0350\0\u0385\0\u03ba\0\u03ef\0\u0424\0\u0459\0\152"+
    "\0\u048e\0\u04c3\0\152\0\u04f8\0\u052d\0\152\0\152\0\152"+
    "\0\152\0\152\0\152\0\u0562\0\152\0\152\0\u0597\0\u05cc"+
    "\0\152\0\152\0\u0601\0\152\0\u0636\0\152\0\u066b\0\u06a0"+
    "\0\u06d5\0\u070a\0\u073f\0\u0774\0\u07a9\0\u07de\0\u0813\0\u0848"+
    "\0\u087d\0\u08b2\0\u08e7\0\u091c\0\u0951\0\u013e\0\u0986\0\u09bb"+
    "\0\u09f0\0\u013e\0\u0a25\0\u0a5a\0\u0a8f\0\u0ac4\0\152\0\u0af9"+
    "\0\u0b2e\0\152\0\152\0\152\0\152\0\u0b63\0\u0b98\0\u013e"+
    "\0\u0bcd\0\u0c02\0\u0c37\0\u013e\0\u0c6c\0\u013e\0\u0ca1\0\u0cd6"+
    "\0\u0d0b\0\u0d40\0\u013e\0\u0d75\0\u0daa\0\u0ddf\0\u0e14\0\u0e49"+
    "\0\u0e7e\0\u0eb3\0\u0ee8\0\u0f1d\0\u013e\0\u0f52\0\u0f87\0\u0fbc"+
    "\0\u013e\0\u013e\0\u0ff1\0\u013e\0\u1026\0\u013e\0\u013e\0\u105b"+
    "\0\u1090\0\u10c5\0\u10fa\0\u112f\0\u1164\0\152\0\u1199\0\u013e"+
    "\0\u11ce\0\u1203\0\u1238\0\u013e\0\u126d\0\u12a2\0\u12d7\0\u130c"+
    "\0\u1341\0\u1376\0\u13ab\0\u13e0\0\u13e0\0\u1415\0\u013e\0\u144a"+
    "\0\u147f\0\u013e\0\u14b4\0\u14e9\0\u151e\0\u1553\0\u013e\0\u1588"+
    "\0\u013e\0\u15bd\0\u15f2\0\u1627";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[148];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\3\1\4\1\5\1\4\1\6\1\7\3\10\1\11"+
    "\1\12\1\10\1\13\1\14\1\15\1\16\1\17\1\10"+
    "\1\20\1\21\1\22\1\23\1\24\1\25\2\10\1\26"+
    "\1\3\1\10\1\27\1\30\1\31\1\10\1\32\1\10"+
    "\1\33\1\34\1\35\2\10\1\36\1\37\1\40\1\41"+
    "\1\42\1\43\1\44\1\45\1\46\1\47\1\50\1\51"+
    "\1\10\33\52\1\53\2\52\1\54\26\52\67\0\1\55"+
    "\56\0\1\56\7\0\1\10\1\57\24\10\2\0\2\10"+
    "\1\0\2\10\1\0\1\10\1\0\4\10\14\0\1\10"+
    "\4\0\22\10\1\60\3\10\2\0\2\10\1\0\2\10"+
    "\1\0\1\10\1\0\4\10\14\0\1\10\4\0\26\10"+
    "\2\0\2\10\1\0\2\10\1\0\1\10\1\0\4\10"+
    "\14\0\1\10\4\0\16\10\1\61\3\10\1\62\3\10"+
    "\2\0\2\10\1\0\2\10\1\0\1\10\1\0\4\10"+
    "\14\0\1\10\4\0\2\10\1\63\5\10\1\64\15\10"+
    "\2\0\2\10\1\0\2\10\1\0\1\10\1\0\4\10"+
    "\14\0\1\10\4\0\22\10\1\65\3\10\2\0\2\10"+
    "\1\0\2\10\1\0\1\10\1\0\4\10\14\0\1\10"+
    "\4\0\6\10\1\66\17\10\2\0\2\10\1\0\2\10"+
    "\1\0\1\10\1\0\4\10\14\0\1\10\4\0\10\10"+
    "\1\67\15\10\2\0\2\10\1\0\2\10\1\0\1\10"+
    "\1\0\4\10\14\0\1\10\4\0\10\10\1\70\15\10"+
    "\2\0\2\10\1\0\2\10\1\0\1\10\1\0\4\10"+
    "\14\0\1\10\4\0\16\10\1\71\7\10\2\0\2\10"+
    "\1\0\2\10\1\0\1\10\1\0\2\10\1\72\1\10"+
    "\14\0\1\10\4\0\6\10\1\73\17\10\2\0\2\10"+
    "\1\0\2\10\1\0\1\10\1\0\4\10\14\0\1\10"+
    "\4\0\6\10\1\74\5\10\1\75\11\10\2\0\2\10"+
    "\1\0\2\10\1\0\1\10\1\0\4\10\14\0\1\10"+
    "\4\0\26\10\2\0\1\10\1\76\1\0\2\10\1\0"+
    "\1\10\1\0\4\10\14\0\1\77\4\0\12\10\1\100"+
    "\7\10\1\101\3\10\2\0\2\10\1\0\2\10\1\0"+
    "\1\10\1\0\4\10\14\0\1\10\4\0\16\10\1\102"+
    "\7\10\2\0\2\10\1\0\2\10\1\0\1\10\1\0"+
    "\4\10\14\0\1\10\4\0\22\10\1\103\3\10\2\0"+
    "\2\10\1\0\2\10\1\0\1\10\1\0\4\10\14\0"+
    "\1\10\11\0\1\104\57\0\12\10\1\105\13\10\2\0"+
    "\2\10\1\0\2\10\1\0\1\10\1\0\4\10\14\0"+
    "\1\10\37\0\1\31\1\0\1\106\64\0\1\107\27\0"+
    "\6\10\1\110\17\10\2\0\2\10\1\0\2\10\1\0"+
    "\1\10\1\0\4\10\14\0\1\10\4\0\22\10\1\111"+
    "\3\10\2\0\2\10\1\0\2\10\1\0\1\10\1\0"+
    "\4\10\14\0\1\10\56\0\1\112\71\0\1\113\62\0"+
    "\1\114\1\0\1\115\15\0\1\52\3\0\1\52\1\0"+
    "\1\52\10\0\4\52\26\0\3\55\1\4\61\55\4\0"+
    "\2\10\1\116\23\10\2\0\2\10\1\0\2\10\1\0"+
    "\1\10\1\0\4\10\14\0\1\10\4\0\10\10\1\117"+
    "\15\10\2\0\2\10\1\0\2\10\1\0\1\10\1\0"+
    "\4\10\14\0\1\10\4\0\6\10\1\120\17\10\2\0"+
    "\2\10\1\0\2\10\1\0\1\10\1\0\4\10\14\0"+
    "\1\10\4\0\26\10\2\0\2\10\1\0\2\10\1\0"+
    "\1\10\1\0\1\121\3\10\14\0\1\10\4\0\26\10"+
    "\2\0\2\10\1\0\2\10\1\0\1\10\1\0\1\122"+
    "\3\10\14\0\1\10\4\0\26\10\2\0\2\10\1\0"+
    "\2\10\1\0\1\10\1\0\3\10\1\123\14\0\1\10"+
    "\4\0\14\10\1\124\11\10\2\0\2\10\1\0\2\10"+
    "\1\0\1\10\1\0\4\10\14\0\1\10\4\0\12\10"+
    "\1\125\13\10\2\0\2\10\1\0\2\10\1\0\1\10"+
    "\1\0\4\10\14\0\1\10\4\0\26\10\2\0\2\10"+
    "\1\0\2\10\1\0\1\10\1\0\3\10\1\126\14\0"+
    "\1\10\4\0\14\10\1\127\11\10\2\0\2\10\1\0"+
    "\2\10\1\0\1\10\1\0\4\10\14\0\1\10\4\0"+
    "\25\10\1\130\2\0\2\10\1\0\2\10\1\0\1\10"+
    "\1\0\4\10\14\0\1\10\4\0\6\10\1\131\17\10"+
    "\2\0\2\10\1\0\2\10\1\0\1\10\1\0\4\10"+
    "\14\0\1\10\4\0\26\10\2\0\2\10\1\0\2\10"+
    "\1\0\1\10\1\0\1\132\3\10\14\0\1\10\4\0"+
    "\14\10\1\133\7\10\1\134\1\10\2\0\2\10\1\0"+
    "\2\10\1\0\1\10\1\0\4\10\14\0\1\10\4\0"+
    "\16\10\1\135\7\10\2\0\2\10\1\0\2\10\1\0"+
    "\1\10\1\0\4\10\14\0\1\10\4\0\5\10\1\136"+
    "\20\10\2\0\2\10\1\0\2\10\1\0\1\10\1\0"+
    "\4\10\14\0\1\10\4\0\15\10\1\133\10\10\2\0"+
    "\2\10\1\0\2\10\1\0\1\10\1\0\4\10\14\0"+
    "\1\10\4\0\22\10\1\137\3\10\2\0\2\10\1\0"+
    "\2\10\1\0\1\10\1\0\4\10\14\0\1\10\4\0"+
    "\2\10\1\140\23\10\2\0\2\10\1\0\2\10\1\0"+
    "\1\10\1\0\4\10\14\0\1\10\22\0\1\141\46\0"+
    "\2\10\1\142\23\10\2\0\2\10\1\0\2\10\1\0"+
    "\1\10\1\0\4\10\14\0\1\10\37\0\1\143\31\0"+
    "\2\10\1\144\23\10\2\0\2\10\1\0\2\10\1\0"+
    "\1\10\1\0\4\10\14\0\1\10\4\0\16\10\1\145"+
    "\7\10\2\0\2\10\1\0\2\10\1\0\1\10\1\0"+
    "\4\10\14\0\1\10\4\0\3\10\1\146\3\10\1\147"+
    "\16\10\2\0\2\10\1\0\2\10\1\0\1\10\1\0"+
    "\4\10\14\0\1\10\4\0\14\10\1\150\11\10\2\0"+
    "\2\10\1\0\2\10\1\0\1\10\1\0\4\10\14\0"+
    "\1\10\4\0\14\10\1\151\11\10\2\0\2\10\1\0"+
    "\2\10\1\0\1\10\1\0\4\10\14\0\1\10\4\0"+
    "\6\10\1\152\17\10\2\0\2\10\1\0\2\10\1\0"+
    "\1\10\1\0\4\10\14\0\1\10\4\0\20\10\1\153"+
    "\5\10\2\0\2\10\1\0\2\10\1\0\1\10\1\0"+
    "\4\10\14\0\1\10\4\0\2\10\1\154\23\10\2\0"+
    "\2\10\1\0\2\10\1\0\1\10\1\0\4\10\14\0"+
    "\1\10\4\0\6\10\1\155\17\10\2\0\2\10\1\0"+
    "\2\10\1\0\1\10\1\0\4\10\14\0\1\10\4\0"+
    "\6\10\1\156\17\10\2\0\2\10\1\0\2\10\1\0"+
    "\1\10\1\0\4\10\14\0\1\10\4\0\10\10\1\157"+
    "\15\10\2\0\2\10\1\0\2\10\1\0\1\10\1\0"+
    "\4\10\14\0\1\10\4\0\25\10\1\160\2\0\2\10"+
    "\1\0\2\10\1\0\1\10\1\0\4\10\14\0\1\10"+
    "\4\0\25\10\1\161\2\0\2\10\1\0\2\10\1\0"+
    "\1\10\1\0\4\10\14\0\1\10\4\0\20\10\1\162"+
    "\5\10\2\0\2\10\1\0\2\10\1\0\1\10\1\0"+
    "\4\10\14\0\1\10\4\0\2\10\1\163\23\10\2\0"+
    "\2\10\1\0\2\10\1\0\1\10\1\0\4\10\14\0"+
    "\1\10\4\0\2\10\1\164\23\10\2\0\2\10\1\0"+
    "\2\10\1\0\1\10\1\0\4\10\14\0\1\10\4\0"+
    "\2\10\1\165\23\10\2\0\2\10\1\0\2\10\1\0"+
    "\1\10\1\0\4\10\14\0\1\10\12\0\1\166\56\0"+
    "\26\10\2\0\2\10\1\0\2\10\1\0\1\10\1\0"+
    "\1\130\3\10\14\0\1\10\12\0\1\167\24\0\1\143"+
    "\2\0\1\167\26\0\26\10\2\0\1\10\1\170\1\0"+
    "\2\10\1\0\1\10\1\0\4\10\14\0\1\10\4\0"+
    "\4\10\1\171\21\10\2\0\2\10\1\0\2\10\1\0"+
    "\1\10\1\0\4\10\14\0\1\10\4\0\10\10\1\172"+
    "\15\10\2\0\2\10\1\0\2\10\1\0\1\10\1\0"+
    "\4\10\14\0\1\10\4\0\6\10\1\173\17\10\2\0"+
    "\2\10\1\0\2\10\1\0\1\10\1\0\4\10\14\0"+
    "\1\10\4\0\26\10\2\0\1\10\1\174\1\0\2\10"+
    "\1\0\1\10\1\0\4\10\14\0\1\10\4\0\15\10"+
    "\1\175\10\10\2\0\2\10\1\0\2\10\1\0\1\10"+
    "\1\0\4\10\14\0\1\10\4\0\2\10\1\176\23\10"+
    "\2\0\2\10\1\0\2\10\1\0\1\10\1\0\4\10"+
    "\14\0\1\10\4\0\6\10\1\177\17\10\2\0\2\10"+
    "\1\0\2\10\1\0\1\10\1\0\4\10\14\0\1\10"+
    "\4\0\10\10\1\200\15\10\2\0\2\10\1\0\2\10"+
    "\1\0\1\10\1\0\4\10\14\0\1\10\4\0\20\10"+
    "\1\201\5\10\2\0\2\10\1\0\2\10\1\0\1\10"+
    "\1\0\4\10\14\0\1\10\4\0\6\10\1\202\17\10"+
    "\2\0\2\10\1\0\2\10\1\0\1\10\1\0\4\10"+
    "\14\0\1\10\4\0\6\10\1\203\17\10\2\0\2\10"+
    "\1\0\2\10\1\0\1\10\1\0\4\10\14\0\1\10"+
    "\2\0\1\204\34\0\1\205\3\0\1\204\25\0\5\10"+
    "\1\206\20\10\2\0\2\10\1\0\2\10\1\0\1\10"+
    "\1\0\4\10\14\0\1\10\4\0\4\10\1\207\21\10"+
    "\2\0\2\10\1\0\2\10\1\0\1\10\1\0\4\10"+
    "\14\0\1\10\4\0\26\10\2\0\2\10\1\0\2\10"+
    "\1\0\1\10\1\0\1\10\1\210\2\10\14\0\1\10"+
    "\4\0\6\10\1\211\17\10\2\0\2\10\1\0\2\10"+
    "\1\0\1\10\1\0\4\10\14\0\1\10\4\0\14\10"+
    "\1\212\11\10\2\0\2\10\1\0\2\10\1\0\1\10"+
    "\1\0\4\10\14\0\1\10\4\0\10\10\1\213\15\10"+
    "\2\0\2\10\1\0\2\10\1\0\1\10\1\0\4\10"+
    "\14\0\1\10\4\0\15\10\1\154\10\10\2\0\2\10"+
    "\1\0\2\10\1\0\1\10\1\0\4\10\14\0\1\10"+
    "\4\0\6\10\1\214\17\10\2\0\2\10\1\0\2\10"+
    "\1\0\1\10\1\0\4\10\14\0\1\10\4\0\12\10"+
    "\1\215\13\10\2\0\2\10\1\0\2\10\1\0\1\10"+
    "\1\0\4\10\14\0\1\10\4\0\1\10\1\216\24\10"+
    "\2\0\2\10\1\0\2\10\1\0\1\10\1\0\4\10"+
    "\14\0\1\10\37\0\1\205\31\0\6\10\1\207\17\10"+
    "\2\0\2\10\1\0\2\10\1\0\1\10\1\0\4\10"+
    "\14\0\1\10\4\0\14\10\1\217\11\10\2\0\2\10"+
    "\1\0\2\10\1\0\1\10\1\0\4\10\14\0\1\10"+
    "\4\0\16\10\1\154\7\10\2\0\2\10\1\0\2\10"+
    "\1\0\1\10\1\0\4\10\14\0\1\10\4\0\1\10"+
    "\1\220\24\10\2\0\2\10\1\0\2\10\1\0\1\10"+
    "\1\0\4\10\14\0\1\10\4\0\26\10\2\0\2\10"+
    "\1\0\2\10\1\0\1\10\1\0\1\221\3\10\14\0"+
    "\1\10\4\0\10\10\1\154\15\10\2\0\2\10\1\0"+
    "\2\10\1\0\1\10\1\0\4\10\14\0\1\10\4\0"+
    "\14\10\1\222\11\10\2\0\2\10\1\0\2\10\1\0"+
    "\1\10\1\0\4\10\14\0\1\10\4\0\6\10\1\133"+
    "\17\10\2\0\2\10\1\0\2\10\1\0\1\10\1\0"+
    "\4\10\14\0\1\10\4\0\20\10\1\223\5\10\2\0"+
    "\2\10\1\0\2\10\1\0\1\10\1\0\4\10\14\0"+
    "\1\10\4\0\22\10\1\224\3\10\2\0\2\10\1\0"+
    "\2\10\1\0\1\10\1\0\4\10\14\0\1\10\4\0"+
    "\10\10\1\133\15\10\2\0\2\10\1\0\2\10\1\0"+
    "\1\10\1\0\4\10\14\0\1\10";

  private static int [] zzUnpackTrans() {
    int [] result = new int[5724];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\2\0\2\11\23\1\1\11\2\1\1\11\2\1\6\11"+
    "\1\1\2\11\2\1\2\11\1\1\1\11\1\0\1\11"+
    "\25\1\1\0\1\1\1\0\1\11\2\1\4\11\23\1"+
    "\1\0\24\1\1\11\1\0\14\1\1\0\20\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[148];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */
	StringBuilder string;
	public List<String> foundTokens = new ArrayList<String>();
	
	public boolean hasNextToken() {
		return !zzAtEOF;
	}
	
	private Symbol symbol(int type, String token) {
		foundTokens.add("Token encontrado: " + token + "\n");
		return new Symbol(type, yyline, yycolumn);
	}
	
	private Symbol symbol(int type, Object value, String token) {
		foundTokens.add("Token encontrado: " + token + "; Valor: " + value.toString() + "\n");
		return new Symbol(type, yyline, yycolumn, value);
	}
	
	/*Metodo para lancamento de erros lexicos*/
	private void error(int line, String message){
		throw new RuntimeException("Erro lexico na linha " + (line+1) + ": " + message);
	}
	


  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public ScannerOCL(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  public ScannerOCL(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 146) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzCurrentPos*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = zzReader.read(zzBuffer, zzEndRead,
                                            zzBuffer.length-zzEndRead);

    if (numRead > 0) {
      zzEndRead+= numRead;
      return false;
    }
    // unlikely but not impossible: read 0 characters, but not at end of stream    
    if (numRead == 0) {
      int c = zzReader.read();
      if (c == -1) {
        return true;
      } else {
        zzBuffer[zzEndRead++] = (char) c;
        return false;
      }     
    }

	// numRead < 0
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


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
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


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
   */
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


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
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
      for (zzCurrentPosL = zzStartRead; zzCurrentPosL < zzMarkedPosL;
                                                             zzCurrentPosL++) {
        switch (zzBufferL[zzCurrentPosL]) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
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
          yycolumn++;
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


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
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
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 24: 
          { return symbol(Symbols.IF, "IF");
          }
        case 49: break;
        case 26: 
          { return symbol(Symbols.POINT_POINT, "POINT_POINT");
          }
        case 50: break;
        case 41: 
          { return symbol(Symbols.THEN, "THEN");
          }
        case 51: break;
        case 38: 
          { return symbol(Symbols.ELSE, "ELSE");
          }
        case 52: break;
        case 33: 
          { return symbol(Symbols.AND, "AND");
          }
        case 53: break;
        case 34: 
          { return symbol(Symbols.OCL_COLLECTION, yytext(), "OCL_COLLECTION");
          }
        case 54: break;
        case 36: 
          { return symbol(Symbols.XOR, "XOR");
          }
        case 55: break;
        case 31: 
          { return symbol(Symbols.PRE, "PRE");
          }
        case 56: break;
        case 7: 
          { return symbol(Symbols.POINT, "POINT");
          }
        case 57: break;
        case 48: 
          { return symbol(Symbols.IMPLIES, "IMPLIES");
          }
        case 58: break;
        case 9: 
          { return symbol(Symbols.LPAREN, "LPAREN");
          }
        case 59: break;
        case 14: 
          { return symbol(Symbols.COMA, "COMA");
          }
        case 60: break;
        case 16: 
          { return symbol(Symbols.STAR, "STAR");
          }
        case 61: break;
        case 4: 
          { return symbol(Symbols.ID, yytext(), "ID");
          }
        case 62: break;
        case 27: 
          { return symbol(Symbols.FOUR_POINTS, "FOUR_POINTS");
          }
        case 63: break;
        case 45: 
          { return symbol(Symbols.OCL_SUPER_TYPE, yytext(), "OCL_SUPER_TYPE");
          }
        case 64: break;
        case 28: 
          { return symbol(Symbols.GE, "GE");
          }
        case 65: break;
        case 40: 
          { return symbol(Symbols.BOOL, new Boolean(yytext()), "BOOL");
          }
        case 66: break;
        case 10: 
          { return symbol(Symbols.RPAREN, "RPAREN");
          }
        case 67: break;
        case 15: 
          { return symbol(Symbols.POINTS, "POINTS");
          }
        case 68: break;
        case 37: 
          { return symbol(Symbols.POST, "POST");
          }
        case 69: break;
        case 22: 
          { yybegin(YYINITIAL); return symbol(Symbols.STRING, string.toString(), "STRING");
          }
        case 70: break;
        case 20: 
          { return symbol(Symbols.EQ, "EQ");
          }
        case 71: break;
        case 46: 
          { return symbol(Symbols.RESULT, "RESULT");
          }
        case 72: break;
        case 8: 
          { return symbol(Symbols.PLUS, "PLUS");
          }
        case 73: break;
        case 5: 
          { yybegin(STRING); string = new StringBuilder();
          }
        case 74: break;
        case 44: 
          { return symbol(Symbols.ENDIF, "ENDIF");
          }
        case 75: break;
        case 19: 
          { return symbol(Symbols.LT, "LT");
          }
        case 76: break;
        case 17: 
          { return symbol(Symbols.DIV, "DIV");
          }
        case 77: break;
        case 47: 
          { return symbol(Symbols.CONTEXT, "CONTEXT");
          }
        case 78: break;
        case 25: 
          { return symbol(Symbols.OR, "OR");
          }
        case 79: break;
        case 13: 
          { return symbol(Symbols.PIPE, "PIPE");
          }
        case 80: break;
        case 11: 
          { return symbol(Symbols.LCURLY, "LCURLY");
          }
        case 81: break;
        case 3: 
          { return symbol(Symbols.MINUS, "MINUS");
          }
        case 82: break;
        case 21: 
          { string.append(yytext());
          }
        case 83: break;
        case 43: 
          { return symbol(Symbols.SELF, "SELF");
          }
        case 84: break;
        case 42: 
          { return symbol(Symbols.AROBAPRE, "AROBAPRE");
          }
        case 85: break;
        case 35: 
          { return symbol(Symbols.FLOAT, new Double(yytext()), "FLOAT");
          }
        case 86: break;
        case 29: 
          { return symbol(Symbols.NE, "NE");
          }
        case 87: break;
        case 18: 
          { return symbol(Symbols.GT, "GT");
          }
        case 88: break;
        case 1: 
          { error(yyline, "o caracter " + yytext() + " nao eh reconhecido pela linguagem.");
          }
        case 89: break;
        case 12: 
          { return symbol(Symbols.RCURLY, "RCURLY");
          }
        case 90: break;
        case 32: 
          { return symbol(Symbols.NOT, "NOT");
          }
        case 91: break;
        case 39: 
          { return symbol(Symbols.OCL_BASIC_TYPE, yytext(), "OCL_BASIC_TYPE");
          }
        case 92: break;
        case 30: 
          { return symbol(Symbols.LE, "LE");
          }
        case 93: break;
        case 2: 
          { 
          }
        case 94: break;
        case 6: 
          { return symbol(Symbols.INT, new Integer(yytext()), "INT");
          }
        case 95: break;
        case 23: 
          { return symbol(Symbols.ARROW, "ARROW");
          }
        case 96: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            zzDoEOF();
            switch (zzLexicalState) {
            case STRING: {
              error(yyline, "String incompleta.");
            }
            case 149: break;
            default:
              {
                return symbol(Symbols.EOF, "EOF");
              }
            }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }

  /**
   * Runs the scanner on input files.
   *
   * This is a standalone scanner, it will print any unmatched
   * text to System.out unchanged.
   *
   * @param argv   the command line, contains the filenames to run
   *               the scanner on.
   */
  public static void main(String argv[]) {
    if (argv.length == 0) {
      System.out.println("Usage : java ScannerOCL <inputfile>");
    }
    else {
      for (int i = 0; i < argv.length; i++) {
        ScannerOCL scanner = null;
        try {
          scanner = new ScannerOCL( new java.io.FileReader(argv[i]) );
          while ( !scanner.zzAtEOF ) scanner.next_token();
        }
        catch (java.io.FileNotFoundException e) {
          System.out.println("File not found : \""+argv[i]+"\"");
        }
        catch (java.io.IOException e) {
          System.out.println("IO error scanning file \""+argv[i]+"\"");
          System.out.println(e);
        }
        catch (Exception e) {
          System.out.println("Unexpected exception:");
          e.printStackTrace();
        }
      }
    }
  }


}
