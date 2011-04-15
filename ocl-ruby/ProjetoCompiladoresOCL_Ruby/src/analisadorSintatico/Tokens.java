package analisadorSintatico;


public class Tokens {

	public static String getTokenName(int token) {
		String tokenName = null;
		switch (token) {
		case Symbols.OCL_BASIC_TYPE:
			tokenName = "OCL_BASIC_TYPE";
			break;
		case Symbols.GE:
			tokenName = "GE";
			break;
		case Symbols.FOUR_POINTS:
			tokenName = "FOUR_POINTS";
			break;
		case Symbols.XOR:
			tokenName = "XOR";
			break;
		case Symbols.LPAREN:
			tokenName = "LPAREN";
			break;
		case Symbols.INT:
			tokenName = "INT";
			break;
		case Symbols.PRE:
			tokenName = "PRE";
			break;
		case Symbols.MINUS:
			tokenName = "MINUS";
			break;
		case Symbols.STAR:
			tokenName = "STAR";
			break;
		case Symbols.RPAREN:
			tokenName = "RPAREN";
			break;
		case Symbols.NOT:
			tokenName = "NOT";
			break;
		case Symbols.AND:
			tokenName = "AND";
			break;
		case Symbols.LT:
			tokenName = "LT";
			break;
		case Symbols.POINTS:
			tokenName = "POINTS";
			break;
		case Symbols.OR:
			tokenName = "OR";
			break;
		case Symbols.BOOL:
			tokenName = "BOOL";
			break;
		case Symbols.IMPLIES:
			tokenName = "IMPLIES";
			break;
		case Symbols.DIV:
			tokenName = "DIV";
			break;
		case Symbols.OCL_COLLECTION:
			tokenName = "OCL_COLLECTION";
			break;
		case Symbols.PLUS:
			tokenName = "PLUS";
			break;
		case Symbols.IF:
			tokenName = "IF";
			break;
		case Symbols.ID:
			tokenName = "ID";
			break;
		case Symbols.CONTEXT:
			tokenName = "CONTEXT";
			break;
		case Symbols.LE:
			tokenName = "LE";
			break;
		case Symbols.EOF:
			tokenName = "EOF";
			break;
		case Symbols.POINT_POINT:
			tokenName = "POINT_POINT";
			break;
		case Symbols.error:
			tokenName = "error";
			break;
		case Symbols.SELF:
			tokenName = "SELF";
			break;
		case Symbols.RESULT:
			tokenName = "RESULT";
			break;
		case Symbols.COMA:
			tokenName = "COMA";
			break;
		case Symbols.OCL_SUPER_TYPE:
			tokenName = "OCL_SUPER_TYPE";
			break;
		case Symbols.LCURLY:
			tokenName = "LCURLY";
			break;
		case Symbols.PIPE:
			tokenName = "PIPE";
			break;
		case Symbols.EQ:
			tokenName = "EQ";
			break;
		case Symbols.RCURLY:
			tokenName = "RCURLY";
			break;
		case Symbols.ARROW:
			tokenName = "ARROW";
			break;
		case Symbols.ENDIF:
			tokenName = "ENDIF";
			break;
		case Symbols.ELSE:
			tokenName = "ELSE";
			break;
		case Symbols.POST:
			tokenName = "POST";
			break;
		case Symbols.POINT:
			tokenName = "POINT";
			break;
		case Symbols.FLOAT:
			tokenName = "FLOAT";
			break;
		case Symbols.THEN:
			tokenName = "THEN";
			break;
		case Symbols.NE:
			tokenName = "NE";
			break;
		case Symbols.STRING:
			tokenName = "STRING";
			break;
		case Symbols.AROBAPRE:
			tokenName = "AROBAPRE";
			break;
		case Symbols.GT:
			tokenName = "GT";
			break;
		default:
			//throw new RuntimeException();
			break;
		}
		return tokenName;
	}
	
}
