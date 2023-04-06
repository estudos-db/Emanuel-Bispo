# ModelMapper
## Descrição da Biblioteca
<h4>O objetivo do ModelMapper é facilitar o mapeamento de objetos, determinando automaticamente como um modelo de objeto é mapeado para outro modelo.</h4>

## Pré-Requisitos
- [Java 8+](https://www.oracle.com/java/technologies/downloads/archive/)
- Maven ou Gradle
# Como instalar?
<h4>Se você for usuário do ModelMapper, basta adicionar umas dessas dependências:</h4>

## Maven

````
<dependency>
    <groupId>org.modelmapper</groupId>
    <artifactId>modelmapper</artifactId>
    <version>${modelmapper.version}</version>
</dependency>
````
## Gradle

````
 implementation ("org.modelmapper:modelmapper:2.1.1")
````

# Configurando o ModelMapper no projeto
<h4>Não basta apenas inserir a dependência dentro do Maven ou Gradle, você precisa configurar para que ele faça o mapeamento corretamente.
Nesse caso, podemos criar um Bean de um ModelMapper:</h4>

````
@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
````
<h4>Em seguida, não esqueça que na hora que você quiser instanciar o ModelMapper, tem que colocar a Anotação @Autowired</h4>

````
@Autowired
private ModelMapper modelMapper;
````
