package equipe3;

public class Element {

    private int numeroAtomique;
    private double masseAtomique;
    private int nombreNeutrons;
    private int nombreProtons;
    private int nombreElectrons;
    private String nom;
    private String symbol;

    public Element(int numeroAtomique, double masseAtomique, String nom, String symbol){
        this.numeroAtomique = numeroAtomique;
        this.masseAtomique = masseAtomique;
        this.nombreNeutrons = nombreNeutrons();
        this.nombreProtons = numeroAtomique;
        this.nombreElectrons = numeroAtomique;
        this.nom = nom;
        this.symbol = symbol;
    }

    /*Getter*/

    public int getNumeroAtomique() {
        return this.numeroAtomique;
    }

    public double getMasseAtomique() {
        return this.masseAtomique;
    }

    public int getNombreNeutrons() {
        return this.nombreNeutrons;
    }

    public int getNombreProtons() {
        return this.nombreProtons;
    }

    public int getNombreElectrons() {
        return this.nombreElectrons;
    }

    public String getNom() {
        return this.nom;
    }

    public String getSymbol() {
        return this.symbol;
    }
    /*Setter*/

    public void setNumeroAtomique(int numeroAtomique) {
        this.numeroAtomique = numeroAtomique;
    }

    public void setMasseAtomique(double masseAtomique) {
        this.masseAtomique = masseAtomique;
    }

    public void setNombreNeutrons(int nombreNeutrons) {
        this.nombreNeutrons = nombreNeutrons;
    }

    public void setNombreProtons(int nombreProtons) {
        this.nombreProtons = nombreProtons;
    }

    public void setNombreElectrons(int nombreElectrons) {
        this.nombreElectrons = nombreElectrons;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    /*Method*/

    private int nombreNeutrons(){
        return (int) this.masseAtomique - this.numeroAtomique;
    }

    /**
     * Change the number of electron in the atom
     * @param charge int
     */
    public void ion(int charge){
        this.nombreElectrons -= charge;
    }

    /**
     * Change the number of electron in the atom
     * @param charge String
     */
    public void ion(String charge){
        this.nombreElectrons -= Integer.parseInt(charge);
    }

    /**
     * Change the number of neutron in the atom
     * @param difference INT; the difference between standard number of neutrons and the isotope number of neutron
     */
    public void isotope(int difference){
        this.nombreNeutrons += difference;
    }

    /**
     * Change the number of neutron in the atom
     * @param difference String; the difference between standard number of neutrons and the isotope number of neutron
     */
    public void isotope(String difference){
        this.nombreNeutrons += Integer.parseInt(difference);
    }

    /**
     *
     * @return the value of the charge of the ion
     */
    public int charge(){
        return -(this.nombreElectrons-this.numeroAtomique);
    }

    /**
     *
     * @return a new Element object that have the same default values
     */
    public Element copy(){
        return new Element(this.numeroAtomique, this.masseAtomique, this.nom, this.symbol);
    }

    public String toString() {
        return "Numéro Atomique: " + this.numeroAtomique + "\nMasse Atomique : " + this.masseAtomique + "\nNeutrons : " + this.nombreNeutrons + "\nProtons : " + this.nombreProtons + "\nElectrons : " + this.nombreElectrons;
    }

    public boolean equals(Element element) {
        if (this == element) return true;
        return this.numeroAtomique == element.numeroAtomique && Double.compare(this.masseAtomique, element.masseAtomique) == 0 &&
                this.nombreNeutrons == element.nombreNeutrons && this.nombreProtons == element.nombreProtons &&
                this.nombreElectrons == element.nombreElectrons;
    }


}
