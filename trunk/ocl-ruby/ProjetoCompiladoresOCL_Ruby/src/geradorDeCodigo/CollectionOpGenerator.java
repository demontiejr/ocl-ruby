package geradorDeCodigo;

public class CollectionOpGenerator {
	
	private static String[] aux;
	
	private static int localizaSeta(String c){
		for (int i = 0; i < c.length()-1; i++) {
			if( c.charAt(i) == '-' && c.charAt(i+1) == '>'){
				return i;
			}
		}
		return c.length()+1;
	}
	
	private static String retornaLista(String c){
		String lista = "";
		
		int posicaoInicial = localizaSeta(c);
//		if(posicaoInicial > c.length()){
//			System.err.println("nao existe referencia a colecao");
//		}
		for (int i = 0; i < c.length(); i++) {
			if(i < posicaoInicial){
				lista += String.valueOf(c.charAt(i));
			}
		}
		return lista;
	}
	
	private static String retornaOp(int i){
		String op = aux[i];
		int pos = op.indexOf("(");
		op = op.substring(0,pos);
		return op;
	}
	
	private static String retornaParametros(String c){
		String params = c.substring(c.indexOf("(")+1,c.indexOf(")"));
		if (params.substring(0,3).equals("#N#"))
			params = "|i|" + params.substring(3,params.length());
		else if (params.substring(0,3).equals("#D#"))
			params = params.substring(3,params.length());
		return params;
	}
	
	public static String getCode(String featureCall){
		aux = featureCall.split("->");
		String code = aux[0];
		for (int contador = 1; contador < aux.length; contador++) {
			String op = retornaOp(contador);
			if (op.equals("size")) {
				code = createSize(code);
			} else if (op.equals("select")) {
				code = createSelect(code,aux[contador]);
			} else if (op.equals("isEmpty")) {
				code = createIsEmpty(code);
			} else if (op.equals("first")) {
				code = createFirst(code);
			} else if (op.equals("includes")) {
				code = createIncludes(code,aux[contador]);
			} else if (op.equals("excludes")) {
				code = createExcludes(code, aux[contador]);
			} else if (op.equals("including")) {
				code = createIncluding(code, aux[contador]);
			} else if (op.equals("excluding")) {
				code = createExcluding(code, aux[contador]);
			} else if (op.equals("forAll")) {
				code = createForAll(code, aux[contador]);
			} else if (op.equals("exists")) {
				code = createExists(code, aux[contador]);
			} else {
				System.err.println("Operacao de colecao nao existe");
			}
		}
		return code;
	}
	
	public static String createSize(String c){
		return retornaLista(c) + ".size()";	
	}
	
	public static String createIsEmpty(String c){
		return retornaLista(c) + ".empty?";
	}
	
	public static String createFirst(String c){
		return retornaLista(c) + ".first";
	}
	
	public static String createIncludes(String c, String params){
		return c + ".include?("+ retornaParametros(params) +")";
	}
	
	//a->excludes(1)
	//if(a.excludes(1) != nil) return true;
	//else return false;
	
	public static String createExcludes(String c, String params){
		return "if (" + c + ".delete("+ retornaParametros(params) +")"+" != nil) return true\nelse return false end";		
	}
	
	public static String createIncluding(String c, String params){
		return c + ".insert(-1,"+ retornaParametros(params) + ")";
	}
	
	//a->excluding(1)
	//a.delete(1)\nprint a
	//else return false;
	
	public static String createExcluding(String c, String params){
		return c + ".delete("+ retornaParametros(params) + ")\nprint "+retornaLista(c);
	}
	
	public static String createSelect(String c, String params){
		return c + ".select {"+ retornaParametros(params) + "}";
	}

	public static String createForAll(String c, String params){
		return "for ( i in "+ c +" )\n\tif !( "+ c + ".fetch(i)." + retornaParametros(params) + ") return false end\nend\nreturn true"; 
	}
	
	public static String createExists(String c, String params){
		return "for ( i in "+ c +" )\n\tif ( "+ c + ".fetch(i)." + retornaParametros(params) + ") return true end\nend\nreturn false"; 
	}
}
