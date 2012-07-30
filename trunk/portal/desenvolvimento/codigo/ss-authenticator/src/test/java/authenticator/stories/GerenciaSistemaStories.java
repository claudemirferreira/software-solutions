package authenticator.stories;

import java.util.ArrayList;
import java.util.List;

import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.StoryFinder;

import authenticator.Steps.Estoria;
import authenticator.Steps.GerenciarSistemaSteps;

public class GerenciaSistemaStories extends Estoria {

	@Override
	protected List<Object> passosEstoria() {
		ArrayList<Object> passos = new ArrayList<Object>();
		//classe com os passos que serao executados
		passos.add(new GerenciarSistemaSteps());
		return passos;
	}

	@Override
	protected List<String> storyPaths() {
		return new StoryFinder().findPaths(CodeLocations.codeLocationFromClass(getClass()),				
				"**/GerenciaSistema.story", "");
	}
}
