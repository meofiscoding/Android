# Jetpack Compose #
## 1. Hàm có khả năng kết hợp ##
- Jetpack Compose được xây dựng xung quanh các hàm có `khả năng kết hợp`.
- Các hàm này cho phép bạn `xác định giao diện người dùng` của ứng dụng theo cách `lập trình bằng cách mô tả giao diện của ứng dụng` đó và cung cấp các phần phụ thuộc dữ liệu thay vì tập trung vào quy trình xây dựng giao diện người dùng (khởi chạy một phần tử, đính kèm phần tử đó vào phần tử mẹ, v.v.). 
-  Để tạo một hàm có khả năng kết hợp, chỉ cần thêm chú thích `@Composable` vào tên hàm.
-  Jetpack Compose dùng một trình bổ trợ biên dịch `Kotlin` để biến những `hàm có khả năng kết hợp` này thành các `thành phần trên giao diện người dùng` của ứng dụng.
-  Ví dụ: hàm kết hợp `Text` do thư viện Giao diện người dùng trong `Compose` xác định sẽ hiển thị một `nhãn văn bản` trên màn hình.
-  Chú thích `@Preview` cho phép bạn `xem trước` các hàm kết hợp trong Android Studio mà không cần phải `tạo` và `cài đặt` ứng dụng trên thiết bị Android hoặc trình mô phỏng. 
-  Hàm `Preview....` ko nhận tham số

## 2. Layout ##
- Các thành phần trên giao diện người dùng được phân cấp, thành phần này được chứa trong thành phần khác.
![image](https://user-images.githubusercontent.com/82217333/169515989-e2874b43-cc9a-472a-a2fa-400c716e4407.png)
- Trong Compose, bạn xây dựng một `hệ phân cấp giao diện người dùng` bằng cách gọi `hàm Composable`  từ các `hàm Composable khác`.

  > 1. __USING COLUMN__
- Hàm Column cho phép bạn sắp xếp các phần tử theo chiều dọc.
- `Tips`: 
  * Sử dụng `Row` để sắp xếp các mục theo `chiều ngang` 
  * `Box` để `nhóm` các phần tử.
  
  > 2. __ADD AN IMAGE ELEMENT__
- Sử dụng [Trình quản lý tài nguyên](https://developer.android.com/studio/write/resource-manager#import) để nhập hình ảnh từ thư viện ảnh của bạn hoặc sử dụng hình ảnh dưới dạng [link]() này.

  > 3. __CONFIGURE YOUR LAYOUT__
- Để trang trí hoặc định cấu hình một thành phần kết hợp, tính năng Compose sử dụng `công cụ sửa đổi`. 
- Những `công cụ sửa đổi` này cho phép bạn thay đổi `kích thước`, `bố cục`, `giao diện` của các `Composable` hoặc `thêm các lượt tương tác cấp cao`, chẳng hạn như `tạo` một `thành phần có thể nhấp`.
## 3.Material Design ## 
- `Compose` được xây dựng để hỗ trợ các nguyên tắc thiết kế `Material Design`.
- Nhiều phần tử thành phần trên giao diện người dùng triển khai thiết kế `Material Design` ngay từ đầu. 
![image](https://user-images.githubusercontent.com/82217333/169569686-cdd96cfb-7dd2-428b-9645-0de459f0f704.png)
  
  > 1. USING MATERIAL DESIGN
  - `Jetpack Compose` triển khai thiết kế `Material Design` và các `thành phần` trên giao diện người dùng ngay từ đầu. 
  - Bạn sẽ cải thiện giao diện của thành phần kết hợp `MessageCard` bằng cách sử dụng thiết kế `Material Design`.
  -Để bắt đầu, hãy chèn hàm `MessageCard` bằng giao diện `Material` đã tạo trong dự án `ComposeTutorialTheme` của bạn. Thực hiện việc đó trong cả hàm @Preview và setContent. 
  
```
   // ...

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTutorialTheme {
                MessageCard(Message("Android", "Jetpack Compose"))
            }
        }
    }
}

@Preview
@Composable
fun PreviewMessageCard() {
    ComposeTutorialTheme {
        MessageCard(
            msg = Message("Colleague", "Take a look at Jetpack Compose, it's great!")
        )
    }
}
 ```
- Thao tác này cho phép `thành phần kết hợp` kế thừa các định kiểu đã được xác định trong `giao diện` của ứng dụng, nhờ đó đảm bảo `tính nhất quán` trên ứng dụng.
- `Material Design` được dựng xoay quanh ba trụ cột: `Color`, `Typography` và `Shape`. Bạn sẽ thêm từng cái một.
> ⭐️ Lưu ý: Mẫu Hoạt động Compose trống sẽ tạo một giao diện mặc định cho dự án để bạn có thể tùy chỉnh MaterialTheme. Nếu đặt tên cho dự án khác với tên trong ComposeTutoria, bạn có thể tìm thấy giao diện tùy chỉnh của mình ở tệp Theme.kt trong gói con ui.theme.`

  > 2. Color
 - Sử dụng `MaterialTheme.colors` để tạo kiểu với các màu trong giao diện được gói vào.
 - Bạn có thể sử dụng các giá trị này trong giao diện ở bất kỳ nơi nào cần tô màu.
 - Định kiểu cho tiêu đề và thêm đường viền cho hình ảnh.
 
  > 3. FONT


 
