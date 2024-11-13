# 📚Lab week 5
## 📝Tạo trang web cho phép ứng viên tìm công việc và gợi ý công việc phù hợp với ứng viên khi log vào. Trang web cho phép công ty đăng việc tuyển và gợi ý danh sách ứng viên phù hợp với công việc theo số % filter.
## 🗞Nội dung của project:
- Login tài khoản với email, số điện thoại và cho biết đang login với tư cách là ứng viên/công ty.
### Login với tư cách là ứng viên
- Đề xuất các công việc phù hợp với skill của ứng viên với mức độ phù hợp >= 50% 
- Ứng viên có thể xem toàn bộ công việc, tìm kiếm công việc them từ khóa tên công việc. 
- Hiển thị thông tin chi tiết công việc khi ứng viên nhấn vào 1 công việc. Gợi ý các skill mà ứng viên còn thiếu để phù hợp với công việc đó.
### Login với tư cách là công ty
- Hiển thị danh sách các công việc của công ty đã đăng. Tìm kiếm công việc theo từ khóa.
- Cho phép công ty đăng tuyển 1 công việc mới và chỉnh sửa các công việc đã tổn tại với các skill mong muốn.
- Hiển thị thông tin chi tiết công việc và danh sách ứng viên phù hợp với công việc đó theo số % (50-90) được filter khi nhấn vào công việc bất kỳ.
## 🛠 Hướng dẫn cài đặt
##### 1. Clone project từ github
```bash
https://github.com/thahhtuyenn/TranThiThanhTuyen_21020281_WWW.git
```
##### 2. Di chuyển đến thư mục LabWeek5 hoặc sử dụng intelij để mở thư mục LabWeek5
```bash
cd ./LabWeek5
mvn install
```
##### 3. Import cơ sở dữ liệu của dự án bằng file .sql
[Database](https://github.com/thahhtuyenn/TranThiThanhTuyen_21020281_WWW/blob/main/LabWeek5/databases/data-week-5.sql) hoặc open file khi đã clone về máy ở thư mục `database/data-week-5.sql`
##### 4. Chạy ứng dụng (có thể dùng lệnh bên dưới hoặc chạy với intelij)
```bash
mvn spring-boot:run
```
#### 5. Truy cập website theo đường dẫn
```bash
http://localhost:8080
```
### Tài khoản demo 
** Đây chỉ là tài khoản tượng trưng để login demo ứng dụng, là 1 trong số các thông tin ứng viên / công ty được lưu trữ trong CSDL
#### 1. Ứng viên
- email
```bash
angelo.hermann@yahoo.com
```
- phone
```bash
(730) 215-4530
```
#### 2. Công ty
- email
```bash
tanya.moen@hotmail.com
```
- phone
```bash
(689) 752-4159
```
## Demo ứng dụng
![Demo](https://github.com/thahhtuyenn/TranThiThanhTuyen_21020281_WWW/blob/main/LabWeek5/demo/demo-www-lab5.gif)

## Tác giả, liên hệ
- Facebook: [Trần Thị Thanh Tuyền](https://www.facebook.com/thahhtuyenn090603)
- Github: [@thahhtuyenn](https://github.com/thahhtuyenn)
- Email: thanhtuyen9623@gmail.com
