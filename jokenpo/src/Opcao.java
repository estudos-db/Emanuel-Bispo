public enum Opcao {
    PEDRA{
        @Override
        public boolean vence(Opcao opcAdversaria) {
            return opcAdversaria == TESOURA;
        }
    },
    PAPEL{
        @Override
        public boolean vence(Opcao opcAdversaria) {
            return opcAdversaria == PEDRA;
        }
    },
    TESOURA{
        @Override
        public boolean vence(Opcao opcAdversaria) {
            return opcAdversaria == PAPEL;
        }
    };

    public abstract boolean vence(Opcao opcAdversaria);
}
