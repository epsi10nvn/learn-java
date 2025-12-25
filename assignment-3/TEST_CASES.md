# Test Cases - Hệ thống Quản lý Đơn hàng

## 1. Test Cases cho Product Model

### TC-P001: Tạo sản phẩm hợp lệ
- **Input**: code="P001", name="Laptop", price=15000000, stock=10
- **Expected**: Tạo thành công Product với id tự động tăng

### TC-P002: Tạo sản phẩm với giá <= 0
- **Input**: code="P002", name="Mouse", price=0, stock=5
- **Expected**: Throw IllegalArgumentException("Price must be greater than 0")

### TC-P003: Tạo sản phẩm với stock < 0
- **Input**: code="P003", name="Keyboard", price=500000, stock=-1
- **Expected**: Throw IllegalArgumentException("Stock must be greater or equals 0")

### TC-P004: Tạo sản phẩm với code rỗng
- **Input**: code="", name="Monitor", price=5000000, stock=5
- **Expected**: Throw IllegalArgumentException("Product code cannot be null or empty")

### TC-P005: Tạo sản phẩm với name rỗng
- **Input**: code="P005", name="", price=1000000, stock=3
- **Expected**: Throw IllegalArgumentException("Product name cannot be null or empty")

### TC-P006: reduceStock với quantity hợp lệ
- **Setup**: Product với stock=10
- **Input**: reduceStock(5)
- **Expected**: stock giảm còn 5

### TC-P007: reduceStock với quantity > stock
- **Setup**: Product với stock=5
- **Input**: reduceStock(10)
- **Expected**: Throw InsufficientStockException

### TC-P008: reduceStock với quantity <= 0
- **Setup**: Product với stock=10
- **Input**: reduceStock(0)
- **Expected**: Throw IllegalArgumentException("Quantity must be greater than 0")

### TC-P009: increaseStock với quantity hợp lệ
- **Setup**: Product với stock=10
- **Input**: increaseStock(5)
- **Expected**: stock tăng lên 15

### TC-P010: increaseStock với quantity <= 0
- **Setup**: Product với stock=10
- **Input**: increaseStock(0)
- **Expected**: Throw IllegalArgumentException("Quantity must be greater than 0")

---

## 2. Test Cases cho Customer Model

### TC-C001: Tạo khách hàng hợp lệ
- **Input**: code="C001", name="Nguyễn Văn A", email="nguyenvana@email.com"
- **Expected**: Tạo thành công Customer với totalSpending=0.0

### TC-C002: Tạo khách hàng với code rỗng
- **Input**: code="", name="Trần Thị B", email="tranthib@email.com"
- **Expected**: Throw IllegalArgumentException("Customer code cannot be null or empty")

### TC-C003: Tạo khách hàng với email không hợp lệ
- **Input**: code="C003", name="Lê Văn C", email="invalidemail"
- **Expected**: Throw IllegalArgumentException("Invalid email format")

### TC-C004: addSpending với số tiền hợp lệ
- **Setup**: Customer với totalSpending=0
- **Input**: addSpending(1000000)
- **Expected**: totalSpending=1000000

### TC-C005: addSpending với số tiền âm
- **Setup**: Customer với totalSpending=500000
- **Input**: addSpending(-100000)
- **Expected**: Throw IllegalArgumentException("Spending amount cannot be negative")

---

## 3. Test Cases cho Order Model

### TC-O001: Tạo đơn hàng hợp lệ
- **Input**: Customer hợp lệ
- **Expected**: Tạo Order với status=PENDING, items rỗng

### TC-O002: Tạo đơn hàng với customer null
- **Input**: customer=null
- **Expected**: Throw IllegalArgumentException("Customer cannot be null")

### TC-O003: Thêm OrderItem vào đơn hàng PENDING
- **Setup**: Order với status=PENDING
- **Input**: addItem(OrderItem hợp lệ)
- **Expected**: Item được thêm vào danh sách

