package org.example.lvls;

import org.example.UI;

public class Monza {

     Monza() {
    }



    public class mapThread extends Thread{
        @Override
        public void run() {
            super.run();
            try {
                sleep(10000);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
