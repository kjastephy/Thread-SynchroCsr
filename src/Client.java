
public class Client extends Thread{

    private Site depart, arrivee;

    public Client ( Site depart, Site arrivee)
    {
        this.setDepart(depart);
        this.setArrivee(arrivee);
    }

    public Site getArrivee()
    {
        return arrivee;
    }

    public void setArrivee(Site arrivee)
    {
        this.arrivee = arrivee;
    }

    public Site getDepart()
    {
        return depart;
    }

    public void setDepart(Site depart)
    {
        this.depart = depart;
    }

    public void emprunt() throws InterruptedException {
        this.depart.emprunt();
    }

    public void restitution() throws InterruptedException {
        this.arrivee.restitution();


    }
    public void deplacement(int duree_deplacement) throws InterruptedException {
        Thread.sleep(duree_deplacement);
    }

    public void run() {
        try {
            this.emprunt();
            this.deplacement(10);
            this.restitution();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
