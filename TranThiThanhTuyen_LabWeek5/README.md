# 🗓 **Lab Week 5**

## Thông tin
#### **Trường**: Đại học Công nghiệp TP Hồ Chí Minh - IUH  
#### **Họ tên**: Trần Thị Thanh Tuyền  
#### **Mã sinh viên**: 21020281  
#### **Giảng viên hướng dẫn**: Võ Văn Hải
#### **File báo cáo**: [Tại đây](https://github.com/thahhtuyenn/TranThiThanhTuyen_21020281_WWW/blob/main/TranThiThanhTuyen_LabWeek5/21020281_TranThiThanhTuyen_BaoCao.docx)
#### **File PowerPoint**: [Tại đây]([https://github.com/thahhtuyenn/TranThiThanhTuyen_21020281_WWW/blob/main/TranThiThanhTuyen_LabWeek5/21020281_TranThiThanhTuyen_BaoCao.docx](https://github.com/thahhtuyenn/TranThiThanhTuyen_21020281_WWW/blob/main/TranThiThanhTuyen_LabWeek5/21020281_TranThiThanhTuyen.pptx))
---

## 📋 Nội dung bài làm
##### 1️⃣ Tạo các Entities, Repositories, Services 
- **Entities**: Định nghĩa các đối tượng (thực thể) trong hệ thống.
- **Repositories**: Tạo các interface truy xuất cơ sở dữ liệu.
- **Services**: Viết các lớp Service xử lý nghiệp vụ.

##### 2️⃣ Phân quyền đăng nhập với Spring Security 
- Phân quyền **ứng viên** và **công ty** qua các cấp độ truy cập khác nhau.

##### 3️⃣ Xem và cập nhật thông tin cá nhân 
- Ứng viên và công ty có thể **xem và cập nhật thông tin cá nhân** sau khi đăng nhập.

##### 4️⃣ Đăng tin tuyển dụng và kỹ năng yêu cầu  
- Công ty có thể **đăng tin tuyển dụng** và yêu cầu các kỹ năng cụ thể.

##### 5️⃣ Gợi ý công việc cho ứng viên  
- Ứng viên nhận **gợi ý công việc** phù hợp với kỹ năng của mình.

##### 6️⃣ Gửi email mời ứng viên ứng tuyển  
- Công ty có thể **gửi email mời** các ứng viên phù hợp.

##### 7️⃣ Đề xuất kỹ năng bổ sung cho ứng viên  
- Hệ thống **đề xuất các kỹ năng mới** cho ứng viên để cải thiện hồ sơ.

---

## 🌟 Demo chi tiết bài làm

### 🏷 *Tài khoản đăng nhập*
- **Ứng viên**:  
  - Tài khoản: evelin.zboncak  
  - Mật khẩu: 123456  
- **Công ty**:  
  - Tài khoản: jacque.oberbrunner  
  - Mật khẩu: 123456  

### ⚙ *Lưu ý trước khi chạy project*
##### 1. Import database từ file `src/main/resources/databases/data-lab-week-5.sql` hoặc sao chép từ [Tại đây](https://github.com/thahhtuyenn/TranThiThanhTuyen_21020281_WWW/blob/main/TranThiThanhTuyen_LabWeek5/src/main/resources/databases/data-lab-week-5.sql).
##### 2. Config Spring Security tại `src/main/java/vn/edu/iuh/fit/thanhtuyen/backend/configs/AppConfig.java`.
##### 3. Thiết lập file `src/main/resources/application.properties`.
##### 4. Truy cập vào trang chủ qua địa chỉ: `http://localhost:9623/`.

## 📍 Giao diện Demo
## 🏠 *Trang chủ - Ứng dụng: Không cần đăng nhập để xem công việc*

