CREATE DATABASE NHOM7_CMS;
GO
USE NHOM7_CMS;
GO

-- Bảng VaiTro
CREATE TABLE VaiTro (
    MaVaiTro CHAR(256) PRIMARY KEY,
    TenVaiTro NVARCHAR(256),
    MoTa TEXT
);

-- Bảng NguoiDung
CREATE TABLE NguoiDung (
    MaNguoiDung CHAR(256) PRIMARY KEY,
    MaVaiTro CHAR(256),
    TenDangNhap NVARCHAR(256),
    MatKhau NVARCHAR(256),
    HoTen NVARCHAR(256),
    Email NVARCHAR(256),
	NgayTao DATETIME,
    FOREIGN KEY (MaVaiTro) REFERENCES VaiTro(MaVaiTro)
);
SELECT * FROM ThongBao
-- Bảng ThongBao
CREATE TABLE ThongBao (
    MaThongBao CHAR(256) PRIMARY KEY,
    MaNguoiDung CHAR(256),
    TieuDe NVARCHAR(256),
    NoiDung NVARCHAR(MAX),
    TrangThai NVARCHAR(256),
    FOREIGN KEY (MaNguoiDung) REFERENCES NguoiDung(MaNguoiDung)
);

-- Bảng SuKien
CREATE TABLE SuKien (
    MaSuKien CHAR(256) PRIMARY KEY,
    MaNguoiDung CHAR(256),
    TieuDe NVARCHAR(256),
    MoTa NVARCHAR(MAX),
    DuongDan NVARCHAR(256),
    ThoiGianBatDau DATETIME,
    ThoiGianKetThuc DATETIME,
    DiaDiem NVARCHAR(256),
    FOREIGN KEY (MaNguoiDung) REFERENCES NguoiDung(MaNguoiDung)
);

-- Bảng DanhMuc
CREATE TABLE DanhMuc (
    MaDanhMuc CHAR(256) PRIMARY KEY,
    Ten NVARCHAR(256),
    DuongDan NVARCHAR(256),
    MoTa TEXT
);

-- Bảng BaiViet
CREATE TABLE BaiViet (
    MaBaiViet CHAR(256) PRIMARY KEY,
    MaNguoiDung CHAR(256),
    MaDanhMuc CHAR(256),
    TieuDe NVARCHAR(256),
    DuongDan NVARCHAR(256),
    NoiDung NVARCHAR(MAX),
	AnhDaiDien NVARCHAR(256),
	TrangThai NVARCHAR(256),
	FileDinhKem NVARCHAR(256),
	NgayXuatBan DATETIME,
	NgayCapNhat DATETIME,
    FOREIGN KEY (MaNguoiDung) REFERENCES NguoiDung(MaNguoiDung),
    FOREIGN KEY (MaDanhMuc) REFERENCES DanhMuc(MaDanhMuc)
);

-- Bảng Menu
CREATE TABLE Menu (
    MaMenu CHAR(256) PRIMARY KEY,
    MaNguoiDung CHAR(256),
    Ten NVARCHAR(256),
    ThuTuHienThi INT,
    FOREIGN KEY (MaNguoiDung) REFERENCES NguoiDung(MaN	guoiDung)
);

-- Bảng Trang
CREATE TABLE Trang (
    MaTrang CHAR(256) PRIMARY KEY,
    MaMenu CHAR(256),
    MaNguoiDung CHAR(256),
    TieuDe NVARCHAR(256),
    DuongDan NVARCHAR(256),
    NoiDung NVARCHAR(MAX),
    TrangThai NVARCHAR(256),
	NgayTao DATETIME,
    FOREIGN KEY (MaMenu) REFERENCES Menu(MaMenu),
    FOREIGN KEY (MaNguoiDung) REFERENCES NguoiDung(MaNguoiDung)
);

-- Bảng SiteInfor
CREATE TABLE SiteInfor (
    MaSiteInfor CHAR(256) PRIMARY KEY,
    TenSite NVARCHAR(256),
    Logo NVARCHAR(256),
    Email NVARCHAR(256),
    SoDienThoai NVARCHAR(256),
    Facebook NVARCHAR(256),	
    DiaChi NVARCHAR(256),
    NgayTao DATETIME,
    NgayCapNhat DATETIME
);

