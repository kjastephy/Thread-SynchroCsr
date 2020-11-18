public class Camion extends Thread {

    private Site[] sites;
    private int nbVelo;

    public Camion(Site[] sites) {
        this.sites=sites;
        this.nbVelo=30;
        this.setDaemon(true);
    }

    public Camion(Site[] sites,int nbVelo) {
        this.sites=sites;
        this.nbVelo=nbVelo;
        this.setDaemon(true);
    }

    public int getnbVelo()
    {
        return nbVelo;
    }

    public Site[] getSites()
    {
        return this.sites;
    }

    public  void setNbVelo(int nbVelo) {
        this.nbVelo = nbVelo;
    }

    public void arriveeSite(Site site) throws InterruptedException {
        site.arriveeSite(this);
    }
    public void run() {
        while(true) {
            int cpt=sites.length-1;
            while(cpt>0) {
                try {
                    this.arriveeSite(sites[cpt]);
                    cpt--;
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    break;
                }
            }
        }

    }
}
