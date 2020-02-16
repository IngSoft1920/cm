package ingsoft1920.config;

import org.apache.commons.dbutils.QueryRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

	// Como QueryRunner no es una clase nuestra, no podemos usar @Autowired directamente
	// porque no podemos indicarle a Spring cómo tiene que construir el objeto (no tenemos
	// la clase para poner @Component encima). Así que hacemos esto para indicarle precisamente esto

	@Bean
	public QueryRunner createQueryRunner() {
		return new QueryRunner();
	}

}
