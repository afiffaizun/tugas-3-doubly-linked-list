import java.util.Scanner;

class Node {
    String data;   // Menyimpan teks berita
    Node next;     // Pointer ke node berikutnya
    Node prev;     // Pointer ke node sebelumnya

    public Node(String data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

// Class Circular Doubly Linked List
class CircularDoublyLinkedList {
    private Node head;  // Node pertama
    private int size;   // Jumlah node

    public CircularDoublyLinkedList() {
        head = null;
        size = 0;
    }

    // Insert di akhir
    public void insert(String data) {
        Node newNode = new Node(data);

        // Jika list kosong
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

    // Hapus berdasarkan nomor urut
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

        // Jika hanya 1 node
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

    // Tampilkan forward (teks berjalan)
    public void displayForward() {
        if (head == null) {
            System.out.println("Tidak ada berita.\n");
            return;
        }

        System.out.println("\n=== Teks Berjalan (Forward) ===");
        Node current = head;

        for (int i = 1; i <= size; i++) {
            System.out.println(i + ". " + current.data);
            try {
                Thread.sleep(5000); // Delay 5 detik
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            current = current.next;
        }
        System.out.println();
    }

    // =========================
    // Tampilkan backward (teks berjalan)
    // =========================
    public void displayBackward() {
        if (head == null) {
            System.out.println("Tidak ada berita.\n");
            return;
        }

        System.out.println("\n=== Teks Berjalan (Backward) ===");
        Node current = head.prev;

        for (int i = size; i >= 1; i--) {
            System.out.println(i + ". " + current.data);
            try {
                Thread.sleep(5000); // Delay 5 detik
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            current = current.prev;
        }
        System.out.println();
    }

    // Tampilkan berita tertentu
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


// Main Class (Menu Program)
public class tugas_3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        CircularDoublyLinkedList cdll = new CircularDoublyLinkedList();

        while (true) {
            System.out.println("====== MENU TEKS BERJALAN ======");
            System.out.println("1. Insert berita");
            System.out.println("2. Hapus berita");
            System.out.println("3. Tampilkan berita forward");
            System.out.println("4. Tampilkan berita backward");
            System.out.println("5. Tampilkan berita tertentu");
            System.out.println("6. Exit");
            System.out.print("Pilih Opsi: ");

            int pilihan = input.nextInt();
            input.nextLine(); // Membersihkan newline

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan teks berita: ");
                    String berita = input.nextLine();
                    cdll.insert(berita);
                    break;

                case 2:
                    System.out.print("Masukkan nomor berita yang ingin dihapus: ");
                    int hapus = input.nextInt();
                    cdll.delete(hapus);
                    break;

                case 3:
                    cdll.displayForward();
                    break;

                case 4:
                    cdll.displayBackward();
                    break;

                case 5:
                    System.out.print("Masukkan nomor berita: ");
                    int lihat = input.nextInt();
                    cdll.displayAt(lihat);
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