-- Thêm vai trò mẫu: Admin
INSERT INTO VaiTro (MaVaiTro, TenVaiTro, MoTa)
VALUES ('VT001', N'Admin', N'Quản trị hệ thống');

-- Thêm người dùng mẫu: Admin
INSERT INTO NguoiDung (MaNguoiDung, MaVaiTro, TenDangNhap, MatKhau, HoTen, Email, NgayTao)
VALUES (
    'ND001',
    'VT001',
    N'admin',
    N'123456', -- Bạn nên hash mật khẩu trong thực tế
    N'Nguyễn Hoàng Gia Khiêm',
    N'admin@gmail.com',
    GETDATE()
);

INSERT INTO DanhMuc (MaDanhMuc, Ten, DuongDan, MoTa) VALUES
('DM01', N'Tin Tức', 'tin-tuc', N'Tin tức Khoa Công Nghệ Thông Tin, Trường Đại Học Nha Trang'),
('DM02', N'Bộ môn', 'bo-mon', N'Bộ môn khoa Công nghệ thông tin, Trường Đại Học Nha Trang');

INSERT INTO SiteInfor (MaSiteInfor, TenSite, Logo, Email, SoDienThoai, Facebook, DiaChi, NgayTao, NgayCapNhat) VALUES
('he-thong-thong-tin-quan-ly', N'CTĐT Ngành Hệ Thống Thông Tin Quản Lý', '/uploads/logo-hethongthongtinquanly.png', 'phamxuantruong@gmail.com', '0909123456', 'https://www.facebook.com/PhamXo.Truong', N'Khu đô thị Mỹ Gia, Vĩnh Thái, Nha Trang', '2025-05-31 10:43:11.713', '2025-05-31 10:43:11.713'),
('khoa-cong-nghe-thong-tin', N'Khoa Công Nghệ Thông Tin', '/uploads/logo.png', 'nhoanggiakhiem@gmail.com', '0905016508', 'https://www.facebook.com/john.nguyen.74340/', N'Phú Nông, Vĩnh Ngọc, Thành phố Nha Trang', '2025-05-29 16:48:43.827', '2025-05-29 16:48:43.827'),
('khoa-hoc-may-tinh', N'CTĐT Ngành Khoa Học Máy Tính', '/uploads/Component3(1).png', 'docaominhquan@gmail.com', '0337782571', 'https://www.facebook.com/erik.tom.2024', N'Lương Định Của, Vĩnh Ngọc, Thành phố Nha Trang', '2025-06-03 13:43:00.240', '2025-06-03 13:43:00.240');

