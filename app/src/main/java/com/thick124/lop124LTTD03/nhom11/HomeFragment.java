package com.thick124.lop124LTTD03.nhom11;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private RecyclerView rcvfolder;
    private SinhVienAdapter folderAdapter;
    private List<SinhVien> folderList = new ArrayList<>();
    private DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Khởi tạo RecyclerView
        rcvfolder = view.findViewById(R.id.rcvfolder); // Đảm bảo ID khớp với file XML
        rcvfolder.setLayoutManager(new LinearLayoutManager(getContext()));

        // Khởi tạo Adapter
        folderAdapter = new SinhVienAdapter(folderList);
        rcvfolder.setAdapter(folderAdapter);

        // Tham chiếu tới Firebase Database
        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        // Tải dữ liệu từ Firebase
        loadFoldersFromFirebase();

        return view;
    }

    private void loadFoldersFromFirebase() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                folderList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    SinhVien sinhVien = dataSnapshot.getValue(SinhVien.class);
                    if (sinhVien != null) {
                        sinhVien.setMasv(dataSnapshot.getKey()); // Gán ID từ key
                        Log.d("FolderData", "Tên: " + sinhVien.getName());
                        folderList.add(sinhVien);
                    }
                }
                folderAdapter.notifyDataSetChanged(); // Cập nhật RecyclerView
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Không thể tải dữ liệu từ Firebase", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
