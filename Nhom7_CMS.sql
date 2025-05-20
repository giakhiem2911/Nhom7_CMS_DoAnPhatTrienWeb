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
    NoiDung TEXT,
    TrangThai NVARCHAR(256),
    FOREIGN KEY (MaNguoiDung) REFERENCES NguoiDung(MaNguoiDung)
);

-- Bảng SuKien
CREATE TABLE SuKien (
    MaSuKien CHAR(256) PRIMARY KEY,
    MaNguoiDung CHAR(256),
    TieuDe NVARCHAR(256),
    MoTa TEXT,
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
    NoiDung TEXT,
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
    LoaiDoiTuong CHAR(256) PRIMARY KEY,
    MaMenu CHAR(256),
    MaNguoiDung CHAR(256),
    TieuDe NVARCHAR(256),
    DuongDan NVARCHAR(256),
    NoiDung TEXT,
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



