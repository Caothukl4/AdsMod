package Model;

public class SliderModel {
    private String image; // Đổi tên thành "image"

    public SliderModel() {}

    public SliderModel(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
