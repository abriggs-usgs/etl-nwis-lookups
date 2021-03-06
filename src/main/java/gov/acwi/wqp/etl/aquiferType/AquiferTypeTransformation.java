package gov.acwi.wqp.etl.aquiferType;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import gov.acwi.wqp.etl.BasicLookup;
import gov.acwi.wqp.etl.BasicLookupProcessor;
import gov.acwi.wqp.etl.GwReflist;

@Configuration
public class AquiferTypeTransformation {

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("deleteAquiferType")
	private Tasklet deleteAquiferType;

	@Autowired
	@Qualifier("gwReflistAquiferTypeReader")
	private JdbcCursorItemReader<GwReflist> gwReflistAquiferTypeReader;

	@Autowired
	@Qualifier("aquiferTypeProcessor")
	private BasicLookupProcessor aquiferTypeProcessor;

	@Autowired
	@Qualifier("aquiferTypeWriter")
	private JdbcBatchItemWriter<BasicLookup> aquiferTypeWriter;

	@Bean
	public Step deleteAquiferTypeStep() {
		return stepBuilderFactory.get("deleteAquiferTypeStep").tasklet(deleteAquiferType).build();
	}

	@Bean
	public Step transformAquiferTypeStep() {
		return stepBuilderFactory
				.get("transformAquiferTypeStep").<GwReflist, BasicLookup>chunk(1000)
				.reader(gwReflistAquiferTypeReader)
				.processor(aquiferTypeProcessor)
				.writer(aquiferTypeWriter).build();
	}

	@Bean
	public Flow aquiferTypeFlow() {
		return new FlowBuilder<SimpleFlow>("aquiferTypeFlow")
				.start(deleteAquiferTypeStep())
				.next(transformAquiferTypeStep())
				.build();
	}
}
