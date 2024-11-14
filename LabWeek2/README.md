# 🗓 Lab week 2
## 📝Project sử dụng rest API để kết nói CSDL và đưa ra dữ liệu mong muốn dưới dạng API, ở phía frontend có thể lấy dữ liệu thông qua API mà không phải liên kết trực tiếp đến tần logic.
## 🗞Nội dung của project
### REST API
- Các thao tác CRUD cho các đối tượng, lập order. (dùng JPA).
### FRONTEND
- Tạo các trang web cho việc hiển thị sản phẩm, chọn vào giỏ hàng và thanh toán
## 🛠 Hướng dẫn cài đặt
##### 1. Clone project từ github
```bash
git clone https://github.com/thahhtuyenn/TranThiThanhTuyen_21020281_WWW.git
```
##### 2. Sử dụng intelij để mở project LabWeek2
##### 3. Từ projectt LabWeek1, sử dụng file `database-lab-week-2.sql` để tạo các bảng
[Database](https://github.com/thahhtuyenn/TranThiThanhTuyen_21020281_WWW/blob/main/LabWeek2/database/database-lab-week-2.sql) hoặc `database/database-lab-week-2.sql` 
##### 4. Add payara server (6.19.0 - version được sử dụng để tạo ra project)
##### 5. Run project lên và sẽ tự nhảy sang trang login với đường dẫn `http://localhost:8080/LabWeek2-1.0-SNAPSHOT/login`
## 🌐 Demo ứng dụng
### Các đường dẫn API get dữ liệu
#### Customer
- Get all 
```bash
http://localhost:8080/LabWeek2-1.0-SNAPSHOT/api/customers
```
- Get by id (id: int)
```bash
http://localhost:8080/LabWeek2-1.0-SNAPSHOT/api/customers/{id}
```
#### Employee
- Get all 
```bash
http://localhost:8080/LabWeek2-1.0-SNAPSHOT/api/employees
```
- Get by id (id: int)
```bash
http://localhost:8080/LabWeek2-1.0-SNAPSHOT/api/employees/{id}
```
- Get by status (status: {ACTIVE, IN_ACTIVE, TERMINATED})
```bash
http://localhost:8080/LabWeek2-1.0-SNAPSHOT/api/employees/status/{status}
```
#### Product
- Get all
```bash
http://localhost:8080/LabWeek2-1.0-SNAPSHOT/api/products
```
- Get by status and last price (status: {ACTIVE, IN_ACTIVE, TERMINATED})
```bash
http://localhost:8080/LabWeek2-1.0-SNAPSHOT/api/products/statusAndPrice/status={status}
```
#### Order
- Get all
```bash
http://localhost:8080/LabWeek2-1.0-SNAPSHOT/api/orders
```
- Get by id (id: int)
```bash
http://localhost:8080/LabWeek2-1.0-SNAPSHOT/api/orders/{id}
```
### Ứng dụng cho phép nhân viên login vào vưới số điện thoại để tạo order trên giao diện website
- Số điện thoại demo
```bash
0376225886
```
![Login](https://github.com/thahhtuyenn/TranThiThanhTuyen_21020281_WWW/blob/main/LabWeek2/demo/gif/lab2-login-employee.gif)
- Sau khi login website hiển thị các sản phẩm ra màn hình, nhân viên có thể chọn sản phẩm và số lượng sản phần 1 -10, nếu chọnt rùng thì giỏ hàng sẽ tăng số lượng sản phẩm đó lên 
![Choose product](https://github.com/thahhtuyenn/TranThiThanhTuyen_21020281_WWW/blob/main/LabWeek2/demo/gif/lab2-choose-product.gif)
- Cho phép người dùng remove sản phẩm nếu không cần thiết nữa và khi nhấn order sẽ chuyển sang trang đẻ lập hóa đơn
![Remove product](https://github.com/thahhtuyenn/TranThiThanhTuyen_21020281_WWW/blob/main/LabWeek2/demo/gif/lab2-remove-product-order.gif)
- Tìm kiếm khách hàng cũ bằng số điện thoại để phục vụ cho việc tích điểm
![Customer exists](https://github.com/thahhtuyenn/TranThiThanhTuyen_21020281_WWW/blob/main/LabWeek2/demo/gif/lab2-create-order-cust-exists.gif)
- Nếu số điện thoại không tìm thấy thì có thể thêm thông tin khách hàng bằng cách thêm khách hàng mới
![Customer new](https://github.com/thahhtuyenn/TranThiThanhTuyen_21020281_WWW/blob/main/LabWeek2/demo/gif/lab2-create-order-cust-new.gif)

### 📂Tài liệu tham khảo 
- [Java Servlet Documentation](https://docs.oracle.com/javaee/7/tutorial/servlets.htm)
- [Bootstrap 5 W3Schools](https://www.w3schools.com/bootstrap5/) 
## Tác giả, liên hệ
- Facebook: [Trần Thị Thanh Tuyền](https://www.facebook.com/thahhtuyenn090603)
- Github: [@thahhtuyenn](https://github.com/thahhtuyenn)
- Email: thanhtuyen9623@gmail.com