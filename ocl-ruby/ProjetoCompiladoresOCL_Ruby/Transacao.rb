class Transacao

	def initialize(pontos, data, conta, cartao, servico)

		@pontos = pontos
		@data = data
		@conta = conta
		@cartao = cartao
		@servico = servico

	end

	def programa() end


	def checkAllPrePrograma()
		return true
	end

	def checkAllPostPrograma()
		if !(checkPost1Programa())
			programaPostViolated()
		end
		return true
	end

	def programaPost IsViolated()
		raise Exception, "Post condition of the method programa was violated"
	end

end