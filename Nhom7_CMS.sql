CREATE DATABASE NHOM7_CMS;
GO
USE NHOM7_CMS;
GO

-- Bảng VaiTro
CREATE TABLE VaiTro (
    MaVaiTro CHAR(256) PRIMARY KEY,
    TenVaiTro CHAR(256),
    MoTa TEXT
);

-- Bảng NguoiDung
CREATE TABLE NguoiDung (
    MaNguoiDung CHAR(256) PRIMARY KEY,
    MaVaiTro CHAR(256),
    TenDangNhap CHAR(256),
    MatKhau CHAR(256),
    HoTen CHAR(256),
    Email CHAR(256),
	NgayTao DATETIME,
    FOREIGN KEY (MaVaiTro) REFERENCES VaiTro(MaVaiTro)
);

-- Bảng ThongBao
CREATE TABLE ThongBao (
    MaThongBao CHAR(256) PRIMARY KEY,
    MaNguoiDung CHAR(256),
    TieuDe CHAR(256),
    NoiDung TEXT,
    TrangThai CHAR(256),
    FOREIGN KEY (MaNguoiDung) REFERENCES NguoiDung(MaNguoiDung)
);

-- Bảng SuKien
CREATE TABLE SuKien (
    MaSuKien CHAR(256) PRIMARY KEY,
    MaNguoiDung CHAR(256),
    TieuDe CHAR(256),
    MoTa TEXT,
    DuongDan CHAR(256),
    ThoiGianBatDau DATETIME,
    ThoiGianKetThuc DATETIME,
    DiaDiem CHAR(256),
    FOREIGN KEY (MaNguoiDung) REFERENCES NguoiDung(MaNguoiDung)
);

-- Bảng DanhMuc
CREATE TABLE DanhMuc (
    MaDanhMuc CHAR(256) PRIMARY KEY,
    Ten CHAR(256),
    DuongDan CHAR(256),
    MoTa TEXT
);

-- Bảng BaiViet
CREATE TABLE BaiViet (
    MaBaiViet CHAR(256) PRIMARY KEY,
    MaNguoiDung CHAR(256),
    MaDanhMuc CHAR(256),
    TieuDe CHAR(256),
    DuongDan CHAR(256),
    NoiDung TEXT,
	AnhDaiDien CHAR(256),
	TrangThai CHAR(256),
	FileDinhKem CHAR(256)
	NgayXuatBan DATETIME,
	NgayCapNhat DATETIME,
    FOREIGN KEY (MaNguoiDung) REFERENCES NguoiDung(MaNguoiDung),
    FOREIGN KEY (MaDanhMuc) REFERENCES DanhMuc(MaDanhMuc)
);

SELECT * FROM Menu
-- Bảng Menu
CREATE TABLE Menu (
    MaMenu CHAR(256) PRIMARY KEY,
    MaNguoiDung CHAR(256),
    Ten CHAR(256),
    ThuTuHienThi CHAR(256),
    FOREIGN KEY (MaNguoiDung) REFERENCES NguoiDung(MaNguoiDung)
);

-- Bảng Trang
CREATE TABLE Trang (
    LoaiDoiTuong CHAR(256) PRIMARY KEY,
    MaMenu CHAR(256),
    MaNguoiDung CHAR(256),
    TieuDe CHAR(256),
    DuongDan CHAR(256),
    NoiDung TEXT,
    TrangThai CHAR(256),
	NgayTao DATETIME,
    FOREIGN KEY (MaMenu) REFERENCES Menu(MaMenu),
    FOREIGN KEY (MaNguoiDung) REFERENCES NguoiDung(MaNguoiDung)
);

-- Bảng SiteInfor
CREATE TABLE SiteInfor (
    MaSiteInfor CHAR(256) PRIMARY KEY,
    TenSite CHAR(256),
    Logo CHAR(256),
    Email CHAR(256),
    SoDienThoai CHAR(256),
    Facebook CHAR(256),	
    DiaChi CHAR(256),
    NgayTao DATETIME,
    NgayCapNhat DATETIME
);

INSERT INTO VaiTro (MaVaiTro, TenVaiTro) VALUES ('VT001', 'Admin');

INSERT INTO NguoiDung (MaNguoiDung, MaVaiTro, TenDangNhap, MatKhau, HoTen, Email)
VALUES ('ND001', 'VT001', 'admin', '123456', 'Nguyen Hoang Gia Khiem', 'admin@gmail.com');

INSERT INTO Menu (MaMenu, MaNguoiDung, Ten, ThuTuHienThi)
VALUES ('MN001', 'ND001', N'Trang Chủ', 1),
       ('MN002', 'ND001', N'Giới Thiệu', 2);