INSERT INTO Trang (MaTrang, MaMenu, MaNguoiDung, TieuDe, DuongDan, NoiDung, TrangThai, NgayTao) VALUES
('page001', 'MN01', 'ND001', N'Trang chủ', 'trang-chu', N'<p>1</p>', 'PUBLISH', '2025-05-31 15:55:55.707'),
('page002', 'MN01', 'ND001', N'Giới thiệu', 'gioi-thieu', N'<h3>GIỚI THIỆU</h3><p>Tiền thân của Khoa Công nghệ Thông tin Trường Đại Học Nha Trang là bộ môn Tin học. Năm 1993, ngành Công nghệ Thông tin được đào tạo tại Trường Đại Học Nha Trang với sự liên kết đào tạo giữa Trường Đại Học Nha Trang và Trường Đại Học Bách Khoa Hà Nội. Sự liên kết này đã tạo điều kiện thúc đẩy công tác đào tạo ngành Công nghệ thông tin Trường Đại Học Nha Trang phát triển.</p><p>Với sự phát triển nhanh của công nghệ thông tin trong cả nước, năm 2000 Bộ Giáo dục &amp; Đào tạo cho phép Trường Đại Học Nha Trang độc lập đào tạo ngành Công nghệ Thông tin. Ngày 17/01/2003 Khoa Công nghệ thông tin được thành lập với 3 bộ môn: Khoa học máy tính, Hệ thống thông tin và Công nghệ tri thức. Từ năm 2006, Khoa Công nghệ Thông tin có 4 bộ môn: Khoa học máy tính, Hệ thống thông tin, Mạng máy tính &amp; truyền thông và Kỹ thuật phần mềm. Đầu tháng 11/2011, Bộ môn Khoa học máy tính sát nhập vào Bộ môn Kỹ thuật phần mềm; Khoa tiếp nhận Bộ môn Toán từ Khoa khoa học cơ bản. Cùng với vị thế ngày càng đi lên của Trường Đại học Nha Trang anh hùng, Khoa Công nghệ Thông tin phát triển không ngừng trở thành cơ sở đào tạo Công nghệ thông tin uy tín trong tỉnh Khánh Hòa và cả nước.</p><p>Kế thừa và phát huy những thành quả đạt được, Khoa Công nghệ thông tin được Trường Đại Học Nha Trang chú trọng đầu tư, xây dựng để trở thành một khoa trọng điểm của trường, phục vụ kế hoạch phát triển nguồn nhân lực Công nghệ thông tin trong tỉnh Khánh Hòa nói riêng và cả nước nói chung.</p><h2>&nbsp;</h2><h3>CHỨC NĂNG</h3><p>Khoa Công nghệ Thông tin có chức năng đào tạo, nghiên cứu khoa học và chuyển giao công nghệ trong lĩnh vực Công nghệ thông tin với các nhiệm vụ:</p><p>– Đào tạo nguồn nhân lực ở các bậc học về Công nghệ thông tin phục vụ sự nghiệp phát triển kinh tế – xã hội của khu vực Nam Trung Bộ và Tây Nguyên.</p><p>– Giảng dạy các môn học thuộc lĩnh vực Công nghệ thông tin cho các ngành đào tạo của Trường Đại học Nha Trang.</p><p>– Nghiên cứu và triển khai các đề tài khoa học về Công nghệ thông tin.</p><p>– Xây dựng, thực hiện hoặc tư vấn thực hiện các dự án ứng dụng Công nghệ thông tin trong quản lý, sản xuất, kinh doanH</p><p><img src="https://khoacntt.ntu.edu.vn/portals/54/20%20nam%20thanh%20lap%20khoa%20cntt.jpg" alt=""></p>', 'PUBLISH', '2025-05-31 15:56:02.547'),
('page003', 'MN01', 'ND001', N'Bộ môn', 'bo-mon', NULL, 'PUBLISH', '2025-06-01 20:49:16.913'),
('page004', 'MN01', 'ND001', N'Đào tạo', 'dao-tao', N'<p>Chương trình đào tạo</p>', 'PUBLISH', '2025-05-31 16:02:41.200'),
('page005', 'MN01', 'ND001', N'Nghiên cứu khoa học', 'nghien-cuu-khoa-hoc', N'<figure class="table"><table><thead><tr><th>STT</th><th>&nbsp;<strong>Mã số –&nbsp;Tên đề tài</strong></th><th>Chủ nhiệm</th></tr></thead><tbody><tr><td colspan="3"><strong>Năm 2022</strong></td></tr><tr><td>1</td><td>TR2022-13-03:&nbsp;Xây dựng hệ thống thử nghiệm nhận dạng bệnh tôm dựa trên kỹ thuật thị giác máy tính</td><td>TS. Nguyễn Đình Hưng</td></tr>...[Rút gọn nội dung nếu cần thiết để tránh lỗi quá dài trong SQL Editor]', 'PUBLISH', '2025-06-01 20:56:23.267'),
('page006', 'MN01', 'ND001', N'Liên hệ', 'lien-he', N'<h4>KHOA CÔNG NGHỆ THÔNG TIN</h4><p>Trưởng khoa: TS. Phạm Thị Thu Thúy</p><ul><li>Tầng 7, Nhà Đa Năng. 02 Tôn Thất Tùng</li><li><a href="tel:02582471367%C2%A0">02582471367&nbsp;</a></li><li><a href="mailto:kcntt@ntu.edu.vn">kcntt@ntu.edu.vn</a> <a href="http://khoacntt.ntu.edu.vn/">khoacntt.ntu.edu.vn</a></li></ul>', 'PUBLISH', '2025-06-01 20:53:08.553');



