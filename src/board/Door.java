public class Door {
    private boolean isOpen;

    public Door(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public boolean isOpen() {
        return this.isOpen;
    }

    public void setOpen(boolean open) {
        this.isOpen = open;
    }

    // ver se vale apena colocar para printar aqui, pois a porta não sabe se é norte, sul, leste ou oeste, isso é responsabilidade do setor
    // public String toString() {
    //     return this.isOpen ? "*" : "-";
    // }
}