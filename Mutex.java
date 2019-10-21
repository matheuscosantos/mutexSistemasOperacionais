import java.util.concurrent.Semaphore;

public class Mutex {

    // Máximo 1 processo
    static Semaphore semaphore = new Semaphore(1);

    static class MyLockerThread extends Thread {

        String name = "";

        MyLockerThread(String name) {
            this.name = name;
        }

        public void run() {

            try {

                System.out.println(name + " : bloqueio de aquisição...");
                System.out.println(name + " : " + "o Semáforo disponível permite agora: "
                        + semaphore.availablePermits());

                semaphore.acquire();
                System.out.println(name + " : tem a permissão!");

                try {

                    for (int i = 1; i <= 5; i++) {

                        System.out.println(name + " : está executando operação " + i
                                + ", " + "licenças de semáforo disponíveis : "
                                + semaphore.availablePermits());

                        // aguarda 1 segundo
                        Thread.sleep(1000);

                    }

                } finally {

                    // chama release() depois de ter sucesso ao executar acquire()
                    System.out.println(name + " : bloqueio de liberação...");
                    semaphore.release();
                    System.out.println(name + " : " + "O Semáforo disponível permite: "
                            + semaphore.availablePermits());

                }

            } catch (InterruptedException e) {

                e.printStackTrace();

            }

        }

    }

    public static void main(String[] args) {

        System.out.println("Total available Semaphore permits : "
                + semaphore.availablePermits());

        MyLockerThread t1 = new MyLockerThread("Thread A");
        t1.start();

        MyLockerThread t2 = new MyLockerThread("Thread B");
        t2.start();

        MyLockerThread t3 = new MyLockerThread("Thread C");
        t3.start();

        MyLockerThread t4 = new MyLockerThread("Thread D");
        t4.start();

        MyLockerThread t5 = new MyLockerThread("Thread E");
        t5.start();

        MyLockerThread t6 = new MyLockerThread("Thread F");
        t6.start();

    }
}