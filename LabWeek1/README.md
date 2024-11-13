# 🗓 Lab week 1 
## 📝Tạo trang login cho người dùng đăng nhập với tài khoản - mật khẩu. Ghi lại thời gian login - logout. ADMIN quản lý tài khoản.

## 🗞Nội dung của project
** Người dùng login với tài khoản và mật khẩu
### Login với role USER
- Hiển thị thông tin tài hoản của user đã login vào và ghi lại thời gian login/logout vào CSDL.
### Login với role ADMIN
- Hiển thị danh sách tài khoản không có role ADMIN
- Cho phép thao tác cập nhật thông tin tài khoản, xóa tải khoản (thay đổi status của tài khoản), thêm 1 tài khoản mới.
## 🛠 Hướng dẫn cài đặt
##### 1. Clone project từ github
```bash
git clone https://github.com/thahhtuyenn/TranThiThanhTuyen_21020281_WWW.git
```
##### 2. Sử dụng intelij để mở project LabWeek1
##### 3. Từ projectt LabWeek1, sử dụng file `database-lab-week-1.sql` để tạo các bảng
[Database](https://github.com/thahhtuyenn/TranThiThanhTuyen_21020281_WWW/blob/main/LabWeek1/database/database-lab-week-1.sql) hoặc `database/database-lab-week-1.sql` 
##### 4. Add tomcat server > 9.0 (10.1.29 - version được sử dụng để tạo ra project)
##### 5. Run project lên và sẽ tự nhảy sang trang login với đường dẫn `http://localhost:8080/app/`

## 🌐 Demo ứng dụng
### Demo role user
Khi người dùng login vào sẽ chuyển sang trang dành cho user, hiển thị thông tin của tài khoản login.
- Tài khoản
```bash
thanhtuyen
```
- Mật khẩu
```bash
123
```
![User login](https://github.com/thahhtuyenn/TranThiThanhTuyen_21020281_WWW/blob/main/LabWeek1/demo/gif/lab1-login-user.gif)
### Demo role admin 
Khi người dùng login vào sẽ chuyển sang trang chủ dành cho admin, hiển thị danh sách các tài khoản không phải là admin và trạng thái vẫn còn hoạt động.
- Tài khoản
```bash
luilui
```
- Mật khẩu
```bash
123
```
![Admin login](https://github.com/thahhtuyenn/TranThiThanhTuyen_21020281_WWW/blob/main/LabWeek1/demo/gif/lab1-login-admin.gif)
- Admin có thể chỉnh sửa thông tin tài khoản bất kỳ
![Admin edit](https://github.com/thahhtuyenn/TranThiThanhTuyen_21020281_WWW/blob/main/LabWeek1/demo/gif/lab1-admin-edit.gif)
- Admin có thể thêm 1 tài khoản
![Admin create](https://github.com/thahhtuyenn/TranThiThanhTuyen_21020281_WWW/blob/main/LabWeek1/demo/gif/lab1-admin-create.gif)
- Admin có thể xóa 1 tài khoản (thay đổi trạng thái hoạt động)
![Admin delete](https://github.com/thahhtuyenn/TranThiThanhTuyen_21020281_WWW/blob/main/LabWeek1/demo/gif/lab1-admin-delete.gif)

### 📂Tài liệu tham khảo 
- [Java Servlet Documentation](https://docs.oracle.com/javaee/7/tutorial/servlets.htm)
- [Bootstrap 5 W3Schools](https://www.w3schools.com/bootstrap5/)
## Tác giả, liên hệ
- Facebook: [Trần Thị Thanh Tuyền](https://www.facebook.com/thahhtuyenn090603)
- Github: [@thahhtuyenn](https://github.com/thahhtuyenn)
- Email: thanhtuyen9623@gmail.com