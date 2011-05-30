class Transacao
	attr "pontos"
	attr "data"
	attr "conta"
	attr "cartao"
	attr "servico"

	def programa() end


	def checkPostfazNadaPrograma()
programa().obtemServicos().select {|i|i.condicao == true}.size() + 2
	end

	def checkAllPrePrograma()
		return true
	end

	def checkAllPostPrograma()
		if !(checkPostfazNadaPrograma())
			programaPostViolated()
		end
		return true
	end

	def programaPostIsViolated()
		raise Exception, "Postcondition of the method programa was violated"
	end

	def main()

		if checkAllPrePrograma()
			programa()
			checkAllPostPrograma()
		else
			programaPreIsViolated()
		end

	end
end