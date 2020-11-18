class Site {

/* Constantes associees au site */

        static final int STOCK_INIT = 5;
        static final int STOCK_MAX = 10;
        static final int BORNE_SUP = 8;
        static final int BORNE_INF = 2;

        private int numb, nbVelo;

        public Site (int numb)
        {
            this.numb = numb;
            this.nbVelo = STOCK_INIT;
        }

        public int getNumb()
        { return numb; }

        public void setNumb (int numb)
        { this.numb= numb; }

        public int getnbVelo()
        { return nbVelo; }

        public void setNbVelo(int nbVelo)
        { this.nbVelo = nbVelo; }

        public synchronized void emprunt() throws InterruptedException {
            while(this.nbVelo==0) {
                wait();
            }
            afficheEmprunt();

            this.nbVelo--;
            notify();


        }

        public synchronized void restitution() throws InterruptedException {
            while(this.nbVelo>=STOCK_MAX) {
                wait();
            }
           afficheRestitution();
            this.nbVelo++;
            notify();

        }

    public synchronized void arriveeSite(Camion camion) {
        // taille du camion toujours suffisante pour reprendre tous les velos en excédent
        if (this.getnbVelo() > Site.BORNE_SUP) {
            System.out.println(Thread.currentThread().getName()+": "+"Le camion arrive dans le site n° " + numb +
                    " avec " + camion.getnbVelo() + " velo(s) en stock.");
            int nbReprise = this.getnbVelo() - Site.STOCK_INIT;
           camion.setNbVelo(camion.getnbVelo() + nbReprise);
            this.setNbVelo(Site.STOCK_INIT);
            System.out.println(Thread.currentThread().getName()+": "+"Le camion reprend " + nbReprise + " velos "
                    +"et part du site n°" + numb +
                    " avec " + camion.getnbVelo() + " velo(s) au total.");
        }
        
        if(this.getnbVelo()<Site.BORNE_INF) {

            if (camion.getnbVelo() >= (Site.STOCK_INIT-this.getnbVelo()))
            {
                System.out.println(Thread.currentThread().getName()+": "+"Le camion arrive dans le site n° " + numb +
                        " avec " + camion.getnbVelo() + " velo(s) en stock.");
                int nbDepot = Site.STOCK_INIT-this.getnbVelo();
                camion.setNbVelo(camion.getnbVelo() - nbDepot);
                this.setNbVelo(Site.STOCK_INIT);
                System.out.println(Thread.currentThread().getName()+": "+"Le camion depose " + nbDepot + " velo(s) et part du site n°"
                        + numb + " avec " + camion.getnbVelo() + " velo(s) au total.");
            }
            else {
                System.out.println(Thread.currentThread().getName()+": "+"Le camion arrive dans le site n°" + numb +
                        " avec " + camion.getnbVelo() + " velo(s) en stock.");
                this.setNbVelo(camion.getnbVelo()+this.getnbVelo());
                int nbDepot = camion.getnbVelo();
                camion.setNbVelo(0);
                System.out.println(Thread.currentThread().getName()+": "+"Le camion depose "+nbDepot+ " velo(s) " +
                        "et part du site n°" + numb +
                        " avec un stock nul(0).");
                }
        }
    }

    public void afficheEmprunt() {
       System.out.println(Thread.currentThread().getName()+": "+"Un client arrive au site n° " + this.numb + " et emprunte un velo " +
               "parmis " + this.nbVelo + " velo(s) disponibles sur ce site.");


    }

    public void afficheRestitution() {
        System.out.println(Thread.currentThread().getName() + ": " + "Un client arrive au site n° " + this.numb + " qui contient "
                + this.nbVelo + " velo(s) et restitue un velo en plus.");

    }



    }
