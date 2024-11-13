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
git clone https://github.com/thahhtuyenn/TranThiThanhTuyen_21020281_WWW.git
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
http://localhost:8080/
```
# 🌐Demo ứng dụng
** Khi run chương trình, truy cập vào đường dẫn `http://localhost:8080/` -> website hiển thị toàn bộ công việc, hiển thị theo trang với mỗi trang 20 công việc. 🏠`/home`
![home](https://github.com/thahhtuyenn/TranThiThanhTuyen_21020281_WWW/blob/main/LabWeek5/demo/gif/home-detail-job.gif)

## 💼Demo candidate
** Login với tư cách là candidate
- email 
```bash
jordan.becker@yahoo.com
```
- phone
```bash
(469) 811-1135
```
![Candidate login](https://github.com/thahhtuyenn/TranThiThanhTuyen_21020281_WWW/blob/main/LabWeek5/demo/gif/candidate-login.gif) 
- Sau khi login, website hiển thị các công việc phù hợp với skill mà candidate có >= 50% số skill mà công việc yêu cầu, khi nhấn vào bất kỳ công việc nào thì sẽ hiển thị thông tin chi tiết, thông tin công ty đăng tuyển, các skill mà candidate cần trao dồi thêm. Cho phép candidate xem tất cả công việc khác.
![Candidate job detail](https://github.com/thahhtuyenn/TranThiThanhTuyen_21020281_WWW/blob/main/LabWeek5/demo/gif/candidate-show-detail-job-show-all.gif) 
- Candidate có thể tìm kiếm công việc mong muốn theo từ khóa tên công việc nhập vào ô tìm kiếm.
![Candidate search](https://github.com/thahhtuyenn/TranThiThanhTuyen_21020281_WWW/blob/main/LabWeek5/demo/gif/candidate-search-job.gif)

## 🏢Demo company
** Login với tư cách company
- email 
```bash
un.durgan@hotmail.com
```
- phone
```bash
(730) 235-8253
```
![Company login](https://github.com/thahhtuyenn/TranThiThanhTuyen_21020281_WWW/blob/main/LabWeek5/demo/gif/company-login.gif)
- Sau khi login, website hiển thị trang chủ dành cho company, dánh ách công việc của company đã đăng, khi nhấn vào bất kỳ công việc, website hiển thị thông tin công việc và danh sách ứng viên phù hợp với công việc đó, mặc định mức độ phù hợp là 50%, company có thể filter ứng viên phù hợp với mức độ % cao hơn.
![Company job detail](https://github.com/thahhtuyenn/TranThiThanhTuyen_21020281_WWW/blob/main/LabWeek5/demo/gif/company-show-detail.gif)
- Company có thể tìm kiếm công việc theo từ khóa tên công vệc.
![Company search](https://github.com/thahhtuyenn/TranThiThanhTuyen_21020281_WWW/blob/main/LabWeek5/demo/gif/company-search.gif)
- Website cho phép company chỉnh sửa thông tin công việc đã đăng tuyển để phù hợp hơn với nhu cầu tuyển dụng.
![Company edit job](https://github.com/thahhtuyenn/TranThiThanhTuyen_21020281_WWW/blob/main/LabWeek5/demo/gif/company-edit-job.gif)
- Website cho phép company đăng tuyển 1 công việc mới với các skill mong muốn.
https://github.com/thahhtuyenn/TranThiThanhTuyen_21020281_WWW/blob/main/LabWeek5/demo/gif/company-create-job.gif
## Tác giả, liên hệ
- Facebook: [Trần Thị Thanh Tuyền](https://www.facebook.com/thahhtuyenn090603)
- Github: [@thahhtuyenn](https://github.com/thahhtuyenn)
- Email: thanhtuyen9623@gmail.com