#### 1. 🌐 Truy cập vào địa chỉ ứng dụng
Sau khi truy cập vào địa chỉ ứng dụng: [http://localhost:9623/](http://localhost:9623/)
- Trang chủ:

![Trang chủ](https://github.com/thahhtuyenn/TranThiThanhTuyen_21020281_WWW/blob/main/TranThiThanhTuyen_LabWeek5/demo/trang-chu.png)

#### 2. 🔍 Tìm kiếm việc làm tại trang chủ
Người dùng có thể tìm kiếm các công việc mong muốn trực tiếp từ trang chủ ứng dụng.
- Tìm kiếm - Trang chủ:

![Tìm kiếm - trang chủ](https://github.com/thahhtuyenn/TranThiThanhTuyen_21020281_WWW/blob/main/TranThiThanhTuyen_LabWeek5/demo/tim-kiem-trang-chu.png)

#### 3. 📄 Xem chi tiết công việc
Khi nhấn vào "Xem chi tiết công việc", người dùng có thể xem thông tin chi tiết về công việc, bao gồm mô tả công việc, yêu cầu, và các kỹ năng cần thiết.
- Chi tiết công việc - Trang chủ:

![Chi tiết công việc - trang chủ](https://github.com/thahhtuyenn/TranThiThanhTuyen_21020281_WWW/blob/main/TranThiThanhTuyen_LabWeek5/demo/gif/chi-tiet-cv-trang-chu.gif)

## 👩‍💻 *Chức năng dành cho Ứng viên*
### 1. 💼 Trang chủ Ứng viên và Chi tiết Công việc
Sau khi đăng nhập, trang chủ ứng viên sẽ hiển thị các công việc đề xuất dành cho ứng viên. Ứng viên cũng có thể xem tất cả công việc trong danh sách.

Khi ứng viên nhấn chọn **Xem chi tiết**, trang web sẽ hiển thị các thông tin về công việc, bao gồm:
- Thông tin công ty
- Kỹ năng ứng viên phù hợp với kỹ năng công việc yêu cầu, kèm theo phần trăm (%).
- Kỹ năng ứng viên chưa có cùng với gợi ý kỹ năng cần học thêm để đáp ứng yêu cầu công việc.

![Ứng viên - Chi tiết công việc](https://github.com/thahhtuyenn/TranThiThanhTuyen_21020281_WWW/blob/main/TranThiThanhTuyen_LabWeek5/demo/gif/uv-chi-tiet-cv.gif)


### 2. 🔍 Tìm kiếm công việc  
   Ứng viên có thể tìm kiếm công việc theo từ khóa và xem các chi tiết công việc phù hợp.

   ![Tìm kiếm - Ứng viên](https://github.com/thahhtuyenn/TranThiThanhTuyen_21020281_WWW/blob/main/TranThiThanhTuyen_LabWeek5/demo/uv-tim-kiem.png)

### 3. 📝 Xem và cập nhật thông tin cá nhân  
   Ứng viên có thể cập nhật thông tin cá nhân của mình sau khi đăng nhập.

   ![Cập nhật thông tin cá nhân](https://github.com/thahhtuyenn/TranThiThanhTuyen_21020281_WWW/blob/main/TranThiThanhTuyen_LabWeek5/demo/gif/uv-cap-nhat-ca-nhan.gif)

---

## 📈 *Chức năng Dành cho Công ty Tuyển Dụng*

### 1. 🔍 Tìm kiếm công việc tại trang chủ công ty hoặc trang quản lý việc làm
Công ty có thể dễ dàng tìm kiếm các công việc tại trang chủ hoặc trong phần quản lý việc làm.

- Trang chủ công ty

![Công ty - tìm kiếm](https://github.com/thahhtuyenn/TranThiThanhTuyen_21020281_WWW/blob/main/TranThiThanhTuyen_LabWeek5/demo/ct-tim-kiem.png)

- Quản lý việc làm

![Công ty - tìm kiếm quản lý](https://github.com/thahhtuyenn/TranThiThanhTuyen_21020281_WWW/blob/main/TranThiThanhTuyen_LabWeek5/demo/ct-tim-kiem-quan-ly.png)

### 2. 💼 Trang chủ, Xem chi tiết công việc, Hiển thị ứng viên phù hợp và lọc theo %
Khi nhấn vào chi tiết công việc, công ty có thể xem các ứng viên phù hợp với công việc và lọc ứng viên theo mức độ phù hợp.

- Chi tiết công việc

![Công ty - chi tiết công việc](https://github.com/thahhtuyenn/TranThiThanhTuyen_21020281_WWW/blob/main/TranThiThanhTuyen_LabWeek5/demo/gif/ct-chi-tiet-cv-loc-uv.gif)

### 3. 👤 Xem hồ sơ ứng viên và gửi email mời ứng viên
Công ty có thể xem thông tin ứng viên và gửi email mời họ ứng tuyển cho công việc hiện tại.

- Xem hồ sơ và gửi email:

![Công ty - xem hồ sơ ứng viên](https://github.com/thahhtuyenn/TranThiThanhTuyen_21020281_WWW/blob/main/TranThiThanhTuyen_LabWeek5/demo/gif/ho-so-uv-gui-email.gif)

- Email gửi cho ứng viên:

![Công ty - email](https://github.com/thahhtuyenn/TranThiThanhTuyen_21020281_WWW/blob/main/TranThiThanhTuyen_LabWeek5/demo/email.png)

### 4. 📧 Xem các email đã gửi đến các ứng viên
Công ty có thể theo dõi các email đã gửi cho các ứng viên từ hệ thống.

- Xem email đã gửi:

![Công ty - xem email đã gửi](https://github.com/thahhtuyenn/TranThiThanhTuyen_21020281_WWW/blob/main/TranThiThanhTuyen_LabWeek5/demo/gif/ct-email-da-gui.gif)

### 5.✏️ Cập nhật công việc đã đăng tuyển
Công ty có thể dễ dàng cập nhật thông tin công việc đã đăng tuyển trên hệ thống.

- Cập nhật công việc

![Công ty - cập nhật công việc](https://github.com/thahhtuyenn/TranThiThanhTuyen_21020281_WWW/blob/main/TranThiThanhTuyen_LabWeek5/demo/gif/ct-cap-nhat-cv.gif)

### 6. 📝 Đăng tuyển công việc mới với các kỹ năng mong muốn
Công ty có thể đăng tuyển công việc mới và yêu cầu các kỹ năng mong muốn.

- Đăng tuyển công việc

![Công ty - đăng tuyển](https://github.com/thahhtuyenn/TranThiThanhTuyen_21020281_WWW/blob/main/TranThiThanhTuyen_LabWeek5/demo/gif/ct-dang-tuyen.gif)

### 7. 🏢 Xem và cập nhật thông tin công ty
Công ty có thể xem và cập nhật thông tin cá nhân của mình trên hệ thống.

- Cập nhật thông tin công ty

![Công ty - thông tin công ty](https://github.com/thahhtuyenn/TranThiThanhTuyen_21020281_WWW/blob/main/TranThiThanhTuyen_LabWeek5/demo/gif/ct-cap-nhat-ca-nhan.gif)

---

## Tác giả, liên hệ
- Facebook: [Trần Thị Thanh Tuyền](https://www.facebook.com/thahhtuyenn090603)
- Github: [@thahhtuyenn](https://github.com/thahhtuyenn)
- Email: thanhtuyen9623@gmail.com
