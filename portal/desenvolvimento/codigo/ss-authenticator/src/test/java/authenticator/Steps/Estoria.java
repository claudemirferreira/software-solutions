package authenticator.Steps;


import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.jbehave.core.Embeddable;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.Keywords;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.StoryControls;
import org.jbehave.core.i18n.LocalizedKeywords;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.UnderscoredCamelCaseResolver;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.model.ExamplesTableFactory;
import org.jbehave.core.parsers.RegexPrefixCapturingPatternParser;
import org.jbehave.core.parsers.RegexStoryParser;
import org.jbehave.core.reporters.ConsoleOutput;
import org.jbehave.core.reporters.CrossReference;
import org.jbehave.core.reporters.FilePrintStreamFactory.ResolveToPackagedName;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.ParameterConverters;
import org.jbehave.core.steps.ParameterConverters.DateConverter;
import org.jbehave.core.steps.ParameterConverters.ExamplesTableConverter;
/**
 * Esta classe executa a(s) estoria(s) 
 * escritas em portugues
 * 
 * ***/
public abstract class Estoria extends JUnitStories {
	private final CrossReference xref = new CrossReference();

	

	
	/**
	 * Configuração do carregamento das stories e do relatório 
	 * usando o potugues como idioma
	 */
	@Override
	    public Configuration configuration() {
		
        Class<? extends Embeddable> embeddableClass = this.getClass();
        Properties viewResources = new Properties();
        viewResources.put("decorateNonHtml", "true");
        // Start from default ParameterConverters instance
        ParameterConverters parameterConverters = new ParameterConverters();
        // factory to allow parameter conversion and loading from external
        // resources (used by StoryParser too)        
        
         //Carregas as keywords em portugues para ser usada pelo jbehave         
         Keywords keywords = new LocalizedKeywords(new Locale("pt"));
        
        ExamplesTableFactory examplesTableFactory = new ExamplesTableFactory(keywords,
                new LoadFromClasspath(embeddableClass), parameterConverters);
        // add custom converters
        parameterConverters.addConverters(new DateConverter(new SimpleDateFormat("yyyy-MM-dd")),
                new ExamplesTableConverter(examplesTableFactory));
        
        
 
        return new MostUsefulConfiguration().useKeywords(keywords)
                .useStoryControls(new StoryControls().doDryRun(false).doSkipScenariosAfterFailure(false))
                .useStoryLoader(new LoadFromClasspath(embeddableClass))
                .useStoryParser(new RegexStoryParser(keywords))
                .useStoryPathResolver(new UnderscoredCamelCaseResolver())
                .useDefaultStoryReporter(new ConsoleOutput(keywords))
                .useStoryReporterBuilder(
                        new StoryReporterBuilder().withKeywords( keywords)
                                .withCodeLocation(CodeLocations.codeLocationFromClass(embeddableClass))
                                .withDefaultFormats().withPathResolver(new ResolveToPackagedName())
                                .withViewResources(viewResources).withFormats(Format.CONSOLE, Format.TXT,Format.HTML,Format.XML)
                                .withFailureTrace(true).withFailureTraceCompression(true).withCrossReference(xref))
                .useParameterConverters(parameterConverters)
                // use '%' instead of '$' to identify parameters
                .useStepPatternParser(new RegexPrefixCapturingPatternParser("%"))
                .useStepMonitor(xref.getStepMonitor());
	    }
	
	@Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(),passosEstoria());
    }
	/**
	 * passos da estoria que serao executados 
	 ***/
	protected abstract List<Object> passosEstoria();


}