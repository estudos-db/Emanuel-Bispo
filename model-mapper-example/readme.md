# ModelMapper
## Descrição da Biblioteca
<h4>O objetivo do ModelMapper é facilitar o mapeamento de objetos, determinando automaticamente como um modelo de objeto é mapeado para outro modelo.</h4>
[Leia a Documentação](https://modelmapper.org/)
## Pré-Requisitos
- [Java 8+](https://www.oracle.com/java/technologies/downloads/archive/)
- Maven ou Gradle
# Configurando o ModelMapper no projeto
<h4>Se você for utilizar o ModelMapper, adicione umas dessas dependências:</h4>

## Maven

````
<dependency>
    <groupId>org.modelmapper</groupId>
    <artifactId>modelmapper</artifactId>
    <version>2.1.1</version>
</dependency>
````
## Gradle

````
 implementation ("org.modelmapper:modelmapper:2.1.1")
````


<h4>Ao inserir a dependência dentro do Maven ou Gradle, você precisa configurar para que ele faça o mapeamento corretamente.
Nesse caso, podemos criar um Bean de ModelMapper:</h4>

````
@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
````
<h4>Em seguida, não esqueça que na hora que você quiser instanciar o ModelMapper, adicione dentro de um construtor:</h4>

````
@RestController
@RequestMapping("/modelmapper")
public class ModelMapperController {

    private final ModelMapper modelMapper;

    @Autowired
    public ModelMapperController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
````
