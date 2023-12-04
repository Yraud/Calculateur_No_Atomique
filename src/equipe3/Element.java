package equipe3;

public class Element {

    private int numeroAtomique;
    private double masseAtomique;
    private int nombreNeutrons;
    private int nombreProtons;
    private int nombreElectrons;

    public Element(int numeroAtomique, double masseAtomique){
        this.numeroAtomique = numeroAtomique;
        this.masseAtomique = masseAtomique;
        this.nombreNeutrons = nombreNeutrons();
        this.nombreProtons = numeroAtomique;
        this.nombreElectrons = numeroAtomique;
    }

    /*Getter*/

    public int getNumeroAtomique() {
        return numeroAtomique;
    }

    public double getMasseAtomique() {
        return masseAtomique;
    }

    public int getNombreNeutrons() {
        return nombreNeutrons;
    }

    public int getNombreProtons() {
        return nombreProtons;
    }

    public int getNombreElectrons() {
        return nombreElectrons;
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
        return -(nombreElectrons-numeroAtomique);
    }

    /**
     *
     * @return a new Element object that have the same default values
     */
    public Element copy(){
        return new Element(this.numeroAtomique, this.masseAtomique);
    }

    public String toString() {
        return "Numéro Atomique: " + numeroAtomique + "\nMasse Atomique : " + masseAtomique + "\nNeutrons : " + nombreNeutrons + "\nProtons : " + nombreProtons + "\nElectrons : " + nombreElectrons;
    }

    public boolean equals(Element element) {
        if (this == element) return true;
        return numeroAtomique == element.numeroAtomique && Double.compare(masseAtomique, element.masseAtomique) == 0 &&
                nombreNeutrons == element.nombreNeutrons && nombreProtons == element.nombreProtons &&
                nombreElectrons == element.nombreElectrons;
    }


}
