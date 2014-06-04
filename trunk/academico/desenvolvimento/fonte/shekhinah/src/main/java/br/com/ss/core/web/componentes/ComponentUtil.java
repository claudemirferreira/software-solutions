package br.com.ss.core.web.componentes;

import javax.inject.Named;

import br.com.ss.core.web.utils.StringUtil;

@Named("componentUtil")
public class ComponentUtil {
	
	// se nao for informado update de componente faz update do proprio componente
	private static String empty = "@this";
	
	/**
	 * Retorna os nomes dos componentes a serem buscados pela classe org.primefaces.util.ComponentUtils do Primefaces.
	 * Uso: update="pnlObservacao pnlTotal"
	 * @param componentNames
	 * @return
	 */
	public String splitNameComponent( String componentNames ) {
		if (StringUtil.notEmpty(componentNames)) {
			
			// find do componente  
			String findCompCall = ":#{p:component(cc.attrs.update)}";
			String[] names = componentNames.split(" ");	// splits by empty spaces
			StringBuilder sb = new StringBuilder();
			for (short i = 0; i < names.length; i++ ) {
				String comp = names[i];
				sb.append( findCompCall.replace("cc.attrs.update", "'" + comp + "'") );
			}
			return sb.toString();
		}
		return empty;
	}

}
