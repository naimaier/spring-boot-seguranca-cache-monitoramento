package br.com.alura.forum.config.swagger;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.alura.forum.modelo.Usuario;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {

	@Bean
	public Docket forumApi() {
	    return new Docket(DocumentationType.SWAGGER_2)
	        .select()
	        //a partir de qual pacote ele vai comecar a documentar
	        .apis(RequestHandlerSelectors.basePackage("br.com.alura.forum"))
	        //quais endpoints analizar
	        .paths(PathSelectors.ant("/**"))
	        .build()
	        //ignora os parametros relacionados a usuario (para nao expor informacoes sensiveis)
	        .ignoredParameterTypes(Usuario.class)
	        .globalOperationParameters(Arrays.asList(
                    new ParameterBuilder()
                            .name("Authorization")
                            .description("Header para token JWT")
                            .modelRef(new ModelRef("string"))
                            .parameterType("header")
                            .required(false)
                            .build()));
	}
}
