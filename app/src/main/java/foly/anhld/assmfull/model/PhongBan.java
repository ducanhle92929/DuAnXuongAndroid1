package foly.anhld.assmfull.model;

public class PhongBan {

    private int imageResId;
//    private static int imvtrash;
//    private int imvpen;
    private String text;

    public PhongBan(String imageResId, int quantity) {
    }

    public PhongBan(int imageResId, String text) {
        this.imageResId = imageResId;
//        this.imvtrash = imvtrash;
//        this.imvpen = imvpen;
        this.text = text;
    }

//    public static Food fromJson(String json) {
//        try {
//            JSONObject jsonObject = new JSONObject(json);
//            int imageResId = jsonObject.getInt("quantity");
//
//            String text = jsonObject.getString("name");
//            return new Food(text, imageResId);
//        } catch (JSONException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }


    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

//    public int getImvtrash() {
//        return imvtrash;
//    }
//
//    public void setImvtrash(int imvtrash) {
//        this.imvtrash = imvtrash;
//    }
//
//    public int getImvpen() {
//        return imvpen;
//    }
//
//    public void setImvpen(int imvpen) {
//        this.imvpen = imvpen;
//    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
