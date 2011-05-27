class Pessoa

	def initialize(nome, idade, rg)
		@nome = nome
		@idade = idade
		@rg = rg
	end
	
	def preCondicao()
		return true
	end
	
	def posCondicao(idadePre)
		return @idade == (idadePre + 1)
	end
	
	def simulaMetodoPrincipal()
	end
	
	def fazAniversario()
		if !preCondicao()
			return false
		end
		idadePre = @idade
		simulaMetodoPrincipal()
		return posCondicao(idadePre)
	end
	
end	