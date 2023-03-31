public class Main {

    public static Pessoa mae() {
        Pessoa mae = new Pessoa(
                "Vila",
                70,
                new Pessoa("Pai da Vila", 94, null, null),
                new Pessoa("Mae da vila", 94, null, null)
        );

        Pessoa pai = new Pessoa(
                "Jose",
                76,
                new Pessoa("Pai do Jose", 104, null, null),
                new Pessoa("Mae do Jose", 104, null, null)
        );

        return new Pessoa(
                "Carla",
                38,
                pai,
                mae
        );

    }

    public static Pessoa pai() {
        Pessoa mae = new Pessoa(
                "Tereza",
                69,
                new Pessoa("Pai da Tereza", 98, null, null),
                new Pessoa("Mae da Tereza", 92, null, null)
        );

        Pessoa pai = new Pessoa(
                "Jorel",
                72,
                new Pessoa("Pai do Jorel", 101, null, null),
                new Pessoa("Mae do Jorel", 99, null, null)
        );

        return new Pessoa(
                "Joao",
                38,
                pai,
                mae
        );

    }

    public static void main(String[] args) {

        Pessoa filho = new Pessoa(
                "Bob",
                20,
                pai(),
                mae()
        );

    }
}
