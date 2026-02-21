import time  # Untuk memberi delay (teks berjalan)

class Node:
    def __init__(self, data):
        self.data = data      # Menyimpan isi berita
        self.next = None      # Pointer ke node berikutnya
        self.prev = None      # Pointer ke node sebelumnya


class CircularDoublyLinkedList:
    def __init__(self):
        self.head = None  # Node pertama
        self.size = 0     # Jumlah node dalam list

    # Insert di akhir
    def insert(self, data):
        new_node = Node(data)  # Buat node baru

        # Jika list masih kosong
        if self.head is None:
            self.head = new_node
            # Karena circular, next dan prev menunjuk ke dirinya sendiri
            new_node.next = new_node
            new_node.prev = new_node
        else:
            tail = self.head.prev  # Ambil node terakhir

            # Hubungkan node baru ke list
            tail.next = new_node
            new_node.prev = tail
            new_node.next = self.head
            self.head.prev = new_node

        self.size += 1  # Tambah jumlah data
        print("Berita berhasil ditambahkan.\n")

    # Hapus berdasarkan nomor urut
    def delete(self, position):
        if self.head is None:
            print("List kosong.\n")
            return

        # Validasi nomor
        if position < 1 or position > self.size:
            print("Nomor tidak valid.\n")
            return

        current = self.head

        # Jika hanya ada 1 node
        if self.size == 1:
            self.head = None
        else:
            # Pindah ke posisi yang ingin dihapus
            for _ in range(position - 1):
                current = current.next

            # Hubungkan node sebelum dan sesudahnya
            current.prev.next = current.next
            current.next.prev = current.prev

            # Jika yang dihapus adalah head
            if position == 1:
                self.head = current.next

        self.size -= 1  # Kurangi jumlah data
        print("Berita berhasil dihapus.\n")

    
    # Tampilkan forward dengan delay
    def display_forward(self):
        if self.head is None:
            print("Tidak ada berita.\n")
            return

        print("\n=== Teks Berjalan (Forward) ===")
        current = self.head
        count = 0

        # Loop sesuai jumlah data
        while count < self.size:
            print(f"{count+1}. {current.data}")
            time.sleep(5)  # Delay 5 detik
            current = current.next
            count += 1
        print()

    # Tampilkan backward dengan delay
    def display_backward(self):
        if self.head is None:
            print("Tidak ada berita.\n")
            return

        print("\n=== Teks Berjalan (Backward) ===")
        current = self.head.prev  # Mulai dari tail
        count = self.size

        # Loop mundur
        while count > 0:
            print(f"{count}. {current.data}")
            time.sleep(5)  # Delay 5 detik
            current = current.prev
            count -= 1
        print()

    # Tampilkan berita tertentu
    def display_at(self, position):
        if self.head is None:
            print("List kosong.\n")
            return

        # Validasi nomor
        if position < 1 or position > self.size:
            print("Nomor tidak valid.\n")
            return

        current = self.head

        # Pindah ke posisi yang dipilih
        for _ in range(position - 1):
            current = current.next

        print(f"\nBerita ke-{position}:")
        print(current.data)
        print()


# Menu Program (User Interface)
def menu():
    cdll = CircularDoublyLinkedList()  # Buat objek list

    while True:
        print("====== MENU TEKS BERJALAN ======")
        print("1. Insert berita")
        print("2. Hapus berita")
        print("3. Tampilkan berita forward")
        print("4. Tampilkan berita backward")
        print("5. Tampilkan berita tertentu")
        print("6. Exit")

        pilihan = input("Pilih Opsi: ")

        if pilihan == "1":
            berita = input("Masukkan teks berita: ")
            cdll.insert(berita)

        elif pilihan == "2":
            nomor = int(input("Masukkan nomor berita yang ingin dihapus: "))
            cdll.delete(nomor)

        elif pilihan == "3":
            cdll.display_forward()

        elif pilihan == "4":
            cdll.display_backward()

        elif pilihan == "5":
            nomor = int(input("Masukkan nomor berita: "))
            cdll.display_at(nomor)

        elif pilihan == "6":
            print("Program selesai.")
            break

        else:
            print("Pilihan tidak valid.\n")


# Menjalankan program
if __name__ == "__main__":
    menu()