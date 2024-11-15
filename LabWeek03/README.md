# 🗓 Lab week 3
## 📝Viết API cho đối tượng product khi product có nhiều giá ở nhiều thời điểm, tạo trang web hiển thị danh sách các product và cho phép người dùng thêm - sửa - xóa.
## 🗞Nội dung của project
### REST API
- Các thao tác CRUD cho các đối tượng
- Lọc ra các đối tượng còn đang được bán
- Cập nhật trạng thái của đối tượng khi delete.
### FRONTEND
- Tạo các trang web cho việc hiển thị sản phẩm.
- Cho phép xóa sản phẩm, chỉnh sửa thông tin, thêm sản phẩm mới.
## 🛠 Hướng dẫn cài đặt
##### 1. Clone project từ github
```bash
git clone https://github.com/thahhtuyenn/TranThiThanhTuyen_21020281_WWW.git
```
##### 2. Sử dụng intelij để mở project LabWeek3
##### 3. Từ project LabWeek3, sử dụng file `database-lab-week-3.sql` để tạo các bảng
[Database](https://github.com/thahhtuyenn/TranThiThanhTuyen_21020281_WWW/blob/main/LabWeek03/database/database-lab-week-3.sql) hoặc `database/database-lab-week-3.sql` 
##### 4. Add payara server (6.19.0 - version được sử dụng để tạo ra project)
##### 5. Run project lên và sẽ tự nhảy sang trang index với đường dẫn `http://localhost:8080/LabWeek03-1.0-SNAPSHOT/`
## 🌐 Demo ứng dụng
### Danh sách các sản phẩm vẫn còn hoạt động (API)
```bash
http://localhost:8080/LabWeek03-1.0-SNAPSHOT/api/products/status=ACTIVE
```
![Product active](https://github.com/thahhtuyenn/TranThiThanhTuyen_21020281_WWW/blob/main/LabWeek03/demo/picture/product-active.png)
### Danh sách các sản phẩm đã xóa (API)
```bash
http://localhost:8080/LabWeek03-1.0-SNAPSHOT/api/products/status=TERMINATED
```
![Product terminated](https://github.com/thahhtuyenn/TranThiThanhTuyen_21020281_WWW/blob/main/LabWeek03/demo/picture/product-terminated.png)

### Demo với giao diện website
- Khi run project, trang web sẽ bậc ra 1 giao diện index, khi nhấn vào nút `Go to the page of products` sẽ chuyển sang trang danh sách sản phẩm. Người dùng có thể chọn `Delete` để xóa sản phẩm bất kỳ. (Lưu ý: 1 sả phẩm sẽ có danh sách giá theo thời gian, giá của sản phẩm hiển thị là giá được áp dụng mới nhất của sản phẩm đó)
![List product](https://github.com/thahhtuyenn/TranThiThanhTuyen_21020281_WWW/blob/main/LabWeek03/demo/gif/lab3-show-product-delete.gif)
- Khi muốn chỉnh sửa thông tin sản phẩm, người dùng nhấn nút `Edit` ngay sản phẩm đó, giao diện chuyển sang trang edit, người dùng chỉnh sửa thông tin và nhấn `Submit`.
![edit product](https://github.com/thahhtuyenn/TranThiThanhTuyen_21020281_WWW/blob/main/LabWeek03/demo/gif/lab3-edit.gif)
- Người dùng có thể thêm 1 sản phẩm mới bằng cách nhấn `Add` và nhập thông tin sản phẩm, nhấn `Submit` để hoàn thành.
![add product](https://github.com/thahhtuyenn/TranThiThanhTuyen_21020281_WWW/blob/main/LabWeek03/demo/gif/lab3-add.gif)


### 📂Tài liệu tham khảo 
- [Java Servlet Documentation](https://docs.oracle.com/javaee/7/tutorial/servlets.htm)
- [Bootstrap 5 W3Schools](https://www.w3schools.com/bootstrap5/) 
## Tác giả, liên hệ
- Facebook: [Trần Thị Thanh Tuyền](https://www.facebook.com/thahhtuyenn090603)
- Github: [@thahhtuyenn](https://github.com/thahhtuyenn)
- Email: thanhtuyen9623@gmail.com