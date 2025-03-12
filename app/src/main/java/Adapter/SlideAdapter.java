//package Adapter;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//import androidx.viewpager2.widget.ViewPager2;
//
//import com.bumptech.glide.Glide;
//import com.example.admod.R;
//
//import java.util.ArrayList;
//
//import Model.SliderModel;
//
//public class SlideAdapter extends RecyclerView.Adapter<SlideAdapter.SliderViewholder> {
//
//    private ArrayList<SliderModel> sliderModels;
//    private ViewPager2 viewPager2;
//    private Context context;
//    private Runnable runnable=new Runnable() {
//        @Override
//        public void run() {
//            sliderModels.addAll(sliderModels);
//            notifyDataSetChanged();
//        }
//    };
//
//    public SlideAdapter(ArrayList<SliderModel> sliderModels, ViewPager2 viewPager2) {
//        this.sliderModels = sliderModels;
//        this.viewPager2 = viewPager2;
//    }
//
//    @NonNull
//    @Override
//    public SlideAdapter.SliderViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        context = parent.getContext();
//        return new SliderViewholder(LayoutInflater.from(context).inflate(R.layout.banners,parent,false));
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull SlideAdapter.SliderViewholder holder, int position) {
//        holder.setImage(sliderModels.get(position));
//        if(position == sliderModels.size()-2){
//            viewPager2.post(runnable);
//        }
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return sliderModels.size();
//    }
//    public class SliderViewholder extends RecyclerView.ViewHolder{
//        private ImageView imageView;
//        public SliderViewholder(@NonNull View itemView) {
//            super(itemView);
//            imageView = itemView.findViewById(R.id.bannerview);
//        }
//            void setImage(SliderModel slidermodel){
//                Glide.with(context)
//                        .load(slidermodel.getImageUrl())
//                        .into(imageView);
//        }
//    }
//}
package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.admod.R;

import java.util.ArrayList;

import Model.SliderModel;

public class SlideAdapter extends RecyclerView.Adapter<SlideAdapter.SliderViewholder> {

    // Danh sách chứa các đối tượng SliderModel (chứa thông tin ảnh)
    private ArrayList<SliderModel> sliderModels;
    // ViewPager2 để hiển thị các ảnh theo dạng slideshow
    private ViewPager2 viewPager2;
    // Context để truy cập tài nguyên của ứng dụng
    private Context context;

    // Runnable để tự động chuyển ảnh sau một khoảng thời gian
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // Kiểm tra nếu chưa phải ảnh cuối thì chuyển tiếp
            if (viewPager2.getCurrentItem() < sliderModels.size() - 1) {
                viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1, true);
            }
        }
    };

    // Constructor khởi tạo adapter với danh sách ảnh và ViewPager2
    public SlideAdapter(ArrayList<SliderModel> sliderModels, ViewPager2 viewPager2) {
        this.sliderModels = sliderModels;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public SlideAdapter.SliderViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Lấy context từ ViewGroup cha
        context = parent.getContext();
        // Inflate layout của từng item (banners.xml)
        return new SliderViewholder(LayoutInflater.from(context).inflate(R.layout.banners, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SlideAdapter.SliderViewholder holder, int position) {
        // Gán ảnh vào ImageView
        holder.setImage(sliderModels.get(position));

        // Nếu ảnh hiện tại là ảnh cuối cùng, đặt timer tự động chuyển sau 3 giây
        if (position == sliderModels.size() - 1) {
            viewPager2.postDelayed(runnable, 3000);
        }
    }

    @Override
    public int getItemCount() {
        return sliderModels.size(); // Trả về số lượng ảnh trong danh sách
    }

    // ViewHolder để ánh xạ và quản lý các item trong RecyclerView
    public class SliderViewholder extends RecyclerView.ViewHolder {
        private ImageView imageView; // ImageView để hiển thị ảnh

        public SliderViewholder(@NonNull View itemView) {
            super(itemView);
            // Ánh xạ ImageView từ layout banners.xml
            imageView = itemView.findViewById(R.id.bannerview);
        }

        // Hàm đặt ảnh vào ImageView bằng thư viện Glide
        void setImage(SliderModel slidermodel) {
            Glide.with(context)
                    .load(slidermodel.getImage()) // Load ảnh từ URL
                    .into(imageView); // Hiển thị ảnh vào ImageView
        }
    }
}

