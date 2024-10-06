## 🗓Week01: Hệ Thống Quản Lý Tài Khoản
### 📰Giới thiệu
Dự án này là một bài tập thực hành trong môn học Lập trình WWW với công nghệ Java. Trong bài tập này, bạn sẽ xây dựng một hệ thống quản lý tài khoản, phân quyền và ghi log lần đăng nhập. Hệ thống bao gồm các tính năng đăng nhập, quản lý quyền cho các tài khoản, và ghi lại nhật ký đăng nhập/đăng xuất.
### 📍Mục tiêu
- Xây dựng ứng dụng web sử dụng JakartaEE và Servlet.
- Kết nối ứng dụng với cơ sở dữ liệu MySQL/MariaDB để quản lý tài khoản và quyền hạn.
- Thực hiện các chức năng như đăng nhập, quản lý tài khoản, phân quyền và ghi log lần đăng nhập.
### Cấu trúc CSDL
Cơ sở dữ liệu có tên week1 bao gồm các bảng sau:
##### 1. Bảng account:
- `account_id`: int (primary key, auto-increment)
- `full_name`: varchar(255)
- `password`: varchar(255)
- `email`: varchar(255)
- `phone`: varchar(15)
- `status`: int (1-active, 0-deactive, -1-deleted)
##### 2. Bảng role:

- `role_id`: int (primary key, auto-increment)
- `role_name`: varchar(255)
- `description`: varchar(255)
- `status: int`: (1-active, 0-deactive)
##### 3. Bảng grant_access:

- `account_id`: int (foreign key from account)
- `role_id`: int (foreign key from role)
- `is_grant`: int (0-disable, 1-enable)
- `note`: varchar(255)
##### 4. Bảng log:

- `id`: int (primary key, auto-increment)
- `account`: int (foreign key from account)
- `login_time`: datetime
- `logout_time`: datetime
- `note`: varchar(255)
### ⚙Chức năng chính 
#### Đăng nhập 
- Người dùng có thể đăng nhập với tài khoản và mật khẩu.
- Nếu đăng nhập thành công, ghi lại thời gian đăng nhập và chuyển đến trang quản lý nếu là Admin, hoặc trang thông tin cá nhân nếu là User.
#### Quản lý tài khoản
- Thêm, cập nhật, xóa tài khoản.
- Hiển thị danh sách tài khoản và quyền hạn của tài khoản.
#### Ghi log đăng nhập
- Ghi lại nhật ký khi một tài khoản đăng nhập và đăng xuất khỏi hệ thống.
### Các bước thực hiện
##### 1. Tạo cơ sở dữ liệu: Tạo các bảng như đã mô tả trong phần cấu trúc CSDL.
##### 2. Cài đặt project
- Clone repository từ GitHub:
```bash
   git clone https://github.com/thahhtuyenn/TranThiThanhTuyen_21020281_WWW.git
  ```
- Mở project có tên LabWeek1
##### 3. Chạy project
- Build và deploy project lên server ứng dụng JakartaEE (như Apache TomEE).
##### 4. Thực hiện các chức năng
- Đăng nhập bằng tài khoản có sẵn trong CSDL.
- Quản lý tài khoản (chỉ Admin).
- Hiển thị quyền hạn của tài khoản.
### Hướng dẫn cài đặt môi trường
- Java Development Kit (JDK): Phiên bản JDK 11 hoặc mới hơn.
- JakartaEE Server: Apache TomEE hoặc Payara Server.
- MariaDB: Cài đặt và tạo cơ sở dữ liệu week1.
- Git: Để clone và quản lý mã nguồn.

### 📂Tài liệu tham khảo 
- [Java Servlet Documentation](https://docs.oracle.com/javaee/7/tutorial/servlets.htm)
- [Bootstrap 5 W3Schools](https://www.w3schools.com/bootstrap5/)


## 📞Liên hệ

- Github: [thahhtuyenn](https://github.com/thahhtuyenn)
- Email: thanhtuyen9623@gmail.com