### TC-O004: Thêm OrderItem vào đơn hàng CONFIRMED
- **Setup**: Order với status=CONFIRMED
- **Input**: addItem(OrderItem hợp lệ)
- **Expected**: Throw IllegalArgumentException("Cannot add items to a non-pending order")

### TC-O005: Thêm OrderItem null
- **Setup**: Order với status=PENDING
- **Input**: addItem(null)
- **Expected**: Throw IllegalArgumentException("Order item cannot be null")

### TC-O006: Tính tổng tiền đơn hàng
- **Setup**: Order với 2 items: item1(subtotal=100000), item2(subtotal=200000)
- **Expected**: getTotal() = 300000

### TC-O007: Xác nhận đơn hàng PENDING
- **Setup**: Order với status=PENDING, có items
- **Input**: confirm()
- **Expected**: status=CONFIRMED

### TC-O008: Xác nhận đơn hàng CONFIRMED
- **Setup**: Order với status=CONFIRMED
- **Input**: confirm()
- **Expected**: Throw IllegalArgumentException("Only pending orders can be confirmed")

### TC-O009: Xác nhận đơn hàng rỗng
- **Setup**: Order với status=PENDING, không có items
- **Input**: confirm()
- **Expected**: Throw IllegalArgumentException("Cannot confirm an empty order")

### TC-O010: getItems trả về defensive copy
- **Setup**: Order với items
- **Input**: getItems(), sau đó modify list
- **Expected**: Original items list không bị thay đổi

---

## 4. Test Cases cho OrderItem Model

### TC-OI001: Tạo OrderItem hợp lệ
- **Input**: Product hợp lệ, quantity=5
- **Expected**: Tạo OrderItem với unitPrice = product.getPrice()

### TC-OI002: Tạo OrderItem với product null
- **Input**: product=null, quantity=3
- **Expected**: Throw IllegalArgumentException("Product cannot be null")

### TC-OI003: Tạo OrderItem với quantity <= 0
- **Input**: Product hợp lệ, quantity=0
- **Expected**: Throw IllegalArgumentException("Quantity must be greater than 0")

### TC-OI004: Tính subtotal
- **Setup**: OrderItem với unitPrice=100000, quantity=3
- **Expected**: getSubtotal() = 300000

---

## 5. Test Cases cho ProductService

### TC-PS001: Thêm sản phẩm mới
- **Input**: addProduct("P001", "Laptop", 15000000, 10)
- **Expected**: Sản phẩm được thêm vào danh sách

### TC-PS002: Thêm sản phẩm với code và name giống (cộng stock)
- **Setup**: Đã có sản phẩm với code="P001", name="Laptop", stock=10
- **Input**: addProduct("P001", "Laptop", 15000000, 5)
- **Expected**: Sản phẩm hiện có stock tăng lên 15, trả về sản phẩm hiện có

### TC-PS003: Thêm sản phẩm với code giống nhưng name khác
- **Setup**: Đã có sản phẩm với code="P001", name="Laptop"
- **Input**: addProduct("P001", "Desktop", 12000000, 5)
- **Expected**: Throw IllegalArgumentException("Product code already exists with different name: P001")

### TC-PS004: Tìm sản phẩm theo code tồn tại
- **Setup**: Có sản phẩm với code="P001"
- **Input**: findProductByCode("P001")
- **Expected**: Trả về Product với code="P001"

### TC-PS005: Tìm sản phẩm theo code không tồn tại
- **Input**: findProductByCode("P999")
- **Expected**: Throw ProductNotFoundException với message chứa "code 'P999' was not found"

### TC-PS006: Tìm sản phẩm theo code null hoặc rỗng
- **Input**: findProductByCode(null) hoặc findProductByCode("")
- **Expected**: Throw IllegalArgumentException("Product code cannot be null or empty")

