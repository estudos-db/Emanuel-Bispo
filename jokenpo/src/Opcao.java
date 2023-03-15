public enum Opcao {
    PEDRA{
        @Override
        public boolean vence(Opcao opcAdversaria) {
            return opcAdversaria == TESOURA ? true : false;
        }
    },
    PAPEL{
        @Override
        public boolean vence(Opcao opcAdversaria) {
            return opcAdversaria == PEDRA ? true : false;
        }
    },
    TESOURA{
        @Override
        public boolean vence(Opcao opcAdversaria) {
            return opcAdversaria == PAPEL ? true : false;
        }
    };

    public abstract boolean vence(Opcao opcAdversaria);
}
