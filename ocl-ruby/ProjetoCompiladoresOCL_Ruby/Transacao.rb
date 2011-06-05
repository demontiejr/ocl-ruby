class Transacao
	attr "pontos"
	attr "data"
	attr "conta"
	attr "cartao"
	attr "servico"

	def programa() end

	def checkPostfazNadaPrograma()
self.programa().obtemServicos().select {|i|i.condicao == true}.size() + 2
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
		raise Exception, "Post condition of the method programa was violated"
	end

	def main()

		if checkAllPrePrograma()
			@pontos_pre = @pontos
			@data_pre = @data
			@conta_pre = @conta
			@cartao_pre = @cartao
			@servico_pre = @servico
			@resultPrograma = programa()
			checkAllPostPrograma()
		else
			programaPreIsViolated()
		end

	end
end