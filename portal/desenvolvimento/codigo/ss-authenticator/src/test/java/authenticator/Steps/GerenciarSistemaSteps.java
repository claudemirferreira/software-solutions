package authenticator.Steps;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.jbehave.core.annotations.Alias;
import org.jbehave.core.annotations.BeforeStories;
import org.jbehave.core.annotations.Composite;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;
import org.jbehave.core.steps.Steps;

import br.ss.authenticator.model.entity.Servico;



public class GerenciarSistemaSteps extends Steps {
	@Inject
	private Object service;
	
	
	@BeforeStories
	public void init(){
		service = this.instance(); 
	}
	
	@Given("os servicos $servicos, as responsabilidades $responsabilidades, os perfis $perfis, os grupos $grupos e os usuarios $usuarios estejam cadastrados no sistema")
	@Alias("as servicos <servicos>, as responsabilidades <responsabilidades>, os perfils <perfis>, os grupos <grupos> e os usuarios <usuarios> estejam cadastrados no sistema")
	@Composite(steps={"existe os seguintes servicos no sistema:<servicos>",
			"existe as seguintes responsabilidades no sistema:<responsabilidades>",
			"existe o seguinte perfil no sistema:<perfis>",
			"existe a seguinte esfera no sistema:<grupos>",
			"existe o seguinte usuario no sistema:<usuarios>"
			})
	public void givenActionsFunctionalityPerfilEsferaOrgans(@Named("servicos") ExamplesTable servico, @Named("responsabilidades") ExamplesTable responsabilidade, 
															@Named("perfis") ExamplesTable perfil, @Named("grupos") ExamplesTable grupo,
															@Named("usuarios") ExamplesTable usuario ){
		System.out.println("*************************************");
		 List<Servico> actionsList = new ArrayList<Servico>();
		 
		  for (Map<String,String> row : servico.getRows()) {
		        String nome = row.get("servico");
		        Servico ser = new Servico();
		        ser.setTxServico(nome);
		        actionsList.add(ser);
		    }
	
	}
	
	@Given("existe os seguintes servicos no sistema:$servicoTable")
	public void givenServicos( ExamplesTable servicoTable){
		
	}
	
	@Given("existe as seguintes responsabilidades no sistema:$responsabilidadeTable")
	public void givenResponsabilidades( ExamplesTable responsabilidadeTable){
		
	}
	
	@Given("existe o seguinte perfil no sistema:$perfilTable")
	public void givenPerfil( ExamplesTable perfilTable){
		
	}
	
	@Given("existe o seguinte grupo no sistema:$grupoTable")
	public void givenGrupo( ExamplesTable grupoTable){
		
	}

	@Given("existe o seguinte usuario no sistema:$usuarioTable")
	public void givenUsuario( ExamplesTable usuarioTable){
		
	}
	
	@When("cadastro um servico $servicoTable")
	public void whenCadastroServico(ExamplesTable servicoTable){
		
	}
	
	@Then("o servico $servicoTable nao foi salvo no sistema")
	public void thenServicoNaoSalvo(ExamplesTable servicoTable){
		
	}
	
	
}
