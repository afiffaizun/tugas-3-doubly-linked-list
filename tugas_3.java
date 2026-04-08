import java.util.Scanner;

class Node {
    String data;
    Node next;
    Node prev;

    public Node(String data) {
        this.data = data;
    }
}

// Circular Doubly Linked List
class CircularDoublyLinkedList {
    private Node head;
    private int size;

    public CircularDoublyLinkedList() {
        head = null;
        size = 0;
    }

    // INSERT
    public void insert(String data) {
        Node newNode = new Node(data);

        if (head == null) {
            head = newNode;
            newNode.next = newNode;
            newNode.prev = newNode;
        } else {
            Node tail = head.prev;

            tail.next = newNode;
            newNode.prev = tail;
            newNode.next = head;
            head.prev = newNode;
        }

        size++;
        System.out.println("Berita berhasil ditambahkan.\n");
    }

    // DELETE
    public void delete(int position) {
        if (head == null) {
            System.out.println("List kosong.\n");
            return;
        }

        if (position < 1 || position > size) {
            System.out.println("Nomor tidak valid.\n");
            return;
        }

        Node current = head;

        if (size == 1) {
            head = null;
        } else {
            for (int i = 1; i < position; i++) {
                current = current.next;
            }

            current.prev.next = current.next;
            current.next.prev = current.prev;

            if (position == 1) {
                head = current.next;
            }
        }

        size--;
        System.out.println("Berita berhasil dihapus.\n");
    }


    // DISPLAY LOOP FORWARD (pakai q)
    public void displayForwardLoop(Scanner input) {
        if (head == null) {
            System.out.println("Tidak ada berita.\n");
            return;
        }

        System.out.println("\n=== Teks Berjalan Forward ===");
        System.out.println("Ketik 'q' lalu Enter untuk keluar\n");

        Node current = head;
        final boolean[] stop = {false};

        Thread t = new Thread(() -> {
            while (true) {
                String s = input.nextLine();
                if (s.equalsIgnoreCase("q")) {
                    stop[0] = true;
                    break;
                }
            }
        });

        t.start();

        while (!stop[0]) {
            System.out.println(current.data);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {}

            current = current.next;
        }

        System.out.println("\nKembali ke menu...\n");
    }

    // DISPLAY LOOP BACKWARD (pakai q)
    public void displayBackwardLoop(Scanner input) {
        if (head == null) {
            System.out.println("Tidak ada berita.\n");
            return;
        }

        System.out.println("\n=== Teks Berjalan Backward ===");
        System.out.println("Ketik 'q' lalu Enter untuk keluar\n");

        Node current = head.prev;
        final boolean[] stop = {false};

        Thread t = new Thread(() -> {
            while (true) {
                String s = input.nextLine();
                if (s.equalsIgnoreCase("q")) {
                    stop[0] = true;
                    break;
                }
            }
        });

        t.start();

        while (!stop[0]) {
            System.out.println(current.data);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {}

            current = current.prev;
        }

        System.out.println("\nKembali ke menu...\n");
    }

    // DISPLAY AT
    public void displayAt(int position) {
        if (head == null) {
            System.out.println("List kosong.\n");
            return;
        }

        if (position < 1 || position > size) {
            System.out.println("Nomor tidak valid.\n");
            return;
        }

        Node current = head;

        for (int i = 1; i < position; i++) {
            current = current.next;
        }

        System.out.println("\nBerita ke-" + position + ":");
        System.out.println(current.data + "\n");
    }
}


// MAIN
public class tugas_3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        CircularDoublyLinkedList cdll = new CircularDoublyLinkedList();

        while (true) {
            System.out.println("====== MENU TEKS BERJALAN ======");
            System.out.println("1. Insert berita");
            System.out.println("2. Hapus berita");
            System.out.println("3. Tampilkan forward loop");
            System.out.println("4. Tampilkan backward loop");
            System.out.println("5. Tampilkan berita tertentu");
            System.out.println("6. Exit");
            System.out.print("Pilih Opsi: ");

            int pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan teks berita: ");
                    cdll.insert(input.nextLine());
                    break;

                case 2:
                    System.out.print("Masukkan nomor berita: ");
                    cdll.delete(input.nextInt());
                    break;

                case 3:
                    cdll.displayForwardLoop(input);
                    break;

                case 4:
                    cdll.displayBackwardLoop(input);
                    break;

                case 5:
                    System.out.print("Masukkan nomor berita: ");
                    cdll.displayAt(input.nextInt());
                    break;

                case 6:
                    System.out.println("Program selesai.");
                    input.close();
                    return;

                default:
                    System.out.println("Pilihan tidak valid.\n");
            }
        }
    }
}