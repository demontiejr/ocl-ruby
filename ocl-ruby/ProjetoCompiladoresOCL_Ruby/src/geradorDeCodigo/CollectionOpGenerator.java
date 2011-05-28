package geradorDeCodigo;

public class CollectionOpGenerator {
	
	private String[] collectionOperations = {"forAll", "exists"};
	
	private String[] jaFiz = {"size","isEmpty","first","includes", "excludes", "including", "excluding"};
	
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
		if(posicaoInicial > c.length()){
			System.err.println("nao existe referencia a colecao");
		}
		for (int i = 0; i < c.length(); i++) {
			if(i < posicaoInicial){
				lista += String.valueOf(c.charAt(i));
			}
		}
		return lista;
		
	}
	
	private static String retornaParametros(String c){
		int posicaoInicial = localizaSeta(c);
		boolean acheiParentese = false;
		String parametros = "";
		
		if(posicaoInicial > c.length()){
			System.err.println("nao existe referencia a colecao");
		}
		
		for (int i = posicaoInicial; i < c.length(); i++) {
			if(!acheiParentese){
				if(c.charAt(i) == '('){
					acheiParentese = true;
				}
			}else{
				if(c.charAt(i) == ')'){
					break;
				}else{
					parametros += String.valueOf(c.charAt(i));
				}
			}
		}return parametros;
		
	}
	
	public String createSize(String c){
		return retornaLista(c) + ".size()";	
	}
	
	public String createIsEmpty(String c){
		return retornaLista(c) + ".empty?";
	}
	
	public String createFirst(String c){
		return retornaLista(c) + ".first";
	}
	
	public static String createIncludes(String c){
		return retornaLista(c) + ".include?("+ retornaParametros(c) +")";
	}
	
	//a->excludes(1)
	//if(a.excludes(1) != nil) return true;
	//else return false;
	
	public static String createExcludes(String c){
		return "if (" + retornaLista(c) + ".delete("+ retornaParametros(c) +")"+" != nil) return true\nelse return false end";		
	}
	
	
	public static String createIncluding(String c){
		return retornaLista(c) + ".insert(-1,"+ retornaParametros(c) + ")";
	}
	
	//a->excluding(1)
	//a.delete(1)\nprint a
	//else return false;
	
	public static String createExcluding(String c){
		return retornaLista(c) + ".delete("+ retornaParametros(c) + ")\nprint "+retornaLista(c);
	}
	
	public static String createSelect(String c){
		return retornaLista(c) + ".select {"+ retornaParametros(c) + "}";
	}

	public static String createForAll(String c){
		return "for ( i in "+ retornaLista(c) +" )\n\tif !( "+ retornaLista(c) + ".fetch(i)." + retornaParametros(c) + ") return false end\nend\nreturn true"; 
	}
	
	public static String createExists(String c){
		return "for ( i in "+ retornaLista(c) +" )\n\tif ( "+ retornaLista(c) + ".fetch(i)." + retornaParametros(c) + ") return true end\nend\nreturn false"; 
	}
}
