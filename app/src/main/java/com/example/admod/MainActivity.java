package com.example.admod;

import static androidx.core.view.ViewCompat.setOverScrollMode; // Import thư viện hỗ trợ cuộn mượt hơn

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;

import com.example.admod.databinding.ActivityMainBinding; // Import View Binding cho MainActivity
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Adapter.SlideAdapter;
import Model.SliderModel;

public class MainActivity extends BaseActivity {
    // Sử dụng View Binding để thay thế findViewById và giúp code gọn gàng hơn
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater()); // Khởi tạo View Binding
        setContentView(binding.getRoot()); // Đặt giao diện từ View Binding

        initBanner(); // Gọi hàm khởi tạo banner
    }

    /**
     * Hàm này thiết lập ViewPager2 để hiển thị danh sách banner (slider)
     * @param items Danh sách ảnh (SliderModel) từ Firebase
     */
    private void banner(ArrayList<SliderModel> items) {
        // Đặt adapter cho ViewPager2
        binding.Banners.setAdapter(new SlideAdapter(items, binding.Banners));

        // Cấu hình hiển thị cho ViewPager2
        binding.Banners.setClipToPadding(false); // Không cắt bớt ảnh ở 2 bên
        binding.Banners.setClipChildren(false);  // Không giới hạn hình ảnh hiển thị bên ngoài ViewPager
        binding.Banners.setOffscreenPageLimit(3); // Giữ 3 trang bên cạnh trong bộ nhớ để cuộn mượt hơn
        binding.Banners.getChildAt(0); // Lấy View con đầu tiên

        // Đặt chế độ cuộn mượt hơn (loại bỏ hiệu ứng bật lại khi cuộn đến cuối)
        View firstChild = binding.Banners.getChildAt(0);
        if (firstChild != null) {
            firstChild.setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        }

        // Thêm hiệu ứng chuyển trang bằng cách đặt khoảng cách giữa các trang
        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40)); // Khoảng cách giữa các ảnh là 40px
        binding.Banners.setPageTransformer(compositePageTransformer);
    }

    /**
     * Hàm này lấy dữ liệu ảnh banner từ Firebase Realtime Database
     */
    private void initBanner() {
        FirebaseDatabase database = FirebaseDatabase.getInstance(); // Khởi tạo Firebase Database
        DatabaseReference myRef = database.getReference("Banners"); // Trỏ đến node "Banners" trong Database

        binding.progressBar.setVisibility(View.VISIBLE); // Hiển thị ProgressBar khi tải dữ liệu

        ArrayList<SliderModel> items = new ArrayList<>(); // Danh sách để lưu các ảnh từ Firebase

        // Lắng nghe dữ liệu từ Firebase
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) { // Kiểm tra nếu dữ liệu tồn tại trong Firebase
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        items.add(issue.getValue(SliderModel.class)); // Lấy từng ảnh và thêm vào danh sách
                    }
                    banner(items); // Sau khi lấy dữ liệu, gọi hàm banner để hiển thị slider
                    binding.progressBar.setVisibility(View.GONE); // Ẩn ProgressBar khi dữ liệu đã tải xong
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Xử lý lỗi khi lấy dữ liệu thất bại (có thể thêm log để debug)
            }
        });
    }
}
