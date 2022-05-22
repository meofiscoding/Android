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
  - Các kiểu chữ `Material Typography` có trong `MaterialTheme`, chỉ cần thêm chúng vào các thành phần kết hợp `Text`.
  
  > 4. SHAPES
  - Với `Shape`, bạn có thể thêm những chi tiết hoàn thiện.
  - Trước tiên, hãy gói nội dung thông báo bằng văn bản vào xung quanh thành phần kết hợp `Surface.`
  - Theo đó cho phép tùy chỉnh `hình dạng`(shapes) và `độ cao`(elevation) của nội dung
  ```
  // ...
// ...
import androidx.compose.material.Surface

@Composable
fun MessageCard(msg: Message) {
   Row(modifier = Modifier.padding(all = 8.dp)) {
       Image(
           painter = painterResource(R.drawable.profile_picture),
           contentDescription = null,
           modifier = Modifier
               .size(40.dp)
               .clip(CircleShape)
               .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
       )
       Spacer(modifier = Modifier.width(8.dp))

       Column {
           Text(
               text = msg.author,
               color = MaterialTheme.colors.secondaryVariant,
               style = MaterialTheme.typography.subtitle2
           )

           Spacer(modifier = Modifier.height(4.dp))

           Surface(shape = MaterialTheme.shapes.medium, elevation = 1.dp) {
               Text(
                   text = msg.body,
                   modifier = Modifier.padding(all = 4.dp),
                   style = MaterialTheme.typography.body2
               )
           }
       }
   }
}
  
  ```
  
  > 5. ENABLE DARK MODE
  - Bạn có thể bật [giao diện tối](https://developer.android.com/guide/topics/ui/look-and-feel/darktheme) (hoặc chế độ ban đêm) để tránh hiển thị màn hình sáng vào ban đêm hoặc để tiết kiệm pin của thiết bị.
  - Nhờ sự hỗ trợ `Material Design`, `Jetpack Compose` có thể xử lý giao diện tối theo mặc định. 
  - Việc sử dụng màu, văn bản và nền của Material Design sẽ tự động thích ứng với nền tối.
  - Bạn có thể tạo nhiều `bản xem trước` (@Preview) trong tệp dưới dạng các `hàm` riêng biệt hoặc thêm nhiều `chú thích` vào cùng một `hàm`.
  - Thêm chú thích `xem trước` mới và bật `chế độ ban đêm`.
  ```
  // ...
import android.content.res.Configuration

@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun PreviewMessageCard() {
   ComposeTutorialTheme {
       MessageCard(
           msg = Message("Colleague", "Hey, take a look at Jetpack Compose, it's great!")
       )
   }
}
  
  ```
- Lựa chọn màu cho giao diện sáng và tối được xác định trong tệp `Theme.kt` do IDE tạo.
![image](https://user-images.githubusercontent.com/82217333/169660182-28c2f856-9d97-4fee-9640-c39ba40508a3.png)

## 4. List and Animation ##
  > 1. Create a list of message
  - Bạn cần tạo một hàm `Conversation` để hiển thị nhiều thông báo.
  - Đối với trường hợp này, hãy sử dụng [LazyColumn](https://developer.android.com/reference/kotlin/androidx/compose/foundation/lazy/package-summary#LazyColumn(androidx.compose.ui.Modifier,androidx.compose.foundation.lazy.LazyListState,androidx.compose.foundation.layout.PaddingValues,kotlin.Boolean,androidx.compose.foundation.layout.Arrangement.Vertical,androidx.compose.ui.Alignment.Horizontal,androidx.compose.foundation.gestures.FlingBehavior,kotlin.Boolean,kotlin.Function1)) và [LazyRow](https://developer.android.com/reference/kotlin/androidx/compose/foundation/lazy/package-summary#LazyRow(androidx.compose.ui.Modifier,androidx.compose.foundation.lazy.LazyListState,androidx.compose.foundation.layout.PaddingValues,kotlin.Boolean,androidx.compose.foundation.layout.Arrangement.Horizontal,androidx.compose.ui.Alignment.Vertical,androidx.compose.foundation.gestures.FlingBehavior,kotlin.Boolean,kotlin.Function1)) của Compose
  - Các thành phần kết hợp này chỉ `hiển thị các phần tử hiển thị trên màn hình`, vì vậy chúng được thiết kế để có hiệu quả với những `trang thông tin dài`.
 
```
// ...
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(message)
        }
    }
}

@Preview
@Composable
fun PreviewConversation() {
    ComposeTutorialTheme {
        Conversation(SampleData.conversationSample)
    }
}

  
```
  > 2. Animate message while expanding
- Bạn sẽ bổ sung khả năng `mở rộng thông báo` để hiển thị một thông báo dài hơn, giúp `hoạt hóa` cả kích thước nội dung lẫn màu nền.
- Để theo dõi những thay đổi về trạng thái này, bạn cần sử dụng các hàm `remember` và `mutableStateOf`.
- Các `hàm kết hợp` (Composable) có thể `lưu trữ` trạng thái cục bộ trong bộ nhớ bằng cách sử dụng `remember` và `theo dõi` các `thay đổi đối với giá trị` được truyền đến `mutableStateOf`.  
- `Thành phần kết hợp (và các yếu tố có thể kết hợp con)` sử dụng trạng thái này sẽ được `tự động vẽ lại` khi giá trị này được cập nhật => Hành động này được gọi là [tái kết hợp](https://developer.android.com/jetpack/compose/mental-model#recomposition).
- Khi bạn sử dụng các `API trạng thái` của `Compose` như `remember` và `mutableStateOf`, mọi `thay đổi về trạng thái` sẽ tự động `cập nhật giao diện người dùng`.
- ⭐️ Lưu ý: Bạn sẽ cần thêm các mục nhập sau để sử dụng đúng `by`. <kbd> Alt+Enter </kbd> hoặc <kbd> Option+Enter </kbd> sẽ thêm các mục nhập đó cho bạn.
`import androidx.compose.runtime.getValue`
`import androidx.compose.runtime.setValue`
- Giờ đây, bạn có thể thay đổi nền của nội dung thông báo dựa trên isExpanded khi nhấp vào một thông báo.




  


 
