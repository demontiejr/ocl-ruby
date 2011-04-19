
public class Teste {

	
	public Object teste() {
		Object RESULT = null;
		Float bool1 = new Float(12.5e-21);
		A a = new A(bool1);
		String b = "Tarara " + a;
		System.out.println(b);
		return RESULT;
	}

	public static void main(String[] args) {
		new Teste().teste();
	}
	
	public class A{
		public A(Object valor){
			this.valor = valor;
		}
		
		public Object valor;
		public String tipo;
		
		@Override
		public String toString(){
			return String.valueOf(valor);
		}
	}
	
	/*
	 * ((A)fCall1)
	*/
}
