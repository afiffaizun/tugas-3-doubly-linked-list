import time

# Class Node
class Node:
    def __init__(self, data):
        self.data = data
        self.next = None
        self.prev = None


# Class Circular Doubly Linked List
class CircularDoublyLinkedList:
    def __init__(self):
        self.head = None
        self.size = 0

    # Insert di akhir
    def insert(self, data):
        new_node = Node(data)

        if self.head is None:
            self.head = new_node
            new_node.next = new_node
            new_node.prev = new_node
        else:
            tail = self.head.prev

            tail.next = new_node
            new_node.prev = tail
            new_node.next = self.head
            self.head.prev = new_node

        self.size += 1
        print("Berita berhasil ditambahkan.\n")

    # Delete berdasarkan posisi
    def delete(self, position):
        if self.head is None:
            print("List kosong.\n")
            return

        if position < 1 or position > self.size:
            print("Nomor tidak valid.\n")
            return

        current = self.head

        if self.size == 1:
            self.head = None
        else:
            for _ in range(1, position):
                current = current.next

            current.prev.next = current.next
            current.next.prev = current.prev

            if position == 1:
                self.head = current.next

        self.size -= 1
        print("Berita berhasil dihapus.\n")

    # Running text forward (loop terus)
    def display_forward_loop(self):
        if self.head is None:
            print("Tidak ada berita.\n")
            return

        print("\n=== Running Text Forward ===")
        print("Tekan Ctrl + C untuk berhenti...\n")

        current = self.head

        try:
            while True:
                print(current.data)
                time.sleep(2)
                current = current.next
        except KeyboardInterrupt:
            print("\nRunning text dihentikan.\n")

    # Running text backward
    def display_backward_loop(self):
        if self.head is None:
            print("Tidak ada berita.\n")
            return

        print("\n=== Running Text Backward ===")
        print("Tekan Ctrl + C untuk berhenti...\n")

        current = self.head.prev

        try:
            while True:
                print(current.data)
                time.sleep(2)
                current = current.prev
        except KeyboardInterrupt:
            print("\nRunning text dihentikan.\n")

    # Tampilkan berita tertentu
    def display_at(self, position):
        if self.head is None:
            print("List kosong.\n")
            return

        if position < 1 or position > self.size:
            print("Nomor tidak valid.\n")
            return

        current = self.head

        for _ in range(1, position):
            current = current.next

        print(f"\nBerita ke-{position}:")
        print(current.data + "\n")


# MAIN PROGRAM
def main():
    cdll = CircularDoublyLinkedList()

    while True:
        print("====== MENU TEKS BERJALAN ======")
        print("1. Insert berita")
        print("2. Hapus berita")
        print("3. Tampilkan forward (loop)")
        print("4. Tampilkan backward (loop)")
        print("5. Tampilkan berita tertentu")
        print("6. Exit")

        pilihan = input("Pilih opsi: ")

        if pilihan == "1":
            berita = input("Masukkan teks berita: ")
            cdll.insert(berita)

        elif pilihan == "2":
            pos = int(input("Masukkan nomor berita: "))
            cdll.delete(pos)

        elif pilihan == "3":
            cdll.display_forward_loop()

        elif pilihan == "4":
            cdll.display_backward_loop()

        elif pilihan == "5":
            pos = int(input("Masukkan nomor berita: "))
            cdll.display_at(pos)

        elif pilihan == "6":
            print("Program selesai.")
            break

        else:
            print("Pilihan tidak valid.\n")


if __name__ == "__main__":
    main()