### TC-PS007: Tìm kiếm sản phẩm theo tên (không phân biệt hoa thường)
- **Setup**: Có sản phẩm "Laptop Dell", "laptop HP", "Mouse"
- **Input**: searchProductsByName("laptop")
- **Expected**: Trả về 2 sản phẩm: "Laptop Dell", "laptop HP"

### TC-PS008: Tìm kiếm sản phẩm theo tên rỗng
- **Input**: searchProductsByName("") hoặc searchProductsByName(null)
- **Expected**: Trả về danh sách rỗng

### TC-PS009: Tìm sản phẩm theo khoảng giá
- **Setup**: Có sản phẩm với giá 1000000, 5000000, 15000000
- **Input**: findProductsByPriceRange(2000000, 10000000)
- **Expected**: Trả về sản phẩm có giá 5000000

### TC-PS010: Tìm sản phẩm với minPrice > maxPrice
- **Input**: findProductsByPriceRange(10000000, 5000000)
- **Expected**: Throw IllegalArgumentException("Min price cannot be greater than max price")

### TC-PS011: Tìm sản phẩm với price âm
- **Input**: findProductsByPriceRange(-1000, 5000000)
- **Expected**: Throw IllegalArgumentException("Price cannot be negative")

### TC-PS012: Lấy tất cả sản phẩm
- **Setup**: Có 3 sản phẩm
- **Input**: getAllProducts()
- **Expected**: Trả về danh sách 3 sản phẩm (defensive copy)

---

## 6. Test Cases cho CustomerService

### TC-CS001: Thêm khách hàng mới
- **Input**: addCustomer("C001", "Nguyễn Văn A", "nguyenvana@email.com")
- **Expected**: Khách hàng được thêm vào danh sách

### TC-CS002: Thêm khách hàng với code trùng
- **Setup**: Đã có khách hàng với code="C001"
- **Input**: addCustomer("C001", "Trần Thị B", "tranthib@email.com")
- **Expected**: Throw IllegalArgumentException("Customer code already exists: C001")

### TC-CS003: Thêm khách hàng với email trùng
- **Setup**: Đã có khách hàng với email="test@email.com"
- **Input**: addCustomer("C002", "Lê Văn C", "test@email.com")
- **Expected**: Throw IllegalArgumentException("Customer email already exists: test@email.com")

### TC-CS004: Tìm khách hàng theo code tồn tại
- **Setup**: Có khách hàng với code="C001"
- **Input**: findCustomerByCode("C001")
- **Expected**: Trả về Customer với code="C001"

### TC-CS005: Tìm khách hàng theo code không tồn tại
- **Input**: findCustomerByCode("C999")
- **Expected**: Throw CustomerNotFoundException với message chứa "code 'C999' was not found"

### TC-CS006: Tìm khách hàng theo code null hoặc rỗng
- **Input**: findCustomerByCode(null) hoặc findCustomerByCode("")
- **Expected**: Throw IllegalArgumentException("Customer code cannot be null or empty")

### TC-CS007: Lấy top N khách hàng chi tiêu nhiều nhất
- **Setup**: 
  - Customer1: totalSpending=5000000
  - Customer2: totalSpending=10000000
  - Customer3: totalSpending=3000000
- **Input**: getTopCustomersBySpending(2)
- **Expected**: Trả về [Customer2, Customer1] (theo thứ tự giảm dần)

### TC-CS008: Lấy top N với N <= 0
- **Input**: getTopCustomersBySpending(0)
- **Expected**: Throw IllegalArgumentException("Top N must be greater than 0")

### TC-CS009: Lấy tất cả khách hàng
- **Setup**: Có 3 khách hàng
- **Input**: getAllCustomers()
- **Expected**: Trả về danh sách 3 khách hàng (defensive copy)

---

## 7. Test Cases cho OrderService

### TC-OS001: Tạo đơn hàng mới
- **Setup**: Có Customer với code="C001"
- **Input**: createOrder("C001")
- **Expected**: Tạo Order với customer có code="C001", status=PENDING

