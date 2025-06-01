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
    FOREIGN KEY (MaNguoiDung) REFERENCES NguoiDung(MaNguoiDung)
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

INSERT INTO ThongBao (MaThongBao, MaNguoiDung, TieuDe, NoiDung, TrangThai) VALUES
('TB001', 'ND001', 'Chào mừng đến với hệ thống', 'Chúc mừng bạn đã đăng ký thành công tài khoản tại hệ thống quản lý bài viết thú cưng.', 'Chưa đọc'),
('TB002', 'ND002', 'Cập nhật mật khẩu', 'Vui lòng cập nhật mật khẩu mới để tăng cường bảo mật cho tài khoản của bạn.', 'Đã đọc'),
('TB003', 'ND003', 'Thông báo bảo trì', 'Hệ thống sẽ được bảo trì vào lúc 00:00 ngày 01/06/2025. Vui lòng lưu lại dữ liệu.', 'Chưa đọc'),
('TB004', 'ND001', 'Thành viên tích cực', 'Bạn là thành viên có nhiều bài viết chất lượng nhất trong tháng 5. Cảm ơn bạn!', 'Đã đọc'),
('TB005', 'ND002', 'Đổi ảnh đại diện', 'Ảnh đại diện của bạn không hợp lệ. Vui lòng chọn ảnh khác.', 'Chưa đọc');
