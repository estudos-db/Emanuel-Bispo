public class Main {

    public static Mae mae() {
        Mae mae = new Mae(
                "Vila",
                70,
                new Pai("Pai da Vila", 94, null, null),
                new Mae("Mae da vila", 94, null, null)
        );

        Pai pai = new Pai(
                "Jose",
                76,
                new Pai("Pai do Jose", 104, null, null),
                new Mae("Mae do Jose", 104, null, null)
        );

        return new Mae(
                "Carla",
                38,
                pai,
                mae
        );

    }

    public static Pai pai() {
        Mae mae = new Mae(
                "Tereza",
                69,
                new Pai("Pai da Tereza", 98, null, null),
                new Mae("Mae da Tereza", 92, null, null)
        );

        Pai pai = new Pai(
                "Jorel",
                72,
                new Pai("Pai do Jorel", 101, null, null),
                new Mae("Mae do Jorel", 99, null, null)
        );

        return new Pai(
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