### TC-OS002: Tạo đơn hàng với customer code không tồn tại
- **Input**: createOrder("C999")
- **Expected**: Throw CustomerNotFoundException

### TC-OS003: Thêm sản phẩm vào đơn hàng PENDING
- **Setup**: Order với status=PENDING, Product với stock=10
- **Input**: addProductToOrder(orderId, "P001", 5)
- **Expected**: OrderItem được thêm vào đơn hàng

### TC-OS004: Thêm sản phẩm với số lượng > stock
- **Setup**: Order PENDING, Product với stock=5
- **Input**: addProductToOrder(orderId, "P001", 10)
- **Expected**: Throw InsufficientStockException

### TC-OS005: Thêm sản phẩm vào đơn hàng CONFIRMED
- **Setup**: Order với status=CONFIRMED
- **Input**: addProductToOrder(orderId, "P001", 3)
- **Expected**: Throw InvalidOrderStateException

### TC-OS006: Thêm sản phẩm với product code không tồn tại
- **Setup**: Order PENDING
- **Input**: addProductToOrder(orderId, "P999", 3)
- **Expected**: Throw ProductNotFoundException

### TC-OS007: Thêm sản phẩm với order ID không tồn tại
- **Input**: addProductToOrder(999, "P001", 3)
- **Expected**: Throw OrderNotFoundException

### TC-OS008: Xác nhận đơn hàng PENDING
- **Setup**: 
  - Order PENDING với items
  - Product1: stock=10, quantity trong order=5
  - Product2: stock=8, quantity trong order=3
- **Input**: confirmOrder(orderId)
- **Expected**: 
  - Order status=CONFIRMED
  - Product1 stock=5, Product2 stock=5
  - Customer totalSpending được cập nhật

### TC-OS009: Xác nhận đơn hàng CONFIRMED
- **Setup**: Order với status=CONFIRMED
- **Input**: confirmOrder(orderId)
- **Expected**: Throw InvalidOrderStateException

### TC-OS010: Xác nhận đơn hàng với stock không đủ
- **Setup**: Order PENDING, Product với stock=3, quantity trong order=5
- **Input**: confirmOrder(orderId)
- **Expected**: Throw InsufficientStockException

### TC-OS011: Xác nhận đơn hàng với order ID không tồn tại
- **Input**: confirmOrder(999)
- **Expected**: Throw OrderNotFoundException

### TC-OS012: Lấy danh sách đơn hàng của khách hàng
- **Setup**: 
  - Customer1 (code="C001") có 2 orders
  - Customer2 (code="C002") có 1 order
- **Input**: getOrdersByCustomer("C001")
- **Expected**: Trả về 2 orders của Customer1

### TC-OS013: Lấy danh sách đơn hàng với customer code không tồn tại
- **Input**: getOrdersByCustomer("C999")
- **Expected**: Throw CustomerNotFoundException

### TC-OS014: Tính tổng doanh thu từ đơn đã xác nhận
- **Setup**: 
  - Order1: status=CONFIRMED, total=1000000
  - Order2: status=CONFIRMED, total=2000000
  - Order3: status=PENDING, total=500000
- **Input**: getTotalRevenueFromConfirmedOrders()
- **Expected**: Trả về 3000000 (chỉ tính Order1 và Order2)

### TC-OS015: Tìm đơn hàng theo ID tồn tại
- **Setup**: Có Order với id=1
- **Input**: findOrderById(1)
- **Expected**: Trả về Order với id=1

### TC-OS016: Tìm đơn hàng theo ID không tồn tại
- **Input**: findOrderById(999)
- **Expected**: Throw OrderNotFoundException với message chứa "ID '999' was not found"

### TC-OS017: Lấy tất cả đơn hàng
- **Setup**: Có 3 đơn hàng
- **Input**: getAllOrders()
- **Expected**: Trả về danh sách 3 đơn hàng (defensive copy)



