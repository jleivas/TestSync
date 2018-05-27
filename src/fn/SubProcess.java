/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import static java.util.concurrent.TimeUnit.SECONDS;
import sync.Cmp;

/**
 *
 * @author sdx
 */
public class SubProcess {
    private static String className="SubProcess";
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    
    public static void isOnline() {
        Cmp.isOnline();
        final Runnable beeper = new Runnable() {
          public void run() { 
              Cmp.isOnline(); 
          }
        };
        final ScheduledFuture<?> beeperHandle =
            scheduler.scheduleAtFixedRate(beeper, 5, 5, SECONDS);
            scheduler.schedule(
                    new Runnable() {
                        public void run() { 
                            beeperHandle.cancel(true); 
                        }
                    }
                    , 60 * 60, SECONDS);
    }
}
