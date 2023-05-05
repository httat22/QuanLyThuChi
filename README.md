# Ứng dụng quản lý thu chi

Ứng dụng giúp bạn quản lý thu chi của bạn và dữ liệu được lưu trữ trong file xml.

## Bài Tập Giữa Phần:
Phát triển Ứng dụng Quản lý Ấn phẩm
Yêu cầu: 
- Nhập thông tin thu; Nhập thông tin chi; Cảnh báo chi quá thu
- Thống kê theo tháng, theo tuần, theo ngày; Sắp xếp

## Mô tả ứng dụng
### 1. Tên ứng dụng:
Ứng dụng quản lý thu chi
### 2. Login
- User: admin
- Password: admin
### 3. Chức năng:
- Thêm, xóa, sửa thông tin thu (chi): gồm 2 mục Income Management và Expense Management với các chức năng tương tự nhau.
    + Trường Id: có kiểu dữ liệu số nguyên int, tự động được chương trình thiết lập cho một thu (chi) mới khi nhập vào, bắt đầu từ 1, và tăng dần 1 đơn vị khi người dùng nhập thông tin vào.
    + Trường Date: hiển thị một JdateChooser để người dùng chọn ngày khi nhấn vào icon Mặc định sẽ lấy ngày hiện tại để hiển thị.
    + Trường Note: có kiểu dữ liệu String, mục đích để mô tả thông tin thu (chi) của người dùng, không bắt buộc phải nhập.
    + Trường Income (Expense): có kiểu dữ liệu long, để nhập giá tiền của khoản thu (chi), không được để trống.
    + Trường category: hiển thị một JComboBox để người dùng lựa chọn danh mục phân loại khoản thu (chi). 
- Sắp xếp theo các tiêu chí( Date, Price, Category, ID ).
- Tính tổng thu nhập theo ngày, tháng, năm, tất cả: người dùng cần lựa chọn thời gian muốn thông kê ở trường "Select statistics date" bằng cách nhấn vào icon, sau đó lựa chọn hình thức thống kê gồm: Day, Month, Year, All. Nếu khoản chi lớn hơn khoản thu thì sẽ đưa ra một thông báo cảnh báo với người dùng. 
- Làm mới: đưa các trường ở mục Income Management và Expense Management hiển thị thị về mặc định

### 4. Hướng dẫn sử dụng:
#### a. Tại màn hình Đăng nhập:
Người dùng phải đăng nhập đúng tài khoản đã cung cấp để sử dụng

#### b. Tại màn hình chính của ứng dụng:
Tại mục Income Management và Expense Management:
+ Người dùng nhập, sửa, xóa các thông tin của khoản thu (chi) theo các trường ID, Date, Note, Income (Expense), Category.
+ Để thao tác thêm / xóa / sửa được thực hiện thì người dùng phải click chuột vào các button tương ứng ADD, EDIT, DELETE, CLEAR tương ứng tùy theo mục đích.
+ Trong 2 table, người dụng có thể click chuột vào hàng chứa thông tin của khoản chi để thông tin của khoản chi đấy được lấy và hiển trị theo các trường trong mục Income Management và Expense Management. Tại 2 mục này, người dùng lại có thể thực hiện các thao tác với các khoản thu (chi).

Tại mục Statistics:
+ Để thực hiện tính năng thống kê, người dùng cần lựa chọn thời gian sau đó lựa chọn kiểu thống kê theo "Day", "Month", "Year", "All". Tổng khoản thu và khoản chi sẽ được hiển thị ở trường "Total income", "Total Expenses", nếu khoản chi lớn hơn khoản thu thì sẽ có thông báo cảnh báo người dùng.
+ Để sắp xếp danh sách ấn phẩm, mặc định thứ tự được sắp xếp theo ID, người dùng có thể lựa chọn những các sắp xếp khác bằng cách click vào các button "Sort by date", "Sort by money", "Sort by category", "Sort by ID".